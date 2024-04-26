---
title: '[SpringBoot] XSS Filter Reponse에서 처리하기'
category: SpringBoot
tags:
  - XSS
---

오늘은 **XSS(Cross-site Scripting) Filter**에 대해서 알아보고 그에 따른 해결법에 대해서 포스팅하고자 한다.

**XSS(Cross-site Scripting)** 공격이란 무엇을 말하는지 간단하게 설명하자면 클라이언트에서

서버로 스크립트를 삽입하여 데이터 전달을 하여 응답 값에 해당 스크립트가 화면에 직접적으로 뿌려져

해당 스크립트가 실행되는 공격이며 이와 같은 공격 대비법으로는 대표적으로 Request에 전달되어져 오는

데이터를 Filter단 에서 미리 체크하여 HTML 관련 태그를 Escape하는 방법이 있다.

그러나 나의 경우에도 위와 같은 방법으로 **XSS(Cross-site Scripting) Filter**를 처리하고 있었으나

Spring Boot Version UP을 하는 과정에서 해당 Filter가 정상적으로 작동하지 않아

해당 문제점에 대해서 파악해본 바로는 **Spring Boot 2.1.X -> Spring Boot 2.4.X**

Version UP 과정에서 **HttpsServletRequest** 부모 클래스의 구현 법이 조금 달라진 것으로 파악된다.

구글링을 통해 문제점을 고치려 했지만 쉽지 않았고 다른 대안을 찾아서 구현하기로 했다.

그래서 구현하기로 한 게 Request에서 데이터를 Escape 하는 것이 아닌 Reponse에서 최종적으로

데이터가 내려보내질 때 Escape 하는 방법으로 진행하기로 했다.

구현한 코드는 아래와 같다.

### 1. build.gradle 해당 Library 추가

```gradle
/*--------------------------[ commons-text ]------------------------------*/
implementation 'org.apache.commons:commons-text:1.9'
/*-----------------------------------------------------------------------*/
```

### 2. HTMLCharacterEscapes 클래스 생성

```java
public class HTMLCharacterEscapes extends CharacterEscapes {

    private final int[] asciiEscapes;

    public HTMLCharacterEscapes() {

        // 1. XSS 방지 처리할 특수 문자 지정
        asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;

    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }

    @Override
    public SerializableString getEscapeSequence(int ch) {
        return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString((char) ch)));
    }
}
```

### 3. WebMvcConfigurer 인터페이스 상속 받아 MappingJackson2HttpMessageConverter 재정의

```java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
        // MappingJackson2HttpMessageConverter Default ObjectMapper 설정 및 ObjectMapper Config 설정
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

}
```

여기서 한 가지 추가로 설명하자면 처음에는 아래와 같은 코드로 ObjectMapper 객체를 생성해 주었으나

```java
ObjectMapper objectMapper = new ObjectMapper();
```

기존의 정상적으로 Mapping 되던 객체가 정상적으로 Mapping이 되지 않아

**MappingJackson2HttpMessageConverter** 해당 클래스의 Default ObjectMapper를 찾아보게 되었고

**MappingJackson2HttpMessageConverter** 클래스의 생성자를 보면 아래와 같은 코드로

ObjectMapper를 생성해주는 점을 참고하여 사용하였다.

```java
Jackson2ObjectMapperBuilder.json().build();
```

그 결과 사이드 이펙트 또한 발생하지 않고 정상적으로 XSS 공격을 막을 수 있게 되었다.

이제 간단한 코드를 통해 정상적으로 Escape 처리가 되어지는 확인해보자.

### 4. Testing

```java
@GetMapping(value = "/responseXss")
Map<String, Object> responseXss(){
    Map<String, Object> resultMap = new HashMap<>();

    resultMap.put("htmlTdTag", "<td></td>");
    resultMap.put("htmlTableTag", "<table>");

    return resultMap;
}
```

```html
{"htmlTdTag":"&lt;td&gt;&lt;/td&gt;","htmlTableTag":"&lt;table&gt;"}
```

정상적으로 Escape 돼서 처리된 것을 확인할 수 있다.

마지막으로 당연한 말일 수도 있지만 기존의 서버에서 Response로 HTML 코드를 내려보내는 코드가 있다면

우선적으로 리팩토링을 거쳐 해당 부분을 걷어내고 진행 줘야 한다.

---

### 5. 추가 이모지 이슈

실제 운영을 하다 보니 윈도우 이모지 관련하여 파싱 중에 에러가 발생하는 케이스가 발견하였다.

아래와 같은 코드로 수정하여 주면 이모지 관련한 이슈는 해결하여 추가로 작성한다.

```java
@Override
public SerializableString getEscapeSequence(int ch) {
    SerializedString serializedString;
    char charAt = (char) ch;
    if (Character.isHighSurrogate(charAt) || Character.isLowSurrogate(charAt)) {
        StringBuilder sb = new StringBuilder();
        sb.append("\\u");
        sb.append(String.format("%04x", ch));
        serializedString = new SerializedString(sb.toString());
    } else {
        serializedString = new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString(charAt)));
    }
    return serializedString;
}
```

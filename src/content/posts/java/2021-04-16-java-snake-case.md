---
title: '[Java] @JsonProperty와 @JsonNaming의 사용법'
categories:
  - Java
tags:
  - JsonProperty
  - JsonNaming
---

오늘은 **@JsonProperty**와 **@JsonNaming**의 사용법에 대해서 알아보려고 한다.

간단하게 해당 어노테이션을 사용하는 이유로는 API 통신 시 Json 객체를 내려보낼 때

실제 클래스의 필드명과 다르게 내보내려고 사용한다.

나 같은 경우는 타사와 API 연동을 해야 하는 상황에서 타사의 API 가이드 문서에서는

요청/응답 파라미터의 네이밍 규칙이 **snake_case** 기법을 사용하였다.

그에 반해 우리 회사에서는 클래스 필드 네이밍 규칙을 **camelCase** 가져가고 있었다.

이렇게 되면은 일단 API 연동이 최우선이니 타사의 API 가이드에 따라서 만들 수밖에 없다

여기서 사용할 수 있는 게 위의 두 개의 어노테이션이다.

자 이제 그럼 해당 어노테이션 사용법에 대해서 알아보자.

우선적으로 나의 경우를 대입하여 클래스의 필드명은 **camelCase** 네이밍 규칙을

응답 파라미터에서 **snake_case** 네이밍 규칙을 가져가겠다.

### 1. @JsonProperty

#### @JsonProperty 선언

```java
@Data
public class JsonData1 {
    @JsonProperty("json_data1")
    private String jsonData1;
    @JsonProperty("json_data2")
    private String jsonData2;
}
```

#### Controller 테스트

```java
@GetMapping(value = "/jsonproperty")
public JsonData1 jsonproperty() {
    JsonData1 jsonData1 = new JsonData1();
    jsonData1.setJsonData1("123");
    jsonData1.setJsonData2("456");
    return jsonData1;
}
```

#### 응답 결과

```console
{"json_data1":"123","json_data2":"456"}
```

---

### 2. @JsonNaming

#### @JsonNaming 선언

```java
@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class JsonData2 {
    private String jsonData1;
    private String jsonData2;
}
```

#### Controller 테스트

```java
@GetMapping(value = "/jsonnaming")
public JsonData2 jsonnaming() {
    JsonData2 jsonData2 = new JsonData2();
    jsonData2.setJsonData1("가나다");
    jsonData2.setJsonData2("마바사");
    return jsonData2;
}
```

#### 응답 결과

```console
{"json_data1":"가나다","json_data2":"마바사"}
```

위와 같이 간단하게 사용법을 알아보았다.

일단 보편적으로 **@JsonProperty**를 사용한다.

명칭도 정확하게 보일뿐더러 공통된 규칙이 아닌 상황에서도 자유롭게 사용할 수 있다.

나의 경우는 **camelCase** -> **snake_case** 기법으로만 변경하면 되니

필드명에 하나씩 선언해 주는 것보단 **@JsonNaming** 사용하여

일괄적으로 변경해 주는 게 코드도 더 깔끔하고 반복적인 작업을 하지 않아도 돼서

**@JsonNaming** 사용하여 구현하였다.

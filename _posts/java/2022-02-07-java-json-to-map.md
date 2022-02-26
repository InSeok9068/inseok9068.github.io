---
title: "[Java] 객체 혹은 JSON String Map으로 변환"
categories:
  - Java
tags:
  - jackson
  - JSON
  - Map
---

오늘은 Jackson 라이브러리를 통해 객체 혹은 JSON String을 Map 클래스로 변환해 보는 법을 간단하게 설명하려 한다.

아래와 같은 코드가 가장 간단하게 구현할 수 있는 코드이다.

그러나 해당 코드를 사용 시 보통 IDE에서 타입 체크를 해주어 경고 표시가 뜨게 된다

```java
Map<String, String> map = new ObjectMapper().readValue(json, Map.class);
```

아래와 같은 코드로 변경을 하면 타입에 관련된 경고가 없어지는 걸 볼 수 있다

```java
// 타입 선언
Map<String, String> map = new ObjectMapper().readValue(json, new TypeReference<Map<String, String>>() {})
// 타입을 선언하지 않아도 무방
Map<String, String> map = new ObjectMapper().readValue(json, new TypeReference<>() {})
```

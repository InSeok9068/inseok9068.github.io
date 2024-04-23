---
title: '[Java] Map, List 클래스 Builder 생성자 사용하여 생성하기'
categories:
  - Java
tags:
  - Map
  - List
  - Builder
---

오늘은 간단하게 Map 클래스와 List 클래스를 Builder 패턴의 생성자를 이용하여 객체를 생성하는 방법을 알아보려 한다.

평소에 객체 생성 시 Builder 패턴으로 하여 객체 생성을 자주 하는데 흔히들 자주 사용하는 Map 또는 List는

HashMap, ArrayList의 기본 생성자로 객체 생성 후 값을 대입하는 방식으로 사용할 것이다.

아래와 같은 방식으로 하여 Builder 패턴으로 객체 생성이 가능하다.

### 1. Map

#### 기본 생성자

```java
Map<String, Object> mapConstructor = new HashMap<>();
mapConstructor.put("name", "홍길동");
mapConstructor.put("age", "19");
```

#### Builder 패턴

```java
Map<String, Object> mapBuilder = ImmutableMap.<String, Object>builder()
        .put("name", "홍길동")
        .put("age", "19")
        .build();
```

---

### 2. List

#### 기본 생성자

```java
List<Integer> numberListConstructor = new ArrayList<>();
numberListConstructor.add(1);
numberListConstructor.add(2);
```

#### Builder 패턴

```java
List<Integer> listBuilder = ImmutableList.<Integer>builder()
        .add(1)
        .add(2)
        .build();
```

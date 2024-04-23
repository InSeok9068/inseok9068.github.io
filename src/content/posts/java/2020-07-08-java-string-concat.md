---
title: '[Java] 문자열 연결 3가지 방법'
categories:
  - Java
tags:
  - +
  - String.format
  - StringBuilder
---

우리가 문자열을 다룰 때 어쩌면 가장 자주 사용되는 게 여러 문자열을 연결하여 새로운 문자열을 만드는 작업이다.

가장 보편적으로 사용되는게 **'+'** 문자열 연산자를 이용해서 작업하는 형태일꺼다.

오늘은 **'+'** 연산자를 포함하여 추가로 2가지 방법을 소개하며 각자의 장단점을 소개할 예정이다.

개인적으로 느낀 장단점이니 오해하지 않길 바란다.

### **'+' 연산자 사용**

```java
String str = "";
String name = "홍길동";

str = "안녕하세요 " + name + " 입니다";
System.out.println(str);
```

- 장점 : 가장 보편적이며 준수한 성능을 나타낸다.
- 단점 : 문자열이 길어질수록 가독성이 떨어진다.

---

### **String클래스 format()메소드 사용**

```java
String str = "";
String name = "홍길동";

str = String.format("안녕하세요 %s 입니다", name);
System.out.println(str);
```

- 장점 : 가독성이 뛰어나다.
- 단점 : 성능이 좋지 못하다.

---

### **StringBuilder클래스 append()메소드 사용**

```java
String str = "";
String name = "홍길동";

str = new StringBuilder().append("안녕하세요 ").append(name).append(" 입니다").toString();
System.out.println(str);
```

- 장점 : 가장 보편적이며 준수한 성능을 나타낸다.
- 단점 : 가독성이 떨어지며 코드가 지져분해질수 있다.

이렇게 간단하게나마 3가지 방법으로 문자열을 연결하는 방법을 알아봤다.

각자의 장단점이 있으니 자기의 상황에 맞게 올바르게 사용한다면 가독성도 챙기고 성능도 챙겨

두 마리의 토끼를 잡을 수 있지 않을까 싶다.

---
title: "[Java] @Data를 지양하고 @Builder를 지향하자"
categories: 
  - Java
tags : 
  - Data
  - Builder
---

자바를 사용하며 클린 코드에 도움을 주는 Lombok이라는 라이브러리를

자주 사용하는 걸 볼 수 있다. 거기서 어쩌면 가장 많이 사용하고 핵심적인 어노테이션은

**@Data**라고 생각한다.

그러나 여기서 나는 한 가지 의문점이 들어 포스팅을 하게 되었다.

**@Data**은 너무 다양한 기능을 함축적으로 표현하고 있다.

- @Getter
- @Setter
- @RequiredArgsConstructor
- @ToString
- @EqualsAndHashCode

나는 여기서 의문점이 들었던게 바로 **@Setter**이다.

여러 블로그에서 볼 수 있듯이 **@Setter**는 우리에게 위험성을 가져다줄 수 있는

메서드이다. 객체의 데이터를 맘대로 바꿀 수 있으며 이게 데이터 무결성의 원칙에

어긋나 추후 문제가 발생할 여지가 있다.

우리가 당장 데이터베이스만 바로 보아도 **Select**문은 조심스럽지 않게 다가가지만

**Insert, Update, Delete**문은 조심스럽게 다가가지 않나

객체 또한 마찬가지이다. 해당 객체가 조회성이 아닌 데이터를 삽입 및 수정하는 객체라면

분명 우리는 조심스럽게 다뤄야 한다.

자 이제 그러면 @Setter를 지웠다고 생각해보자 그럼 가장 먼저 처음에 드는 생각이

`어? 그러면 객체 생성하고 해당 객체의 변수에 데이터를 넣을 때는 어떻게 해야 하나요?`

아래와 같이 생성자를 통해 객체를 생성할 때 객체 안의 변수에 값을 넣어주며 생성할 수 있다.

```java
// User 클래스
public class User {
    private String id;
    private String pw;
    private int age;

    public User(String id, String pw, int age) {
        this.id = id;
        this.pw = pw;
        this.age = age;
    }
}
```

```java
User user = new User("id", "pw", 18);
```

이와 같이 생성자를 통해 객체를 생성했다.

그러나 생성자를 통해 생성하게 되면 해당 변수에 어떠한 값이 들어가는지

한눈에 보기 힘들다.

그래서 나는 **@Builder** 어노테이션을 통해 객체를 생성하라고 추천해 준다.

이제 **User** 객체를 **@Builder** 어노테이션을 통해 생성하는 코드를 봐보자.

**@Builder**는 Lombok 어노테이션으로 클래스 위에 선언을 해주어야 한다.

```java
@Builder
public class User {
    private String id;
    private String pw;
    private int age;
}
```

```java
User user = User.builder()
                .id("id")
                .pw("pw")
                .age(18)
                .build();
```

생성자로 생성한 것보다 훨씬 보기 편하며

어떤 변수에 어떤한 값이 들어가는지 한눈에 알 수가 있다.

이와 같이 나의 생각을 정리하여 포스팅을 했지만

분명 **@Setter** 메서드가 없으면 프로세스를 처리하는 로직에서는

불편함을 겪을 수 있으며 **@Setter**하나 없애자고 **@Data**로

함축되어 있는 어노테이션을 다 분리해서 선언하기도 클린 코드에 안 맞는다고 생각할 수 있다.

개발자는 무엇보다 본인의 생각이 중요하니 자신이 생각하는 개발 철학을 생각하며

유동적으로 받아들여서 좋은 점만 흡수했으면 한다.
---
title: "[Java] 공통 필드를 가진 Response 객체 두 가지 구현 법"
categories:
  - Java
tags:
  - Response
---

Java를 통해 API 개발 시 Response로 데이터 내려보내는 경우 여러 가지 방법이 존재하지만

오늘은 Reponse 객체에 공통된 필드가 존재하는 경우의 클래스 구현 법에 대해서 알아보고자 한다.

일단 아래와 같은 두 개의 공통된 필드 두 개가 존재한다고 가정하여보자

```java
private int resCode;    // 결과 코드

private String resMsg;  // 결과 메시지
```

우리는 여기서 두가지 방법을 생각할 수 있다.

### 1. 부모 클래스 상속 받아 사용하기

**첫 번째**로는 공통 필드를 가지고 있는 부모 클래스를 상속 받아서 사용하는 것이다.

아래의 예제로 알아보자

#### 공통 필드를 가진 부모 클래스

```java
@Data
public class CommonRes {
    private int resCode;

    private String resMsg;
}

```

#### 부모 클래스를 상속받은 자식 클래스

```java
@ToString(callSuper = true)           // 부모 객체도 ToString시 포함
@EqualsAndHashCode(callSuper = true)  // 객체 비교 시 부모 클래스도 포함
@Data
public class Member1 extends CommonRes{
    private String name;

    private int age;
}
```

#### 자식 클래스 생성하여 출력해보기

```java
Member1 member1 = new Member1();
member1.setResCode(0);
member1.setResMsg("성공");
member1.setName("홍길동");
member1.setAge(15);

System.out.println("member1 : " + member1);
```

```console
member1 : Member1(super=CommonRes(resCode=0, resMsg=성공), name=홍길동, age=15)
```

이와 같이 공통 필드를 가진 부모 클래스를 상속받아서 사용한다면 간단하게 공통된 필드를 가진

객체를 생성할 수 있다.

---

### 2. 제네릭 클래스 이용하기

**두 번째**로는 제네릭 클래스를 이용하여 공통 필드와 임의의 타입을 가진 객체를 포함하는 클래스를

생성하여 사용하는 것이다.

여기서 제네릭 클래스를 간단하게 설명하자만 객체가 생성될 때 타입을 정하여 생성하는 기법이다.

#### 제네릭 클래스 선언

```java
@Data
public class CommonGenericRes<T> {
    private int resCode;

    private String resMsg;

    private T resObj;
}
```

#### 타입을 가진 클래스

```java
@Data
public class Member2{
    private String name;

    private int age;
}

```

#### 제네릭 클래스 생성하여 객체 주입하여 출력하기

```java
CommonGenericRes<Member2> commonGenericRes = new CommonGenericRes<>();
Member2 member2 = new Member2();
member2.setName("홍길동");
member2.setAge(15);
commonGenericRes.setResCode(0);
commonGenericRes.setResMsg("성공");
commonGenericRes.setResObj(member2);

System.out.println("member2 : " + commonGenericRes);
```

```console
member2 : CommonGenericRes(resCode=0, resMsg=성공, resObj=Member2(name=홍길동, age=15))
```

이와 같은 제네릭 클래스를 이용하는 방법으로도 공통된 필드를 가진 Response 객체를 생성할 수 있다.

**위의 방법 말고도 전역으로 캐치하여 필드를 주입해 주는 방법 및 여러 가지가 존재하니 자신의 프로젝트의 상황에 맞게<br> 사용한다면 좋을 것으로 생각된다.**

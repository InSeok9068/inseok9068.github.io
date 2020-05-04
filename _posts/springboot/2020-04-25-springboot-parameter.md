---
title: "[SpringBoot] @RequestParam, @PathVariable 차이점"
categories: 
  - SpringBoot
tags : 
  - RequestParam
  - PathVariable
---

데이터 파라미터를 주고받을 때 여러 가지 방법이 있지만

오늘은 **@RequestParam**과 **@PathVariable**의 차이점에 대해서 알아보려 한다.

1. http://restapi.com?userId=test&memo=테스트

2. http://restapi.com/test/테스트

1과 같은 방식은 쿼리 스트링이라 부르며 Get 방식의 통신을 할 때 주로 쓰인다.

2와 같은 방식은 RESTful 방식이며 Rest 통신할 때 쓰인다.

각자의 장단점이 있으며 두 개의 방식은 Spring에서 파라미터를 받는 방식이 조금 다르다.

### @RequestParam
```java
@RestController
public class TestController (){

  @GetMapping("/")
  public String test(@RequestParam("userId") String userId, 
                     @RequestParam("memo")   String memo){

    //아래와 같이 해당 변수에 파라미터값이 할당된다.
    //userId = "test"
    //memo   = "테스트"

    return "TEST 성공"
  }
  
}
```

### @PathVariable
```java
@RestController
public class TestController (){

  @GetMapping("/{userId}/{memo}")
  public String test(@PathVariable("userId") String userId,
                     @PathVariable("memo")   String memo){

    //아래와 같이 해당 변수에 파라미터값이 할당된다.
    //userId = "test"
    //memo   = "테스트"

    return "TEST 성공"
  }
  
}
```

**@PathVariable**에서 이메일과 같은 방식의 값이나 특수문자를 받을 때는 값이 잘리거나 비정상적으로 들어온다.

이때는 아래와 같은 방법을 사용하면 정상적으로 받을 수 있다.

```java
@RestController
public class TestController (){

  @GetMapping("/{userId}/{memo:.+}")
  public String test(@PathVariable("userId") String userId,
                     @PathVariable("memo")   String memo){

    //아래와 같이 해당 변수에 파라미터값이 할당된다.
    //userId = "test"
    //memo   = "테스트"

    return "TEST 성공"
  }
  
}
```

@PathVariable은 아무래도 RESTful 방식에 맞게 좀 더 직관적이다.

@RequestParam는 null 값 허용이나 **키:밸류** 값으로 보낼 수 있다는 점 정도로 들 수 있다.

꼭 정답은 없으니 상황에 맞게 사용하면 좋을 거 같다.
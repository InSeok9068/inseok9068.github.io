---
title: '[Java] NullPointException 예방 toString보단 valueOf를 사용하자'
categories:
  - Java
tags:
  - NullPointException
  - toString
  - valueOf
---

우리가 Java를 사용하면서 가장 자주 맞닥뜨리는 에러는 NullPointException을 들 수 있을 것이다.

분명 내가 작성한 코드 안에 무수히 많은 NullPointException 에러가 내재되어 있을 것이다.

이를 방지하기 위해서는 **null**을 사전에 체크하거나 올바른 습관으로 고쳐나가야 할 것이다.

오늘 소개할 것은 **valueOf()**라는 메서드인데 이는 String 클래스의 메서드이다.

우리가 사용하는 모든 객체에는 Object 클래스를 상속받아 **toString()**이라는 메서드가 존재할 것이다.

API통신이나 MVC 구조에서 데이터를 주고 받을때 **Map<String,Object>** 사용하거나

아니면 확장성?을 위해 Object변수로 주고 받는 경우가 있다. Object로 받아 String 객체에 넘겨줄때

**toString()** 메서드를 자주 사용할 것이다.

이때 발생하는 문제점은 **null**이 들어왔을때의 대체법이다.

```java
public static void main(String[] args) {
  Map<String,Object> map = new HashMap<>();

  map.put("param1", "1번");
  map.put("param2", 1);
  map.put("param3", null);

  System.out.println(map.get("param1").toString());
  System.out.println(map.get("param2").toString());
  System.out.println(map.get("param3").toString());

  // 1번
  // 1
  // 에러 발생

}
```

마지막 줄에서 **param3** 변수가 **null**이므로 NullPointException이 발생한다.

그럼 여기서 **toString()**이 아닌 **String.valueOf()**을 사용해보고 결과값을 비교해보자.

```java
public static void main(String[] args) {
  Map<String,Object> map = new HashMap<>();

  map.put("param1", "1번");
  map.put("param2", 1);
  map.put("param3", null);

    System.out.println(String.valueOf(map.get("param1")));
    System.out.println(String.valueOf(map.get("param2")));
    System.out.println(String.valueOf(map.get("param3")));

  // 1번
  // 1
  // null

}
```

이와 같이 에러를 발생시키지 않고 해당 값 **null**이면 **"null"**이라는 문자열을 만들어 리턴해준다.

**null**이라는 문자열로 넘어간다는 것이 문제를 야기할 수도 있다.

자기가 생각하는 프로세스가 문자열이 **"null"**이라는 값이 문제가 되지않는다면

이와 같은 방법으로 NullPointException을 예방할 수 있다.

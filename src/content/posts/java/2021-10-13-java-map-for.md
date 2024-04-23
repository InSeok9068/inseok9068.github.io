---
title: '[Java] Map 객체안 요소들 For문 돌리기'
categories:
  - Java
tags:
  - request
---

가끔씩 Map 객체 안의 요소를 순회하여 데이터 처리할 때가 있는데 그럴 때 몇 가지 방식이 있지만

아래의 방식이 가독성이 좋아 채택하여 사용한다.

```java
Map<String,Object> map = ImmutableMap.<String,Object>builder()
        .put("1번",1)
        .put("2번",2)
        .put("3번",3)
        .build();

for(Map.Entry<String,Object> entry : map.entrySet()) {
    System.out.println("Key : " + entry.getKey());
    System.out.println("Value : " + entry.getValue());
}
```

```console
Key : 1번
Value : 1
Key : 2번
Value : 2
Key : 3번
Value : 3
```

---
title: '[Java] HashMap과 LinkedHashMap의 차이점'
categories:
  - Java
tags:
  - HashMap
  - LinkedHashMap
---

오늘은 Java의 HashMap 클래스와 LinkedHashMap 클래스의 차이점을 알아보겠습니다.

보통 대부분 Map 인터페이스를 통해 HashMap 클래스를 많이 사용할 것으로 예상된다.

Key와 Value 쌍이 되는 구조로 JSON 객체와 구조가 비슷하여 데이터를 주고받을 때 자주 사용한다.

**HashMap 과 LinkedHashMap의 가장 큰 차이점은 키의 순서에 있다.**

간단한 예시 코드를 통해 알아보도록 하자.

```java
package Java;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * MapDiff
 */
public class MapDiff {
  public static void main(String[] args) {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();

    hashMap.put("one", "1번");
    hashMap.put("two", "2번");
    hashMap.put("three", "3번");
    hashMap.put("four", "4번");
    hashMap.put("five", "5번");

    System.out.println(hashMap.toString());
    // {four=4번, one=1번, two=2번, three=3번, five=5번}

    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();

    linkedHashMap.put("one", "1번");
    linkedHashMap.put("two", "2번");
    linkedHashMap.put("three", "3번");
    linkedHashMap.put("four", "4번");
    linkedHashMap.put("five", "5번");

    System.out.println(linkedHashMap.toString());
    // {one=1번, two=2번, three=3번, four=4번, five=5번}
  }
}
```

위와 같이 HashMap으로 선언하여 데이터 삽입 후 출력하면 키값이 입력 순서가 아닌 임의대로 들어가 있는 걸 볼 수 있다.

그러나 LinkedHashMap은 데이터 삽입 후 출력하면 내가 입력한 선수대로 나오는 걸 볼 수 있다.

별다른 차이점이 없다고 볼 수도 있지만 API 개발하여 제공할 때 좀 더 보기 좋은 데이터를 넘겨주기 위해 나는 사용한다.

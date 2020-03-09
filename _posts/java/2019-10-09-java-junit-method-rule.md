---
title: "[Java] Junit 메소드명 한글로 사용(한글날기념)"
categories: 
  - Java
tags : 
  - junit
---

Junit으로 단위 테스트를 진행하다 보면 테스트 경우의 수에 따라 메소드를 작성하게 되는데<br>
개발을 진행하다 보면 한글 쓸 일이 참 없다. 근데 Junit의 메소드 명은 한글로 사용해도 에러도 안 나고<br>
한글이라 그래서 친숙한지 좀 더 메소드의 의미를 명확하게 전달할 수 있다.

```java
@Test
public void 고객을찾는다() {

    Object a = service.findUser();

    // 객체가 Null여부 검사
    // Null이면 실패, Null이 아니면 성공
    assertNotNull(a);
}
```

다음에 기회가 된다면 Junit사용법을 자세히 포스팅 하겠다.
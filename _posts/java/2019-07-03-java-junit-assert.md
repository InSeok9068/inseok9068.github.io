---
title: "[Java] Junit Assert 주요 메서드 설명"
categories: 
  - Java
tags : 
  - junit
  - assert
---

| assert 메서드               | 설명                                                                                       |
|:----------------------------|:-------------------------------------------------------------------------------------------|
| assertArrayEquals(a, b);    | 배열 A와 B가 일치함을 확인한다. |
| assertEquals(a, b);         | 객체 A와 B가 일치함을 확인한다. |
| assertSame(a, b);           | 객체 A와 B가 같은 객임을 확인한다. assertEquals 메서드는 두 객체의 값이 같은가를 검사는데 반해 assertSame메서드는 두 객체가 동일한가 즉 하나의 객인 가를 확인한다. (== 연산자)    | 
| assertTrue(a);              | 조건 A가 참인가를 확인한다. |
| assertNotNull(a);           | 객체 A가 null이 아님을 확인한다. |


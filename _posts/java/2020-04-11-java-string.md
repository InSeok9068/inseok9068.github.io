---
title: "[Java] String, StringBuffer, StringBuilder의 차이점"
categories: 
  - Java
tags : 
  - String
  - StringBuffer
  - StringBuilder
---

오늘은 간단하게 String, StringBuffer, StringBuilder의 차이점에 대해서 알아보려 한다.

막연하게 코딩을 하다 보면 문자열 관련돼서는 String을 자주 쓰게 된다.

그러나 인터넷에 떠도는 코드를 보면 어떤 건 StringBuffer 또 다른 건 StringBuilder을 사용한 걸 볼 수 있다.

문득 그 세가지의 차이점에 대해서 궁금했고

기본적으로는 String은 immutable(불변)하고 StringBuffer, StringBuilder는 mutable(가변)하다.

어떤 상황에서 어떤 타입의 클래스를 사용해야지 효율적으로 메모리 관리를 할 수 있는지 알아보려 한다.

### String 
일단 우리가 가장 많이 쓰는 걸로 알고 있는 String 클래스이다.

String 객체를 선언하면 JVM의 Heap 메모리에 생성이 되는데 여기서

String 객체를 **concat이나 +** 연산자를 통해 문자열을 변경해도

해당 객체의 공간이 늘어나지 않고 다시 새롭게 생성된다.

이렇게 무작위 하게 String 객체에 문자열 연산자를 사용하게 되면

Heap 메모리에 과부하가 오고 문제점이 발생할 수 있다.

그러나 불변하다보니 조회연산자에서는 빠른 속도를 보인다.

### StringBuffer

StringBuffer는 이러한 String의 문자열 연산의 불변성과는 반대로

가변 하다 StringBuffer 클래스로 만들어진 객체에 문자열 연산을 하면

해당 객체의 메모리 값을 변경시켜 문자열을 변경한다.

이러하다 보니 아무래도 문자열 연산이 자주 일어나는 객체에는

StringBuffer 클래스 사용하는 게 좀 더 효율적이라고 볼 수 있다.

### StringBuilder

마지막으로 StringBuilder는 StringBuffer와 크게 다른 점이 없으나

가장 큰 차이점으로는 동기화 여부이다.

StringBuilder는 동기화가 되지 않아 싱글스 레드 환경에서 적합하고

StringBuffer는 동기화가 가능해 멀티 스레드 환경에 적합하다.

---

간단하게 String, StringBuffer, StringBuilder 클래스의 차이점을 알아봤다.

적재적소에 알맞은 클래스를 사용하면 적지만 조그이나마 좋은 프로그램을 만드는데

도움을 줄 수 있을 거 같다.
---
title: "[Java] @Slf4j를 사용하여 편하게 로그찍기"
categories: 
  - Java
tags : 
  - Lombok
  - Slf4j
---

오늘은 Lombok 어노테이션중 하나인 **@Slf4j**를 통해 좀 더 편리하게 로그를 찍어보자 한다.

일단 나도 가끔씩 헷갈리는 Log 레벨에 대해서 먼저 한번 테이블을 형태로 알아보도록 하자.

|       | Trace | Debug | Info | Warn | Error |
|:-----:|:-----:|:-----:|:----:|:----:|:-----:|
| Trace |   O   |   O   |   O  |   O  |   O   |
| Debug |   X   |   O   |   O  |   O  |   O   |
| Info  |   X   |   X   |   O  |   O  |   O   |
| Warn  |   X   |   X   |   X  |   O  |   O   |
| Error |   X   |   X   |   X  |   X  |   O   |

- TRACE : 추적 레벨은 Debug보다 좀더 상세한 정보를 나타냄
- DEBUG : 프로그램을 디버깅하기 위한 정보 지정
- INFO :  상태변경과 같은 정보성 메시지를 나타냄
- WARN :  처리 가능한 문제, 향후 시스템 에러의 원인이 될 수 있는 경고성 메시지를 나타냄
- ERROR :  요청을 처리하는 중 문제가 발생한 경우

일단 우리가 평소에 쓰는 클래스 변수로 선언하여 쓰는방식에 대해서 먼저 봐보겠다.

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jSample {
    private static final Logger log = LoggerFactory.getLogger(Slf4jSample.class);

    public static void main(String[] args) {
        log.info("---------- Log 테스트 ---------");
    }
}
```

#### Console
```console
20200616 15:03:00.675 [main] INFO b.Slf4jSample - ---------- Log 테스트 ---------
```

위와 같이 로그가 찍히는 걸 볼 수 있다.

자 이제 소개할 **@Slf4j** 어노테이션을 사용하는 방식에 대해서 봐보자.

```java
@Slf4j
public class Slf4jSample {

    public static void main(String[] args) {
        log.info("---------- Log 테스트 ---------");
    }

}
```

#### Console
```console
20200616 15:03:00.675 [main] INFO b.Slf4jSample - ---------- Log 테스트 ---------
```

동일하게 로그가 찍히며

특징이라면 자동으로 log 변수를 선언하여 준 것을 볼 수 있다.

어쩌면 한 줄 차이지만 본인이 어노테이션이 더 편하면 해당 어노테이션을 사용하면 되고 그게 아니라면

클래스 변수로 직접 선언해 주어 사용하면 된다.

그러나 여기서 **@Slf4j** 선언하여 사용할 경우 변수명이 **log**로 고정된다는 점이 있다.

---

여기서 이제 로그 관련된 팁을 추가로 적자고 한다.

우리가 로그를 찍는 이유는 여러 가지가 있는데

그중 하나가 보통 파라미터의 값이나 프로세스 간의 해당 전달 값을 찍는 경우가 많다.

그러나 아래와 같이 **+** 문자열 연산자를 통해 파라미터 값을 출력하다 보면

해당 로그가 문자열 연산자를 먼저 실행한 뒤 해당 로그 레벨을 확인한 뒤 출력한다.

이렇게 되면 찍을 필요가 없는 로그에도 문자열 연산자가 실행되어 리소스 낭비가 된다.

두 가지 여기서 **@Slfj4**의 **{}**를 사용하면 이와 같은 문제를 해결할 수 있다.

**{}**를 사용하면 먼저 우선적으로 로그 레벨을 확인한 뒤

그 뒤로 **{}**에 값을 대입하여 출력하여 준다.

```java
@Slf4j
public class Slf4jSample {
    public static void main(String[] args) {
        String value1 = "1번값";
        String value2 = "2번값";
        log.info("---------- Log 테스트 ---------");
        log.info("1번의 값은 : "+value1 + " 2번의 값은 : "+value2);
        log.info("1번의 값은 : {} 2번의 값은 : {}", value1, value2);
    }
}
```

```console
20200616 15:36:09.144 [main] INFO b.Slf4jSample - ---------- Log 테스트 ---------
20200616 15:36:09.148 [main] INFO b.Slf4jSample - 1번의 값은 : 1번값 2번의 값은 : 2번값
20200616 15:36:09.149 [main] INFO b.Slf4jSample - 1번의 값은 : 1번값 2번의 값은 : 2번값
```
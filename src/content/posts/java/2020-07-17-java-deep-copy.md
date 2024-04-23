---
title: '[Java] Gson 라이브러리를 통해 객체 DeepCopy 구현하기'
categories:
  - Java
tags:
  - Gson
  - DeepCopy
  - Serialization
---

오늘은 자주 하는 실수 중 하나인 객체 복사에 대해서 문제점이 될 수 있는 부분을 알아보고

**Gson** 라이브러리를 통해 해결하는 방법을 같이 알아보도록 하겠다.

우리가 복사라 하면 **=** 대입 연산자를 통해 해당 객체에 동일한 타입의 객체를 넣어서 복사하곤 한다.

그러나 이렇게 하게 되면 문제점이 발생되는 게 같은 주솟값을 바라보아 원치 않게 원 데이터마저도 바뀔 수 있다.

이것을 **얕은복사**라 하며 간단하게 코드를 통해 알아보도록 하자

### 얕은 복사

```java
@Slf4j
public class DeepCopyTest {
    public static void main(String[] args) {
        DeepCopy deepCopy1 = new DeepCopy();

        deepCopy1.setNumber(10);

        log.info("deepCopy1 : {}", deepCopy1.getNumber());

        DeepCopy deepCopy2 = new DeepCopy();

        // 복사
        deepCopy2 = deepCopy1;

        deepCopy2.setNumber(20);

        log.info("deepCopy1 : {}", deepCopy1.getNumber());
        log.info("deepCopy2 : {}", deepCopy2.getNumber());

    }
}

@Getter
@Setter
class DeepCopy {
    private int number;
}
```

위와 같이 나는 분명 deepCopy1의 객체를 deepCopy2에 넣어

deepCopy2 객체만 값을 바꾸었지만 로그를 확인해보면 deepCopy1도 원치 않게 값이 바뀌어있는 걸 볼 수 있다.

```console
20200717 15:05:32.056 [main] INFO b.DeepCopyTest - deepCopy1 : 10
20200717 15:05:32.066 [main] INFO b.DeepCopyTest - deepCopy1 : 20
20200717 15:05:32.066 [main] INFO b.DeepCopyTest - deepCopy2 : 20
```

이를 극복하는 방법으로는 대표적으로는 해당 클래스에 **Cloneable**를 상속받아 처리하는 방법이 있다.

그러나 오늘 포스팅할 거는 **Gson**이라는 객체 직렬화, 역 직렬화를 시켜주는 라이브러리를 통해

객체를 직렬화를 거쳐 다시 역직렬화를 해줌으로써 해당 객체의 주소값이 다시 할당되어

아예 새로운 객체로 인식하는 방법을 통해서 해결하려고 한다.

이것 또한 코드를 통해 알아보도록 하자.

---

### 깊은 복사

```java
@Slf4j
public class DeepCopyTest {
    public static void main(String[] args) {
        DeepCopy deepCopy1 = new DeepCopy();

        deepCopy1.setNumber(10);

        log.info("deepCopy1 : {}", deepCopy1.getNumber());

        // 직렬화 후 역직렬화를 통해 복사
        DeepCopy deepCopy2 = new Gson().fromJson(new Gson().toJson(deepCopy1), DeepCopy.class);

        deepCopy2.setNumber(20);

        log.info("deepCopy1 : {}", deepCopy1.getNumber());
        log.info("deepCopy2 : {}", deepCopy2.getNumber());

    }
}

@Getter
@Setter
class DeepCopy {
    private int number;
}
```

이제 로그를 확인해보자.

```console
20200717 15:12:16.413 [main] INFO b.DeepCopyTest - deepCopy1 : 10
20200717 15:12:16.513 [main] INFO b.DeepCopyTest - deepCopy1 : 10
20200717 15:12:16.513 [main] INFO b.DeepCopyTest - deepCopy2 : 20
```

내가 원하는 deepCopy2의 객체만 값이 바뀌었고 deepCopy1의 데이터는 그대로 유지하고 있다.

해당 방법은 **Jackson**이라는 라이브러리를 통해서도 가능하니 객체 깊은 복사가 필요할 경우

유용하게 사용하면 될 거 같다.

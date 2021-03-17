---
title: "[IntelliJ] TODO 주석으로 할일 관리하기"
categories:
  - IntelliJ
  - TODO
---

코드를 작성하고 일을 하다 보면 순간 생각나는 고쳐야 할 부분이나 리팩토링 해야 할 부분이 보일 때가 있다.

그때마다 따로 작성해두는 것도 좋지만 그러면 어디 소스코드에서 고쳐야 했는지 기억이 안 날 때도 많다.

그때 주석을 작성할 텐데 주석 작성 시 글자 도입부에 **TODO**를 작성하여주면

IDE 도구에서 TODO List로 할 일 관리를 따로 리스트업 해서 볼 수 있다.

```java
public class Main {
    public static void main(String[] args) {

        //TODO ... 고쳐야함

    }
}
```

![IMAGE1](/assets/images/post/2020-11-30-intellij-todo-image1.PNG)

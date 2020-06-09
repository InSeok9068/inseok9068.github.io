---
title: "[Java] 실행중인 Method Name 가져오기"
categories: 
  - Java
tags : 
  - Method
  - Thread
---

Logger를 통해 Method 명를 쌓는 경우나

현재 진행 중인 Thread에서 실행 중인 Method 명을 알아야 할 때가 있다.

이때 아래와 같은 코드를 통해 Method명을 쉽게 알 수 있다.

```java
public class MethodName {
    public static void main(String[] args) {
        getMethodName();
    }
    private static void getMethodName() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
```

```shell
getMethodName
```
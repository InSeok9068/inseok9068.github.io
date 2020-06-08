---
title: "[Java] 클린 코드 Try-With-Resources를 사용하자"
categories: 
  - Java
tags : 
  - Exception
  - Try-With-Resources
---

Java를 사용하면서 입출력 로직이나 DB 커넥션을 통해 자원을 열었다가 닫는 작업을 많이 할 것이다.

보통으로는 Try-Catch-Finally를 통해 자원을 사용하고 닫았다는 반복하며 필요 없는 코드들이 너무 많아지는 걸 볼 수 있다.

그러나 자바 7부터 지원되는 **Try-With-Resources** 이러한 불편함을 줄여준다.

간단하게 두 개의 코드를 비교해보며 알아보자.

### Try-Catch-Finally
```java
public static void main(String[] args) {
    FileOutputStream out = null;
    try {
        out = new FileOutputStream("test.txt");
        // 입출력 로직 구현
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } finally {
        if (out != null) {
            try {
                out.close(); // close 하다가 예외가 발생할 수 있다.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### Try-With-Resources
```java
public static void main(String[] args) {
    try(FileOutputStream out = new FileOutputStream("test.txt")) {
        // 입출력 로직 구현
    }
    catch(IOException e) {
        e.printStackTrace();
    }
}
```

이와 같이 불필요한 자원을 닫아주는 close()를 자동으로 처리해 준다.

한 가지 유의할 점은 try() 안에서 객체를 선언해 주어야 한다는 것이다.
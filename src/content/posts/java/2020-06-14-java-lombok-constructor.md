---
title: '[Java] Lombok의 생성자 Annotation 3개를 알아보자'
categories:
  - Java
tags:
  - NoArgsConstructor
  - RequiredArgsConstructor
  - AllArgsConstructor
---

오늘은 Lombok의 생성자 어노테이션인 3가지의 차이점을 알아보겠다.

- @NoArgsConstructor
- @RequiredArgsConstructor
- @AllArgsConstructor

### 1. @NoArgsConstructor

파라미터 변수가 없는 생성자를 만들어준다.

**\* 자바에서 기본적으로 아무 생성자도 선언해주지 않으면**

**기본적으로 파라미터가 없는 생성자를 자동으로 만들어준다.**

### 2. @NoArgsConstructor

필수 파라미터 (@NotNull 변수 혹은 초기화 되지않은 Final변수)를

포함한 생성자를 만들어준다.

### 3. @AllArgsConstructor

해당 클래스의 모든 변수를 포함한 생성자를 만들어준다.

---

간단하게 코드로 봐보자.

```java
public class LombokConstructor {
    @NotNull
    private String var1;
    private String var2;
    private String var3;

    // @NoArgsConstructor
    public LombokConstructor() {

    }

    // @RequiredArgsConstructor
    public LombokConstructor(String var1, String var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    // @AllArgsConstructor
    public LombokConstructor(String var1, String var2, String var3) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
    }
}
```

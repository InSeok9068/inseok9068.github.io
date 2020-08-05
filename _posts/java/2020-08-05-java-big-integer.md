---
title: "[Java] BigInteger를 통해 무한대 정수 사용하기"
categories: 
  - Java
tags : 
  - int
  - BingInteger
---

오늘은 **BigInteger** 클래스를 알아볼 예정이다.

우리는 보통 정수를 사용할 때 **int**혹은 Wapper 클래스인 **Integer**을 주로 사용한다

그러나 해당 클래스는 정해진 정수 범위 내에서 값을 허용하고 그렇지 못하면 해당 값을 담지 못한다.

최소값과 최대값을 알아보자면 아래와 같다.

- 최소값 : -2,147,483,648
- 최대값 : 2,147,483,647

위와 같은 값보다 큰 정수를 사용할 때는 **BigInteger**를 사용하면 무한대의 정수값을 받을 수 있다.

내가 맞닥드린상황을 예시로 간단하게 알아보자.

나는 통계 데이터 뽑고자 쿼리를 실행시켜 VO 객체에 매핑시키려던 시점에 아래와 같은 에러가 발생하였다.

**에러 예시)** Numeric value out of range: "11111111111111111" in column

보통 쿼리의 resultType을 **Map<String, Object>** 이와 같이 Map 클래스를 사용했을 때는 만나지 못한

에러라 조금 당황했지만 VO 클래스의 int 변수를 **BigInteger**를 사용하면 간단하게 해결할 수 있었다.

코드를 통해 알아보자. 간단하게 데이터를 주고 받는형식이라 Service단은 생략한다.

### Controller

```java
@RestController
@RequiredArgsConstructor
public class BigIntegerController {

    private final BigInterMapper bigInterMapper;

    @GetMapping("/integer")
    public IntegerVO getInteger() throws Exception {
        return bigInterMapper.selectInteger();
    }

    @GetMapping("/bigInteger")
    public BigIntegerVO getbigInteger() throws Exception {
        return bigInterMapper.selectBigInteger();
    }

}
```

---

### Mapper

```java
@Mapper
public interface BigInterMapper {

    IntegerVO selectInteger();

    BigIntegerVO selectBigInteger();

}
```

---

### VO

#### IntegerVo

```java
@Data
public class IntegerVO {
    private Integer integer;
}
```

#### BigIntegerVo

```java
@Data
public class BigIntegerVO {
    private BigInteger bigInteger;
}
```

---

### XML

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.demo.app.blog.biginteger.mapper.BigInterMapper">
    <select id="selectInteger" resultType="IntegerVO">
      SELECT 11111111111111111 AS integer FROM DUAL
    </select>

    <select id="selectBigInteger" resultType="BigIntegerVO">
        SELECT 11111111111111111 AS bigInteger FROM DUAL
    </select>
</mapper>
```

---

### 결과

#### /integer
```html
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Wed Aug 05 11:12:33 KST 2020
There was an unexpected error (type=Internal Server Error, status=500).
Error attempting to get column 'INTEGER' from result set. Cause: org.h2.jdbc.JdbcSQLDataException: Numeric value out of range: "11111111111111111" in column [22004-200] ; Numeric value out of range: "11111111111111111" in column [22004-200]; nested exception is org.h2.jdbc.JdbcSQLDataException: Numeric value out of range: "11111111111111111" in column [22004-200]
org.springframework.dao.DataIntegrityViolationException: Error attempting to get column 'INTEGER' from result set.  Cause: org.h2.jdbc.JdbcSQLDataException: Numeric value out of range: "11111111111111111" in column  [22004-200]
; Numeric value out of range: "11111111111111111" in column  [22004-200]; nested exception is org.h2.jdbc.JdbcSQLDataException: Numeric value out of range: "11111111111111111" in column  [22004-200]
```

#### /bigInteger

```html
{"bigInteger":11111111111111111}
```
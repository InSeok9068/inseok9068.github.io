---
title: '[MyBatis] 조건문 비교 숫자형 비교연산자'
category: Others
tags:
  - MyBatis
---

오늘은 MyBatis에서 숫자형 비교 연산자를 사용할 때

**>,<,<=,>=** 이와 같은 연산자를 사용하면 만나는 오류에 대해서

해결방안을 알아보도록 하겠다.

```xml
<if test="intAge > 0">
  AND AGE > #{intAge}
<if/>
```

위와 같이 작성한다면 오류가 발생하는 걸 볼 수 있다.

XML 확장자 특성으로 볼수 있으며 이러한 현상을 해결하기 위해

아래와 같은 문자로 대신하여 사용한다면 오류를 해결할수있다.

| 기호 | 대체문자 | 예제                       |
| :--- | :------- | :------------------------- |
| <    | lt       | `<if test="intAge lt 0">`  |
| >    | gt       | `<if test="intAge gt 0">`  |
| <=   | lte      | `<if test="intAge lte 0">` |
| >=   | gte      | `<if test="intAge gte 0">` |

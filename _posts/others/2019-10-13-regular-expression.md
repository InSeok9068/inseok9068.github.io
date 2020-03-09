---
title: "정규표현식의 패턴"
categories: 
  - Others
tags : 
  - regexp
published : false
---

## 정규 표현식이란

[인프런](https://www.inflearn.com/course/%EC%83%9D%ED%99%9C%EC%BD%94%EB%94%A9-%EC%A0%95%EA%B7%9C%ED%91%9C%ED%98%84%EC%8B%9D/lecture/431)


#### 1. 기본 패턴

- Reqular expression : Hellow

    대소문자를 구분하게 된다.

ex) Hellow World -> **Hellow** World<br>
ex) hellow World -> hellow World

#### 2. ^, $, \ 의 이미

- Reqular expression : ^who

    `^` 문자열의 첫번째를 가르킨다.

ex) who is who -> **who** is who

- Reqular expression : who$

    `$` 문자열의 마지막을 가르킨다.

ex) who is who -> who is **who**

- Reqular expression : ^\$

    `\` 다음에 나오는 문자는 특수한기호라도 문자열로 반환된다.

ex) $12$ -> **$**12$

#### 3. . 의 이미

- Reqular expression : .

    `.` 문자열의 하나를 의미하며 공백의 제외하지않는다

ex) Hello World -> **H**ello World

#### 4. [] 의 이미

- Reqular expression : [abc]

    `[]` 안의 문자열

ex) Hello World -> **H**ello World

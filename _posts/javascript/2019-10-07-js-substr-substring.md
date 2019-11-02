---
title: "[Javasciprt] substr, substring 차이점"
categories: 
  - javasciprt
---

문자열을 자르는데 자주 쓰이는 함수로는 substr, substring이 있다.

두개의 차이점을 간단히 알아보자.

### substr
---
```javascript
var str = "안녕하세요"
str.substr(0,2) // "안녕"

str.substr(1,2) // "녕하"
```
*substr*은 (시작 인덱스, 종료 인덱스(시작인덱스로부터 +2))

### substring
---
```javascript
var str = "안녕하세요"
str.substring(0,2) // "안녕"

str.substring(1,2) // "녕"
```
*substring* (시작 인덱스, 종료 인덱스)

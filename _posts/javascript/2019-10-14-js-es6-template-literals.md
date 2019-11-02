---
title: "[Javasciprt](ES6) Template Literals 활용 방법"
categories: 
  - javasciprt
tags : 
  - es6
  - Template Literals
---

#### 템플릿 리터럴 (Template Literals)

**`** 키보드의 Tab키 위에 있는 문자

```javascript
var a = 100;
var b = 200;

// 기존
var str = "값 = " + (a+b);

console.log(str) // 값 = 300

// ES6
var str = `값 = ${a+b}`

console.log(str) // 값 = 300
```

좀 더 간편하게 문자열을 표현 할 수 있다.
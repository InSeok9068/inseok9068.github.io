---
title: '[Javasciprt] (console.time,console.timeEnd)로 코드 실행시간 파악하기 '
categories:
  - Javascript
tags:
  - console.time
  - console.timeEnd
---

웹 로딩 속도나 처리 속도가 너무 느리다 보면 디버깅을 해보거나 원인 파악을 할 텐데

그때 크롬 개발자 도구를 이용하면 좀 더 쉽게 원인을 찾아낼 수 있다.

오늘은 크롬 개발자 도구가 아닌 **console.time**, **console.timeEnd**로 내 코드를 감싸

코드가 걸리는 시간이 얼마나인지 파악해볼 것이다.

여기서 특징은 시작 시 console.time

종료 시점에서는 console.timeEnd를 사용해주어야 한다.

또한 다양한 console.time을 찍을 수 있기엔 두 개의 라벨을 동일하게 주어 세트로 묶어주어야 한다.

#### consoleTime.js

```js
// 십억개의 for문을 돌려 time을 구분하여 보겠다.
console.time('A');
for (let i = 0; i < 1000000000; i++) {}
console.timeEnd('A');

// A: 674.370ms
```

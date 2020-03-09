---
title: "[Node] Moment 패키지로 날짜 이용하기"
categories: 
  - Node
tag:
  - moment
---

자바스크립트에는 `new Date()`로 날짜 값을 불러올 수 있다.

그러나 Node의 패키지인 **moment** 를 이용하면 좀 더 편리하게 날짜 값을 다룰 수 있다.

물론 자바스크립트로도 **moment** 객체를 사용할 수 있다.

몇 가지 예시로 기능들을 알아보도록 하자.

일단 첫 번째로 당연히 해당 패키지를 다운로드해준다.

```shell
npm install moment --save
```

```js
const moment = require("moment");

// 현재시간
console.log(moment());
// moment("2019-11-13T20:20:56.274")

// 원하는 날짜 Date타입 구하기
console.log(moment("2019-01-01"));
console.log(moment("20190101"));
// moment("2019-01-01T00:00:00.000")

// 포맷 설정 1
console.log(moment().format("YYYY-MM-DD"));
// 2019-11-13

// 포맷 설정 2
console.log(moment().format("YYYY년 MM월 DD일"));
// 2019년 11월 13일

// 년도
console.log(moment().year());
// 2019

// 월
console.log(moment().month());
// 10

// 일
console.log(moment().date());
// 13

// 요일
console.log(moment().day());
// 3

// 시간
console.log(moment().hours());
// 20

// 분
console.log(moment().minutes());
// 21

// 초
console.log(moment().milliseconds());
// 229

// 현재 날짜로 부터 2년뒤
console.log(moment().add(2, "year"));
// moment("2021-11-13T20:21:17.229")

// 현재 날짜로 부터 1달 뒤
console.log(moment().add(1, "month"));
// moment("2019-12-13T20:21:17.231")

// 달기준으로 몇달 차이나는지 위 두개의 날짜 값의 비교
console.log(
  moment()
    .add(2, "year")
    .diff(moment().add(1, "month"), "month")
);
// 23

// moment객체를 자바스크립트 날짜객체로 변환
console.log(moment().toDate());
// 2019-11-13T11:29:47.367Z
```

이렇게 다양한 기능들을 통해 좀 더 편리하게 날짜 객체를 다룰 수 있게 되었다.

공식 문서 [moment](https://momentjs.com/docs/)를 참고하면 위에 설명한 것보다 훨씬 더 많은 기능을 이용할 수 있다.

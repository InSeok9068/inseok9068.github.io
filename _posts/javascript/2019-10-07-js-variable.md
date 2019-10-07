---
title: "Javasciprt 변수 접근자 var, let, const 차이"
categories: 
  - javasciprt
---

Javascript는 ES6가 나오기 전까진 **var**로 모든 변수를 선언해왔다.

ES6로 버전이 올라가면서 **let, const** 사용을 권장한다.

기존 **var**는 함수 스코프안에서 데이터가 공유가 되어 문제점을 가지고있었다.

이러한 문제점을 **let, const** 로 해결 할 수 있게 되었다.

간단하게 코드로 설명드리겠습니다.

```javascript
if(true){
    var val = 1
    console.log(val) // 1
}

console.log(val) // 1

val = 2

console.log(val) // 2
```

```javascript
if(true){
    const val = 1
    console.log(val) // 1
}

console.log(val) // 1

val = 2 // Assignment to constant variable.

console.log(val) // 1
```

**const 와 var** 의 차이점은 const는 상수와 같이 한번 선언되면 재할당이 불가능합니다.

```javascript
if(true){
    let val = 1
    console.log(val) // 1
}

console.log(val) // val is not defined
```

**let**은 보시다 시피 블록 스코프안에서만 유효한 변수가 됩니다.

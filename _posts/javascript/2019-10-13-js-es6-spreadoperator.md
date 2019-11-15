---
title: "[Javasciprt] (ES6) spread operator 활용 방법"
categories: 
  - javasciprt
tags : 
  - es6
  - spread operator
---

ES6의 배열를 다루는 **spread operator**의 기능을 몇가지 알아보겠다.

**첫번째**로는 `...list` 이와 같이 작성해주면 해당 배열을 펼쳐주어 배열 복사을 할 수 있다.

```javascript
let list = [1,2,3,4,5]
let newList = [...list]

console.log(list)       // [1,2,3,4,5]
console.log(newList)    // [1,2,3,4,5]
```

**두번째**로는 위와 같은점을 이용해 배열 중간에 데이터를 삽입하기 훨씬 쉬워진다.

```javascript
let list = [1,2,3,4,5]
let newList = [1,2,3, ...list, 4,5]

console.log(list)       // [1,2,3,4,5]
console.log(newList)    // [1,2,3,1,2,3,4,5,4,5]
```

**마지막으로** 함수의 여러개의 인자값을 받을 경우 하나씩 넣어줄 필요가 없이 배열을 펼쳐주어 파라미터에 인자값을 넣어줄 수 있다.

```javascript
function sum(a,b,c){
    return a+b+c
}

let list = [10,20,30]

console.log(list)            // [10,20,30]
console.log(sum(...list))    // 60
```
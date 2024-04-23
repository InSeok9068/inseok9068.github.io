---
title: '[Javasciprt] 리스트 내림차순, 오름차순 정렬'
categories:
  - Javascript
tags:
  - sort
  - reverse
---

```js
const list = [2, 3, 1, 5, 7, 4];

console.log(list);
// [ 2, 3, 1, 5, 7, 4 ]

// 오름차순
console.log(list.sort());
// [ 1, 2, 3, 4, 5, 7 ]

// 내림차순
console.log(list.reverse());
// [ 7, 5, 4, 3, 2, 1 ]
```

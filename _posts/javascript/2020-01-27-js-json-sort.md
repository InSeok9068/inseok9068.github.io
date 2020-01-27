---
title: "[Javasciprt] JSON List 내림차순, 오름차순 정렬"
categories: 
  - javasciprt
tags : 
  - json
---

#### Custom Function 
```js
var sortJSON = function(data, key, type) {
  if (type == undefined) {
    type = "asc";
  }
  return data.sort(function(a, b) {
    var x = a[key];
    var y = b[key];
    if (type == "desc") {
      return x > y ? -1 : x < y ? 1 : 0;
    } else if (type == "asc") {
      return x < y ? -1 : x > y ? 1 : 0;
    }
  });
};
```

#### 예시
```js
var testData = [{ key: 2 }, { key: 1 }, { key: 3 }, { key: 5 }, { key: 4 }];

console.log(testData);
// [ { key: 2 }, { key: 1 }, { key: 3 }, { key: 5 }, { key: 4 } ]

// 오름차순
console.log(sortJSON(testData, "key", "asc"));
// [ { key: 1 }, { key: 2 }, { key: 3 }, { key: 4 }, { key: 5 } ]

// 내림차순
console.log(sortJSON(testData, "key", "desc"));
// [ { key: 5 }, { key: 4 }, { key: 3 }, { key: 2 }, { key: 1 } ]
```

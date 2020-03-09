---
title: "[Javasciprt] 리스트 중복 제거"
categories: 
  - Javascript
---

``` javascript
var data = [2,3,4,5,5,4];

var filter = function(value, index){
    return this.indexOf(value) == index
};

var filteredData = data.filter(filter, data );

console.log(filteredData)

-> (4) [2, 3, 4, 5]
```

---
title: "Javasciprt 리스트 중복 제거"
categories: 
  - javasciprt
last_modified_at: 2019-07-01T13:00:00+09:00
toc: true
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

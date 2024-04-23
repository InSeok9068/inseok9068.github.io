---
title: '[Javasciprt] 리스트 요소 검색'
categories:
  - Javascript
---

Javascript에는 List 요소가 검색으로는 Includes, indexOf 두가지가 있다

### 변수 선언

```javascript
const list = ['1', '2', '3'];
```

### indexOf

---

```javascript
const indexOf = list.indexOf('2');

console.log(indexOf); // 1
```

리스트에 요소가 존재하지 않을시 **-1**

### Includes

---

```javascript
const includes = list.indexOf('2');

console.log(includes); // true
```

리스트에 요소가 존재하지 않을시 **false**

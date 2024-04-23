---
title: '[Javasciprt] HTML DOM 접근 방법(id,name,class)'
categories:
  - Javascript
tag:
  - jquery
---

```html
<input id="tag1" name="TAG" class="inputClass" value="100" />
```

위와 같은 HTML의 돔을 선택하는 방법으로는 세가지가 있다.

- ID Selector
- Element Selector
- Class Selector

```javascript
// ID Selector
console.log($('#tag1').val()); // 100

// Class Selector
console.log($('.inputClass').val()); // 100

// Element Selector
console.log($('input[name="TAG"]').val()); // 100
```

대체적으로는 ID Selector는 단일 선택자로 사용되고

Class Selector, Element Selector 는 다중 선택자로 사용된다.

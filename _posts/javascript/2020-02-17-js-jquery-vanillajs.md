---
title: "[Javasciprt] Jquery에서 벗어나 VanillaJS(순수 자바스크립트) 적응하기"
categories: 
  - Javascript
tags : 
  - Jquery
  - VanillnaJs
---

>> 아래와 같은 포스팅은 위의 페이지를 참조하였습니다. [참조](http://jeonghwan-kim.github.io/2018/01/25/before-jquery.html)

프론트엔드 시장은 빠르게 변화하고 있다. 그중에서 Jquery는 프론트엔드 개발자에겐 편리하고 유용한 라이브러리였다.

그러나 예전처럼 브라우저 이슈에서 벗어난 지금 Jquery보단 VanillnaJS(순수 자바스크립트)가 

조금 각광받고 있는 거 같다.

대표적으로 ES6 문법이 발표되면서 기존의 라이브러리에 종속되었던 것들도 일부 벗어나 순수 자바스크립트만으로도 

코딩하는데 지장이 없다.

하나 Jquery 10년을 넘게 사용되어왔기에 기존의 프론트엔드 개발자는 바로 적응하기 힘들 거라 생각한다. 

나 역시도 아직은 Jquery가 편해서 Jquery 문법을 사용하여 작업하는 경우도 많다. 

그래도 순수 자바스크립트를 알아두면 추후에 React 혹은 Vue 프레임 워크를 사용할 때 유용할 것이다.

오늘은 Jquery의 문법을 자바스크립트로 대체하여 구현한 코드를 보며 하나씩 알아볼 예정이다.

---

### 돔 Selecter

#### Id Selecter

```html
<div id="app"><div/>
```

```js
// Jquery
$('#app')

// JS
document.getElementById('app')
document.querySelector('#app')
```

#### Class Selecter

```html
<div class="app"><div/>
```

```js
// Jquery
$('.app')

// JS
document.getElementsByClassName('app')
document.querySelector('.app')

//여러개의 Dom객체를 포괄하고있는 Class는 아래와 같은 문법으로 배열값으로 리턴
document.querySelectorAll('.app')
```

---

### 돔에서 데이터 얻기

```html
<div id="app" data-val="123">데이터</div>
```

```js
// Jquery
$('#app').data("val");

// JS
document.getElementById('app').dataset.val
```

---

### 이벤트 바인딩

```html
<button id="app" >버튼</button>
```

```js
// Jquery
$('#app').click(()=>{

});

// JS
document.getElementById('app')addEventListener('click', () => {

});
```

---

# ... 추후 이어서 포스팅 예정
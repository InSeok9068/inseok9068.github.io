---
title: "[Javasciprt] (ES6) Arrow Function의 this"
categories: 
  - Javascript
tags : 
  - this
  - Arrowfunction
---

ES6 문법을 사용하면서 불편함을 못 느끼고 사용하고 있었지만

작업을 하던 도중 Arrow Function **this**의 의미가 기존과는 다르다는 걸 알게 되었다.

여기서 function 객체의 call 메소드를 사용하게 되면 **this**값은 호출한 Object를 가리킨다.

#### function() 사용
```js
// function() 사용
function function1() {
  console.log(this.name)
  return {
    name: "홍길동",
    getName: function() {
      // 여기서의 this는 자신의 Object내에 this를 가르킴
      console.log(this.name);
    }
  };
}

function1.call({ name: "손흥민" }).getName();
// 손흥민
// 홍길동
```

#### Arrow Function 사용

```js
// Arrow Function 사용
function function2() {
  console.log(this.name)
  return {
    name: "홍길동",
    //* arrow function은 자신을 포함하는 외부 scope에서 this를 계승받는다.
    //즉, arrow function은 자신만의 this를 생성하지 않는다. (Lexical this)
    getName: () => {
      console.log(this.name);
    }
  };
}

function2.call({ name: "손흥민" }).getName();
// 손흥민
// 손흥민
```

아직 실무에서 어떻게 활용하면 좋을지는 감이 오진 않지만 그래도 혹여나 ES6 문법으로 작업 시

아래와 같은 문제를 인식하고 있으면 자신이 원하는 방향으로 코딩을 하는 데 도움이 될 것이다.
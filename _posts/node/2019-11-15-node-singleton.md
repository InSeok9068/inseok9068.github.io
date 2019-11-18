---
title: "[Node] 간단한 예제로 알아보는 Node 싱글톤"
categories: 
  - node
tag:
  - singleton
---

[참조](https://medium.com/@lazlojuly/are-node-js-modules-singletons-764ae97519af)

싱글톤 패턴이란 하나의 프로그램 내에서 하나의 인스턴스가 한 번만 메모리에 할당하여 생성되며 사용할 때마다

객체를 생성하지 않고 필요시 가져다 씀으로써 데이터 공유가 쉽다는 장점과 같이 메모리 낭비도 줄일 수 있다.

간단한 예제를 살펴보겠다.


다른 곳에서 사용할 수 있게 인스턴스를 모듈화해서 하나 만들어줍니다.

#### plus.js

```js
let value = 0;

module.exports = {
  plus : () => value++,
  get  : () => value
}
```

해당 인스턴스를 가져와 객체를 생성해줍니다.

그리곤 객체의 메서드를 사용해서 값을 증가해보고 그 값을 확인해줍니다.

#### plusApp.js

```js
const plusApp = require("./plus");

plusApp.plus();
plusApp.plus();

console.log(plusApp.get()); // 2
```

이제 싱글톤의 핵심인 동일한 인스턴스를 가지고 여러 개의 객체를 만들어서 데이터 공유를 하는 것을 해볼 것입니다.

#### sington.js

```js
const plusApp1 = require("./plus");
const plusApp2 = require("./plus");

plusApp1.plus();
plusApp1.plus();
plusApp2.plus();

console.log(plusApp1.get()); // 3
console.log(plusApp2.get()); // 3
```

위와의 결과와 같은 인스턴스에서 만들어진 객체는 데이터가 공유되어 하나의 인스턴스로 관리되는 것을 볼 수 있습니다.
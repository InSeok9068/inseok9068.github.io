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

#### plus.js

```js
let value = 0;

module.exports = {

}
```
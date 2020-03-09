---
title: "[Javasciprt] 콜백 지옥의 해결방법 Promise"
categories: 
  - Javascript
tag:
  - promise
---

오늘은 자바스크립트 코딩하면서 자주 겪게되는 CallBack지옥에 대한 해결책인 **Promise**를 알아보겠다.

자바스크립트 라이브러리인 Jquery의 **Ajax**를 많이 사용할텐데 비동기통신을 하다보면 조금 복잡한 로직을 구현 할 때 코드 가독성이 떨어지게 된다.

대표적으로는 순차적으로 어떠한 값을 가지고 여러가지 작업을 진행할때 아래와 같은 코드가 발생 하기 쉽다.

이렇게 되면 코드가 지져분해질뿐 아니라 가독성 또한 떨어진다.

```js
$.ajax{
  url  : "url1"
  data : data
  success : function(result){
    $.ajax{
      url  : "url2"
      data : data
      success : function(result){
        $.ajax{
          url  : "url3"
          data : data
          success : function(result){
          }
        }
      }
    }
  }
}
```

또한 다른 실수로는 비동기 통신임을 생각하지 못하고 값을 계산했을시 아래와 같이 원하는 값이 안나올수가 있다.

```js
let applePrice = 100;

//비동기 통신을 구현하기 위해 setTimeOut으로 표현
const process1 = () => {
  setTimeout(function() {
    applePrice += 100;
  }, 3000);
};

const process2 = () => {
  console.log(applePrice * 2);
};

process1();
process2();

// 200
```

이럴때 자바스크립트이 Promise를 사용하면 조금 더 보기좋고 효율적인 코드를 완성 시킬 수 있다.

여기서 **reslove** 성공, **reject** 실패라고 볼수 있다.

**reslove**는 **then**으로 캐치가 가능하고 **reject**는 **catch**로 캐치가 가능하다.

지금은 몇개의 프로세스가 없어서 오히려 더 복잡해 보일수있지만 프로세스가 방대해지다보면 코드 가독성이 훨씬 뛰어나서 작업하기 수월해질것이다.

```js
const process1 = isTrue => {
  return new Promise(function(reslove, reject) {
    if (isTrue) {
      setTimeout(function() {
        applePrice += 100;
        reslove(applePrice);
      }, 2000);
    } else {
      reject();
    }
  });
};

const process2 = () => console.log(applePrice * 2);

const errorCatch = () => console.log("Error");

// 작업이 성공
process1(true)
  .then(process2)
  .catch(errorCatch);

//400

// 작업이 실패
process1(false)
  .then(process2)
  .catch(errorCatch);

//Error
```
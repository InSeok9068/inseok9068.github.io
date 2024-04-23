---
title: '[Node] Async/Await로 동기 통신하기'
categories:
  - Node
tags:
  - Async
  - Await
published: false
---

자바스크립트로 코딩을 하다 보면 **Ajax**를 이용해서 코딩을 자주 하게 된다.

그러면 이제 예전에도 Promise 포스팅 했다시피 로직을 여러 번 거치는 프로세스 같은 경우

콜백 지옥과 같은 코드가 나오게 된다.

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

아무래도 이와 같은 코드가 있으면 가독성도 떨어질 뿐 아니라 소스가 지저분해져서

추후 유지 보수에서 어려움을 겪게 된다.

그래서 나는 그동안 **Promise** 패턴을 통해서 이를 극복해나아갔다.

근데 아무래도 조금 나아진 것뿐이지 가독성이 좋다고는 볼 수 없을 거 같다.

그래서 찾아본게 **Async/Await** 패턴이다.

간단하게 코드를 통해서 해당 패턴의 방법을 알아보고 내가 생각하는 점을 말해보려고 한다.

```js
const asyncFunction1 = () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(1);
    }, 3000);
  });
};

const asyncFunction2 = () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(2);
    }, 1000);
  });
};

async function excuteJob() {
  const result1 = await asyncFunction1();
  const result2 = await asyncFunction2();

  console.log(result1 + result2); // 3
}

excuteJob();
```

위와 같은 패턴으로 구현하면 동기 통신으로 조금 더? 보기 좋게 구현할 수 있다.

<!-- 본인 느끼기에는 Node도 분명 좋은 백엔드 프레임워크 도구이다.

그러나 아직은 복잡한 프로세스가 들어가 있는 시스템에는 잘 안 어울린다고 생각한다.

이와 같이 가독성이 나아지기는 했지만 본인이 느끼기에는 한눈에 들어오지 않을뿐더러

코드도 좀 더 작성해 줄게 많아 보인다. -->

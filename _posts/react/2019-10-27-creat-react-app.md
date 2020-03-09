---
title: "[React] React 프로젝트 create-react-app로 시작하기"
categories: 
  - React
tags : 
  - create-react-app
---

React 프로젝트를 시작하려하면 생각보다 설정해야할 작업이 다양하다.<br>
대표적으로 WebPack, babel 추가적으로는 문법체크(eslint), test모드, build 등 설정해야할게 많다.<br>
본인의 입맛대로 설정하고싶으신 분들도 있으실거라고 본다.<br>
그러나 React 입문자가 설정하기에는 벅찬부분도 있다고 생각한다.<br>
그래서 페이스북은 이러한 사용자를 생각하여 crate-react-app이라는 패키지로<br>
해당 작업들은 구성해놓은 프로젝트를 단번에 구성할수있게 만들었다.

내용이 어렵진 않으니 React를 시작하려하시는분들에게는 정말 추천드리고 싶다.

혹시나 이해가 안되시는분들은 공식문서를 보고 따라한것이기 때문에 참고하기바란다.<br>
[create-react-app](https://github.com/facebook/create-react-app)

npx를 사용하면 필요한 패키지까지 같이 설치해준다.

```shell
npx create-react-app my-app
```

설치시간이 조금 걸린다. 다 설치되면 아래와 같이 프로젝트가 구성되어 있을것이다.

![디렉토리](/assets/images/post/2019-10-27-creat-react-app-image1.PNG)

너무나 간편하게 프로젝트 구성이 끝났다. 이제 시작해보자

```shell
npm start
```

서버가 다 올라가면 브라우저를 키고 **localhost:3000**을 입력하여 접속해보자.

![인덱스](/assets/images/post/2019-10-27-creat-react-app-image2.PNG)

올바르게 서버가 올라갔다. 이제 다른건 신경쓰지말고 오직 React에만 집중해서 코딩을 하면 된다.<br>

작업이 완료되면 테스트도 실시할수 있고, 빌드를 해서 배포준비까지 진행 할 수 있다. 

```
// 테스트모드로 실행합니다.
npm test

// 실서버에 배포될 앱으로 빌드를 진행합니다.
npm run build
```

사실이것말고도 정말 여러가지가 설정이 되어있다. 한번 시작하기전에 문서를 읽어보는걸 추천드린다.
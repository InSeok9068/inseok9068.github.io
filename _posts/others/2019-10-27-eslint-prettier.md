---
title: "[VSCODE] ESlint/Prettier 설정하기"
categories: 
  - others
tags : 
  - eslint
  - prettier
---

사람들이 많이 사용하는 에디터인 Visual Studio Code(VSCODE)에 다가 코드 포맷터인 Prettier와<br>
ES문법 체크인 Eslint를 사용하여 문법검사를 할 수 있는 설정을 잡아보려 한다. 

코드가 규칙성없이 작성한걸 싫어하는 나로써 이 두개를 설정한뒤 뭔지 모르게 마음의 편안함을 가질수 있었다.

일단 VSCODE는 설치되었다는 가정하게 작성하겠다.

### 1. VSCODE Extensions를 통해서 먼저 Prettier 과 ESlint를 설치해주어야한다.

![Prettier](/assets/images/post/2019-10-27-eslint-prettier-image1.PNG)
![ESlint](/assets/images/post/2019-10-27-eslint-prettier-image2.PNG)

### 2. 필요한 Npm 패키지를 다운로드 받아야 한다.

일단 Npm 프로젝트를 하나 만들어 본다.

```shell
// -y는 init을 하면 물어보는 질문에 모두 Yes를 자동적으로 해준다.
npm init -y
```

그러면 디렉토리에 package.json라는 파일이 생겼을것이다.

이제 필요한 Npm 패키지를 다운받아야 한다.<br>

공통적으로는 아래와 같은 패키지를 다운받는다.
- eslint
- prettier
- eslint-config-prettier
- eslint-plugin-prettier

```shell
npm install eslint prettier eslint-config-prettier eslint-plugin-prettier --save-dev
```

다운로드가 완료되면 이제 ES6에 관한 규칙이 여러가지가 존재하는데 본인은 그중에서 가장 보편적으로<br>
사용되는 airbnb을 사용해볼 예정이다.

여기서 한가지 airbnb에는 airbnb과 airbnb-base가 존재하는데 airbnb-base는 React관련 규칙이 빠져있다.<br>
나는 지금은 간단한 js파일을 작성해서 테스트 해볼예정이기 때문에 airbnb-base를 다운로드 하겠다.

airbnb-base 다운받기 위해서는 그에 관련된 패키지들을 모두 다운받아야하는데 해당 문장을 통해 확인 할 수 있다.

```shell
npm info "eslint-config-airbnb-base@latest" peerDependencies

// 아래와 같이 나온다. airbnb을 사용하셨던 분은 React관련된것 까지 좀 더 많이 나온다.
{ eslint: '^5.16.0 || ^6.1.0',
  'eslint-plugin-import': '^2.18.2' }
```

노드 5+ 이상을 사용하시는 분이라면 아래와 같이 npx를 사용하시면 관련 패키지까지 전부 한번에 다운로드가 가능하다.

```shell
npx install-peerdeps --dev eslint-config-airbnb-base
```

다운로드가 완료되면 아래와 같이 나오면 정상적으로 모두 다운로드가 완료된것이다.
```json
"devDependencies": {
  "eslint": "^6.1.0",
  "eslint-config-airbnb-base": "^14.0.0",
  "eslint-config-prettier": "^6.5.0",
  "eslint-plugin-import": "^2.18.2",
  "eslint-plugin-prettier": "^3.1.1",
  "prettier": "^1.18.2"
}
```

자 이제 거의 다왔다. Root디렉토리에 해당 두개의 파일을 만들어서 복사하여준다.<br>
아래의 두개의 파일은 본인의 입맛에 맞게 수정해서 사용하면 된다. 일단 가장 기본적인 설정만 해둔 상태이다.

Prettier, ESlint 공식 홈페이지를 참조하면 설정을 더 다양하게 할 수 있다.<br>
[Prettier](https://prettier.io/docs/en/options.html)<br>
[ESlint](https://eslint.org/docs/rules/)

#### .eslintrc.json 
```json
{ 
    "extends": ["airbnb-base","plugin:prettier/recommended"],
    "plugins": ["prettier"], 
    "rules": {
        "prettier/prettier": "error"
    }
}
```

#### .prettierrc
```json
{ 
  "printWidth": 150 
}
```

테스트해볼 파일 하나를 생성해볼거다. index.js를 Root 디렉토리에 아래와 같이 문법과 정렬이 맞지 않는 코드를 작성해준다.

#### index.js
```js
var a = 2; const b=3
let c= '1';
```

그리곤 Shift + Alt + F를 눌러보자. 아래와 같이 자동적으로 '->"로 변경 변수 선언 사이에 띄어쓰기 줄맞춤 등 자동적으로 코드 정렬이 됐을것이다. 
```js
var a = 2;
const b = 3;
let c = "1";
```

이것을 좀 더 편하게 저장할때마다 자동적으로 코드정렬이 이뤄지게 설정할거다. <br>
VSCODE의 왼쪽 상단 File -> Preferences -> Settings (단축키로는 Ctrl + ,)

![Setting](/assets/images/post/2019-10-27-eslint-prettier-image3.PNG)

오른쪽 위 Open Settings (JSON) 클릭하여 `"editor.formatOnSave"`를 true로 바꿔 주면 된다.<br>
나는 true로 되어있네?? false이거나 해당 코드가 없으신분은 넣어주시면 된다. 그러면 이제 저장할때마다 코드는 알아서 정렬이 된다.
```json
    "[javascript]": {
        "editor.defaultFormatter": "esbenp.prettier-vscode",
        "editor.formatOnSave": true,
    },
```

자 이제 ESlint를 볼것이다 분명 내가 index.js를 작성하라했을때 어느정도 눈치채신분들도 있을것이다.

![INDEX.JS](/assets/images/post/2019-10-27-eslint-prettier-image4.PNG)

아래와 같이 빨간줄과 함께 문법체크로 에러가 발생했다. 

간단한 코드지만 그동안 해왔던 프로젝트에 적용을 하면 분명 이것보다 훨씬 많은 문법오류가 나타날거다.<br>
그러나 모두 지키면 좋겠지만 이게 사실 한번에 바꾸기가 쉽지않다. 그래서 본인이 판단하에 지키고 싶지않는 문법이<br>
존재하면 아래와같이 `.eslintrc.json`에 룰을 설정해주면 된다.

나는 no-var는 에러가 아닌 경고표시로
나머지 no-unused-vars, prefer-const는 아예 체크 안함으로 바꿔보겠다.

```json
{ 
    "extends": ["airbnb-base","plugin:prettier/recommended"],
    "plugins": ["prettier"], 
    "rules": {
        "prettier/prettier": "error",
        "no-var"            :"warn",
        "no-unused-vars"    :"off",
        "prefer-const"      :"off"
    }
}
```

위와 같이 바꾸고 다시 index.js로 가보자.

![INDEX.JS_2](/assets/images/post/2019-10-27-eslint-prettier-image5.PNG)

내가 원하는대로 경고표시와 기존에있던 에러는 표시되지 않는다.

깔끔한 코드정렬과 올바른 문법사용은 유지보수에서도 편리함과 퍼포먼스쪽에서도 유리하게<br> 
작동할것이라고 생각한다.

꼭 사용을 하라는건 아니지만 사용을 권장하면서 이렇게 내가 준비했던 포스팅을 마치려 한다.
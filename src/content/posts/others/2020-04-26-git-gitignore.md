---
title: '[Git] .gitignore 간편하게 설정하기'
category: Others
tags:
  - git
  - gitignore
---

보통 Git을 이용하여 형상관리를 할 것이라 생각한다.

많은 개발자들이 협업하는 과정에서 Git을 통해서 소스코드를 올리거나 내려받을 때

로컬 설정 같은 파일 혹은 컴파일 되어 오는 파일 및 로컬 개발 환경에 맞춰진 의존성 관리라던가

이러한 부분들이 커밋 되어 Git에 올라오게 된다면 다른 개발자들한테 원치 않게 피해를 주거나

설정값이 꼬이는 경우가 가끔 있을 것이다.

이럴 때 사용하는 게 **.gitingore**라는 파일을 통해 Git에 업로드에 제외되는

파일 확장자 혹은 폴더 등을 정의해줄 수 있다.

나는 평소에 <https://github.com/github/gitignore> 해당 링크를 통해 직접 내가 쓰는

언어 혹은 IDE 툴 등등을 찾아서 직접 복 붙으러 설정해 주었다.

근데 찾아보니까 좀 더 편리한 방법이 있어서 공유해 주려고 한다.

## <https://www.gitignore.io/>

위의 링크를 따라가 보면 아래와 같은 페이지가 나오는데

해당 검색란에 내가 사용하는 언어들은 입력한뒤 생성을 누르면

자동으로 **.gitingore** 파일 텍스트가 생성된다.

![gitignore.io](/assets/images/post/2020-04-26-git-gitignore-image1.PNG)

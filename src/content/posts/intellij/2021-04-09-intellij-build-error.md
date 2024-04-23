---
title: '[IntelliJ] Build 시 Out Of Memory 대처법'
categories:
  - IntelliJ
---

IntelliJ 새로운 Version이 Release 돼서 업그레이드하는 도중

기존의 프로젝트가 Build 오류가 나는 현상이 발견되었다.

오류 메시지를 파악해보니 Build 시 **Out Of Memory** 가 발생하여

Build가 더 이상 진행되지 못하였다.

해결책으로는 말 그대로 Build가 진행될 때 Memory를 늘려주면 된다.

VM option으로 해줘도 되지만 간단하게 IntelliJ Option으로 늘려보자

![IMAGE](/assets/images/post/2021-04-09-intellij-build-error-image1.PNG)

### 700 -> 1500

위와 같이 Heap Size 두 배 늘려주니 정상적으로 Build가 동작했다.

---
title: "[Jenkins] Ubuntu에서 Jenkins 설치하기"
categories: 
  - Jenkins
tags:
  - Ubuntu
---

Ubuntu에서 CI 툴인 Jenkins를 설치해보도록 하겠다.

아래와 같은 명령어를 순차대로 입력하면 Jenkins가 설치가 완료된다.

```shell
wget -q -O - https://pkg.jenkins.io/debian/jenkins-ci.org.key | sudo apt-key add -
sudo sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt-get update
sudo apt-get install jenkins
```

나도 인터넷에 있는 저러한 방법으로 설치를 시도했지만 원인을 모를 에러에 마주친적이있다.

그럴때 [Jenkins 설치 메뉴얼](https://wiki.jenkins.io/display/JENKINS/Installing+Jenkins+on+Ubuntu) 해당 링크를 통해서 위와 같은 명령어를 얻었다.

지금 포스팅되어있는 글 또한 분명 추후에는 원인 모를 에러에 맞닥뜨릴 수 있다.

그럴 때는 공식 홈페이지에서 답을 얻는 게 가장 현명한 거 같다.

설치가 완료되면 **http://서버IP/8080** 로 접속을 하면 Jenkins이 설치되어 있는 걸 볼 수 있다.

나는 8080포트가 톰캣 기본 포트이기 때문에 이를 7070포트로 변경해 주었다.

변경 방법은 아래와 같다.

**/etc/default/jenkins** 해당 경로의 파일의 HTTP_PORT를 변경해 주면 된다.

**HTTP_PORT=8080 => HTTP_PORT=7070**

```
sudo systemctl restart jenkins
```

jenkins를 재시작 해주면 완료된다.
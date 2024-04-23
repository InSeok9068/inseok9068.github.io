---
title: '[Linux] Spring Boot Jar파일 Background 실행'
categories:
  - Linux
tags:
  - nohup
---

Spring Boot로 개발을 해서 배포를 하려면 Jar 파일을 실행시켜야 한다.

-Dspring.profiles.active은 Spring의 Profile 설정인데 입력하지 않으면 디폴트로 들어간다.

개인적으로는 배포 시에는 로그 단계를 낮추기 위해서 따로 설정해 주었다.

Linux 아래와 같이 입력해주면 Spring Boot가 실행된다.

```
java -jar -Dspring.profiles.active=prod REST-API-0.0.1-SNAPSHOT.jar
```

그러나 이와 같이 실행하게 되면 연결되어 있는 사용자가 로그아웃을 하거나

다른 작업을 위해서 Command를 빠져나가기만 해도 실행했던 Spring Boot 서버는 내려간다.

이와 같은 문제점을 해결하기 위해 **nohup**이라는 문으로 Background 실행을 할 수 있다.

```
nohup java -jar -Dspring.profiles.active=prod REST-API-0.0.1-SNAPSHOT.jar &
```

위와 같이 실행하면 이제 Background로 서버가 실행되며 해당 폴더에 **nohup.sh**라는 파일이 생길 텐데

```
tail -500f nohup.sh
```

해당 문을 실행시키면 로그를 확인할 수 있다.

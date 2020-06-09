---
title: "[Window] Window에서 특정 포트 Kill 시키기"
categories: 
  - Window
tags : 
  - Tomcat
---

가끔 가다가 로컬 환경에서 IDE를 통해 톰캣을 올리고 내리는 과정에서 오류로 인해

IDE 상에서는 톰캣이 종료되어 있고 실제로는 PORT가 살아있는 경우를 볼 수 있다.

이럴 때는 Window CMD 창에서 직접 포트를 죽여줘야지

IDE 상에서 톰캣을 시작할 수 있는데 이런 방법을 간단하게 알아보자

일단 **8080**포트로 톰캣이 하나 올라가 있다 생각해보자

## **netstat -ao | findstr [PORT값]**

```shell
> netstat -ao | findstr 8080

   TCP    0.0.0.0:8080           DESKTOP-OF09BR0:0      LISTENING       46504
   TCP    [::]:8080              DESKTOP-OF09BR0:0      LISTENING       46504
```

자신의 로컬 기준이므로 PID 값은 유동적으로 변한다.

자 이제 그러면 해당 PORT를 죽여보자.

## **taskkill /f /pid [PID값]**

```shell
> taskkill /f /pid 46504
```

이제 다시 IDE로 돌아가 톰캣을 재구동 시키면 정상적으로 올라가는 것을 확인할 수 있다.
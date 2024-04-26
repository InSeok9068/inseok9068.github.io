---
title: '[SpringBoot] @Scheduled 사용으로 스케줄러 구현'
category: SpringBoot
tags:
  - Scheduled
draft: false
---

Spring Boot를 통해서 간단하게 스케줄러를 구현해보려고 한다.

기본적인 속성으로는

### 속성

cron : cron표현식을 지원한다. "초 분 시 일 월 주 (년)"으로 표현한다.

fixedDelay : milliseconds 단위로, 이전 작업이 끝난 시점으로 부터 고정된 시간을 설정한다. ex) fixedDelay = 5000

fixedDelayString : fixedDelay와 같은데 property의 value만 문자열로 넣는 것이다. ex) fixedDelay = "5000"

fixedRate : milliseconds 단위로, 이전 작업이 수행되기 시작한 시점으로 부터 고정된 시간을 설정한다. ex) fixedRate = 3000

fixedRateString : fixedDelay와 같은데 property의 value만 문자열로 넣는 것이다. ex) fixedRate = "3000"

initialDelay : 스케줄러에서 메서드가 등록되자마자 수행하는 것이 아닌 초기 지연시간을 설정하는 것이다.

initialDelayString : 위와 마찬가지로 문자열로 값을 표현하겠다는 의미다.

zone : cron표현식을 사용했을 때 사용할 time zone으로 따로 설정하지 않으면 기본적으로 서버의 time zone이다.

그 전에crontab 주기설정 방법부터 알아보자.

```
*           *　　　　　　*　　　　　　*　　　　　　*　　　　　　*
초(0-59)   분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　요일(0-7)
```

각 별 위치에 따라 주기를 다르게 설정 할 수 있다.
순서대로 초-분-시간-일-월-요일 순이다. 그리고 괄호 안의 숫자 범위 내로 별 대신 입력 할 수도 있다.
요일에서 0과 7은 일요일이며, 1부터 월요일이고 6이 토요일이다.

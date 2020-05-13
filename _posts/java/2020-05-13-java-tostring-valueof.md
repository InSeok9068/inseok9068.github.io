---
title: "[Java] NullPointException 예방 toString보단 valueOf를 사용하자"
categories: 
  - Java
tags : 
  - NullPointException
  - toString
  - valueOf
published : false
---

우리가 Java를 사용하면서 가장 자주 맞닥뜨리는 에러는 NullPointException을 들 수 있을 것이다.

분명 내가 작성한 코드 안에 무수히 많은 NullPointException 에러가 내재되어 있을 것이다.

이를 방지하기 위해서는 Null을 사전에 체크하거나 올바른 사소한 습관으로 고쳐나가야 할 것이다.
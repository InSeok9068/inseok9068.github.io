---
title: "[SpringBoot] @Transactional 트랜잭션 사용하기"
categories: 
  - SpringBoot
tags : 
  - Transactional
---

데이터 삽입 프로세스를 진행하던 중 에러가 발생하면 기존에 삽입이 이미 이루어진 

데이터들도 다시 롤백이 되어야 한다.

이러한 점을 감안하여 Spring은 @Transactional 이란 어노테이션을 제공한다. 메서드 앞에 해당 어노테이션을 넣으면 

작업 중에 Exception이 발생하면 해당 작업을 모두 롤백 시키며 Exception을 던져준다.

이러한 작업을 꼭 @Transactional을 사용하지 않고 @Service로 되어있는 서비스단 전부를 Spring Boot설정으로

트랜잭션 처리가 가능하게 일괄처리할 수 있다.

```java

@Component
public Class Process {
  
  @Transactional
  public void run {
    .... 데이터 삽입 프로세스
  }
} 

```
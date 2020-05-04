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

또한 해당 Exception마다 롤백 여부 또한 정해줄 수 있다.

- **rollbackOn** : 롤백시킬 Exception

- **dontRollbackOn** : 롤백을 안시킬 Exception

#### @Transactional
```java

@Component
public Class Process {
  
  @Transactional(rollbackOn = RollBackException.class, dontRollbackOn = RuntimeException.class)
  public void run() throws Exception {
    .... 데이터 삽입 프로세스

    if(조건){
        throw new RollBackException();
    }else{
        throw new RuntimeException();
    }
  }
}
```

이러한 작업을 꼭 @Transactional을 사용하지 않고 *serviceImpl로 되어있는 메서드 전부를 Spring Boot설정으로

트랜잭션 처리가 가능하게 일괄처리할 수 있다.

#### Java Config
```java
@Configuration
public class TransactionAspect {
	
	@Autowired
	PlatformTransactionManager transactionManager;

	@Bean
	public TransactionInterceptor transactionAdvice() {
		NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();
		RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();
		
		txAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		HashMap<String, TransactionAttribute> txMethods = new HashMap<String, TransactionAttribute>();
		txMethods.put("*", txAttribute);
		txAttributeSource.setNameMap(txMethods);

		return new TransactionInterceptor(transactionManager, txAttributeSource);
	}

	@Bean
	public Advisor transactionAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* 패키지 경로..service.*Impl.*(..))");
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
	
}
```

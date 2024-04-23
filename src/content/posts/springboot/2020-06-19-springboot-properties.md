---
title: '[SpringBoot] properties값 클래스로 매핑하기'
categories:
  - SpringBoot
tags:
  - Properties
  - ConfigurationProperties
---

Spring Boot로 개발을 하다 보면 **application.properties**에 설정값을 저장하여

사용하는 경우를 볼 수 있다.

간단하게 예시로 들어보면

```properties
# Config Class
config.test1=test
config.test2=111
config.test3=1.5
```

```java
public Class ConfigSetting {

    @Value(${config.test1})
    private String test1

    @Value(${config.test2})
    private int test2

    @Value(${config.test3})
    private double test3

    //..... Config 설정
}
```

이러한 방법으로 **application.properties**의 설정값을 가져와서 사용할 수 있다.

그러나 이러한 설정값이 계속 늘어날수록 Class 변수는 수도 없이 많아지며

가독성 또한 떨어질 수 있다. 그리고 이와 같은 설정값을 여러 군대에서 사용한다면

또 하나씩 변수 선언해 주는 것도 번거로운 일이라 볼 수 있다.

이럴 때 할 수 있는 게 **properties**값으로 클래스와 매핑을 시켜 하나의 클래스로 관리하는 것이다.

이것 또한 간단한 예시로 알아보자

```properties
# Config Class
config.test1=test
config.test2=111
config.test3=1.5
config.testMap.test1=testMap
config.testMap.test2=222
config.testMap.test3=3.0
```

위와 같은 **properties**값이 있다고 가정하고 아래와 같은 클래스를 만들어보자.

```java
@ConfigurationProperties(prefix = "config")
@Data
@Component
public class Config {
    private String test1;

    private int test2;

    private double test3;

    private Map < String, Object > testMap;
}
```

- **@ConfigurationProperties** : prefix에 매핑할 properties 값을 적어준다.
- **@Data** : 스프링 부트가 시작될 때 해당 클래스에 properties 값을 매핑 시켜줄 때 <br>
  Setter 메 서드가 필요함과 동시에 추후 데이터를 꺼낼 것을 대비하여 **@Data**를 선언하여 준다.<br>
  **\* Lombok 라이브러리가 없으면 Getter와 Setter 메서드를 따로 구현해 준다.**
- **@Component** : 해당 클래스를 빈으로 등록해 준다.

**※ 이제 여기서 @EnableConfigurationProperties를 사용하여 @ConfigurationProperties 사용을 명시해 주어야 하지만**
**Spring Boot 공식 문서에 의하면 모든 프로젝트에 자동으로 @EnableConfigurationProperties가 포함되어 있다고 한다. 그러므로 따로 등록해 줄 필요가 없다**

이제 스프링 부트를 다시 실행시켜 보며 해당 클래스의 값을 봐보겠다.

```java
@RequiredArgsConstructor
@RestController
public class TestController {

    private final Config config;

    @GetMapping("/")
    public String root_test() throws Exception {
        System.out.println(config);
        return "Hello World";
    }
}
```

```console
Config(test1=test, test2=111, test3=1.5, testMap={test1=testMap, test2=222, test3=3.0})
```

매핑이 정상적으로 잘 되어있는 걸 확인할 수 있다.

이제 설정값을 보다 쉽게 관리할 수 있게 되었다.

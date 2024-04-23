---
title: '[SpringBoot] 특정 URL만 다른 Encoding 방식 설정하기'
categories:
  - SpringBoot
tags:
  - Filter
  - Encoding
---

오늘은 특정 URL로 서비스에 진입하였을 때 Encoding 방식을 다르게 가져가는 방법을 알아보려 한다.

타사 서비스랑 연동하다 보면 그쪽 연동 규격에 맞춰야 하는 케이스가 있는데 나와 같은 경우

타사의 API의 응답이 **EUC-KR**로 고정되어 Encoding되어 들어와 우리의 서비스의 UTF-8 양식에 맞지 않아

한글이 깨진 경우였으며 아래와 같은 방식으로 해결하였다.

기본 Encoding 방식이 **UTF-8**이라 하면 특정 URL로 진입하였을 때 **EUC-KR**로 Encoding 하는

Filter를 설정하여 처리하려고 한다.

### Filter 설정

```java
@Configuration
public class Encodingfilter {

    @Bean
    public FilterRegistrationBean encodingFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setForceEncoding(true);
        filter.setEncoding("EUC-KR");
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(특정 URL 매핑);
        return registrationBean;
    }
}
```

위와 같이 선언하여 주면 특정 URL로 들어왔을 때는 **EUC-KR**로 Encoding을 해주어

Encoding 양식이 맞지 않아 깨지던 한글도 정상적으로 표기되는 걸 볼 수 있다.

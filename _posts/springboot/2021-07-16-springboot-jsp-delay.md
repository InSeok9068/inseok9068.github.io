---
title: "[SpringBoot] Spring Boot 2.4.X + 버전업 JSP 페이지 딜레이 이슈"
categories:
  - SpringBoot
tags:
  - JSP
  - EL
---

이번에 회사에서 운영하는 서비스의 Spring Boot 버전을 올리는 마이그레이션 작업을 하던 도중

몇 가지 문제점이 발생하여 겪은 문제점과 해결 방법에 대해서 간단히 포스팅하려 한다.

내가 겪은 문제점은 기존의 Spring Boot 2.1.8의 버전을 사용하고 있던 서비스의 Spring Boot 버전을

2.3.8로 업그레이드하였다.

그리고 발생한 문제는 JSP 페이지를 들어갈 때마다 기존과 다르게 너무 긴 딜레이 시간이

걸리는 현상이 발생하였고 이는 크롬 개발자 도구의 네트워크, 퍼포먼스, 스카우터(APM) 도구로

분석하여 해결하려 해도 파악하기 힘든 병목구간이 생겼으며 구글링을 해본 결과

Spring Boot 자체에서도 JSP를 정식 지원하지 않다 보니 EL 문을 사용하여 작성을 하면

이와 같은 현상이 발생하는 글들을 찾아볼 수 있었다.

몇 가지 해결책을 찾아볼 수 있었는데 그중에서도 이와 같은 문제를 파악한 스프링 재단에서

2.5.0 이후의 버전에서는 해당 문제가 수정되었다는 글을 얼핏 찾아볼 수 있었고 2.5.1(GA) 버전으로

마이크 레이션을 진행했으며 그 결과 매 페이지마다 딜레이가 걸리는 현상은 없어졌으나 여전히

첫 페이지 로딩할 때 딜레이는 여전했고 이와 관련해서는 아래와 같은 글을 참조하여 수정하였다.

<https://dirask.com/questions/Spring-Boot-2-4-Tomcat-9-is-serving-web-page-response-content-extremely-slow-for-first-API-requests-1XrwYp>

```java
import org.apache.catalina.Context;
import org.apache.catalina.webresources.ExtractingRoot;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainerCustomizer() {
        return (TomcatServletWebServerFactory container) -> {
            container.addContextCustomizers((Context context) -> {
                // This configuration is used to improve initialization performance.

                context.setResources(new ExtractingRoot());
                context.setReloadable(false);
            });
        };
    }
}
```

위와 같은 설정값을 설정하여 주면 페이지 딜레이 현상은 사라졌다.

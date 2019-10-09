---
title: "개발언어별 의존성 관리도구"
categories: 
  - others
tags : 
  - java
  - node
  - python
---

Java, Node, Python을 사용하여 개발을 진행하다 보면 적재적소에 맞는 라이브러리를 사용한다.<br>
그때마다 하나씩 다운로드해서 사용하기에는 불편함이 이만저만이 아니다. 그래서 있는 게 사실 의존성 관리 도구다<br>

각 언어별로 대표적인 관리도구를 소개해주려한다.

### Java
**Maven**

```xml
    <dependency>
      <groupId>commons-pool</groupId>
      <artifactId>commons-pool</artifactId>
      <version>1.2</version>
    </dependency>
```

<https://mvnrepository.com/> 해당 URL을 통해 원하는 버전과 라이브러리를 찾을 수 있다.

### Node
**NPM**

```shell
$ npm install 라이브러리
```

<https://www.npmjs.com/> 해당 URL을 통해 원하는 버전과 라이브러리를 찾을 수 있다.

### Python
**PIP**

```shell
$ pip install 라이브러리
```

<https://pypi.org/project/pip/> 해당 URL을 통해 원하는 버전과 라이브러리를 찾을 수 있다.
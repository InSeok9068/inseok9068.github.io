---
title: "[SpringBoot] RestTemplate 이용하여 API 통신하기"
categories: 
  - SpringBoot
tags : 
  - RestTemplate
---

오늘은 Spring 3.0부터 지원하며 Rest API의 통신을 할 수 있도록 도와주는

**RestTemplate**을 알아보도록 하겠다.

이전에 내가 포스팅한 [[Java] REST API GET, POST 통신 예제](https://inseok9068.github.io/java/java-rest-ful/)를 통해서도

Rest API 통신을 할 수 있지만 오늘 소개할 **RestTemplate**을 이용하면 Spring에서 공식 지원하는 라이브러리라

좀 더 안정적이고 다양하며 방식으로 사용할 수 있다.

간단하게 Get 통신과 Post 통신을 예제로 통해 알아볼 것이고

Rest API 통신을 도와줄 <http://jsonplaceholder.typicode.com/> 해당 홈페이지의 방식에 맞춰

데이터를 주고받을 것이다.

사전작업으로 POST통신시 Json 데이터 타입으로 으로 주고 받아 JSONObject가 필요함으로

아래의 라이브러리를 추가해준다.

```gradle
implementation 'com.googlecode.json-simple:json-simple:1.1'
```

### RestTemplate 객체 생성

```java
public class RestTemplateMain {
    public static RestTemplate createRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);    // 읽기시간초과, ms
        factory.setConnectTimeout(3000); // 연결시간초과, ms
        HttpClient httpClient = HttpClientBuilder.create()
            .setMaxConnTotal(100)   // connection pool 적용
            .setMaxConnPerRoute(5)  // connection pool 적용
            .build();
        factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅
        return new RestTemplate(factory);
    }
}
```

기초적인 TimeOut과 Connetcion Pool을 설정해 주어 RestTemplate 객체를 만들어준다.

위와 같은 기초 설정을 하지 않아도 Default로 값을 만들어주니 필요에 따라서 값을 변경해서 사용하길 바란다.

---

### Get 통신

**GET	/comments?postId=1 참고**

```java
public class RestTemplateMain {
    public static void main(String[] args) {
        getPost();
    }

    public static RestTemplate createRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);    // 읽기시간초과, ms
        factory.setConnectTimeout(3000); // 연결시간초과, ms
        HttpClient httpClient = HttpClientBuilder.create()
            .setMaxConnTotal(100)   // connection pool 적용
            .setMaxConnPerRoute(5)  // connection pool 적용
            .build();
        factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅
        return new RestTemplate(factory);
    }

    public static void getPost() {

        RestTemplate restTemplate = createRestTemplate();

        HttpEntity<?> httpEntity = new HttpEntity<>(null, null);

        String uri = "http://jsonplaceholder.typicode.com/comments";

        // Query Param 설정
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uri)
                                                                        .queryParam("postId", 1);

        ResponseEntity <List<Map<String, Object>>> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().encode().toUri(), HttpMethod.GET, httpEntity, new ParameterizedTypeReference <List<Map<String, Object>>> () {});

        System.out.println(responseEntity.getBody());
    }
}
```

여기서 하나 알아두면 좋은건 restTemplate의 exchage의 4번째 파라미터  **Class<Map> responseType**의 대해서이다.

만약의 기존의 방식이라면 Response Type Class에 대해서 단편적으로 선언하여 사용한다.

그러나 <code>List<Map<String, Object>></code>까지만 해도 여러 클래스를 품고있어 List.class만으로 표현하기 힘들다.

기존의 방식대로 하면 다시 값을 꺼내어 캐스팅해줘야한다는 불편함이 존재한다.

ParameterizedTypeReference 해당 클래스를 이용하면 제네릭 타입 혹은 <code>List<Map<String, Object>></code>같이 여러클래스를 내포하고있는 값도 표현해줄수있다.

변경전 : <code>ResponseEntity<List> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().encode().toUri(), HttpMethod.GET, httpEntity, List.class);</code>

변경후 : <code>ResponseEntity<List<Map<String, Object>>> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().encode().toUri(), HttpMethod.GET, httpEntity, new ParameterizedTypeReference <List<Map<String, Object>>> () {});</code>

위와같이 API 통신 하면 아래와 같은 값이 리턴된다.

#### Response

```json
[
  {
    "postId": 1,
    "id": 1,
    "name": "id labore ex et quam laborum",
    "email": "Eliseo@gardner.biz",
    "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
  },
  {
    "postId": 1,
    "id": 2,
    "name": "quo vero reiciendis velit similique earum",
    "email": "Jayne_Kuhic@sydney.com",
    "body": "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et"
  },
  {
    "postId": 1,
    "id": 3,
    "name": "odio adipisci rerum aut animi",
    "email": "Nikita@garfield.biz",
    "body": "quia molestiae reprehenderit quasi aspernatur\naut expedita occaecati aliquam eveniet laudantium\nomnis quibusdam delectus saepe quia accusamus maiores nam est\ncum et ducimus et vero voluptates excepturi deleniti ratione"
  },
  {
    "postId": 1,
    "id": 4,
    "name": "alias odio sit",
    "email": "Lew@alysha.tv",
    "body": "non et atque\noccaecati deserunt quas accusantium unde odit nobis qui voluptatem\nquia voluptas consequuntur itaque dolor\net qui rerum deleniti ut occaecati"
  },
  {
    "postId": 1,
    "id": 5,
    "name": "vero eaque aliquid doloribus et culpa",
    "email": "Hayden@althea.biz",
    "body": "harum non quasi et ratione\ntempore iure ex voluptates in ratione\nharum architecto fugit inventore cupiditate\nvoluptates magni quo et"
  }
]
```

---

### Post 통신

```java
public class RestTemplateMain {
    public static void main(String[] args) {
        postPost();
    }

    public static RestTemplate createRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);    // 읽기시간초과, ms
        factory.setConnectTimeout(3000); // 연결시간초과, ms
        HttpClient httpClient = HttpClientBuilder.create()
            .setMaxConnTotal(100)   // connection pool 적용
            .setMaxConnPerRoute(5)  // connection pool 적용
            .build();
        factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅
        return new RestTemplate(factory);
    }

    public static void postPost() {

        RestTemplate restTemplate = createRestTemplate();

        // Http Header 설정
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        // Http Body 설정
        JSONObject bodyParam = new JSONObject();

        bodyParam.put("title", "Rest");
        bodyParam.put("body", "Teamplte");
        bodyParam.put("userId", 1);

        HttpEntity <?> httpEntity = new HttpEntity <>(bodyParam, header);

        String uri = "http://jsonplaceholder.typicode.com/posts";

        // Query Param 설정
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uri);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().encode().toUri(), HttpMethod.POST, httpEntity, Map.class);

        System.out.println(responseEntity.getBody());
    }
}
```

Post 통신에서는 Response Type을 ParameterizedTypeReference를 이용하지않고

Map.class를 이용하여 작업하였다.

#### Response

```json
{
  id: 101,
  title: 'Rest',
  body: 'Teamplte',
  userId: 1
}
```

정상적으로 POST 통신을 한 것을 볼 수 있다.

규칙성을 가지고 **RestTemplate**를 이용한다면 보다 보기 좋고 효율성 있는 코드를 표현할 수 있다.

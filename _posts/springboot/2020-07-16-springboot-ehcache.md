---
title: "[SpringBoot] EhCache 사용하여 DB 과부화 줄이기"
categories: 
  - SpringBoot
tags : 
  - EhCache
---

오늘은 Spring Boot의 **EhCache** 설정법과 사용법에 대해서 포스팅하려고 한다.

우리가 기본적으로 Cache를 사용하여 데이터를 가져오는 이유는

데이터 베이스의 과부하를 줄임과 동시에 짧은 데이터 조회시간을 통해

쾌적한 서비를 제공함에 있다.

그러나 무분별하게 사용할 경우 원치 않는 데이터가 보여 사용자에게 오히려 신뢰성만 떨어트릴 뿐이다.

그래서 신중하게 사용해야 하는데 오늘은 간단하게 예제를 통해서 알아보도록 하겠다.

### Gradle 설정

```gradle
implementation 'org.springframework.boot:spring-boot-starter-cache'
implementation 'net.sf.ehcache:ehcache-core:2.6.11'
```

---

### Bean 생성

```java
@Slf4j
@Component
public class CacheManagerCheck implements CommandLineRunner {

    private final CacheManager cacheManager;

    public CacheManagerCheck(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    // 정상적으로 Bean 생성되었는지 로그를 찍어보기
    @Override
    public void run(String...strings) throws Exception {
        log.info("\n\n" + "=========================================================\n" + "Using cache manager: " +
            this.cacheManager.getClass().getName() + "\n" +
            "=========================================================\n\n");
    }
}
```

### ehcache.xml 설정

application.properties 설정은 Default로 classpath의 ehcache.xml을 찾아가는 거 같다.

그러나 본인이 다른 디렉터리에 ehcache.xml를 작성했다면 아래와 같이 해당 경로를 매핑 시켜주어야 한다.

##### application.properties

```properties
# ehcache 설정
spring.cache.ehcache.config=classpath:ehcache.xml
```

##### ehcache.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
    updateCheck="false"
    monitoring="autodetect"
    dynamicConfig="true">

    <diskStore path="java.io.tmpdir" />

    <cache name="findEmpCache"
           maxEntriesLocalHeap="10000"
           maxEntriesLocalDisk="1000"
           eternal="false"
           diskSpoolBufferSizeMB="20"
           timeToIdleSeconds="300" 
           timeToLiveSeconds="600"
           memoryStoreEvictionPolicy="LFU"
           transactionalMode="off">
        <persistence strategy="localTempSwap" />
    </cache>

</ehcache>
```

##### 설정값

| 속성                             | 설명                                                                                                        | Default |
|:---------------------------------|:-----------------------------------------------------------------------------------------------------------|:--------|
| name                             | 코드에서 사용할 캐시 name                                                                                   | 필수     |
| maxEntriesLocalHeap              | 메모리에 생성 될 최대 캐시 갯수                                                                              | 0       |
| maxEntriesLocalDisk              | 스크에 생성 될 최대 캐시 갯수                                                                                | 0       |
| eternal                          | 영속성 캐시 설정 (지워지는 캐시인지?) external = "true"이면 timeToIdleSecond, timeToLiveSeconds 설정이 무시됨  | false   |
| timeToIdleSecond                 | 해당 초동안 캐시가 호출 되지 않으면 삭제                                                                      | 0       |
| timeToLiveSeconds                | 해당 초가 지나면 캐시가 삭제                                                                                 | 0       |
| overflowToDisk                   | 오버플로우 된 항목에 대해 disk에 저장할 지 여부                                                               | false   |
| diskPersistent                   | 캐시를 disk에 저장하여, 서버 로드 시 캐시를 말아 둘지 설정                                                     | false   |
| diskExpiryThreadIntervalSeconds  | Disk Expiry 스레드의 작업 수행 간격 설정                                                                     | 0       |
| memoryStoreEvictionPolicy        | 캐시의 객체 수가 maxEntriesLocalHeap에 도달하면, 객체를 추가하고 제거하는 정책 설정<br>LRU : 가장 오랫동안 호출 되지 않은 캐시를 삭제<br>LFU : 호출 빈도가 가장 적은 캐시를 삭제<br>FIFO : First In First Out 캐시가 생성된 순서대로 가장 오래된 캐시를 삭제| LRU |

---

### Controller, ServiceImpl

나는 DB조회를 위해 InMemory DB인 **H2**를 미리 설정해놓았다.

Service와, Mapper, mapper.xml 부분은 기본적인 MVC 패턴과 똑같음으로 따로 적진 않겠다.

#### Controller

```java
@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final EmpService empService;

    @GetMapping("/emp/cache")
    public Object getEmpForCache() throws Exception {

        long start = System.currentTimeMillis(); // 수행시간 측정
        Object result = empService.getEmpForCache(); // db 조회
        long end = System.currentTimeMillis();

        log.info("Cache 수행시간 : " + Long.toString(end - start));

        return result;
    }

    @GetMapping("/emp/nocache")
    public Object getEmpForNoCache() throws Exception {

        long start = System.currentTimeMillis(); // 수행시간 측정
        Object result = empService.getEmpForNoCache(); // db 조회
        long end = System.currentTimeMillis();

        log.info("NoCache 수행시간 : " + Long.toString(end - start));

        return result;
    }

    @GetMapping("/emp/cacheClear")
    @ResponseBody
    public String clearEmpCache() {
        empService.clearEmpCache(); // 캐시제거
        return "cache clear!";
    }

}
```

#### Serviceimpl

대용량 데이터 조회라는 조건을 걸기 위해 2초라는 딜레이 시간을 주었다.

@Cacheabl 어노테이션을 통해 해당 메서드에 Cache를 적용할 수 있으며

아까 ehcache.xml name 값을 통해 해당 설정을 불러와 적용이 가능하다.

또한 @CacheEvict을 통해 해당 Cache 키값을 제거해 줄 수 있다.

```java
@Slf4j
@RequiredArgsConstructor
@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;

    @Override
    @Cacheable(value = "findEmpCache")
    public List < Map < String, Object >> getEmpForCache() {
        slowQuery(2000);
        return empMapper.selectEmp();
    }

    @Override
    public List < Map < String, Object >> getEmpForNoCache() {
        slowQuery(2000);
        return empMapper.selectEmp();
    }

    @Override
    @CacheEvict(value = "findEmpCache")
    public void clearEmpCache() {
        log.info("Cache Clear!");
    }

    private void slowQuery(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
```

```console
20200716 16:17:11.951 [http-nio-8080-exec-3] INFO c.e.d.t.w.TestController - Cache 수행시간 : 2002 
20200716 16:17:15.582 [http-nio-8080-exec-4] INFO c.e.d.t.w.TestController - Cache 수행시간 : 0 
20200716 16:17:23.206 [http-nio-8080-exec-5] INFO c.e.d.t.w.TestController - NoCache 수행시간 : 2007 
```

위와 같은 Console 창의 결과를 보면

첫 조회에서는 해당 메서드의 Cache 키값이 존재하지 않아 Cache가 적용되지 않고 실행이 되며

두 번째 조회부터는 키값이 존재하여 InMemory에 저장되어 있는 값을 가져와 데이터 조회를 하지 않고 바로 가져올 수 있다.

세 번째는 Cache를 적용하지 않는 메서드를 불러와 다시 2초의 시간이 걸리는 걸 볼 수 있다.

이와 같이 Cache를 통해 데이터 조회가 가능하지만 적용할 때는 신중해야 한다

---

### 고려사항

내가 생각한 몇 가지 고려 사항을 정리해보면

- 자주 변경되는 데이터일수록 Cache 적용 신중하게 선택해야 한다.
  + 데이터의 무결성이 깨질 염려가 있다.

- 데이터 조회 여러 곳에 Cache를 적용하려면 ImMemory에 쌓이는 Memory 양도 생각해야 한다.
  + 이 부분은 Cache의 생명주기를 어떻게 설정하느냐에 따라 극복할 수도 있다고 생각한다.


자신이 만드는 서비스의 특성을 잘 파악하여 사용한다면 사용자에게 좀 더 쾌적한 환경의 서비스를 제공할 수 있다고 생각한다.
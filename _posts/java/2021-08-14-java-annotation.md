---
title: "[Java] 유용한 어노테이션 정리"
categories:
  - Java
tags:
  - Annotation
---

개발하면서 가끔씩 혹은 자주 사용하게 되는 어노테이션에 대해서 짧게 정리한 내용이며

어노테이션의 사용 패키지 혹은 자세한 사용법은 구글링을 통해 알아보면 좋을 거 같다.

- @Data

  - Lombok의 어노테이션으로 @Getter, @Setter, @ToString, @EqualsAndCode @RequiredArgsConstructor 해당 어노테이션을 한 번에 포함한 어노테이션이다.

- @NotNull, @NonNull

  - 널이 들어오면 안 되는 파라미터 혹은 필드에 정의해 주며 IntelliJ는 @NotNull(org.jetbrains.annotations), 이클립스는 @NonNull(org.eclipse.jdt.annotation)<br>
    공통적으로는 Lombok의 @NonNull도 사용이 가능하다.

- @Nullable

  - 위와 반대로 널을 허용하는 필드 혹은 파라미터에 정의해주면 된다.

- @Transactional

  - 해당 메서드에서의 에러 발생시 트랜잭션을 보장해주는 어노테이션이다.

- @Controller

  - Spring MVC 구조에서의 Controller 명시

- @RestController

  - Rest API 사용하는 Controller 명시

- @ReuqestMapping

  - 리퀘스트 URL를 캐치하는 역할을 한다.
  - 각 메서드 타입별로 @GetMapping, @PostMapping, @PutMapping, @DeleteMapping... 등이 있다.

- @Scheduled

  - 간단하게 사용이 가능한 스케줄링이며 특별한 호출이 없어도 스케줄에 따라 실행된다.

- @Builder

  - 클래스 생성자를 빌더로 만들어주는 Lombok 어노테이션

- @Value

  - properties의 값을 바인딩 해준다.

- @RquestParam

  - ex) www.test.com?number=1
  - 해당 number 파라미터를 바인딩 시 사용

- @PathVariable

  - ex) www.test.com/1
  - 해당 URL의 1의 값을 바인딩 시 사용

# ... 추후 이어서 포스팅 예정

---
title: "[SpringBoot] @Valid로 유효성 검사하기"
categories: 
  - SpringBoot
tags : 
  - Valid
---

Spring에서 파라미터를 주고 받으면 통신을 할때 유효성이 필요할떄 

해당 파라미터에 대해서 하나씩 검증체크를 해주었다.

대표적으로 NotNull 체크에 대해서 알아보도록 하자.

### 예시

```java
@PostMapping("/message")
@ResponseBody
public String pushMessage(@RequestBody Message msg) throws Exception{

  if(msg.getMemo() != null || msg.getMemo().equals("")) {
    throw new Exception("메모를 입력해주세요.");
  }
  
  return "성공";
}
```

그러나 @Valid 어노테이션으로 해당 파라미터들을 간편하고 더 다양하게 유효성 처리해줄수있다.

### 예시

```java
@PostMapping("/message")
@ResponseBody
public String pushMessage(@RequestBody @Valid Message msg) throws Exception{
  
  return "성공";
}
```

```java
import javax.validation.constraints.NotNull;

public class Message {
	
	@NotNull(message = "메모를 입력해주세요.")
	private String Memo;

	public String getMemo() {
		return Memo;
	}

	public void setMemo(String memo) {
		Memo = memo;
	}
}
```

많이 쓰이는 몇가지 어노테이션을 더 알아보도록 하자

```java
@NotNull(message = "Name cannot be null")
private String name;

@AssertTrue
private boolean working;

@Size(min = 10, max = 200, message = "About Me must be between 10 and 200 characters")
private String aboutMe;

@Min(value = 18, message = "Age should not be less than 18")
@Max(value = 150, message = "Age should not be greater than 150")
private int age;

@Email(message = "Email should be valid")
private String email;
```

위와 같은 코드로 좀 더 객체지향적 코드에 가까워진거 같다.

Map으로 데이터를 주고받는경우도 많겠지만 VO객체로 데이터를 주고받는 경우

해당 **@Valid** 어노테이션을 통해서 좀 더 편리하게 구현하기를 바란다.
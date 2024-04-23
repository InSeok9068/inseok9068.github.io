---
title: '[Java] Private 변수 Getter Setter 메소드 자동 완성'
categories:
  - Java
tags:
  - eclipse
---

클래스를 설계하다가보면 클래스의 변수를 함부로 접근 할 수 없게 접근 제한자를 private 변수를 선언하게 된다.

private를 사용하게 되면 변수를 접근 하는데 Get 메소드와 Set 메소드가 필요하게 된다.

이러한 메서드를 이클립스에서는 하나씩 메서드를 선언하는 번거로움을 덜기 위해 자동완성 기능이 있다.

```java
public class TEST_CLASS {

	private int num;

	private String text;

}

```

![IMAGE1](/assets/images/post/2019-10-07-java-get-set-method-image1.png)

![IMAGE2](/assets/images/post/2019-10-07-java-get-set-method-image2.png)

원하는 변수를 선택하여 적용을 시키면 아래와 같이 자동으로 getter setter 메소드를 생성 해주는것을 볼 수 있다.

```java
public class TEST_CLASS {

	private int num;

	private String text;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
```

---
title: "Javasciprt debugging 방법"
categories: 
  - javasciprt
tags : 
  - debugger
---

자바스크립트 debugging 하는 방법으로는 여러가기가 있는데 대표적으로는<br>
다들 break point를 이용해서 에디터 상에서 찍어놓거나, 크롬 개발자 도구를 이용해서 break point를 찍어 사용할 것이다.<br>
소소하지만 다른방법 하나를 소개해주려고 한다.

``` html
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<button id="btn">버튼</button>
</body>
<script type="text/javascript">
$('#btn').on('click',function(){
	debugger //break point
	alert("안녕")
})
</script>
```

이와 같이 직접 코드상으로 break point를 삽입할 수 있다. 다시 지워줘야하는 번거로움이 있지만<br>
이클립스로 JS를 작업하다보면 유용하게 사용하는날이 올것이다.
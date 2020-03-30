---
title: "[Eclipse] 여러가지 초기 세팅"
categories: 
  - Others
tags : 
  - eclipse
---

이클립스 초기 설정을 잡을 때 가끔 내가 편리하게 이용하던 기능을 세팅하는데 까먹거나 하는 경우가 있어

이참에 간단하게 블로그에 남겨 놓으려 한다.

### 1. 테마 설정

#### Dark 테마 설정
- Window > Preferences > General > Appearance > Theme > **Dark**

#### Darkset Dark Theme with DevStyle (Plugin) 
- Help > Eclipse Marketplace > Find(theme) > **Darkset Dark Theme with DevStyle (Install)**

### 2. Validaion 체크
- Window > Preferences > Validation > **JSP, XML, Javascript 해제**

### 3. UTF-8 설정
- [UTF-8 설정](https://gangzzang.tistory.com/entry/%EC%9D%B4%ED%81%B4%EB%A6%BD%EC%8A%A4-%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD-UTF8-%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%84%A4%EC%A0%95)

### 4. Spelling 체크 설정 해제
- Window > Preferences > General > Editors > Text Editors > **Spelling : ‘Enable spell checking’ 체크해제**

### 5. 자동 업데이트 해제
- Window > Preferences > Install/Update > **Automatic Update 해제**

### 6. Heap 메모리 상태 표시
- Window > Preferences > **General에서 Show heap status 체크**

### 7. Search 조건 줄이기
- Ctrl + H > Customize... > **File Search 제외 전부 체크해제** 

  - .java 검색 `*.java`

  - .jsp 검색 `*.jsp`

  - .js 검색 `*.js`

  - .js .jsp 동시 검색 `*.js*`

  - .xml 검색 `*.xml`

### 8. 복사시 느려짐 현상 
- Window > Preferences > General > Editors > Text Editors > Hyperlinking > **JavaScript Element 속성 해제**

### 8. 코드 자동 완성 기능 해제 (본인은 설정 해놓는편)
- Window > Preferences > Java > Editor > Content Assist > **Enable auto activation 체크 해제**

### 10. eclipse.ini 파일 수정
>Xverify:none 
>
>클래스 검사 생략. eclipse 실행 시간 단축
>
>XX:+UseParallelGC 
>
>Parallel Collector를 사용. 병렬 가비지 컬렉션.
>
>XX:-UseConcMarkSweepGC 
>
>병행 mark-sweepGC 기능을 수행하여 GUI 응답 속도 처리
>
>XX:PermSize=32M 
>
>eclipse 클래스 로딩 기본 메모리
>
>XX:MaxPermSize=128M 
>
>eclipse 클래스 로딩 최대 메모리
>
>Xms256m 
>
>eclipse 실행시 잡는 최소 메모리
>
>Xmx256m 
>
>eclipse 실행시 잡는 최대 메모리

#### 예시)
```
-Xverify:none 
-XX:+UseParallelGC
-XX:-UseConcMarkSweepGC 
-XX:PermSize=64M
-XX:MaxPermSize=512M  
-XX:MaxNewSize=512M 
-XX:NewSize=128M 
-Xms2048m
-Xmx2048m
-vm
JDK 경로
```

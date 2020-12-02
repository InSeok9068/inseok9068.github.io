---
title: "[IntelliJ] 여러가지 초기 세팅"
categories: 
  - IntelliJ
---

인텔리 제이 Default Setting (개인적인)

### 1. 단축키 설정

#### 화면 전체 종료 
- Setting > Keymap > `Close` 검색 > Close All **(Ctrl + Shift + F4)** 변경 후 기존값 삭제

#### ~~자동완성 -> 스마트 자동완성~~
- ~~Setting > Keymap > `Completion` 검색 > SmartType **(Ctrl + Space)** 변경 후 기존값 삭제~~

#### 화면 분할
- Setting > Keymap > `Split` 검색 > Split Vertically **(Ctrl + Shift + F11)** 변경

### 2. Plugins 설치
[플러그인 설치](https://inseok9068.github.io/intellij/intellij-plugins/)

### 3. Auto Imports 설정
- Editor > General > Auto Import > Optimize imports on the fly (for courrent project), Add unambiguous imports on the fly 체크

### 4. Live Template 설정
- Editor > Live Templates > 원하는 Templates 추가 및 사용할 applicable contexts 설정

### 5. Tomcat JSP 파일 자동 재시작
- Tmocat > Edit Configuration > Server > On 'Update' action, On frame deactivation `Update classes and resources`

### 6. MultiModule 프로젝트 구성 시 Spring Boot Working directory 못찾는 현상 
- 해당프로젝트 Edit Configuration > Working directory > 해당 프로젝트 경로 절대 경로로 설정

### 7. 자동 빌드 재시작
- Setting > Build, Execution, Deployment > Compiler > Build project automatically 체크
- Ctrl + Shift + A > Registry > `compiler.automake.allow.when.app.running` 체크

### 8. Mybatis XML id 불일치 관련 오류
- Setting > `mybatis` 검색 > Mybatis > Mapper xml inspection 체크 해제
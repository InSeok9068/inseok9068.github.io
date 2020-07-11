# inseok9068.github.io

## 루비 설치 후 Jekyll 설치
```shell
gem install jekyll bundler

bundle install

gem uninstall eventmachine
gem install eventmachine --platform ruby

chcp 65001 
bundle exec jekyll serve --livereload
```

---

## 로컬 서버 실행
한국어 깨지는 현상 

```shell
chcp 65001 
jekyll serve

chcp 65001 
jekyll serve --livereload 브라우저 자동 리로드
```

---

## Jekyll 버전 이슈시 
```shell
chcp 65001 
bundle exec jekyll serve --livereload
```

---

## --livereload 에러시
```
gem uninstall eventmachine
gem install eventmachine --platform ruby
``` 

---

## Jekyll 설정
```yaml
---
# 타이틀
title: "Javasciprt 리스트 중복 제거"

# 제목과 사진
header:
  overlay_image: /assets/images/siba.gif
  image : 

# 포스트 발췌문
excerpt: "Change log of enhancements and bug fixes made to the theme." 

# 태그
tags : 
  - java    

# 카테고리
categories: 
  - javasciprt 

# 목차 설정
toc : 

# 날짜
last_modified_at: 2019-07-01T13:00:00+09:00

# 왼쪽 사이드바 프로필 삭제
author_profile: false

# 포스트 비공개
published : false

# 왼쪽 사이드바 생성
sidebar:
  - title: "Title"
    image: http://placehold.it/350x250
    image_alt: "image"
    text: "Some text here."
  - title: "Another Title"
    text: "More text here."
    nav: sidebar-sample
---
```
---
## 마크다운 문법

#### 동영상 첨부
```html
<iframe width="640" height="360" src="https://www.youtube-nocookie.com/embed/l2Of1-d5E5o?controls=0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe>
```

#### 들여쓰기 및 줄바꿈
Html 문법이 허용되므로
```
들여쓰기 : &nbsp;

띄어쓰기 : <br>
``` 

#### 윗첨자, 아랫첨자

윗첨자<sup>윗첨자<sup>    
아랫첨자<sub>아랫첨자<sub>


```
윗첨자<sup>윗첨자<sup>      -- 윗첨자
아랫첨자<sub>아랫첨자<sub>  -- 아랫첨자
```

#### 가운데 정렬

<center>가운데정렬</center>

#### 이모티콘 넣기

<https://apps.timwhitlock.info/emoji/tables/unicode#note1> 해당 홈페이지에서 Navtive 복사 하여 넣기

😁

#### 마크다운 예약어 그대로 출력
<https://4urdev.tistory.com/62>
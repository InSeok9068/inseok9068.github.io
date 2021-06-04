---
title: "[Markdown] Markdown 문법 총 정리"
categories:
  - Others
tags:
  - Markdown
toc: true
---

오늘은 마크다운 문법에 대해서 정리해보자고 한다.

## 헤더

```markdown
# 제목 1

## 제목 2

### 제목 3

#### 제목 4

##### 제목 5
```

# 제목 1

## 제목 2

### 제목 3

#### 제목 4

##### 제목 5

---

## 목록

### 숫자가 있는 목록

```markdown
1. 사과
2. 딸기
3. 포도
```

1. 사과
2. 딸기
3. 포도

### 숫자가 없는 목록

```markdown
- 사과
- 딸기
- 포도
```

- 사과
- 딸기
- 포도

### 들여쓰기로 계층구조 표현

```markdown
1. 과일
   - 사과

- 채소
  1.  대파
```

1. 과일
   - 사과

- 채소
  1.  대파

---

## 인용문

```markdown
> 인용문
>
> > 인용문
```

> 인용문 1
>
> > 인용문 1-1

---

## 코드 블록

````markdown
```java
String text = "안녕하세요!";
int number = 100;
```
````

```java
String text = "안녕하세요!";
int number = 100;
```

---

## 강조

```markdown
**굵은체**
_기울임_
~~취소선~~
<u>밑줄</u>
윗첨자<sup>윗첨자<sup>  
아랫첨자<sub>아랫첨자<sub>
```

**굵은체**

_기울임_

~~취소선~~

<u>밑줄</u>

윗첨자<sup>윗첨자<sup>

아랫첨자<sub>아랫첨자<sub>

---

## 줄바꿈

```markdown
컴퓨터를 바꿔서<br>게임을 했다.

컴퓨터를 바꿔서

게임을 했다.
```

컴퓨터를 바꿔서<br>게임을 했다.

컴퓨터를 바꿔서

게임을 했다.

---

## 수평선

```markdown
---
```

---

---

## 테이블

```markdown
| 과일 | 당도 |  가격 |
| :--: | :--- | ----: |
| 사과 | 약함 | 1,000 |
| 딸기 | 강함 | 3,000 |
```

|   과일   | 당도     |    가격 |
| :------: | :------- | ------: |
| 파인애플 | 약함     | 1,00000 |
|   딸기   | 매우강함 |   3,000 |

---

## 체크박스

```markdown
- [ ] 미체크
- [x] 체크
```

- [ ] 미체크
- [x] 체크

---

## 링크

```markdown
<https://www.naver.com>

[네이버](<(https://www.naver.com)>)
```

<https://www.naver.com>

[네이버](<(https://www.naver.com)>)

---

## 이미지

```markdown
![이미지1](https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Markdown-mark.svg/300px-Markdown-mark.svg.png)
```

![이미지1](https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Markdown-mark.svg/300px-Markdown-mark.svg.png)

## 동영상

```html
<iframe
  width="640"
  height="360"
  src="https://www.youtube-nocookie.com/embed/l2Of1-d5E5o?controls=0&amp;showinfo=0"
  frameborder="0"
  allowfullscreen
></iframe>
```

<iframe
  width="640"
  height="360"
  src="https://www.youtube-nocookie.com/embed/l2Of1-d5E5o?controls=0&amp;showinfo=0"
  frameborder="0"
  allowfullscreen
></iframe>

---

## 목차

```markdown
[TOC]
```

![목차](/assets/images/post/2021-06-02-markdown-syntax-image1.png)

---

## 이모티콘

사용법 : `Window + .` 이모티콘 사용 및 아래의 링크를 통하여 Navtive 복사 하여 넣기

<https://apps.timwhitlock.info/emoji/tables/unicode#note1>

---

## 그래프, 다이어그램

<https://richwind.co.kr/147> 그래프, 다이어그램 정리

---

---
title: "[Oracle] DEFINE으로 쿼리 실행 시 변수 선언하기"
categories:
  - Oracle
  - DEFINE
---

오늘은 오라클에서 쿼리 실행 시 특정 값을 변수로 선언하여 사용하는 법을 알아보려 한다.

아래와 같이 DEFINE으로 변수에 값을 대입해 준 다음에 `&&변수명` 이와 같이 사용해 주면

변수값을 사용할 수 있디.

```sql
DEFINE DATE_YMD = '20210404';

-- 통계 데이터 1
SELECT *
  FROM STATISTICS_1
 WHERE DATE_YMD = &DATE_YMD&

-- 통계 데이터 2
SELECT *
  FROM STATISTICS_2
 WHERE DATE_YMD = &DATE_YMD&
```

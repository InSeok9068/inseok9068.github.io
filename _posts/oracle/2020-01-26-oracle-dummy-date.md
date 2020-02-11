---
title: "[Oracle] 날짜 Dummy 데이터 만들기"
categories: 
  - oralce
tag :
  - date
---

쿼리를 작성하다 보면 날짜 더비 데이터가 필요할 때 해당 SQL 문을 사용하면

손쉽게 날짜 더미 데이터를 만들 수가 있다.

```sql
SELECT TO_CHAR( TO_DATE(시작날짜, 'YYYYMMDD') + ROWNUM-1, 'YYYYMMDD') AS DAY
FROM DUAL
CONNECT BY
  LEVEL <= ROUND( TO_DATE(종료일자, 'YYYYMMDD') - TO_DATE(시작날짜, 'YYYYMMDD') +1)
```

#### 예시 

```sql
SELECT TO_CHAR( TO_DATE('20200101', 'YYYYMMDD') + ROWNUM-1, 'YYYYMMDD') AS DAY
FROM DUAL
CONNECT BY
  LEVEL <= ROUND( TO_DATE('20200110', 'YYYYMMDD') - TO_DATE('20200101', 'YYYYMMDD') +1)
```

#### SQL 결과

| DAY     |
|:--------|
|20200101 |
|20200102 |
|20200103 |
|20200104 |
|20200105 |
|20200106 |
|20200107 |
|20200108 |
|20200109 |
|20200110 |

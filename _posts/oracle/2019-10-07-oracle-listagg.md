---
title: "[Oracle] 그룹으로 문자열 이어쓰기 (LISTAGG 함수)"
categories: 
  - oralce
---

``` sql
SELECT
  NAME,
  LISTAGG(COMENT, ',') WITHIN GROUP (ORDER BY NAME) COMENT
FROM
  (SELECT
     '홍길동' AS NAME,
     '테스트1' AS COMENT
   FROM DUAL
   UNION ALL
   SELECT
     '홍길동' AS NAME,
     '테스트2' AS COMENT
   FROM DUAL)
GROUP BY NAME;
```
---

| NAME | COMENT        |
|:-----|:--------------|
|홍길동 |테스트1,테스트2 |

---
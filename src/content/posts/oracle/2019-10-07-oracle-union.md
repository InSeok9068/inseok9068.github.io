---
title: '[Oracle] 집합 연산자 UNION ALL, UNION 차이점'
categories:
  - Oracle
---

### UNION ALL

```sql
SELECT
  '홍길동' AS NAME,
  '테스트1' AS COMENT
FROM DUAL
UNION ALL
SELECT
  '홍길동' AS NAME,
  '테스트1' AS COMENT
FROM DUAL

```

| NAME   | COMENT  |
| :----- | :------ |
| 홍길동 | 테스트1 |
| 홍길동 | 테스트1 |

UNION ALL을 집합의 중복을 제거하지 않는다.

---

### UNION

```sql
SELECT
  '홍길동' AS NAME,
  '테스트1' AS COMENT
FROM DUAL
UNION
SELECT
  '홍길동' AS NAME,
  '테스트1' AS COMENT
FROM DUAL

```

| NAME   | COMENT  |
| :----- | :------ |
| 홍길동 | 테스트1 |

UNION ALL을 집합의 중복을 제거 한다.

---

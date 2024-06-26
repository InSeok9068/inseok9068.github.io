---
title: '[Oracle] 1,2와 같이 MultiValue값 조회시 조건 설정법'
category: Oracle
---

### 익명 테이블

| 이름   | 옵션    |
| :----- | :------ |
| 홍길동 | 1,2,3,4 |
| 손흥민 | 1,2,3   |
| 김연아 | 2,3,4   |

해당 테이블이 있다고 생각해보자.

---

**조회조건(1)** : 이제 나는 조회 조건으로 **1,2,3,4**라는 텍스트 값을 조회를 할 것이다.

조회조건의 다중의 값에 포함된 데이터가 모두 조회될 것이다.

```sql
SELECT *
  FROM 테이블
 WHERE (','||'1,2,3,4'||',' LIKE '%,'||옵션||',%');
```

### 결과 값

| 이름   | 옵션    |
| :----- | :------ |
| 홍길동 | 1,2,3,4 |
| 손흥민 | 1,2,3   |
| 김연아 | 2,3,4   |

---

**조회조건(2)** : 이제 나는 조회 조건으로 **2,3**라는 텍스트 값을 조회를 할 것이다.

```sql
SELECT *
  FROM 테이블
 WHERE (','||'2,3'||',' LIKE '%,'||옵션||',%');
```

### 결과 값

| 이름 | 옵션 |
| :--- | :--- |
|      |      |

그러나 이와 같이 조회조건의 더 적은 옵션 값으로

해당 다중의 값에 포함되어 있는 데이터는 조회할 수 없다.

**해당 방법보다 좋은 방법이 많을 테지만 나도 가끔 사용하게 되는 경우가 있어서 포스팅한다.**

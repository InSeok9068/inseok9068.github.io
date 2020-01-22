---
title: "[Oracle] 커밋한 데이터한 복구 방법"
categories: 
  - oralce
tag :
  - commit
---

데이터베이스 작업 도중 작업이 완료한 후 Commit을 한다.

그러나 Commit 한 데이터가 잘못된 데이터이면 복구를 해야 하는데 RollBack은 Commit 데이터를 살리지 못하여 당황할 수 있다.

Oracle에는 Commit를 하였을때 임시로 데이터를 저장하게 되어있으며 그 데이터를 활용하여 데이터를 복구 시킬수 있다.

#### 15분 전의 테이블의 데이터 조회
```sql
SELECT * 
  FROM 테이블명
    AS OF TIMESTAMP(SYSTIMESTAMP-INTERVAL '15' MINUTE); 
 ```

#### 15분 전의 테이블 데이터 조회 후 해당 테이블에 INSERT
```sql
INSERT INTO 테이블명
SELECT *
FROM 테이블명
  AS OF TIMESTAMP(SYSTIMESTAMP-INTERVAL '15' MINUTE);  
```

이러한 방법으로 테이블의 데이터를 복구 시킬 수 있다.

실수를 하지 않는 게 가장 좋지만 만약에 일어날 상황에 대비하여 알아두면 유용하게 사용이 가능하다.
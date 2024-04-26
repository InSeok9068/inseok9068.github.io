---
title: '[Oracle] 데이터베이스 DDL, DML, DCL 명령어와 차이점'
category: Oracle
tags:
  - DDL
  - DML
  - DCL
---

데이터베이스 명령어는 SQL의 기능에 따라 3가지 영역으로 나눌 수 있다.

- 데이터 정의 언어(DDL: Data Definition Language)
- 데이터 조작 언어(DML: Data Manipulation Language)
- 데이터 제어 언어(DCL: Data Control Language)

#### (1) 데이터 정의 언어

DDL이란 데이터베이스 내의 개체를 생성 및 삭제하고 그 구조를 조작하는 SQL 문을 총칭하는 용어이다. 대표적인 DDL 명령어로는 `CRATE, ALTER, DROP`문이 있으며 이들은 각각 데이터베이스 내의 개체(스키마, 테이블, 인덱스)등을 생성하거나, 수정하거나, 삭제하기 위해 사용된다.

```sql
-- 테이블 생성
CREATE TABLE DEPT(
    DEPTNO      NUMBER (2),
    DNAME       VARCHAR2 (14),
    START_DATE  TIMESTAMP(7)
);

-- 테이블 수정
ALTER TABLE DEPT
ADD (END_DATE TIMESTAMP(7))

-- 테이블 삭제
DROP TABLE DEPT
```

---

#### (2) 데이터 조작 언어

DML은 테이블에 행(row) 추가, 수정, 삭제 등의 조작과 관련된 SQL문을 총칭하는 용어이다. 대표적인 DML 명령어로는 `INSERT, UPDATE, DELETE`문이 있다. 또한 직접적으로 데이터를 수정하지 않더라고 테이블 내의 데이터를 검색하는 데에 이용되는 `SELECT` 문 또한 DML로 간주된다.

```sql
-- 데이터 조회
SELECT DEPTNO,DNAME
  FROM DEPT

-- 데이터 삽입
INSERT INTO DEPT(
    DEPTNO,
    DNAME,
    START_DATE,
)VALUES(
    1,
    '인사과',
    SYSDATE
)

-- 데이터 수정
UPDATE DEPT
   SET DNAME   = '정보과'
 WHERE DEPTNO  = 1

-- 데이터 삭제
DELETE FROM DEPT
```

---

#### (3)데이터 제어 언어

DCL은 DBMS 접근에 대한 권한을 관리하는 SQL문을 총칭하는 용어이다. Oracle에서의 DCL 문은 `GRANT, REVOKE` 문으로 구성된다. 주로 데이터베이스 관리자(DBA)에 의해 많이 사용된다.

```sql
-- 권한 부여
GRANT SELECT ON DEPT TO [USER];

-- 권한 제거
REVOKE SELECT ON DEPT FROM [USER];
```

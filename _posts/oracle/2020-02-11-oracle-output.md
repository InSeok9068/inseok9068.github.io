---
title: "[Oracle] 프로시저 결과 및 값을 출력 DBMS_OUTPUT"
categories: 
  - oralce
tag :
  - output
---

PL/SQL 문을 작성하다 보면 결괏값을 보거나 오류가 발생한 부분을 확인하고 싶을 때

해당 **DBMS_OUTPUT**을 사용하면 DBMS_OUTPUT 창에 해당 값을 표시해줄 수 있다.

```sql

BEGIN
   DBMS_OUTPUT.PUT_LINE('출력 확인');
END;

```

DBMS_OUTPUT 창에 **출력 확인** 이란 메시지가 뜨는지 확인해보면 된다.


하나의 예시로 INSERT도 중 에러가 발생했을 때 어떠한 값 때문에 에러가 발생했는지 볼 수 있다.

#### 예시

```sql
DECLARE
  OUT_MSG VARCHAR2(4000);
BEGIN

  INSERT INTO 테이블 (
    값1,
    값2,
    값3,
  )VALUES(
    변수1,
    변수2,
    변수3
  );
  EXCEPTION 
  WHEN OTHERS THEN
      OUT_MSG := 'INSERT 중 오류 발생.(테이블명)-'|| 변수1 || '-' || 변수2 ||'-'|| 변수3 ||'-'||SQLERRM;
      DBMS_OUTPUT.PUT_LINE(OUT_MSG);
      RETURN;

END;

```

이러한 INSERT 작업 도중 중간에 에러 발생 시 해당 변수들을 DBMS_OUTPUT 창에 출력하게 되면

LOOP 문을 통한 데이터 INSERT 작업을 좀 더 편리하게 가능하다.
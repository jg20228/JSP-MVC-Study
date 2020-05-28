#### Jsp 모델 2 블로그 프로젝트

## 오라클 12C 사용자 생성
```sql
alter session set "_ORACLE_SCRIPT"=true;
CREATE USER blog IDENTIFIED BY bitc5600;

GRANT CREATE SESSION TO blog;
GRANT CREATE TABLESPACE TO blog; 
GRANT CREATE TABLE TO blog; 
GRANT CREATE SEQUENCE TO blog; 
alter user cos default tablespace users quota unlimited on users; 
GRANT RESOURCE, CONNECT TO blog;
```
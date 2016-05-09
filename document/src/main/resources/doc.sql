
DELETE FROM t_dep WHERE id = 1;
DESC t_dep;
SHOW CREATE DATABASE document_db;
show table status from document_db like '%t_dep%';
SHOW FULL COLUMNS FROM t_dep;

SELECT * FROM t_user;

SELECT * FROM t_user WHERE nickname LIKE '%王%';
SELECT * FROM t_user WHERE role = 0;
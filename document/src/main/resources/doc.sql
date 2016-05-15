
DELETE FROM t_dep WHERE id = 1;
DESC t_dep;
SHOW CREATE DATABASE document_db;
show table status from document_db like '%t_dep%';
SHOW FULL COLUMNS FROM t_dep;

SELECT * FROM t_user;

SELECT * FROM t_user WHERE nickname LIKE '%王%';
SELECT * FROM t_user WHERE role = 0;

SELECT * FROM t_user t1
  LEFT JOIN t_dep_scope td on t1.dep_id = td.depId
  RIGHT JOIN t_user t2 on td.scope_id = t2.dep_id
WHERE t1.id = 2 ORDER BY td.scope_id ASC;
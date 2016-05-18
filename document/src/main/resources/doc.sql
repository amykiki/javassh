DELETE FROM t_dep
WHERE id = 1;
DESC t_dep;
SHOW CREATE DATABASE document_db;
SHOW TABLE STATUS FROM document_db
LIKE '%t_dep%';
SHOW FULL COLUMNS FROM t_dep;

SELECT *
FROM t_user;

SELECT *
FROM t_user
WHERE nickname LIKE '%王%';
SELECT *
FROM t_user
WHERE role = 0;

SELECT *
FROM t_user t1
  LEFT JOIN t_dep_scope td ON t1.dep_id = td.depId
  RIGHT JOIN t_user t2 ON td.scope_id = t2.dep_id
  LEFT JOIN t_dep tdep ON t2.dep_id = tdep.id
WHERE t1.id = 1
ORDER BY td.scope_id ASC;

SELECT *
FROM t_user t1
  LEFT JOIN t_dep_scope td ON t1.dep_id = td.depId
  RIGHT JOIN t_user t2 ON t2.dep_id = td.scope_id
WHERE t1.id = 2
ORDER BY td.scope_id;

SELECT *
FROM t_msg m
  INNER JOIN t_user_msg um
    ON m.id = um.m_id
       AND um.is_send = 0 AND um.u_id IN
                              (SELECT u.id
                               FROM t_user u
                               WHERE u.nickname LIKE '%沈%');

SELECT DISTINCT m.id, m.title, m.create_date
                            FROM t_msg m
                              INNER JOIN t_user_msg um ON m.id = um.m_id AND um.is_send = 0
                              INNER JOIN t_user u1 ON um.u_id = u1.id
                              INNER JOIN t_msg_attach ma1 ON m.id = ma1.msg_id
                              INNER JOIN t_attach ta ON ma1.attach_id = ta.id
                              INNER JOIN t_user u2 ON m.author_id = u2.id
                            WHERE u2.id = '2'
                                  AND u1.nickname LIKE '%沈%'
                                  AND ta.old_name LIKE '%.jpg%'
                                  AND m.content LIKE '%正文%' OR m.title LIKE '%正文%'
                            ORDER BY m.create_date DESC;

SELECT *
FROM t_msg m
  INNER JOIN t_user_msg um ON m.id = um.m_id AND um.is_send = 0
  INNER JOIN t_user u1 ON um.u_id = u1.id
  INNER JOIN t_msg_attach ma1 ON m.id = ma1.msg_id
  INNER JOIN t_attach ta ON ma1.attach_id = ta.id
  INNER JOIN t_user u2 ON m.author_id = u2.id
WHERE u2.id = '2'
      AND u1.nickname LIKE '%沈%'
      AND ta.old_name LIKE '%.jpg%'
      AND m.content LIKE '%正文%' OR m.title LIKE '%正文%'
ORDER BY m.create_date DESC;

SELECT count(DISTINCT m.id)
FROM t_msg m
  INNER JOIN t_user_msg um ON m.id = um.m_id AND um.is_send = 0
  INNER JOIN t_user u1 ON um.u_id = u1.id
  INNER JOIN t_msg_attach ma1 ON m.id = ma1.msg_id
  INNER JOIN t_attach ta ON ma1.attach_id = ta.id
  INNER JOIN t_user u2 ON m.author_id = u2.id
WHERE u2.id = '2'
      AND u1.nickname LIKE '%沈%'
      AND ta.old_name LIKE '%.jpg%'
      AND m.content LIKE '%正文%' OR m.title LIKE '%正文%'
ORDER BY m.create_date DESC;


SELECT
  m.id,
  m.title,
  m.create_date
FROM t_msg AS m
DELETE FROM t_dep
WHERE id = 1;
DESC t_dep;
SHOW CREATE DATABASE document_db;
SHOW TABLE STATUS FROM document_db
LIKE '%t_dep%';
SHOW FULL COLUMNS FROM t_dep;

# 增加一列
ALTER TABLE t_user_msg ADD is_delete TINYINT(4) NOT NULL DEFAULT FALSE;
SELECT *
FROM t_user;

SELECT count(DISTINCT msg.id) FROM t_msg msg WHERE msg.author_id = 2;

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

SELECT DISTINCT
  m.id,
  m.title,
  m.create_date
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


SELECT *
FROM t_msg m
  INNER JOIN t_user_msg um ON m.id = um.m_id AND um.is_send = 0
  INNER JOIN t_user u1 ON m.author_id = u1.id
  INNER JOIN t_msg_attach ma1 ON m.id = ma1.msg_id
  INNER JOIN t_attach ta ON ma1.attach_id = ta.id
WHERE u1.id = '2'
      AND um.u_id = '176'
      AND (m.content LIKE '%标题%' OR m.title LIKE '%标题%')
ORDER BY m.create_date DESC;

SELECT *
FROM t_user_msg um1
  INNER JOIN t_msg msg ON um1.m_id = msg.id
  INNER JOIN t_user su ON msg.author_id = su.id
  LEFT JOIN t_msg_attach ma on msg.id = ma.msg_id
  LEFT JOIN t_attach ta on ma.attach_id = ta.id
WHERE um1.is_send = 0
  AND um1.u_id = 176
      AND (msg.content LIKE '%标题%' OR msg.title LIKE '%标题%')
      AND su.id = 2
ORDER BY msg.create_date DESC ;

SELECT * FROM t_user_msg um
INNER JOIN t_msg msg on um.m_id = msg.id
  INNER JOIN t_user_msg um2 ON um2.m_id = msg.id
WHERE msg.author_id = 2
      AND um.is_send = 1
      AND um.is_delete = 0
      AND um2.u_id = 176
      AND um2.is_send = 0
;

UPDATE t_user_msg um SET um.is_delete = 1 WHERE um.m_id = 2 AND um.u_id = 2;

SELECT * FROM t_msg as msg
  INNER JOIN t_user as author on msg.author_id = author.id
INNER JOIN t_dep as adep on author.dep_id = adep.id
LEFT JOIN t_msg_attach mattach on msg.id = mattach.msg_id
LEFT JOIN t_attach as attach on mattach.attach_id = attach.id
LEFT JOIN t_user_msg as um on msg.id = um.m_id
LEFT JOIN t_user as ruser on um.u_id = ruser.id
  LEFT JOIN t_dep AS rdep on ruser.dep_id = rdep.id
WHERE msg.id = 3;
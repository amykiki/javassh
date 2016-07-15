CREATE TABLE t_perm (
  id BIGINT AUTO_INCREMENT,
  permission VARCHAR(100) NOT NULL,
  CONSTRAINT pk_t_perm PRIMARY KEY (id)
)
  CHARSET=utf8 ENGINE=InnoDB;
CREATE UNIQUE INDEX index_t_perm_permission ON t_perm(permission);

CREATE TABLE t_role_perm(
  role_id INT(4),
  perm_id BIGINT,
  CONSTRAINT pk_t_role_perm PRIMARY KEY (role_id, perm_id)
)
  CHARSET=utf8 ENGINE= InnoDB;

INSERT INTO t_perm(permission) VALUES ('sys:user');
INSERT INTO t_perm(permission) VALUES ('sys:user:role');
INSERT INTO t_perm (permission) VALUES ('sys:msg');


UPDATE t_perm SET permission = 'sys:user:list' WHERE id = 2;
UPDATE t_user SET role = 0 WHERE id = 1;
UPDATE t_user SET role = 2 WHERE id = 2;

INSERT INTO t_role_perm VALUES (0, 0);
INSERT INTO t_role_perm VALUES (0, 3);
INSERT INTO t_role_perm VALUES (2, 1);

UPDATE t_role_perm SET perm_id = 2 WHERE role_id = 2 AND perm_id = 1;

SELECT * FROM t_user u RIGHT JOIN t_role_perm p ON u.role = p.role_id WHERE u.username = 'amysue';

SELECT * FROM t_user u
  INNER JOIN t_role_perm p ON u.role = p.role_id
  LEFT JOIN t_perm perm ON p.perm_id = perm.id
WHERE u.username = 'amysue';
SELECT perm.permission FROM t_user u INNER JOIN t_role_perm p ON u.role = p.role_id LEFT JOIN t_perm perm ON p.perm_id = perm.id WHERE u.username = 'amysue';
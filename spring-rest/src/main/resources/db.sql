SHOW TABLES;

CREATE TABLE Student (
  id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(100) NOT NULL DEFAULT '',
  lastname VARCHAR(100) NOT NULL DEFAULT '',
  uscId VARCHAR(10),
  birthday DATE,
  gender CHAR(1),
  gpa REAL(4,3),
  mentorId INT(11),
  department VARCHAR(255),
  PRIMARY KEY(id)
)ENGINE=InnoDB CHARSET=utf8;
INSERT INTO Student VALUES (NULL , 'amy', 'zou', '1234506789', str_to_date('04-18-1987', '%m-%d-%Y'), 'F', 3.75, NULL , '通信工程');

CREATE TABLE Class (
  id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL DEFAULT '',
  section CHAR(1),
  days VARCHAR(10),
  begin_time TIME,
  end_time TIME,
  PRIMARY KEY (id)

) ENGINE= InnoDB CHARSET = utf8;
INSERT INTO Class VALUES (NULL, 'Algorithm', 'A', 'TTH', str_to_date('09-01-2016 09:00:00', '%m-%d-%Y %H:%i:%s'), NULL );

CREATE TABLE ClassToken (
  student_id INT(11) UNSIGNED NOT NULL,
  class_id INT(11) UNSIGNED NOT NULL,
  grade VARCHAR(2),
  PRIMARY KEY (student_id, class_id)
)ENGINE= InnoDB CHARSET = utf8;

INSERT INTO ClassToken VALUES (1, 1, 'A+');

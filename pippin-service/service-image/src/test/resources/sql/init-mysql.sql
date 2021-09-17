CREATE TABLE tb_locations (
  id bigint(20) NOT NULL,
  flag varchar(6) DEFAULT NULL,
  local_code varchar(30) DEFAULT NULL,
  local_name varchar(100) DEFAULT NULL,
  lv int(11) DEFAULT NULL,
  sup_local_code varchar(30) DEFAULT NULL,
  url varchar(60) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tb_file_info (
  id bigint(20) NOT NULL,
  file_name varchar(300) DEFAULT NULL,
  file_path varchar(300) DEFAULT NULL,
  file_size bigint(20) DEFAULT NULL,
  suffix_name varchar(30) DEFAULT NULL,
  md5 varchar(40) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
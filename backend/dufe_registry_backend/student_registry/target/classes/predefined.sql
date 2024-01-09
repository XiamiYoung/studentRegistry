create database StudentRegistry CHARACTER SET=utf8 COLLATE=utf8_bin;
USE StudentRegistry;
show tables;

delete from tbl_user;
delete from tbl_registry;

insert into tbl_user (PK,USER_NAME,PASSWORD,CREATED_TS,UPDATED_TS) values(1,'admin123','admin123',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

commit;
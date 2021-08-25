drop table if exists id_generator_config;
create table id_generator_config(
    `key` char(32) not null,
    template char(64) not null,
    step integer not null,
    initial_value char(64) not null,
    primary key(`key`)
);

insert into id_generator_config(`key`,template,step,initial_value) values
('user.user','{id}',10,1000),
('order.sales_order','XSDD{year}{month}{day}{id:8}',10,'0');

create table user(
    id integer not null auto_increment,
    name char(32) not null,
    password char(60) not null,
    roles varchar(128) not null,
    remark varchar(255) not null,
    is_enabled integer not null,
    primary key( id )
);

create table persistent_logins(
      username varchar(64) not null,
      series varchar(64) not null,
      token varchar(64) not null,
      last_used datetime not null,
      primary key(series)
);


insert into user(id,name,roles,remark,is_enabled,password) values
(10001,'admin','admin','',1,'$2a$12$WtxiMJuXjgzCpa1OWT8hR.wMpxq0DbeF1fMpCJbdzCdhdYte1ZtfC'),
(10002,'fish','clerk','',1,'$2a$12$WtxiMJuXjgzCpa1OWT8hR.wMpxq0DbeF1fMpCJbdzCdhdYte1ZtfC');
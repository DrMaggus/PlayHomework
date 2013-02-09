# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table sign_in (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  constraint pk_sign_in primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table sign_in;

SET FOREIGN_KEY_CHECKS=1;


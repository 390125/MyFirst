# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table label (
  id                            bigint auto_increment not null,
  title                         varchar(255) not null,
  create_date                   datetime(6) not null,
  update_date                   datetime(6) not null,
  constraint pk_label primary key (id)
);


# --- !Downs

drop table if exists label;


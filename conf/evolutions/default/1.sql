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

create table task (
  id                            bigint auto_increment not null,
  label_id                      bigint(19) not null,
  title                         varchar(255) not null,
  done                          tinyint(1) default 0 not null,
  create_date                   datetime(6) not null,
  update_date                   datetime(6) not null,
  constraint pk_task primary key (id)
);

alter table task add constraint fk_task_label_id foreign key (label_id) references label (id) on delete restrict on update restrict;
create index ix_task_label_id on task (label_id);


# --- !Downs

alter table task drop foreign key fk_task_label_id;
drop index ix_task_label_id on task;

drop table if exists label;

drop table if exists task;


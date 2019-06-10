create table IF NOT EXISTS users(
      username varchar(250) not null primary key,
      password varchar(500) not null,
      enabled boolean not null);

create table IF NOT EXISTS authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username));
  --create unique index ix_auth_username on authorities (username,authority);

create table IF NOT EXISTS groups (
  id bigint primary key,
  group_name varchar(50) not null);

create table IF NOT EXISTS group_authorities (
  group_id bigint not null,
  authority varchar(50) not null,
constraint fk_group_authorities_group foreign key(group_id) references groups(id));

create table IF NOT EXISTS group_members (
  id bigint primary key,
  username varchar(50) not null,
  group_id bigint not null,
  constraint fk_group_members_group foreign key(group_id) references groups(id));
-- this is a mysql test database that i'm using

-- mysql -u root -p
--
-- create database if not exists scrumtaculous;
-- use scrumtaculous;
-- 
-- grant all on scrumtaculous.* to 'scrumtaculous'@'localhost' identified by 'scrumtaculous';
-- flush privileges;
-- \q
-- mysql < create_db.sql

----------
-- create statements
----------


drop database scrumtaculous;
create database scrumtaculous;
use scrumtaculous;

create table project
(
   id                      integer unsigned auto_increment primary key,
   name                    varchar(256)  unique not null,
   iteration_length_weeks  integer unsigned not null,
   kickoff                 timestamp not null,
   version                 integer unsigned default 0 not null
) engine=innodb;

create table iteration
(
   id         integer unsigned auto_increment primary key,
   number     integer unsigned not null,
   name       varchar(256),
   project_id integer unsigned not null,
   version    integer unsigned default 0 not null,
   foreign key  (project_id) references project (id),
   unique  key number_unique_for_project (number, project_id)
) engine=innodb;

create table story
(
   id           integer unsigned auto_increment primary key,
   name         varchar(256),
   iteration_id integer unsigned not null,
   ordinal      integer unsigned not null,
   points       integer unsigned, 
   version      integer unsigned default 0 not null,
   foreign key  (iteration_id) references iteration (id),
   unique  key name_unique_for_iteration (name, iteration_id),
   unique  key ordinal_unique_for_iteration (ordinal, iteration_id)
) engine=innodb;
-- points should only be <null> 0 1 2 3 5 8 13 21 34 55 89

create table task
(
   id              integer unsigned auto_increment primary key,
   name            varchar(256),
   story_id        integer unsigned not null,
   hours_estimated integer unsigned, 
   hours_actual    integer unsigned, 
   status          enum('NOT_STARTED', 'PROCESSING', 'TESTING', 'VERIFYING', 'DOCUMENTATION', 'DONE'),
   version         integer unsigned default 0 not null,
   foreign key  (story_id) references story (id),
   unique  key name_unique_for_story (name, story_id)
) engine=innodb;

create table user
(
   id       integer unsigned auto_increment primary key,
   name     varchar(64) unique not null,
   password varchar(64) not null,
   email    varchar(64),
   version  integer unsigned default 0 not null
) engine=innodb;

create table story_note
(
   story_id     integer unsigned not null,
   content      varchar(256),
   user_id      integer unsigned,
   create_time  timestamp default current_timestamp comment 'when the note was created',
   foreign key  (story_id) references story (id),
   foreign key  (user_id) references user (id)
) engine=innodb;

create table story_tag
(
   story_id    integer unsigned not null,
   name        varchar(256) not null,
   foreign key (story_id) references story (id),
   unique  key tag_name_unique_for_story_id (story_id, name)
) engine=innodb;

----------
-- test data
----------
insert into project set
   name = 'test 1',
   iteration_length_weeks = 2,
   kickoff = "20080808";
select @PROJECT_ID := LAST_INSERT_ID();

insert into iteration set
   number = 0,
   name = 'backlog',
   project_id = @PROJECT_ID;

select @BACKLOG_ID := LAST_INSERT_ID();

insert into iteration set
   number = 1,
   name = 'make box',
   project_id = @PROJECT_ID;

select @ITERATION_1_ID := LAST_INSERT_ID();

insert into iteration set
   number = 2,
   project_id = @PROJECT_ID;

insert into iteration set
   number = 3,
   project_id = @PROJECT_ID;

   insert into story set
      name = 'as a user I want a pony',
      iteration_id = @BACKLOG_ID,
      ordinal = 0;
   select @PONY_STORY_ID := LAST_INSERT_ID();

      insert into story_note set
         story_id = @PONY_STORY_ID,
         content = 'I want one because they are cool';
      
      insert into story_note set
         story_id = @PONY_STORY_ID,
         content = '...yeah, totally cool';

   insert into story set
      name = 'as a user I want to see a box',
      iteration_id = @BACKLOG_ID,
      ordinal = 1;

   insert into story set
      name = 'as an admin I want to control who sees a box',
      iteration_id = @BACKLOG_ID,
      ordinal = 2;

insert into story set
   name = 'as an admin I should see a box',
   iteration_id = @ITERATION_1_ID,
   ordinal = 2;


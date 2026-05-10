create table lock_tracking (
  object_type   varchar2(30),
  object_key    number,
  username      varchar2(30) not null,
  obtained      date default sysdate,
  primary key (object_type, object_key);
);

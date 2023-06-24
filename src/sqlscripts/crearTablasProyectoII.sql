prompt PL/SQL Developer Export Tables for user AMARIN@UNA
prompt Created by Angelo on viernes, 23 de junio de 2023
set feedback off
set define off

prompt Dropping PLAYER...
drop table PLAYER cascade constraints;
prompt Dropping LEVEL_SET...
drop table LEVEL_SET cascade constraints;
prompt Dropping SAVE...
drop table SAVE cascade constraints;
prompt Dropping LEVEL_PROGRESS...
drop table LEVEL_PROGRESS cascade constraints;
prompt Dropping PROGRESS_BOX_POS...
drop table PROGRESS_BOX_POS cascade constraints;
prompt Dropping STAGE...
drop table STAGE cascade constraints;
prompt Dropping TILE...
drop table TILE cascade constraints;
prompt Creating PLAYER...
create table PLAYER
(
  player_name             VARCHAR2(30) not null,
  player_levels_completed NUMBER(5),
  player_sets_completed   NUMBER(4)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table PLAYER
  add constraint PK_PLAYER primary key (PLAYER_NAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt Creating LEVEL_SET...
create table LEVEL_SET
(
  level_set_name         VARCHAR2(30) not null,
  level_set_creator      VARCHAR2(30),
  level_set_times_played NUMBER(5)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table LEVEL_SET
  add constraint PK_LEVEL_SET primary key (LEVEL_SET_NAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table LEVEL_SET
  add constraint FK_LEVEL_SET_CREATOR foreign key (LEVEL_SET_CREATOR)
  references PLAYER (PLAYER_NAME);

prompt Creating SAVE...
create table SAVE
(
  save_id              NUMBER(2) not null,
  save_player          VARCHAR2(30) not null,
  save_level_set       VARCHAR2(30) not null,
  save_stages_unlocked NUMBER(2) not null
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SAVE
  add constraint PK_SAVE primary key (SAVE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table SAVE
  add constraint FK_SAVE_LEVELS foreign key (SAVE_LEVEL_SET)
  references LEVEL_SET (LEVEL_SET_NAME);
alter table SAVE
  add constraint FK_SAVE_PLAYER foreign key (SAVE_PLAYER)
  references PLAYER (PLAYER_NAME);

prompt Creating LEVEL_PROGRESS...
create table LEVEL_PROGRESS
(
  progress_save_id     NUMBER(2) not null,
  progress_level       NUMBER(2) not null,
  progress_player_posx NUMBER(2),
  progress_player_posy NUMBER(2)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table LEVEL_PROGRESS
  add constraint PK_PROGRESS primary key (PROGRESS_LEVEL, PROGRESS_SAVE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table LEVEL_PROGRESS
  add constraint FK_PROGRESS_SAVE foreign key (PROGRESS_SAVE_ID)
  references SAVE (SAVE_ID);

prompt Creating PROGRESS_BOX_POS...
create table PROGRESS_BOX_POS
(
  progress_save_id NUMBER(2) not null,
  progress_level   NUMBER(2) not null,
  box_posx         NUMBER(2) not null,
  box_posy         NUMBER(2) not null
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table PROGRESS_BOX_POS
  add constraint PK_BOX primary key (PROGRESS_SAVE_ID, PROGRESS_LEVEL, BOX_POSX, BOX_POSY)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table PROGRESS_BOX_POS
  add constraint FK_BOX_PROGRESS foreign key (PROGRESS_LEVEL, PROGRESS_SAVE_ID)
  references LEVEL_PROGRESS (PROGRESS_LEVEL, PROGRESS_SAVE_ID);

prompt Creating STAGE...
create table STAGE
(
  stage_level_set    VARCHAR2(30) not null,
  stage_number       NUMBER(2) not null,
  stage_initial_posx NUMBER(2) not null,
  stage_initial_posy NUMBER(2) not null,
  stage_size         NUMBER(2)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table STAGE
  add constraint PK_STAGE primary key (STAGE_LEVEL_SET, STAGE_NUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table STAGE
  add constraint FK_STAGE_LEVEL_SET foreign key (STAGE_LEVEL_SET)
  references LEVEL_SET (LEVEL_SET_NAME);

prompt Creating TILE...
create table TILE
(
  tile_level_set VARCHAR2(30) not null,
  tile_stage     NUMBER(2) not null,
  tile_posx      NUMBER(2) not null,
  tile_posy      NUMBER(2) not null,
  tile_type      VARCHAR2(8) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
alter table TILE
  add constraint PK_TILE primary key (TILE_LEVEL_SET, TILE_STAGE, TILE_POSX, TILE_POSY)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table TILE
  add constraint FK_TILE_STAGE foreign key (TILE_LEVEL_SET, TILE_STAGE)
  references STAGE (STAGE_LEVEL_SET, STAGE_NUMBER);

prompt Disabling triggers for PLAYER...
alter table PLAYER disable all triggers;
prompt Disabling triggers for LEVEL_SET...
alter table LEVEL_SET disable all triggers;
prompt Disabling triggers for SAVE...
alter table SAVE disable all triggers;
prompt Disabling triggers for LEVEL_PROGRESS...
alter table LEVEL_PROGRESS disable all triggers;
prompt Disabling triggers for PROGRESS_BOX_POS...
alter table PROGRESS_BOX_POS disable all triggers;
prompt Disabling triggers for STAGE...
alter table STAGE disable all triggers;
prompt Disabling triggers for TILE...
alter table TILE disable all triggers;
prompt Disabling foreign key constraints for LEVEL_SET...
alter table LEVEL_SET disable constraint FK_LEVEL_SET_CREATOR;
prompt Disabling foreign key constraints for SAVE...
alter table SAVE disable constraint FK_SAVE_LEVELS;
alter table SAVE disable constraint FK_SAVE_PLAYER;
prompt Disabling foreign key constraints for LEVEL_PROGRESS...
alter table LEVEL_PROGRESS disable constraint FK_PROGRESS_SAVE;
prompt Disabling foreign key constraints for PROGRESS_BOX_POS...
alter table PROGRESS_BOX_POS disable constraint FK_BOX_PROGRESS;
prompt Disabling foreign key constraints for STAGE...
alter table STAGE disable constraint FK_STAGE_LEVEL_SET;
prompt Disabling foreign key constraints for TILE...
alter table TILE disable constraint FK_TILE_STAGE;
prompt Loading PLAYER...
prompt Table is empty
prompt Loading LEVEL_SET...
prompt Table is empty
prompt Loading SAVE...
prompt Table is empty
prompt Loading LEVEL_PROGRESS...
prompt Table is empty
prompt Loading PROGRESS_BOX_POS...
prompt Table is empty
prompt Loading STAGE...
prompt Table is empty
prompt Loading TILE...
prompt Table is empty
prompt Enabling foreign key constraints for LEVEL_SET...
alter table LEVEL_SET enable constraint FK_LEVEL_SET_CREATOR;
prompt Enabling foreign key constraints for SAVE...
alter table SAVE enable constraint FK_SAVE_LEVELS;
alter table SAVE enable constraint FK_SAVE_PLAYER;
prompt Enabling foreign key constraints for LEVEL_PROGRESS...
alter table LEVEL_PROGRESS enable constraint FK_PROGRESS_SAVE;
prompt Enabling foreign key constraints for PROGRESS_BOX_POS...
alter table PROGRESS_BOX_POS enable constraint FK_BOX_PROGRESS;
prompt Enabling foreign key constraints for STAGE...
alter table STAGE enable constraint FK_STAGE_LEVEL_SET;
prompt Enabling foreign key constraints for TILE...
alter table TILE enable constraint FK_TILE_STAGE;
prompt Enabling triggers for PLAYER...
alter table PLAYER enable all triggers;
prompt Enabling triggers for LEVEL_SET...
alter table LEVEL_SET enable all triggers;
prompt Enabling triggers for SAVE...
alter table SAVE enable all triggers;
prompt Enabling triggers for LEVEL_PROGRESS...
alter table LEVEL_PROGRESS enable all triggers;
prompt Enabling triggers for PROGRESS_BOX_POS...
alter table PROGRESS_BOX_POS enable all triggers;
prompt Enabling triggers for STAGE...
alter table STAGE enable all triggers;
prompt Enabling triggers for TILE...
alter table TILE enable all triggers;

set feedback on
set define on
prompt Done

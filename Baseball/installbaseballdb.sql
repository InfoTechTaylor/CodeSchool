drop database if exists baseball;

create database baseball;

use baseball;
CREATE TABLE `baseball`.`team` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `baseball`.`player` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `home_town` VARCHAR(45) NULL,
  `team_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_team_idx` (`team_id` ASC),
  CONSTRAINT `fk_team`
    FOREIGN KEY (`team_id`)
    REFERENCES `baseball_test`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

  
  
  
  drop database if exists baseball_test;

create database baseball_test;
use baseball_test;
CREATE TABLE `baseball_test`.`team` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

use baseball_test;
CREATE TABLE `baseball_test`.`player` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `home_town` VARCHAR(45) NULL,
  `team_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_team_idx` (`team_id` ASC),
  CONSTRAINT `fk_team`
    FOREIGN KEY (`team_id`)
    REFERENCES `baseball_test`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
  
  use baseball_test;
    CREATE TABLE `baseball_test`.`position` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));
  
  use baseball_test;
CREATE TABLE `baseball_test`.`player_position` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `player_id` BIGINT NOT NULL,
  `position_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_player_idx` (`player_id` ASC),
  INDEX `fk_position_idx` (`position_id` ASC),
  UNIQUE INDEX `position_player_unique` (`player_id` ASC, `position_id` ASC),
  CONSTRAINT `fk_player`
    FOREIGN KEY (`player_id`)
    REFERENCES `baseball_test`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_position`
    FOREIGN KEY (`position_id`)
    REFERENCES `baseball_test`.`position` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

use baseball_test;
drop table player_position;
select * from team;



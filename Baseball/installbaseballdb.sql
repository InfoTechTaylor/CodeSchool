drop database if exists baseball;

create database baseball;

use baseball;
CREATE TABLE `baseball`.`team` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
  
  
  drop database if exists baseball_test;

create database baseball_test;
use baseball_test;
CREATE TABLE `baseball_test`.`team` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));





/* PROD */
DROP DATABASE IF EXISTS contact_list;

CREATE DATABASE IF NOT EXISTS contact_list;


USE contact_list;

CREATE TABLE IF NOT EXISTS `contacts` (
	`contact_id` int(11) NOT NULL auto_increment,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `company` VARCHAR(50) NOT NULL,
    `phone` VARCHAR(10) DEFAULT NULL,
    `email` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=23;



/* TEST */
DROP DATABASE IF EXISTS contact_list_test;

CREATE DATABASE IF NOT EXISTS contact_list_test;


USE contact_list_test;

CREATE TABLE IF NOT EXISTS `contacts` (
	`contact_id` int(11) NOT NULL auto_increment,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `company` VARCHAR(50) NOT NULL,
    `phone` VARCHAR(10) DEFAULT NULL,
    `email` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=23;
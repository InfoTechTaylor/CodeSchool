DROP DATABASE IF EXISTS supersightings;

CREATE DATABASE supersightings;
USE supersightings;

CREATE TABLE person (
  id bigint NOT NULL AUTO_INCREMENT,
  type varchar(10) NOT NULL,
  name varchar(45) NOT NULL,
  description varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE person_power (
  id bigint NOT NULL AUTO_INCREMENT,
  person_id bigint NOT NULL,
  power_id bigint NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idxPower ON person_power (person_id, power_id);

CREATE TABLE superpower (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE person_power
ADD CONSTRAINT fk_person_personpower
FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE NO ACTION;

ALTER TABLE person_power
ADD CONSTRAINT fk_superpower_personpower
FOREIGN KEY (power_id) REFERENCES superpower (id) ON DELETE NO ACTION;

CREATE TABLE person_organization (
  id bigint NOT NULL AUTO_INCREMENT,
  person_id bigint NOT NULL,
  organization_id bigint NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idxOrganization ON person_organization (person_id, organization_id);


CREATE TABLE organization (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  description varchar(255) NOT NULL,
  location_id bigint NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE person_organization
ADD CONSTRAINT fk_personorganization_person
FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE NO ACTION;

ALTER TABLE person_organization
ADD CONSTRAINT fk_personorganization_organization
FOREIGN KEY (organization_id) REFERENCES organization (id) ON DELETE NO ACTION;

CREATE TABLE sighting (
  id bigint NOT NULL AUTO_INCREMENT,
  location_id bigint NOT NULL,
  sighting_date date NOT NULL,
  description varchar(255),
  PRIMARY KEY (id)
);



CREATE TABLE person_sighting (
  id bigint NOT NULL AUTO_INCREMENT,
  person_id bigint NOT NULL,
  sighting_id bigint NOT NULL,
  PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX idxSighting ON person_sighting (person_id, sighting_id);

CREATE TABLE location (
  id bigint NOT NULL AUTO_INCREMENT,
  latitude decimal(10, 7) NOT NULL,
  longitude decimal(10, 7) NOT NULL,
  name varchar(50) NOT NULL,
  description varchar(100) NOT NULL,
  street varchar(100) NOT NULL,
  city varchar(100) NOT NULL,
  state varchar(10) NULL,
  zip varchar(10) NULL,
  country varchar(25) NULL,
  PRIMARY KEY (ID)
);

ALTER TABLE sighting
ADD CONSTRAINT fk_sighting_location
FOREIGN KEY (location_id) REFERENCES location (id) ON DELETE NO ACTION;

ALTER TABLE organization
ADD CONSTRAINT fk_organization_location
FOREIGN KEY (location_id) REFERENCES location (id) ON DELETE NO ACTION;



DROP DATABASE IF EXISTS supersightings_test;

CREATE DATABASE supersightings_test;
USE supersightings_test;

CREATE TABLE person (
  id bigint NOT NULL AUTO_INCREMENT,
  type varchar(10) NOT NULL,
  name varchar(45) NOT NULL,
  description varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE person_power (
  id bigint NOT NULL AUTO_INCREMENT,
  person_id bigint NOT NULL,
  power_id bigint NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idxPower ON person_power (person_id, power_id);

CREATE TABLE superpower (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE person_power
ADD CONSTRAINT fk_person_personpower
FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE NO ACTION;

ALTER TABLE person_power
ADD CONSTRAINT fk_superpower_personpower
FOREIGN KEY (power_id) REFERENCES superpower (id) ON DELETE NO ACTION;

CREATE TABLE person_organization (
  id bigint NOT NULL AUTO_INCREMENT,
  person_id bigint NOT NULL,
  organization_id bigint NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idxOrganization ON person_organization (person_id, organization_id);


CREATE TABLE organization (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  description varchar(255) NOT NULL,
  location_id bigint NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE person_organization
ADD CONSTRAINT fk_personorganization_person
FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE NO ACTION;

ALTER TABLE person_organization
ADD CONSTRAINT fk_personorganization_organization
FOREIGN KEY (organization_id) REFERENCES organization (id) ON DELETE NO ACTION;

CREATE TABLE sighting (
  id bigint NOT NULL AUTO_INCREMENT,
  location_id bigint NOT NULL,
  sighting_date date NOT NULL,
  description varchar(255),
  PRIMARY KEY (id)
);



CREATE TABLE person_sighting (
  id bigint NOT NULL AUTO_INCREMENT,
  person_id bigint NOT NULL,
  sighting_id bigint NOT NULL,
  PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX idxSighting ON person_sighting (person_id, sighting_id);

CREATE TABLE location (
  id bigint NOT NULL AUTO_INCREMENT,
  latitude decimal(10, 7) NOT NULL,
  longitude decimal(10, 7) NOT NULL,
  name varchar(50) NOT NULL,
  description varchar(100) NOT NULL,
  street varchar(100) NOT NULL,
  city varchar(100) NOT NULL,
  state varchar(10) NULL,
  zip varchar(10) NULL,
  country varchar(25) NULL,
  PRIMARY KEY (ID)
);

ALTER TABLE sighting
ADD CONSTRAINT fk_sighting_location
FOREIGN KEY (location_id) REFERENCES location (id) ON DELETE NO ACTION;

ALTER TABLE organization
ADD CONSTRAINT fk_organization_location
FOREIGN KEY (location_id) REFERENCES location (id) ON DELETE NO ACTION;


Use supersightings;

INSERT INTO Person (type, name, description)
values ("Person", "Bruce Wayne", "Rich man that dresses like bat with fancy toys"),
("Hero", "Wonder Woman", "Amazon warrior");

INSERT INTO Location(latitude, longitude, name, description, street, city, state, zip, country)
values (40.779287, -73.969326, "Central Park", "near Belvedere Castle", "79th Street", "New York", "NY", "10021", "USA"),
(51.503157, -0.119715, "London Eye", "The Ferris Wheel", "The Queen's Walk", "London", "Lambeth", "SE1 7PB", "UK");

INSERT INTO Sighting(location_id, sighting_date, description)
values (2, "2018-04-10", "Wonder Woman spotted in London beating up men in ally.");

INSERT INTO person_sighting(person_id, sighting_id)
values(2, 1);

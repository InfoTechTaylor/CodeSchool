DROP DATABASE IF EXISTS AddressBook;

CREATE DATABASE IF NOT EXISTS AddressBook;

USE AddressBook;

CREATE TABLE Contact
(
	contactID int NOT NULL auto_increment,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    streetAddress VARCHAR(50) NOT NULL,
    city VARCHAR(30) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    PRIMARY KEY(contactID)
);

INSERT INTO Contact (firstName, lastName, streetAddress, city, state, zip)
VALUES('Brett', 'Hicklin', '5 Gretchens Way', 'Barrington', 'NH', '03825'),
('Brian', 'Lapointe', '6 Main Street', 'Manchester', 'NH', '03030');


DROP DATABASE IF EXISTS AddressBook_test;

CREATE DATABASE IF NOT EXISTS AddressBook_test;

USE AddressBook_test;

CREATE TABLE Contact
(
	contactID int NOT NULL auto_increment,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    streetAddress VARCHAR(50) NOT NULL,
    city VARCHAR(30) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    PRIMARY KEY(contactID)
);

INSERT INTO Contact (firstName, lastName, streetAddress, city, state, zip)
VALUES('Brett', 'Hicklin', '5 Gretchens Way', 'Barrington', 'NH', '03825'),
('Brian', 'Lapointe', '6 Main Street', 'Manchester', 'NH', '03030');

USE AddressBook_test;
SELECT * FROM Contact;
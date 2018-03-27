USE DVD;

DROP DATABASE IF EXISTS DVD;

CREATE DATABASE IF NOT EXISTS DVD;


USE DVD;
CREATE TABLE IF NOT EXISTS Dvd
(
	dvdID int NOT NULL auto_increment,
    dvdTitle VARCHAR(50) NOT NULL,
    releaseDate DATE,
    director VARCHAR(50) NULL,
    mpaaRating VARCHAR(5),
    notes VARCHAR(128),
    PRIMARY KEY (dvdID)
);

INSERT INTO Dvd (dvdTitle, releaseDate, director, mpaaRating, notes)
VALUES ('Harry Potter', '2001-12-1', 'Christopher Colombus','PG', NULL),
		('Fantastic Beasts and Where to Find Them', '2016-11-1', NULL, 'PG-13', 'Fantastic!');
        
        

DROP DATABASE IF EXISTS DVD_test;

CREATE DATABASE IF NOT EXISTS DVD_test;


USE DVD_test;
CREATE TABLE IF NOT EXISTS Dvd
(
	dvdID int NOT NULL auto_increment,
    dvdTitle VARCHAR(50) NOT NULL,
    releaseDate DATE,
    director VARCHAR(50) NULL,
    mpaaRating VARCHAR(5),
    notes VARCHAR(128),
    PRIMARY KEY (dvdID)
);

INSERT INTO Dvd (dvdTitle, releaseDate, director, mpaaRating, notes)
VALUES ('Harry Potter', '2001-12-1', 'Christopher Colombus', 'PG', NULL),
		('Fantastic Beasts and Where to Find Them', '2016-11-1', NULL, 'PG-13', 'Fantastic!');
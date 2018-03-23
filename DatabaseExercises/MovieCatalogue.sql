CREATE DATABASE MovieCatalogue;

USE MovieCatalogue;

    
CREATE TABLE Genre
(
	GenreID INT NOT NULL auto_increment,
    GenreName VARCHAR(30) NOT NULL,
    PRIMARY KEY (GenreID)
);

USE MovieCatalogue;

CREATE TABLE Director
(
	DirectorID INT NOT NULL auto_increment,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate DATE,
    PRIMARY KEY(DirectorID)
);

USE MovieCatalogue;
CREATE TABLE Rating
(
	RatingID INT NOT NULL auto_increment,
    RatingName VARCHAR(5),
    PRIMARY KEY (RatingID)
);


USE MovieCatalogue;
CREATE TABLE Actor
(
	ActorID INT NOT NULL auto_increment,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate DATE,
    PRIMARY KEY(ActorID)
);

USE MovieCatalogue;
CREATE TABLE Movie
(
	MovieID INT NOT NULL auto_increment,
    GenreID INT NOT NULL,
    DirectorID INT NOT NULL,
    RatingID INT NOT NULL,
    Title VARCHAR(128) NOT NULL,
    ReleaseDate DATE,
    PRIMARY KEY (MovieID),
    FOREIGN KEY (GenreID) REFERENCES Genre(GenreID),
    FOREIGN KEY (DirectorID) REFERENCES Director(DirectorID),
    FOREIGN KEY (RatingID) REFERENCES Rating(RatingID)
);


USE MovieCatalogue;
CREATE TABLE CastMembers
(
	CastMemberID INT NOT NULL auto_increment,
    ActorID INT NOT NULL,
    MovieID INT NOT NULL,
    Role VARCHAR(50) NOT NULL,
    PRIMARY KEY(CastMemberID),
    FOREIGN KEY(ActorID) REFERENCES Actor(ActorID),
    FOREIGN KEY(MovieID) REFERENCES Movie(MovieID)
    
);



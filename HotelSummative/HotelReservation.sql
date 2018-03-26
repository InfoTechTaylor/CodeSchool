/************* DROP the database and all objects if they exist************************************/
 DROP DATABASE IF EXISTS HotelReservation;


/************* CREATE the database and all tables with primary keys*******************************/
CREATE DATABASE IF NOT EXISTS HotelReservation;

USE HotelReservation;

CREATE TABLE HotelFloor
  (
    FloorID int NOT NULL,
    PRIMARY KEY (FloorID)
  );

CREATE TABLE Room
  (
    RoomNum int NOT NULL,
    FloorID int NOT NULL,
    OccupantLimit int NOT NULL,
    RoomTypeID int NOT NULL,
    PRIMARY KEY(RoomNum)
  );
  

CREATE TABLE RoomAmmenity
(
	RoomNum int NOT NULL,
    AmmenityID int NOT NULL,
    PRIMARY KEY (RoomNum, AmmenityID)
);


CREATE TABLE Ammenity
(
	AmmenityID int NOT NULL auto_increment,
    AmmenityName VARCHAR(30) NOT NULL,
    PRIMARY KEY (AmmenityID)
);

CREATE TABLE RoomType
(
	RoomTypeID int NOT NULL auto_increment,
    RoomType VARCHAR(30) NOT NULL,
    PRIMARY KEY (RoomTypeID)
);

CREATE TABLE RoomRate
(
	RateID int NOT NULL auto_increment,
    Rate DECIMAL(7,2) NOT NULL,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME,
    RoomTypeID int NOT NULL,
    PRIMARY KEY (RateID)
);

CREATE TABLE RoomReservation
(
	RoomNum int NOT NULL,
    ReservationID int NOT NULL,
    PRIMARY KEY (RoomNum, ReservationID)
);

CREATE TABLE Reservation
(
	ReservationID int NOT NULL auto_increment,
    CustomerID int NOT NULL,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME NOT NULL,
    PromoCodeID int,
    PRIMARY KEY (ReservationID)
);

CREATE TABLE BillReservation
(
	BillID int NOT NULL,
    ReservationID int NOT NULL,
    PRIMARY KEY (BillID, ReservationID)
);

CREATE TABLE Bill
(
	BillID int NOT NULL auto_increment,
    BillAmount DECIMAL(7,2) NOT NULL,
    TaxAmount DECIMAL(7,2) NOT NULL,
    TotalBill DECIMAL(7,2) NOT NULL,
    TaxID int NOT NULL,
    PRIMARY KEY (BillID)
);

CREATE TABLE BillAddOn
(
	BillID int NOT NULL,
    AddOnID  INT NOT NULL,
    PRIMARY KEY (BillID, AddOnID)
);

CREATE TABLE PromoCode
(
	PromoCodeID int NOT NULL auto_increment,
    PromoCode VARCHAR(30) NOT NULL,
    PromoCodeType VARCHAR(30) NOT NULL,
    PromoAmount DECIMAL(5,2) NOT NULL,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME,
    PRIMARY KEY (PromoCodeID)
);

CREATE TABLE AddOn
(
	AddOnID int NOT NULL auto_increment,
    AddOnName VARCHAR(30) NOT NULL,
    PRIMARY KEY (AddOnID)
);

CREATE TABLE AddOnRate
(
	AddOnRateID int NOT NULL auto_increment,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME,
    Rate DECIMAL (7,2) NOT NULL,
    AddOnID int NOT NULL,
    PRIMARY KEY (AddOnRateID)
);

CREATE TABLE PERSON
(
	PersonID int NOT NULL auto_increment,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    DOB DATE NOT NULL,
    Email VARCHAR(50),
    Phone VARCHAR(10),
    PRIMARY KEY(PersonID)
);

CREATE TABLE Customer
(
	CustomerID int NOT NULL auto_increment,
    PersonID int NOT NULL,
    PRIMARY KEY (CustomerID)
);

CREATE TABLE GuestReservation
(
	ReservationID int NOT NULL,
    GuestID int NOT NULL,
    PRIMARY KEY (ReservationID, GuestID)
);

CREATE TABLE Guest
(
	GuestID int NOT NULL auto_increment,
    PersonID int NOT NULL,
    PRIMARY KEY(GuestID)
);

CREATE TABLE Tax
(
	TaxID int NOT Null auto_increment,
    TaxAmount DECIMAL(4,2) NOT NULL,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME,
    PRIMARY KEY (TaxID)
);

/************* MODIFY Tables to add foreign keys*******************************/

ALTER TABLE RoomRate
ADD CONSTRAINT fk_RoomRate_RoomType
FOREIGN KEY (RoomTypeID) REFERENCES RoomType(RoomTypeID);

ALTER TABLE Room
ADD CONSTRAINT fk_Room_Floor
FOREIGN KEY (FloorID) REFERENCES HotelFloor(FloorID);

ALTER TABLE Room
ADD CONSTRAINT fk_Room_RoomType
FOREIGN KEY (RoomTypeID) REFERENCES RoomType(RoomTypeID);

ALTER TABLE AddOnRate
ADD CONSTRAINT fk_AddOnRate_AddOn
FOREIGN KEY (AddOnID) REFERENCES AddOn(AddOnID);

ALTER TABLE Reservation
ADD CONSTRAINT fk_Reservation_Customer
FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID);

ALTER TABLE Reservation
ADD CONSTRAINT fk_Reservation_PromoCode
FOREIGN KEY (PromoCodeID) REFERENCES PromoCode(PromoCodeID);

ALTER TABLE Customer
ADD CONSTRAINT fk_Customer_Person
FOREIGN KEY (PersonID) REFERENCES Person(PersonID);

ALTER TABLE Guest
ADD CONSTRAINT fk_Guest_Person
FOREIGN KEY (PersonID) REFERENCES Person(PersonID);

ALTER TABLE RoomAmmenity
ADD CONSTRAINT fk_RoomAmmenity_Room
FOREIGN KEY (RoomNum) REFERENCES Room(RoomNum);

ALTER TABLE RoomAmmenity
ADD CONSTRAINT fk_RoomAmmenity_Ammenity
FOREIGN KEY (AmmenityID) REFERENCES Ammenity(AmmenityID);

ALTER TABLE RoomReservation
ADD CONSTRAINT fk_RoomReservation_Room
FOREIGN KEY (RoomNum) REFERENCES Room(RoomNum);

ALTER TABLE RoomReservation
ADD CONSTRAINT fk_RoomReservation_Reservation
FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID);

ALTER TABLE BillReservation
ADD CONSTRAINT fk_BillReservation_Bill
FOREIGN KEY (BillID) REFERENCES Bill(BillID);

ALTER TABLE BillReservation
ADD CONSTRAINT fk_BillReservation_Reservation
FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID);

ALTER TABLE BillAddOn
ADD CONSTRAINT fk_BillAddOn_Bill
FOREIGN KEY (BillID) REFERENCES Bill(BillID);

ALTER TABLE BillAddOn
ADD CONSTRAINT fk_BillAddOn_AddOn
FOREIGN KEY (AddOnID) REFERENCES AddOn(AddOnID);

ALTER TABLE GuestReservation
ADD CONSTRAINT fk_GuestReservation_Reservation
FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID);

ALTER TABLE GuestReservation
ADD CONSTRAINT fk_GuestReservation_Guest
FOREIGN KEY (GuestID) REFERENCES Guest(GuestID);

ALTER TABLE Bill
ADD CONSTRAINT fk_Bill_Tax
FOREIGN KEY (TaxID) REFERENCES Tax(TaxID);



/****** POPULATE DATEBASE ************************************************************/

/*Floor*/        
INSERT INTO HotelFloor(FloorID)
VALUES (1),
		(2),
        (3);
        
/*RoomType*/
INSERT INTO RoomType(RoomType)
VALUES ('Single'),
		('Double');
        
/*Room*/
INSERT INTO Room(RoomNum, FloorID, OccupantLimit, RoomTypeID)
VALUES (101, 1, 4, 2),
		(102, 1, 2, 1),
		(201, 2, 2, 1),
        (202, 2, 4, 2),
        (301, 3, 4, 2),
        (302, 3, 2, 1);

/*RoomRate*/
INSERT INTO RoomRate(Rate, StartDate, EndDate, RoomTypeID)
VALUES (129.99, '2017-01-01', '2017-12-30', 1),
		(139.99, '2018-01-01', NULL, 1),
        (159.99, '2017-01-01', '2017-12-30', 2),
        (169.99, '2018-01-01', NULL, 2);
        
/*Ammenity*/
INSERT INTO Ammenity(AmmenityName)
VALUES ('Mini Fridge'),
		('Toaster'),
        ('Free Wi-Fi'),
        ('Free Breakfast Buffet');
        
/*RoomAmmenity*/
INSERT INTO RoomAmmenity(RoomNum, AmmenityID)
VALUES (101, 1),
		(102, 4),
        (201, 3),
        (202, 3),
        (301, 3),
        (302, 3),
        (301, 2),
        (101, 4);
        
/*Person*/
INSERT INTO Person(FirstName, LastName, DOB, Email, Phone) 
VALUES ('John', 'Doe', '1963/1/5', 'john.d@email.com', '5555555555'),
		('Sally', 'Smith', '1982/3/21', 'sally@email.com', '4444444444'),
        ('Mark', 'Smith', '1982/4/21', 'Mark@email.com', '4444444446'),
        ('Joe', 'Williams', '1975/2/18', NULL, NULL),
        ('Jim', 'Sullivan', '1980/5/24', NULL, NULL);
        
/*Customer*/
INSERT INTO Customer(PersonID)
VALUES (1),
		(2),
        (3);
        
/*Guest*/
INSERT INTO Guest(PersonID)
VALUES(4),
	(5);
    
/*Promo Code*/
INSERT INTO PromoCode(PromoCodeType, PromoCode, PromoAmount, StartDate, EndDate)
VALUES('PercentOff', 'SPRINGSALE2018', .10, '2018/3/20', '2018/5/31'),
('DollarsOff', 'SPRINGSALE2017', 20, '2017/3/20', '2017/5/31');
    
/*Reservation*/
INSERT INTO Reservation(CustomerID, StartDate, EndDate, PromoCodeID)
VALUES (1, '2017/2/14', '2017/2/18', NULL),
		(2, '2018/1/1', '2018/1/7', NULL),
        (3, '2018/10/2', '2018/10/5', 2),
        (3, '2018/3/21', '2018/3/27', 1);
        
        
/*GuestReservation*/
INSERT INTO GuestReservation(ReservationID, GuestID)
VALUES (1, 1),
		(2, 2);
        
/*RoomReservation*/
INSERT INTO RoomReservation(RoomNum, ReservationID)
VALUES (101,1),
		(102, 2),
        (201, 3),
        (301, 4),
        (302, 4);
        
/*Tax*/
INSERT INTO Tax(TaxAmount, StartDate, EndDate)
VALUES (.07, '2015/02/01', '2017/12/30'),
	(.08, '2018/02/01', NULL);
        
/*Bill*/
INSERT INTO Bill(BillAmount, TaxAmount, TotalBill, TaxID)
VALUES(357, 50, 407, 1),
	(425, 25, 450, 2),
    (700, 75, 775, 2),
    (500, 75, 75, 2);
        
        
/*BillReservation*/
INSERT INTO BillReservation(BillID, ReservationID)
VALUES(1,1),
(2,2),
(3,3),
(4,4);

/*AddOn*/
INSERT INTO AddOn(AddOnName)
VALUES ('Internet'),
('Room Service'),
('Movie');

/*BillAddOn*/
INSERT INTO BillAddOn(BillID, AddOnID)
VALUES(1, 2),
(2, 2),
(2,1);

/*AddOnRate*/
INSERT INTO AddOnRate(StartDate, EndDate, Rate, AddOnID)
VALUES('2017/01/01', '2017/12/30', 10.99, 1),
('2018/01/01', NULL, 15.99, 1),
('2018/01/01', NULL, 9.99, 2);
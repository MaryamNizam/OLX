create database OLX
go
use OLX
go

SET ANSI_WARNINGS OFF

CREATE TABLE Locale
(
	LocaleID INT PRIMARY KEY IDENTITY(1, 1),
	residenceBlock VARCHAR(5) NOT NULL,
	society VARCHAR(40) NOT NULL,
	city VARCHAR(20) NOT NULL,
	residenceState VARCHAR(20) NOT NULL
)

CREATE TABLE Users
(
	UserID INT PRIMARY KEY IDENTITY(1, 1),
	fName VARCHAR(40) NOT NULL, 
	lName VARCHAR(40) NOT NULL, 
	email VARCHAR(60) NOT NULL UNIQUE,
	userPassword VARCHAR(30) NOT NULL,
	joinDate date NOT NULL,
	phone VARCHAR(20),
	userLocation INT FOREIGN KEY REFERENCES Locale(LocaleID) ON DELETE NO ACTION ON UPDATE NO ACTION,
)

CREATE TABLE Advertisement
(
	advertisementID INT PRIMARY KEY IDENTITY(1, 1),
	adPosterID INT FOREIGN KEY REFERENCES Users(UserID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	title VARCHAR(250) NOT NULL,
	price INT NOT NULL,
	adDescription VARCHAR(250) NOT NULL,
	approvalStatus VARCHAR(250),
	category VARCHAR(250) NOT NULL
)

CREATE TABLE Jobs
(
	JobID INT PRIMARY KEY IDENTITY(1, 1),
	AdvertisementID INT FOREIGN KEY REFERENCES Advertisement(advertisementID) ON DELETE SET NULL ON UPDATE NO ACTION,
	companyName VARCHAR(70) NOT NULL,
	jobDescription VARCHAR(250) NOT NULL
)

CREATE TABLE Pets
(
	PetID INT PRIMARY KEY IDENTITY(1, 1),
	AdvertisementID INT FOREIGN KEY REFERENCES Advertisement(advertisementID) ON DELETE SET NULL ON UPDATE NO ACTION,
	breed VARCHAR(40) NOT NULL
)

CREATE TABLE Electronics
(
	ElectronicID INT PRIMARY KEY IDENTITY(1, 1),
	AdvertisementID INT FOREIGN KEY REFERENCES Advertisement(advertisementID) ON DELETE SET NULL ON UPDATE NO ACTION,
	condition VARCHAR(50),
	make VARCHAR(30) NOT NULL
)

CREATE TABLE Property
(
	PropertyID INT PRIMARY KEY IDENTITY(1, 1),
	AdvertisementID INT FOREIGN KEY REFERENCES Advertisement(advertisementID) ON DELETE SET NULL ON UPDATE NO ACTION,
	category VARCHAR(240)
)

CREATE TABLE House
(
	HouseID INT PRIMARY KEY IDENTITY(1, 1),
	PropertyID INT FOREIGN KEY REFERENCES Property(PropertyID) ON DELETE SET NULL ON UPDATE NO ACTION,
	noOfBedrooms INT NOT NULL,
	noOfBathrooms INT NOT NULL
)

CREATE TABLE Mobile
(
	MobileID INT PRIMARY KEY IDENTITY(1, 1),
	AdvertisementID INT FOREIGN KEY REFERENCES Advertisement(advertisementID) ON DELETE SET NULL ON UPDATE NO ACTION,
	condition VARCHAR(50),
	make VARCHAR(30) NOT NULL
)

CREATE TABLE Vehicle
(
	registrationNo INT PRIMARY KEY IDENTITY(1, 1),
	AdvertisementID INT FOREIGN KEY REFERENCES Advertisement(advertisementID) ON DELETE SET NULL ON UPDATE NO ACTION,
	registrationYear INT NOT NULL,
	make VARCHAR(30) NOT NULL,
	condition VARCHAR(30) NOT NULL,
	kmDriven INT NOT NULL,
	fuelEfficiency FLOAT NOT NULL
)

CREATE TABLE Followings
(
	FollowerID INT FOREIGN KEY REFERENCES Users(UserID) ON DELETE CASCADE ON UPDATE NO ACTION,
	FolloweeID INT FOREIGN KEY REFERENCES Users(UserID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	PRIMARY KEY(FollowerID, FolloweeID)
)

CREATE TABLE Likes
(
	UserID INT FOREIGN KEY REFERENCES Users(UserID) ON DELETE CASCADE ON UPDATE NO ACTION,
	AdvertisementID INT FOREIGN KEY REFERENCES Advertisement(advertisementID) ON DELETE CASCADE ON UPDATE NO ACTION,
	PRIMARY KEY(UserID, AdvertisementID)
)

Select * from Advertisement a join Pets j on a.advertisementID=j.AdvertisementID

select * from Users
select * from Advertisement
select * from Jobs
select * from Pets
select * from Electronics
select * from Property
select * from House
select * from Mobile
select * from Vehicle
select * from Locale
select * from Advertisement
select * from Followings
select * from Likes


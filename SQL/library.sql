-- MySQL Workbench 2022
-- @version 1.0.0
-- @date 02/04/2022
-- @authors Tufegdzic, F., Kurfurst, D., Novosel, N., Suric, G.
-- Pupil Library Database
-- Schema `library`
DROP DATABASE IF EXISTS library;
CREATE DATABASE library;
USE library;
-- Table `user`
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserID` int(10) NOT NULL DEFAULT '0',
  `Username` varchar(50) NOT NULL DEFAULT '',
  `UserPassword` varchar(20) NOT NULL DEFAULT '',
  `UserEmail` varchar(100) NOT NULL DEFAULT '',
  `UserRole` char(3) NOT NULL DEFAULT '',
  PRIMARY KEY (`UserID`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
LOCK TABLES `user` WRITE;
INSERT INTO `user`
VALUES (
    5320,
    'fatimaziv',
    'ft5320',
    'fatima.zivkovic@gmail.com',
    'STU'
  ),
  (
    7716,
    'ninaknows',
    'nn7716',
    'nina.novosel@gmail.com',
    'STU'
  ),
  (
    1062,
    'bxmcada',
    'databasesrock',
    'branko.mihaljevic@gmail.com',
    'EDU'
  ),
  (
    4368,
    'domagojkur',
    'kurfurstdabest',
    'dkurfurst@yahoo.com',
    'STU'
  ),
  (
    2003,
    'mrkul',
    'dobrodosli2022',
    'martina.kulic@edu.hr',
    'SPE'
  ),
  (
    1157,
    'tolicd',
    'poppi1995',
    'darko.tolic@gmail.com',
    'EDU'
  );
UNLOCK TABLES;
-- Table `student`
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `UserID` int(10) NOT NULL DEFAULT '0',
  `FirstName` varchar(50) NOT NULL DEFAULT '',
  `LastName` varchar(50) NOT NULL DEFAULT '',
  `UserPassword` varchar(20) NOT NULL DEFAULT '',
  `UserEmail` varchar(100) NOT NULL DEFAULT '',
  `SchoolID` int(10) NOT NULL DEFAULT '0',
  `Grade` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserID`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`SchoolID`) REFERENCES `school` (`SchoolID`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
LOCK TABLES `student` WRITE;
INSERT INTO `student`
VALUES (
    5320,
    'Fatima',
    'Zivkovic',
    'unbreakable123',
    'fatima.zivkovic@gmail.com',
    16849,
    10
  ),
  (
    7716,
    'Nina',
    'Novosel',
    'ninaknows',
    'nina.novosel@gmail.com',
    14668,
    8
  ),
  (
    4368,
    'Domagoj',
    'Kurfurst',
    'kurfurstdabest',
    'dkurfurst@yahoo.com',
    13875,
    9
  );
UNLOCK TABLES;
-- Table `educator`
DROP TABLE IF EXISTS `educator`;
CREATE TABLE `educator` (
  `UserID` int(10) NOT NULL DEFAULT '0',
  `FirstName` varchar(50) NOT NULL DEFAULT '',
  `LastName` varchar(50) NOT NULL DEFAULT '',
  `UserPassword` varchar(20) NOT NULL DEFAULT '',
  `UserEmail` varchar(100) NOT NULL DEFAULT '',
  `SchoolID` int(10) NOT NULL DEFAULT '0',
  `CourseID` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserID`),
  CONSTRAINT `educator_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `educator_ibfk_2` FOREIGN KEY (`SchoolID`) REFERENCES `school` (`SchoolID`),
  CONSTRAINT `educator_ibfk_3` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
LOCK TABLES `educator` WRITE;
INSERT INTO `educator`
VALUES (
    1062,
    'Branko',
    'Mihaljevic',
    'databasesrock',
    'branko.mihaljevic@gmail.com',
    16849,
    1050
  ),
  (
    1157,
    'Darko',
    'Tolic',
    'poppi1995',
    'darko.tolic@gmail.com',
    14668,
    1088
  );
UNLOCK TABLES;
-- Table `education_specialist`
DROP TABLE IF EXISTS `education_specialist`;
CREATE TABLE `education_specialist` (
  `UserID` int(10) NOT NULL DEFAULT '0',
  `FirstName` varchar(50) NOT NULL DEFAULT '',
  `LastName` varchar(50) NOT NULL DEFAULT '',
  `UserPassword` varchar(20) NOT NULL DEFAULT '',
  `UserEmail` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`UserID`),
  CONSTRAINT `educationspecialist_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
LOCK TABLES `education_specialist` WRITE;
INSERT INTO `education_specialist`
VALUES (
    2003,
    'Martina',
    'Kulic',
    'dobrodosli2022',
    'martina.kulic@edu.hr'
  );
UNLOCK TABLES;
-- Table `school`
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `SchoolID` int(10) NOT NULL DEFAULT '0',
  `SchoolName` varchar(100) NOT NULL DEFAULT '',
  `Address` varchar(50) NOT NULL DEFAULT '',
  `Phone` varchar(10) NOT NULL DEFAULT '',
  `Email` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`SchoolID`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
LOCK TABLES `school` WRITE;
INSERT INTO `school`
VALUES(
    13875,
    'Privatna gimnazija i ekonomsko-informatička škola Futura',
    'Ul. Divka Budaka 1D, 10000, Zagreb',
    '014828571',
    'info@skola-futura.hr'
  ),
  (
    14668,
    'Osnovna škola Središće',
    'Ul. Savezne Republike Njemačke 2, 10000, Zagreb',
    '015999550',
    'os-sredisce-zg.skole.hr'
  ),
  (
    16849,
    'XV. Gimnazija',
    'Jordanovac ul. 8, 10000, Zagreb',
    '012302255',
    'xvg@mioc.hr'
  );
UNLOCK TABLES;
-- Table `course`
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `CourseID` int(10) NOT NULL DEFAULT '0',
  `CourseName` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`CourseID`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
LOCK TABLES `course` WRITE;
INSERT INTO `course`
VALUES(1006, 'Croatian A'),
  (1050, 'Mathematics'),
  (1054, 'English B'),
  (1088, 'Physics');
UNLOCK TABLES;
-- Table `book`
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `BookID` int(10) NOT NULL DEFAULT '0',
  `Title` varchar(100) NOT NULL DEFAULT '',
  `AuthorID` int(10) NOT NULL DEFAULT '0',
  `Language` varchar(50) NOT NULL DEFAULT '',
  `PublisherID` int(10) NOT NULL DEFAULT '0',
  `PublishDate` date NOT NULL DEFAULT '0000-00-00',
  `GenreID` int(8) NOT NULL DEFAULT '',
  PRIMARY KEY (`BookID`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`AuthorID`) REFERENCES `author` (`AuthorID`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`PublisherID`) REFERENCES `publisher` (`PublisherID`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
INSERT INTO `book`
VALUES(
    11625,
    'MOJ SRETNI BROJ 1 udžbenik matematike',
    221,
    'Croatian',
    6884,
    '2019-10-08',
    003
  ),
  (
    12587,
    'DIP IN 7 - radna bilježnica za engleski jezik u sedmom razredu osnovne škole, sedma godina učenja',
    532,
    'Croatian',
    2145,
    '2020-05-22',
    004
  ),
  (
    16983,
    'Mali Princ',
    801,
    'English',
    6884,
    '1984-05-12',
    005
  );
UNLOCK TABLES;
-- Table `author`
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `AuthorID` int(10) NOT NULL DEFAULT '0',
  `FirstName` varchar(100) NOT NULL DEFAULT '',
  `LastName` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`AuthorID`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
INSERT INTO `author`
VALUES(221, 'Sanja', 'Jakovljević Rogić'),
  (475, 'Danijel', 'Orešić'),
  (532, 'Orešić', 'Anić'),
  (801, 'Antoine', 'de Saint-Exupéry');
UNLOCK TABLES;
-- Table `publisher`
DROP TABLE IF EXISTS `publisher`;
CREATE TABLE `publisher` (
  `PublisherID` int(10) NOT NULL DEFAULT '0',
  `PublisherName` varchar(100) NOT NULL DEFAULT '',
  `Address` varchar(100) NOT NULL DEFAULT '',
  `Phone` varchar(10) NOT NULL DEFAULT '',
  `Email` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`PublisherID`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
INSERT INTO `publisher`
VALUES(
    2145,
    'Alfa D.D.',
    'Nova Ves 23a, Zagreb',
    '014666066',
    'info@skolski-udzbenici.hr'
  ),
  (
    6884,
    'Skolska Knjiga',
    'Masarykova ul. 28, 10000, Zagreb',
    '014830511',
    'info@skolskaknjiga.hr'
  ),
  (
    8957,
    'Mozaik Knjiga Publishing',
    'Karlovačka cesta 24a, 10020, Zagreb',
    '016315111',
    'info@mozaik-knjiga.hr'
  );
UNLOCK TABLES;
-- Table `genre`
DROP TABLE IF EXISTS `genre`;
CREATE TABLE `genre` (
  `GenreID` int(8) NOT NULL DEFAULT '0',
  `GenreName` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`AuthorID`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
INSERT INTO `genre`
VALUES(002, 'Fiction'),
  (003, 'Physics'),
  (004, 'Literature'),
  (005, 'Non-Fiction');
UNLOCK TABLES;
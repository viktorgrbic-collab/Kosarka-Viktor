/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.32-MariaDB : Database - kosarka
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kosarka` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `kosarka`;

/*Table structure for table `drzava` */

DROP TABLE IF EXISTS `drzava`;

CREATE TABLE `drzava` (
  `DrzavaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`DrzavaID`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `drzava` */

INSERT  INTO `drzava`(`DrzavaID`,`Naziv`) VALUES 
(1,'Slovenija'),
(2,'Italija'),
(3,'Grčka');

/*Table structure for table `igrac` */

DROP TABLE IF EXISTS `igrac`;

CREATE TABLE `igrac` (
  `IgracID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Visina` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`IgracID`)
) ENGINE=INNODB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `igrac` */

INSERT  INTO `igrac`(`IgracID`,`Ime`,`Prezime`,`Visina`) VALUES 
(1,'Nikola','Jokić',211.00),
(2,'Jamal','Murray',193.00),
(3,'Michael','Porter',208.00),
(4,'Aaron','Gordon',203.00),
(5,'Kentavious','Caldwell-Pope',196.00),
(6,'Giannis','Antetokounmpo',211.00),
(7,'Damian','Lillard',188.00),
(8,'Khris','Middleton',201.00),
(9,'Brook','Lopez',213.00),
(10,'Luka','Dončić',201.00),
(11,'Kyrie','Irving',188.00),
(12,'Maxi','Kleber',208.00),
(13,'Vasilije','Micić',196.00),
(14,'Nikola','Milutinov',213.00),
(15,'Bogdan','Bogdanović',198.00),
(16,'Marko','Guduric',196.00),
(17,'Sergio','Rodriguez',191.00),
(18,'Luigi','Datome',203.00),
(19,'Thomas','Walkup',193.00),
(20,'Kostas','Antetokounmpo',210.00);

/*Table structure for table `kvalifikacija` */

DROP TABLE IF EXISTS `kvalifikacija`;

CREATE TABLE `kvalifikacija` (
  `KvalifikacijaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`KvalifikacijaID`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kvalifikacija` */

INSERT  INTO `kvalifikacija`(`KvalifikacijaID`,`Naziv`) VALUES 
(1,'Video analiza'),
(2,'Skauting'),
(3,'Sportska statistika');

/*Table structure for table `liga` */

DROP TABLE IF EXISTS `liga`;

CREATE TABLE `liga` (
  `LigaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  `DrzavaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`LigaID`),
  KEY `fk_drzava_id` (`DrzavaID`),
  CONSTRAINT `fk_drzava_id` FOREIGN KEY (`DrzavaID`) REFERENCES `drzava` (`DrzavaID`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `liga` */

INSERT  INTO `liga`(`LigaID`,`Naziv`,`DrzavaID`) VALUES 
(1,'Liga A',1),
(2,'Serie A',2),
(3,'Greek A1',3);

/*Table structure for table `statisticar` */

DROP TABLE IF EXISTS `statisticar`;

CREATE TABLE `statisticar` (
  `StatisticarID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Prezime` VARCHAR(50) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`StatisticarID`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `statisticar` */

INSERT  INTO `statisticar`(`StatisticarID`,`Ime`,`Prezime`,`Username`,`Password`) VALUES 
(1,'Viktor','Grbic','vik','vik'),
(2,'Petar','Ilić','petar','petar');

/*Table structure for table `statisticarkvalifikacija` */

DROP TABLE IF EXISTS `statisticarkvalifikacija`;

CREATE TABLE `statisticarkvalifikacija` (
  `KvalifikacijaID` BIGINT(10) UNSIGNED NOT NULL,
  `StatisticarID` BIGINT(10) UNSIGNED NOT NULL,
  `Opis` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`KvalifikacijaID`,`StatisticarID`),
  KEY `fk_st_id2` (`StatisticarID`),
  CONSTRAINT `fk_kv_id` FOREIGN KEY (`KvalifikacijaID`) REFERENCES `kvalifikacija` (`KvalifikacijaID`),
  CONSTRAINT `fk_st_id2` FOREIGN KEY (`StatisticarID`) REFERENCES `statisticar` (`StatisticarID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `statisticarkvalifikacija` */

INSERT  INTO `statisticarkvalifikacija`(`KvalifikacijaID`,`StatisticarID`,`Opis`) VALUES 
(1,1,'Napredna video analiza utakmica.'),
(2,2,'Skauting protivnika i sklonosti igrača.');

/*Table structure for table `stavkatima` */

DROP TABLE IF EXISTS `stavkatima`;

CREATE TABLE `stavkatima` (
  `TimID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `BrojNaDresu` INT(7) NOT NULL,
  `Pozicija` VARCHAR(50) NOT NULL,
  `IgracID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`TimID`,`Rb`),
  KEY `fk_ig_id` (`IgracID`),
  CONSTRAINT `fk_ig_id` FOREIGN KEY (`IgracID`) REFERENCES `igrac` (`IgracID`),
  CONSTRAINT `fk_tim_id` FOREIGN KEY (`TimID`) REFERENCES `tim` (`TimID`) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkatima` */

INSERT  INTO `stavkatima`(`TimID`,`Rb`,`BrojNaDresu`,`Pozicija`,`IgracID`) VALUES 
(1,1,7,'Plejmejker',13),
(1,2,10,'Bek šuter',15),
(1,3,20,'Krilo',3),
(1,4,23,'Krilni centar',4),
(1,5,41,'Centar',14),
(2,1,9,'Plejmejker',17),
(2,2,70,'Bek šuter',2),
(2,3,77,'Krilo',18),
(2,4,11,'Krilni centar',12),
(2,5,55,'Centar',9),
(3,1,0,'Plejmejker',19),
(3,2,34,'Bek šuter',7),
(3,3,43,'Krilo',8),
(3,4,13,'Krilni centar',6),
(3,5,21,'Centar',20),
(4,1,77,'Plejmejker',11),
(4,2,14,'Bek šuter',5),
(4,3,31,'Krilo',10),
(4,4,32,'Krilni centar',18),
(4,5,50,'Centar',1),
(5,1,3,'Plejmejker',2),
(5,2,22,'Bek šuter',16),
(5,3,17,'Krilo',3),
(5,4,44,'Krilni centar',4),
(5,5,15,'Centar',12),
(6,1,1,'Plejmejker',1),
(6,2,2,'Plejmejker',2),
(6,3,3,'Plejmejker',6);

/*Table structure for table `tim` */

DROP TABLE IF EXISTS `tim`;

CREATE TABLE `tim` (
  `TimID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  `BrojIgraca` INT(7) NOT NULL,
  `BrojUtakmica` INT(7) NOT NULL,
  `BrojPobeda` INT(7) NOT NULL,
  `LigaID` BIGINT(10) UNSIGNED NOT NULL,
  `StatisticarID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`TimID`),
  KEY `fk_liga_id` (`LigaID`),
  KEY `fk_stat_id` (`StatisticarID`),
  CONSTRAINT `fk_liga_id` FOREIGN KEY (`LigaID`) REFERENCES `liga` (`LigaID`),
  CONSTRAINT `fk_stat_id` FOREIGN KEY (`StatisticarID`) REFERENCES `statisticar` (`StatisticarID`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tim` */

INSERT  INTO `tim`(`TimID`,`Naziv`,`BrojIgraca`,`BrojUtakmica`,`BrojPobeda`,`LigaID`,`StatisticarID`) VALUES 
(1,'Cedevita Olimpija',12,22,18,1,1),
(2,'Virtus Bologna',14,24,20,2,2),
(3,'Panathinaikos',14,26,22,3,1),
(4,'Olimpia Milano',14,26,21,2,2),
(5,'PAOK',13,24,15,3,1),
(6,'Tim 1',3,2,1,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

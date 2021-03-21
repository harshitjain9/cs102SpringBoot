CREATE DATABASE IF NOT EXISTS `psa`;
USE `psa`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
    `aid`  int NOT NULL,
    `email`   VARCHAR(255)  NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `dateOfBirth` DateTime NOT NULL,
    PRIMARY KEY(`email`),
    UNIQUE  (`username`)
);


DROP TABLE IF EXISTS `vessel`;
CREATE TABLE IF NOT EXISTS `vessel` (
  `imon` int(11) NOT NULL,
  `fullVslM` varchar(255) NOT NULL,
  `abbrVslM` varchar(255) NOT NULL,
  `inVoyN` varchar(255) NOT NULL,
  `outVoyN` varchar(16) DEFAULT NULL,
  `full_in_voyn` varchar(12) DEFAULT NULL,
  `btr_dt` varchar(20) DEFAULT NULL,
  `unbthg_dt` varchar(20) DEFAULT NULL,
  `berthn` varchar(6) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`abbrVslM`,`inVoyN`),
  UNIQUE (`abbrVslM`,`outVoyN`)
);

DROP TABLE IF EXISTS `alert_table`;
CREATE TABLE IF NOT EXISTS `alert` (
  `abbr_vslm` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `in_voyn` varchar(255) NOT NULL,
  PRIMARY KEY (`abbr_vslm`,`email`,`in_voyn`)
);

DROP TABLE IF EXISTS `registration_table`;
CREATE TABLE IF NOT EXISTS `registration` (
  `emailSuffix` varchar(255) NOT NULL,
  PRIMARY KEY (`emailSuffix`)
);

DROP TABLE IF EXISTS `predicted_btr`;
CREATE TABLE IF NOT EXISTS `predicted_btr` (
	  `AVG_SPEED` int,
    `DISTANCE_TO_GO` int,
    `IS_PATCHING_ACTIVATED` int,
    `MAX_SPEED` int,
    `PATCHING_PREDICTED_BTR` varchar(255),
    `PREDICTED_BTR` varchar(255),
    `VESSEL_NAME` varchar(255),
    `VOYAGE_CODE_INBOUND` varchar(255),
    `VSL_VOY` varchar(255),
    PRIMARY KEY (`VOYAGE_CODE_INBOUND`)
);


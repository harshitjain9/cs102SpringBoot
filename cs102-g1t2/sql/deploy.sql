CREATE DATABASE IF NOT EXISTS `psa`;
USE `psa`;

DROP TABLE IF EXISTS `ACCOUNT_TBL`;
CREATE TABLE IF NOT EXISTS `ACCOUNT_TBL` (
    `email` VARCHAR(255)  NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `dateOfBirth` DateTime NOT NULL,
    PRIMARY KEY(`email`),
    UNIQUE  (`username`)
);


DROP TABLE IF EXISTS `VESSEL_TBL`;
CREATE TABLE IF NOT EXISTS `VESSEL_TBL` (
	#`imon` int(11) NOT NULL,
	`fullVslM` varchar(255) NOT NULL,
	`abbrVslM` varchar(255) NOT NULL,
	`inVoyN` varchar(255) NOT NULL,
	`outVoyN` varchar(16) DEFAULT NULL,
	`fullInVoyN` varchar(12) DEFAULT NULL,
	`bthgDt` varchar(20) DEFAULT NULL,
	`unbthgDt` varchar(20) DEFAULT NULL,
	`berthN` varchar(6) DEFAULT NULL,
	`status` varchar(10) DEFAULT NULL,
	`AVG_SPEED` int,
    `DISTANCE_TO_GO` int,
    `IS_PATCHING_ACTIVATED` int,
    `MAX_SPEED` int,
    `PATCHING_PREDICTED_BTR` varchar(255),
    `PREDICTED_BTR` varchar(255),
    #`VESSEL_NAME` varchar(255),
    #`VOYAGE_CODE_INBOUND` varchar(255),
    `VSL_VOY` varchar(255),

  PRIMARY KEY (`abbrVslM`,`inVoyN`),
  UNIQUE (`abbrVslM`,`outVoyN`)
);

DROP TABLE IF EXISTS `alert_table`;
CREATE TABLE IF NOT EXISTS `alert_table` (
  `abbr_vslm` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `in_voyn` varchar(255) NOT NULL,
  PRIMARY KEY (`abbr_vslm`,`email`,`in_voyn`)
);

DROP TABLE IF EXISTS `registration_table`;
CREATE TABLE IF NOT EXISTS `registration_table` (
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

DROP TABLE IF EXISTS `email_server`;
CREATE TABLE IF NOT EXISTS `email_server` (
	`ID` VARCHAR(255)  NOT NULL,
	`server` VARCHAR(255)  NOT NULL,
	`senderEmail`   VARCHAR(255)  NOT NULL
);

DROP TABLE IF EXISTS `WebService_table`;
CREATE TABLE IF NOT EXISTS `WebService_table` (
	`id` int NOT NULL,
    `apiKey` VARCHAR(255)  NOT NULL,
    `dailyUpdate` VARCHAR(255)  NOT NULL,
    `currentDayUpdate` int NOT NULL
);



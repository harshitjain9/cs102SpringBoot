-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 15, 2020 at 12:02 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appdb`
--
CREATE DATABASE IF NOT EXISTS `appdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `appdb`;

-- --------------------------------------------------------

--
-- Table structure for table `account_tbl`
--

DROP TABLE IF EXISTS `account_tbl`;
CREATE TABLE IF NOT EXISTS `account_tbl` (
  `email` varchar(255) NOT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `account_tbl`
--

INSERT INTO `account_tbl` (`email`, `date_of_birth`, `password`) VALUES
('haha@smu.sg', '2012-09-15', '0dd3e512642c97ca3f747f9a76e374fbda73f9292823c0313be9d78add7cdd8f72235af0c553dd26797e78e1854edee0ae002f8aba074b066dfce1af114e32f8'),
('hello@psa.com', '2016-01-01', '0dd3e512642c97ca3f747f9a76e374fbda73f9292823c0313be9d78add7cdd8f72235af0c553dd26797e78e1854edee0ae002f8aba074b066dfce1af114e32f8'),
('test@smu.sg', '2020-11-15', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2'),('admin@admin.com', '1996-11-11', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec');

-- --------------------------------------------------------

--
-- Table structure for table `favorite_tbl`
--

DROP TABLE IF EXISTS `favorite_tbl`;
CREATE TABLE IF NOT EXISTS `favorite_tbl` (
  `abbr_vslm` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `in_voyn` varchar(255) NOT NULL,
  PRIMARY KEY (`abbr_vslm`,`email`,`in_voyn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `register_tbl`
--

DROP TABLE IF EXISTS `register_tbl`;
CREATE TABLE IF NOT EXISTS `register_tbl` (
  `email_suffix` varchar(255) NOT NULL,
  PRIMARY KEY (`email_suffix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `register_tbl`
--

INSERT INTO `register_tbl` (`email_suffix`) VALUES
('@smu.sg'),
('@psa.com');


-- --------------------------------------------------------

--
-- Table structure for table `subscription_tbl`
--

DROP TABLE IF EXISTS `subscription_tbl`;
CREATE TABLE IF NOT EXISTS `subscription_tbl` (
  `abbr_vslm` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `in_voyn` varchar(255) NOT NULL,
  PRIMARY KEY (`abbr_vslm`,`email`,`in_voyn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `vessel_tbl`
--

DROP TABLE IF EXISTS `vessel_tbl`;
CREATE TABLE IF NOT EXISTS `vessel_tbl` (
  `abbr_vslm` varchar(255) NOT NULL,
  `in_voyn` varchar(255) NOT NULL,
  `abbr_terminalm` varchar(6) DEFAULT NULL,
  `berthn` varchar(6) DEFAULT NULL,
  `bthg_dt` varchar(20) DEFAULT NULL,
  `full_in_voyn` varchar(12) DEFAULT NULL,
  `full_out_voyn` varchar(12) DEFAULT NULL,
  `full_vslm` varchar(255) DEFAULT NULL,
  `imon` int(11) NOT NULL,
  `out_voyn` varchar(16) DEFAULT NULL,
  `shift_seqn` varchar(6) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `unbthg_dt` varchar(20) DEFAULT NULL,
  `change_count` int(11) NOT NULL,
  `first_bthg_dt` varchar(20) DEFAULT NULL,
  `display_color` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`abbr_vslm`,`in_voyn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `webserviceinstruction_tbl`
--

DROP TABLE IF EXISTS `webserviceinstruction_tbl`;
CREATE TABLE IF NOT EXISTS `webserviceinstruction_tbl` (
  `id` int(11) NOT NULL,
  `api_key` varchar(255) DEFAULT NULL,
  `current_day_update` int(11) NOT NULL,
  `daily_update` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `webserviceinstruction_tbl`
--

INSERT INTO `webserviceinstruction_tbl` (`id`, `api_key`, `current_day_update`, `daily_update`) VALUES
(1, '8f765e3bf8534243bceeb5341a78f5f2', 3600000, '0 0 0 * * *');


DROP TABLE IF EXISTS `emailserver_tbl`;
CREATE TABLE IF NOT EXISTS `emailserver_tbl` (
  `id` int(11) NOT NULL,
  `server` varchar(255) DEFAULT NULL,
  `sender_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `emailserver_tbl` (`id`, `server`, `sender_email`) VALUES
(1, 'smtp.psa', 'psa@email.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
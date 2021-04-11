-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 11, 2021 at 02:51 PM
-- Server version: 5.7.26
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `psa`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE DATABASE IF NOT EXISTS psa;
USE psa;


-- --------------------------------------------------------

--
-- Table structure for table `ACCOUNT_TBL`
--
DROP TABLE IF EXISTS `account_tbl`;
CREATE TABLE `account_tbl` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `dateOfBirth` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ACCOUNT_TBL`
--

INSERT INTO `account_tbl` (`email`, `password`, `dateOfBirth`) VALUES
('admin@psa.com', 'YWJj', '01-02-2000'),
('test@test.com', 'YWJj', '01-02-2000');

-- --------------------------------------------------------

--
-- Table structure for table `alert_table`
--
DROP TABLE IF EXISTS `alert_table`;
CREATE TABLE `alert_table` (
  `email` varchar(255) NOT NULL,
  `full_vslm` varchar(255) NOT NULL,
  `in_voyn` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `alert_table`
--

INSERT INTO `alert_table` (`email`, `full_vslm`, `in_voyn`) VALUES
('test@test.com', 'ABHIMATA 1', 'B125A'),
('test@test.com', 'ABHIMATA 1', 'B125B'),
('test@test.com', 'ABHIMATA 1', 'B127A');

-- --------------------------------------------------------

--
-- Table structure for table `alert_triggered_table`
--
DROP TABLE IF EXISTS `alert_triggered_table`;
CREATE TABLE `alert_triggered_table` (
  `email` varchar(255) NOT NULL,
  `full_vslm` varchar(255) NOT NULL,
  `in_voyn` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `message` varchar(8000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `alert_triggered_table`
--

INSERT INTO `alert_triggered_table` (`email`, `full_vslm`, `in_voyn`, `time`, `message`) VALUES
('test@test.com', 'ABHIMATA 1', 'B125A', 'Mon Apr 05 03:26:23 UTC 2021', 'status:BERTHING->BERTHED'),
('test@test.com', 'ABHIMATA 1', 'B125B', 'Mon Apr 05 03:26:23 UTC 2021', 'status:BERTHING->BERTHED');

-- --------------------------------------------------------

--
-- Table structure for table `email_server`
--
DROP TABLE IF EXISTS `email_server`;
CREATE TABLE `email_server` (
  `id` varchar(255) NOT NULL,
  `sender_email` varchar(255) DEFAULT NULL,
  `server` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `email_server`
--

INSERT INTO `email_server` (`id`, `sender_email`, `server`) VALUES
('1', 'psac_corpcomms@globalpsa.com', 'smtp.psa');


-- --------------------------------------------------------

--
-- Table structure for table `registration_table`
--
DROP TABLE IF EXISTS `registration_table`;
CREATE TABLE `registration_table` (
  `email_suffix` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `registration_table`
--


-- --------------------------------------------------------

--
-- Table structure for table `vessel_tbl`
--
DROP TABLE IF EXISTS `vessel_tbl`;
CREATE TABLE `vessel_tbl` (
  `abbr_vslm` varchar(255) NOT NULL,
  `in_voyn` varchar(255) NOT NULL,
  `berthn` varchar(255) DEFAULT NULL,
  `bthg_dt` varchar(255) DEFAULT NULL,
  `full_in_voyn` varchar(255) DEFAULT NULL,
  `full_vslm` varchar(255) DEFAULT NULL,
  `out_voyn` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `unbthg_dt` varchar(255) DEFAULT NULL,
  `avg_speed` varchar(255) DEFAULT NULL,
  `distance_to_go` varchar(255) DEFAULT NULL,
  `is_patching_activated` varchar(255) DEFAULT NULL,
  `max_speed` varchar(255) DEFAULT NULL,
  `patching_predicted_btr` varchar(255) DEFAULT NULL,
  `predicted_btr` varchar(255) DEFAULT NULL,
  `vessel_name` varchar(255) DEFAULT NULL,
  `voyage_code_inbound` varchar(255) DEFAULT NULL,
  `vsl_voy` varchar(255) DEFAULT NULL,
  `shift_seqn` varchar(255) DEFAULT NULL,
  `current_port` varchar(255) DEFAULT NULL,
  `current_port_country` varchar(255) DEFAULT NULL,
  `last_port` varchar(255) DEFAULT NULL,
  `last_port_country` varchar(255) DEFAULT NULL,
  `next_port_country` varchar(255) DEFAULT NULL,
  `next_port_name` varchar(255) DEFAULT NULL,
  `current_avg_speed` varchar(255) DEFAULT NULL,
  `second_last_avg_speed` varchar(255) DEFAULT NULL,
  `third_last_avg_speed` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vessel_tbl`
--

INSERT INTO `vessel_tbl` (`abbr_vslm`, `in_voyn`, `berthn`, `bthg_dt`, `full_in_voyn`, `full_vslm`, `out_voyn`, `status`, `unbthg_dt`, `avg_speed`, `distance_to_go`, `is_patching_activated`, `max_speed`, `patching_predicted_btr`, `predicted_btr`, `vessel_name`, `voyage_code_inbound`, `vsl_voy`, `shift_seqn`, `current_port`, `current_port_country`, `last_port`, `last_port_country`, `next_port_country`, `next_port_name`, `current_avg_speed`, `second_last_avg_speed`, `third_last_avg_speed`) VALUES
('A EXPLORER', '080421', 'S03', '2021-04-10T08:45:00', 'AE080421', 'ASEAN EXPLORER', '080421', 'ALONGSIDE', '2021-04-12T12:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('A EXPLORER', 'TEST', 'S03', '2021-04-10T08:45:00', 'AE080421', 'ASEAN EXPLORER', '080421', 'ALONGSIDE', '2021-04-12T12:00:00',   NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ABHIMATA 1', 'B125A', 'K09', '2021-03-29T08:30:00', 'B125A', 'ABHIMATA 1', 'B125A', 'UNBERTHED', '2021-03-29T11:00:00',   NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ABHIMATA 1', 'B125B', 'K09', '2021-03-29T18:00:00', 'B125B', 'ABHIMATA 1', 'B125B', 'BERTHING', '2021-03-29T20:00:00',   NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ABHIMATA 1', 'B126A', 'K09', '2021-03-30T09:25:00', 'B126A', 'ABHIMATA 1', 'B126A', 'UNBERTHED', '2021-03-30T10:45:00',  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ABHIMATA 1', 'B126B', 'K09', '2021-03-30T17:20:00', 'B126B', 'ABHIMATA 1', 'B126B', 'UNBERTHED', '2021-03-30T18:05:00',  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ABHIMATA 1', 'B127A', 'K09', '2021-03-31T10:00:00', 'B127A', 'ABHIMATA 1', 'B127A', 'BERTHING', '2021-03-31T12:00:00',   NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ABHIMATA 1', 'B127B', 'K09', '2021-03-31T18:00:00', 'B127B', 'ABHIMATA 1', 'B127B', 'BERTHING', '2021-03-31T20:00:00',   NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ABHIMATA 1', 'B128A', 'K09', '2021-04-01T10:00:00', 'B128A', 'ABHIMATA 1', 'B128A', 'BERTHING', '2021-04-01T12:00:00',  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ABHIMATA 1', 'B128B', 'K09', '2021-04-01T18:00:00', 'B128B', 'ABHIMATA 1', 'B128B', 'BERTHING', '2021-04-01T20:00:00',   NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `web_service_table`
--
DROP TABLE IF EXISTS `web_service_table`;
CREATE TABLE `web_service_table` (
  `id` int(11) NOT NULL,
  `api_key` varchar(255) DEFAULT NULL,
  `current_day_update` int(11) NOT NULL,
  `daily_update` varchar(255) DEFAULT NULL,
  `first_api_server_name` varchar(255) DEFAULT NULL,
  `second_api_server_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `web_service_table`
--

INSERT INTO `web_service_table` (`id`, `api_key`, `current_day_update`, `daily_update`, `first_api_server_name`, `second_api_server_name`) VALUES
(1, 'OGY3NjVlM2JmODUzNDI0M2JjZWViNTM0MWE3OGY1ZjI=', 43200000, '0 0 12 * * *', 'https://api.portnet.com/vsspp/pp/bizfn/berthingSchedule/retrieveByBerthingDate/v1.2', 'https://api.portnet.com/extapi/vessels/predictedbtr');

--
-- Indexes for dumped tables
--

-- Indexes for table `ACCOUNT_TBL`
--
ALTER TABLE `account_tbl`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `alert_table`
--
ALTER TABLE `alert_table`
  ADD PRIMARY KEY (`email`,`full_vslm`,`in_voyn`);

--
-- Indexes for table `alert_triggered_table`
--
ALTER TABLE `alert_triggered_table`
  ADD PRIMARY KEY (`email`,`full_vslm`,`in_voyn`,`time`);

--
-- Indexes for table `email_server`
--
ALTER TABLE `email_server`
  ADD PRIMARY KEY (`id`);


-- Indexes for table `vessel_tbl`
--
ALTER TABLE `vessel_tbl`
  ADD PRIMARY KEY (`abbr_vslm`,`in_voyn`);

--
-- Indexes for table `web_service_table`
--
ALTER TABLE `web_service_table`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--

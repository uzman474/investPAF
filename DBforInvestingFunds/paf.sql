-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 06:57 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paf`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `investID` int(11) NOT NULL,
  `investName` varchar(20) NOT NULL,
  `researcherID` varchar(20) NOT NULL,
  `investPrice` double NOT NULL,
  `investDet` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`investID`, `investName`, `researcherID`, `investPrice`, `investDet`) VALUES
(234323243, 'dfsfgwefwe', 'dfwdfgd', 673563, 'wdasfasdfsdcvsdv'),
(874874874, 'hjvsdhvashvaf', '232ed', 3848, 'jhvdshdfvdvhdfbkdcb hbvhdf'),
(874874875, 'uzman', 'uzz', 65555, 'hsvcgv ashgc vahgsvchg'),
(874874876, 'uzman', 'uzz', 65555, 'hsvcgv ashgc vahgsvchg'),
(874874877, 'uzman', 'uzz', 65555, 'hsvcgv ashgc vahgsvchg'),
(874874878, 'azrem', '30ABC', 89000, 'vhvdjhsvadjsvdajsvdashvcjhvcja');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`investID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `investID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=874874879;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

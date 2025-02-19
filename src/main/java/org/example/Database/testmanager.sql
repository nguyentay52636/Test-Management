-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2025 at 09:57 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlytracnghiem`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
                           `awID` int(11) NOT NULL,
                           `qID` int(11) NOT NULL,
                           `awContent` text DEFAULT NULL,
                           `awPictures` text DEFAULT NULL,
                           `isRight` tinyint(4) DEFAULT NULL,
                           `awStatus` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `exams`
--

CREATE TABLE `exams` (
                         `testCode` varchar(20) NOT NULL,
                         `exOrder` varchar(1) NOT NULL,
                         `exCode` varchar(20) NOT NULL,
                         `ex_quesIDs` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
                        `logID` int(11) NOT NULL,
                        `logContent` text DEFAULT NULL,
                        `logUserID` int(11) NOT NULL,
                        `logExID` int(11) DEFAULT NULL,
                        `logDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
                             `qID` int(11) NOT NULL,
                             `qContent` text NOT NULL,
                             `qPictures` text DEFAULT NULL,
                             `qTopicID` int(11) NOT NULL,
                             `qLevel` varchar(10) DEFAULT NULL,
                             `qStatus` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
                          `rs_num` tinyint(1) NOT NULL,
                          `userID` int(11) NOT NULL,
                          `exCode` varchar(20) NOT NULL,
                          `rs_answers` longtext DEFAULT NULL,
                          `rs_mark` decimal(10,0) DEFAULT NULL,
                          `rs_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
                        `testID` int(11) NOT NULL,
                        `testCode` varchar(20) NOT NULL,
                        `testTitle` text NOT NULL,
                        `tpID` int(11) NOT NULL,
                        `testTime` int(11) DEFAULT NULL,
                        `num_easy` int(11) DEFAULT NULL,
                        `num_medium` int(11) DEFAULT NULL,
                        `num_diff` int(11) DEFAULT NULL,
                        `testLimit` tinyint(4) DEFAULT NULL,
                        `testDate` date DEFAULT NULL,
                        `testStatus` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `topics`
--

CREATE TABLE `topics` (
                          `tpID` int(11) NOT NULL,
                          `tpTitle` text NOT NULL,
                          `tpParent` int(11) DEFAULT NULL,
                          `tpStatus` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
                         `userID` int(11) NOT NULL,
                         `userName` varchar(40) NOT NULL,
                         `userEmail` varchar(20) NOT NULL,
                         `userPassword` varchar(40) NOT NULL,
                         `userFullName` varchar(40) DEFAULT NULL,
                         `isAdmin` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `userName`, `userEmail`, `userPassword`, `userFullName`, `isAdmin`) VALUES
                                                                                                       (1, 'admin', 'admin@gmail.com', '123456', 'Lê Văn A', 1),
                                                                                                       (2, 'tester1', 'tester1@gmail.com', '123456', 'tester', 0),
                                                                                                       (3, 'tester2', 'asd@gmail.com', '11111', 'á', 0),
                                                                                                       (4, 'wib', 'wib@gmail.com', '111111', 'dora', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
    ADD PRIMARY KEY (`awID`),
  ADD KEY `fk_answers_qID` (`qID`);

--
-- Indexes for table `exams`
--
ALTER TABLE `exams`
    ADD PRIMARY KEY (`testCode`,`exOrder`),
  ADD UNIQUE KEY `unique_exCode` (`exCode`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
    ADD PRIMARY KEY (`logID`),
  ADD KEY `fk_logs_userID` (`logUserID`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
    ADD PRIMARY KEY (`qID`),
  ADD KEY `fk_questions_tpID` (`qTopicID`);

--
-- Indexes for table `result`
--
ALTER TABLE `result`
    ADD PRIMARY KEY (`rs_num`,`userID`,`exCode`),
  ADD KEY `fk_result_userID` (`userID`),
  ADD KEY `fk_result_exCode` (`exCode`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
    ADD PRIMARY KEY (`testID`),
  ADD UNIQUE KEY `testCode` (`testCode`),
  ADD KEY `fk_test_tpID` (`tpID`);

--
-- Indexes for table `topics`
--
ALTER TABLE `topics`
    ADD PRIMARY KEY (`tpID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
    MODIFY `awID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
    MODIFY `logID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
    MODIFY `qID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
    MODIFY `testID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `topics`
--
ALTER TABLE `topics`
    MODIFY `tpID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
    ADD CONSTRAINT `fk_answers_qID` FOREIGN KEY (`qID`) REFERENCES `questions` (`qID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `exams`
--
ALTER TABLE `exams`
    ADD CONSTRAINT `fk_exams_testCode` FOREIGN KEY (`testCode`) REFERENCES `test` (`testCode`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `logs`
--
ALTER TABLE `logs`
    ADD CONSTRAINT `fk_logs_userID` FOREIGN KEY (`logUserID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
    ADD CONSTRAINT `fk_questions_tpID` FOREIGN KEY (`qTopicID`) REFERENCES `topics` (`tpID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `result`
--
ALTER TABLE `result`
    ADD CONSTRAINT `fk_result_exCode` FOREIGN KEY (`exCode`) REFERENCES `exams` (`exCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_result_userID` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `test`
--
ALTER TABLE `test`
    ADD CONSTRAINT `fk_test_tpID` FOREIGN KEY (`tpID`) REFERENCES `topics` (`tpID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
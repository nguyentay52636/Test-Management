-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th3 09, 2025 lúc 08:45 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `tracnghiem`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `answers`
--

CREATE TABLE `answers` (
  `awID` int(11) NOT NULL,
  `qID` int(11) NOT NULL COMMENT 'id câu hỏi',
  `awContent` text NOT NULL,
  `awPictures` text NOT NULL COMMENT 'url ảnh',
  `isRight` tinyint(4) NOT NULL COMMENT '1: đúng; 0: Sai',
  `awStatus` tinyint(4) NOT NULL COMMENT '1: active; 0: hidden'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `answers`
--

INSERT INTO `answers` (`awID`, `qID`, `awContent`, `awPictures`, `isRight`, `awStatus`) VALUES
(1, 1, '10', '', 0, 1),
(2, 1, '12', '', 1, 1),
(3, 1, '14', '', 0, 1),
(4, 2, '8', '', 1, 1),
(5, 2, '6', '', 0, 1),
(6, 2, '10', '', 0, 1),
(7, 3, '10', '', 0, 1),
(8, 3, '12', '', 1, 1),
(9, 3, '14', '', 0, 1),
(10, 4, '5', '', 1, 1),
(11, 4, '3', '', 0, 1),
(12, 4, '6', '', 0, 1),
(13, 5, '16', '', 0, 1),
(14, 5, '18', '', 1, 1),
(15, 5, '20', '', 0, 1),
(16, 6, '15', '', 1, 1),
(17, 6, '10', '', 0, 1),
(18, 6, '20', '', 0, 1),
(19, 7, '10', '', 0, 1),
(20, 7, '12', '', 1, 1),
(21, 7, '14', '', 0, 1),
(22, 8, '3', '', 1, 1),
(23, 8, '6', '', 0, 1),
(24, 8, '9', '', 0, 1),
(25, 9, '10', '', 0, 1),
(26, 9, '12', '', 1, 1),
(27, 9, '15', '', 0, 1),
(28, 10, '7', '', 1, 1),
(29, 10, '5', '', 0, 1),
(30, 10, '9', '', 0, 1),
(31, 11, 'x = 3', '', 1, 1),
(32, 11, 'x = 2', '', 0, 1),
(33, 11, 'x = 4', '', 0, 1),
(34, 12, '20', '', 1, 1),
(35, 12, '25', '', 0, 1),
(36, 12, '15', '', 0, 1),
(37, 13, 'y = 5', '', 1, 1),
(38, 13, 'y = 3', '', 0, 1),
(39, 13, 'y = 6', '', 0, 1),
(40, 14, '15', '', 1, 1),
(41, 14, '8', '', 0, 1),
(42, 14, '20', '', 0, 1),
(43, 15, '16', '', 1, 1),
(44, 15, '8', '', 0, 1),
(45, 15, '24', '', 0, 1),
(46, 16, 'x = 5', '', 1, 1),
(47, 16, 'x = 4', '', 0, 1),
(48, 16, 'x = 6', '', 0, 1),
(49, 17, '24', '', 1, 1),
(50, 17, '20', '', 0, 1),
(51, 17, '28', '', 0, 1),
(52, 18, '5/6', '', 1, 1),
(53, 18, '2/5', '', 0, 1),
(54, 18, '1/6', '', 0, 1),
(55, 19, 'x = 32', '', 1, 1),
(56, 19, 'x = 24', '', 0, 1),
(57, 19, 'x = 40', '', 0, 1),
(58, 20, '30', '', 1, 1),
(59, 20, '25', '', 0, 1),
(60, 20, '35', '', 0, 1),
(61, 21, '3x^2', '', 1, 1),
(62, 21, 'x^2', '', 0, 1),
(63, 21, '6x', '', 0, 1),
(64, 22, 'x = ±2', '', 1, 1),
(65, 22, 'x = 2', '', 0, 1),
(66, 22, 'x = 4', '', 0, 1),
(67, 23, 'x^2', '', 1, 1),
(68, 23, '2x', '', 0, 1),
(69, 23, 'x', '', 0, 1),
(70, 24, '1', '', 1, 1),
(71, 24, '0', '', 0, 1),
(72, 24, '∞', '', 0, 1),
(73, 25, '-2', '', 1, 1),
(74, 25, '2', '', 0, 1),
(75, 25, '0', '', 0, 1),
(76, 26, 'x = 1, -2.5', '', 1, 1),
(77, 26, 'x = 2, -1', '', 0, 1),
(78, 26, 'x = 0, 5', '', 0, 1),
(79, 27, '3', '', 1, 1),
(80, 27, '2', '', 0, 1),
(81, 27, '1', '', 0, 1),
(82, 28, '55', '', 1, 1),
(83, 28, '45', '', 0, 1),
(84, 28, '65', '', 0, 1),
(85, 29, '-1', '', 1, 1),
(86, 29, '1', '', 0, 1),
(87, 29, '0', '', 0, 1),
(88, 30, 'x = 8', '', 1, 1),
(89, 30, 'x = 6', '', 0, 1),
(90, 30, 'x = 4', '', 0, 1),
(91, 31, '.py', '', 1, 1),
(92, 31, '.java', '', 0, 1),
(93, 31, '.cpp', '', 0, 1),
(94, 32, '#', '', 1, 1),
(95, 32, '//', '', 0, 1),
(96, 32, '/*', '', 0, 1),
(97, 33, 'int', '', 1, 1),
(98, 33, 'var', '', 0, 1),
(99, 33, 'let', '', 0, 1),
(100, 34, 'Cascading Style Sheets', '', 1, 1),
(101, 34, 'Computer System Software', '', 0, 1),
(102, 34, 'Creative Style Syntax', '', 0, 1),
(103, 35, 'C', '', 1, 1),
(104, 35, 'Python', '', 0, 1),
(105, 35, 'Ruby', '', 0, 1),
(106, 36, '5', '', 1, 1),
(107, 36, '6', '', 0, 1),
(108, 36, '23', '', 0, 1),
(109, 37, '<p>', '', 1, 1),
(110, 37, '<div>', '', 0, 1),
(111, 37, '<span>', '', 0, 1),
(112, 38, '==', '', 1, 1),
(113, 38, '=', '', 0, 1),
(114, 38, '!=', '', 0, 1),
(115, 39, 'Save changes', '', 1, 1),
(116, 39, 'Delete files', '', 0, 1),
(117, 39, 'Push code', '', 0, 1),
(118, 40, 'Structured Query Language', '', 1, 1),
(119, 40, 'Simple Query Logic', '', 0, 1),
(120, 40, 'System Query Language', '', 0, 1),
(121, 41, 'for', '', 1, 1),
(122, 41, 'while', '', 0, 1),
(123, 41, 'do-while', '', 0, 1),
(124, 42, 'true', '', 1, 1),
(125, 42, 'false', '', 0, 1),
(126, 42, 'null', '', 0, 1),
(127, 43, 'int[] arr = new int[5]', '', 1, 1),
(128, 43, 'int arr = 5', '', 0, 1),
(129, 43, 'array arr = new array', '', 0, 1),
(130, 44, 'start()', '', 1, 1),
(131, 44, 'run()', '', 0, 1),
(132, 44, 'begin()', '', 0, 1),
(133, 45, 'O(n)', '', 1, 1),
(134, 45, 'O(log n)', '', 0, 1),
(135, 45, 'O(1)', '', 0, 1),
(136, 46, 'Object-Oriented Programming', '', 1, 1),
(137, 46, 'Operational Online Processing', '', 0, 1),
(138, 46, 'Optimized Output Programming', '', 0, 1),
(139, 47, 'SELECT *', '', 1, 1),
(140, 47, 'SELECT ALL', '', 0, 1),
(141, 47, 'GET *', '', 0, 1),
(142, 48, 'Unique identifier', '', 1, 1),
(143, 48, 'Foreign key', '', 0, 1),
(144, 48, 'Index', '', 0, 1),
(145, 49, 'Initialize object', '', 1, 1),
(146, 49, 'Destroy object', '', 0, 1),
(147, 49, 'Copy object', '', 0, 1),
(148, 50, 'Function calling itself', '', 1, 1),
(149, 50, 'Looping structure', '', 0, 1),
(150, 50, 'Conditional statement', '', 0, 1),
(151, 51, 'O(n log n)', '', 1, 1),
(152, 51, 'O(n)', '', 0, 1),
(153, 51, 'O(n^2)', '', 0, 1),
(154, 52, 'BST is sorted', '', 1, 1),
(155, 52, 'BT is sorted', '', 0, 1),
(156, 52, 'No difference', '', 0, 1),
(157, 53, 'Threads waiting forever', '', 1, 1),
(158, 53, 'Thread termination', '', 0, 1),
(159, 53, 'Thread priority issue', '', 0, 1),
(160, 54, 'Optimal subproblem solving', '', 1, 1),
(161, 54, 'Random problem solving', '', 0, 1),
(162, 54, 'Greedy approach', '', 0, 1),
(163, 55, 'Single instance class', '', 1, 1),
(164, 55, 'Multiple instances', '', 0, 1),
(165, 55, 'Static class', '', 0, 1),
(166, 56, '== checks reference', '', 1, 1),
(167, 56, '.equals() checks reference', '', 0, 1),
(168, 56, 'No difference', '', 0, 1),
(169, 57, 'Key-value storage', '', 1, 1),
(170, 57, 'Sequential list', '', 0, 1),
(171, 57, 'Tree structure', '', 0, 1),
(172, 58, 'Passing dependencies', '', 1, 1),
(173, 58, 'Creating objects', '', 0, 1),
(174, 58, 'Static initialization', '', 0, 1),
(175, 59, 'Define methods', '', 1, 1),
(176, 59, 'Store data', '', 0, 1),
(177, 59, 'Execute code', '', 0, 1),
(178, 60, 'Web service protocol', '', 1, 1),
(179, 60, 'Database query', '', 0, 1),
(180, 60, 'File format', '', 0, 1),
(181, 61, 'Brasília', '', 1, 1),
(182, 61, 'Rio de Janeiro', '', 0, 1),
(183, 61, 'São Paulo', '', 0, 1),
(184, 62, '7', '', 1, 1),
(185, 62, '6', '', 0, 1),
(186, 62, '8', '', 0, 1),
(187, 63, 'Pacific', '', 1, 1),
(188, 63, 'Atlantic', '', 0, 1),
(189, 63, 'Indian', '', 0, 1),
(190, 64, 'William Shakespeare', '', 1, 1),
(191, 64, 'Charles Dickens', '', 0, 1),
(192, 64, 'Jane Austen', '', 0, 1),
(193, 65, 'Blue', '', 1, 1),
(194, 65, 'Green', '', 0, 1),
(195, 65, 'Red', '', 0, 1),
(196, 66, '7', '', 1, 1),
(197, 66, '5', '', 0, 1),
(198, 66, '8', '', 0, 1),
(199, 67, 'Flour', '', 1, 1),
(200, 67, 'Sugar', '', 0, 1),
(201, 67, 'Salt', '', 0, 1),
(202, 68, 'Dog', '', 1, 1),
(203, 68, 'Cat', '', 0, 1),
(204, 68, 'Horse', '', 0, 1),
(205, 69, 'Yen', '', 1, 1),
(206, 69, 'Dollar', '', 0, 1),
(207, 69, 'Euro', '', 0, 1),
(208, 70, '12', '', 1, 1),
(209, 70, '10', '', 0, 1),
(210, 70, '14', '', 0, 1),
(211, 71, 'Isaac Newton', '', 1, 1),
(212, 71, 'Albert Einstein', '', 0, 1),
(213, 71, 'Galileo Galilei', '', 0, 1),
(214, 72, '1945', '', 1, 1),
(215, 72, '1939', '', 0, 1),
(216, 72, '1950', '', 0, 1),
(217, 73, 'Au', '', 1, 1),
(218, 73, 'Ag', '', 0, 1),
(219, 73, 'Fe', '', 0, 1),
(220, 74, 'George Washington', '', 1, 1),
(221, 74, 'Thomas Jefferson', '', 0, 1),
(222, 74, 'Abraham Lincoln', '', 0, 1),
(223, 75, 'Everest', '', 1, 1),
(224, 75, 'K2', '', 0, 1),
(225, 75, 'Kangchenjunga', '', 0, 1),
(226, 76, 'Nitrogen', '', 1, 1),
(227, 76, 'Oxygen', '', 0, 1),
(228, 76, 'Carbon Dioxide', '', 0, 1),
(229, 77, 'Antarctica', '', 1, 1),
(230, 77, 'Sahara', '', 0, 1),
(231, 77, 'Gobi', '', 0, 1),
(232, 78, 'Thomas Edison', '', 1, 1),
(233, 78, 'Nikola Tesla', '', 0, 1),
(234, 78, 'Alexander Bell', '', 0, 1),
(235, 79, '100', '', 1, 1),
(236, 79, '90', '', 0, 1),
(237, 79, '110', '', 0, 1),
(238, 80, 'China', '', 1, 1),
(239, 80, 'India', '', 0, 1),
(240, 80, 'USA', '', 0, 1),
(241, 81, 'E = mc²', '', 1, 1),
(242, 81, 'F = ma', '', 0, 1),
(243, 81, 'V = IR', '', 0, 1),
(244, 82, 'Charles Darwin', '', 1, 1),
(245, 82, 'Gregor Mendel', '', 0, 1),
(246, 82, 'Louis Pasteur', '', 0, 1),
(247, 83, '299,792 km/s', '', 1, 1),
(248, 83, '150,000 km/s', '', 0, 1),
(249, 83, '500,000 km/s', '', 0, 1),
(250, 84, 'Hydrogen', '', 1, 1),
(251, 84, 'Helium', '', 0, 1),
(252, 84, 'Oxygen', '', 0, 1),
(253, 85, 'Nile', '', 1, 1),
(254, 85, 'Amazon', '', 0, 1),
(255, 85, 'Yangtze', '', 0, 1),
(256, 86, 'Sun', '', 1, 1),
(257, 86, 'Earth', '', 0, 1),
(258, 86, 'Moon', '', 0, 1),
(259, 87, 'Reykjavik', '', 1, 1),
(260, 87, 'Oslo', '', 0, 1),
(261, 87, 'Helsinki', '', 0, 1),
(262, 88, 'Harper Lee', '', 1, 1),
(263, 88, 'Mark Twain', '', 0, 1),
(264, 88, 'J.K. Rowling', '', 0, 1),
(265, 89, 'Whale Shark', '', 1, 1),
(266, 89, 'Great White', '', 0, 1),
(267, 89, 'Hammerhead', '', 0, 1),
(268, 90, 'Study of particles', '', 1, 1),
(269, 90, 'Study of planets', '', 0, 1),
(270, 90, 'Study of fossils', '', 0, 1),
(271, 91, 'Đúng vậy !', 'no_image.png', 1, 1),
(272, 91, 'Tây 6 múi', 'no_image.png', 0, 1),
(273, 91, 'Tây m8', 'no_image.png', 0, 1),
(274, 91, 'có nhiều ny', 'no_image.png', 0, 1),
(275, 92, 'sdfsdgfs', 'no_image.png', 1, 1),
(276, 92, 'sdfsdgfsdgsdgsdgsdgsdg', 'no_image.png', 0, 1),
(277, 92, 'sdfsdgfsdgsdgsdgsdgsdgsdgsdg', 'no_image.png', 0, 1),
(278, 92, 'sdfsdgfsdgsdgsdgsdgsdgsdgsdgsdg', 'no_image.png', 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `exams`
--

CREATE TABLE `exams` (
  `testCode` varchar(20) NOT NULL,
  `exOrder` varchar(1) NOT NULL COMMENT 'A;B;C;D;E;F',
  `exCode` varchar(20) NOT NULL COMMENT '=testCode + exOrder',
  `ex_quesIDs` text NOT NULL COMMENT 'mảng các id câu hỏi'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `logs`
--

CREATE TABLE `logs` (
  `logID` int(11) NOT NULL,
  `logContent` text NOT NULL,
  `logUserID` int(11) NOT NULL,
  `logExCode` varchar(20) NOT NULL,
  `logDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `questions`
--

CREATE TABLE `questions` (
  `qID` int(11) NOT NULL,
  `qContent` text NOT NULL COMMENT 'nội dung câu hỏi',
  `qPictures` text NOT NULL COMMENT 'url hình đính kèm',
  `qTopicID` int(11) NOT NULL,
  `qLevel` varchar(10) NOT NULL COMMENT 'easy, meidum, diff',
  `qStatus` tinyint(4) NOT NULL COMMENT '1: active; 0: hidden'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `questions`
--

INSERT INTO `questions` (`qID`, `qContent`, `qPictures`, `qTopicID`, `qLevel`, `qStatus`) VALUES
(1, 'What is 5 + 7?', '', 1, 'easy', 1),
(2, 'What is 12 - 4?', '', 1, 'easy', 1),
(3, 'What is 3 × 4?', '', 1, 'easy', 1),
(4, 'What is 15 ÷ 3?', '', 1, 'easy', 1),
(5, 'What is 10 + 8?', '', 1, 'easy', 1),
(6, 'What is 20 - 5?', '', 1, 'easy', 1),
(7, 'What is 6 × 2?', '', 1, 'easy', 1),
(8, 'What is 18 ÷ 6?', '', 1, 'easy', 1),
(9, 'What is 9 + 3?', '', 1, 'easy', 1),
(10, 'What is 14 - 7?', '', 1, 'easy', 1),
(11, 'Solve for x: 2x + 4 = 10', '', 1, 'medium', 1),
(12, 'What is 25% of 80?', '', 1, 'medium', 1),
(13, 'Solve for y: 3y - 9 = 6', '', 1, 'medium', 1),
(14, 'What is the area of a rectangle with length 5 and width 3?', '', 1, 'medium', 1),
(15, 'What is 2^4?', '', 1, 'medium', 1),
(16, 'Solve for x: 5x = 25', '', 1, 'medium', 1),
(17, 'What is the perimeter of a square with side 6?', '', 1, 'medium', 1),
(18, 'What is 1/2 + 1/3?', '', 1, 'medium', 1),
(19, 'Solve for x: x/4 = 8', '', 1, 'medium', 1),
(20, 'What is 15% of 200?', '', 1, 'medium', 1),
(21, 'What is the derivative of x^3?', '', 1, 'diff', 1),
(22, 'Solve: x^2 - 4 = 0', '', 1, 'diff', 1),
(23, 'What is the integral of 2x dx?', '', 1, 'diff', 1),
(24, 'Find the limit as x approaches 0 of sin(x)/x', '', 1, 'diff', 1),
(25, 'What is the determinant of a 2x2 matrix [[1, 2], [3, 4]]?', '', 1, 'diff', 1),
(26, 'Solve: 2x^2 + 3x - 5 = 0', '', 1, 'diff', 1),
(27, 'What is the slope of the line y = 3x + 2?', '', 1, 'diff', 1),
(28, 'What is the sum of the first 10 positive integers?', '', 1, 'diff', 1),
(29, 'What is the value of cos(π)?', '', 1, 'diff', 1),
(30, 'Solve for x: log₂(x) = 3', '', 1, 'diff', 1),
(31, 'What is the extension of a Python file?', '', 2, 'easy', 1),
(32, 'Which symbol is used for comments in Python?', '', 2, 'easy', 1),
(33, 'What keyword declares a variable in Java?', '', 2, 'easy', 1),
(34, 'What does CSS stand for?', '', 2, 'easy', 1),
(35, 'Which language uses curly braces for blocks?', '', 2, 'easy', 1),
(36, 'What is the output of print(2 + 3)?', '', 2, 'easy', 1),
(37, 'What tag is used for a paragraph in HTML?', '', 2, 'easy', 1),
(38, 'Which operator checks equality in Java?', '', 2, 'easy', 1),
(39, 'What is the purpose of git commit?', '', 2, 'easy', 1),
(40, 'What does SQL stand for?', '', 2, 'easy', 1),
(41, 'What is a loop that runs a fixed number of times?', '', 2, 'medium', 1),
(42, 'What is the output of 5 > 3 && 2 < 4?', '', 2, 'medium', 1),
(43, 'How do you declare an array in Java?', '', 2, 'medium', 1),
(44, 'What method starts a thread in Java?', '', 2, 'medium', 1),
(45, 'What is the time complexity of a linear search?', '', 2, 'medium', 1),
(46, 'What does OOP stand for?', '', 2, 'medium', 1),
(47, 'How do you select all columns in SQL?', '', 2, 'medium', 1),
(48, 'What is a primary key in a database?', '', 2, 'medium', 1),
(49, 'What is the purpose of a constructor?', '', 2, 'medium', 1),
(50, 'What is recursion?', '', 2, 'medium', 1),
(51, 'What is the time complexity of merge sort?', '', 2, 'diff', 1),
(52, 'How does a binary tree differ from a binary search tree?', '', 2, 'diff', 1),
(53, 'What is a deadlock in multithreading?', '', 2, 'diff', 1),
(54, 'What is dynamic programming?', '', 2, 'diff', 1),
(55, 'How do you implement a singleton pattern?', '', 2, 'diff', 1),
(56, 'What is the difference between == and .equals() in Java?', '', 2, 'diff', 1),
(57, 'What is a hash table?', '', 2, 'diff', 1),
(58, 'What is dependency injection?', '', 2, 'diff', 1),
(59, 'What is the purpose of an interface?', '', 2, 'diff', 1),
(60, 'What is a RESTful API?', '', 2, 'diff', 1),
(61, 'What is the capital of Brazil?', '', 3, 'easy', 1),
(62, 'How many continents are there?', '', 3, 'easy', 1),
(63, 'What is the largest ocean?', '', 3, 'easy', 1),
(64, 'Who wrote Romeo and Juliet?', '', 3, 'easy', 1),
(65, 'What color is the sky on a clear day?', '', 3, 'easy', 1),
(66, 'How many days are in a week?', '', 3, 'easy', 1),
(67, 'What is the main ingredient in bread?', '', 3, 'easy', 1),
(68, 'What animal is known as man’s best friend?', '', 3, 'easy', 1),
(69, 'What is the currency of Japan?', '', 3, 'easy', 1),
(70, 'How many months are in a year?', '', 3, 'easy', 1),
(71, 'Who discovered gravity?', '', 3, 'medium', 1),
(72, 'What year did World War II end?', '', 3, 'medium', 1),
(73, 'What is the chemical symbol for gold?', '', 3, 'medium', 1),
(74, 'Who was the first president of the United States?', '', 3, 'medium', 1),
(75, 'What is the tallest mountain in the world?', '', 3, 'medium', 1),
(76, 'What gas makes up most of Earth’s atmosphere?', '', 3, 'medium', 1),
(77, 'What is the largest desert in the world?', '', 3, 'medium', 1),
(78, 'Who invented the light bulb?', '', 3, 'medium', 1),
(79, 'What is the boiling point of water in Celsius?', '', 3, 'medium', 1),
(80, 'What country has the most population?', '', 3, 'medium', 1),
(81, 'What is the theory of relativity?', '', 3, 'diff', 1),
(82, 'Who developed the theory of evolution?', '', 3, 'diff', 1),
(83, 'What is the speed of light?', '', 3, 'diff', 1),
(84, 'What element has the atomic number 1?', '', 3, 'diff', 1),
(85, 'What is the longest river in the world?', '', 3, 'diff', 1),
(86, 'What is the primary source of energy for Earth?', '', 3, 'diff', 1),
(87, 'What is the capital of Iceland?', '', 3, 'diff', 1),
(88, 'Who wrote \"To Kill a Mockingbird\"?', '', 3, 'diff', 1),
(89, 'What is the largest species of shark?', '', 3, 'diff', 1),
(90, 'What is quantum mechanics?', '', 3, 'diff', 1),
(91, 'Tây đẹp trai đúng k ?', 'A', 1, 'Dễ', 1),
(92, 'sdfsdgfsdgsdgsd', 'A', 1, 'Dễ', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `result`
--

CREATE TABLE `result` (
  `rs_num` tinyint(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `exCode` varchar(20) NOT NULL,
  `rs_anwsers` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'các đáp án đã chọn' CHECK (json_valid(`rs_anwsers`)),
  `rs_mark` decimal(10,0) NOT NULL,
  `rs_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test`
--

CREATE TABLE `test` (
  `testID` int(11) NOT NULL,
  `testCode` varchar(20) NOT NULL COMMENT 'mã bài thi',
  `testTitle` text NOT NULL,
  `testTime` int(11) NOT NULL COMMENT 'thời gian làm bài (phút)',
  `testLimit` tinyint(4) NOT NULL COMMENT 'số lần thi',
  `testDate` date NOT NULL,
  `testStatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test_structure`
--

CREATE TABLE `test_structure` (
  `testCode` varchar(50) NOT NULL,
  `tpID` int(11) NOT NULL,
  `numberEasy` int(11) NOT NULL,
  `numberMedium` int(11) NOT NULL,
  `numberDiff` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `topics`
--

CREATE TABLE `topics` (
  `tpID` int(11) NOT NULL,
  `tpTitle` text NOT NULL COMMENT 'tên topic',
  `tpParent` int(11) NOT NULL COMMENT 'id của topic cha',
  `tpStatus` tinyint(4) NOT NULL COMMENT '1: active; 0: hidden'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `topics`
--

INSERT INTO `topics` (`tpID`, `tpTitle`, `tpParent`, `tpStatus`) VALUES
(1, 'Mathematics', 0, 1),
(2, 'Programming', 0, 1),
(3, 'General Knowledge', 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `userName` varchar(40) NOT NULL COMMENT 'login = userName',
  `userEmail` varchar(20) NOT NULL,
  `userPassword` varchar(40) NOT NULL COMMENT 'mã hóa dùng md5',
  `userFullName` varchar(40) NOT NULL,
  `isAdmin` tinyint(4) NOT NULL COMMENT '1: admin; 0: user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`userID`, `userName`, `userEmail`, `userPassword`, `userFullName`, `isAdmin`) VALUES
(1, 'dsfds', 'sadfdsf', 'sdfdsf', 'sdf', 1),
(2, 'tay nguyen', 'tay123@gmail.com', '05052003', '1213123', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`awID`),
  ADD KEY `qID` (`qID`);

--
-- Chỉ mục cho bảng `exams`
--
ALTER TABLE `exams`
  ADD PRIMARY KEY (`testCode`,`exOrder`),
  ADD KEY `testCode` (`testCode`),
  ADD KEY `exCode` (`exCode`);

--
-- Chỉ mục cho bảng `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`logID`),
  ADD KEY `logUserID` (`logUserID`);

--
-- Chỉ mục cho bảng `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`qID`),
  ADD KEY `qTopicID` (`qTopicID`);

--
-- Chỉ mục cho bảng `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`rs_num`,`userID`,`exCode`),
  ADD KEY `userID` (`userID`),
  ADD KEY `exID` (`exCode`),
  ADD KEY `exCode` (`exCode`);

--
-- Chỉ mục cho bảng `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`testID`);

--
-- Chỉ mục cho bảng `test_structure`
--
ALTER TABLE `test_structure`
  ADD PRIMARY KEY (`testCode`),
  ADD KEY `tpID` (`tpID`);

--
-- Chỉ mục cho bảng `topics`
--
ALTER TABLE `topics`
  ADD PRIMARY KEY (`tpID`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `answers`
--
ALTER TABLE `answers`
  MODIFY `awID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=279;

--
-- AUTO_INCREMENT cho bảng `logs`
--
ALTER TABLE `logs`
  MODIFY `logID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `questions`
--
ALTER TABLE `questions`
  MODIFY `qID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT cho bảng `test`
--
ALTER TABLE `test`
  MODIFY `testID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `topics`
--
ALTER TABLE `topics`
  MODIFY `tpID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`qID`) REFERENCES `questions` (`qID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `exams`
--
ALTER TABLE `exams`
  ADD CONSTRAINT `exams_ibfk_1` FOREIGN KEY (`exCode`) REFERENCES `result` (`exCode`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`logUserID`) REFERENCES `users` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`qTopicID`) REFERENCES `topics` (`tpID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `test_structure`
--
ALTER TABLE `test_structure`
  ADD CONSTRAINT `test_structure_ibfk_1` FOREIGN KEY (`tpID`) REFERENCES `topics` (`tpID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

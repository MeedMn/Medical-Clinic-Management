-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 16, 2023 at 12:32 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestion_cabinet`
--

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

CREATE TABLE `consultation` (
  `id` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `date_consultation` date NOT NULL,
  `time_consultation` varchar(20) NOT NULL,
  `state` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consultation`
--

INSERT INTO `consultation` (`id`, `id_patient`, `date_consultation`, `time_consultation`, `state`) VALUES
(1, 1, '2023-01-04', '9:30', 'Cancelled'),
(2, 1, '2023-01-13', '12:15', 'Done'),
(3, 1, '2023-01-14', '11:15', 'Done'),
(4, 2, '2022-12-30', '10:30', 'Done'),
(5, 2, '2023-01-15', '13:00', 'Cancelled'),
(6, 2, '2023-01-16', '12:15', 'Done'),
(7, 1, '2023-01-16', '13:15', 'Done');

-- --------------------------------------------------------

--
-- Table structure for table `ordonnance`
--

CREATE TABLE `ordonnance` (
  `id` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `doctor` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ordonnance`
--

INSERT INTO `ordonnance` (`id`, `id_patient`, `doctor`, `content`, `date`) VALUES
(1, 1, 'Mohamed Mohamed', 'doliprane 1000mg\nzoukam 500mg', '2023-01-16');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `cne` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `numero` varchar(50) NOT NULL,
  `date_naissance` date NOT NULL,
  `sexe` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `cne`, `nom`, `prenom`, `age`, `numero`, `date_naissance`, `sexe`) VALUES
(1, 'test1', 'soufiqne', 'test1', 22, '0625349867', '2000-01-04', 'FEMME'),
(2, 'test2', 'test2', 'test2', 50, '0685943587', '1988-01-14', 'HOMME'),
(3, 'V00010', 'tttt', 'dsfsdfdsf', 22, '0642417229', '2023-01-04', 'HOMME');

-- --------------------------------------------------------

--
-- Table structure for table `rendezvous`
--

CREATE TABLE `rendezvous` (
  `id` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `date_rendezvous` date NOT NULL,
  `time_rendezvous` varchar(20) NOT NULL,
  `state` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rendezvous`
--

INSERT INTO `rendezvous` (`id`, `id_patient`, `date_rendezvous`, `time_rendezvous`, `state`) VALUES
(1, 1, '2023-01-04', '9:30', 'Cancelled'),
(2, 1, '2023-01-13', '12:15', 'Done'),
(3, 1, '2023-01-14', '11:15', 'Done'),
(4, 2, '2022-12-30', '10:30', 'Done'),
(5, 2, '2023-01-15', '13:00', 'Cancelled'),
(6, 2, '2023-01-16', '12:15', 'Not yet'),
(7, 1, '2023-01-16', '13:15', 'Not yet');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `cne` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(80) NOT NULL,
  `numero` varchar(50) NOT NULL,
  `role` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `cne`, `password`, `nom`, `prenom`, `age`, `email`, `numero`, `role`, `state`) VALUES
(1, 'V00001', '123456', 'Fatiha', 'Fatiha', 22, 'fatiha@gmail.com', '0624568595', 'assistante', 'exist'),
(2, 'V00002', '123456', 'Mohamed', 'Mohamed', 32, 'mohamed@gmail.com', '0698655315', 'doctor', 'exist'),
(3, 'V00003', '123456', 'Ahmed', 'Ahmed', 52, 'ahmed@gmail.com', '0615249862', 'doctor', 'absent');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_patient` (`id_patient`);

--
-- Indexes for table `ordonnance`
--
ALTER TABLE `ordonnance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_patient` (`id_patient`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cne` (`cne`);

--
-- Indexes for table `rendezvous`
--
ALTER TABLE `rendezvous`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_patient` (`id_patient`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ordonnance`
--
ALTER TABLE `ordonnance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rendezvous`
--
ALTER TABLE `rendezvous`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `consultation_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id`);

--
-- Constraints for table `ordonnance`
--
ALTER TABLE `ordonnance`
  ADD CONSTRAINT `ordonnance_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id`);

--
-- Constraints for table `rendezvous`
--
ALTER TABLE `rendezvous`
  ADD CONSTRAINT `rendezvous_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

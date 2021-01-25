-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 25 jan. 2021 à 01:03
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `api-labo-v2`
--

-- --------------------------------------------------------

--
-- Structure de la table `api`
--

DROP TABLE IF EXISTS `api`;
CREATE TABLE IF NOT EXISTS `api` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `api`
--

INSERT INTO `api` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES
(51, '', 'API V E');

-- --------------------------------------------------------

--
-- Structure de la table `apibacterie`
--

DROP TABLE IF EXISTS `apibacterie`;
CREATE TABLE IF NOT EXISTS `apibacterie` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `REFERENCE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `apibacterie`
--

INSERT INTO `apibacterie` (`ID`, `DESCRIPTION`, `REFERENCE`) VALUES
(8, 'Er.  am ylouora	\r\n', 'Er.  am ylouora	\r\n'),
(9, 'Erwinia  rubriJaciens	\r\n', 'Erwinia  rubriJaciens	\r\n'),
(10, 'Er.  rubrifaciens', 'Er.  rubrifaciens'),
(11, 'Erwinia  mallotivora', 'Erwinia  mallotivora');

-- --------------------------------------------------------

--
-- Structure de la table `apibook`
--

DROP TABLE IF EXISTS `apibook`;
CREATE TABLE IF NOT EXISTS `apibook` (
  `ID` bigint(20) NOT NULL,
  `IDENTIFICATION` varchar(255) DEFAULT NULL,
  `APIBACTERIE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_APIBOOK_APIBACTERIE_ID` (`APIBACTERIE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `apibook`
--

INSERT INTO `apibook` (`ID`, `IDENTIFICATION`, `APIBACTERIE_ID`) VALUES
(1, '0005122\r\n', 8),
(2, '0004022', 9),
(3, '0004122', 10),
(4, '1005522', 11);

-- --------------------------------------------------------

--
-- Structure de la table `apibooklet`
--

DROP TABLE IF EXISTS `apibooklet`;
CREATE TABLE IF NOT EXISTS `apibooklet` (
  `ID` bigint(20) NOT NULL,
  `POSITIF` tinyint(1) DEFAULT 0,
  `APIELEMENT_ID` bigint(20) DEFAULT NULL,
  `COULEUR_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_APIBOOKLET_COULEUR_ID` (`COULEUR_ID`),
  KEY `FK_APIBOOKLET_APIELEMENT_ID` (`APIELEMENT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `apibooklet`
--

INSERT INTO `apibooklet` (`ID`, `POSITIF`, `APIELEMENT_ID`, `COULEUR_ID`) VALUES
(102, 1, 79, 1),
(103, 1, 78, 1),
(104, 1, 77, 1),
(105, 1, 76, 1),
(106, 1, 75, 1),
(151, 1, 59, 1),
(152, 1, 60, 201),
(153, 1, 61, 201),
(154, 1, 62, 201),
(155, 1, 63, 202),
(156, 1, 64, 204),
(157, 1, 65, 201),
(158, 1, 66, 6),
(159, 1, 67, 203),
(160, 1, 68, 101),
(161, 1, 69, 4),
(162, 1, 71, 1),
(163, 1, 72, 1),
(164, 1, 73, 1),
(165, 1, 74, 1);

-- --------------------------------------------------------

--
-- Structure de la table `apielement`
--

DROP TABLE IF EXISTS `apielement`;
CREATE TABLE IF NOT EXISTS `apielement` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL,
  `NUMERO` bigint(20) DEFAULT NULL,
  `POIDS` bigint(20) DEFAULT NULL,
  `APIGROUPE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_APIELEMENT_APIGROUPE_ID` (`APIGROUPE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `apielement`
--

INSERT INTO `apielement` (`ID`, `DESCRIPTION`, `LIBELLE`, `NUMERO`, `POIDS`, `APIGROUPE_ID`) VALUES
(59, 'test for ?-galactosidase enzyme by hydrolysis of the substrate o-nitrophenyl-b-D-galactopyranoside', 'ONPG', 1, 1, 52),
(60, 'decarboxylation of the amino acid arginine by arginine dihydrolase', 'ADH', 2, 2, 52),
(61, 'decarboxylation of the amino acid lysine by lysine decarboxylase', 'LDC', 3, 4, 52),
(62, 'decarboxylation of the amino acid ornithine by ornithine decarboxylase', 'ODC', 1, 1, 53),
(63, 'utilization of citrate as only carbon source', 'CIT', 2, 2, 53),
(64, 'production of hydrogen sulfide', 'H2S', 3, 4, 53),
(65, 'test for the enzyme urease', 'URE', 1, 1, 54),
(66, '(Tryptophan deaminase): detection of the enzyme tryptophan deaminase: Reagent- Ferric Chloride.', 'TDA', 2, 2, 54),
(67, 'Indole Test-production of indole from tryptophan by the enzyme tryptophanase . Reagent- Indole is detected by addition of Kovac’s reagent.', 'IND', 3, 4, 54),
(68, 'the Voges-Proskauer test for the detection of acetoin (acetyl methylcarbinol) produced by fermentation of glucose by bacteria utilizing the butylene glycol pathway', 'VP', 1, 1, 55),
(69, 'test for the production of the enzyme gelatinase which liquefies gelatin', 'GEL', 2, 2, 55),
(71, 'fermentation of glucose (hexose sugar)', 'GLU', 3, 4, 55),
(72, 'fermentation of mannose (hexose sugar)', 'MAN', 1, 1, 56),
(73, 'fermentation of inositol (cyclic polyalcohol)', 'INO', 2, 2, 56),
(74, 'fermentation of sorbitol (alcohol sugar)', 'SOR', 3, 4, 56),
(75, 'fermentation of rhamnose (methyl pentose sugar)', 'RHA', 1, 1, 57),
(76, 'fermentation of sucrose (disaccharide)', 'SAC', 2, 2, 57),
(77, 'fermentation of melibiose (disaccharide)', 'MEL', 3, 4, 57),
(78, 'fermentation of amygdalin (glycoside)', 'AMY', 1, 1, 58),
(79, 'fermentation of arabinose (pentose sugar)', 'ARA', 2, 2, 58);

-- --------------------------------------------------------

--
-- Structure de la table `apigroupe`
--

DROP TABLE IF EXISTS `apigroupe`;
CREATE TABLE IF NOT EXISTS `apigroupe` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL,
  `NUMERO` bigint(20) DEFAULT NULL,
  `API_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_APIGROUPE_API_ID` (`API_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `apigroupe`
--

INSERT INTO `apigroupe` (`ID`, `DESCRIPTION`, `LIBELLE`, `NUMERO`, `API_ID`) VALUES
(52, '', 'G 1', 1, 51),
(53, '', 'G 2', 2, 51),
(54, '', 'G 3', 3, 51),
(55, '', 'G 4', 4, 51),
(56, '', 'G 5', 5, 51),
(57, '', 'G 6', 6, 51),
(58, '', 'G 7', 7, 51);

-- --------------------------------------------------------

--
-- Structure de la table `couleur`
--

DROP TABLE IF EXISTS `couleur`;
CREATE TABLE IF NOT EXISTS `couleur` (
  `ID` bigint(20) NOT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `couleur`
--

INSERT INTO `couleur` (`ID`, `CODE`) VALUES
(1, 'Jaune'),
(2, 'Vert'),
(3, 'Bleu'),
(4, 'Noire diffuse'),
(5, 'Rose'),
(6, 'Marron-Rouge'),
(7, 'Sans Couleur'),
(101, 'Rouge/Rose'),
(201, 'Rouge/Orange'),
(202, 'Bleu-Vert/Bleu'),
(203, 'Rouge'),
(204, 'Noire deposite');

-- --------------------------------------------------------

--
-- Structure de la table `infection`
--

DROP TABLE IF EXISTS `infection`;
CREATE TABLE IF NOT EXISTS `infection` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '250');

-- --------------------------------------------------------

--
-- Structure de la table `testidentificationapi`
--

DROP TABLE IF EXISTS `testidentificationapi`;
CREATE TABLE IF NOT EXISTS `testidentificationapi` (
  `ID` bigint(20) NOT NULL,
  `DATETESTIDENTIFICATIONAPI` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `IDENTIFICATION` varchar(255) DEFAULT NULL,
  `REFERENCPATIENT` varchar(255) DEFAULT NULL,
  `REFERENCE` varchar(255) DEFAULT NULL,
  `APIBACTERIE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TESTIDENTIFICATIONAPI_APIBACTERIE_ID` (`APIBACTERIE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `testidentificationelement`
--

DROP TABLE IF EXISTS `testidentificationelement`;
CREATE TABLE IF NOT EXISTS `testidentificationelement` (
  `ID` bigint(20) NOT NULL,
  `POIDS` bigint(20) DEFAULT NULL,
  `APIELEMENT_ID` bigint(20) DEFAULT NULL,
  `COULEUR_ID` bigint(20) DEFAULT NULL,
  `POIDS_SELECTED` bigint(20) NOT NULL,
  `SELECTED` smallint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TESTIDENTIFICATIONELEMENT_COULEUR_ID` (`COULEUR_ID`),
  KEY `FK_TESTIDENTIFICATIONELEMENT_APIELEMENT_ID` (`APIELEMENT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `testidentificationgroupe`
--

DROP TABLE IF EXISTS `testidentificationgroupe`;
CREATE TABLE IF NOT EXISTS `testidentificationgroupe` (
  `ID` bigint(20) NOT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `APIGROUPE_ID` bigint(20) DEFAULT NULL,
  `TESTIDENTIFICATIONAPI_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `TESTIDENTIFICATIONGROUPE_TESTIDENTIFICATIONAPI_ID` (`TESTIDENTIFICATIONAPI_ID`),
  KEY `FK_TESTIDENTIFICATIONGROUPE_APIGROUPE_ID` (`APIGROUPE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

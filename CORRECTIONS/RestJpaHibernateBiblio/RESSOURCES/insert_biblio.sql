-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 23 nov. 2022 à 10:40
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `biblio`
--

--
-- Déchargement des données de la table `auteur`
--

INSERT INTO `auteur` (`id`, `email`, `nom`, `prenom`, `telephone`) VALUES
(1, NULL, 'Toto', NULL, '0606060606'),
(2, 'thinking@me.net', 'Eckel', 'Bruce', '0605040302'),
(3, NULL, 'Goncalves', 'Antonio', '0102030405'),
(4, 'petharg@hotmail.com', 'Haggar', 'Petter', '0655443322'),
(5, 'claude@delanooy.com', 'Delannoy', 'Claude', '0677889900');

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `categorie`, `nb_pages`, `titre`, `auteur_id`, `couverture_id`) VALUES
(1, 'java', 320, 'Thinking in Java', 1, NULL),
(2, 'cpp', 640, 'Thinking in C++', 1, NULL),
(3, 'java', 240, 'Les cahiers du programmeur Java EE', 2, NULL),
(4, 'javaee', 120, 'Beginning Java EE 7', 2, NULL),
(5, 'java', 540, 'Mieux programmer en Java', 3, NULL),
(6, 'java', 184, 'Exercices en Java', 4, NULL),
(7, 'algo', 350, 'Initiation à la programmation', 4, NULL),
(8, 'cpp', 842, 'C++ Guide complet', 4, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

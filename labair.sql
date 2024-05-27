-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 27, 2024 alle 15:45
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `labair`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `carrelli`
--

CREATE TABLE `carrelli` (
  `id` int(10) UNSIGNED NOT NULL,
  `utente_id` int(10) UNSIGNED NOT NULL,
  `totale` float(7,2) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `carrello_ordine`
--

CREATE TABLE `carrello_ordine` (
  `carrello_id` int(10) UNSIGNED NOT NULL,
  `ordine_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `categorie`
--

CREATE TABLE `categorie` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `categorie`
--

INSERT INTO `categorie` (`id`, `nome`) VALUES
(1, 'Basket'),
(2, 'Football'),
(3, 'Volley'),
(4, 'Jogging');

-- --------------------------------------------------------

--
-- Struttura della tabella `colore_scarpa`
--

CREATE TABLE `colore_scarpa` (
  `scarpa_id` int(10) UNSIGNED NOT NULL,
  `colore_id` int(10) UNSIGNED NOT NULL,
  `quantita` int(5) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `colore_scarpa`
--

INSERT INTO `colore_scarpa` (`scarpa_id`, `colore_id`, `quantita`) VALUES
(1, 1, 50),
(2, 2, 50),
(3, 2, 50),
(3, 3, 50),
(1, 4, 50);

-- --------------------------------------------------------

--
-- Struttura della tabella `colori_disponibili`
--

CREATE TABLE `colori_disponibili` (
  `id` int(10) UNSIGNED NOT NULL,
  `colore` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `colori_disponibili`
--

INSERT INTO `colori_disponibili` (`id`, `colore`) VALUES
(1, 'Bianco/Grigio'),
(2, 'Giallo/Verde'),
(3, 'Gold/Nero'),
(4, 'Nero/Giallo'),
(5, 'Rosso/Nero');

-- --------------------------------------------------------

--
-- Struttura della tabella `immagini`
--

CREATE TABLE `immagini` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `immagini`
--

INSERT INTO `immagini` (`id`, `nome`) VALUES
(1, '1.jpeg'),
(2, '2.jpeg'),
(3, '3.jpeg'),
(4, '4.jpeg'),
(5, '5.jpeg');

-- --------------------------------------------------------

--
-- Struttura della tabella `indirizzi`
--

CREATE TABLE `indirizzi` (
  `id` int(10) UNSIGNED NOT NULL,
  `via` varchar(50) NOT NULL,
  `comune` varchar(50) NOT NULL,
  `provincia` char(2) NOT NULL,
  `paese` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `login`
--

CREATE TABLE `login` (
  `id` int(10) UNSIGNED NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(20) NOT NULL,
  `utente_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `modelli_scarpe_disponibili`
--

CREATE TABLE `modelli_scarpe_disponibili` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descrizione` varchar(200) NOT NULL,
  `prezzo` float(5,2) UNSIGNED NOT NULL,
  `immagine_id` int(10) UNSIGNED NOT NULL,
  `categoria_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `modelli_scarpe_disponibili`
--

INSERT INTO `modelli_scarpe_disponibili` (`id`, `nome`, `descrizione`, `prezzo`, `immagine_id`, `categoria_id`) VALUES
(1, 'Nike React Infinity Run Flyknit', 'Le Nike React Infinity Run Flyknit sono progettate per la massima \r\nammortizzazione e stabilità durante la corsa. Con tomaia in Flyknit per un comfort \r\nsuperiore', 159.99, 1, 1),
(2, 'Nike Run Flyknit', 'Le Nike Run Flyknit sono progettate per la massima \r\nammortizzazione e stabilità durante la corsa. Con tomaia in Flyknit per un comfort \r\nsuperiore', 179.99, 3, 4),
(3, 'Nike Infinity React', 'Le Nike Infinity React sono progettate per la massima \r\nammortizzazione e stabilità durante la corsa. Con tomaia in Flyknit per un comfort \r\nsuperiore', 169.00, 5, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `ordini_nei_carrelli`
--

CREATE TABLE `ordini_nei_carrelli` (
  `id_ordine` int(10) UNSIGNED NOT NULL,
  `identificativo_scarpa` int(10) UNSIGNED NOT NULL,
  `nome` varchar(100) NOT NULL,
  `taglia` char(2) NOT NULL,
  `colore` varchar(50) NOT NULL,
  `prezzo` float(5,2) UNSIGNED NOT NULL,
  `quantita` int(2) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `taglia_scarpa`
--

CREATE TABLE `taglia_scarpa` (
  `scarpa_id` int(10) UNSIGNED NOT NULL,
  `taglia_id` int(10) UNSIGNED NOT NULL,
  `quantita` int(5) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `taglia_scarpa`
--

INSERT INTO `taglia_scarpa` (`scarpa_id`, `taglia_id`, `quantita`) VALUES
(1, 1, 50),
(2, 2, 50),
(3, 3, 50),
(1, 4, 50),
(2, 5, 50),
(3, 6, 50);

-- --------------------------------------------------------

--
-- Struttura della tabella `taglie_disponibili`
--

CREATE TABLE `taglie_disponibili` (
  `id` int(10) UNSIGNED NOT NULL,
  `taglia` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `taglie_disponibili`
--

INSERT INTO `taglie_disponibili` (`id`, `taglia`) VALUES
(1, '38'),
(2, '39'),
(3, '40'),
(4, '41'),
(5, '42'),
(6, '43'),
(7, '44'),
(8, '45'),
(9, '46');

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `cognome` varchar(50) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `indirizzo_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `nome`, `cognome`, `email`, `indirizzo_id`) VALUES
(2, NULL, NULL, NULL, NULL);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `carrelli`
--
ALTER TABLE `carrelli`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `xd_carrelli_utente_id` (`utente_id`);

--
-- Indici per le tabelle `carrello_ordine`
--
ALTER TABLE `carrello_ordine`
  ADD PRIMARY KEY (`carrello_id`,`ordine_id`),
  ADD KEY `ordine_id` (`ordine_id`);

--
-- Indici per le tabelle `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `colore_scarpa`
--
ALTER TABLE `colore_scarpa`
  ADD PRIMARY KEY (`colore_id`,`scarpa_id`);

--
-- Indici per le tabelle `colori_disponibili`
--
ALTER TABLE `colori_disponibili`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `immagini`
--
ALTER TABLE `immagini`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `indirizzi`
--
ALTER TABLE `indirizzi`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `password` (`password`),
  ADD KEY `xd_login_utente_id` (`utente_id`);

--
-- Indici per le tabelle `modelli_scarpe_disponibili`
--
ALTER TABLE `modelli_scarpe_disponibili`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `xd_scarpe_immagine_id` (`immagine_id`),
  ADD KEY `xd_scarpe_categoria_id` (`categoria_id`);

--
-- Indici per le tabelle `ordini_nei_carrelli`
--
ALTER TABLE `ordini_nei_carrelli`
  ADD PRIMARY KEY (`id_ordine`),
  ADD KEY `identificativo_scarpa` (`identificativo_scarpa`);

--
-- Indici per le tabelle `taglia_scarpa`
--
ALTER TABLE `taglia_scarpa`
  ADD PRIMARY KEY (`taglia_id`,`scarpa_id`);

--
-- Indici per le tabelle `taglie_disponibili`
--
ALTER TABLE `taglie_disponibili`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `indirizzo_id` (`indirizzo_id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `carrelli`
--
ALTER TABLE `carrelli`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT per la tabella `colori_disponibili`
--
ALTER TABLE `colori_disponibili`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `immagini`
--
ALTER TABLE `immagini`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `indirizzi`
--
ALTER TABLE `indirizzi`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `login`
--
ALTER TABLE `login`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `ordini_nei_carrelli`
--
ALTER TABLE `ordini_nei_carrelli`
  MODIFY `id_ordine` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `taglie_disponibili`
--
ALTER TABLE `taglie_disponibili`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `carrelli`
--
ALTER TABLE `carrelli`
  ADD CONSTRAINT `carrelli_ibfk_1` FOREIGN KEY (`utente_id`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `carrello_ordine`
--
ALTER TABLE `carrello_ordine`
  ADD CONSTRAINT `carrello_ordine_ibfk_1` FOREIGN KEY (`carrello_id`) REFERENCES `carrelli` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `carrello_ordine_ibfk_2` FOREIGN KEY (`ordine_id`) REFERENCES `ordini_nei_carrelli` (`id_ordine`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `colore_scarpa`
--
ALTER TABLE `colore_scarpa`
  ADD CONSTRAINT `colore_scarpa_ibfk_1` FOREIGN KEY (`colore_id`) REFERENCES `colori_disponibili` (`id`),
  ADD CONSTRAINT `colore_scarpa_ibfk_2` FOREIGN KEY (`scarpa_id`) REFERENCES `modelli_scarpe_disponibili` (`id`);

--
-- Limiti per la tabella `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`utente_id`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `modelli_scarpe_disponibili`
--
ALTER TABLE `modelli_scarpe_disponibili`
  ADD CONSTRAINT `modelli_scarpe_disponibili_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categorie` (`id`),
  ADD CONSTRAINT `modelli_scarpe_disponibili_ibfk_2` FOREIGN KEY (`immagine_id`) REFERENCES `immagini` (`id`);

--
-- Limiti per la tabella `taglia_scarpa`
--
ALTER TABLE `taglia_scarpa`
  ADD CONSTRAINT `taglia_scarpa_ibfk_1` FOREIGN KEY (`scarpa_id`) REFERENCES `modelli_scarpe_disponibili` (`id`),
  ADD CONSTRAINT `taglia_scarpa_ibfk_2` FOREIGN KEY (`taglia_id`) REFERENCES `taglie_disponibili` (`id`);

--
-- Limiti per la tabella `utenti`
--
ALTER TABLE `utenti`
  ADD CONSTRAINT `utenti_ibfk_1` FOREIGN KEY (`indirizzo_id`) REFERENCES `indirizzi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

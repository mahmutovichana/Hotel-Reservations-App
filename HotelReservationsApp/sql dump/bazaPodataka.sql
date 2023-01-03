-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_rprbaza1
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `HOTELS`
--

DROP TABLE IF EXISTS `HOTELS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HOTELS` (
  `hotel_id` int NOT NULL DEFAULT '2',
  `name` varchar(45) NOT NULL,
  `zipCode` int NOT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(100) NOT NULL,
  `starRating` int NOT NULL,
  PRIMARY KEY (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HOTELS`
--

LOCK TABLES `HOTELS` WRITE;
/*!40000 ALTER TABLE `HOTELS` DISABLE KEYS */;
INSERT INTO `HOTELS` VALUES (0,'Hotel Sunce',88390,'Neum','Bosnia and Herzegovina',3),(1,'Hotel Europe',71000,'Sarajevo','Bosnia and Herzegovina',4),(2,'Hotel Hills',71000,'Sarajevo','Bosna i Hercegovina',5);
/*!40000 ALTER TABLE `HOTELS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESERVATIONS`
--

DROP TABLE IF EXISTS `RESERVATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RESERVATIONS` (
  `reservation_id` int NOT NULL,
  `checkIn` datetime NOT NULL,
  `checkOut` datetime NOT NULL,
  `total` int NOT NULL,
  `adults` int NOT NULL,
  `children` int NOT NULL,
  `room_id` int NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `room_id_idx` (`room_id`),
  KEY `username_idx` (`username`),
  CONSTRAINT `room_id` FOREIGN KEY (`room_id`) REFERENCES `ROOMS` (`room_id`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `USERS` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESERVATIONS`
--

LOCK TABLES `RESERVATIONS` WRITE;
/*!40000 ALTER TABLE `RESERVATIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `RESERVATIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROOMS`
--

DROP TABLE IF EXISTS `ROOMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ROOMS` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `capacity` int NOT NULL,
  `hasAirConditioning` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `hotel_id` int NOT NULL,
  `price` double unsigned NOT NULL,
  PRIMARY KEY (`room_id`),
  KEY `hotel_id_idx` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROOMS`
--

LOCK TABLES `ROOMS` WRITE;
/*!40000 ALTER TABLE `ROOMS` DISABLE KEYS */;
/*!40000 ALTER TABLE `ROOMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USERS` (
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'if role = 1 then the user is admin of the application, else it''s a basic user.\\n',
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES ('Amina','Bajrić','abajric2@etf.unsa.ba',0,'abajric2','amina123'),('Aldin','Bulbul','abulbul1@etf.unsa.ba',0,'abulbul1','aldin123'),('Amina','Čajić','acajic1@etf.unsa.ba',0,'acajic1','amina123'),('Amina','Hromić','ahromic1@etf.unsa.ba',0,'ahromic1','amina123'),('Alem','Muratović','amuratovic2@etf.unsa.ba',0,'amuratovic2','alem123'),('Almedin','Pašalić','apasalic1@etf.unsa.ba',0,'apasalic1','almedin123'),('Amel','Ridžal','aridzal2@etf.unsa.ba',0,'aridzal2','popravio'),('Amina ','Rokša','aroksa2@etf.unsa.ba',0,'aroksa2','amina123'),('Aida','Zametica','azametica1@etf.unsa.ba',0,'azametica1','aida123'),('Azra','Žunić','azunic3@etf.unsa.ba',0,'azunic3','azra123'),('Benjamin','Kadić','bkadic1@etf.unsa.ba',0,'bkadic1','benjamin123'),('Berin ','Karahodžić','bkarahodzic1@etf.unsa.ba',0,'bkarahodzic1','berin123'),('Berina','Zejnilović','bzejnilovic1@etf.unsa.ba',0,'bzejnilovic1','berina123'),('Dalila','Kršlak','dkrslak1@etf.unsa.ba',0,'dalilakrslak','dalila123'),('Emir','Agović','eagovic1@etf.unsa.ba',0,'eagovic1','emir123'),('Esma ','Bajramović','ebajramovic3@etf.unsa.ba',0,'ebajramovic3','esma123'),('Edna','Bašić','ebasic1@etf.unsa.ba',0,'ebasic1','edna123'),('Emir','Bronja','ebronja1@etf.unsa.ba',0,'ebronja1','emir123'),('Edwin','Graca','egraca1@etf.unsa.ba',0,'egraca1','edwin123'),('Emir','Kapić','ekapic1@etf.unsa.ba',0,'ekapic1','emir123'),('Ehlimana','Krupalija','ekrupalija1@etf.unsa.ba',0,'ekrupalija1','ehlimana123'),('Faris ','Gabela','fgabela1@etf.unsa.ba',0,'fgabela1','faris123'),('Hana','Mahmutović','hmahmutovi3@etf.unsa.ba',1,'hanamahmutovic','hana123'),('Ilhana','Gabela','igabela1@etf.unsa.ba',0,'igabela1','ilhana123'),('Ilhan','Hasičić','ihasicic1@etf.unsa.ba',0,'ihasicic1','ilhan123'),('Lejla','Heleg','lheleg1@etf.unsa.ba',0,'lheleg1','lejla123'),('Medin','Mujković','mmujkovic1@etf.unsa.ba',0,'mmujkovic1','medin123'),('Nejra','Adilović','nadilovic2@etf.unsa.ba',0,'nadilovic2','nejra123'),('Naila','Džananović','ndzananovic1@etf.unsa.ba',0,'ndzananovic1','naila123'),('Nerma','Kadrić','nkadric1@etf.unsa.ba',0,'nkadric1','nerma123'),('Selma','Ličina','slicina1@etf.unsa.ba',0,'slicina1','selma123'),('Taner','Bajrović','tbajrovic1@etf.unsa.ba',0,'tbajrovic1','taner123'),('Tarik','Berkovac','tberkovac1@etf.unsa.ba',0,'tberkovac1','tarik123'),('Tajra','Selimović','tselimovic2@etf.unsa.ba',0,'tselimovic2','tajra123'),('Vedran','Mujić','vmujic1@etf.unsa.ba',0,'vmujic1','vedran123'),('Zerina','Ahmetović','zahmetovic1@etf.unsa.ba',0,'zahmetovic1','zerina123');
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 17:10:52

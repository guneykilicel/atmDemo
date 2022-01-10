-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: atm_bank_customer_data
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `customer_data`
--

DROP TABLE IF EXISTS `customer_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_data` (
  `musteriNo` int NOT NULL,
  `TC` varchar(45) NOT NULL,
  `Sifre` varchar(4) NOT NULL,
  `HesapBakiye` double NOT NULL,
  `KrediKartiBorc` double DEFAULT NULL,
  `KrediKartiLimit` double DEFAULT NULL,
  `ABDDolari` double DEFAULT NULL,
  `Euro` double DEFAULT NULL,
  `IngilizSterlini` double DEFAULT NULL,
  `KuveytDinari` double DEFAULT NULL,
  `Altin` double DEFAULT NULL,
  `Telefon` varchar(45) DEFAULT NULL,
  `Iban` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`musteriNo`),
  UNIQUE KEY `musteriNo_UNIQUE` (`musteriNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_data`
--

LOCK TABLES `customer_data` WRITE;
/*!40000 ALTER TABLE `customer_data` DISABLE KEYS */;
INSERT INTO `customer_data` VALUES (1,'123456789','1234',26440,7000,13076,1493.456505003849,500,800,900,19.637103577209825,'5554443322','123456789'),(2,'123456788','1234',29520,15000,33640,500,200,300,600,5,'5443332211','123456788'),(3,'123456777','1234',21750,150,5000,700,100,300,500,8,'5998887766','123456777'),(4,'123456666','1234',26650,800,3000,200,300,500,100,9,'5887776655','123456789');
/*!40000 ALTER TABLE `customer_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-03 11:13:53
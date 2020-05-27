
CREATE DATABASE  IF NOT EXISTS `fogdb`;


USE `fogdb`;

-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fogdb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `items` (
  `itemID` int(11) NOT NULL AUTO_INCREMENT,
  `orderID` int(11) NOT NULL,
  `materialID` int(11) DEFAULT NULL,
  `availableSize` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `variationID` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  KEY `fk_items_variants_idx` (`variationID`),
  KEY `fk_items_orders_idx` (`orderID`),
  KEY `fk_items_material_idx_idx` (`materialID`),
  CONSTRAINT `fk_items_material_idx` FOREIGN KEY (`materialID`) REFERENCES `materials` (`materialID`),
  CONSTRAINT `fk_items_orders` FOREIGN KEY (`orderID`) REFERENCES `orders` (`orderID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_items_variants` FOREIGN KEY (`variationID`) REFERENCES `variations` (`variationID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `materials` (
  `materialID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `thickness` int(11) DEFAULT NULL,
  `unit` varchar(45) DEFAULT NULL,
  `keyword` varchar(100) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `spending` double DEFAULT NULL,
  PRIMARY KEY (`materialID`),
  UNIQUE KEY `materialID` (`materialID`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'SPÆRTRÆ UBEHANDLET',195,45,'stk',NULL,'Konstruktion',49.95,NULL,NULL),(2,'TRYKIMPRENERET BRÆDT 200',200,25,'stk',NULL,'Konstruktion',32.95,NULL,NULL),(3,'TRYKIMPRENERET BRÆDT 125',125,25,'stk','','Konstruktion',27.95,NULL,NULL),(4,'LÆGTE UBEHANDLET',73,38,'stk','','Skur',22.95,NULL,NULL),(5,'REGLAR UBEHANDLET',95,45,'stk','','Skur',24.95,NULL,NULL),(6,'TRYKIMPRENERET STOLPE',97,97,'stk','','Konstruktion',41.95,NULL,NULL),(7,'TRYKIMPRENERET BRÆDT',100,19,'stk','','Konstruktion',9.95,NULL,NULL),(8,'TRAPEZPLADE',109,15,'stk','FladtTag','FladtTag',35.95,NULL,NULL),(9,'TRAPEZPLADE',106,15,'stk','FladtTag','FladtTag',189.95,NULL,NULL),(10,'TRAPEZPLADE',109,15,'stk','FladtTag','FladtTag',99.95,NULL,NULL),(11,'BUNDSKRUER',NULL,NULL,'pk','FladtTag','FladtTag Konstruktion',409,NULL,NULL),(12,'HULBÅND',20,1,'rulle','','Konstruktion',209,NULL,NULL),(13,'UNIVERSALBESLAG',190,NULL,'stk','','Konstruktion',79.95,NULL,NULL),(14,'SKRUER',60,5,'pk','','Konstruktion',74.95,NULL,NULL),(15,'BESLAGSKRUER',50,4,'pk','','Konstruktion',1.7,NULL,NULL),(16,'BRÆDDEBOLT',120,10,'stk','','Konstruktion',5.5,NULL,NULL),(17,'FIRKANTSKIVER',40,11,'stk','','Konstruktion',10.11,NULL,NULL),(18,'SKRUER',70,5,'pk','','Konstruktion',129,NULL,NULL),(19,'SKRUER',50,5,'pk','','Konstruktion',99.95,NULL,NULL),(20,'STALDDØRSGREB',75,50,'stk','','Skur',59.95,NULL,NULL),(21,'HÆNGSEL',390,NULL,'stk','','Skur',49.95,NULL,NULL),(22,'VINKELBESLAG',35,NULL,'stk','','Skur',4.95,NULL,NULL),(23,'TÆTNINGSPROFIL',105,15,'stk','FladtTag','FladtTag Konstruktion',44.95,NULL,NULL),(24,'BETONTAGSTEN',NULL,NULL,'stk','RejsningTag','RejsningTag',180.95,NULL,NULL),(25,'ETERNITTAG B6',NULL,NULL,'stk','RejsningTag','RejsningTag',220.95,NULL,NULL),(26,'ETERNITTAG B7',NULL,NULL,'stk','RejsningTag','RejsningTag',230.95,NULL,NULL),(27,'VINDSKEDER',150,25,'stk','RejsningTag','RejsningTag Konstruktion',39.95,NULL,NULL),(28,'STERNBRÆT',150,25,'stk','RejsningTag','RejsningTag Konstruktion',129.95,NULL,NULL),(29,'VANDBRÆT',100,19,'stk','RejsningTag','RejsningTag Konstruktion',100,NULL,NULL),(30,'TAGLÆGTE ',73,38,'stk','RejsningTag','RejsningTag Konstruktion',99.95,NULL,NULL),(31,'TAGFODSLÆGTE BRÆDT',50,25,'stk','RejsningTag','RejsningTag Konstruktion',49.95,NULL,NULL),(32,'TAGSTEN BINDERE',NULL,NULL,'pk','RejsningTag','RejsningTag Konstruktion',20,NULL,NULL),(33,'TAGSTEN NAKKEKROGE',NULL,NULL,'pk','RejsningTag','RejsningTag Konstruktion',20,NULL,NULL),(34,'TOPLÆGTEHOLDER',NULL,NULL,'stk','RejsningTag','RejsningTag Konstruktion',30,NULL,NULL),(35,'SKRUER',60,4,'pk','RejsningTag','RejsningTag Konstruktion',50,NULL,NULL),(36,'TOPLÆGTE BESLAGSKRUER',40,5,'pk','RejsningTag','RejsningTag Konstruktion',50,NULL,NULL),(37,'TAGLÆGTER SKRUER',100,5,'pk','RejsningTag','RejsningTag Konstruktion',70,NULL,NULL),(38,'TOPLÆGTE',73,38,'stk','RejsningTag','RejsningTag Konstruktion',80,NULL,NULL),(39,'RYGSTEN',NULL,NULL,'stk','RejsningTag','RejsningTag Konstruktion',40,NULL,NULL),(40,'RYGSTENBESLAG',NULL,NULL,'stk','RejsningTag','RejsningTag Konstruktion',20,NULL,NULL),(45,'HARDIEPLANK 180X3600X8MM',180,8,'stk','','overlayMaterial',80,'Hardi.png',1.85),(46,'19X50 MM BRÆDDER FYR',19,50,'m','','overlay',4.95,'uncategorized/logo.png',0),(47,'47X100 MM SPÆRTRÆ',45,95,'m','','overlay',25.95,'uncategorized/logo.png',0),(48,'5X80 MM RUST FRI SKRUER',50,80,'pk.','','overlay',139,'uncategorized/logo.png',0),(49,'BASIC SKRUE 5,0X40MM',5,40,'pk.','','overlay',149,'uncategorized/logo.png',0),(50,'Stalddørsgreb 50x75',50,75,'stk','','overlay',209,'uncategorized/logo.png',0),(51,'T-Hængsel 390 mm',390,15,'stk','','overlay',70,'uncategorized/logo.png',0),(52,'FACADESKRUE TIL HARDIEPLANK',4,45,'pk.','','overlay',220,'uncategorized/logo.png',0),(53,'19X100 MM FYR SEKSTA ',95,15,'stk','','overlayMaterial',9.95,'overlayMaterial/fyrSeksa.png',11.36),(54,'SIBIRISK LÆRK KLINKBEKLÆDNING',142,29,'stk','','overlayMaterial',49.95,'overlayMaterial/sibirisk.png',7.64),(55,'15X95 MM FYR PANEL ROYAL',95,15,'stk','','overlayMaterial',13.95,'overlayMaterial/fyrPanel.png',11.8),(56,'21X113 RU PROFILBRÆDDER',113,21,'stk','','overlayMaterial',16.95,'overlayMaterial/ruProfil.png',9.09),(57,'STERNBRÆDT',100,19,'stk','FladtTag','FladtTag Konstruktion',139.95,NULL,NULL),(58,'SPÆR',NULL,NULL,'stk','RejsningTag',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orderdetails` (
  `orderID` int(11) NOT NULL,
  `constructionHeight` int(11) DEFAULT NULL,
  `carportWidth` int(11) DEFAULT NULL,
  `carportLength` int(11) DEFAULT NULL,
  `constructionLength` int(11) DEFAULT NULL,
  `constructionWidth` int(11) DEFAULT NULL,
  `shedDepth` int(11) DEFAULT NULL,
  `shedWidth` int(11) DEFAULT NULL,
  `shedSide` varchar(45) DEFAULT NULL,
  `overlay` varchar(45) DEFAULT NULL,
  `roofHeight` int(11) DEFAULT NULL,
  `roofLength` int(11) DEFAULT NULL,
  `roofWidth` int(11) DEFAULT NULL,
  `roofDegree` int(11) DEFAULT NULL,
  `ispitched` tinyint(4) DEFAULT NULL,
  `tilt` int(11) DEFAULT NULL,
  `wallSides` varchar(45) DEFAULT NULL,
  `overlayColor` varchar(10) DEFAULT NULL,
  `roofColor` varchar(10) DEFAULT NULL,
  `roofCover` varchar(45) DEFAULT NULL,
  UNIQUE KEY `orderID_UNIQUE` (`orderID`),
  KEY `listID_idx` (`orderID`),
  CONSTRAINT `fk_order_details` FOREIGN KEY (`orderID`) REFERENCES `orders` (`orderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='attributes of construction';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (59,2000,4200,3500,6400,4500,3000,4200,'right','SIBIRISK LÆRK KLINKBEKLÆDNING',0,6400,4500,2,0,3,'right','standard','Blåtonet','TRAPEZPLADE'),(60,2000,2400,2400,3500,2700,1200,2400,'left','SIBIRISK LÆRK KLINKBEKLÆDNING',0,3500,2700,2,0,3,NULL,'standard','Opal','TRAPEZPLADE'),(61,2000,3600,3700,5900,3900,2300,3600,'right','SIBIRISK LÆRK KLINKBEKLÆDNING',0,5900,3900,25,1,0,'right;left;back','standard','Teglrød','ETERNITTAG B6'),(62,2000,4700,3700,6500,5000,2900,4700,'right','19X100 MM FYR SEKSTA ',0,6500,5000,30,1,0,'right;left;back','standard','Teglrød','ETERNITTAG B6'),(63,2000,2400,2400,3500,2700,1200,2400,'left','HARDIEPLANK 180X3600X8MM',0,3500,2700,2,0,3,'right','Hvid','Blåtonet','TRAPEZPLADE'),(64,2000,2400,2400,5200,2700,2900,1250,'right','HARDIEPLANK 180X3600X8MM',0,5200,2700,25,1,0,'right','Hvid','Rød','BETONTAGSTEN'),(65,2000,2400,4300,7000,2700,2800,1250,'right','SIBIRISK LÆRK KLINKBEKLÆDNING',0,7000,2700,2,0,3,'left','standard','Blåtonet','TRAPEZPLADE'),(66,2000,2400,2400,5400,2700,3100,1250,'left','HARDIEPLANK 180X3600X8MM',0,5400,2700,15,1,0,'right;back','Hvid','Rød','BETONTAGSTEN'),(67,2000,2400,2400,3500,2700,1200,2400,'left','HARDIEPLANK 180X3600X8MM',0,3500,2700,2,0,3,'right','Hvid','Blåtonet','TRAPEZPLADE'),(68,2000,3300,2400,5300,3600,3000,1700,'left','HARDIEPLANK 180X3600X8MM',0,5300,3600,15,1,0,'right','Hvid','Rød','BETONTAGSTEN'),(69,2000,3500,2400,5300,3800,3000,3500,'right','HARDIEPLANK 180X3600X8MM',0,5300,3800,15,1,0,'right;left;back','Hvid','Rød','BETONTAGSTEN'),(70,2000,2400,2400,3500,2700,1200,2400,'left','HARDIEPLANK 180X3600X8MM',0,3500,2700,2,0,3,'right;left;back','Hvid','Blåtonet','TRAPEZPLADE'),(71,2000,3900,3800,4900,4200,1200,2000,'left','15X95 MM FYR PANEL ROYAL',0,4900,4200,30,1,0,'right;back','standard','Sort','ETERNITTAG B6'),(72,2000,2400,2400,2400,2700,0,0,'',NULL,0,2400,2700,25,1,0,NULL,NULL,'Rødbrun','ETERNITTAG B6');
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `date` bigint(20) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `salePrice` double DEFAULT NULL,
  `transport` double DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='all orders that have ever been made by a customer, that it''s status changes while processing an order';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (59,5,1589830907271,'validated',10049.58,55803,1111),(60,5,1589830479703,'validated',7605.81,44029,1200),(61,5,1589973562448,'newrequest',0,0,NULL),(62,5,1590132120287,'newrequest',0,0,NULL),(63,5,1590406513788,'validated',7069.06,22123,1780),(64,5,1590406589285,'validated',7884.71,17508,1920),(65,5,1590407935392,'validated',8493.73,21000,1001),(66,5,1590408052268,'validated',9934.56,16724,100),(67,5,1590408178509,'newrequest',0,0,NULL),(68,5,1590408279350,'validated',8419.51,19000,100),(69,5,1590424267242,'newrequest',0,0,NULL),(70,5,1590425265856,'newrequest',0,0,NULL),(71,5,1590480673496,'newrequest',0,0,NULL),(72,5,1590564468236,'newrequest',0,0,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Mia de Fries','hullubalu@gmail.com','fish&chips','employee'),(2,'Carl Hansen','ca.h@gmail.com','dyrErSøde','customer'),(3,'Alexander','as@gmail.com','mia','customer'),(4,'Oscar de Fries','oscar&Pussycats@hotmail.com','mismis','customer'),(5,'customer','customer@mail.com','customer','customer'),(6,'employee','employee@mail.com','employee','employee'),(7,'Anne Cathrine Høyer Christensen','allecalle@hotmail.com','10hi','customer'),(8,'Michael Sørensen','someone@nowhere.com','huhu','customer'),(9,'Oscar de Fries','oscar&Pussycats@hotmail.com','mismis','customer'),(10,'Oscar de Fries','oscar&Pussycats@hotmail.com','mismis','customer'),(11,'Oscar de Fries','oscar&Pussycats@hotmail.com','mismis','customer'),(12,'Oscar de Fries','oscar&Pussycats@hotmail.com','mismis','customer'),(13,'Oscar de Fries','oscar&Pussycats@hotmail.com','mismis','customer'),(14,'king','king@kong.com','uhahvorhemmeligt','konge');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variations`
--

DROP TABLE IF EXISTS `variations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `variations` (
  `variationID` int(11) NOT NULL AUTO_INCREMENT,
  `materialID` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT '0',
  `color` varchar(45) DEFAULT NULL,
  `quantity` int(11) DEFAULT '0',
  PRIMARY KEY (`variationID`),
  KEY `fk_material_variants_idx` (`materialID`),
  CONSTRAINT `fk_material_variants` FOREIGN KEY (`materialID`) REFERENCES `materials` (`materialID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='contains variations of material f.eg coulors or tekstures ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variations`
--

LOCK TABLES `variations` WRITE;
/*!40000 ALTER TABLE `variations` DISABLE KEYS */;
INSERT INTO `variations` VALUES (1,1,300,NULL,1),(2,1,360,NULL,1),(3,1,420,NULL,1),(4,1,480,NULL,1),(5,1,540,NULL,1),(6,1,600,NULL,1),(7,1,660,NULL,1),(8,1,720,NULL,1),(9,2,300,NULL,1),(10,2,360,NULL,1),(11,2,420,NULL,1),(12,2,480,NULL,1),(13,2,540,NULL,1),(14,2,600,NULL,1),(15,3,300,NULL,1),(16,3,360,NULL,1),(17,3,420,NULL,1),(18,3,480,NULL,1),(19,3,540,NULL,1),(20,3,600,NULL,1),(21,6,240,NULL,1),(22,6,270,NULL,1),(23,6,300,NULL,1),(24,6,360,NULL,1),(25,7,180,NULL,1),(26,7,210,NULL,1),(27,7,240,NULL,1),(28,12,100,NULL,1),(29,13,0,NULL,1),(30,14,0,NULL,200),(31,15,0,NULL,50),(32,15,0,NULL,200),(33,15,0,NULL,250),(34,16,0,NULL,1),(35,17,0,NULL,1),(36,18,0,NULL,400),(37,19,0,NULL,300),(38,24,0,'Rød',0),(39,24,0,'Teglrød',0),(40,24,0,'Brun',0),(41,25,0,'Grå',0),(42,25,0,'Sort',0),(43,25,0,'Mokka',0),(44,25,0,'Rødbrun',0),(45,25,0,'Teglrød',0),(46,26,0,'Grå',0),(47,26,0,'Sort',0),(48,26,0,'Mokka',0),(49,26,0,'Rødbrun',0),(50,26,0,'Teglrød',0),(51,26,0,'FlammeRød',0),(52,27,480,NULL,2),(53,28,600,NULL,2),(54,29,480,NULL,2),(55,30,540,NULL,3),(56,32,0,NULL,2),(57,33,0,NULL,2),(58,34,0,NULL,8),(59,35,0,NULL,1),(60,36,0,NULL,1),(61,37,0,NULL,2),(62,38,420,NULL,2),(63,39,0,NULL,21),(64,40,0,NULL,21),(65,45,0,'Hvid',0),(66,45,0,'Sort',0),(67,45,0,'Masgrøn',0),(68,45,0,'Lysbrun',0),(73,47,240,NULL,0),(80,52,0,'Hvid',200),(81,52,0,'Sort',200),(82,52,0,'Masgrøn',200),(83,52,0,'Lysbrun',200),(84,49,0,NULL,400),(85,48,0,NULL,250),(86,8,300,'Blåtonet',1),(87,9,300,'Opal',1),(88,10,300,'Klar',1),(89,8,600,'Blåtonet',1),(90,9,600,'Opal',1),(91,10,600,'Klar',1),(92,57,360,NULL,1),(93,57,540,NULL,1),(94,23,100,NULL,1),(95,11,0,NULL,200),(96,47,120,NULL,0),(97,47,150,NULL,0),(98,47,180,NULL,0),(99,47,210,NULL,0),(100,47,270,NULL,0),(101,47,300,NULL,0),(102,53,0,'standard',0),(103,54,0,'standard',0),(104,55,0,'standard',0),(105,56,0,'standard',0),(106,46,120,NULL,0),(107,46,150,NULL,0),(108,46,180,NULL,0),(109,46,210,NULL,0),(110,46,240,NULL,0),(111,46,270,NULL,0),(112,46,300,NULL,0),(113,46,330,NULL,0),(114,46,360,NULL,0),(115,46,390,NULL,0),(116,46,420,NULL,0);
/*!40000 ALTER TABLE `variations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-27 11:14:44

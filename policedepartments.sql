-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: policedepartments
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `case`
--

DROP TABLE IF EXISTS `case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `case` (
  `case_no` int NOT NULL,
  `crime_name` varchar(45) NOT NULL,
  `department_dep_no` int NOT NULL,
  `officer_officer_id` int NOT NULL,
  PRIMARY KEY (`case_no`),
  KEY `fk_case_department1_idx` (`department_dep_no`),
  KEY `fk_case_officer1_idx` (`officer_officer_id`),
  CONSTRAINT `fk_case_department1` FOREIGN KEY (`department_dep_no`) REFERENCES `department` (`dep_no`),
  CONSTRAINT `fk_case_officer1` FOREIGN KEY (`officer_officer_id`) REFERENCES `officer` (`officer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case`
--

LOCK TABLES `case` WRITE;
/*!40000 ALTER TABLE `case` DISABLE KEYS */;
INSERT INTO `case` VALUES (1,'Drug Dealing',630,317168),(2,'Murder',620,657812),(3,'Criminal Organization',610,616545),(4,'DDOS Attack',640,862791),(5,'Redlight Violation',650,788811),(6,'Transporting Explosive Bombs',660,112333),(7,'Street Fight',670,987654);
/*!40000 ALTER TABLE `case` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criminal`
--

DROP TABLE IF EXISTS `criminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criminal` (
  `criminal_id` int NOT NULL,
  `criminal_name` varchar(45) NOT NULL,
  `criminal_last_name` varchar(45) NOT NULL,
  `officer_officer_id` int NOT NULL,
  `case_case_no` int NOT NULL,
  PRIMARY KEY (`criminal_id`),
  KEY `fk_criminal_officer_idx` (`officer_officer_id`),
  KEY `fk_criminal_case1_idx` (`case_case_no`),
  CONSTRAINT `fk_criminal_case1` FOREIGN KEY (`case_case_no`) REFERENCES `case` (`case_no`),
  CONSTRAINT `fk_criminal_officer` FOREIGN KEY (`officer_officer_id`) REFERENCES `officer` (`officer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criminal`
--

LOCK TABLES `criminal` WRITE;
/*!40000 ALTER TABLE `criminal` DISABLE KEYS */;
INSERT INTO `criminal` VALUES (303132,'Taha','Umut',317168,1),(707172,'Başar','Koyuncu',657812,2),(789009,'Yiğit','Eryiğit',788811,5),(818283,'Kemal','Kaya',616545,3),(909192,'Mohammad','Binnasib',112333,6),(998812,'İzzet','Şakrak',862791,4),(3987444,'Can','Çankaya',987654,7);
/*!40000 ALTER TABLE `criminal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `dep_no` int NOT NULL,
  `dep_name` varchar(45) NOT NULL,
  `dep_location` varchar(45) NOT NULL,
  `manager_id` int NOT NULL,
  PRIMARY KEY (`dep_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (610,'Organized Crime Unit','Atatürk Boulevard No:1',150696),(620,'Homiced Unit','Mevlana Boulevard Konya Road',567859),(630,'Narcotics Unit','Gazi D200 Yenimahalle',385877),(640,'Cyber Crimes Unit','Varlık, Yardım Street No:1',583011),(650,'Traffic Unit','Dumlupınar Street No:1',583011),(660,'Counter-Terrorism Unit','Anadolu Boulevard, Çitosan No:18',999876),(670,'Public Safety Unit','Celal Esat Arseven Street,No:345',987654);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evidence`
--

DROP TABLE IF EXISTS `evidence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evidence` (
  `evidence_no` int NOT NULL,
  `evidence_desc` varchar(100) NOT NULL,
  `case_case_no` int NOT NULL,
  PRIMARY KEY (`evidence_no`),
  KEY `fk_evidence_case1_idx` (`case_case_no`),
  CONSTRAINT `fk_evidence_case1` FOREIGN KEY (`case_case_no`) REFERENCES `case` (`case_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evidence`
--

LOCK TABLES `evidence` WRITE;
/*!40000 ALTER TABLE `evidence` DISABLE KEYS */;
INSERT INTO `evidence` VALUES (61,'Camera Record and delation',1),(62,'Dead body and fingerprints',2),(63,'Electronical Messages about creating/involving criminal organization',3),(64,'Various entries from different ip addresses to banking system',4),(65,'City surveilance camera records',5),(66,'Found bombs in police traffic stop',6),(67,'Public delation, witnesses and camera records',7);
/*!40000 ALTER TABLE `evidence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `officer`
--

DROP TABLE IF EXISTS `officer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `officer` (
  `officer_id` int NOT NULL,
  `officer_rank` varchar(45) NOT NULL,
  `officer_first_name` varchar(45) NOT NULL,
  `officer_last_name` varchar(45) NOT NULL,
  `officer_birth_date` int NOT NULL,
  `officer_phone_num` int NOT NULL,
  `department_dep_no` int NOT NULL,
  PRIMARY KEY (`officer_id`),
  KEY `fk_officer_department1_idx` (`department_dep_no`),
  CONSTRAINT `fk_officer_department1` FOREIGN KEY (`department_dep_no`) REFERENCES `department` (`dep_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `officer`
--

LOCK TABLES `officer` WRITE;
/*!40000 ALTER TABLE `officer` DISABLE KEYS */;
INSERT INTO `officer` VALUES (111399,'Unit Manager','Tahsin','Yılmaz',1986,538383,670),(112333,'Sergeant','Abdullah','Kalem',1971,546546,660),(124002,'Unit Manager','Serdar','Çolak',1971,570081,630),(145798,'Officer','Engin','Balkan',1980,545131,610),(150696,'Unit Manager','Rıza','Soylu',1960,535321,610),(178999,'Assistant Chief','Osman','Göğebakan',1987,199981,630),(221333,'Officer','Berk','Kazım',1995,525361,640),(317168,'Chief','Kemal','Uzun',1986,677716,630),(336006,'Lieutenant','Mehmet','Kıraç',1981,511599,660),(385877,'Unit Manager','Atıl','Samancıoğlu',1987,555777,640),(567959,'Unit Manager','Murat','Ateş',1976,565167,620),(583011,'Unit Manager','Süleyman','Aşık',1972,544687,650),(616545,'Chief','Ömer','Kavas',1975,555123,610),(657812,'Chief','Hüsnü','Çolak',1974,545168,620),(670863,'Officer','Selma','Koca',1981,545812,620),(687654,'Officer','Serkan','Kocak',1998,199687,670),(714417,'Officer','Yağız','Kasap',1990,160821,660),(731618,'Senior Officer','Pelin','Özsoy',1990,535535,640),(771851,'Officer','Şevket','Çoruh',1977,555321,610),(788811,'Chief','Talat','Duman',1977,555888,650),(795801,'Officer','Arif','Işık',1990,555786,620),(816911,'Officer','Berk','Tabut',1988,711123,630),(819723,'Senior Officer','Metin','Günbaz',1979,556916,620),(862791,'Chief','Asuman','Kara',1988,545919,640),(916916,'Officer','Mahsun','Kaya',1991,546461,650),(987654,'Chief','Behzat','Çağlar',1989,545505,670),(999876,'Unit Manager','Cem','Kurt',1970,516516,660);
/*!40000 ALTER TABLE `officer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17 18:25:41

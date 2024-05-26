-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_dormitory
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(10) NOT NULL COMMENT '姓名',
  `password` varchar(10) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='超级管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','123456');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dormitory`
--

DROP TABLE IF EXISTS `dormitory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dormitory` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '楼号',
  `fnum` int NOT NULL COMMENT '楼层数',
  `location` varchar(20) NOT NULL COMMENT '位置',
  `mid` varchar(10) NOT NULL COMMENT '管理员编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_d` (`mid`),
  CONSTRAINT `fk_d` FOREIGN KEY (`mid`) REFERENCES `master` (`id`),
  CONSTRAINT `dormitory_chk_1` CHECK ((`fnum` between 4 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宿舍楼表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dormitory`
--

LOCK TABLES `dormitory` WRITE;
/*!40000 ALTER TABLE `dormitory` DISABLE KEYS */;
INSERT INTO `dormitory` VALUES (1,4,'aaaa','1190'),(2,5,'222','1190'),(3,4,'b区','1192');
/*!40000 ALTER TABLE `dormitory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master`
--

DROP TABLE IF EXISTS `master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `master` (
  `id` varchar(10) NOT NULL COMMENT '管理员编号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `phone` varchar(11) NOT NULL DEFAULT '123456' COMMENT '电话号码',
  `gender` tinyint unsigned NOT NULL COMMENT '性别，男1，女2',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宿管员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master`
--

LOCK TABLES `master` WRITE;
/*!40000 ALTER TABLE `master` DISABLE KEYS */;
INSERT INTO `master` VALUES ('1190','张三','123',1),('1192','哈哈','123',2);
/*!40000 ALTER TABLE `master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reside`
--

DROP TABLE IF EXISTS `reside`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reside` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '居住编号',
  `sid` varchar(10) NOT NULL COMMENT '学号',
  `stname` varchar(20) NOT NULL COMMENT '学生姓名',
  `rid` varchar(10) NOT NULL COMMENT '宿舍号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `sid` (`sid`),
  KEY `fk_rd` (`rid`),
  CONSTRAINT `fk_rd` FOREIGN KEY (`rid`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_s` FOREIGN KEY (`sid`) REFERENCES `stu` (`stno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='居住表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reside`
--

LOCK TABLES `reside` WRITE;
/*!40000 ALTER TABLE `reside` DISABLE KEYS */;
INSERT INTO `reside` VALUES (20,'2200311011','1','1-102'),(24,'2200311012','哈哈','1-102');
/*!40000 ALTER TABLE `reside` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` varchar(10) NOT NULL COMMENT '宿舍号',
  `num` int NOT NULL COMMENT '可居住人数',
  `cnum` int NOT NULL COMMENT '目前居住的人数',
  `did` int NOT NULL COMMENT '所在楼层',
  `type` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_r` (`did`),
  CONSTRAINT `fk_r` FOREIGN KEY (`did`) REFERENCES `dormitory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `room_chk_1` CHECK ((`num` between 4 and 6))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宿舍表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('1-101',5,0,1,1),('1-102',4,2,1,2),('2-101',5,0,2,2);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stu`
--

DROP TABLE IF EXISTS `stu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stu` (
  `stno` varchar(10) NOT NULL COMMENT '学号',
  `stname` varchar(10) NOT NULL COMMENT '姓名',
  `gender` tinyint unsigned NOT NULL COMMENT '性别，男1，女2',
  `grad` int NOT NULL COMMENT '年级',
  `major` varchar(25) NOT NULL COMMENT '专业',
  `hometown` varchar(30) DEFAULT NULL COMMENT '来着哪里',
  `birthday` date NOT NULL,
  `isReside` tinyint unsigned NOT NULL COMMENT '是否入住',
  PRIMARY KEY (`stno`),
  UNIQUE KEY `stno` (`stno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stu`
--

LOCK TABLES `stu` WRITE;
/*!40000 ALTER TABLE `stu` DISABLE KEYS */;
INSERT INTO `stu` VALUES ('2200311011','嘿嘿',1,2022,'软工','广西','1999-12-29',1),('2200311012','哈哈',2,2022,'计科','广西','1999-12-29',1),('2200311013','哦哦',2,2022,'计了','广西','1999-12-27',0),('2200311014','哦豁',2,2022,'软工','广西','1999-12-27',0),('2200311015','或哈',1,2022,'软工','广西','1999-12-28',0),('2200311016','嘻嘻哈',1,2022,'计科','广西','1999-12-28',0),('2200311017','嘻哈或',1,2022,'软工','广西','1999-12-28',0);
/*!40000 ALTER TABLE `stu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-26 19:54:26

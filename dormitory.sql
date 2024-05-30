-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dormitory
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
  `id` int NOT NULL COMMENT '楼号',
  `fnums` int NOT NULL COMMENT '楼层数',
  `location` varchar(20) NOT NULL COMMENT '位置',
  `mid` varchar(10) NOT NULL COMMENT '管理员编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_d` (`mid`),
  CONSTRAINT `fk_d` FOREIGN KEY (`mid`) REFERENCES `master` (`id`),
  CONSTRAINT `dormitory_chk_1` CHECK ((`fnums` between 1 and 6))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宿舍楼表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dormitory`
--

LOCK TABLES `dormitory` WRITE;
/*!40000 ALTER TABLE `dormitory` DISABLE KEYS */;
INSERT INTO `dormitory` VALUES (1,4,'a区','1190'),(2,5,'a区','1191'),(3,4,'b区','1192');
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
INSERT INTO `master` VALUES ('1190','张三','123',1),('1191','欣欣','123',2),('1192','哈哈','123',2);
/*!40000 ALTER TABLE `master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reside`
--

DROP TABLE IF EXISTS `reside`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reside` (
  `sid` varchar(10) NOT NULL COMMENT '学号',
  `rid` int NOT NULL COMMENT '宿舍号',
  `did` int NOT NULL COMMENT '所在楼号',
  PRIMARY KEY (`sid`,`rid`,`did`),
  UNIQUE KEY `sid` (`sid`),
  KEY `fk_rid` (`rid`),
  KEY `fk_rdid` (`did`),
  CONSTRAINT `fk_rdid` FOREIGN KEY (`did`) REFERENCES `room` (`did`) ON UPDATE CASCADE,
  CONSTRAINT `fk_rid` FOREIGN KEY (`rid`) REFERENCES `room` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_s` FOREIGN KEY (`sid`) REFERENCES `stu` (`stno`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='住宿';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reside`
--

LOCK TABLES `reside` WRITE;
/*!40000 ALTER TABLE `reside` DISABLE KEYS */;
/*!40000 ALTER TABLE `reside` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL COMMENT '宿舍号',
  `did` int NOT NULL COMMENT '所在楼号',
  `num` int NOT NULL COMMENT '可居住人数',
  `cnum` int NOT NULL COMMENT '目前居住的人数',
  `type` int NOT NULL COMMENT '寝室类型',
  PRIMARY KEY (`id`,`did`),
  KEY `fk_r` (`did`),
  CONSTRAINT `fk_r` FOREIGN KEY (`did`) REFERENCES `dormitory` (`id`),
  CONSTRAINT `room_chk_1` CHECK ((`id` between 101 and 610)),
  CONSTRAINT `room_chk_2` CHECK ((`did` between 1 and 20)),
  CONSTRAINT `room_chk_3` CHECK ((`num` between 4 and 8))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宿舍表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (101,1,6,0,1),(102,1,4,0,1),(201,2,6,0,1),(202,2,6,0,1),(301,3,6,0,2),(302,3,6,0,2),(401,3,6,0,2);
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
INSERT INTO `stu` VALUES ('2200311020','tom0',1,2022,'计科','广西','2003-12-29',0),('2200311022','tom1',1,2022,'软工','广西','2003-12-29',0),('2200311023','tom2',2,2022,'计了','广西','2003-12-29',0);
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

-- Dump completed on 2024-05-30 19:25:12

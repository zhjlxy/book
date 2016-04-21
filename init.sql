/*
SQLyog v10.2 
MySQL - 5.6.20 : Database - shbooktradingplatform
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shbooktradingplatform` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shbooktradingplatform`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `desc` varchar(500) NOT NULL,
  `price` double DEFAULT NULL,
  `picture` varchar(100) NOT NULL,
  `sell_userId` int(11) NOT NULL,
  `new_status` int(1) NOT NULL,
  `sell_status` varchar(10) NOT NULL,
  `Cts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Uts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`name`,`author`,`desc`,`price`,`picture`,`sell_userId`,`new_status`,`sell_status`,`Cts`,`Uts`) values (1,'java','java','java',2.23,'/001.png',1,9,'1','2016-04-17 01:27:34','2016-04-20 00:39:13'),(2,'java','java','java',10.2,'/default.svg',1,9,'1','2016-04-17 01:27:34','2016-04-20 00:31:49'),(3,'java','java','java',210,'/002.png',1,9,'1','2016-04-17 01:27:34','2016-04-20 00:39:24'),(4,'java','java','java',26,'/default.svg',1,9,'1','2016-04-17 01:27:34','2016-04-20 00:31:52'),(5,'java','java','java',2.2,'/001.png',1,9,'1','2016-04-17 01:27:34','2016-04-20 00:39:29'),(6,'java','java','java',56,'/default.svg',1,9,'1','2016-04-17 01:27:34','2016-04-20 00:31:55'),(7,'java','java','java',55,'/002.png',1,9,'1','2016-04-17 01:27:34','2016-04-20 00:39:33'),(8,'java','java','java',2.23,'/001.png',1,9,'1','2016-04-17 01:27:34','2016-04-20 00:39:41'),(9,'java','java','java',54,'/002.png',1,9,'1','2016-04-17 01:27:34','2016-04-20 00:39:36'),(10,'java','java','java',2.23,'/pic/001.java',1,9,'1','2016-04-17 01:27:34','2016-04-17 01:27:48');


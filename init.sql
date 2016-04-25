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
  `book_name` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `book_desc` varchar(500) NOT NULL,
  `price` double DEFAULT NULL,
  `picture` varchar(100) NOT NULL,
  `sell_userId` int(11) NOT NULL,
  `new_status` int(1) NOT NULL,
  `sell_status` varchar(10) NOT NULL,
  `Cts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Uts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`book_name`,`author`,`book_desc`,`price`,`picture`,`sell_userId`,`new_status`,`sell_status`,`Cts`,`Uts`) values (1,'java','java','java',6.5,'/002.png',123,7,'in','2016-04-24 21:13:28','2016-04-24 21:13:49'),(2,'java','java','java',10.2,'/default.svg',1,9,'in','2016-04-17 01:27:34','2016-04-24 12:41:59'),(3,'java','java','java',210,'/002.png',1,9,'in','2016-04-17 01:27:34','2016-04-24 12:42:00'),(4,'java','java','java',26,'/default.svg',1,9,'in','2016-04-17 01:27:34','2016-04-24 12:42:01'),(5,'java','java','java',2.2,'/001.png',1,9,'in','2016-04-17 01:27:34','2016-04-24 12:42:03'),(6,'java','java','java',56,'/default.svg',1,9,'in','2016-04-17 01:27:34','2016-04-24 12:42:04'),(7,'java','java','java',55,'/002.png',1,9,'in','2016-04-17 01:27:34','2016-04-24 12:42:05'),(8,'java','java','java',2.23,'/001.png',1,9,'in','2016-04-17 01:27:34','2016-04-24 12:42:07'),(9,'java','java','java',54,'/002.png',1,9,'in','2016-04-17 01:27:34','2016-04-24 12:42:08'),(10,'java','java','java',2.23,'/pic/001.java',1,9,'in','2016-04-17 01:27:34','2016-04-24 12:42:12'),(11,'test','test','test',12,'/001.png',0,7,'in','2016-04-24 13:03:06','2016-04-24 13:03:06'),(12,'test1','test','teate',5,'/002.png',123,5,'in','2016-04-24 13:09:05','2016-04-24 15:37:39'),(13,'java','java','java',8,'/002.png',123,9,'in','2016-04-24 20:43:51','2016-04-24 20:43:51'),(14,'test','tes','dasd',2,'/002.png',123,5,'in','2016-04-24 21:02:04','2016-04-24 21:02:04');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


-- user表
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(10) NOT NULL,
  `uts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


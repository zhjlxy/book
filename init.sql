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

REATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `book_desc` varchar(500) NOT NULL,
  `price` double DEFAULT NULL,
  `picture` varchar(100) NOT NULL,
  `sell_userId` int(11) NOT NULL,
  `new_status` int(1) NOT NULL,
  `type_id` int(11) NOT NULL,
  `sell_status` varchar(10) NOT NULL,
  `Cts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Uts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','6.5','/002.png','123','7','1','in','2016-04-24 21:13:28','2016-04-27 17:38:43');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','10.2','/default.svg','1','9','2','in','2016-04-17 01:27:34','2016-04-27 17:38:45');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','210','/002.png','1','9','1','in','2016-04-17 01:27:34','2016-04-27 17:38:46');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','26','/default.svg','1','9','3','in','2016-04-17 01:27:34','2016-04-27 17:38:48');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','2.2','/001.png','1','9','4','in','2016-04-17 01:27:34','2016-04-27 17:38:49');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','56','/default.svg','1','9','1','in','2016-04-17 01:27:34','2016-04-27 17:38:50');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','55','/002.png','1','9','5','in','2016-04-17 01:27:34','2016-04-27 17:38:54');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','2.23','/001.png','1','9','4','in','2016-04-17 01:27:34','2016-04-27 17:38:51');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','54','/002.png','1','9','6','in','2016-04-17 01:27:34','2016-04-27 17:38:57');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','2.23','/pic/001.java','1','9','1','in','2016-04-17 01:27:34','2016-04-27 17:38:59');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('test','test','test','12','/001.png','0','7','2','in','2016-04-24 13:03:06','2016-04-27 17:39:00');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('test1','test','teate','5','/002.png','123','5','3','in','2016-04-24 13:09:05','2016-04-27 17:39:01');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('java','java','java','8','/002.png','123','9','4','in','2016-04-24 20:43:51','2016-04-27 17:39:02');
insert into `book` (`book_name`, `author`, `book_desc`, `price`, `picture`, `sell_userId`, `new_status`, `type_id`, `sell_status`, `Cts`, `Uts`) values('test','tes','dasd','2','/002.png','123','5','5','in','2016-04-24 21:02:04','2016-04-27 17:39:05');


-- user表
DROP TABLE IF EXISTS `user`;
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

-- 类型表
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type` (
  `id` int(11) NOT NULL,
  `desc` varchar(100) NOT NULL,
  `cts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into `book_type` (`desc`, `cts`) values('百科','2016-04-27 17:35:20');
insert into `book_type` (`desc`, `cts`) values('社会','2016-04-27 17:36:20');
insert into `book_type` (`desc`, `cts`) values('科学','2016-04-27 17:36:25');
insert into `book_type` (`desc`, `cts`) values('文学','2016-04-27 17:36:31');
insert into `book_type` (`desc`, `cts`) values('历史','2016-04-27 17:36:39');
insert into `book_type` (`desc`, `cts`) values('外国','2016-04-27 17:36:51');




/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost
 Source Database       : yjj

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : utf-8

 Date: 12/07/2015 15:09:52 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_advert`
-- ----------------------------
DROP TABLE IF EXISTS `t_advert`;
CREATE TABLE `t_advert` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `content` mediumtext,
  `title` varchar(255) DEFAULT NULL,
  `pubUnit` varchar(255) DEFAULT NULL,
  `readNum` int(10) DEFAULT NULL,
  `publish` char(1) DEFAULT NULL,
  `idx` int(10) DEFAULT NULL,
  `picUrl` varchar(255) DEFAULT NULL,
  `picPath` varchar(255) DEFAULT NULL,
  `picName` varchar(255) DEFAULT NULL,
  `outUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(10) DEFAULT NULL,
  `keyValue` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_feature`
-- ----------------------------
DROP TABLE IF EXISTS `t_feature`;
CREATE TABLE `t_feature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_friendlink`
-- ----------------------------
DROP TABLE IF EXISTS `t_friendlink`;
CREATE TABLE `t_friendlink` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `outUrl` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `publish` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_news`
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `content` mediumtext,
  `title` varchar(255) DEFAULT NULL,
  `pubUnit` varchar(255) DEFAULT NULL,
  `readNum` int(10) DEFAULT NULL,
  `publish` char(1) DEFAULT NULL,
  `idx` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `content` mediumtext,
  `title` varchar(255) DEFAULT NULL,
  `pubUnit` varchar(255) DEFAULT NULL,
  `readNum` int(10) DEFAULT NULL,
  `publish` char(1) DEFAULT NULL,
  `idx` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_org`
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `orgNo` varchar(255) DEFAULT NULL,
  `picName` varchar(255) DEFAULT NULL,
  `picPath` varchar(255) DEFAULT NULL,
  `picUrl` varchar(255) DEFAULT NULL,
  `description` mediumtext,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `integrity` varchar(255) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `dbr` varchar(255) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  `xkzNo` varchar(255) DEFAULT NULL,
  `fzjg` varchar(255) DEFAULT NULL,
  `fzDate` date DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `cls` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`) USING BTREE,
  KEY `integrity` (`integrity`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_orgquestion`
-- ----------------------------
DROP TABLE IF EXISTS `t_orgquestion`;
CREATE TABLE `t_orgquestion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `surveyId` bigint(20) DEFAULT NULL,
  `questionId` bigint(20) DEFAULT NULL,
  `orgId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `surveyName` varchar(255) DEFAULT NULL,
  `questionName` varchar(255) DEFAULT NULL,
  `questionRule` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `surveyId` (`surveyId`) USING BTREE,
  KEY `orgId` (`orgId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_orgsurveyaudit`
-- ----------------------------
DROP TABLE IF EXISTS `t_orgsurveyaudit`;
CREATE TABLE `t_orgsurveyaudit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orgId` bigint(20) DEFAULT NULL,
  `surveyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `surveyName` varchar(255) DEFAULT NULL,
  `orgName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orgId` (`orgId`) USING BTREE,
  KEY `surveyId` (`surveyId`) USING BTREE,
  KEY `userId` (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_question`
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `rule` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` tinytext,
  `displayName` varchar(255) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_rolefeature`
-- ----------------------------
DROP TABLE IF EXISTS `t_rolefeature`;
CREATE TABLE `t_rolefeature` (
  `roleId` bigint(20) DEFAULT NULL,
  `featureId` bigint(20) DEFAULT NULL,
  KEY `roleId` (`roleId`) USING BTREE,
  KEY `featureId` (`featureId`) USING BTREE,
  CONSTRAINT `t_rolefeature_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`),
  CONSTRAINT `t_rolefeature_ibfk_2` FOREIGN KEY (`featureId`) REFERENCES `t_feature` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_survey`
-- ----------------------------
DROP TABLE IF EXISTS `t_survey`;
CREATE TABLE `t_survey` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `verify` int(10) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `verifyUserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_surveyaudit`
-- ----------------------------
DROP TABLE IF EXISTS `t_surveyaudit`;
CREATE TABLE `t_surveyaudit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `surveyId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `verify` int(10) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  `verifyUserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `surveyId` (`surveyId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_surveyorg`
-- ----------------------------
DROP TABLE IF EXISTS `t_surveyorg`;
CREATE TABLE `t_surveyorg` (
  `surveyId` bigint(20) DEFAULT NULL,
  `orgId` bigint(20) DEFAULT NULL,
  KEY `surveyId` (`surveyId`) USING BTREE,
  KEY `orgId` (`orgId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_surveyquestion`
-- ----------------------------
DROP TABLE IF EXISTS `t_surveyquestion`;
CREATE TABLE `t_surveyquestion` (
  `surveyId` bigint(20) DEFAULT NULL,
  `questionId` bigint(20) DEFAULT NULL,
  KEY `surveyId` (`surveyId`) USING BTREE,
  KEY `questionId` (`questionId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `accountExpired` char(1) DEFAULT NULL,
  `accountLocked` char(1) DEFAULT NULL,
  `credentialsExpired` char(1) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `credits` double DEFAULT NULL,
  `portraitName` varchar(255) DEFAULT NULL,
  `portraitUrl` varchar(255) DEFAULT NULL,
  `portraitPath` varchar(255) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`) USING BTREE,
  KEY `roleId` (`roleId`) USING BTREE,
  KEY `mobile` (`mobile`) USING BTREE,
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_userrole`
-- ----------------------------
DROP TABLE IF EXISTS `t_userrole`;
CREATE TABLE `t_userrole` (
  `userId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  KEY `roleId` (`roleId`) USING BTREE,
  KEY `userId` (`userId`) USING BTREE,
  CONSTRAINT `t_userrole_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_userrole_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

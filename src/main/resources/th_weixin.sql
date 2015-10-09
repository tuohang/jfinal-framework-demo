/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : th_weixin

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-10-09 23:12:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `id` varchar(32) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` varchar(32) NOT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `menuKey` varchar(30) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `respType` varchar(5) DEFAULT NULL COMMENT '响应类型如果为1表示去content来响应',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', null, '学习网站', null, 'www.baidu.com', null, 'view', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `birth` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('5fcf890171ed4e959005bda3f3062efb', 'tuohangxx', '1', '拓航xx', '20', null, '2015-10-04 11:11:13.863000');
INSERT INTO `t_user` VALUES ('7c1ffe5021924a6e83da6a1322616556', 'tuohang', '1', '拓航', '20', '1', '2015-10-04 01:13:50.024000');
INSERT INTO `t_user` VALUES ('b7c0ec25a89946088b1fd7e43081438d', 'tuohang', '1', '拓航', '20', '1', '2015-10-07 08:21:37.242000');
INSERT INTO `t_user` VALUES ('e2429ea48e38427f9421acd3b3d3715a', 'tuohangxx', '1', '拓航xx', '20', null, '2015-10-04 11:37:30.925000');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `birth` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1f53645fb52345438b6ca59d50da19f1', 'tuohang', '1', '拓航', '20', '1', '2015-10-05 16:10:36.501000');
INSERT INTO `user` VALUES ('d147b26031b94733b93f2e914eb1218e', 'tuohangxx', '1', '拓航xx', '20', null, '2015-10-05 16:09:50.807000');

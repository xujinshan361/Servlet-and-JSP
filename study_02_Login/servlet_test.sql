/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : servlet_test

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-02-01 22:01:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(100) NOT NULL,
  `pwd` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'zhangsan', '123');
INSERT INTO `t_user` VALUES ('2', 'lisi', '456');

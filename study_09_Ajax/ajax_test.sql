/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : ajax_test

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-02-14 13:50:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(4) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `price` double DEFAULT NULL,
  `loc` varchar(200) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '韩信', '1000', '打野', '打野贼流');
INSERT INTO `users` VALUES ('2', '后裔', '200', '射手', '很牛逼');
INSERT INTO `users` VALUES ('3', 'wangwu', '1001', '中路', null);

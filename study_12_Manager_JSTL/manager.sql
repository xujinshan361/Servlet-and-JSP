/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : manager

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-02-05 14:58:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `sex` char(2) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '张三', '123', '1', '18', '2010-06-16');
INSERT INTO `t_user` VALUES ('2', '李四', '456', '0', '18', '2010-02-17');
INSERT INTO `t_user` VALUES ('3', 'lisi', '123', '1', '18', null);
INSERT INTO `t_user` VALUES ('4', 'qq', '111', '1', '13', '2020-02-04');
INSERT INTO `t_user` VALUES ('5', '1', '111', '1', '1', '2020-02-05');

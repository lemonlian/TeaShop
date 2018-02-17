/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : teashop

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-02-17 19:20:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tea
-- ----------------------------
DROP TABLE IF EXISTS `tea`;
CREATE TABLE `tea` (
  `tea_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '茶品的ID',
  `tea_name` varchar(255) NOT NULL COMMENT '茶的名字',
  `tea_price` double(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '茶的价格',
  `tea_photo` varchar(255) DEFAULT NULL COMMENT '茶的图片',
  `tea_state` int(255) NOT NULL DEFAULT '2' COMMENT '茶的状态（1-表示正常，2-表示下架）',
  `tea_intro` varchar(255) DEFAULT NULL COMMENT '茶的简介',
  `tea_num` int(11) unsigned DEFAULT '0' COMMENT '商品的份数',
  PRIMARY KEY (`tea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tea
-- ----------------------------
INSERT INTO `tea` VALUES ('3', '2', '2.00', 'http://localhost:8080/images/-3.jpg', '1', '2', '0');
INSERT INTO `tea` VALUES ('4', '茶2', '50.00', 'http://localhost:8080/images/11.jpg', '1', '茶2', '8');
INSERT INTO `tea` VALUES ('7', '茶3', '50.00', 'http://localhost:8080/images/12.jpg', '1', '茶3', '10');
INSERT INTO `tea` VALUES ('8', 'ss', '50.00', 'http://localhost:8080/images/-3.jpg', '1', 's', '10');
INSERT INTO `tea` VALUES ('11', '1', '10.00', 'http://localhost:8080/images/-2.jpg', '2', '1', '10');
INSERT INTO `tea` VALUES ('12', 'test', '100.00', 'http://localhost:8080/images/photo5.jpg', '2', '1', '1');

-- ----------------------------
-- Table structure for teaorder
-- ----------------------------
DROP TABLE IF EXISTS `teaorder`;
CREATE TABLE `teaorder` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `o_user_id` int(11) NOT NULL COMMENT '用户ID',
  `o_tea_id` int(11) NOT NULL COMMENT '茶ID',
  `order_state` int(11) NOT NULL DEFAULT '0' COMMENT '订单状态(0-未处理，1-已处理)',
  `tea_buy_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '订购的茶的份数',
  `order_price` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单总价',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teaorder
-- ----------------------------
INSERT INTO `teaorder` VALUES ('3', '2', '3', '1', '5', '600.00');
INSERT INTO `teaorder` VALUES ('5', '3', '3', '1', '1', '100.00');
INSERT INTO `teaorder` VALUES ('6', '2', '3', '1', '1', '100.00');
INSERT INTO `teaorder` VALUES ('8', '2', '3', '1', '1', '100.00');
INSERT INTO `teaorder` VALUES ('9', '3', '7', '1', '1', '10.00');
INSERT INTO `teaorder` VALUES ('10', '3', '7', '1', '1', '10.00');
INSERT INTO `teaorder` VALUES ('12', '3', '3', '1', '1', '2.00');
INSERT INTO `teaorder` VALUES ('13', '3', '4', '1', '2', '100.00');
INSERT INTO `teaorder` VALUES ('14', '7', '3', '1', '1', '2.00');
INSERT INTO `teaorder` VALUES ('15', '3', '3', '0', '1', '2.00');
INSERT INTO `teaorder` VALUES ('16', '3', '4', '0', '3', '150.00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) NOT NULL COMMENT '用户姓名',
  `user_password` varchar(255) NOT NULL COMMENT '用户密码',
  `user_balance` double unsigned NOT NULL DEFAULT '0' COMMENT '余额',
  `user_state` int(11) NOT NULL DEFAULT '0' COMMENT '用户状态(0-表示正常用户，1-表示管理员)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '0', '1');
INSERT INTO `user` VALUES ('2', 'text', 'text', '900', '0');
INSERT INTO `user` VALUES ('3', 'text1', 'text1', '78', '0');
INSERT INTO `user` VALUES ('5', 'text4', 'text4', '0', '0');
INSERT INTO `user` VALUES ('6', 'textadd', 'textadd', '0', '0');
INSERT INTO `user` VALUES ('7', '测试注册', '123', '8', '0');
SET FOREIGN_KEY_CHECKS=1;

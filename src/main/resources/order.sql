/*
 Navicat Premium Data Transfer

 Source Server         : Huppert
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : order

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 30/05/2020 17:03:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ssn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES (1, '张三', NULL);
INSERT INTO `customers` VALUES (2, '李四', NULL);
INSERT INTO `customers` VALUES (3, '王五', NULL);
INSERT INTO `customers` VALUES (4, '赵六', NULL);

-- ----------------------------
-- Table structure for menu_dishes
-- ----------------------------
DROP TABLE IF EXISTS `menu_dishes`;
CREATE TABLE `menu_dishes`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` int(11) NULL DEFAULT NULL,
  `restaurant_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_dishes
-- ----------------------------
INSERT INTO `menu_dishes` VALUES (1, '青椒皮蛋', 12, 1);
INSERT INTO `menu_dishes` VALUES (2, '回锅肉', 25, 1);
INSERT INTO `menu_dishes` VALUES (3, '炒时蔬', 10, 1);
INSERT INTO `menu_dishes` VALUES (4, '粉蒸排骨', 30, 1);
INSERT INTO `menu_dishes` VALUES (5, '酸菜鱼', 60, 1);
INSERT INTO `menu_dishes` VALUES (6, '意大利披萨', 40, 2);
INSERT INTO `menu_dishes` VALUES (7, '炸鸡', 30, 2);
INSERT INTO `menu_dishes` VALUES (8, '汉堡包', 40, 2);
INSERT INTO `menu_dishes` VALUES (9, '可乐鸡翅', 40, 2);
INSERT INTO `menu_dishes` VALUES (10, '牛肉沙拉', 60, 2);

-- ----------------------------
-- Table structure for order_dishes
-- ----------------------------
DROP TABLE IF EXISTS `order_dishes`;
CREATE TABLE `order_dishes`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_dish_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_dishes
-- ----------------------------
INSERT INTO `order_dishes` VALUES (1, 1, 1, 1);
INSERT INTO `order_dishes` VALUES (2, 2, 1, 2);
INSERT INTO `order_dishes` VALUES (3, 3, 1, 3);
INSERT INTO `order_dishes` VALUES (4, 6, 2, 1);
INSERT INTO `order_dishes` VALUES (5, 7, 2, 1);
INSERT INTO `order_dishes` VALUES (6, 8, 2, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) NULL DEFAULT NULL,
  `customer_id` bigint(20) NULL DEFAULT NULL,
  `restarunt_id` bigint(20) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '2020-05-29 14:13:12.000000', 1, 1, 3);
INSERT INTO `orders` VALUES (2, '2020-05-29 14:26:13.000000', 2, 2, 0);

-- ----------------------------
-- Table structure for restaurants
-- ----------------------------
DROP TABLE IF EXISTS `restaurants`;
CREATE TABLE `restaurants`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of restaurants
-- ----------------------------
INSERT INTO `restaurants` VALUES (1, 0, '成都市青羊区蜀源路8号');
INSERT INTO `restaurants` VALUES (2, 1, '成都市天府新区华阳街道');

SET FOREIGN_KEY_CHECKS = 1;

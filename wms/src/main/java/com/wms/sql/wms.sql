/*
 Navicat Premium Data Transfer

 Source Server         : java
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : wms

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 22/06/2023 10:43:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
                          `id` INT(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                          `name` VARCHAR(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '货名',
                          `storage_id` INT(0) NOT NULL COMMENT '仓库',
                          `goods_type_id` INT(0) NOT NULL COMMENT '分类',
                          `count` INT(0) NULL DEFAULT NULL COMMENT '数量',
                          `remark` VARCHAR(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (8, '篮球', 1, 1, 50, '');
INSERT INTO `goods` VALUES (9, 'sa', 1, 1, 30, '');`storage`
INSERT INTO `goods` VALUES (11, '篮球', 2, 2, 30, '');

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type`  (
                               `id` INT(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `name` VARCHAR(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分类名',
                               `remark` VARCHAR(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type` VALUES (1, '足球3', '');
INSERT INTO `goods_type` VALUES (2, '篮球', '');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
                         `id` INT(0) NOT NULL,
                         `menu_code` VARCHAR(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
                         `menu_name` VARCHAR(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单名字',
                         `menu_level` VARCHAR(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单级别',
                         `menu_parent_code` VARCHAR(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单的父code',
                         `menu_click` VARCHAR(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '点击触发的函数',
                         `menu_right` VARCHAR(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
                         `wms`CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                         `menu_icon` VARCHAR(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '001', '管理员管理', '1', NULL, 'AdminManage', '0', 'admin/AdminManage', 'el-icon-s-custom');
INSERT INTO `menu` VALUES (2, '002', '用户管理', '1', NULL, 'UserManage', '0,1', 'user/UserManage', 'el-icon-user-solid');
INSERT INTO `menu` VALUES (3, '003', '仓库管理', '1', NULL, 'StorageManage', '0,1', 'storage/StorageManage', 'el-icon-office-building');
INSERT INTO `menu` VALUES (4, '004', '物品分类管理', '1', NULL, 'GoodstypeManage', '0,1', 'goodstype/GoodstypeManage', 'el-icon-menu');
INSERT INTO `menu` VALUES (5, '005', '物品管理 ', '1', NULL, 'GoodsManage', '0,1,2', 'goods/GoodsManage', 'el-icon-s-management');
INSERT INTO `menu` VALUES (6, '006', '记录管理', '1', NULL, 'RecordManage', '0,1,2', 'record/RecordManage', 'el-icon-s-order');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
                           `id` INT(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `goods_id` INT(0) NOT NULL COMMENT '货品id',
                           `user_id` INT(0) NULL DEFAULT NULL COMMENT '取货人/补货人',
                           `admin_id` INT(0) NULL DEFAULT NULL COMMENT '操作人id',
                           `count` INT(0) NULL DEFAULT NULL COMMENT '数量',
                           `create_time` TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '操作时间',
                           `remark` VARCHAR(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, 2, 1, 1, 60, '2023-06-29 10:45:42', NULL);
INSERT INTO `record` VALUES (2, 3, 2, 1, 30, '2023-06-17 11:12:48', '');
INSERT INTO `record` VALUES (3, 6, 2, 1, 10, '2023-06-17 11:12:48', '');
INSERT INTO `record` VALUES (4, 6, 2, 1, 10, '2023-06-17 11:12:48', '');
INSERT INTO `record` VALUES (5, 3, 2, 1, 10, '2023-06-17 11:12:48', '');
INSERT INTO `record` VALUES (6, 3, 2, 1, -10, '2023-06-17 11:12:48', '');
INSERT INTO `record` VALUES (7, 3, 2, 1, 10, '2023-06-18 09:51:23', '');
INSERT INTO `record` VALUES (8, 8, 2, 1, 10, '2023-06-18 10:01:35', '');
INSERT INTO `record` VALUES (9, 9, 2, 1, 10, '2023-06-18 16:40:53', '');
INSERT INTO `record` VALUES (10, 8, 2, 1, -10, '2023-06-18 16:47:05', '');
INSERT INTO `record` VALUES (11, 9, 2, 3, -10, '2023-06-18 16:48:42', '');`record`
INSERT INTO `record` VALUES (12, 10, 2, 1, 5, '2023-06-18 20:26:54', '');
INSERT INTO `record` VALUES (13, 10, 2, 1, -10, '2023-06-18 20:28:28', '');
INSERT INTO `record` VALUES (14, 9, 2, 3, 10, '2023-06-18 20:41:05', '');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
                            `id` INT(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `name` VARCHAR(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '仓库名',
                            `remark` VARCHAR(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES (1, '仓库2', '');
INSERT INTO `storage` VALUES (2, '仓库1', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` INT(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `no` VARCHAR(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
                         `name` VARCHAR(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名字',
                         `password` VARCHAR(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
                         `age` INT(0) NULL DEFAULT NULL,
                         `sex` INT(0) NULL DEFAULT NULL COMMENT '性别',
                         `phone` VARCHAR(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话',
                         `role_id` INT(0) NULL DEFAULT NULL COMMENT '角色 0超级管理员，1管理员，2普通账号',
                         `is_valid` VARCHAR(4) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'Y' COMMENT '是否有效，Y有效，其他无效',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'sa', '超级管理员', '123', 18, 1, '111', 0, 'Y');
INSERT INTO `user` VALUES (2, 'lii', '用户', '456', 20, 2, '17789677785', 2, 'Y');
INSERT INTO `user` VALUES (3, 'sss', 'ssss', '789', 13, 0, '17789563354', 1, 'Y');
INSERT INTO `user` VALUES (5, 'lisi', '李四', '123456', 30, 1, '17789545632', 1, 'Y');
INSERT INTO `user` VALUES (6, 'zhang', '张三', '123', 19, 0, '17789566323', 1, 'Y');

SET FOREIGN_KEY_CHECKS = 1;

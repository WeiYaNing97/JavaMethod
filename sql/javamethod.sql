/*
 Navicat Premium Dump SQL

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80018 (8.0.18)
 Source Host           : localhost:3306
 Source Schema         : javamethod

 Target Server Type    : MySQL
 Target Server Version : 80018 (8.0.18)
 File Encoding         : 65001

 Date: 05/03/2025 07:25:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (8, '管理员');
INSERT INTO `role` VALUES (9, '董事长');
INSERT INTO `role` VALUES (10, '总经理');
INSERT INTO `role` VALUES (11, '副总经理');
INSERT INTO `role` VALUES (12, '主管');
INSERT INTO `role` VALUES (13, '员工');
INSERT INTO `role` VALUES (14, '司机');
INSERT INTO `role` VALUES (15, '保安');
INSERT INTO `role` VALUES (16, '保洁');

-- ----------------------------
-- Table structure for scores
-- ----------------------------
DROP TABLE IF EXISTS `scores`;
CREATE TABLE `scores`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩表主键',
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `subject` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '科目',
  `score` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '成绩',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scores
-- ----------------------------
INSERT INTO `scores` VALUES (1, 1, '语文', '32');
INSERT INTO `scores` VALUES (2, 1, '数学', '43');
INSERT INTO `scores` VALUES (3, 1, '外语', '54');
INSERT INTO `scores` VALUES (4, 2, '语文', '65');
INSERT INTO `scores` VALUES (5, 2, '数学', '76');
INSERT INTO `scores` VALUES (6, 2, '外语', '77');
INSERT INTO `scores` VALUES (7, 3, '语文', '21');
INSERT INTO `scores` VALUES (8, 3, '数学', '31');
INSERT INTO `scores` VALUES (9, 3, '外语', '41');
INSERT INTO `scores` VALUES (10, 4, '语文', '51');
INSERT INTO `scores` VALUES (11, 4, '数学', '61');
INSERT INTO `scores` VALUES (12, 4, '外语', '71');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '学生姓名',
  `class_name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '张三', '一班');
INSERT INTO `student` VALUES (2, '李四', '二班');
INSERT INTO `student` VALUES (3, '王五', '二班');
INSERT INTO `student` VALUES (4, '赵六', '三班');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `user_sex` int(11) NOT NULL DEFAULT 2 COMMENT '用户性别  0女 1男 2未知',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (35, '厍天娇', 78);
INSERT INTO `user` VALUES (50, '奕雨桐', 23);
INSERT INTO `user` VALUES (52, '苟佳琪', 69);
INSERT INTO `user` VALUES (69, '佛国平', 87);
INSERT INTO `user` VALUES (93, '覃文昊', 84);
INSERT INTO `user` VALUES (94, '水安琪', 38);
INSERT INTO `user` VALUES (95, '蓬秀珍', 72);
INSERT INTO `user` VALUES (96, '林娜', 22);
INSERT INTO `user` VALUES (97, '竹熙瑶', 61);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (2, 35, 11);
INSERT INTO `user_role` VALUES (3, 94, 11);
INSERT INTO `user_role` VALUES (4, 52, 11);
INSERT INTO `user_role` VALUES (5, 50, 11);
INSERT INTO `user_role` VALUES (6, 93, 11);
INSERT INTO `user_role` VALUES (7, 69, 9);
INSERT INTO `user_role` VALUES (8, 95, 9);
INSERT INTO `user_role` VALUES (9, 96, 9);
INSERT INTO `user_role` VALUES (10, 97, 10);

-- ----------------------------
-- View structure for user_role_name
-- ----------------------------
DROP VIEW IF EXISTS `user_role_name`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `user_role_name` AS select `user`.`user_name` AS `user_name`,`role`.`role_name` AS `role_name` from ((`user` join `role`) join `user_role`) where ((`user_role`.`user_id` = `user`.`user_id`) and (`user_role`.`role_id` = `role`.`role_id`));

SET FOREIGN_KEY_CHECKS = 1;

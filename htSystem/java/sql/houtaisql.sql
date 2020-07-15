/*
 Navicat Premium Data Transfer

 Source Server         : bendi
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : houtaisql

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 15/07/2020 17:07:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '按钮id主键',
  `button_code` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '鎸夐挳缂栫爜',
  `status` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `button_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '鎸夐挳鍚嶇О',
  `menu_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属菜单id',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮描述',
  `create_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `button_url` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_button
-- ----------------------------
INSERT INTO `sys_button` VALUES ('0f9366c3ac244b7eab15aeaa6a03c943', 'T001', '1', '新增/修改', '3f913fd812364619a8efe9b727696a98', '新增/修改字典', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:33:41', NULL, NULL, 'sysdic:save');
INSERT INTO `sys_button` VALUES ('11a09de082b949bcbdd400723c2b3887', 'T002', '1', '删除', '95456a9c2e394441b97671dbe19137bb', '删除按钮', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:35:46', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:36:34', 'buttonConfig:deleteID');
INSERT INTO `sys_button` VALUES ('13f2ea34b9d5447e9d04943395aad098', 'T002', '1', '删除', '3f913fd812364619a8efe9b727696a98', '删除字典', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:33:05', NULL, NULL, 'sysdic:deleteID');
INSERT INTO `sys_button` VALUES ('1b38f27b09d6436cbd7ce578f28f7a8e', 'T003', '1', '批量删除', '3f913fd812364619a8efe9b727696a98', '批量删除字典类型', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:34:55', NULL, NULL, 'dicType:bacthDelete');
INSERT INTO `sys_button` VALUES ('292fa92fae2c4cb9a1136ba699a40365', 'T007', '1', '重置密码', 'fd8a867a59924bbcae445046d7e1a5c2', '重置密码', '4fcee6c3f7164300ab75e67b32871145', '2020-03-22 14:47:44', NULL, NULL, 'sysUser:resetPassword');
INSERT INTO `sys_button` VALUES ('2d062a47fd8a4e1a98b552ef60c37643', 'T001', '1', '新增/修改', 'fd8a867a59924bbcae445046d7e1a5c2', '新增/修改管理员', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:31:14', NULL, NULL, 'sysUser:save');
INSERT INTO `sys_button` VALUES ('366a577f08674f8bbe4c3f7164e908a7', 'T002', '1', '删除', 'e4b0afc1df694c12b54f3d8e58c394ca', '删除菜单', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:19:31', NULL, NULL, 'sysMenu:delete');
INSERT INTO `sys_button` VALUES ('3700186ba1a5417490322f4d337a7ea4', 'T001', '1', '新增/修改', 'e4b0afc1df694c12b54f3d8e58c394ca', '新增/修改菜单', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:18:43', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:19:39', 'sysMenu:save');
INSERT INTO `sys_button` VALUES ('5a193e98280e46e1b6a86c68aeb653eb', 'T001', '1', '新增/修改', 'e4b0afc1df694c12b54f3d8e58c394ca', '新增/修改权限按钮', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:21:13', NULL, NULL, 'sysButton:save');
INSERT INTO `sys_button` VALUES ('7b373815054e49bfabaf0273f0419bc9', 'T006', '1', '批量保存', 'eee385792dc44c7191641f4cc70e007c', '批量保存权限', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:29:27', NULL, NULL, 'rolePermission:save');
INSERT INTO `sys_button` VALUES ('98837acc49d5480793ebd949d6410548', 'T002', '1', '删除', '3f913fd812364619a8efe9b727696a98', '删除字典类型', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:34:32', NULL, NULL, 'dicType:deleteID');
INSERT INTO `sys_button` VALUES ('9cdd5443365f4e38a478d6e36c6aa40e', 'T002', '1', '删除', 'e4b0afc1df694c12b54f3d8e58c394ca', '删除权限按钮', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:21:42', NULL, NULL, 'sysButton:delete');
INSERT INTO `sys_button` VALUES ('b24fa16517354646ac49bd535b081f80', 'T001', '1', '新增/修改', '95456a9c2e394441b97671dbe19137bb', '新增/修改按钮', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:35:27', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:36:10', 'buttonConfig:save');
INSERT INTO `sys_button` VALUES ('be0eb3b0c68441bc8b6de554082821f3', 'T003', '1', '批量删除', 'fd8a867a59924bbcae445046d7e1a5c2', '批量删除管理员', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:31:56', NULL, NULL, 'sysUser:batchDelete');
INSERT INTO `sys_button` VALUES ('c8b689b3a9484fb69e4532a185928baa', 'T003', '1', '批量删除', '95456a9c2e394441b97671dbe19137bb', '批量删除按钮', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:36:53', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:37:00', 'buttonConfig:batchDelete');
INSERT INTO `sys_button` VALUES ('d2e2d7bb8373428bab6b03d7aca33f7d', 'T003', '1', '批量删除', '3f913fd812364619a8efe9b727696a98', '批量删除字典', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:32:51', NULL, NULL, 'sysdic:batchDelete');
INSERT INTO `sys_button` VALUES ('d94b592715de4489b5bc61cfc767ac18', 'T001', '1', '新增/修改', '3f913fd812364619a8efe9b727696a98', '新增/修改字典类型', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:34:11', NULL, NULL, 'dicType:save');
INSERT INTO `sys_button` VALUES ('e983e5dbfd6e41cbb24553cd9ae007f3', 'T002', '1', '删除', 'fd8a867a59924bbcae445046d7e1a5c2', '删除管理员', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:31:37', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:32:03', 'sysUser:deleteID');
INSERT INTO `sys_button` VALUES ('eac4c809840e46d6ada8ed12773d831b', 'T001', '1', '新增/修改', 'eee385792dc44c7191641f4cc70e007c', '新增/修改角色', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:22:21', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:25:01', 'sysRole:save');
INSERT INTO `sys_button` VALUES ('eadcec66d32c4eb0998eab7b2bb9abfd', 'T002', '1', '删除', 'eee385792dc44c7191641f4cc70e007c', '删除角色', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:23:30', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:25:27', 'sysRole:delete');
INSERT INTO `sys_button` VALUES ('f1eb624af2cf42448671288969f9f6a5', 'T003', '1', '批量删除', 'eee385792dc44c7191641f4cc70e007c', '批量删除角色', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:22:47', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:25:40', 'sysRole:batchDelete');
INSERT INTO `sys_button` VALUES ('f98965a6d0e84829a3c59644b9108cc8', 'T005', '1', '批量更新状态', 'eee385792dc44c7191641f4cc70e007c', '批量更新角色状态', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:27:55', NULL, NULL, 'sysRole:updateStatus');

-- ----------------------------
-- Table structure for sys_button_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_button_config`;
CREATE TABLE `sys_button_config`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '按钮id',
  `button_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮编码',
  `button_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮描述',
  `create_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_button_config
-- ----------------------------
INSERT INTO `sys_button_config` VALUES ('27d1d29b22da4da6a01d3be35070e303', 'T007', '重置密码', '重置密码', '4fcee6c3f7164300ab75e67b32871145', '2020-03-22 14:47:03', NULL, NULL);
INSERT INTO `sys_button_config` VALUES ('3cc5d609a587456ea35442dacb02a6dc', 'T001', '新增/修改', '新增/修改', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:15:41', NULL, NULL);
INSERT INTO `sys_button_config` VALUES ('7cc1ec1d04b54ecbbfdcfdb15b7eb048', 'T006', '批量保存', '批量保存', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:28:59', NULL, NULL);
INSERT INTO `sys_button_config` VALUES ('8a9aa865dc6141babcca76286083031e', 'T005', '批量更新状态', '批量更新状态', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:26:52', NULL, NULL);
INSERT INTO `sys_button_config` VALUES ('d64e4f7795a447cab75a0830a91ba093', 'T003', '批量删除', '批量删除', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:16:18', NULL, NULL);
INSERT INTO `sys_button_config` VALUES ('dd6389887e28413fa1f3f63a5bb030d2', 'T002', '删除', '删除', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:16:06', NULL, NULL);
INSERT INTO `sys_button_config` VALUES ('f64e1d2367cd4930a8aa5fe0da64bec1', 'T004', '更新状态', '更新状态', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:26:30', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典数据id',
  `dic_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典数据标签',
  `dic_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典数据值',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典数据状态',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典状态描述',
  `dic_type_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属字典类型id',
  `create_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES ('0f5ca1ecb90846a7adcce173c9088e74', '启用', '1', '1', '启用', '5c3f998628df4b8ca5fe0a77a0dfb028', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:08:27', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('15771d5c2e1842638b29dcfde176a164', '其他', '0', '1', '其他', 'f4e43c924d7a4dff96e5eea0d38b281c', '4fcee6c3f7164300ab75e67b32871145', '2020-06-16 22:05:17', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('23b0ea617b6b4625b1e5b2abdd781ab1', 'linux', '3', '1', 'linux', 'f4e43c924d7a4dff96e5eea0d38b281c', '4fcee6c3f7164300ab75e67b32871145', '2020-06-16 22:04:54', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('43133df22c2e468d9f09778ec28757c0', '未状态', '0', '1', '未状态', '80c4072f134c435997fe8c665516c404', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:40:54', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('68165c46a8dc4888bc4418845961b0b6', '已登入', '1', '1', '已登入', '80c4072f134c435997fe8c665516c404', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:41:08', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('82e816dd3ee24d678b700eb9ae4b3ce4', '禁用', '0', '1', '禁用', '5c3f998628df4b8ca5fe0a77a0dfb028', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:08:13', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('9c002b2c2ca54f90a99286dc964a5038', '存在', '1', '1', '存在', 'd598e4e5ee5e4a83a158557398a4cce6', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:10:23', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('9d8a62ffe68645488f933feb957ad355', '女', '1', '1', '女', 'db3d45abda294918a1f7f16d8b0308e8', '4fcee6c3f7164300ab75e67b32871145', '2020-03-08 04:05:15', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('9ecc34507b13468b96ef9c59c09fa107', '男', '0', '1', '男', 'db3d45abda294918a1f7f16d8b0308e8', '4fcee6c3f7164300ab75e67b32871145', '2020-03-08 04:05:06', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('cc1caf56848d419aaad8ce6713b04cbf', '不存在', '0', '1', '不存在', 'd598e4e5ee5e4a83a158557398a4cce6', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:10:12', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('d839a5091aa542e6a4f09c0bf4f8658f', 'java', '2', '1', 'java', 'f4e43c924d7a4dff96e5eea0d38b281c', '4fcee6c3f7164300ab75e67b32871145', '2020-06-16 22:03:44', NULL, NULL);
INSERT INTO `sys_dic` VALUES ('eadec425b54549bd8cc966a9ea4f8143', 'vue', '1', '1', 'vue', 'f4e43c924d7a4dff96e5eea0d38b281c', '4fcee6c3f7164300ab75e67b32871145', '2020-06-16 22:03:32', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dic_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic_type`;
CREATE TABLE `sys_dic_type`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型id',
  `dic_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型名称',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型状态',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型描述',
  `create_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dic_type
-- ----------------------------
INSERT INTO `sys_dic_type` VALUES ('5c3f998628df4b8ca5fe0a77a0dfb028', 'enabled', '1', '状态', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:07:30', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:17:32');
INSERT INTO `sys_dic_type` VALUES ('80c4072f134c435997fe8c665516c404', 'loginStatus', '1', '登入状态', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:39:58', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:40:27');
INSERT INTO `sys_dic_type` VALUES ('d598e4e5ee5e4a83a158557398a4cce6', 'isChildren', '1', '是否存在下级', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:09:40', NULL, NULL);
INSERT INTO `sys_dic_type` VALUES ('db3d45abda294918a1f7f16d8b0308e8', 'gender', '1', '性别', '4fcee6c3f7164300ab75e67b32871145', '2020-03-08 04:04:43', NULL, NULL);
INSERT INTO `sys_dic_type` VALUES ('f4e43c924d7a4dff96e5eea0d38b281c', 'blogType', '1', '博客标签', '4fcee6c3f7164300ab75e67b32871145', '2020-06-16 22:02:09', NULL, NULL);

-- ----------------------------
-- Table structure for sys_files
-- ----------------------------
DROP TABLE IF EXISTS `sys_files`;
CREATE TABLE `sys_files`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `type` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  `status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_files
-- ----------------------------
INSERT INTO `sys_files` VALUES ('003d9a6922724d69a570377204b4bc64', 'https://www.xinglintang.net/upload/4aef60c3-7be9-4d94-a226-3d847720d5e9.png', NULL, '1', '2020-05-15 09:32:44', '1', NULL);
INSERT INTO `sys_files` VALUES ('03c5dc5772fa4325baf0d9444b3e0b7a', 'https://www.xinglintang.net/upload/f469742e-9b72-4874-a972-de466b583e49.jpg', NULL, '1', '2020-05-20 15:06:18', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('07a827fe53354cb98cba6639f8dd417d', 'https://www.xinglintang.net/uploadb46b9647-d65f-4255-a4a8-9e7fe368d8b2.png', NULL, '1', '2020-05-15 08:52:15', '1', NULL);
INSERT INTO `sys_files` VALUES ('0a155fd21bd8476b8e7a70b22bd99922', 'https://www.xinglintang.net/upload/bb5e9ba5-39a6-4d7b-8731-c7b1071fcebe.jpg', NULL, '1', '2020-05-15 10:01:28', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('0b391aa50b3540a4809b38a1228703d1', 'https://www.xinglintang.net/upload/b9f92518-ffa8-450b-86b4-a2fa16ef7a24.jpg', NULL, '1', '2020-05-20 15:15:45', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('0d095daf52ce49eeb83995e17ee23d13', 'https://www.xinglintang.net/upload/cba369e1-94ca-4480-acd2-68dc31f19ad7.jpg', NULL, '1', '2020-05-15 16:44:53', '1', NULL);
INSERT INTO `sys_files` VALUES ('1111', 'https://www.xinglintang.net/upload/xiangqing.jpg', NULL, '1', '2020-05-15 08:56:28', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1113', 'https://www.xinglintang.net/upload/e29f36cae0fb20b9a87a6c958e25150.jpg', NULL, '1', '2020-05-15 08:56:40', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1118', 'https://www.xinglintang.net/upload/IMG_0849.JPG', NULL, '1', '2020-05-15 08:56:45', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1119', 'https://www.xinglintang.net/upload/IMG_0851.JPG', NULL, '1', '2020-05-15 08:56:49', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1120', 'https://www.xinglintang.net/upload/IMG_0854.JPG', NULL, '1', '2020-05-15 08:56:53', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1122', 'https://www.xinglintang.net/upload/1.jpg', NULL, '1', '2020-05-15 08:57:00', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1123', 'https://www.xinglintang.net/upload/2.jpg', NULL, '1', '2020-05-15 08:57:06', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1124', 'https://www.xinglintang.net/upload/3.jpg', NULL, '1', '2020-05-15 08:57:03', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1125', 'https://www.xinglintang.net/upload/4.jpg', NULL, '1', '2020-05-15 08:57:09', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1126', 'https://www.xinglintang.net/upload/5.jpg', NULL, '1', '2020-05-15 08:57:13', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1127', 'https://www.xinglintang.net/upload/6.png', NULL, '1', '2020-05-15 08:57:18', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1128', 'https://www.xinglintang.net/upload/6c617f976e7309f59113e10c3501a87.png', NULL, '1', '2020-05-16 08:57:21', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1129', 'https://www.xinglintang.net/upload/4f627575218d352f8353da12a339e4d.png', NULL, '1', '2020-05-16 08:57:55', NULL, NULL);
INSERT INTO `sys_files` VALUES ('1217c13ff6954dbf803283770a99eb4f', 'https://www.xinglintang.net/upload/5cf5410a-52ac-4bf2-9b21-7d9115479f3c.jpg', NULL, '1', '2020-05-20 15:24:43', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('12cb09e9da444c029a3a83dd6d09db0e', 'https://www.xinglintang.net/upload/3d6879f8-bf04-41e3-9686-1c66f1053c8e.jpg', NULL, '1', '2020-05-19 14:57:05', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('1321c7c8c0f24414a3037d4bb47d987b', 'https://www.xinglintang.net/upload/5c4fa976-ac7d-428c-9c90-772ca84ab0f8.jpg', NULL, '1', '2020-05-20 15:15:56', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('192e6989936247cdb36372a8959621d1', 'https://www.xinglintang.net/upload/cb715146-1612-4816-884c-14c00981c0fd.JPG', NULL, '1', '2020-05-19 16:47:08', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('19aa0438f2e44d2c8257696e247dfc0b', 'https://www.xinglintang.net/upload/b224c352-6820-49a4-889d-d2eecf5fb0e3.jpg', NULL, '1', '2020-05-20 15:06:53', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('1b594a3638c64742992ec4da760c44ed', 'https://www.xinglintang.net/upload/7d4e959e-4855-4fb3-a9a7-60c875d781ea.jpg', NULL, '1', '2020-05-20 15:07:07', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('1e055481e4364b7bbaecbe7434472ee1', 'https://www.xinglintang.net/upload/beb307cc-669c-4e9c-9373-5f9809586383.jpg', NULL, '1', '2020-05-19 16:53:34', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('1ee4442dcc474abab87695722ff69856', 'https://www.xinglintang.net/upload/dbca676d-47bf-475c-b0f1-ce4b6ea1046f.jpg', NULL, '1', '2020-05-19 14:36:42', '1', NULL);
INSERT INTO `sys_files` VALUES ('218f777da9a145dea13c3ae18affc888', 'https://www.xinglintang.net/upload/e6f33f2f-d2de-48e0-940c-74b58b0573f4.jpg', NULL, '1', '2020-05-19 16:47:25', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('21d71cd336ea44d19d91400dfb7a1ba7', 'https://www.xinglintang.net/upload/b2e39475-84e2-47dd-a811-6536497854d8.jpg', NULL, '1', '2020-05-19 15:16:45', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('245f9241a0a045d08ca910f6ac048386', 'https://www.xinglintang.net/upload/581edbdc-c228-4e8d-843f-c2c80cf18f53.jpg', NULL, '1', '2020-05-19 15:24:06', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('2595a02e63c74984b7ace48e1fb15e2d', 'https://www.xinglintang.net/upload/a2e61255-0e18-4aa4-916f-312202601072.png', NULL, '1', '2020-05-16 09:45:13', '', NULL);
INSERT INTO `sys_files` VALUES ('281246b30ba4431c97f995ca71cb5fa3', 'https://www.xinglintang.net/upload/d08265f4-d2c6-4050-bada-c179c978f698.jpg', NULL, '1', '2020-05-19 14:21:04', '1', NULL);
INSERT INTO `sys_files` VALUES ('29997b68853241da996176a73c5db948', 'https://www.xinglintang.net/upload/6cddca0b-aaac-48aa-a272-b5f51e1cc443.jpg', NULL, '1', '2020-05-20 15:15:34', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('2a32aa3b5322402fa13b3b8425ec4f85', 'https://www.xinglintang.net/upload/a379d6db-2240-47ce-9561-72bccf691aa3.JPG', NULL, '1', '2020-05-19 16:47:41', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('2b311edf94504ceb82e099458d689c8e', 'https://www.xinglintang.net/upload/fde93c3e-c8b6-448b-a1d9-68ce1bfe8839.jpg', NULL, '1', '2020-05-19 14:25:12', '1', NULL);
INSERT INTO `sys_files` VALUES ('2f092dc9f20d4238996a0355f66f8704', 'https://www.xinglintang.net/upload/f62f876e-661d-4b45-86fb-ab61d983d497.jpg', NULL, '1', '2020-05-19 14:57:01', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('3124a046cf604c099ffdd49e5fb8cdc6', 'https://www.xinglintang.net/upload/2465d937-fe90-4be9-aba1-5c3e11b732cb.jpg', NULL, '1', '2020-05-19 14:25:09', '1', NULL);
INSERT INTO `sys_files` VALUES ('35ca17c64b884bdaaeda73d16db40223', 'https://www.xinglintang.net/upload/7a37a2c3-013d-4161-9acf-3c7a36bc1f96.jpg', NULL, '1', '2020-05-20 15:06:50', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('380f22561cbb47c28e7ab82778075a4f', 'https://www.xinglintang.net/upload/f9bc6d79-3881-43b8-ae3e-563b7d0ce5ee.jpg', NULL, '1', '2020-05-20 15:06:40', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('386b6e7de3c943e09fa0b74a8c932a5c', 'https://www.xinglintang.net/upload/3e60026c-0774-4c2f-8703-9df9b79fd2c7.jpg', NULL, '1', '2020-05-19 14:54:39', '1', NULL);
INSERT INTO `sys_files` VALUES ('3ff24d01c4124b469a61a5213226abbd', 'https://www.xinglintang.net/upload/ca8ea66a-76c2-4966-af9b-3473363e941e.jpg', NULL, '1', '2020-05-19 14:36:53', '1', NULL);
INSERT INTO `sys_files` VALUES ('41d761798d844d6aa91bd715392a8058', 'https://www.xinglintang.net/upload/e4c96643-fa37-4b52-b117-e78d77f06119.jpg', NULL, '1', '2020-05-19 14:57:13', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('43fbaf01616f4f4594097e41b773664d', 'https://www.xinglintang.net/upload/b5a34fe5-2d35-4a4e-966b-72341a16f92f.jpg', NULL, '1', '2020-05-20 15:06:47', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('48a9f77f8251461bb37af44276e798ed', 'https://www.xinglintang.net/upload/a1f55062-6d6b-4d2b-93df-93d55406459f.png', NULL, '1', '2020-05-20 15:24:37', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('4bef115c3fd34ca6a48d148ba4ce7d04', 'https://www.xinglintang.net/upload/bddc5962-7bb4-43a4-8ede-009d98486eb3.jpg', NULL, '1', '2020-05-20 15:21:37', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('4d3e09ca3c0146e8a117e62e4145e78d', 'https://www.xinglintang.net/upload/b69c97fa-f3d5-485a-b813-1222c8d1ed96.jpg', NULL, '1', '2020-05-20 15:24:48', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('4ecca56c8ca04562ae426bbd3ea2b993', 'https://www.xinglintang.net/upload/9763a7b1-ed64-4996-a950-8058d5a9b384.jpg', NULL, '1', '2020-05-20 15:21:45', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('5162d43c376d407bbafa1b6a578df33b', 'https://www.xinglintang.net/upload/9353f9ea-c2c7-49eb-b0c4-36443b41147c.jpg', NULL, '1', '2020-05-19 14:12:51', '1', NULL);
INSERT INTO `sys_files` VALUES ('5342cb66b7924426b7813da3561ebe0c', 'https://www.xinglintang.net/upload/a7c71c50-aeb2-4fb3-8e62-8bedf7ea04f7.jpg', NULL, '1', '2020-05-19 14:29:53', '1', NULL);
INSERT INTO `sys_files` VALUES ('5639b0af9fd843f7bb11eba912dd0d30', 'https://www.xinglintang.net/upload/1add9e62-5e1a-47e3-bd14-b34687e6ebcc.jpg', NULL, '1', '2020-05-20 15:24:20', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('56a0cee12bdd4724b42c95ac4d687515', 'https://www.xinglintang.net/upload/52a569fb-d5c7-44f6-9fae-0d199a7cef5a.jpg', NULL, '1', '2020-05-19 14:29:47', '1', NULL);
INSERT INTO `sys_files` VALUES ('582f37c9145446f9a4d1ae92eeabc3c5', 'https://www.xinglintang.net/upload/858879f9-d6f5-4861-a99d-ed1b968ee039.jpg', NULL, '1', '2020-05-19 14:36:43', '1', NULL);
INSERT INTO `sys_files` VALUES ('599f2a6d934f4cec888ddd1737d8d077', 'https://www.xinglintang.net/upload/bcc65e39-cb4e-4f91-abb9-eadb3a802136.png', NULL, '1', '2020-05-20 15:19:36', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('59b2ef05e2a040dcade016baf2f63e39', 'https://www.xinglintang.net/upload/3e83640a-b083-46b9-b062-11924d1a5892.jpg', NULL, '1', '2020-05-19 14:54:30', '1', NULL);
INSERT INTO `sys_files` VALUES ('5a3977440d214139a9553532609c5bf6', 'https://www.xinglintang.net/upload/390e3965-7539-49d0-8373-173f0ec6b815.jpg', NULL, '1', '2020-05-20 15:24:46', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('5d41dccf7253443e98d1e2ea0ca39d98', 'https://www.xinglintang.net/upload/f74993ba-6389-4e01-858c-8c5f937a85b9.jpg', NULL, '1', '2020-05-20 15:13:07', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('60055875b5e34f1590958524a4a7203c', 'https://www.xinglintang.net/upload/26fa991a-34dd-42b9-9b1a-5a007f462541.jpg', NULL, '1', '2020-05-20 15:06:33', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('6211b026f13d4abd8ae36c743c0fa8eb', 'https://www.xinglintang.net/upload/e95ddb77-b5b6-4291-b15e-22b72a4b69d2.jpg', NULL, '1', '2020-05-20 15:21:34', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('6235e586419b4c47a32fab39377c4faf', 'https://www.xinglintang.net/upload/f846503e-b2f2-41dd-b8a5-93f6fac22e18.jpg', NULL, '1', '2020-05-19 16:53:32', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('62c76ac2b59f400a818264d6cfb106b4', 'https://www.xinglintang.net/upload/21a14944-f447-43be-ae0d-a21fbc3b5cbe.jpg', NULL, '1', '2020-05-20 15:15:54', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('648a96515914422f8ec30bee14994e08', 'https://www.xinglintang.net/upload/3038e3e7-1eeb-4b90-bda8-38ff9ea0d517.jpg', NULL, '1', '2020-05-20 15:13:14', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('6824bd9f41884de1a3201cfb3e755dcd', 'https://www.xinglintang.net/upload/d71169e0-4229-41c3-bf17-24e50336a960.png', NULL, '1', '2020-05-20 15:21:41', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('68cbbde4867d4ae1ba692354397ed4d2', 'https://www.xinglintang.net/upload/f9bbc275-fa8a-491f-b87b-839e5bae03df.JPG', NULL, '1', '2020-05-19 16:47:10', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('6af02baf4f1c487db138326fac37f374', 'https://www.xinglintang.net/upload/210bf2ff-6622-4a76-9367-efd1aca910ea.jpg', NULL, '1', '2020-05-20 15:15:36', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('6ce3318010664aa991d1789698669397', 'https://www.xinglintang.net/upload/80389e9b-e763-4d50-80d0-a140ebc1ec63.jpg', NULL, '1', '2020-05-20 15:24:25', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('6d9bc424db70470da7f9053317c6e5f7', 'https://www.xinglintang.net/upload/e6edbeaf-d594-40a7-8729-98a6267da5a3.jpg', NULL, '1', '2020-05-20 15:21:54', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('70bcbc0fe96f4d0dbfb57283888e3e36', 'https://www.xinglintang.net/upload/1d0abaef-fb8e-446a-b3c2-a217d48df30a.jpg', NULL, '1', '2020-05-15 16:44:59', '1', NULL);
INSERT INTO `sys_files` VALUES ('70c665d606d34d0a9b71e117f0797ef8', 'https://www.xinglintang.net/upload/4964011f-e22c-43fc-8ece-29d7f79dd0ff.jpg', NULL, '1', '2020-05-15 16:44:43', '1', NULL);
INSERT INTO `sys_files` VALUES ('78baefae492e4f269c200aa9d83f353d', 'https://www.xinglintang.net/upload/772fbed6-4390-4c3c-aa58-11782f868ffa.png', NULL, '1', '2020-05-16 00:51:01', '', NULL);
INSERT INTO `sys_files` VALUES ('7c30bb2575054f82be89ed8f446797cf', 'https://www.xinglintang.net/upload/489d72ce-fcf9-4597-8f56-4376809e4bcc.jpg', NULL, '1', '2020-05-19 14:13:01', '1', NULL);
INSERT INTO `sys_files` VALUES ('7f1306b3f3914e8784daff2e003ecb89', 'https://www.xinglintang.net/upload/18709bad-5b5c-4e14-a6a9-9e1ad469cc11.jpg', NULL, '1', '2020-05-20 15:15:59', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('7f991d017bd644678df7d997b42ca159', 'https://www.xinglintang.net/upload/1b6a6fae-777e-4293-8a4f-d01f797e1771.jpg', NULL, '1', '2020-05-20 15:13:11', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('80137146bbc8434f83426a3806b8712e', 'https://www.xinglintang.net/upload/1d30a15e-4362-48af-8364-2b3b74d2c355.jpg', NULL, '1', '2020-05-15 16:44:48', '1', NULL);
INSERT INTO `sys_files` VALUES ('80a9542a7b1541b98962121312e31a98', 'https://www.xinglintang.net/upload/93b7bc00-7ef2-4c11-852f-29d026631719.jpg', NULL, '1', '2020-05-20 15:19:41', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('833835cc36e740ab9e8fd1fab086582e', 'https://www.xinglintang.net/upload/0f811ed7-40e2-4bf7-a882-aa88082848b1.jpg', NULL, '1', '2020-05-20 15:15:39', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('83e2caf4b63a41898911cf0e1706ea62', 'https://www.xinglintang.net/upload/46350e80-10c0-407c-a926-9df611ce2a52.jpg', NULL, '1', '2020-05-20 15:13:16', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('86bf69ec30f64aada409f7d4eee1cfad', 'https://www.xinglintang.net/upload/0e848e0d-3e3c-48e7-83b7-0043f583bfa2.jpg', NULL, '1', '2020-05-20 15:19:26', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('87f7bc9d53e643b8b753da75f0984371', 'https://www.xinglintang.net/upload/6fbc2517-9567-4ace-a0d1-f661b16e0e63.jpg', NULL, '1', '2020-05-19 16:28:22', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('8c5925dbd317411394cf04ab2d3732bc', 'https://www.xinglintang.net/upload/6facd070-bf05-4b5e-858c-350325b033d0.JPG', NULL, '1', '2020-05-19 16:47:19', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('91077d00a5654893aa4fc74a26db6a79', 'https://www.xinglintang.net/upload/23d752b0-cf88-494a-adc5-8ea44cf30072.jpg', NULL, '1', '2020-05-20 15:06:12', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('9153280643ab4cf9a43e15a1ee424867', 'https://www.xinglintang.net/upload/a0ee8ad1-4757-4039-af92-19f6c3d42dde.png', NULL, '1', '2020-05-19 16:47:28', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('9172a67ac33e4abc8a832641a141097f', 'https://www.xinglintang.net/upload/ab60f42c-132a-4b96-be0b-5c538493ea2c.jpg', NULL, '1', '2020-05-20 15:06:27', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('925a561d3c974932a8ebd12d269405e1', 'https://www.xinglintang.net/upload/b88aefaf-9e88-4428-aa28-9474d66f7d0b.png', NULL, '1', '2020-05-15 16:45:08', '1', NULL);
INSERT INTO `sys_files` VALUES ('94c66d4f6f5e4f9eb2d22fae302dc7cb', 'https://www.xinglintang.net/upload/cec19d1b-4a75-44aa-bf4b-c4141d57b549.jpg', NULL, '1', '2020-05-19 16:53:39', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('993463a527db45eca1693e54cbebf91a', 'https://www.xinglintang.net/upload/b32a9a07-7e0c-44b3-8dd6-972c83270749.jpg', NULL, '1', '2020-05-20 15:06:43', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('a1eb9ee432fe409dab951d090a86577a', 'https://www.xinglintang.net/upload/b86492ac-955b-4907-a7ab-76242751ca1a.jpg', NULL, '1', '2020-05-19 14:29:50', '1', NULL);
INSERT INTO `sys_files` VALUES ('a24d2243cc1d4aba8056db532af57a01', 'https://www.xinglintang.net/upload/109aac75-9a80-4285-b870-ac34a2a2d7b4.jpg', NULL, '1', '2020-05-20 15:06:20', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('a4bac61cf2344569ae469f8f8e5ddccc', 'https://www.xinglintang.net/upload/47a0b9ba-ddb9-4378-83be-c50c977cbfb0.jpg', NULL, '1', '2020-05-19 14:36:49', '1', NULL);
INSERT INTO `sys_files` VALUES ('a6120b52cfc34f3889777b581a7f0ee2', 'https://www.xinglintang.net/upload/c2949892-8ee7-4766-965a-8fd140d32aed.jpg', NULL, '1', '2020-05-20 15:19:28', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('a89bfd1a2e834df8916df9157d83556d', 'https://www.xinglintang.net/upload/95a545ec-4f92-42c4-8070-4000b663c3bf.jpg', NULL, '1', '2020-05-19 14:12:57', '1', NULL);
INSERT INTO `sys_files` VALUES ('ac0660f08a1b44028ff4b928dfb53516', 'https://www.xinglintang.net/upload/410162e6-75d3-49f3-824e-632dcc95a9ab.jpg', NULL, '1', '2020-05-20 09:37:40', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('ac4e155957d0435fbd4065f925534554', 'https://www.xinglintang.net/upload/cb2b06ab-00aa-40c6-9ccf-3d95cde72fd9.jpg', NULL, '1', '2020-05-20 15:06:36', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('af3eb5977b5040fb96c3b1336cab808a', 'https://www.xinglintang.net/upload/f1cae092-59ae-41ff-9445-3420feb4b719.jpg', NULL, '1', '2020-05-20 15:15:51', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('af4c7c33e96642fe9f41004f6dac60c5', 'https://www.xinglintang.net/upload/d553664e-ba90-45db-9e64-f7b9bf302bf2.jpg', NULL, '1', '2020-05-20 15:07:00', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('afc820da36804d9597e1c0ec8f8745ea', 'https://www.xinglintang.net/upload/bf16f69f-3eaa-454b-9506-67a91b5dd837.jpg', NULL, '1', '2020-05-19 14:29:44', '1', NULL);
INSERT INTO `sys_files` VALUES ('b1094d62d71d4916bf5736b5e1603f58', 'https://www.xinglintang.net/upload/f68a47bb-0c35-42eb-815c-024dc6853968.jpg', NULL, '1', '2020-05-20 15:21:48', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('b206326c4ab04d38a0f06319e20ccc90', 'https://www.xinglintang.net/upload/e14d66df-e345-4ebc-ab9a-7736c9e8966a.jpg', NULL, '1', '2020-05-19 16:33:19', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('b2cd0d8435504144b63cde0af2fe0280', 'https://www.xinglintang.net/upload/a89fcc67-8af5-48f9-8218-a39b8b37145e.jpg', NULL, '1', '2020-05-20 15:06:23', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('b43a1979b053429396aacefd5049e538', 'https://www.xinglintang.net/upload/56d26da3-9fcc-4ecf-9901-d017dccaf15e.jpg', NULL, '1', '2020-05-19 16:53:29', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('b6a5f784555a492e92045fec9b39fafd', 'https://www.xinglintang.net/upload/5e8267b8-8d12-46bd-80df-1c0ba44993ee.jpg', NULL, '1', '2020-05-19 14:24:46', '1', NULL);
INSERT INTO `sys_files` VALUES ('b7c73c51892d4aa7ada12f3692c4ef69', 'https://www.xinglintang.net/upload/a4ab3e92-4e64-464f-9098-8035be8085e5.jpg', NULL, '1', '2020-05-15 16:45:04', '1', NULL);
INSERT INTO `sys_files` VALUES ('b86076b4eb684582b71617b9132e398b', 'https://www.xinglintang.net/upload/eebaa288-e2be-40a2-83fd-abf21769920c.jpg', NULL, '1', '2020-05-20 15:21:51', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('be28f4c119a144598a0b5fb58442d724', 'https://www.xinglintang.net/upload/b9c509af-c1b1-498d-8abe-fbfc6fb23c85.jpg', NULL, '1', '2020-05-20 15:19:31', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('bf1b83e71b074b37b99fea43afce310f', 'https://www.xinglintang.net/upload/73e8605a-3f9d-414f-8966-73bd7cd7a37a.JPG', NULL, '1', '2020-05-19 16:47:16', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('c1c02b9ccd8e4611932b97e59559f69e', 'https://www.xinglintang.net/upload/46e68dfe-97cd-4245-b97d-6d75f131c4f0.jpg', NULL, '1', '2020-05-20 15:07:04', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('c53d1eca9ac741cca228d85b91260551', 'https://www.xinglintang.net/upload/36cc2ef7-8acc-4f1c-8edc-d308a8b215d4.jpg', NULL, '1', '2020-05-20 15:24:28', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('c65a776347274071b80db8098941ab3e', 'https://www.xinglintang.net/upload/e75f8c10-bbbe-47eb-b34c-42b6dbd96537.jpg', NULL, '1', '2020-05-20 15:21:56', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('cfd3dac909dc4e739dedc53679193b81', 'https://www.xinglintang.net/upload/d627a766-04c6-4969-b530-81bcfa707c7d.jpg', NULL, '1', '2020-05-19 14:36:46', '1', NULL);
INSERT INTO `sys_files` VALUES ('d6635cebfd30411fb4e30c6af24c08a5', 'https://www.xinglintang.net/upload/3bf78c5f-6259-4664-ad7e-6c566fb66763.jpg', NULL, '1', '2020-05-20 15:24:23', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('d6edf850b20147048bef44d70ca6ed55', 'https://www.xinglintang.net/upload/aa4a9aa2-26d3-41b1-b0d2-5ebf42fed5e7.jpg', NULL, '1', '2020-05-20 15:06:56', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('d8bc2cca133b4b8da02ea78eb2b53bd5', 'https://www.xinglintang.net/upload/2eeaba2d-28b3-4bb4-b13b-4244e9498a13.jpg', NULL, '1', '2020-05-20 15:06:15', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('db5534e63bef4f05bacd9c330935c5c0', 'https://www.xinglintang.net/upload/c24ae687-039f-4b9f-8833-ecf78f8aefe1.jpg', NULL, '1', '2020-05-19 14:54:42', '1', NULL);
INSERT INTO `sys_files` VALUES ('dbfba723a0d14131abc0807fa9fbcd49', 'https://www.xinglintang.net/upload/8961be1a-9b4b-4fff-9413-0fa0d3aa3a94.jpg', NULL, '1', '2020-05-19 15:21:28', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('dc2e23bf718d4278bebd567b4a276991', 'http://120.25.197.11:8082/resources/photo/1ef47880-cb5f-4fa2-bd7e-9e6a0d4dda80.png', NULL, '1', '2020-05-16 09:50:25', '', NULL);
INSERT INTO `sys_files` VALUES ('df0eae2e7b9b4f2a8a2191b2c78a607e', 'https://www.xinglintang.net/upload/a99497e7-3768-4e41-95b0-685a4ff0d500.JPG', NULL, '1', '2020-05-19 16:47:13', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('e4c78dddc14046e4bfd59d2196af4d51', 'https://www.xinglintang.net/upload/aa91593b-2f39-4de1-9f3e-44d56b67bbbc.jpg', NULL, '1', '2020-05-20 15:24:41', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('e5ce127c414e4decaf558d706c1952ee', 'https://www.xinglintang.net/upload/6d9bd11b-a470-4ddc-a305-34f6d80b13a5.jpg', NULL, '1', '2020-05-19 14:54:35', '1', NULL);
INSERT INTO `sys_files` VALUES ('e6725589a1424437b2784926210272ab', 'https://www.xinglintang.net/upload/75f37c73-1d98-4cbb-adc1-38f6845e5fdf.JPG', NULL, '1', '2020-05-19 16:47:22', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('ea874fbd33e84a609f479efac3408310', 'https://www.xinglintang.net/upload/064596dc-1bb9-4148-9688-1dfe2a3f73f0.jpg', NULL, '1', '2020-05-19 14:54:26', '1', NULL);
INSERT INTO `sys_files` VALUES ('eb703595c54948ae89e94750fb533c2f', 'https://www.xinglintang.net/upload/74ed8b38-7b73-42fb-84a8-899c38ebbbf5.jpg', NULL, '1', '2020-05-20 15:15:41', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('ecac2d1853d24f788a3f2130d66abdee', 'https://www.xinglintang.net/upload/b456f3c9-e8e5-4e5e-9162-f0c060b48aa2.jpg', NULL, '1', '2020-05-20 15:21:39', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('f419b17d8ba7410d97edbec620d70f91', 'https://www.xinglintang.net/upload/72214ce3-9693-4428-80e3-aedaf64b2280.JPG', NULL, '1', '2020-05-19 16:47:44', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('f51271a386e0408c833b18a07143141d', 'https://www.xinglintang.net/upload/9a0cc710-f333-4d11-98b3-093064bf5d2a.jpg', NULL, '1', '2020-05-16 00:50:47', '', NULL);
INSERT INTO `sys_files` VALUES ('f6b153b646194899873f59e2260d74bf', 'https://www.xinglintang.net/upload/d5e84165-5954-47b2-8c35-e5f0c7188fa5.jpg', NULL, '1', '2020-05-20 15:24:30', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('f8e18d9466734a3c9b20cbf1ae0c4b7c', 'https://www.xinglintang.net/upload/44d86abd-b1bc-46c8-aa92-63ccd3d6a506.jpg', NULL, '1', '2020-05-20 15:13:09', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('f9389ac58229491cb422d2cf83d4ceda', 'https://www.xinglintang.net/upload/9c4d2b21-be46-424a-9957-8e8a37d0b7d8.jpg', NULL, '1', '2020-05-20 15:19:34', '39c519bb28b24f208e25d344d5bdf41b', NULL);
INSERT INTO `sys_files` VALUES ('f988d29438f749a1a8d25e7049973279', 'https://www.xinglintang.net/upload/ce496c7f-e2d9-47cf-8eea-e3f79e960eeb.jpg', NULL, '1', '2020-05-19 14:21:01', '1', NULL);
INSERT INTO `sys_files` VALUES ('fbb5976ab2c74f92b03d92eac00b9015', 'https://www.xinglintang.net/upload/af1daf55-efc7-4892-9797-0395db20e21b.png', NULL, '1', '2020-05-16 10:04:25', '', NULL);
INSERT INTO `sys_files` VALUES ('fc9150c7fab945b88839592266c25a98', 'https://www.xinglintang.net/upload/54cae16e-f058-4c34-a4c5-608743eea2c1.jpg', NULL, '1', '2020-05-20 15:15:48', '39c519bb28b24f208e25d344d5bdf41b', NULL);

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '日志编号',
  `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id,记录登录用户',
  `login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  `login_address` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录地点',
  `browser_name` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器名称',
  `operation_system` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `login_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录状态',
  `operation_info` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作信息',
  `login_time` datetime(0) NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7237 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES (7172, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-29 15:12:39');
INSERT INTO `sys_login_log` VALUES (7173, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-30 01:29:34');
INSERT INTO `sys_login_log` VALUES (7174, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-30 01:48:26');
INSERT INTO `sys_login_log` VALUES (7175, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-30 02:07:11');
INSERT INTO `sys_login_log` VALUES (7176, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-30 02:11:02');
INSERT INTO `sys_login_log` VALUES (7177, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-30 02:18:05');
INSERT INTO `sys_login_log` VALUES (7178, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-30 04:10:51');
INSERT INTO `sys_login_log` VALUES (7179, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-30 04:31:11');
INSERT INTO `sys_login_log` VALUES (7180, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-30 11:11:31');
INSERT INTO `sys_login_log` VALUES (7181, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-30 23:32:23');
INSERT INTO `sys_login_log` VALUES (7182, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-05-31 10:29:49');
INSERT INTO `sys_login_log` VALUES (7183, '4fcee6c3f7164300ab75e67b32871145', '192.168.124.4', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-06-02 19:55:59');
INSERT INTO `sys_login_log` VALUES (7184, '4fcee6c3f7164300ab75e67b32871145', '192.168.2.118', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-06-16 15:38:43');
INSERT INTO `sys_login_log` VALUES (7185, '', '192.168.2.155', 'xx', 'Chrome', 'Windows 10', '0', '登录失败', '2020-06-16 21:33:17');
INSERT INTO `sys_login_log` VALUES (7186, '4fcee6c3f7164300ab75e67b32871145', '192.168.2.155', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-06-16 21:33:27');
INSERT INTO `sys_login_log` VALUES (7187, '', '192.168.43.140', 'xx', 'Chrome', 'Windows 10', '0', '登录失败', '2020-06-18 23:30:25');
INSERT INTO `sys_login_log` VALUES (7188, '4fcee6c3f7164300ab75e67b32871145', '192.168.43.140', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-06-18 23:30:33');
INSERT INTO `sys_login_log` VALUES (7189, '4fcee6c3f7164300ab75e67b32871145', '192.168.43.140', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-06-19 00:36:39');
INSERT INTO `sys_login_log` VALUES (7190, '4fcee6c3f7164300ab75e67b32871145', '192.168.2.155', 'xx', 'Chrome', 'Windows 10', '1', '登录成功', '2020-06-19 13:24:22');
INSERT INTO `sys_login_log` VALUES (7191, '4fcee6c3f7164300ab75e67b32871145', '192.168.43.140', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-02 23:22:28');
INSERT INTO `sys_login_log` VALUES (7192, '4fcee6c3f7164300ab75e67b32871145', '192.168.43.140', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-02 23:38:52');
INSERT INTO `sys_login_log` VALUES (7193, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-03 09:16:40');
INSERT INTO `sys_login_log` VALUES (7194, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-03 09:32:54');
INSERT INTO `sys_login_log` VALUES (7195, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-03 09:37:47');
INSERT INTO `sys_login_log` VALUES (7196, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-03 13:49:05');
INSERT INTO `sys_login_log` VALUES (7197, '4fcee6c3f7164300ab75e67b32871145', '127.0.0.1', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-06 13:36:17');
INSERT INTO `sys_login_log` VALUES (7198, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-06 15:00:22');
INSERT INTO `sys_login_log` VALUES (7199, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-06 15:02:27');
INSERT INTO `sys_login_log` VALUES (7200, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-06 15:20:26');
INSERT INTO `sys_login_log` VALUES (7201, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-06 17:14:06');
INSERT INTO `sys_login_log` VALUES (7202, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-06 17:21:17');
INSERT INTO `sys_login_log` VALUES (7203, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-07 09:10:05');
INSERT INTO `sys_login_log` VALUES (7204, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-08 12:50:54');
INSERT INTO `sys_login_log` VALUES (7205, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-10 16:09:19');
INSERT INTO `sys_login_log` VALUES (7206, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-10 16:28:09');
INSERT INTO `sys_login_log` VALUES (7207, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-10 16:29:11');
INSERT INTO `sys_login_log` VALUES (7208, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-10 17:07:51');
INSERT INTO `sys_login_log` VALUES (7209, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 09:24:31');
INSERT INTO `sys_login_log` VALUES (7210, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 10:02:41');
INSERT INTO `sys_login_log` VALUES (7211, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 14:16:50');
INSERT INTO `sys_login_log` VALUES (7212, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 14:29:32');
INSERT INTO `sys_login_log` VALUES (7213, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:14:28');
INSERT INTO `sys_login_log` VALUES (7214, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:30:53');
INSERT INTO `sys_login_log` VALUES (7215, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:35:21');
INSERT INTO `sys_login_log` VALUES (7216, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:36:16');
INSERT INTO `sys_login_log` VALUES (7217, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:38:34');
INSERT INTO `sys_login_log` VALUES (7218, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:39:10');
INSERT INTO `sys_login_log` VALUES (7219, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:40:29');
INSERT INTO `sys_login_log` VALUES (7220, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:43:51');
INSERT INTO `sys_login_log` VALUES (7221, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:49:00');
INSERT INTO `sys_login_log` VALUES (7222, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:50:18');
INSERT INTO `sys_login_log` VALUES (7223, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:52:45');
INSERT INTO `sys_login_log` VALUES (7224, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:53:33');
INSERT INTO `sys_login_log` VALUES (7225, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:54:50');
INSERT INTO `sys_login_log` VALUES (7226, '', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '0', '登录失败', '2020-07-13 15:58:19');
INSERT INTO `sys_login_log` VALUES (7227, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 15:58:27');
INSERT INTO `sys_login_log` VALUES (7228, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 16:07:32');
INSERT INTO `sys_login_log` VALUES (7229, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 16:08:58');
INSERT INTO `sys_login_log` VALUES (7230, '4fcee6c3f7164300ab75e67b32871145', '127.0.0.1', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-13 21:29:41');
INSERT INTO `sys_login_log` VALUES (7231, '4fcee6c3f7164300ab75e67b32871145', '127.0.0.1', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 10:02:37');
INSERT INTO `sys_login_log` VALUES (7232, '4fcee6c3f7164300ab75e67b32871145', '127.0.0.1', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 10:18:26');
INSERT INTO `sys_login_log` VALUES (7233, '4fcee6c3f7164300ab75e67b32871145', '127.0.0.1', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 10:26:43');
INSERT INTO `sys_login_log` VALUES (7234, '', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '0', '登录失败', '2020-07-14 14:41:09');
INSERT INTO `sys_login_log` VALUES (7235, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 14:41:19');
INSERT INTO `sys_login_log` VALUES (7236, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 15:23:37');
INSERT INTO `sys_login_log` VALUES (7237, '4fcee6c3f7164300ab75e67b32871145', '127.0.0.1', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 21:20:05');
INSERT INTO `sys_login_log` VALUES (7238, '4fcee6c3f7164300ab75e67b32871145', '127.0.0.1', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 22:01:33');
INSERT INTO `sys_login_log` VALUES (7239, '4fcee6c3f7164300ab75e67b32871145', '192.168.43.140', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 22:08:34');
INSERT INTO `sys_login_log` VALUES (7240, '4fcee6c3f7164300ab75e67b32871145', '192.168.43.140', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 22:11:20');
INSERT INTO `sys_login_log` VALUES (7241, '4fcee6c3f7164300ab75e67b32871145', '192.168.43.140', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 22:15:44');
INSERT INTO `sys_login_log` VALUES (7242, '4fcee6c3f7164300ab75e67b32871145', '192.168.43.140', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 22:19:00');
INSERT INTO `sys_login_log` VALUES (7243, '4fcee6c3f7164300ab75e67b32871145', '192.168.43.140', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 22:57:14');
INSERT INTO `sys_login_log` VALUES (7244, '4fcee6c3f7164300ab75e67b32871145', '192.168.43.140', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-14 22:57:56');
INSERT INTO `sys_login_log` VALUES (7245, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 09:42:42');
INSERT INTO `sys_login_log` VALUES (7246, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 13:31:44');
INSERT INTO `sys_login_log` VALUES (7247, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 13:49:38');
INSERT INTO `sys_login_log` VALUES (7248, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 13:53:35');
INSERT INTO `sys_login_log` VALUES (7249, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 13:54:47');
INSERT INTO `sys_login_log` VALUES (7250, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 14:10:41');
INSERT INTO `sys_login_log` VALUES (7251, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 14:23:54');
INSERT INTO `sys_login_log` VALUES (7252, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 14:34:07');
INSERT INTO `sys_login_log` VALUES (7253, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 14:44:40');
INSERT INTO `sys_login_log` VALUES (7254, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 15:23:45');
INSERT INTO `sys_login_log` VALUES (7255, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 15:45:44');
INSERT INTO `sys_login_log` VALUES (7256, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 15:46:24');
INSERT INTO `sys_login_log` VALUES (7257, '4fcee6c3f7164300ab75e67b32871145', '10.7.161.117', 'xx', 'Chrome 8', 'Windows 10', '1', '登录成功', '2020-07-15 16:48:24');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `menu_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `parent_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级主键',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路径',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态(是否启用 0禁用 1启用)',
  `create_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sys_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全选表用到的占位字段',
  `is_children` int(0) NULL DEFAULT NULL COMMENT '是否含有下级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('05626cd8b1d0451d8554d0739562c6d6', '0002005', '测试', 'icon-e944', '48301af1d1964ade807d40e95f3bf369', '/views/moban/textUser/textUser', '1', '2', '2020-07-10 16:27:43', '2', '2020-07-10 16:29:02', '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('069ab7cacef24e57813b315c199e66c8', '001', '系统配置', 'icon-e931', '', '001', '1', '2', '2020-02-29 15:41:48', '2', '2020-02-29 15:44:28', '', NULL, 1);
INSERT INTO `sys_menu` VALUES ('06c3fdf719aa4b5296d6a18f98df0745', '005002', 'blog项目', 'icon-e956', '8d4ff4e5461e47bab41218f914debf69', '/guanwang/ht/blogProject', '1', '2', '2020-07-06 17:21:05', '', NULL, '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('0dfe3dc17e834905ad26f242e62e2938', '003001', '个人信息', 'icon-e932', 'f0d3b0b49bde44d78b7a1fddbb7fbeff', '/views/user/userinfo', '1', '2', '2020-02-29 15:53:43', '2', '2020-02-29 16:12:40', '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('28ebade33269407c8d23f7cbc53d6b55', '005001', 'blog文章', 'icon-e93e', '8d4ff4e5461e47bab41218f914debf69', '/guanwang/ht/blogBlog', '1', '2', '2020-06-16 15:40:32', '', NULL, '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('3f913fd812364619a8efe9b727696a98', '001003001', '字典管理', 'icon-e951', '448be58a26b7400dbe7fe7757f352248', '/views/system/sysParameter/sysDic/sysDicList', '1', '2', '2020-02-29 15:48:52', '2', '2020-02-29 16:10:40', '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('448be58a26b7400dbe7fe7757f352248', '001003', '全局参数', 'icon-e940', '069ab7cacef24e57813b315c199e66c8', '001003', '1', '2', '2020-02-29 15:45:49', '', NULL, '', NULL, 1);
INSERT INTO `sys_menu` VALUES ('45e2aa89304f43b297f71df411f5229b', '001004', '基本信息', 'icon-e93b', '069ab7cacef24e57813b315c199e66c8', '001004', '1', '2', '2020-02-29 15:46:55', '2', '2020-02-29 16:38:13', '', NULL, 1);
INSERT INTO `sys_menu` VALUES ('48301af1d1964ade807d40e95f3bf369', '002', '模板', 'icon-e92a', '', '002', '1', '2', '2020-02-29 15:46:23', '2', '2020-02-29 16:38:24', '', NULL, 1);
INSERT INTO `sys_menu` VALUES ('542f1746b229448c9c6b96cccae0735f', '002004', '官网样式', 'icon-e93e', '48301af1d1964ade807d40e95f3bf369', '/views/moban/layout/layout', '1', '2', '2020-03-20 01:31:39', '', NULL, '', NULL, 1);
INSERT INTO `sys_menu` VALUES ('55db5a09a0bd407cae675400f26ad194', '004', 'app管理', 'icon-e937', '', '004', '1', '2', '2020-03-22 14:31:36', '2', '2020-03-22 14:32:48', '', NULL, 1);
INSERT INTO `sys_menu` VALUES ('64161f092b7a462786bf25e5c72fd967', '004002', '资讯管理', 'icon-e929', '55db5a09a0bd407cae675400f26ad194', '/views/dhlg/article/articleList', '1', '2', '2020-05-30 11:14:33', '', NULL, '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('695c95daac4047d98ab36a3d7ddf10ad', '004001', 'app用户管理', 'icon-e942', '55db5a09a0bd407cae675400f26ad194', '/views/dhlg/mbSysUser/mbSysUserList', '1', '2', '2020-03-22 14:33:15', '', NULL, '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('75fca2801cd0415cb9e865871d2f85fc', '001004001', '登入日志', 'icon-e93c', '45e2aa89304f43b297f71df411f5229b', '/views/system/syslog/sysLoginLog', '1', '2', '2020-02-29 15:51:43', '2', '2020-02-29 16:12:23', '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('7b3ede7ac32942e5935c4f0007ba5f34', '001002', '权限管理', 'icon-e93a', '069ab7cacef24e57813b315c199e66c8', '001002', '1', '2', '2020-02-29 15:44:15', '2', '2020-02-29 16:11:37', '', NULL, 1);
INSERT INTO `sys_menu` VALUES ('8d4ff4e5461e47bab41218f914debf69', '005', 'blog管理', 'icon-e92f', '', '005', '1', '2', '2020-06-16 15:39:55', '', NULL, '', NULL, 1);
INSERT INTO `sys_menu` VALUES ('95456a9c2e394441b97671dbe19137bb', '001003002', '按钮配置', 'icon-e94f', '448be58a26b7400dbe7fe7757f352248', '/views/system/sysParameter/sysBut/sysButList', '1', '2', '2020-02-29 15:49:33', '2', '2020-02-29 16:11:10', '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('af965b9edceb4853a1db088f2c556850', '001005', '文件上传', 'icon-e95f', '069ab7cacef24e57813b315c199e66c8', '/views/system/sysFiles/sysFilesList', '1', '2', '2020-05-30 04:11:26', '', NULL, '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('cbaba57dda95454193c1187cf70633f3', '002003', 'websocket', 'icon-e935', '48301af1d1964ade807d40e95f3bf369', '/views/moban/websocket/websocket', '1', '2', '2020-03-15 12:55:59', '', NULL, '', NULL, 1);
INSERT INTO `sys_menu` VALUES ('dc87ede61db24e7b908892c0ae5d8016', '002002', '模板2', 'icon-e941', '48301af1d1964ade807d40e95f3bf369', '/views/moban/moban2/mobanList', '1', '2', '2020-02-29 15:50:32', '2', '2020-02-29 16:38:34', '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('e4b0afc1df694c12b54f3d8e58c394ca', '001001', '菜单配置', 'icon-e942', '069ab7cacef24e57813b315c199e66c8', '/views/system/sysMenu/sysMenuList', '1', '2', '2020-02-29 15:43:31', '2', '2020-02-29 16:12:56', '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('eee385792dc44c7191641f4cc70e007c', '001002001', '角色管理', 'icon-e938', '7b3ede7ac32942e5935c4f0007ba5f34', '/views/system/sysPrivilege/sysRole/sysroleList', '1', '2', '2020-02-29 15:47:41', '2', '2020-02-29 16:38:50', '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('f0d3b0b49bde44d78b7a1fddbb7fbeff', '003', '个人中心', 'icon-e933', '', '003', '1', '2', '2020-02-29 15:53:09', '', NULL, '', NULL, 1);
INSERT INTO `sys_menu` VALUES ('f590eab1dd624836b07332f072db2390', '002001', '模板1', 'icon-e93f', '48301af1d1964ade807d40e95f3bf369', '/views/moban/moban1/mobanList', '1', '2', '2020-02-29 15:50:12', '2', '2020-02-29 16:12:30', '', NULL, 0);
INSERT INTO `sys_menu` VALUES ('fd8a867a59924bbcae445046d7e1a5c2', '001002002', '账号管理', 'icon-e93e', '7b3ede7ac32942e5935c4f0007ba5f34', '/views/system/sysPrivilege/sysUser/sysUserList', '1', '2', '2020-02-29 15:48:16', '2', '2020-02-29 16:12:06', '', NULL, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '瑙掕壊鍚嶇О',
  `dr` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '瑙掕壊鎻忚堪',
  `create_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0f5462af2dad446182d0a2bc37e6a999', 'app管理员', '1', '1', 'app管理员', 'admin', '2020-02-29 16:44:54', NULL, NULL);
INSERT INTO `sys_role` VALUES ('fe9502fa296b4a6fa86812348537d496', '超级管理员', '1', '1', '超级管理员', 'admin', '2020-02-29 16:44:20', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限表id',
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单id',
  `button_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮id',
  `parent_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('00a38ee67c064df7bc262bf140afba97', 'fe9502fa296b4a6fa86812348537d496', '95456a9c2e394441b97671dbe19137bb', 'b24fa16517354646ac49bd535b081f80', NULL);
INSERT INTO `sys_role_permission` VALUES ('071e82e566994ff0ab44d2957903340c', 'fe9502fa296b4a6fa86812348537d496', '3f913fd812364619a8efe9b727696a98', NULL, '448be58a26b7400dbe7fe7757f352248');
INSERT INTO `sys_role_permission` VALUES ('07bfeef5b5f349f0a08610adc61e01ba', 'fe9502fa296b4a6fa86812348537d496', '3f913fd812364619a8efe9b727696a98', '0f9366c3ac244b7eab15aeaa6a03c943', NULL);
INSERT INTO `sys_role_permission` VALUES ('09e733afd0df4b8f95a16227a545e8cf', '0f5462af2dad446182d0a2bc37e6a999', '48301af1d1964ade807d40e95f3bf369', NULL, '');
INSERT INTO `sys_role_permission` VALUES ('0ca82902b0b845178138bf851453d239', 'fe9502fa296b4a6fa86812348537d496', 'fd8a867a59924bbcae445046d7e1a5c2', '2d062a47fd8a4e1a98b552ef60c37643', NULL);
INSERT INTO `sys_role_permission` VALUES ('10df333a36cb4019aac2e65f601a5684', 'fe9502fa296b4a6fa86812348537d496', 'eee385792dc44c7191641f4cc70e007c', 'eac4c809840e46d6ada8ed12773d831b', NULL);
INSERT INTO `sys_role_permission` VALUES ('13b69859ef37467f8778e535b0919a98', 'fe9502fa296b4a6fa86812348537d496', 'eee385792dc44c7191641f4cc70e007c', 'f1eb624af2cf42448671288969f9f6a5', NULL);
INSERT INTO `sys_role_permission` VALUES ('181d5259c76c4e66b9c031ebfafbb9f9', 'fe9502fa296b4a6fa86812348537d496', '48301af1d1964ade807d40e95f3bf369', NULL, '');
INSERT INTO `sys_role_permission` VALUES ('19ec21d1b83747b7b8bfaa8b664a308b', 'fe9502fa296b4a6fa86812348537d496', '45e2aa89304f43b297f71df411f5229b', NULL, '069ab7cacef24e57813b315c199e66c8');
INSERT INTO `sys_role_permission` VALUES ('2344f22fe5744290b7c2022a4dde9b8c', 'fe9502fa296b4a6fa86812348537d496', '95456a9c2e394441b97671dbe19137bb', NULL, '448be58a26b7400dbe7fe7757f352248');
INSERT INTO `sys_role_permission` VALUES ('33e705dfea654736b9888a5661baa922', 'fe9502fa296b4a6fa86812348537d496', 'e4b0afc1df694c12b54f3d8e58c394ca', '3700186ba1a5417490322f4d337a7ea4', NULL);
INSERT INTO `sys_role_permission` VALUES ('3f342f5959494b1f913b75c6f254971c', 'fe9502fa296b4a6fa86812348537d496', '3f913fd812364619a8efe9b727696a98', '13f2ea34b9d5447e9d04943395aad098', NULL);
INSERT INTO `sys_role_permission` VALUES ('4814e92f94f44238918198850bf06d66', 'fe9502fa296b4a6fa86812348537d496', 'f590eab1dd624836b07332f072db2390', NULL, '48301af1d1964ade807d40e95f3bf369');
INSERT INTO `sys_role_permission` VALUES ('50010a1af8134a23b42a666e451dccff', 'fe9502fa296b4a6fa86812348537d496', '75fca2801cd0415cb9e865871d2f85fc', NULL, '45e2aa89304f43b297f71df411f5229b');
INSERT INTO `sys_role_permission` VALUES ('5829094809fd4ff79a60c1a1146a9704', '0f5462af2dad446182d0a2bc37e6a999', 'dc87ede61db24e7b908892c0ae5d8016', NULL, '48301af1d1964ade807d40e95f3bf369');
INSERT INTO `sys_role_permission` VALUES ('6ce80eead10c4209bcd8c4a41daa4d7a', 'fe9502fa296b4a6fa86812348537d496', '069ab7cacef24e57813b315c199e66c8', NULL, '');
INSERT INTO `sys_role_permission` VALUES ('770ca9275e0f4c85bd5911bbae54feb6', 'fe9502fa296b4a6fa86812348537d496', 'eee385792dc44c7191641f4cc70e007c', 'eadcec66d32c4eb0998eab7b2bb9abfd', NULL);
INSERT INTO `sys_role_permission` VALUES ('8170536a96474e9dbbb88ac754ced8a1', 'fe9502fa296b4a6fa86812348537d496', '3f913fd812364619a8efe9b727696a98', '1b38f27b09d6436cbd7ce578f28f7a8e', NULL);
INSERT INTO `sys_role_permission` VALUES ('852cedcdb191456ca7935ff5058fac05', 'fe9502fa296b4a6fa86812348537d496', '448be58a26b7400dbe7fe7757f352248', NULL, '069ab7cacef24e57813b315c199e66c8');
INSERT INTO `sys_role_permission` VALUES ('880dc12c06114efaacdd1a9672eb0953', 'fe9502fa296b4a6fa86812348537d496', 'e4b0afc1df694c12b54f3d8e58c394ca', '366a577f08674f8bbe4c3f7164e908a7', NULL);
INSERT INTO `sys_role_permission` VALUES ('93897c2abb48418fa9f057e549878758', 'fe9502fa296b4a6fa86812348537d496', 'e4b0afc1df694c12b54f3d8e58c394ca', '5a193e98280e46e1b6a86c68aeb653eb', NULL);
INSERT INTO `sys_role_permission` VALUES ('b133819b15994dfea6cf802ed8dd0fb1', 'fe9502fa296b4a6fa86812348537d496', '3f913fd812364619a8efe9b727696a98', 'd2e2d7bb8373428bab6b03d7aca33f7d', NULL);
INSERT INTO `sys_role_permission` VALUES ('b9ec845a8f9d4581a49e4f66a058575b', 'fe9502fa296b4a6fa86812348537d496', 'e4b0afc1df694c12b54f3d8e58c394ca', '9cdd5443365f4e38a478d6e36c6aa40e', NULL);
INSERT INTO `sys_role_permission` VALUES ('c965865749c2478299591328135e8e6c', 'fe9502fa296b4a6fa86812348537d496', 'fd8a867a59924bbcae445046d7e1a5c2', 'e983e5dbfd6e41cbb24553cd9ae007f3', NULL);
INSERT INTO `sys_role_permission` VALUES ('cccb8844ab29475e9ac17e05b27f5efd', 'fe9502fa296b4a6fa86812348537d496', '0dfe3dc17e834905ad26f242e62e2938', NULL, 'f0d3b0b49bde44d78b7a1fddbb7fbeff');
INSERT INTO `sys_role_permission` VALUES ('d18179f3228c479bb33041fe3462626a', 'fe9502fa296b4a6fa86812348537d496', 'eee385792dc44c7191641f4cc70e007c', '7b373815054e49bfabaf0273f0419bc9', NULL);
INSERT INTO `sys_role_permission` VALUES ('d39e254065b44f9d89677c8f06733afa', 'fe9502fa296b4a6fa86812348537d496', 'eee385792dc44c7191641f4cc70e007c', 'f98965a6d0e84829a3c59644b9108cc8', NULL);
INSERT INTO `sys_role_permission` VALUES ('d5f96ec073414523a85de6a4ee3df657', 'fe9502fa296b4a6fa86812348537d496', 'e4b0afc1df694c12b54f3d8e58c394ca', NULL, '069ab7cacef24e57813b315c199e66c8');
INSERT INTO `sys_role_permission` VALUES ('d96d7f5cc6c44ea4a3a6d8205eb90f60', 'fe9502fa296b4a6fa86812348537d496', 'dc87ede61db24e7b908892c0ae5d8016', NULL, '48301af1d1964ade807d40e95f3bf369');
INSERT INTO `sys_role_permission` VALUES ('dee1a26eb05d4bafaf2a981d9dad83ee', 'fe9502fa296b4a6fa86812348537d496', '3f913fd812364619a8efe9b727696a98', '98837acc49d5480793ebd949d6410548', NULL);
INSERT INTO `sys_role_permission` VALUES ('df1b89f6e3e348bfb2b50a49685163d0', 'fe9502fa296b4a6fa86812348537d496', '95456a9c2e394441b97671dbe19137bb', 'c8b689b3a9484fb69e4532a185928baa', NULL);
INSERT INTO `sys_role_permission` VALUES ('df44329da547494095c6a1bdf4194cac', 'fe9502fa296b4a6fa86812348537d496', 'f0d3b0b49bde44d78b7a1fddbb7fbeff', NULL, '');
INSERT INTO `sys_role_permission` VALUES ('e13d8442be4f4e43af17879448805c2f', '0f5462af2dad446182d0a2bc37e6a999', 'f0d3b0b49bde44d78b7a1fddbb7fbeff', NULL, '');
INSERT INTO `sys_role_permission` VALUES ('e4d2a538a7c04828a39e527b51a31658', 'fe9502fa296b4a6fa86812348537d496', '3f913fd812364619a8efe9b727696a98', 'd94b592715de4489b5bc61cfc767ac18', NULL);
INSERT INTO `sys_role_permission` VALUES ('e5978cb7d7ba4eb893048b5e6afa21c4', 'fe9502fa296b4a6fa86812348537d496', '7b3ede7ac32942e5935c4f0007ba5f34', NULL, '069ab7cacef24e57813b315c199e66c8');
INSERT INTO `sys_role_permission` VALUES ('ea31f372c91f48ce835f17b110e67932', 'fe9502fa296b4a6fa86812348537d496', 'fd8a867a59924bbcae445046d7e1a5c2', 'be0eb3b0c68441bc8b6de554082821f3', NULL);
INSERT INTO `sys_role_permission` VALUES ('ecf2980f6ee94566be0723f199bc3869', '0f5462af2dad446182d0a2bc37e6a999', '0dfe3dc17e834905ad26f242e62e2938', NULL, 'f0d3b0b49bde44d78b7a1fddbb7fbeff');
INSERT INTO `sys_role_permission` VALUES ('f40d1d5eb1924dc38f3fc5d932aacdfa', 'fe9502fa296b4a6fa86812348537d496', 'fd8a867a59924bbcae445046d7e1a5c2', NULL, '7b3ede7ac32942e5935c4f0007ba5f34');
INSERT INTO `sys_role_permission` VALUES ('f88f1c1b37de452493e538aee36bc15a', 'fe9502fa296b4a6fa86812348537d496', 'eee385792dc44c7191641f4cc70e007c', NULL, '7b3ede7ac32942e5935c4f0007ba5f34');
INSERT INTO `sys_role_permission` VALUES ('fe120763b24347249f1c89e3f9ad850e', 'fe9502fa296b4a6fa86812348537d496', '95456a9c2e394441b97671dbe19137bb', '11a09de082b949bcbdd400723c2b3887', NULL);
INSERT INTO `sys_role_permission` VALUES ('ff32495c1ea94520afe11a1cbe5e8331', '0f5462af2dad446182d0a2bc37e6a999', 'f590eab1dd624836b07332f072db2390', NULL, '48301af1d1964ade807d40e95f3bf369');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `role_ids` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '鑱岀Оid',
  `birthday` datetime(6) NULL DEFAULT NULL COMMENT '生日',
  `identity_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密用的盐值',
  `login_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `head_portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('27de363882514f65b1763eea6724e665', 'aaaa', '', '7b76e3547ffdd04f6b4c1745d2885b66', '15236545856', '', '0f5462af2dad446182d0a2bc37e6a999', NULL, '', '78cfb11894ed36f0b24dd82714654e5a', 'aaaa', '1', '', NULL, '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:50:15', NULL, NULL);
INSERT INTO `sys_user` VALUES ('4fcee6c3f7164300ab75e67b32871145', 'admin', '0', 'c23d007f772aa61cb203b1c16092bef7', '1577023143', '1542@qq.com', 'fe9502fa296b4a6fa86812348537d496', NULL, '', 'e9489043d06ee1c243981c4bb72ff9bb', 'admin', '1', '', 'http://120.25.197.11/file/1f59f7f4-4303-4672-a239-0cf19a8b23c4', '', '2020-02-29 16:02:44', '4fcee6c3f7164300ab75e67b32871145', '2020-02-29 16:45:15');

SET FOREIGN_KEY_CHECKS = 1;

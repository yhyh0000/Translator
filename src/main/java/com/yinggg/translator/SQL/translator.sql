SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` int NOT NULL AUTO_INCREMENT COMMENT '用户 ID，自增长的主键',
                           `username` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名，唯一且不能为空',
                           `password` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码，存储经过加密处理后的密码值',
                           `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户电子邮箱，可为空',
                           `created_at` datetime NULL DEFAULT NULL COMMENT '用户注册时间，默认当前时间',
                           PRIMARY KEY (`id`) USING BTREE,
                           INDEX `account`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '123456', NULL, NULL);
INSERT INTO `t_user` VALUES (2, 'test', '123456', NULL, NULL);

-- ----------------------------
-- Table structure for t_question_bank
-- ----------------------------
DROP TABLE IF EXISTS `t_question_bank`;
CREATE TABLE `t_question_bank`  (
                                    `id` int NOT NULL AUTO_INCREMENT COMMENT '句子唯一标识',
                                    `source_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '源语言句子',
                                    `target_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '目标语言句子',
                                    `user_id` int NULL DEFAULT NULL COMMENT '上传句子的用户id，关联用户表',
                                    `upload_time` datetime NULL DEFAULT NULL COMMENT '句子上传时间',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `user`(`user_id` ASC) USING BTREE,
                                    CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_question_bank
-- ----------------------------
INSERT INTO `t_question_bank` VALUES (5, 'On a Harmonious Dormitory Life', '论和谐的宿舍生活', 1, '2024-11-19 16:22:28');
INSERT INTO `t_question_bank` VALUES (6, 'When it comes to the dormitory life, we have to face theproblem that sometimes the harmony in the dormitory is disturbed in one way or another', '说到宿舍生活，我们不得不面对宿舍里的和谐有时会受到这样或那样的干扰', 1, '2024-11-19 16:22:28');

-- ----------------------------
-- Table structure for t_translation_records
-- ----------------------------
DROP TABLE IF EXISTS `t_translation_records`;
CREATE TABLE `t_translation_records`  (
                                          `id` int NOT NULL AUTO_INCREMENT COMMENT '翻译记录 ID，自增长的主键',
                                          `user_id` int NULL DEFAULT NULL COMMENT '提交翻译请求的用户 ID，外键关联用户表',
                                          `original_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原文内容',
                                          `translated_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '翻译后的内容',
                                          `source_language` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原文语言',
                                          `target_language` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '目标翻译语言',
                                          `translation_date` datetime NOT NULL COMMENT '翻译时间，默认当前时间',
                                          `differences` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用户自行翻译与软件翻译结果的差异（可存储为特定格式，如 JSON）',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_translation_records
-- ----------------------------
INSERT INTO `t_translation_records` VALUES (2, 2, 'world', '世界', 'English', 'Chinese', '2024-11-14 15:47:08', '世界');
INSERT INTO `t_translation_records` VALUES (3, 1, 'gril', '女孩', 'English', 'Chinese', '2024-11-14 15:47:08', '女孩');

SET FOREIGN_KEY_CHECKS = 1;

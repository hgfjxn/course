
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pe_course
-- ----------------------------
DROP TABLE IF EXISTS `pe_course`;
CREATE TABLE `pe_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `title` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程名称',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '课程描述',
  `students` int(255) DEFAULT '0' COMMENT '容纳学生',
  `stars` int(255) DEFAULT NULL COMMENT '星级',
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地点',
  `price` double(10,2) DEFAULT NULL COMMENT '价格',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
-- ----------------------------
-- Table structure for pr_user_course
-- ----------------------------
DROP TABLE IF EXISTS `pr_user_course`;
CREATE TABLE `pr_user_course` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `course_id` int(11) NOT NULL COMMENT '课程Id',
  PRIMARY KEY (`user_id`,`course_id`),
  KEY `course_id_foreign_key` (`course_id`),
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `course_id_foreign_key` FOREIGN KEY (`course_id`) REFERENCES `pe_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;

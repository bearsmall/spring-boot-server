SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `projecttask`;
CREATE TABLE `projecttask` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `dir` varchar(256) NOT NULL,
  `zip_size` int(11) NOT NULL,
  `upload_time` timestamp(6) NULL DEFAULT NULL,
  `check_start_time` timestamp(6) NULL DEFAULT NULL,
  `check_end_time` timestamp(6) NULL DEFAULT NULL,
  `language` varchar(45) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `state` int(11) DEFAULT '1',
  `vul_high` int(11) DEFAULT '0',
  `vul_mid` int(11) DEFAULT '0',
  `vul_low` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `javadependency`;
CREATE TABLE `javadependency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupid` varchar(45) DEFAULT NULL,
  `artifactid` varchar(45) DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `sha1` varchar(45) DEFAULT NULL,
  `scope` varchar(45) DEFAULT NULL,
  `option` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

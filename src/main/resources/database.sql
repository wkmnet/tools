DROP TABLE IF EXISTS `idea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idea` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(200) DEFAULT NULL COMMENT 'username',
  `content` varchar(3000) DEFAULT NULL COMMENT 'text',
  `license` varchar(3000) DEFAULT NULL COMMENT 'license',
  `action` varchar(100) DEFAULT NULL COMMENT 'access-action',
  `user_agent` varchar(3000) DEFAULT NULL COMMENT 'user-agent',
  `user_address` varchar(50) DEFAULT NULL COMMENT 'ip-address',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1654 DEFAULT CHARSET=utf8;

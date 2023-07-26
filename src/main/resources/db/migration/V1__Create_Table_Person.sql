
DROP TABLE IF EXISTS `tb_person`;
CREATE TABLE `tb_person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `last_name` varchar(80) NOT NULL,
  `gender` varchar(11) NOT NULL,
  `first_name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
);

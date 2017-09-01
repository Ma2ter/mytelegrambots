CREATE DATABASE `mytbdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `user_states` (
  `iduser_states` int(11) NOT NULL AUTO_INCREMENT,
  `entity_type` varchar(45) NOT NULL,
  `entity_id` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  PRIMARY KEY (`iduser_states`),
  UNIQUE KEY `iduser_states_UNIQUE` (`iduser_states`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

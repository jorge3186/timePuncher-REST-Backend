CREATE TABLE IF NOT EXISTS `users` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(150) NOT NULL,
	`enabled` tinyint(4) NOT NULL,
	`password` varchar(100) NOT NULL,
	PRIMARY KEY (`user_id`),
	UNIQUE KEY `user_id` (`user_id`))
	ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1

	
CREATE TABLE IF NOT EXISTS `basic_info` (
  	`user_id` int(11) NOT NULL,
  	`firstname` varchar(45) NOT NULL,
  	`lastname` varchar(60) NOT NULL,
  	`alt_email` varchar(150) NOT NULL,
  	`address` varchar(250) NOT NULL,
  	`city` varchar(150) NOT NULL,
  	`state` varchar(2) NOT NULL,
  	`zipcode` varchar(12) NOT NULL,
  	`phone` varchar(11) DEFAULT NULL,
  	PRIMARY KEY (`user_id`),
  	CONSTRAINT `fk_user_id_basic_info` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE)
  	ENGINE=InnoDB DEFAULT CHARSET=latin1
  	
  	
CREATE TABLE IF NOT EXISTS `work_info` (
  	`user_id` int(11) NOT NULL,
  	`employeeId` int(11) DEFAULT NULL,
  	`hourly_wage` decimal(13,2) DEFAULT NULL,
  	`salary_wage` decimal(13,2) DEFAULT NULL,
  	`department` varchar(150) DEFAULT NULL,
  	PRIMARY KEY (`user_id`),
  	CONSTRAINT `fk_user_id_work_info` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE)
  	ENGINE=InnoDB DEFAULT CHARSET=latin1 	
 
  	
CREATE TABLE IF NOT EXISTS `authorities` (
  	`user_id` int(10) NOT NULL,
  	`authority` varchar(50) NOT NULL,
  	PRIMARY KEY (`user_id`),
  	CONSTRAINT `fk_user_id_authority` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE)
  	ENGINE=InnoDB DEFAULT CHARSET=latin1
  	
  	
CREATE TABLE IF NOT EXISTS `punches` (
  	`user_id` int(11) NOT NULL,
  	`status` varchar(40) NOT NULL,
  	`time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  	`punch_id` int(11) NOT NULL AUTO_INCREMENT,
  	PRIMARY KEY (`punch_id`),
  	KEY `fk_user_id_punches` (`user_id`),
  	CONSTRAINT `fk_user_id_punches` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`))
  	ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1
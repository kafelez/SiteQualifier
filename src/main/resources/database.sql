

CREATE DATABASE marfeel;

GRANT USAGE ON *.* TO siteuser@localhost IDENTIFIED BY 'site';

GRANT ALL PRIVILEGES ON marfeel.* TO siteuser@localhost;

FLUSH PRIVILEGES;


use marfeel;


CREATE TABLE `websites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL,
  `rank` varchar(10) ,
  `qualify` varchar(10) ,
  `error` varchar(50) ,
  `creationtime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `url` (`url`),
  KEY `qualify` (`qualify`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;



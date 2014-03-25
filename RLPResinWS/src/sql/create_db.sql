drop schema ResinDB;

create schema ResinDB;

use ResinDB;

CREATE TABLE `users` (
  `userName` varchar(32) NOT NULL,
  `password` varchar(20) NOT NULL,
  `fullName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

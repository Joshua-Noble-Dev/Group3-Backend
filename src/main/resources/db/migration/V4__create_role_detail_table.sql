CREATE TABLE Role_Detail (
	`roleDetailId` smallint PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `detailName` varchar(100) NOT NULL,
    `description` varchar(7500) NOT NULL,
    `responsibilities` varchar(3500) NOT NULL,
    `link` varchar(1000) NOT NULL
);
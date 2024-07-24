CREATE TABLE Role_Detail (
	`RoleDetailId` smallint PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `roleName` varchar(100) NOT NULL,
    `description` varchar(7500) NOT NULL,
    `responsibilities` varchar(3500) NOT NULL,
    `link` varchar(1000) NOT NULL,
    RoleId int NOT NULL,
    FOREIGN KEY (RoleId) REFERENCES Role (RoleId)
);
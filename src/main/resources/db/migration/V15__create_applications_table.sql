create table `Applications` (
`applicationId` int PRIMARY KEY AUTO_INCREMENT NOT NULL,
`id` int NOT NULL,
`userId` int NOT NULL,
`cvUrl` varchar(1000) NOT NULL,
`status` varchar(100) NOT NULL,
foreign key (id) references Role(id),
foreign key (userId) references User(userId)
);
create table `User` (
`username` varchar(100) not null,
`password` varchar(100) not null,
`UserRoleID` int default 2 not null,
foreign key (UserRoleID) references UserRole(UserRoleID)
);
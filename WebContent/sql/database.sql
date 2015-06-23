#Contacts database script

#1.Create database Contacts
Create database Contacts;

use Contacts;

#create table Class
create table Class
(
	ID int auto_increment primary key,
    Name varchar(32),
    Vocational varchar(128)
);

#create table Contact
create table Contact
(
	ID int auto_increment primary key,
    Name varchar(32),
    Cid int,
    Phone varchar(16),
    Email varchar(64),
    Living varchar(128),
    Company varchar(128),
    Remark varchar(1024)
);

alter table Contact 
	add column AddDate datetime,
    add column UpdateDate datetime,
    add column IP varchar(32);

alter table Contact
	add column Password varchar(128);
	

create table PasswordChangeRequest
(
	ID varchar(36) primary key,
	AddTime datetime,
	Email varchar(64),
	`Status` int
);
#select * from Contact;
create table Gallery
(
	ID int auto_increment primary key,
	CID int,
	CName varchar(128),
	Title varchar(128),
	Caption varchar(256),
	Content varchar(2048),
	Picture varchar(128),
	AddDate datetime,
	UpdateDate datetime
);
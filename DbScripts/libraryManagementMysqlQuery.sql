create database LibraryManagement;
use LibraryManagement;
create table Book(
bookId int auto_increment primary key, 
bookName varchar(50),
author varchar(50),
review int,
edition int,
quantity int,
genre varchar(50)
);
insert into Book(bookName,author,review,edition,quantity,genre) 
values('Atomic Habit', 'Gery Chapman', 4, 2011, 8, 'Self-Help');
update book set bookName = 'Atomic Habit2',author='Gery Chapman2',review=5,edition=2010,quantity=10,genre='Self-help2' where bookId = 1;
select * from Book;
drop table user;
create table Users(
userId int auto_increment primary key,
userStatus bit,
address varchar(250),
contactNo long,
regDate date,
userName varchar(50),
roleId int,
emailId varchar(100),
userPassword varchar(15)
);
insert into User(readerName,address,contactNo,email,registrationDate,roleId,pwd)
values('Vishal Shah', 'Nandanagar, Indore', 5698564785, 'vishal@gmail.com', '0000');

create table Genre(
genreId int auto_increment primary key, 
genreName varchar(1000)
);
insert into Genre(genreName)
values(('Self-help'),('Fiction'),('Educational'),('Biography'),('Bestselling'),('Adventure'),('Crime/Thriller/Suspense'),('Classics'),('Comic'),('Graphic Novel
'),('Philosophy'),('Political'),('Political'),('Romance'),('Sports'),('Travel'),('Autobiography')
);
select * from Genre;
insert into Genre(genreName)values(('Fiction'));
insert into Genre(genreName)values(('Educational'));
insert into Genre(genreName)values(('Romance'));
insert into Genre(genreName)values(('Travel'));
select * from Genre;
create table CustomerFeedback(
readerID int auto_increment primary key,
comments varchar(500), 
date datetime,
isApproved boolean,
bookid int,
review int);
create table BookBorrow(
userId int,
	bookId int,
	borrowDate datetime,
	returnDate datetime
    );
create table Review(
reviewId int auto_increment primary key,
userId int,
bookId int,
stars int,
comments varchar(500)
);

create table Subscription(
userId int,
amount long, 
dateOfSubscription datetime, 
Validity da
	

use LibraryManagement;
userId int(11) AI PK 
userStatus varchar(10) 
registrationDate date 
readerName varchar(50) 
contactNo mediumtext 
emailId varchar(25) 
address varchar(100) 
roleId int(11) 
readerPassword varchar(15)

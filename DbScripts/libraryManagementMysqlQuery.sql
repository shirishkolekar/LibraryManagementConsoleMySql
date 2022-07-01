-- drop database LibraryManagement;
create database LibraryManagement;
use LibraryManagement;

create table Roles(roleId int auto_increment primary key, roleName varchar(15));
insert into Roles(roleName) values('Admin'),('Librarian'),('Reader');
select * from Roles;

create table Users(
userId int auto_increment primary key,userStatus bit,address varchar(250),contactNo long,registrationDate date,userName varchar(50),
roleId int, emailId varchar(100), userPassword varchar(50), foreign key(roleId) references Roles(roleId)
);
insert into Users(userStatus, address,contactNo,registrationDate,userName,roleId,emailId,userPassword) values
(1, 'Ahmedabad',9876543210,'2021-12-31','admin',1,'admin@library.com','admin');
-- values('Vishal Shah', 'Nandanagar, Indore', 5698564785, 'vishal@gmail.com', '0000');

select * from Users;

create table Genres(genreId int auto_increment primary key, genreName varchar(1000));
insert into Genres(genreName)
values('Self-help'),('Fiction'),('Educational'),('Biography'),('Bestselling'),('Adventure'),('Crime/Thriller/Suspense'),('Classics'),('Comic'),('Graphic Novel
'),('Philosophy'),('Political'),('Political'),('Romance'),('Sports'),('Travel'),('Autobiography');
select * from Genres;

create table Books(
bookId int auto_increment primary key, bookName varchar(50),author varchar(50),review int,edition int,quantity int,genreId int,
foreign key(genreId) references Genres(genreId)
);
insert into Books(bookName, author, review, edition, quantity, genreId) values('Atomic Habit', 'Gery Chapman', 4, 2011, 8, 1);
-- update book set bookName = 'Atomic Habit2',author='Gery Chapman2',review=5,edition=2010,quantity=10,genre='Self-help2' where bookId = 1;
select * from Books;

create table CustomerFeedback(
customerFeedbackId int auto_increment primary key, userId int, comments varchar(500), feedbackDate date, isApproved bit, 
bookid int, review int,
foreign key(userId) references Users(userId),foreign key(bookid) references Books(bookid)
);

select * from CustomerFeedback;

create table BookBorrow( bookBorrowId int auto_increment primary key,
userId int, bookId int, borrowDate date, returnDate date, borrowApproved bit, returnApproved bit,
foreign key(userId) references Users(userId),foreign key(bookid) references Books(bookid));

create table Review(reviewId int auto_increment primary key,userId int,bookId int,stars int,comments varchar(500),
foreign key(userId) references Users(userId),foreign key(bookid) references Books(bookid)
);

create table Subscription( subscriptionId int auto_increment primary key, 
userId int,amount decimal, dateOfSubscription date, validity date, approved bit,foreign key(userId) references Users(userId));

create database libraryManagementDb;

use libraryManagementDb;

create table books
(
	bookId int auto_increment PRIMARY key,
	bookName varchar(50) not null,
	author varchar(50) not null,
	review int,
	edition int,
	quantitiy int,
	genre varchar(50) not null
);

insert into books(bookName,author,review,edition,quantitiy,genre)
values ('My E Book','Shirish',5,2022,100,'SciFi');
insert into books(bookName,author,review,edition,quantitiy,genre)
values ('My E Book2','Shirish',5,2022,100,'SciFi');

select * from books;

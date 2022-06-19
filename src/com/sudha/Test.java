package com.sudha;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) 
	{
		BookDAO bookDAO = new BookDAOImpl();		
		ArrayList<Book> books = bookDAO.getAllBooks();	
		for(Book b : books)
		{
			System.out.println(b.getBookName());
		}
	}
}
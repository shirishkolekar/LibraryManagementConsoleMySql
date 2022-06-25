package com.sudha;

public class Book {
	
	private int bookId;
	private String bookName;
	private String author;
	private int review;
	private int edition;
	private int quantity;
	private String genre;

	public int getBookId() 
	{
		return bookId;
	}
	
	public void setBookId(int bookId)
	{
		this.bookId = bookId;
	}
	
	public String getBookName() 
	{
		return bookName;
	}
	
	public void setBookName(String bookName) 
	{
		this.bookName = bookName;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String author) 
	{
		this.author = author;
	}
	
	public int getReview() 
	{
		return review;
	}
	
	public void setReview(int review) 
	{
		this.review = review;
	}
	
	public int getEdition() 
	{
		return edition;
	}
	
	public void setEdition(int edition) 
	{
		this.edition = edition;
	}
	
	public int getQuantity() 
	{
		return quantity;
	}
	
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	public String getGenre() 
	{
		return genre;
	}
	
	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public Book() 
	{

		this.bookId = 0;
		this.bookName = "";
		this.author = "";
		this.review = 0;
		this.edition = 0;
		this.quantity = 0;
		this.genre = "";
	}

	public Book(int bookId, String bookName, String author, int edition, int quantity, String genre) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.review = 0;
		this.edition = edition;
		this.quantity = quantity;
		this.genre = genre;
	}
}
//have to change genre to genreID
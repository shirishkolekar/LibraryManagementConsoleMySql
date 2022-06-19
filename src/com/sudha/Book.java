package com.sudha;

public class Book {
	
	private int bookId;
	private String bookName;
	private String author;
	private int review;
	private int edition;
	private int quantitiy;
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
	
	public int getQuantitiy() 
	{
		return quantitiy;
	}
	
	public void setQuantitiy(int quantitiy) 
	{
		this.quantitiy = quantitiy;
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
		this.quantitiy = 0;
		this.genre = "";
	}

	public Book(int bookId, String bookName, String author, int edition, int quantitiy, String genre) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.review = 0;
		this.edition = edition;
		this.quantitiy = quantitiy;
		this.genre = genre;
	}
}

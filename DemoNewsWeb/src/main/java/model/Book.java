package model;

import java.time.LocalDateTime;

public class Book {
	private int bookId;
	private String title;
	private String author;
	private float price;
	private String imagePath;
	private float quantityInStock;
	private String detail;
	private LocalDateTime createDate;

	
	
	
	public Book(int bookId, String title, String author, float price, String imagePath, float quantityInStock,
			String detail, LocalDateTime createDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.imagePath = imagePath;
		this.quantityInStock = quantityInStock;
		this.detail = detail;
		this.createDate = createDate;
	}


	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public float getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(float quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

}

package model;

public class CartItem {
	// cuon sach duoc su dung
	private Book selectedBook;
	// so luong
	private int quantity;

	public CartItem(Book selectedBook, int quantity) {
		super();
		this.selectedBook = selectedBook;
		this.quantity = quantity;
	}

	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

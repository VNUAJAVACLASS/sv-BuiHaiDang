package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	// Danh sach chon dua vao gio hang
	private Map<Integer, CartItem> cartItemList;
	
	private float totalCost; // tong tien
	
	private String paymentModel; // hinh thuc thanh toan 
	
	private boolean paymentStatus; // trang thai
	
	public Cart() {
		 cartItemList = new HashMap<Integer, CartItem>();
		 totalCost = 0;
	}
	
	
	// them mat hang vao gio hang, neu trung mat hang thi thay the
	public void addCartItemToCart(int bookId,CartItem cartItem) {
			CartItem oldCartItem = cartItemList.get(bookId);
			
			// neu trung thi thay the
			if(oldCartItem !=  null) {
				totalCost -= oldCartItem.getQuantity() * oldCartItem.getSelectedBook().getBookId(); 
			}
			
			// thay the neu da ton tai
			cartItemList.put(bookId, cartItem);
			
			// cap nhat so tien trong gio hang 
			totalCost += cartItem.getQuantity() * cartItem.getSelectedBook().getPrice();
	}
	
	
	// Xoa mat hang khoi gio hang
	public void removeCartItemFromCart(int bookId) {
		CartItem cartItem = cartItemList.get(bookId);
		cartItemList.remove(bookId);
		
		// cap nhat so tien
		totalCost -= cartItem.getQuantity() *  cartItem.getSelectedBook().getPrice();
	}


	public Map<Integer, CartItem> getCartItemList() {
		return cartItemList;
	}


	public void setCartItemList(Map<Integer, CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}


	public float getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}


	public String getPaymentModel() {
		return paymentModel;
	}


	public void setPaymentModel(String paymentModel) {
		this.paymentModel = paymentModel;
	}


	public boolean isPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
}

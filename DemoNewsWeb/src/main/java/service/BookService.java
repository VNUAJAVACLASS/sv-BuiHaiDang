package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.DBConnection;
import model.Book;

public class BookService {

	// Lấy tất cả sách
	public List<Book> getAllBooks() {
		List<Book> list = new ArrayList<>();
		String sql = "SELECT * FROM book";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(new Book(rs.getInt("book_id"), rs.getString("title"), rs.getString("author"),
						rs.getFloat("price"), rs.getString("image_path"), rs.getFloat("quantity_in_stock"),
						rs.getString("detail"), rs.getTimestamp("create_date").toLocalDateTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Tìm sách theo id
	public Book findById(int id) {
		String sql = "SELECT * FROM book WHERE book_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Book(rs.getInt("book_id"), rs.getString("title"), rs.getString("author"),
							rs.getFloat("price"), rs.getString("image_path"), rs.getFloat("quantity_in_stock"),
							rs.getString("detail"), rs.getTimestamp("create_date").toLocalDateTime());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Thêm sách mới
	public void insertBook(Book book) {
		String sql = "INSERT INTO book(title, author, price, image_path, quantity_in_stock, detail, create_date) VALUES(?, ?, ?, ?,?,?,?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setFloat(3, book.getPrice());
			ps.setString(4, book.getImagePath());
			ps.setFloat(5, book.getQuantityInStock());
			ps.setString(6, book.getDetail());
			ps.setTimestamp(7, Timestamp.valueOf(book.getCreateDate()));

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Cập nhật sách
	public void updateBook(Book book) {
		String sql = "UPDATE book SET title=?, author=?, price=?, image_path=?, quantity_in_stock=?, detail=?, create_date=?   WHERE book_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setFloat(3, book.getPrice());
			ps.setString(4, book.getImagePath());
			ps.setFloat(5, book.getQuantityInStock());
			ps.setString(6, book.getDetail());
			ps.setTimestamp(7, Timestamp.valueOf(book.getCreateDate()));
			ps.setInt(8, book.getBookId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Xóa sách
	public void deleteBook(int id) {
		String sql = "DELETE FROM book WHERE book_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

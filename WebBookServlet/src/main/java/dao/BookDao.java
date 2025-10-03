package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import dao.DBConnection;

public class BookDao {
	 private Connection jdbcConnection = null;
     private PreparedStatement jdbcPreparedStatement = null;
     private ResultSet jdbcResultSet = null;
     
    // Lấy tất cả sách
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book";
       

        try {
            jdbcConnection = DBConnection.getConnection();
            jdbcPreparedStatement = jdbcConnection.prepareStatement(sql);
            jdbcResultSet = jdbcPreparedStatement.executeQuery();

            while (jdbcResultSet.next()) {
                Book book = new Book(
                    jdbcResultSet.getInt("book_id"),
                    jdbcResultSet.getString("title"),
                    jdbcResultSet.getString("author"),
                    jdbcResultSet.getFloat("price"),
                    jdbcResultSet.getString("image_path"),
                    jdbcResultSet.getInt("quantity_in_stock"),
                    jdbcResultSet.getString("detail"),
                    jdbcResultSet.getTimestamp("create_date").toLocalDateTime()
                );
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jdbcResultSet, jdbcPreparedStatement, jdbcConnection);
        }

        return bookList;
    }

    // Tìm sách theo ID
    public Book findById(int id) {
        String sql = "SELECT * FROM book WHERE book_id=?";
        Book book = null;

        try {
            jdbcConnection = DBConnection.getConnection();
            jdbcPreparedStatement = jdbcConnection.prepareStatement(sql);
            jdbcPreparedStatement.setInt(1, id);
            jdbcResultSet = jdbcPreparedStatement.executeQuery();

            if (jdbcResultSet.next()) {
                book = new Book(
                    jdbcResultSet.getInt("book_id"),
                    jdbcResultSet.getString("title"),
                    jdbcResultSet.getString("author"),
                    jdbcResultSet.getFloat("price"),
                    jdbcResultSet.getString("image_path"),
                    jdbcResultSet.getInt("quantity_in_stock"),
                    jdbcResultSet.getString("detail"),
                    jdbcResultSet.getTimestamp("create_date").toLocalDateTime()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jdbcResultSet, jdbcPreparedStatement, jdbcConnection);
        }

        return book;
    }

    // Lấy sách theo phân trang
    public List<Book> getBooksByPage(int offset, int limit) {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book LIMIT ? OFFSET ?";

        try {
            jdbcConnection = DBConnection.getConnection();
            jdbcPreparedStatement = jdbcConnection.prepareStatement(sql);
            jdbcPreparedStatement.setInt(1, limit);
            jdbcPreparedStatement.setInt(2, offset);
            jdbcResultSet = jdbcPreparedStatement.executeQuery();

            while (jdbcResultSet.next()) {
                Book book = new Book(
                    jdbcResultSet.getInt("book_id"),
                    jdbcResultSet.getString("title"),
                    jdbcResultSet.getString("author"),
                    jdbcResultSet.getFloat("price"),
                    jdbcResultSet.getString("image_path"),
                    jdbcResultSet.getInt("quantity_in_stock"),
                    jdbcResultSet.getString("detail"),
                    jdbcResultSet.getTimestamp("create_date").toLocalDateTime()
                );
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jdbcResultSet, jdbcPreparedStatement, jdbcConnection);
        }

        return bookList;
    }

    // Đếm tổng số sách
    public int getTotalBookCount() {
        String sql = "SELECT COUNT(*) FROM book";
        int count = 0;

        try {
            jdbcConnection = DBConnection.getConnection();
            jdbcPreparedStatement = jdbcConnection.prepareStatement(sql);
            jdbcResultSet = jdbcPreparedStatement.executeQuery();

            if (jdbcResultSet.next()) {
                count = jdbcResultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jdbcResultSet, jdbcPreparedStatement, jdbcConnection);
        }

        return count;
    }

    // Thêm sách mới
    public void insertBook(Book book) {
        String sql = "INSERT INTO book(title, author, price, image_path, quantity_in_stock, detail, create_date) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            jdbcConnection = DBConnection.getConnection();
            jdbcPreparedStatement = jdbcConnection.prepareStatement(sql);
            jdbcPreparedStatement.setString(1, book.getTitle());
            jdbcPreparedStatement.setString(2, book.getAuthor());
            jdbcPreparedStatement.setFloat(3, book.getPrice());
            jdbcPreparedStatement.setString(4, book.getImagePath());
            jdbcPreparedStatement.setFloat(5, book.getQuantityInStock());
            jdbcPreparedStatement.setString(6, book.getDetail());
            jdbcPreparedStatement.setTimestamp(7, Timestamp.valueOf(book.getCreateDate()));
            jdbcPreparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null, jdbcPreparedStatement, jdbcConnection);
        }
    }

    // Cập nhật sách
    public void updateBook(Book book) {
        String sql = "UPDATE book SET title=?, author=?, price=?, image_path=?, quantity_in_stock=?, detail=?, create_date=? WHERE book_id=?";

        try {
            jdbcConnection = DBConnection.getConnection();
            jdbcPreparedStatement = jdbcConnection.prepareStatement(sql);
            jdbcPreparedStatement.setString(1, book.getTitle());
            jdbcPreparedStatement.setString(2, book.getAuthor());
            jdbcPreparedStatement.setFloat(3, book.getPrice());
            jdbcPreparedStatement.setString(4, book.getImagePath());
            jdbcPreparedStatement.setFloat(5, book.getQuantityInStock());
            jdbcPreparedStatement.setString(6, book.getDetail());
            jdbcPreparedStatement.setTimestamp(7, Timestamp.valueOf(book.getCreateDate()));
            jdbcPreparedStatement.setInt(8, book.getBookId());
            jdbcPreparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null, jdbcPreparedStatement, jdbcConnection);
        }
    }

    // Xóa sách
    public void deleteBook(int id) {
        String sql = "DELETE FROM book WHERE book_id=?";

        try {
            jdbcConnection = DBConnection.getConnection();
            jdbcPreparedStatement = jdbcConnection.prepareStatement(sql);
            jdbcPreparedStatement.setInt(1, id);
            jdbcPreparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null, jdbcPreparedStatement, jdbcConnection);
        }
    }

    // Hàm đóng kết nối dùng chung
    private void close(ResultSet jdbcResultSet, PreparedStatement jdbcPreparedStatement, Connection jdbcConnection) {
        try { if (jdbcResultSet != null) jdbcResultSet.close(); } catch (Exception ignored) {}
        try { if (jdbcPreparedStatement != null) jdbcPreparedStatement.close(); } catch (Exception ignored) {}
        try { if (jdbcConnection != null) jdbcConnection.close(); } catch (Exception ignored) {}
    }
}
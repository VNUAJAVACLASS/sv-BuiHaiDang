package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	
	public UserDao() {
		super();
	}

	public UserDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	// check login
	public boolean checkLogin(String username, String password) {
		String sql = "SELECT id FROM tbluser WHERE user_name = ? AND password = ? ";

		boolean isValid = false;

		try (Connection jdbcConnection = DBConnection.getConnection()) {
			PreparedStatement preparedStatement = jdbcConnection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Neu tim thay mot nguoi dung username khop pass
				if (resultSet.next()) {
					// Lay id cua nguoi dung can thiet
					int userId = resultSet.getInt("id");
					System.out.println("UserID: " + userId);

					// danh dau dang nhap thanh cong
					isValid = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}

	public User findUser(String username, String password) {
		String sql = "SELECT * FROM tbluser WHERE user_name = ? AND password = ? ";

		try (Connection jdbcConnection = DBConnection.getConnection()) {
			PreparedStatement preparedStatement = jdbcConnection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Neu tim thay mot nguoi dung username khop pass
				if (resultSet.next()) {
					return new User(resultSet.getString("user_name"), resultSet.getString("password"),
							resultSet.getString("fullname"), resultSet.getByte("role"), resultSet.getString("email"),
							resultSet.getString("mobile"), resultSet.getString("address"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public List<User> getAllUsers() {
	    List<User> userList = new ArrayList<>();
	    String sql = "SELECT * FROM tbluser";

	    try (Connection jdbcConnection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = jdbcConnection.prepareStatement(sql);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        while (resultSet.next()) {
	            User user = new User(
	                resultSet.getString("user_name"),
	                resultSet.getString("password"),
	                resultSet.getString("fullname"),
	                resultSet.getByte("role"),
	                resultSet.getString("email"),
	                resultSet.getString("mobile"),
	                resultSet.getString("address")
	            );
	            userList.add(user);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userList;
	}
	
	
	// Tìm người dùng theo ID
	public User findByUsername(String username) {
	    String sql = "SELECT * FROM tbluser WHERE user_name = ?";
	    try (Connection jdbcConnection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = jdbcConnection.prepareStatement(sql)) {

	        preparedStatement.setString(1, username);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                User user = new User(
	                    resultSet.getString("user_name"),
	                    resultSet.getString("password"),
	                    resultSet.getString("fullname"),
	                    resultSet.getByte("role"),
	                    resultSet.getString("email"),
	                    resultSet.getString("mobile"),
	                    resultSet.getString("address")
	                );
	                return user;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	// Thêm người dùng mới
	public void insertUser(User user) {
	    String sql = "INSERT INTO tbluser (user_name, password, fullname, role, email, mobile, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    try (Connection jdbcConnection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = jdbcConnection.prepareStatement(sql)) {

	        preparedStatement.setString(1, user.getUsername());
	        preparedStatement.setString(2, user.getPassword());
	        preparedStatement.setString(3, user.getFullname());
	        preparedStatement.setByte(4, user.getRole());
	        preparedStatement.setString(5, user.getEmail());
	        preparedStatement.setString(6, user.getMobile());
	        preparedStatement.setString(7, user.getAddress());

	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// Cập nhật người dùng
	public void updateUser(User user) {
	    String sql = "UPDATE tbluser SET password = ?, fullname = ?, role = ?, email = ?, mobile = ?, address = ? WHERE  user_name = ?";
	    try (Connection jdbcConnection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = jdbcConnection.prepareStatement(sql)) {

	        preparedStatement.setString(1, user.getPassword());
	        preparedStatement.setString(2, user.getFullname());
	        preparedStatement.setByte(3, user.getRole());
	        preparedStatement.setString(4, user.getEmail());
	        preparedStatement.setString(5, user.getMobile());
	        preparedStatement.setString(6, user.getAddress());
	        preparedStatement.setString(7, user.getUsername());

	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// Xóa người dùng theo ID
	public void deleteUser(String username) {
	    String sql = "DELETE FROM tbluser WHERE user_name = ?";
	    try (Connection jdbcConnection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = jdbcConnection.prepareStatement(sql)) {

	        preparedStatement.setString(1, username);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}

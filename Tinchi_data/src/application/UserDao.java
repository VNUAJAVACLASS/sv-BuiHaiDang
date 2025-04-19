package application;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	private Connection connection;

	public UserDao() {
		try {
			String dbURL = "jdbc:ucanaccess://Lib/Database2.accdb";

			connection = DriverManager.getConnection(dbURL);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		String query = "SELECT * FROM tblUser";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				int userID = rs.getInt("UserID");
				String fullName = rs.getString("FullName");
				boolean userType = rs.getBoolean("UserType");
				String email = rs.getString("Email");
			     Date birthdayDate = rs.getDate("Birthday");
		            LocalDate birthday = birthdayDate != null ? birthdayDate.toLocalDate() : null;
				boolean gender = rs.getBoolean("Gender");

				User user = new User(userID, fullName, userType, email, birthday, gender);
				userList.add(user);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}

	public boolean addUser(User user) {
		String query = "INSERT INTO  tblUser (UserID ,FullName ,UserType ,Email ,Birthday ,Gender ) VALUES(?,?,?,?,?,?)";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, user.getUserID());
			stmt.setString(2, user.getFullName());
			stmt.setBoolean(3, user.isUserType());
			stmt.setString(4, user.getEmail());
			stmt.setDate(5, java.sql.Date.valueOf(user.getBirthday()));
			stmt.setBoolean(6, user.isGender());

			int rowInserted = stmt.executeUpdate();

			return rowInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	
	public boolean updateUser(User user) {
		String query = "UPDATE tblUser SET FullName = ?, UserType = ?, Email = ?, Birthday = ?, Gender = ? WHERE UserID = ? ";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, user.getFullName());
			stmt.setBoolean(2, user.isUserType());
			stmt.setString(3, user.getEmail());
			stmt.setDate(4, java.sql.Date.valueOf(user.getBirthday()));
			stmt.setBoolean(5, user.isGender());
			stmt.setInt(6, user.getUserID());


			int rowInserted = stmt.executeUpdate();

			return rowInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	
	public boolean deleteUser(int userID) {
		
		String query = "DELETE tblUser WHERE UserID = ? ";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userID);
			int rowInserted = stmt.executeUpdate();
			return rowInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/BookWeb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "root";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	}

}

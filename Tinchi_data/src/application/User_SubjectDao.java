package application;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;

public class User_SubjectDao {
	private Connection connection;

	public User_SubjectDao() {
		try {
			String dbURL = "jdbc:ucanaccess://Lib/Database2.accdb";

			connection = DriverManager.getConnection(dbURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<User_Subject> getAllUserSubjects() {
		List<User_Subject> userSubjectList = new ArrayList<>();
//		String query = "SELECT * FROM tblUser_Subject";

		String query = "SELECT us.User_SubjectID, us.UserID, us.SubjectID, us.AttendancePoint, us.Point1, us.Point2, us.Point3, us.FinalPoint, "
				+ "ROUND( us.AttendancePoint * s.AttendancePoint +  us.Point1 * s.Point1 + us.Point2 * s.Point2 +  us.Point3 * s.Point3 + us.FinalPoint * s.FinalPoint, 2) AS Sum FROM tblUser_Subject us INNER JOIN tblSubject s ON us.SubjectID = s.SubjectID";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				int user_subjectID = rs.getInt("User_SubjectID");
				int userID = rs.getInt("UserID");
				int subjectID = rs.getInt("SubjectID");
				float attendancePoint = rs.getFloat("AttendancePoint");
				float point1 = rs.getFloat("Point1");
				float point2 = rs.getFloat("Point2");
				float point3 = rs.getFloat("Point3");
				float finalPoint = rs.getFloat("finalPoint");
				float sum = rs.getFloat("Sum");

				User_Subject userSubject = new User_Subject(user_subjectID, userID, subjectID, attendancePoint, point1,
						point2, point3, finalPoint);
		
				userSubject.setSum(sum);
				
				userSubjectList.add(userSubject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userSubjectList;
	}

	public boolean addSubjectToUser(User_Subject user_Subject) {
		String checkUserTypeQuery = "SELECT UserType FROM tblUser WHERE UserID = ?";
		String checkExistQuery = "SELECT COUNT(*) FROM tblUser_Subject WHERE UserID = ? AND SubjectID = ?";
		String insertUserSubjectQuery = "INSERT INTO tblUser_Subject (User_SubjectID, UserID, SubjectID, AttendancePoint, Point1, Point2, Point3, finalPoint) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement checkUserTypeStmt = connection.prepareStatement(checkUserTypeQuery);
				PreparedStatement checkExistStmt = connection.prepareStatement(checkExistQuery);
				PreparedStatement addStmt = connection.prepareStatement(insertUserSubjectQuery);) {

			// 1. Kiểm tra user có phải giảng viên không
			checkUserTypeStmt.setInt(1, user_Subject.getUserID());
			ResultSet userTypeRs = checkUserTypeStmt.executeQuery();
			if (userTypeRs.next() && userTypeRs.getBoolean("UserType")) {
				showAlert("Không thành công", "Giáo viên không được thêm môn học!");
				return false;
			}

			// 2. Kiểm tra quan hệ đã tồn tại chưa
			checkExistStmt.setInt(1, user_Subject.getUserID());
			checkExistStmt.setInt(2, user_Subject.getSubjectID());
			ResultSet existRs = checkExistStmt.executeQuery();
			if (existRs.next() && existRs.getInt(1) > 0) {
				showAlert("Không thành công", "User đã có môn học này!");
				return false;
			}

			// 3. Thêm vào bảng
			addStmt.setInt(1, user_Subject.getUser_SubjectID());
			addStmt.setInt(2, user_Subject.getUserID());
			addStmt.setInt(3, user_Subject.getSubjectID());
			addStmt.setFloat(4, user_Subject.getAttendancePoint());
			addStmt.setFloat(5, user_Subject.getPoint1());
			addStmt.setFloat(6, user_Subject.getPoint2());
			addStmt.setFloat(7, user_Subject.getPoint3());
			addStmt.setFloat(8, user_Subject.getFinalPoint());
			return addStmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private void showAlert(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Thông báo");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public boolean updateUserSubject(User_Subject us) {
	    String sql = "UPDATE tblUser_Subject SET AttendancePoint = ?, Point1 = ?, Point2 = ?, Point3 = ?, finalPoint = ? WHERE User_SubjectID = ?";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setFloat(1, us.getAttendancePoint());
	        stmt.setFloat(2, us.getPoint1());
	        stmt.setFloat(3, us.getPoint2());
	        stmt.setFloat(4, us.getPoint3());
	        stmt.setFloat(5, us.getFinalPoint());
	        stmt.setInt(6, us.getUser_SubjectID());
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	public boolean deleteUserSubject(int user_subjectId) {

		String query = "DELETE tblUser_Subject WHERE User_SubjectID = ? ";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, user_subjectId);
			int rowInserted = stmt.executeUpdate();
			return rowInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}

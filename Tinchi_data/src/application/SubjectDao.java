package application;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {
	private Connection connection;

	public SubjectDao() {
		try {
			String dbURL = "jdbc:ucanaccess://Lib/Database2.accdb";

			connection = DriverManager.getConnection(dbURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Subject> getAllSubject() {
		List<Subject> subjectList = new ArrayList<>();
		String query = "SELECT * FROM tblSubject";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				int subjectID = rs.getInt("SubjectID");
				String subjectName = rs.getString("SubjectName");
				Integer credit = rs.getInt("Credit");
				Float attendancePoint = rs.getFloat("AttendancePoint");
				Float point1 = rs.getFloat("Point1");
				Float point2 = rs.getFloat("Point2");
				Float point3 = rs.getFloat("Point3");
				Float finalPoint = rs.getFloat("FinalPoint");

				Subject subject = new Subject(subjectID, subjectName, credit, attendancePoint, point1, point2, point3,
						finalPoint);
				subjectList.add(subject);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return subjectList;
	}

	public boolean addSubject(Subject subject) {
		String query = "INSERT INTO  tblSubject (SubjectID ,SubjectName ,Credit ,AttendancePoint ,Point1 ,Point2 ,Point3 ,FinalPoint ) VALUES(?,?,?,?,?,?,?,?)";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, subject.getSubjectID());
			stmt.setString(2, subject.getSubjectName());
			stmt.setFloat(3, subject.getCredit());
			stmt.setFloat(4, subject.getAttendancePoint());
			stmt.setFloat(5, subject.getPoint1());
			stmt.setFloat(6, subject.getPoint2());
			stmt.setFloat(7, subject.getPoint3());
			stmt.setFloat(8, subject.getFinalPoint());

			int rowInserted = stmt.executeUpdate();

			return rowInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean deleteSubject(int subjectID) {

		String query = "DELETE tblSubject WHERE SubjectID = ? ";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, subjectID);
			int rowInserted = stmt.executeUpdate();
			return rowInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean updateSubject(Subject subject) {
		String updateSubjectQuery = "UPDATE tblSubject SET SubjectName = ?, Credit = ?, AttendancePoint = ?, Point1 = ?, Point2 = ?, Point3 = ?, FinalPoint = ? WHERE SubjectID = ?";

		try (PreparedStatement stmt = connection.prepareStatement(updateSubjectQuery)) {
			stmt.setString(1, subject.getSubjectName());
			stmt.setFloat(2, subject.getCredit());
			stmt.setFloat(3, subject.getAttendancePoint());
			stmt.setFloat(4, subject.getPoint1());
			stmt.setFloat(5, subject.getPoint2());
			stmt.setFloat(6, subject.getPoint3());
			stmt.setFloat(7, subject.getFinalPoint());
			stmt.setInt(8, subject.getSubjectID());

			int updated = stmt.executeUpdate();

			return updated >0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}

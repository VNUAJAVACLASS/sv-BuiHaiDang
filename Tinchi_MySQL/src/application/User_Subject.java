package application;

public class User_Subject {
	private int user_SubjectID;
	private int userID;
	private int subjectID;
	private float attendancePoint;
	private float point1;
	private float point2;
	private float point3;
	private float finalPoint;
	private float sum;

	public User_Subject() {

	}

	public User_Subject(int user_SubjectID, int userID, int subjectID, float attendancePoint, float point1,
			float point2, float point3, float point4) {
		super();
		this.user_SubjectID = user_SubjectID;
		this.userID = userID;
		this.subjectID = subjectID;
		this.attendancePoint = attendancePoint;
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		this.finalPoint = point4;
	}

	public User_Subject(int user_SubjectID, int userID, int subjectID, float attendancePoint, float point1,
			float point2, float point3, float finalPoint, float sum) {
		super();
		this.user_SubjectID = user_SubjectID;
		this.userID = userID;
		this.subjectID = subjectID;
		this.attendancePoint = attendancePoint;
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		this.finalPoint = finalPoint;
		this.sum = sum;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	public float getPoint1() {
		return point1;
	}

	public void setPoint1(float point1) {
		this.point1 = point1;
	}

	public float getPoint2() {
		return point2;
	}

	public void setPoint2(float point2) {
		this.point2 = point2;
	}

	public float getPoint3() {
		return point3;
	}

	public void setPoint3(float point3) {
		this.point3 = point3;
	}

	public float getFinalPoint() {
		return finalPoint;
	}

	public void setFinalPoint(float point4) {
		this.finalPoint = point4;
	}

	public int getUser_SubjectID() {
		return user_SubjectID;
	}

	public void setUser_SubjectID(int user_SubjectID) {
		this.user_SubjectID = user_SubjectID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public float getAttendancePoint() {
		return attendancePoint;
	}

	public void setAttendancePoint(float attendancePoint) {
		this.attendancePoint = attendancePoint;
	}

}

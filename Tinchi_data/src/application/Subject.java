package application;

public class Subject {
	private int subjectID;
	private String subjectName;
	private int credit;
	private float attendancePoint;
	private float point1;
	private float point2;
	private float point3;
	private float finalPoint;

	public Subject() {
		super();
	}

	

	public Subject(int subjectID, String subjectName, int credit2, float attendancePoint, float point1, float point2,
			float point3, float finalPoint) {
		super();
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.credit = credit2;
		this.attendancePoint = attendancePoint;
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		this.finalPoint = finalPoint;
	}



	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public float getAttendancePoint() {
		return attendancePoint;
	}

	public void setAttendancePoint(float attendancePoint) {
		this.attendancePoint = attendancePoint;
	}

	public float getPoint1() {
		return point1;
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



	public void setFinalPoint(float finalPoint) {
		this.finalPoint = finalPoint;
	}



	public void setPoint1(float point1) {
		this.point1 = point1;
	}

	
	
	

}

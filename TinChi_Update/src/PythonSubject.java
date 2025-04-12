
public class PythonSubject extends Subject {

	private float projectMark;

	public float getProjectMark() {
		return projectMark;
	}

	public void setProjectMark(float projectMark) {
		this.projectMark = projectMark;
	}

	@Override
	public float calSubjectMark() {
		return (float) (getAttendanceMark() * 0.1 + getFinalExamMark() * 0.2 + getProjectMark() * 0.2
				+ getFinalExamMark() * 0.5);
	}

	@Override
	public String toString() {
		
		float sumMark = calSubjectMark();
		return "Python [subjectCode=" + getSubjectCode() 
				+ ", attendanceMark=" + getAttendanceMark() + ", midExamMark=" + getMidExamMark()+"  ,projectMark= "+getProjectMark() + ", finalExamMark="
				+ getFinalExamMark() +" ,Sum= "+sumMark+"]";
	}
	
	

}

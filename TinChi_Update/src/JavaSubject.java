
public class JavaSubject extends Subject {

	@Override
	public float calSubjectMark() {
		return (float) (getAttendanceMark() * 0.1 + getMidExamMark() * 0.3 + getFinalExamMark() * 0.6);
	}

	@Override
	public String toString() {
		float sumMark = calSubjectMark();
		return "Java [subjectCode=" + getSubjectCode() 
				+ ", attendanceMark=" + getAttendanceMark() + ", midExamMark=" + getMidExamMark() + ", finalExamMark="
				+ getFinalExamMark() +" ,Sum= "+sumMark+ "]";
	}
	
	
}

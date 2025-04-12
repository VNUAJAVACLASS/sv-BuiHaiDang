
public class Main {
	public static void main(String[] args) {
		
			Student s1 = new Student("45435", "Nguyen A", "K777");
		
			PythonSubject sub2 = new PythonSubject();
			sub2.setCredit(2);
			sub2.setSubjectCode("22222");
			sub2.setSubjectName("Python");
			sub2.setAttendanceMark(10f);
			sub2.setMidExamMark(5f);
			sub2.setProjectMark(7f);
			sub2.setFinalExamMark(6f);
			s1.addSubject(sub2);
			
			JavaSubject sub3 = new JavaSubject();
			sub3.setCredit(2);
			sub3.setSubjectCode("222444422");
			sub3.setSubjectName("Python");
			sub3.setAttendanceMark(10f);
			sub3.setMidExamMark(5f);
			sub3.setFinalExamMark(6f);
			s1.addSubject(sub3);
			
			
//			System.err.println(s1);\
			s1.searchByCode("22222");
	}
}

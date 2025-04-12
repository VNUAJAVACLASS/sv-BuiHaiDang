import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Student extends Human {
	private String class_;
	private HashMap<String, Subject> subjectMap = new HashMap<String, Subject>();

	public Student() {
	}

	public Student(String code) {
		super(code);
	}

	public Student(String code, String fullname) {
		super(code, fullname);
	}

	public Student(String code, String fullname, String class_) {
		super(code, fullname);
		this.class_ = class_;
	}

	public Student(String address, String code, String fullname, String class_) {
		super(address, code, fullname);
		this.class_ = class_;
	}

	public void addSubject(Subject sub) {
		subjectMap.put(sub.getSubjectCode(), sub);
	}

	public void deleteSubject(String key) {
		subjectMap.remove(key);
	}

	// Hai phương thức cùng một biến chưa tìm được cách nạp chồng toán tử 
	public void searchByCode(String code) {
		Subject a = subjectMap.get(code);
		if(a == null) {
			System.out.println("Khong có môn học !");
		}else {
			System.out.println(a);
		}
	}

	public void searchByName(String name) {
		Subject a = subjectMap.get(name);
		if(a == null) {
			System.out.println("Khong có môn học !");
		}else {
			System.out.println(a);
		}
	}

//	 Trung bình học kì
	public float calTermAverageMark() {
		float ts = 0;
		int ms = 0;

		for (Subject sub : subjectMap.values()) {
			ts += sub.getCredit() * sub.calConversionMark();
			ms += sub.getCredit();
		}

		return ts / ms;

	}

	@Override
	public int hashCode() {
		return Objects.hash(class_, subjectMap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		float d = Math.abs(this.calTermAverageMark() - other.calTermAverageMark());
		return d < 0.3;
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	@Override
	public void enterInfo(Scanner sc) {

	}

	@Override
	public String toString() {
		float avg = calTermAverageMark();
		  StringBuilder sb = new StringBuilder();
		    sb.append("Student [Code= ").append(code)
		      .append(" ,Address= ").append(address)
		      .append(" ,Name= ").append(fullname)
		      .append(" ,class_= ").append(class_)
		      .append("]\nDanh sách môn học:\n");
		    
		    for (HashMap.Entry<String, Subject> entry : subjectMap.entrySet()) {
		        sb.append("- ").append(entry.getKey())
		          .append(": ").append(entry.getValue()).append("\n");
		    }
		    
		    sb.append("====================================================================================\n")
		    .append("Trung bình học kì: "+avg);
		    return sb.toString();
	}

}

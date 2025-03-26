import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends Human {
	private String class_;
	private List<Subject> subjectList = new ArrayList<Subject>();

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
		subjectList.add(sub);
	}

	public float calTermAverageMark() {
		float ts = 0;
		int ms = 0;

		for (Subject sub : subjectList) {
			ts += sub.getCredit() * sub.calConversionMark();
			ms += sub.getCredit();
		}

		return ts / ms;

	}

	@Override
	public String toString() {
		return "Student [ code=" + code + ", fullname=" + fullname + "class_=" + class_ + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(class_, subjectList);
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

	
	
}

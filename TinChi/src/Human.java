import java.util.Scanner;

public class Human {
	String address;
	String code;
	String fullname;

	public Human() {
	}

	public Human(String code) {
		this.code = code;
	}

	public Human(String code, String fullname) {
		this.code = code;
		this.fullname = fullname;
	}

	public Human(String address, String code, String fullname) {
		this.address = address;
		this.code = code;
		this.fullname = fullname;
	}

	public void enterInfo(Scanner sc) {
		System.out.println("Nhap ma: ");
		code = sc.nextLine();

		System.out.println("Nhap ten: ");
		fullname = sc.nextLine();

		System.out.println("Nhap dia chi: ");
		address = sc.nextLine();

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Override
	public String toString() {
		return "Human [address=" + address + ", code=" + code + ", fullname=" + fullname + "]";
	}

	
	
	
	
}

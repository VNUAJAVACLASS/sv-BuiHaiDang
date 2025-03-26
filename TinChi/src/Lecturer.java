import java.util.Scanner;

public class Lecturer extends Human {
	String password;

	public Lecturer() {
	}

	public Lecturer(String password, String code) {
		this.password = password;
		this.code = code;
	}

	public Lecturer(String address, String code, String fullname) {
		super(address, code, fullname);
	}

	public void enterInfo(Scanner sc) {
		super.enterInfo(sc);

		System.out.println("Nhap pass: ");
		password = sc.nextLine();

	}

}

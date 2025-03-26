package BaiTH3;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		QLHS qlhs = new QLHS();
		qlhs.nhapDanhSach(scanner);
		qlhs.timKiemHocSinh(1985, "Thái Nguyên");
//		qlhs.timKiemHocSinh("10");
	}
}

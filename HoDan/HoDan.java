package De_5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class HoDan extends Nguoi {
	private int soThanhVien;
	private String soNha;
	private List<Nguoi> danhSachThanhVien;

	public HoDan() {
		danhSachThanhVien = new ArrayList<>();
	};

	public HoDan(int soThanhVien, String soNha, List<Nguoi> danhSachThanhVien) {
		super();
		this.soThanhVien = soThanhVien;
		this.soNha = soNha;
		this.danhSachThanhVien = danhSachThanhVien;
	}

	
	@Override
	public void enterInfor() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Số nhà: ");
		soNha = scanner.nextLine();
		System.out.println("Số thành viên: ");
		soThanhVien = scanner.nextInt();
		scanner.nextLine();

		for (int i = 1; i <= soThanhVien; i++) {
			Nguoi nguoi = new Nguoi();
			System.out.println("Thành viên " + i + ": ");
			nguoi.enterInfor();
			danhSachThanhVien.add(nguoi);
		}
	}

	
	

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("\nSố nhà: ").append(soNha).append(", Số thành viên: ").append(soThanhVien).append("\n");
		for (Nguoi nguoi : danhSachThanhVien) {
			result.append(nguoi.toString()).append("\n");
		}
		return result.toString();
	}

	public List<Nguoi> getDanhSachThanhVien() {
		return danhSachThanhVien;
	}

}

package De_2;

import java.util.Scanner;

public class NhanVien extends CanBo {
	private String congViec;

	public NhanVien() {};
	public NhanVien(String hoTen, String ngaySinh, String gioiTinh, String diaChi, String congViec) {
		super(hoTen, ngaySinh, gioiTinh, diaChi);
		this.congViec = congViec;
	}


	public String getCongViec() {
		return congViec;
	}

	
	public void setCongViec(String congViec) {
		this.congViec = congViec;
	}


	@Override
	public String toString() {
		return super.toString() + " Côngviệc: "+ congViec;
	}
	
	@Override
	public void enterInfor(Scanner sc) {
		super.enterInfor(sc);
		System.out.println("Nhập công việc: ");
		congViec = sc.nextLine();
	}
}

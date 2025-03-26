package BaiTH3;

import java.util.Date;
import java.util.Scanner;

public class HocSinh extends Nguoi {
	private String lop;
	private String khoaHoc;
	private int kyHoc;

	public HocSinh() {}

	public HocSinh(String hoTen,Date ngaySinh,String lop, String khoaHoc) {
		super(hoTen,ngaySinh);
		this.lop = lop;
		this.khoaHoc = khoaHoc;
	}

	public HocSinh(String hoTen,String lop, String khoaHoc) {
		super();
		this.lop = lop;
		this.khoaHoc = khoaHoc;
	}
	
	public void nhapThongTin(Scanner sc) {
		super.nhapThongTin(sc);
		System.out.println("Nhập lớp: ");
		lop = sc.nextLine();
		System.out.println("Nhập khoa hoc: ");
		khoaHoc = sc.nextLine();
		System.out.println("Nhập ky hoc: ");
		kyHoc = sc.nextInt();
	}

	public void inRaThongTin() {
		super.inThongTin();
		System.out.println("Lop: "+ lop);
		System.out.println("khoa hoc: "+ khoaHoc);
		System.out.println("ky hoc: "+ kyHoc);
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public String getKhoaHoc() {
		return khoaHoc;
	}

	public void setKhoaHoc(String khoaHoc) {
		this.khoaHoc = khoaHoc;
	}

	public int getKyHoc() {
		return kyHoc;
	}

	public void setKyHoc(int kyHoc) {
		this.kyHoc = kyHoc;
	}

	
	
	
}

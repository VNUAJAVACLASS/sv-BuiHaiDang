package De_2;

import java.util.Scanner;

public class CanBo {
	private String hoTen;
	private String ngaySinh;
	private String gioiTinh;
	private String diaChi;
	
	public CanBo() {};
	
	public CanBo(String hoTen, String ngaySinh, String gioiTinh, String diaChi) {
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
	}

	@Override
	public String toString() {
		return "CanBo hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	//phuong thuc cua lop CanBo
		public void enterInfor(Scanner sc) {
			System.out.print("Nhap ho ten: "); hoTen = sc.nextLine();
			System.out.print("Nhap ngay sinh: "); ngaySinh = sc.nextLine();
			System.out.print("Nhap gioi tinh: "); gioiTinh = sc.nextLine();
			System.out.print("Nhap dia chi: "); diaChi = sc.nextLine();
		}
}

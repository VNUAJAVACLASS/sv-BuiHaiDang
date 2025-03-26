package De_5;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Nguoi {
	private String hoTen;
	private String  ngaySinh;
	private String ngheNghiep;

	public Nguoi() {};
	
	
	public Nguoi(String hoTen, String ngaySinh, String ngheNghiep) {
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.ngheNghiep = ngheNghiep;
	}


	// Nhập thông tin cá nhân
	public void enterInfor() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhập họ tên: "); hoTen = scanner.nextLine();
		System.out.println("Nhập ngày sinh:(d/M/yyyy) "); ngaySinh = scanner.nextLine();
		System.out.println("Nhập nghề nghiệp: "); ngheNghiep = scanner.nextLine();
	}

	// Hiển thị thông tin cá nhân 
	@Override
	public String toString() {
		return "\nhoTen=" + hoTen + " ,ngaySinh=" + ngaySinh + " ,ngheNghiep=" + ngheNghiep;
	}

	// Tính tuổi
	public int  tinhTuoi() {
		// Định nghĩa định dạng cho chuỗi ngày tháng 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		  // Chuyển đổi chuỗi ngày sinh thành đối tượng LocalDate
        LocalDate ngaySinhDate = LocalDate.parse(ngaySinh, formatter);
		
		// Chuyển đổi chuỗi thành đối tượng localDate
		LocalDate ngayHienTai = LocalDate.now();
		
		// Tính khoảng thời gian giữa ngày sinh và ngày hiện tại
		Period period = Period.between(ngaySinhDate, ngayHienTai);
		
		// Lấy số tuổi 
		int tuoi = period.getYears();
		
		return tuoi;
    }

	public String getHoTen() {
		return hoTen;
	}


	public String getNgaySinh() {
		return ngaySinh;
	}

	public String getNgheNghiep() {
		return ngheNghiep;
	}
	
}

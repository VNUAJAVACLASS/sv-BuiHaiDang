package BaiTH3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Nguoi {
	protected String hoTen;
	protected Date ngaySinh;
	protected String queQuan;

	public Nguoi() {
	}

	public Nguoi(String hoTen, Date ngaySinh) {
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
	}

	public Nguoi(String hoTen, Date ngaySinh, String queQuan) {
		this(hoTen, ngaySinh);
		this.queQuan = queQuan;
	}

	public void nhapThongTin(Scanner sc) {
		sc.nextLine();
		System.out.println("Nhập họ va ten: ");
		hoTen = sc.nextLine();
		System.out.println("Nhập Ngày sinh: ");
		String strDate = sc.nextLine();
		ngaySinh = strToDate(strDate);

		System.out.println("Nhập que quán: ");
		queQuan = sc.nextLine();
	}

	public String getHoTen() {
		return hoTen;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public String getQueQuan() {
		return queQuan;
	}

	private Date strToDate(String strDate) {
		Date ngaySinhDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			ngaySinhDate = sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Lỗi!");
		}
		return ngaySinhDate;
	}

	public void inThongTin() {
		System.out.println("Ho ten: "+ hoTen);
		System.out.println("Ho ten: "+ ngaySinh);
		System.out.println("Ho ten: "+ queQuan);
	}
	

}

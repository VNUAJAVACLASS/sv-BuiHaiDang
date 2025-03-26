package De_5;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class KhuPho extends HoDan {
	List<HoDan> danhSachHoDan = new ArrayList<HoDan>();
	
	public KhuPho() {
	};

	
	
	@Override
	public String toString() {
		return "KhuPho ==> danhSachHoDan=" + danhSachHoDan;
	}



	// Nhập vào hộ dân
	public void nhapVaoHoDan() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhập n hộ dân: ");
		int n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 1; i <= n; i++) {
			System.out.println("==============");
			System.out.println("Hộ dân "+i+":");
			HoDan hoDan = new HoDan();
			hoDan.enterInfor();
			danhSachHoDan.add(hoDan);
		}
	}

	
	// Kiểm tra xem 80 tuổi
	public void hienThiHoThuongTho() {
		for (HoDan hoDan : danhSachHoDan) {
			for (Nguoi nguoi : hoDan.getDanhSachThanhVien()) {
					if (nguoi.tinhTuoi() >= 80) {
						System.out.println(hoDan);
						break;
					}else {
						System.out.println("Không có hộ dân nào có người thượng thọ !");
					}
					
			}
		}
		
	}
	
	
	
	
}

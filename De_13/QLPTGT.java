package De_13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLPTGT {
	private List<PTGT> dsPTGT;

	public QLPTGT() {
		dsPTGT = new ArrayList<>();
	}

	public void nhapDangKyPhuongTien(Scanner sc) {
		int lc = 0;
		do {
			System.out.println("===Nhap lua chon====");
			System.out.println("1. Nhap oto");
			System.out.println("2. Nhap xe tai");
			System.out.println("3. Nhap xe may");
			int loaiXe;
			loaiXe = sc.nextInt();

			if (loaiXe == 1) {
				Oto oto = new Oto();
				oto.nhapThongTin(sc);
				dsPTGT.add(oto);
			} else if (loaiXe == 2) {
				XeTai xetai = new XeTai();
				xetai.nhapThongTin(sc);
				dsPTGT.add(xetai);
			} else if (loaiXe == 3) {
				XeMay xeMay = new XeMay();
				xeMay.nhapThongTin(sc);
				dsPTGT.add(xeMay);
			} else {
				System.out.println("Nhap thong tin khong chinh xac !");
			}

			System.out.println("Nhap lua chon(0-thoat, 1-tieptuc)");
			lc = sc.nextInt();
		} while (lc >=1);
		
	}
	
	public void hienThiDanhSach() {
		System.out.println("Danh sach phuong tien: ");
		for (PTGT ptgt : dsPTGT) {
			System.out.println(ptgt);
		}
	}
	
	public void timPhuongTienTheoNamSanXuat(int namSX) {
		System.out.println("\nPhuong tine tim duoc theo nam: ");
		for (PTGT ptgt : dsPTGT) {
			if(ptgt.getNamSX() == namSX) {
				System.out.println(ptgt);
			}
		}
	}
	public void timPhuongTienThaoMau(String mau) {
		System.out.println("\nPhuong tine tim duoc theo mau: ");
		for (PTGT ptgt : dsPTGT) {
			if(ptgt.getMauXe().equalsIgnoreCase(mau)) {
				System.out.println(ptgt);
			}
		}
	}
	
	public static void main(String[] args) {
		QLPTGT qlptgt = new QLPTGT();
		Scanner sc = new Scanner(System.in);
		qlptgt.nhapDangKyPhuongTien(sc);
		qlptgt.hienThiDanhSach();
		qlptgt.timPhuongTienThaoMau("xanh");
		qlptgt.timPhuongTienTheoNamSanXuat(2000);
	}
}

package BaiTH3;

import java.util.ArrayList;
import java.util.Scanner;

public class QLHS {
	private ArrayList<HocSinh> dshs;

	public QLHS() {
		dshs = new ArrayList<HocSinh>(100);
	}

	public void themHocSinh(HocSinh hs) {
		dshs.add(hs);
	}

	public void nhapDanhSach(Scanner sc) {
		System.out.println("Nhap so hoc sinh can nhap: ");
		int n = sc.nextInt();
		HocSinh hs = new HocSinh();
		for (int i = 0; i < n; i++) {
			System.out.println("Nhap hs " + (i + 1) + " : ");
			hs.nhapThongTin(sc);
			dshs.add(hs);
		}
	}

	public void inDanhSach() {
		System.out.println("Danh sach hs: ");
		for (HocSinh hocSinh : dshs) {
			System.out.println(hocSinh);
		}
	}

	public void timKiemHocSinh(int namSinh, String queQuan) {
		System.out.println("Học sinh sinh 1985,Thái Nguyên: ");
		for (HocSinh hocSinh : dshs) {
			if(queQuan.equalsIgnoreCase(hocSinh.getQueQuan()) && hocSinh.getNgaySinh().getYear()+1900 == namSinh) {
				hocSinh.inRaThongTin();
			}
		}
	}

	public void timKiemHocSinh(String lop) {
		System.out.println("học sinh cần tìm: ");
		for (HocSinh hocSinh : dshs) {
			if (lop.equalsIgnoreCase(hocSinh.getLop())) {
				hocSinh.inRaThongTin();
			}
		}
	}
	
	
	
}

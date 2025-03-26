package De_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import De_1.PhanSo;

public class QLCB extends CanBo {
	private List<CanBo> listCanBo = new ArrayList<CanBo>();

	public QLCB() {};
		
	// Nhập thông tin cán bộ cho cán bộ
	public void nhapThongChoCanBo() {
		Scanner sc = new Scanner(System.in);
		int nhap;
		do {
		System.out.println("Nhập loại cán bộ (CN/KS/NV): ");
		String loaiCanBo = sc.nextLine();
		
		switch (loaiCanBo) {
		case "CN":
			CongNhan congNhan = new CongNhan();
			congNhan.enterInfor(sc);
			listCanBo.add(congNhan);
			break;
		case "KS":
			KySu kySu = new KySu();
			kySu.enterInfor(sc);
			listCanBo.add(kySu);
			break;
		case "NV":
			NhanVien nhanVien = new NhanVien();
			nhanVien.enterInfor(sc);
			listCanBo.add(nhanVien);
			break;
		default:
			System.out.println("Loại cán bộ không hợp lệ !");
			break;
		}
		System.out.println("nhập lựa chọn (1-tiếp , 0-dừng):");
		nhap = sc.nextInt();
		sc.nextLine();
		} while (nhap == 1);

		
	}

	// Tìm kiếm theo họ tên và hiển thị
	public void timKiemHoTen(String hoTen) {
		boolean timCanBo = false;
		for (CanBo canBo : listCanBo) {
			if (canBo.getHoTen().equalsIgnoreCase(hoTen)) {
				System.out.println("Đã tìm thấy: ");
				System.out.println(canBo);
				timCanBo = true;
			}
		}
	}

	// Hiển thị cán bộ
	public void hienThiThongTinCanBo() {
		System.out.println("Các cán bộ: ");
		for (CanBo canBo : listCanBo) {
			System.out.println(canBo);
		}
	}

}

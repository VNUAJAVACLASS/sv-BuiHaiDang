package De_31;

import java.util.ArrayList;

public class QLCC {
	private ArrayList<CongChuc> danhSachCongChuc;

	public QLCC(ArrayList<CongChuc> danhSachCongChuc) {
		this.danhSachCongChuc = danhSachCongChuc;
	}
	
	//	
	public void inRaThongTinVaLuong() {
		System.out.println("Thông tin công chức và lương thực lĩnh:");
		int a = 1;
		for (CongChuc congChuc : danhSachCongChuc) {
				System.out.println("Công chức "+a+" :"); a++;
				double luongCoBan = 1.8d * congChuc.getHeSoLuong();
				double luongThucLinh = luongCoBan + congChuc.getTienPhuCap();
				System.out.println("Họ tên: "+congChuc.getHoVaTen());
				System.out.println("Ngày sinh: "+congChuc.getNgaySinh());
				System.out.println("Quê quán: "+congChuc.getQueQuan());
				System.out.println("Lương thực lĩnh: "+ luongThucLinh+ "VNĐ\n");
			}
	}

	// Tìm kiếm lương công chức nhỏ hơn 7 triệu
	public void timKiemCongChuc() {
	    System.out.println("Các công chức lương cơ bản nhỏ hơn hoặc bằng 7 triệu:");
	    for (CongChuc congChuc : danhSachCongChuc) {
	    	double luongCoBan = 1800000 * congChuc.getHeSoLuong();
	    	if (luongCoBan <= 7000000) {
	            System.out.println("Công chức:");
	            System.out.println("Họ tên: " + congChuc.getHoVaTen());
	            System.out.println("Ngày sinh: " + congChuc.getNgaySinh());
	            System.out.println("Quê quán: " + congChuc.getQueQuan());
	            System.out.println("Lương cơ bản: " + luongCoBan+ " VNĐ\n");
	        }
	    }
	}


	
	
}

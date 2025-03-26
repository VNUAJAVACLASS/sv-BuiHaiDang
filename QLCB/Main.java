package De_31;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
	public static void main(String[] args) {
		// Khởi tạo danh sách công chức
		ArrayList<CongChuc> danhSachCongChuc = new ArrayList<CongChuc>();
		
		
//		danhSachCongChuc.add(new CongChuc("Nguyễn Văn A", "1/5/2020", "Hà Nam", 2, 1500000));
//		danhSachCongChuc.add(new CongChuc("Nguyễn Văn B", "6/5/2020", "Hà Nội", 15, 8000000));
//		danhSachCongChuc.add(new CongChuc("Nguyễn Văn C", "8/5/2020", "Hà Đông", 10, 5000000));

		   CongChuc congChuc1 = new CongChuc("Nguyễn Văn A", "1/5/2020", "Hà Nam", 1.0, 2000000);
	        CongChuc congChuc2 = new CongChuc("Nguyễn Văn B", "6/5/2020", "Hà Nội", 2.0, 5000000);
	        CongChuc congChuc3 = new CongChuc("Nguyễn Văn C", "8/5/2020", "Hà Đông", 30.0, 3000000);

	        danhSachCongChuc.add(congChuc1);
	        danhSachCongChuc.add(congChuc2);
	        danhSachCongChuc.add(congChuc3);
		
		QLCC qlcc = new QLCC(danhSachCongChuc);
//		qlcc.inRaThongTinVaLuong();
		qlcc.timKiemCongChuc();
		
	}

}

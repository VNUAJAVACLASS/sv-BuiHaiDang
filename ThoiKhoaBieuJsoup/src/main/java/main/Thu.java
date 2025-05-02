//package main;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//public class Thu {
//	private int thuSo; // 1 = Monday, 7 = Sunday
//	private List<LichHoc> danhSachLichHoc;
//
//
//
//	public Thu(int thuSo) {
//		this.thuSo = thuSo;
//		this.danhSachLichHoc = new ArrayList<>();
//	}
//
//	public void themLichHoc(LichHoc lichHoc) {
//		danhSachLichHoc.add(lichHoc);
//		danhSachLichHoc.sort(Comparator.comparingInt(LichHoc::getTietBD));
//	}
//
//	public String getTenThu() {
//		switch (thuSo) {
//		case 1:
//			return "Thứ hai";
//		case 2:
//			return "Thứ ba";
//		case 3:
//			return "Thứ tư";
//		case 4:
//			return "Thứ năm";
//		case 5:
//			return "Thứ sáu";
//		case 6:
//			return "Thứ bảy";
//		case 7:
//			return "Chủ nhật";
//		default:
//			return "Không xác định";
//		}
//	}
//
//	// Getters
//	public int getThuSo() {
//		return thuSo;
//	}
//
//	public List<LichHoc> getDanhSachLichHoc() {
//		return danhSachLichHoc;
//	}
//}

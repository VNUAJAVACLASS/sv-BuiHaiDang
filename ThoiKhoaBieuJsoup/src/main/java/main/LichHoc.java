package main;

import java.util.ArrayList;

import java.util.List;

public class LichHoc {

	private String thu;
	private String tietBD;
	private String soTiet;
	private String phongHoc;
	private String giangVien;
	private String thoiGianHoc;

	public LichHoc() {
		super();
	}

	public LichHoc(String thu, String tietBatDau, String soTiet2, String phongHoc, String giangVien, String thoiGianHoc) {
		super();
		this.thu = thu;
		this.tietBD = tietBatDau;
		this.soTiet = soTiet2;
		this.phongHoc = phongHoc;
		this.giangVien = giangVien;
		this.thoiGianHoc = thoiGianHoc;
	}

	public String getThu() {
		return thu;
	}

	public String getTietBD() {
		return tietBD;
	}

	public String getSoTiet() {
		return soTiet;
	}

	public String getPhongHoc() {
		return phongHoc;
	}

	public String getGiangVien() {
		return giangVien;
	}

	public String getThoiGianHoc() {
		return thoiGianHoc;
	}

	public void setThoiGianHoc(String thoiGianHoc) {
		this.thoiGianHoc = thoiGianHoc;
	}

	// Lấy danh sách các tuần học từ cột "Thời gian học"
    public List<Integer> getWeeks() {
        List<Integer> weeks = new ArrayList<>();

        // Duyệt qua chuỗi "Thời gian học" từ vị trí 1 đến 20
        for (int i = 0; i < thoiGianHoc.length(); i++) {
            char c = thoiGianHoc.charAt(i);
            if (c == '-') {
                continue; 
            }

            // Vị trí i+1 thành tuần vị trí 1 -> tuần 1
            int week = i + 1;
            if (!weeks.contains(week)) {
                weeks.add(week);
            }
        }
        return weeks;
    }

	// Lấy ngày trong tuần (1-7, với 1 là thứ 2, 7 là CN)
	public int getDayOfWeek() {
		if (thu.equals("CN")) {
			return 7;
		}
		return Integer.parseInt(thu) - 1;
	}

	@Override
    public String toString() {
        return String.format("Thứ: %s | Tiết: %s-%s | Phòng: %s | Giảng viên: %s | Thời gian: %s",
                thu, tietBD, Integer.parseInt(tietBD) + Integer.parseInt(soTiet) - 1, phongHoc, giangVien, thoiGianHoc);
    }

}

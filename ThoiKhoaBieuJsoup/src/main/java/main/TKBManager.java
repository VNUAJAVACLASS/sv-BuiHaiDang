package main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TKBManager {
	private List<MonHoc> monHocList;
	private LocalDate ngayBDHK; // Ngày bắt đầu học kì

	public TKBManager(String html, LocalDate ngayBDHK) throws IOException {
		this.monHocList = new ArrayList<MonHoc>();
		this.ngayBDHK = ngayBDHK;
		parseHTML(html);
	}

	// Phân tích HTML để lấy dữ liệu
	private void parseHTML(String htmlContent) throws IOException {
		Document doc = Jsoup.parse(htmlContent);
		Elements rows = doc.select("tbody tr");
		MonHoc monHocHienTai = null;

		for (Element row : rows) {
			Elements cells = row.select("td"); // Lấy tất cả các hàng trong body

			// Kiểm tra hàng có thông tin môn học (có thuộc tính rowspan)
			if (cells.get(0).hasAttr("rowspan")) {
				String maMH = cells.get(0).text().trim();
				String tenMonHoc = cells.get(1).text().trim();
				String nhomTo = cells.get(2).text().trim();
				String soTinChi = cells.get(3).text().trim();
				String lop = cells.get(6).text().trim();

				monHocHienTai = new MonHoc(maMH, tenMonHoc, nhomTo, soTinChi, lop);
				monHocList.add(monHocHienTai);
			}

			String thu = cells.get(cells.size() - 10).text().trim();
			String tietBatDau = cells.get(cells.size() - 9).text().trim();
			String soTiet = cells.get(cells.size() - 8).text().trim();
			String phong = cells.get(cells.size() - 7).text().trim();
			String giangVien = cells.get(cells.size() - 6).text().trim();
			String thoiGianHoc = cells.get(cells.size() - 5).text().trim();

			LichHoc lichHoc = new LichHoc(thu, tietBatDau, soTiet, phong, giangVien, thoiGianHoc);
			if (monHocHienTai != null) {
				monHocHienTai.addLichHoc(lichHoc);
			}
		}
	}

	// 1. Xem thời khóa biểu ngày hiện tại
	public List<MonHoc> getTKBNgayHienTai() {
		LocalDate today = LocalDate.now();
		return getTKBTheoNgay(today);
	}

	public TuanHoc getTKBCaTuan(int week) {
		TuanHoc tuanHoc = new TuanHoc(week);
		for (MonHoc monHoc : monHocList) {
			for (LichHoc lichHoc : monHoc.getDSLichHoc()) {
				if (lichHoc.getWeeks().contains(week)) {
					int day = lichHoc.getDayOfWeek() - 1; // Chuyển đổi thứ thành chỉ số 0-6
					tuanHoc.addLichHoc(day, monHoc, lichHoc);
				}
			}
		}
		return tuanHoc;
	}

	public void getTKBTheoTuanThu(int week, int day) {
		// Kiểm tra tuần nhập vào có hợp lệ không (chỉ hỗ trợ tuần 1-21)
		if (week < 1 || week > 22) {
			System.out.println("Tuần " + week + " không hợp lệ. Vui lòng nhập tuần từ 1 đến 21.");
			return;
		}

		// Chuẩn hóa giá trị ngày (người dùng nhập 2-7, CN=8, nhưng trong hệ thống: 1-6, CN=7)
		int adjustedDay = (day == 8) ? 7 : day - 1;

		System.out.println("\nThời khóa biểu tuần " + week + ", thứ " + (day == 8 ? "Chủ nhật" : day));
		boolean coLich = false;

		// Duyệt qua từng môn học
		for (MonHoc monHoc : monHocList) {
			boolean xemLichHoc = false;
			StringBuilder lichHocOutput = new StringBuilder();

			// Duyệt qua danh sách lịch học của môn học
			for (LichHoc lichHoc : monHoc.getDSLichHoc()) {
				// Kiểm tra xem lịch học có thuộc tuần và thứ đã chọn không
				if (lichHoc.getWeeks().contains(week) && lichHoc.getDayOfWeek() == adjustedDay) {
					xemLichHoc = true;
					lichHocOutput.append(lichHoc.toString()).append("\n");
				}
			}

			// Nếu môn học có lịch học phù hợp, in thông tin môn học và lịch học
			if (xemLichHoc) {
				coLich = true;
				System.out.println("Mã MH: " + monHoc.getMaMH());
				System.out.println("Tên môn học: " + monHoc.getTenMH());
				System.out.println("Nhóm tổ: " + monHoc.getNhomTo());
				System.out.println("Số tín chỉ: " + monHoc.getSoTinChi());
				System.out.println("Lớp: " + monHoc.getLop());
				System.out.println("Lịch học:");
				System.out.print(lichHocOutput.toString());
				System.out.println();
			}
		}

		// Nếu không có lịch học nào khớp, thông báo
		if (!coLich) {
			System.out.println("Không có lịch học cho tuần " + week + ", thứ " + (day == 8 ? "Chủ nhật" : day));
		}
	}

	public List<MonHoc> getTKBTheoNgay(LocalDate date) {
		List<MonHoc> result = new ArrayList<>();
		LocalDate ngayBDHK = LocalDate.of(2025, 1, 16); // Ngày bắt đầu học kỳ

		// Tính số tuần từ ngày bắt đầu học kỳ đến ngày nhập
		long daysDiff = ChronoUnit.DAYS.between(ngayBDHK, date);
		int weekNum = (int) (daysDiff / 7) + 1; // Tính tuần (làm tròn xuống)
		int dayOfWeek = date.getDayOfWeek().getValue() % 7; // 1 = Thứ 2, 2 = Thứ 3, ..., 7 = Chủ nhật
		if (dayOfWeek == 7)
			dayOfWeek = 0; // Chuẩn hóa: 0 = Thứ 2, 6 = Chủ nhật

		// Kiểm tra tuần hợp lệ (1-22)
		if (weekNum < 1 || weekNum > 22) {
			return result;
		}

		// Lọc môn học có lịch học khớp với tuần và ngày
		for (MonHoc monHoc : monHocList) {
			for (LichHoc lichHoc : monHoc.getDSLichHoc()) {
				if (lichHoc.getWeeks().contains(weekNum) && lichHoc.getDayOfWeek() == dayOfWeek) {
					if (!result.contains(monHoc)) {
						result.add(monHoc);
					}
					break;
				}
			}
		}

		return result;
	}

	public void xuatTKBNgay(List<MonHoc> tkb) {
		if (tkb == null || tkb.isEmpty()) {
			System.out.println("Không có lịch học cho ngày này.");
			return;
		}

		for (MonHoc monHoc : tkb) {
			System.out.println("Mã MH: " + monHoc.getMaMH());
			System.out.println("Tên môn học: " + monHoc.getTenMH());
			System.out.println("Nhóm tổ: " + monHoc.getNhomTo());
			System.out.println("Số tín chỉ: " + monHoc.getSoTinChi());
			System.out.println("Lớp: " + monHoc.getLop());
			System.out.println("Lịch học:");
			for (LichHoc lichHoc : monHoc.getDSLichHoc()) {
				System.out.println(lichHoc.toString());
			}
			System.out.println();
		}
	}

	public void xuatTKBTuan(TuanHoc tuan) {
		System.out.println("\nThời khóa biểu tuần " + tuan.soTuan + ":");

		// Duyệt qua từng ngày trong tuần (0: Thứ 2, 1: Thứ 3, ..., 6: Chủ nhật)
		for (int day = 0; day < 7; day++) {
			String dayName = (day == 6) ? "Chủ nhật" : "Thứ " + (day + 2);
			System.out.println("\n" + dayName + ":");

			List<Map.Entry<MonHoc, LichHoc>> lichHocList = tuan.getDays().get(day);

			// Kiểm tra xem có lịch học nào không
			if (lichHocList == null || lichHocList.isEmpty()) {
				System.out.println("  - Không có lịch học.");
			} else {
				// Duyệt qua từng lịch học trong ngày
				for (Map.Entry<MonHoc, LichHoc> entry : lichHocList) {
					MonHoc monHoc = entry.getKey();
					LichHoc lichHoc = entry.getValue(); 

					// In thông tin môn học
					System.out.println("  - Môn học:");
					System.out.println("    Mã MH: " + monHoc.getMaMH());
					System.out.println("    Tên môn: " + monHoc.getTenMH());
					System.out.println("    Nhóm tổ: " + monHoc.getNhomTo());
					System.out.println("    Số tín chỉ: " + monHoc.getSoTinChi());
					System.out.println("    Lớp: " + monHoc.getLop());

					// In thông tin lịch học
					System.out.println("    Lịch học: " + lichHoc);
				}
			}
		}
	}

}

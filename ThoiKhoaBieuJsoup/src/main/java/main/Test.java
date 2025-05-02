package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Test {
//	1. Xem thời khóa biểu ngày hiện tại (ko cần nhập gì, chạy cái là hiển thị luôn)
//	2. Xem thời khóa biểu cả tuần (nhập chỉ số tuần 1-22)
//	3. Xem thời khóa biểu theo tuần, thứ (nhập tuần, nhập thứ)
//	4. Xem thời khóa biểu theo ngày (nhập ngày/tháng/năm)

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String html = "";
		String filePath = "D:\\Code\\Java_Uck\\Hello\\src\\main\\java\\main\\BuiHaiDang.html";
		System.out.println("Đang đọc file " + filePath + "...");
		try {
			html = new String(Files.readAllBytes(Paths.get(filePath)));
			System.out.println("Đã đọc file thành công!");

			LocalDate ngayBDHK = LocalDate.of(2025, 1, 16);
			TKBManager tkbManager = new TKBManager(html, ngayBDHK);

			// Menu chính
			while (true) {
				System.out.println(
						"\n===================================== QUẢN LÝ THỜI KHÓA BIỂU ======================================");
				System.out.println("1. Xem thời khóa biểu ngày hiện tại");
				System.out.println("2. Xem thời khóa biểu cả tuần");
				System.out.println("3. Xem thời khóa biểu theo tuần, thứ");
				System.out.println("4. Xem thời khóa biểu theo ngày");
				System.out.println("0. Thoát");
				System.out.print("Chọn chức năng: ");

				int choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				switch (choice) {
				case 0:
					System.out.println("Tạm biệt!");
					return;

				case 1:
					// 1. Xem thời khóa biểu ngày hiện tại
					List<MonHoc> tkbToday = tkbManager.getTKBNgayHienTai();
					System.out.println("\nThời khóa biểu ngày "
							+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					tkbManager.xuatTKBNgay(tkbToday);
					break;

				case 2:
					// 2. Xem thời khóa biểu cả tuần
					System.out.print("Nhập tuần (1-22): ");
					int week = scanner.nextInt();
					scanner.nextLine(); // Consume newline

					TuanHoc tuan = tkbManager.getTKBCaTuan(week);
					tkbManager.xuatTKBTuan(tuan);
					break;

				case 3:
					// 3. Xem thời khóa biểu theo tuần, thứ
					System.out.print("Nhập tuần (1-22): ");
					int weekNum = scanner.nextInt();
					System.out.print("Nhập thứ (2-7, CN=8): ");
					int dayInput = scanner.nextInt();

					scanner.nextLine();

					// Gọi phương thức getTKBTheoTuanThu (đã được sửa để tự in kết quả)
					tkbManager.getTKBTheoTuanThu(weekNum, dayInput);
					break;

				case 4:
					// 4. Xem thời khóa biểu theo ngày
					System.out.print("Nhập ngày (dd/MM/yyyy): ");
					String dateStr = scanner.nextLine();

					try {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate date = LocalDate.parse(dateStr, formatter);
						List<MonHoc> tkbDay = tkbManager.getTKBTheoNgay(date);
						System.out.println("\nThời khóa biểu ngày " + dateStr);
						tkbManager.xuatTKBNgay(tkbDay);
					} catch (Exception e) {
						System.out.println("Ngày nhập không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.");
					}
					break;

				default:
					System.out.println("Chức năng không hợp lệ!");
				}
			}

		} catch (IOException e) {
			System.err.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Đã xảy ra lỗi: " + e.getMessage());
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

}

package main;

import java.io.IOException;
import java.util.Scanner;

public class Test {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Đang lấy dữ liệu từ web qua read_web...");
		try {
			read_web.WebData data = read_web.fetchData();
			if (data.tableHtml == null) {
				System.out.println("Không thể lấy dữ liệu thời khóa biểu. Chương trình sẽ kết thúc.");
				return;
			}
			System.out.println("Đã lấy dữ liệu thành công!");
			TKBManager tkbManager = new TKBManager(data.tableHtml, data.startDate);

			int lc;
			do {
				System.out.println(
						"\n===================================== QUẢN LÝ THỜI KHÓA BIỂU ======================================");
				System.out.println("1. Xem thời khóa biểu ngày hiện tại");
				System.out.println("2. Xem thời khóa biểu cả tuần");
				System.out.println("3. Xem thời khóa biểu theo tuần, thứ");
				System.out.println("4. Xem thời khóa biểu theo ngày");
				System.out.println("0. Thoát");
				System.out.print("Chọn chức năng: ");

				lc = scanner.nextInt();
				scanner.nextLine();

				switch (lc) {
				case 0:
					System.out.println("Tạm biệt!");
					break;

				case 1:
//					Xem thời khóa biểu ngày hiện tại
					tkbManager.showTodaySchedule();
					break;

				case 2:
//					 Xem thời khóa biểu theo tuần, thứ
					tkbManager.showWeekSchedule(scanner.nextInt());
					break;

				case 3:
//					Xem thời khóa biểu theo ngày
					tkbManager.showWeekDaySchedule(scanner.nextInt(), scanner.nextInt());
					break;

				case 4:
//					Xem thời khóa biểu theo ngày
					tkbManager.showDateSchedule(scanner.nextLine());
					break;

				default:
					System.out.println("Chức năng không hợp lệ!");
				}
			} while (lc != 0);

		} catch (InterruptedException e) {
			System.err.println("Lỗi khi lấy dữ liệu từ web: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Lỗi khi phân tích dữ liệu: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Đã xảy ra lỗi: " + e.getMessage());
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
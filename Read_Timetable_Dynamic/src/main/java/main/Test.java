package main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String html = "";
        LocalDate startDate = null;

        System.out.println("Đang lấy dữ liệu từ web qua read_web...");
        try {
            read_web.WebData data = read_web.fetchData();
            if (data.tableHtml == null) {
                System.out.println("Không thể lấy dữ liệu thời khóa biểu. Chương trình sẽ kết thúc.");
                return;
            }
            html = data.tableHtml;
            startDate = data.startDate;
            System.out.println("Đã lấy dữ liệu thành công!");

            TKBManager tkbManager = new TKBManager(html, startDate);

            while (true) {
                System.out.println("\n===================================== QUẢN LÝ THỜI KHÓA BIỂU ======================================");
                System.out.println("1. Xem thời khóa biểu ngày hiện tại");
                System.out.println("2. Xem thời khóa biểu cả tuần");
                System.out.println("3. Xem thời khóa biểu theo tuần, thứ");
                System.out.println("4. Xem thời khóa biểu theo ngày");
                System.out.println("5. in html");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");

                int lc = scanner.nextInt();
                scanner.nextLine();

                switch (lc) {
                    case 0:
                        System.out.println("Tạm biệt!");
                        return;

                    case 1:
                        List<MonHoc> tkbToday = tkbManager.getTKBNgayHienTai();
                        System.out.println("\nThời khóa biểu ngày " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        tkbManager.xuatTKBNgay(tkbToday);
                        break;

                    case 2:
                        System.out.print("Nhập tuần (1-22): ");
                        int week = scanner.nextInt();
                        scanner.nextLine();

                        if (week < 1 || week > 22) {
                            System.out.println("Tuần không hợp lệ. Vui lòng nhập từ 1 đến 22.");
                            break;
                        }

                        TuanHoc tuan = tkbManager.getTKBCaTuan(week);
                        tkbManager.xuatTKBTuan(tuan);
                        break;

                    case 3:
                        System.out.print("Nhập tuần (1-22): ");
                        int weekNum = scanner.nextInt();
                        System.out.print("Nhập thứ (2-7, CN=8): ");
                        int dayInput = scanner.nextInt();
                        scanner.nextLine();
                        tkbManager.getTKBTheoTuanThu(weekNum, dayInput);
                        break;

                    case 4:
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
                    case 5: 
                    	System.out.println(html);
                    default:
                        System.out.println("Chức năng không hợp lệ!");
                }
            }
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


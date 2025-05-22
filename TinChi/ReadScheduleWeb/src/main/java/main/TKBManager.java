package main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class TKBManager {
    private List<Tuan> danhSachTuan;
    private LocalDate ngayBDHK;

    public TKBManager(String html, LocalDate ngayBDHK) throws IOException {
        this.danhSachTuan = new ArrayList<>();
        this.ngayBDHK = ngayBDHK;
        parseHTML(html);
    }

    private void parseHTML(String htmlContent) throws IOException {
        Document doc = Jsoup.parse(htmlContent);
        Element table = doc.select("table").first();
        Elements rows = table.select("tr");

        for (Element row : rows) {
            Elements cols = row.select("td");
            if (cols.isEmpty()) continue;

            try {
                if (!cols.get(0).attr("rowspan").isEmpty()) {
                    String maMH = cols.get(0).text().trim();
                    String tenMH = cols.get(1).text().trim();
                    String nhomTo = cols.get(2).text().trim();
                    String soTinChi = cols.get(3).text().trim();
                    String lop = cols.get(6).text().trim();
                    String thu = cols.get(8).text().trim();
                    String tietBD = cols.get(9).text().trim();
                    String soTiet = cols.get(10).text().trim();
                    String phong = cols.get(11).text().trim();
                    String giangVien = cols.get(12).text().trim();
                    String thoiGianHoc = cols.get(13).text().trim();

                    // Chuẩn hóa thu
                    thu = standardizeThu(thu);
                    MonHoc monHoc = new MonHoc(maMH, tenMH, nhomTo, soTinChi, lop, thu, tietBD, soTiet, phong, giangVien, thoiGianHoc);
                    addMonHoc(monHoc);
                } else {
                    String thu = cols.get(0).text().trim();
                    String tietBD = cols.get(1).text().trim();
                    String soTiet = cols.get(2).text().trim();
                    String phong = cols.get(3).text().trim();
                    String giangVien = cols.get(4).text().trim();
                    String thoiGianHoc = cols.get(5).text().trim();

                    // Chuẩn hóa thu
                    thu = standardizeThu(thu);
                    MonHoc monHoc = new MonHoc(cols.get(0).text().trim(), cols.get(1).text().trim(), cols.get(2).text().trim(),
                            cols.get(3).text().trim(), cols.get(6).text().trim(), thu, tietBD, soTiet, phong, giangVien, thoiGianHoc);
                    addMonHoc(monHoc);
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Lỗi phân tích hàng: " + e.getMessage());
            }
        }
    }

    // Chuẩn hóa giá trị thu
    private String standardizeThu(String thu) {
        if (thu == null) return "";
        thu = thu.trim();
        if (thu.equalsIgnoreCase("Chủ Nhật") || thu.equalsIgnoreCase("CN")) {
            return "CN";
        }
        try {
            int day = Integer.parseInt(thu);
            if (day == 1) return "2"; // Chuyển Chủ nhật (1) thành Thứ 2 (2) nếu cần
            if (day < 2 || day > 7) {
                System.out.println("Giá trị 'thu' không hợp lệ, chuyển thành rỗng: " + thu);
                return "";
            }
            return String.valueOf(day);
        } catch (NumberFormatException e) {
            System.out.println("Không thể chuẩn hóa 'thu': " + thu);
            return "";
        }
    }

    private void addMonHoc(MonHoc monHoc) {
        List<Integer> weeks = monHoc.getWeeks();
        int thu = monHoc.getDayOfWeek();

        if (thu == -1) {
            System.out.println("Bỏ qua MonHoc do 'thu' không hợp lệ: " + monHoc.getMaMH());
            return;
        }

        for (Integer week : weeks) {
            if (week < 1 || week > 22) continue;

            Tuan tuan = danhSachTuan.stream()
                    .filter(t -> t.getSoTuan() == week)
                    .findFirst()
                    .orElseGet(() -> {
                        Tuan newTuan = new Tuan();
                        newTuan.setSoTuan(week);
                        danhSachTuan.add(newTuan);
                        return newTuan;
                    });

            Ngay ngay = tuan.getNgay(thu);
            if (ngay == null) {
                ngay = new Ngay();
                tuan.addNgay(thu, ngay);
            }

            ngay.addMonHoc(monHoc);
        }
    }

    public void showTodaySchedule() {
        LocalDate today = LocalDate.now();
        xuatTKBNgay(getTKBTheoNgay(today));
    }

    public List<MonHoc> getTKBTheoNgay(LocalDate date) {
        List<MonHoc> result = new ArrayList<>();
        long daysDiff = ChronoUnit.DAYS.between(ngayBDHK, date);
        int weekNum = (int) (daysDiff / 7) + 1;
        int dayOfWeek = date.getDayOfWeek().getValue();

        if (weekNum < 1 || weekNum > 22) {
            return result;
        }

        Tuan tuan = danhSachTuan.stream()
                .filter(t -> t.getSoTuan() == weekNum)
                .findFirst()
                .orElse(null);

        if (tuan != null) {
            Ngay ngay = tuan.getNgay(dayOfWeek);
            if (ngay != null) {
                for (MonHoc monHoc : ngay.getDsMH()) {
                    if (monHoc.getWeeks().contains(weekNum) && monHoc.getDayOfWeek() == dayOfWeek) {
                        result.add(monHoc);
                    }
                }
            }
        }

        return result;
    }

    public void showWeekDaySchedule(int week, int day) {
        if (week < 1 || week > 22) {
            System.out.println("Tuần " + week + " không hợp lệ. Vui lòng nhập tuần từ 1 đến 22.");
            return;
        }

        int adjustedDay = (day == 8) ? 7 : day;
        Tuan tuan = danhSachTuan.stream()
                .filter(t -> t.getSoTuan() == week)
                .findFirst()
                .orElse(null);

        System.out.println("\nThời khóa biểu tuần " + week + ", thứ " + (day == 8 ? "Chủ nhật" : day));
        if (tuan == null) {
            System.out.println("Không có lịch học cho tuần " + week);
            return;
        }

        Ngay ngay = tuan.getNgay(adjustedDay);
        if (ngay == null || ngay.getDsMH().isEmpty()) {
            System.out.println("Không có lịch học cho thứ " + (day == 8 ? "Chủ nhật" : day));
        } else {
            List<MonHoc> filteredMonHoc = ngay.getDsMH().stream()
                    .filter(mh -> mh.getWeeks().contains(week) && mh.getDayOfWeek() == adjustedDay)
                    .collect(Collectors.toList());
            xuatTKBNgay(filteredMonHoc);
        }
    }

    public void showWeekSchedule(int week) {
        if (week < 1 || week > 22) {
            System.out.println("Tuần " + week + " không hợp lệ. Vui lòng nhập tuần từ 1 đến 22.");
            return;
        }

        Tuan tuan = danhSachTuan.stream()
                .filter(t -> t.getSoTuan() == week)
                .findFirst()
                .orElse(null);

        if (tuan == null) {
            System.out.println("Không có lịch học cho tuần " + week);
            return;
        }

        xuatTKBTuan(tuan);
    }

    public void showDateSchedule(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.println("\nThời khóa biểu ngày " + dateStr);
            xuatTKBNgay(getTKBTheoNgay(date));
        } catch (Exception e) {
            System.out.println("Ngày không hợp lệ (định dạng: dd/MM/yyyy).");
        }
    }

    private void xuatTKBNgay(List<MonHoc> tkb) {
        if (tkb == null || tkb.isEmpty()) {
            System.out.println("Không có lịch học cho ngày này.");
            return;
        }

        for (MonHoc monHoc : tkb) {
            System.out.println(monHoc.toString());
        }
    }

    private void xuatTKBTuan(Tuan tuan) {
        System.out.println("\nThời khóa biểu tuần " + tuan.getSoTuan() + ":");

        for(int day = 2; day <= 7; day++) {
            String dayName = (day == 7) ? "Chủ nhật" : "Thứ " + day;
            System.out.println("\n" + dayName + ":");

            Ngay ngay = tuan.getNgay(day);
            if (ngay == null || ngay.getDsMH().isEmpty()) {
                System.out.println("  - Không có lịch học.");
            } else {
                List<MonHoc> filteredMonHoc = ngay.getDsMH().stream()
                        .filter(mh -> mh.getWeeks().contains(tuan.getSoTuan()) && mh.getDayOfWeek() == day)
                        .collect(Collectors.toList());
                if (filteredMonHoc.isEmpty()) {
                    System.out.println("  - Không có lịch học.");
                } else {
                    for (MonHoc monHoc : filteredMonHoc) {
                        System.out.println("  - " + monHoc.getMaMH() + ": " + monHoc.getTenMH());
                        System.out.println("    Nhóm tổ: " + monHoc.getNhomTo());
                        System.out.println("    Số tín chỉ: " + monHoc.getSoTinChi());
                        System.out.println("    Lớp: " + monHoc.getLop());
                        System.out.println("    Thứ: " + monHoc.getThu() + " | Tiết: " + monHoc.getTietBD() + "-" +
                                (Integer.parseInt(monHoc.getTietBD()) + Integer.parseInt(monHoc.getSoTiet()) - 1) +
                                " | Phòng: " + monHoc.getPhongHoc() + " | Giảng viên: " + monHoc.getGiangVien() +
                                " | Thời gian: " + monHoc.getThoiGianHoc());
                    }
                }
            }
        }
    }
}
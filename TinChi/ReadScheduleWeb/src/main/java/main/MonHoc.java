package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MonHoc {
    private String maMH;
    private String tenMH;
    private String nhomTo;
    private String soTinChi;
    private String lop;
    private String thu;
    private String tietBD;
    private String soTiet;
    private String phongHoc;
    private String giangVien;
    private String thoiGianHoc;

    public MonHoc(String maMH, String tenMH, String nhomTo, String soTinChi, String lop,
                  String thu, String tietBD, String soTiet, String phongHoc, String giangVien, String thoiGianHoc) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.nhomTo = nhomTo;
        this.soTinChi = soTinChi;
        this.lop = lop;
        this.thu = thu;
        this.tietBD = tietBD;
        this.soTiet = soTiet;
        this.phongHoc = phongHoc;
        this.giangVien = giangVien;
        this.thoiGianHoc = thoiGianHoc;
    }

    public String getMaMH() {
        return maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public String getNhomTo() {
        return nhomTo;
    }

    public String getSoTinChi() {
        return soTinChi;
    }

    public String getLop() {
        return lop;
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

    public List<Integer> getWeeks() {
        List<Integer> weeks = new ArrayList<>();
        if (thoiGianHoc == null || thoiGianHoc.isEmpty()) return weeks;

        try {
            if (thoiGianHoc.contains("-")) {
                String[] range = thoiGianHoc.split("-");
                int start = Integer.parseInt(range[0].trim());
                int end = Integer.parseInt(range[1].trim());
                for (int i = start; i <= end; i++) {
                    if (!weeks.contains(i)) weeks.add(i);
                }
            } else if (thoiGianHoc.contains(",")) {
                weeks = Arrays.stream(thoiGianHoc.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .distinct()
                        .collect(Collectors.toList());
            } else {
                for (char c : thoiGianHoc.toCharArray()) {
                    int week = Integer.parseInt(String.valueOf(c));
                    if (!weeks.contains(week)) weeks.add(week);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng tuần: " + thoiGianHoc);
        }
        return weeks;
    }

    public int getDayOfWeek() {
        if (thu == null || thu.isEmpty()) {
            System.out.println("Lỗi: Giá trị 'thu' rỗng hoặc null");
            return -1; // Giá trị không hợp lệ
        }
        try {
            if (thu.equalsIgnoreCase("CN") || thu.equalsIgnoreCase("Chủ Nhật")) {
                return 7;
            }
            int day = Integer.parseInt(thu.trim());
            if (day < 2 || day > 7) {
                System.out.println("Lỗi: Giá trị 'thu' không hợp lệ: " + thu);
                return -1;
            }
            return day;
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng 'thu': " + thu);
            return -1;
        }
    }
    @Override
    public String toString() {
        return String.format("Mã MH: %s\nTên môn học: %s\nNhóm tổ: %s\nSố tín chỉ: %s\nLớp: %s\n" +
                        "Thứ: %s | Tiết: %s-%s | Phòng: %s | Giảng viên: %s | Thời gian: %s\n",
                maMH, tenMH, nhomTo, soTinChi, lop, thu, tietBD,
                Integer.parseInt(tietBD) + Integer.parseInt(soTiet) - 1, phongHoc, giangVien, thoiGianHoc);
    }
}
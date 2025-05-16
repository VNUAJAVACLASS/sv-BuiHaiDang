package main;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuanHoc {

	int soTuan;
    Map<Integer, List<Map.Entry<MonHoc, LichHoc>>> days;

    public TuanHoc(int soTuan) {
        this.soTuan = soTuan;
        this.days = new HashMap<>();
        // Khởi tạo danh sách cho mỗi ngày 0: thứ 2, 1: thứ 3
        for (int i = 0; i < 7; i++) {
            this.days.put(i, new ArrayList<>());
        }
    }

    // Thêm lịch học cùng với thông tin môn học
    public void addLichHoc(int day, MonHoc monHoc, LichHoc lichHoc) {
        this.days.get(day).add(Map.entry(monHoc, lichHoc));
    }

    // Getter cho days
    public Map<Integer, List<Map.Entry<MonHoc, LichHoc>>> getDays() {
        return days;
    }
}

package main;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuanHoc {
	private int soTuan;
    private Map<Integer, List<Map.Entry<MonHoc, LichHoc>>> days;
    private Map<Integer, Ngay> dsNgay = new HashMap<Integer, Ngay>();
    
    public TuanHoc(int soTuan) {
        this.setSoTuan(soTuan);
        this.days = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            this.days.put(i, new ArrayList<>());
        }
    }

    // Thêm lịch học cùng với thông tin môn học
    public void addLichHoc(int day, MonHoc monHoc, LichHoc lichHoc) {
        this.days.get(day).add(Map.entry(monHoc, lichHoc));
    }

    
    
    public Map<Integer, List<Map.Entry<MonHoc, LichHoc>>> getDays() {
        return days;
    }

	public int getSoTuan() {
		return soTuan;
	}

	public void setSoTuan(int soTuan) {
		this.soTuan = soTuan;
	}
}

package main;

import java.util.HashMap;
import java.util.Map;

public class Tuan {
    private Map<Integer, Ngay> danhSachNgay = new HashMap<>();
    private int soTuan;

    public void addNgay(Integer thu, Ngay ngay) {
        danhSachNgay.put(thu, ngay);
    }

    public Map<Integer, Ngay> getDanhSachNgay() {
        return danhSachNgay;
    }

    public Ngay getNgay(Integer thu) {
        return danhSachNgay.get(thu);
    }

    public int getSoTuan() {
        return soTuan;
    }

    public void setSoTuan(int soTuan) {
        this.soTuan = soTuan;
    }

    @Override
    public String toString() {
        return "Tuan{soTuan=" + soTuan + ", danhSachNgay=" + danhSachNgay + '}';
    }
}
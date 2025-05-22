package main;

import java.util.ArrayList;

import java.util.List;



public class Ngay {
    private List<MonHoc> dsMH = new ArrayList<>();

    public void addMonHoc(MonHoc monHoc) {
        dsMH.add(monHoc);
    }

    public List<MonHoc> getDsMH() {
        return dsMH;
    }

    public void setDsMH(List<MonHoc> dsMH) {
        this.dsMH = dsMH;
    }

    @Override
    public String toString() {
        return "Ngay{dsMH=" + dsMH + '}';
    }
}
package main;

import java.util.ArrayList;
import java.util.List;

public class MonHoc {
	private String maMH;
	private String tenMH;
	private String nhomTo;
	private String soTinChi;
	private String lop; 
	private List<LichHoc> dsLichHoc;

	

	public MonHoc(String maMH, String tenMH, String nhomTo, String soTinChi, String lop) {
		super();
		this.maMH = maMH;
		this.tenMH = tenMH;
		this.nhomTo = nhomTo;
		this.soTinChi = soTinChi;
		this.lop = lop;
		this.dsLichHoc = new ArrayList<>(); // khoi tao danh sach
	}
	public void addLichHoc(LichHoc lichHoc) {
        this.dsLichHoc.add(lichHoc);
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

	public List<LichHoc> getDSLichHoc() {
		return dsLichHoc;
	}
	
	// Phương thức toString mặc định
    @Override
    public String toString() {
        return toString(dsLichHoc); // Gọi toString với danh sách lịch học mặc định
    }

    // Phương thức toString với danh sách lịch học được truyền vào
    public String toString(List<LichHoc> filteredLichHoc) {
        StringBuilder sb = new StringBuilder();
        sb.append("Mã MH: ").append(maMH).append("\n");
        sb.append("Tên môn học: ").append(tenMH).append("\n");
        sb.append("Nhóm tổ: ").append(nhomTo).append("\n");
        sb.append("Số tín chỉ: ").append(soTinChi).append("\n");
        sb.append("Lớp: ").append(lop).append("\n");
        sb.append("Lịch học:\n");
        if (filteredLichHoc.isEmpty()) {
            sb.append("Không có lịch học.\n");
        } else {
            for (LichHoc lichHoc : filteredLichHoc) {
                sb.append(lichHoc.toString()).append("\n");
            }
        }
        return sb.toString();
    }

}

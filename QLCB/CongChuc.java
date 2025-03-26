package De_31;

public class CongChuc extends Nguoi{
	private double heSoLuong;
	private double tienPhuCap;

	public CongChuc(String hoVaTen, String ngaySinh, String queQuan, double heSoLuong, double tienPhuCap) {
	super(hoVaTen, ngaySinh, queQuan);
	this.heSoLuong = heSoLuong;
	this.tienPhuCap = tienPhuCap;
}


	public double getHeSoLuong() {
		return heSoLuong;
	}

	public double getTienPhuCap() {
		return tienPhuCap;
	}

	
}

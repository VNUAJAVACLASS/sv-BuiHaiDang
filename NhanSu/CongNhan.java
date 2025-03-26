package De_2;

import java.util.Scanner;

public class CongNhan extends CanBo{

	private String bacCongNhan;
	
	public CongNhan() {};
	public CongNhan(String hoTen, String ngaySinh, String gioiTinh, String diaChi,String bacCongNhan) {
		super(hoTen, ngaySinh, gioiTinh, diaChi);
		this.bacCongNhan = bacCongNhan;
	}

	public String getBacCongNhan() {
		return bacCongNhan;
	}

	public void setBacCongNhan(String bacCongNhan) {
		this.bacCongNhan = bacCongNhan;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+ " " +" Bậc: "+ bacCongNhan;
	}
	
	@Override
	public void enterInfor(Scanner sc) {
		super.enterInfor(sc);
		System.out.println("Nhập bâc: ");
		this.bacCongNhan = sc.nextLine();
	}
}

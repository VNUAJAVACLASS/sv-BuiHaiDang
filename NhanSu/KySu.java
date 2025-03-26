package De_2;

import java.util.Scanner;

public class KySu extends CanBo{
	private String nganhDaoTao;

	public KySu() {};
	
	public KySu(String hoTen, String ngaySinh, String gioiTinh, String diaChi, String nganhDaoTao) {
		super(hoTen, ngaySinh, gioiTinh, diaChi);
		this.nganhDaoTao = nganhDaoTao;
	}

	public String getNganhDaoTao() {
		return nganhDaoTao;
	}


	public void setNganhDaoTao(String nganhDaoTao) {
		this.nganhDaoTao = nganhDaoTao;
	}
	
	@Override
	public void enterInfor(Scanner sc) {
		super.enterInfor(sc);
		System.out.println("Nhập ngành: ");
		nganhDaoTao = sc.nextLine();
	}
	
	@Override
	public String toString() {
		return super.toString() + " Nghành: "+ nganhDaoTao;
	}
	
}

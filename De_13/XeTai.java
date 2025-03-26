package De_13;

import java.util.Scanner;

public class XeTai extends PTGT {
	private float trongTai;

	public XeTai() {
	}

	public XeTai(float trongTai) {
		this.trongTai = trongTai;
	}

	public XeTai(String hangSX, int namSX, float giaBan, String mauXe, float trongTai) {
		super(hangSX, namSX, giaBan, mauXe);
		this.trongTai = trongTai;
	}

	public float getTrongTai() {
		return trongTai;
	}

	public void setTrongTai(float trongTai) {
		this.trongTai = trongTai;
	}
	
	@Override
	public void nhapThongTin(Scanner sc) {
		super.nhapThongTin(sc);
		System.out.println("Nhap trongTai: "); trongTai = sc.nextFloat();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "XeTai: "+ super.toString()+" ,trongTai= "+trongTai;
	}
}

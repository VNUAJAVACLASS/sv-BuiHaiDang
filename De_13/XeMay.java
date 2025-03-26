package De_13;

import java.util.Scanner;

public class XeMay extends PTGT {
	private float congSuat;
	

	public XeMay() {}
	
	public XeMay(float congSuat) {
		this.congSuat = congSuat;
	}
	
	public XeMay(String hangSX, int namSX, float giaBan, String mauXe,float congSuat) {
		super(hangSX, namSX, giaBan, mauXe);
		this.congSuat = congSuat;
	}

	public float getCongSuat() {
		return congSuat;
	}

	public void setCongSuat(float congSuat) {
		this.congSuat = congSuat;
	}
	
	
	@Override
	public void nhapThongTin(Scanner sc) {
		super.nhapThongTin(sc);
		System.out.println("Nhap congSuat: "); congSuat = sc.nextFloat();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "XeMay: "+ super.toString()+ " ,congSuat="+congSuat;
	}
	
	
}

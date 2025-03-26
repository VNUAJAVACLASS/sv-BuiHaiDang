package De_13;

import java.util.Scanner;

public class PTGT {
	private String hangSX;
	private int namSX;
	private float giaBan;
	private String mauXe;

	public PTGT() {
	}

	public PTGT(String hangSX, int namSX, float giaBan, String mauXe) {
		this.hangSX = hangSX;
		this.namSX = namSX;
		this.giaBan = giaBan;
		this.mauXe = mauXe;
	}

	public String getHangSX() {
		return hangSX;
	}

	public void setHangSX(String hangSX) {
		this.hangSX = hangSX;
	}

	public int getNamSX() {
		return namSX;
	}

	public void setNamSX(int namSX) {
		this.namSX = namSX;
	}

	public float getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}

	public String getMauXe() {
		return mauXe;
	}

	public void setMauXe(String mauXe) {
		this.mauXe = mauXe;
	}
	
	public void nhapThongTin(Scanner sc) {
		sc.nextLine();
		System.out.println("Nhập hangSX:"); hangSX = sc.nextLine();
		System.out.println("Nhap namSX: "); namSX = sc.nextInt();
		sc.nextLine();
		System.out.println("Nhap giaBan: "); giaBan = sc.nextFloat();
		sc.nextLine();
		System.out.println("Nhap mauXe: "); mauXe  = sc.nextLine();
	}
	

	@Override
	public String toString() {
		return " hangSX=" + this.hangSX + ", namSX=" + namSX + ", giaBan=" + giaBan + ", mauXe=" + mauXe;
	}
	
}

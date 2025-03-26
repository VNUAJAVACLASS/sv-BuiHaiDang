package De_13;

import java.util.Scanner;

public class Oto extends PTGT {
	private int soChoNgoi;
	private String kieuDongCo;

	public Oto() {
	}

	public Oto(int soChoNgoi, String kieuDongCo) {
		this.soChoNgoi = soChoNgoi;
		this.kieuDongCo = kieuDongCo;
	}

	public Oto(String hangSX, int namSX, float giaBan, String mauXe, int soChoNgoi, String kieuDongCo) {
		super(hangSX, namSX, giaBan, mauXe);
		this.soChoNgoi = soChoNgoi;
		this.kieuDongCo = kieuDongCo;
	}

	public int getSoChoNgoi() {
		return soChoNgoi;
	}

	public void setSoChoNgoi(int soChoNgoi) {
		this.soChoNgoi = soChoNgoi;
	}

	public String getKieuDongCo() {
		return kieuDongCo;
	}

	public void setKieuDongCo(String kieuDongCo) {
		this.kieuDongCo = kieuDongCo;
	}
	
	@Override
	public void nhapThongTin(Scanner sc) {
		super.nhapThongTin(sc);
		System.out.println("Nhap soChoNgoi: "); soChoNgoi = sc.nextInt();
		sc.nextLine();
		System.out.println("Nhap kieuDongCo: "); kieuDongCo = sc.nextLine();
	}
	
	@Override
	public String toString() {
		return "Oto: "+ super.toString()+" ,soChoNgoi= "+soChoNgoi+" ,kieuDongCo= "+kieuDongCo;
	}

}

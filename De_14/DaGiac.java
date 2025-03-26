package De_14;

public class DaGiac {
	private int soCanh;
	protected int[] kichThuocCanh;

	public DaGiac() {
	}

	public DaGiac(int soCanh, int[] kichThuocCanh) {
		this.soCanh = soCanh;
		this.kichThuocCanh = kichThuocCanh;
	}

	public int getSoCanh() {
		return soCanh;
	}

	public void setSoCanh(int soCanh) {
		this.soCanh = soCanh;
	}

	public int[] getKichThuocCanh() {
		return kichThuocCanh;
	}

	public void setKichThuocCanh(int[] kichThuocCanh) {
		this.kichThuocCanh = kichThuocCanh;
	}


	public float tinhChuVi() {
		float chuvi = 0;
		for (float i : kichThuocCanh) {
			chuvi +=i;
		}
		return chuvi;
	}
	
	public void hienThiCacCanh() {
		System.out.println("Gia tri cac canh: ");
		for (int i : kichThuocCanh) {
			System.out.print(i+" ");
		}
	}
}

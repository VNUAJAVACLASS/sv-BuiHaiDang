package De_14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TamGiac extends DaGiac {
	private List<TamGiac> dsTamGiac;

	public TamGiac() {
		super(3, new int[3]);
		dsTamGiac = new ArrayList<>();
	}

	public TamGiac(int[] kichThuocCanh) {
		super(3, kichThuocCanh);
	}

	public void nhapVaoTamGiac(Scanner sc) {
		System.out.println("Nhap so tam giac");
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.println("Nhập các cạnh của tam giác " + (i + 1) + ": ");
			int[] kichThuocCanh = new int[3];
			for (int j = 0; j < 3; j++) {
				kichThuocCanh[j] = sc.nextInt();
			}
			TamGiac tamGiac = new TamGiac(kichThuocCanh);
			if (tamGiac.kiemTraHopLe()) {
				dsTamGiac.add(tamGiac);
			} else {
				System.out.println("Tam giac khong hop le");
			}
		}

	}

	public void inTamGiacDienTichLonNhat() {
		float maxDienTich = 0;
		TamGiac tamGiacLonNhat = null;
		for (TamGiac tamGiac : dsTamGiac) {
			float dienTich = tamGiac.tindDienTich();
			if (maxDienTich < dienTich) {
				maxDienTich = dienTich;
				tamGiacLonNhat = tamGiac;
			}
		}
		if (tamGiacLonNhat != null) {
			System.out.println("Tam giác có diện tích lớn nhất:");
			tamGiacLonNhat.hienThiCacCanh();
			System.out.println("Diện tích: " + tamGiacLonNhat.tindDienTich());
		} else {
			System.out.println("Không có tam giác hợp lệ.");
		}
	}


    public boolean kiemTraHopLe() {
        if (getSoCanh() != 3)
            return false;
        int a = kichThuocCanh[0];
        int b = kichThuocCanh[1];
        int c = kichThuocCanh[2];
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

	public float tindDienTich() {
		if (kiemTraHopLe())
			return 0;

		int a = kichThuocCanh[0];												
		int b = kichThuocCanh[1];
		int c = kichThuocCanh[2];
		float p = (float) ((a + b + c) / 2.0);
		return (float) Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}
	
	public static void main(String[] args) {
		TamGiac tamgiac = new TamGiac();
		Scanner sc = new Scanner(System.in);
		tamgiac.nhapVaoTamGiac(sc);
		tamgiac.inTamGiacDienTichLonNhat();
		sc.close();
	}

}

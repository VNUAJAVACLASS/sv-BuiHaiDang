
public class TamGiac {
	Diem A;
	Diem B;
	Diem C;

	public TamGiac() {
	}

	public TamGiac(Diem a, Diem b, Diem c) {
		A = a;
		B = b;
		C = c;
	}

	public float dienTich() {
		float AB = A.kc(B);
		float BC = B.kc(C);
		float CA = C.kc(A);

		float p = (AB + BC + CA) / 2;

		float dt = (float) Math.sqrt(p * (p - AB) * (p - BC) * (p - CA));
		
		return dt;
	}
	
	
	public float chuVi() {
	    float AB = A.kc(B);
	    float BC = B.kc(C);
	    float CA = C.kc(A);
	    
	    float cv = AB + BC + CA;
	    
	    return cv;
	}
	
	
	public static void main(String[] args) {
		Diem A = new Diem(3, 3);
		Diem B = new Diem(3, 4);
		Diem C = new Diem(5, 3);

		
		TamGiac tamGiac = new TamGiac(A, B, C);

	
	
	
	}

}

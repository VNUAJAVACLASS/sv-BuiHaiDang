
public class Test {
		
		public static void main(String[] args) {
			Diem A = new Diem(0, 0);
			Diem B = new Diem(0, 4);
			Diem C = new Diem(3, 0);

			
			TamGiac tamGiac = new TamGiac(A, B, C);
			System.out.println("Dien tich: "+  tamGiac.dienTich());
			System.out.println("Chu vi: "+tamGiac.chuVi());

		}
		
		
}	

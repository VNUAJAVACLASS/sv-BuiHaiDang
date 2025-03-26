
public class Diem {
	float x;
	float y;

	public Diem() {
	}

	public Diem(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float kc(Diem d) {
		float kc = (float) Math.sqrt((x-d.x)*(x-d.x)+(y-d.y)*(y-d.y));
		return kc;
	}

	public void inDiem() {
		System.out.println("Diem: ( "+ Diem.this.x + Diem.this.y+ " )");
	}

}

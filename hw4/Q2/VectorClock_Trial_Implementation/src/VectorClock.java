
public class VectorClock {
	private int p0, p1, p2;

	public VectorClock(int p0, int p1, int p2) {
		super();
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
	}

	public int getP0() {
		return p0;
	}

	public void setP0(int p0) {
		this.p0 = p0;
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	@Override
	public String toString() {
		return "VectorClock [p0=" + p0 + ", p1=" + p1 + ", p2=" + p2 + "]";
	}

}

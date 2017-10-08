package com.sjsu.vector;

public class Cut {

	VectorClock vc;

	Cut(VectorClock cut) {
		this.vc = cut;
	}

	public VectorClock getVc() {
		return vc;
	}

	@Override
	public String toString() {
		return "Cut [vc=" + vc + "]";
	}

}

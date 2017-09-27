package com.sjsu.vector;

import java.util.Arrays;

public class VectorClock implements Comparable<VectorClock> {
	int compareToIndex;
	int[] vectorClock;

	public VectorClock(int noOfProcesses) {
		vectorClock = new int[noOfProcesses];
	}

	@Override
	public int compareTo(VectorClock o) {
		// This will compare only at the required index and not each and every value.
		return this.vectorClock[compareToIndex] - o.vectorClock[compareToIndex];
	}

	public int getCompareToIndex() {
		return compareToIndex;
	}

	public void setCompareToIndex(int compareToIndex) {
		this.compareToIndex = compareToIndex;
	}

	@Override
	public String toString() {
		return "VectorClock [vc=" + Arrays.toString(vectorClock) + "]";
	}

	/**
	 * Based on a event vector clock will be incremented, changed or updated. Which
	 * index should be updated will be decided by a processor
	 * 
	 * @param index
	 * @param value
	 */
	public void updateAt(int index, int value) {
		vectorClock[index] = value;
	}

	public void updateByOneAt(int index) {
		vectorClock[index] += 1;
	}

}

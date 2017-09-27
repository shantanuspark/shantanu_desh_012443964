package com.sjsu.vector;

public class Main {
	public static void main(String[] args) {
		Algorithm algorithm = new Algorithm();
		algorithm.executePlan();

		// Final Vector Clocks of all the processors
		System.out.println("Process p0 " + algorithm.getProcessor0().getVectorClock().toString());
		System.out.println("Process p1 " + algorithm.getProcessor1().getVectorClock().toString());
		System.out.println("Process p2 " + algorithm.getProcessor2().getVectorClock().toString());

	}
}

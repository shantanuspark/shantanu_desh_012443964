public class Main {

	public static void main(String[] args) {

		// Creating a ring and adding all processors to the ring.
		Ring ring = new Ring();
		ring.add(new Processor(44));
		ring.add(new Processor(25));
		ring.add(new Processor(32));
		ring.add(new Processor(56));
		ring.add(new Processor(99));

		System.out.println("The formed ring is");
		ring.print();

		// Starting election process
		ring.startElection();
	}
}
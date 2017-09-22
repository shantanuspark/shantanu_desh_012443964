
public class ProcessingUnit {
	private static Processor p0, p1, p2;

	public static void initializeProcessors() {
		p0 = new Processor(0);
		p1 = new Processor(1);
		p2 = new Processor(2);
		System.out.println("All processors initialized");
	}

	public static Processor getProcessorById(int id) {
		switch (id) {
			case 0:
				return p0;
				
			case 1:
				return p1;
				
			case 2:
				return p2;
	
			default:
				return null;
		}
	}
	
	public static Processor getProcessorP0() {
		return p0;
	}
	
	public static Processor getProcessorP1() {
		return p1;
	}
	
	public static Processor getProcessorP2() {
		return p2;
	}
}

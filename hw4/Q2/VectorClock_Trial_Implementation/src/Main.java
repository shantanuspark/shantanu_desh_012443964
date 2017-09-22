
public class Main {
	public static void main(String[] args) {
		
		// Create instance of all the processors
		ProcessingUnit.initializeProcessors();
		
		Processor p0, p1, p2;
		p0 = ProcessingUnit.getProcessorP0();
		p1 = ProcessingUnit.getProcessorP1();
		p2 = ProcessingUnit.getProcessorP2();
		
		p0.setEventArray(new String[] { "NO", "P1", "NO", "P2", "NO", "CO", "NO", "NO" });
		p1.setEventArray(new String[] { "P0", "NO", "P2", "P0", "NO", "P0", "NO", "CO" });
		p2.setEventArray(new String[] { "NO", "CO", "P0", "NO", "NO", "P1", "NO", "NO" });
		
		p0.start();
		p1.start();
		p2.start();

	}
}

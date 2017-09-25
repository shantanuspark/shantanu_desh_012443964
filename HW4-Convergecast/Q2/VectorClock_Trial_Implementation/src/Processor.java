
public class Processor extends Thread {
	private int processorId;
	private VectorClock vectorClock;
	private String eventArray[];
	private EventManager eventManager;

	public Processor(int id) {
		this.processorId = id;
		eventManager = new EventManager();
		vectorClock = new VectorClock(0, 0, 0);
	}

	public int getProcesorId() {
		return processorId;
	}

	public void setProcessorId(int id) {
		this.processorId = id;
	}

	public VectorClock getVectorClock() {
		return vectorClock;
	}

	public void setVectorClock(VectorClock vectorClock) {
		this.vectorClock = vectorClock;
	}

	public String[] getEventArray() {
		return eventArray;
	}

	public void setEventArray(String[] eventArray) {
		this.eventArray = eventArray;
	}

	@Override
	public void run() {
		for (String event : eventArray) {
			System.out.println(this.vectorClock.toString());
			if (event.equals("NO")) {
				continue;
			} else if (event.equals("CO")) {
				eventManager.computationEvent(this);
			} else {
				int destProcessorId = 0;
				switch (event) {
				case "P0":
					processorId = 0;
					break;

				case "P1":
					processorId = 1;
					break;

				case "P2":
					processorId = 2;
					break;

				default:
					break;
				}
				Processor p = ProcessingUnit.getProcessorById(destProcessorId);
				eventManager.sendEvent(this, p);
			}

		}

		super.run();
	}
	
	public void incrementClock() {
		switch (this.processorId) {
		case 0:
			this.vectorClock.setP0(this.vectorClock.getP0() + 1);
			break;

		case 1:
			this.vectorClock.setP1(this.vectorClock.getP1() + 1);
			break;

		case 2:
			this.vectorClock.setP2(this.vectorClock.getP2() + 1);
			break;
		}
	}
}

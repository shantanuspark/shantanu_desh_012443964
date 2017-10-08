package com.sjsu.vector;

public class Algorithm {
	private int numberOfProcessors;
	private Processor processor0, processor1, processor2; // Creating three processors

	public Algorithm() {
		super();
		this.numberOfProcessors = 3;
		processor0 = new Processor(0, numberOfProcessors);
		processor1 = new Processor(1, numberOfProcessors);
		processor2 = new Processor(2, numberOfProcessors);
	}

	/**
	 * 
	 * @param processor
	 * @param message
	 * 
	 *            This method carries out the computational event.
	 */
	public void compute(Processor processor, Message message) {
		processor.sendMessgeToMyBuffer(message);
	}

	/**
	 * 
	 * @param cut
	 * @return
	 * 
	 * 		Checks if the cut is consistent or not by comparing it with the m' of
	 *         each processor
	 */
	private boolean isConsistentCut(Cut cut) {

		VectorClock cutVC = cut.getVc();

		VectorClock processor0MDash = processor0.getMDash(cutVC);
		VectorClock processor1MDash = processor1.getMDash(cutVC);
		VectorClock processor2MDash = processor2.getMDash(cutVC);

		processor0MDash.setCompareToIndex(1);
		if (processor0MDash.compareTo(cutVC) > 0) {
			return false;
		}
		processor0MDash.setCompareToIndex(2);
		if (processor0MDash.compareTo(cutVC) > 0) {
			return false;
		}

		processor1MDash.setCompareToIndex(0);
		if (processor1MDash.compareTo(cutVC) > 0) {
			return false;
		}
		processor1MDash.setCompareToIndex(2);
		if (processor1MDash.compareTo(cutVC) > 0) {
			return false;
		}

		processor2MDash.setCompareToIndex(0);
		if (processor2MDash.compareTo(cutVC) > 0) {
			return false;
		}
		processor2MDash.setCompareToIndex(1);
		if (processor2MDash.compareTo(cutVC) > 0) {
			return false;
		}

		return true;

	}

	/**
	 * This is the method which carries out the actual execution which includes
	 * send/receive operations as well as computations
	 */
	protected void executePlan() {
		System.out.println("Event Computation 2");
		compute(processor2, new Message(MessageType.COMPUTATION, processor2.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());

		System.out.println("Event Computation 2");
		compute(processor2, new Message(MessageType.COMPUTATION, processor2.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());

		System.out.println("Event Send 0->1");
		send(processor0, processor1, new Message(MessageType.SEND, processor0.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());

		System.out.println("Event Send 2->1");
		send(processor2, processor1, new Message(MessageType.SEND, processor2.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());

		System.out.println("Event Send 0->2");
		send(processor0, processor2, new Message(MessageType.SEND, processor0.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());

		System.out.println("Event Computation 0");
		compute(processor0, new Message(MessageType.COMPUTATION, processor0.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());

		System.out.println("Event Send 1->2");
		send(processor1, processor2, new Message(MessageType.SEND, processor1.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());

		System.out.println("Event Send 2->1");
		send(processor2, processor1, new Message(MessageType.SEND, processor2.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());

		System.out.println("Event Send 1->0");
		send(processor1, processor0, new Message(MessageType.SEND, processor1.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());
		
		System.out.println("Event Computation 0");
		compute(processor0, new Message(MessageType.COMPUTATION, processor0.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());

		System.out.println("Event Computation 2");
		compute(processor2, new Message(MessageType.COMPUTATION, processor2.getVectorClock()));
		System.out.println("Process p0 " + processor0.getVectorClock().toString());
		System.out.println("Process p1 " + processor1.getVectorClock().toString());
		System.out.println("Process p2 " + processor2.getVectorClock().toString());

		VectorClock cut = new VectorClock(new int[] { 2, 2, 3 });
		Cut c = new Cut(cut);
		System.out.println(c.toString() + " is consistent -> " + isConsistentCut(c));

		VectorClock secondCut = new VectorClock(new int[] { 1, 2, 4 });
		Cut c2 = new Cut(secondCut);
		System.out.println(c2.toString() + " is consistent -> " + isConsistentCut(c2));

		VectorClock thirdCut = new VectorClock(new int[] { 1, 0, 0 });
		Cut c3 = new Cut(thirdCut);
		System.out.println(c3.toString() + " is consistent -> " + isConsistentCut(c3));

	}

	public int getNumberOfProcessors() {
		return numberOfProcessors;
	}

	public Processor getProcessor0() {
		return processor0;
	}

	public Processor getProcessor1() {
		return processor1;
	}

	public Processor getProcessor2() {
		return processor2;
	}

	/**
	 * 
	 * @param sender
	 * @param receiver
	 * @param message
	 * 
	 *            This method sends the message from one processor to another while
	 *            incrementing the sender's vector clock.
	 */
	public void send(Processor sender, Processor receiver, Message message) {
		sender.getVectorClock().updateByOneAt(sender.getId());
		receiver.sendMessgeToMyBuffer(message);
		sender.addToStore(sender.getVectorClock());
	}
}

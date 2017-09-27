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
	}
}

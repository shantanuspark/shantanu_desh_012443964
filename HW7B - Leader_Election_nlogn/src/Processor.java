public class Processor implements Runnable {
	int id;
	private Processor leader;
	Processor next;
	Processor previous;
	private int ringSize = 5;
	private boolean isCandidate;

	public Processor(int id) {
		isCandidate = true;
		this.id = id;
	}

	/**
	 * Run method for every processor running as a thread.
	 */
	@Override
	public void run() {

		System.out.println("Initiating election by processor " + this.id);
		// This loop runs for half the number of processors since in each round, number
		// of processors participating in election are half of those originally started.
		for (int i = 1; i <= ringSize / 2; i++) {
			// As the number of rounds increase, ith processors are skipped since they won't
			// be participating.
			send(new Message(id, MessageType.ELECTION, this), getIthPrevious(i));
			send(new Message(id, MessageType.ELECTION, this), getIthNext(i));
			// If after this round, the processor is not leader, it will stop participating.
			if (!isCandidate) {
				break;
			}
		}
		
		// After all rounds, if processor is still a candidate, it means that it is the
		// leader. He will broadcast its leadership to all other participants.
		if (isCandidate) {
			leader = this;
			announceTheLeader();
			broadcastLeadership();
		}

	}

	private void broadcastLeadership() {
		Processor head = this.next;
		do {
			send(new Message(id, MessageType.ELECTED, this), head);
			head = head.next;
		} while (head != this);

	}

	/**
	 * Sends the message to the processor.
	 * 
	 * @param message
	 */
	private void send(Message message, Processor processor) {
		processor.receive(message);
	}

	/**
	 * Processes the received message.
	 * 
	 * If the id in message is lesser than its own, processor sends ACK_FAULT
	 * message to the sender and tells him to quit.
	 * 
	 * If the id in message is greater or equal to its own, processor sends ACK
	 * message and quits.
	 * 
	 * @param message
	 */
	private void receive(Message message) {
		switch (message.getMessageType()) {
		
		case ELECTION:
			if (message.getMessage() < id) {
				// I have a greater value. I would ACK_FAULT to the sender.
				send(new Message(id, MessageType.ACK_FAULT, this), message.getProcessor());
			} else if (message.getMessage() >= id) {
				// I have a lesser value. I would ACK to the sender.
				send(new Message(id, MessageType.ACK, this), message.getProcessor());
			}
			break;
			
		case ACK:
			// Do Nothing, since isCandidate won't change
			break;
			
		case ACK_FAULT:
			isCandidate = false;
			break;
			
		case ELECTED:
			leader = message.getProcessor();
			announceTheLeader();
			break;
		default:
			break;
		}

	}

	private Processor getIthPrevious(int i) {
		Processor p = this;
		for (int j = 0; j < i; j++) {
			p = p.previous;
		}
		return p;
	}

	private Processor getIthNext(int i) {
		Processor p = this;
		for (int j = 0; j < i; j++) {
			p = p.next;
		}
		return p;
	}
	
	private void announceTheLeader() {
		System.out.println("Leader elected " + leader.id + " by processor " + id);
	}

}

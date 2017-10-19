public class Processor implements Runnable {
	int id;
	Processor leader;
	Processor next;

	public Processor(int id) {
		this.id = id;
	}

	/**
	 * Run method for every processor running as a thread.
	 */
	@Override
	public void run() {
		System.out.println("Initiating election by processor " + this.id);
		send(new Message(id, MessageType.ELECTION, this));
	}

	/**
	 * Sends the message to the next processor on clockwise direction.
	 * 
	 * @param message
	 */
	private void send(Message message) {
		next.receive(message);
	}

	/**
	 * Processes the received message. If the id in message is greater than its own,
	 * processor passes it on. 
	 * 
	 * If the id in message is lesser, processor replaces
	 * the id in message and passes the modified message on.
	 *  
	 * If the id in message is
	 * the same as its own, processor knows that it is leader.
	 * 
	 * @param message
	 */
	private void receive(Message message) {
		if (message.getMessageType() == MessageType.ELECTION) {
			if (message.getMessage() < id) {
				// I have a greater value. I would pass my own value in message.
				message.setMessage(id);
				message.setProcessor(this);
			} else if (message.getMessage() == id) {
				// I am the leader. I should stop now.
				send(new Message(id, MessageType.ELECTED, this));
				System.out.println("Leader is " + id);
				return;
			}
			send(message);
		}

		if (message.getMessageType() == MessageType.ELECTED) {
			leader = message.getProcessor();

			if (message.getMessage() == id) {
				return;
			}

			send(message);
		}
	}

}

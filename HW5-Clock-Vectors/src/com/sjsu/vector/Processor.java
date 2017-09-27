package com.sjsu.vector;

import java.util.Observable;
import java.util.Observer;

/**
 * Performs all the processor related tasks
 * 
 * @author Sample
 * @version 1.0
 *
 */
public class Processor implements Observer {
	private Integer id;
	private Buffer messageBuffer;
	private int totalNumberOfProcessors;
	private VectorClock vectorClock; // This is the current vector clock

	/**
	 * Initializes the processor with id, children and unexplored lists. Adds
	 * himself in the observers list.
	 * 
	 * @param id
	 *            of the processor
	 */
	public Processor(int id, int totalProcesors) {
		messageBuffer = new Buffer();
		vectorClock = new VectorClock(3);
		this.id = id;
		this.totalNumberOfProcessors = totalProcesors;
		messageBuffer.addObserver(this);
	}

	public void calculateVectorClocks(Observable observable, Object arg) {
		Message m = this.messageBuffer.getMessage();
		this.vectorClock.updateByOneAt(this.id);

		for (int i = 0; i < totalNumberOfProcessors; i++) {
			this.vectorClock.setCompareToIndex(i);
			if (this.vectorClock.compareTo(m.vectorClock) < 0) {
				this.vectorClock.updateAt(i, m.vectorClock.vectorClock[i]);
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public Buffer getMessageBuffer() {
		return messageBuffer;
	}

	public int getTotalNumberOfProcessors() {
		return totalNumberOfProcessors;
	}

	public VectorClock getVectorClock() {
		return vectorClock;
	}

	/**
	 * Overloaded method, called with single argument This method will add a message
	 * to this processors buffer. Other processors will invoke this method to send a
	 * message to this Processor
	 * 
	 * @param message
	 *            Message to be sent
	 */
	public void sendMessgeToMyBuffer(Message message) {
		this.messageBuffer.setMessage(message);
	}

	/**
	 * Gets called when a node receives a message in it buffer Processes the message
	 * received in the buffer
	 */
	@Override
	public void update(Observable observable, Object arg) {
		calculateVectorClocks(observable, arg);
	}

}

package com.sjsu.vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	private List<VectorClock> store;

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
		store = new ArrayList<VectorClock>();
		addToStore(vectorClock);
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

		addToStore(this.vectorClock);

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
	 * Computes m' of each processor for a give cut vc
	 * 
	 * @param vc
	 * @return
	 */
	public VectorClock getMDash(VectorClock vc) {
		Collections.reverse(store);
		vc.setCompareToIndex(this.id);
		for (VectorClock vectorClock : store) {
			if (vc.compareTo(vectorClock) == 0) {
				return vectorClock;
			}
		}
		return null;
	}

	/**
	 * Gets called when a node receives a message in it buffer Processes the message
	 * received in the buffer
	 */
	@Override
	public void update(Observable observable, Object arg) {
		calculateVectorClocks(observable, arg);
	}

	/**
	 * Add current vector clock to store array
	 * 
	 * @param vc
	 */
	public void addToStore(VectorClock vc) {
		int a = vc.getVectorClock()[0];
		int b = vc.getVectorClock()[1];
		int c = vc.getVectorClock()[2];

		VectorClock nvc = new VectorClock(new int[] { a, b, c });

		this.store.add(nvc);
	}

}

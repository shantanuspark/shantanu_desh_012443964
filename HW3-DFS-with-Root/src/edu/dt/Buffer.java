package edu.dt;

import java.util.Observable;

/**
 * Observable Buffer of each node
 * @author Shantanu Deshmukh
 * @version 1.0
 */
public class Buffer extends Observable {
    private Message message;
    private Processor fromProcessor;
    
    /**
     * Getter for the FromProcessor 
     * @return Processor
     */
    public Processor getFromProcessor() {
		return fromProcessor;
	}

    /**
     * Setter for the FromProcessor
     * @param fromProcessor
     */
	public void setFromProcessor(Processor fromProcessor) {
		this.fromProcessor = fromProcessor;
	}

	/**
     * 
     * Creates empty buffer
     */
    public Buffer(){
    	this.message = null;
    }

    /**
     * Creates buffer with message
     * @param message Message to be stored
     */
    public Buffer(Message message) {
        this.message = message;
    }
    
    /**
     * @return Message from the buffer
     */
    public Message  getMessage() {
        return message;
    }

    /**
     * Sets the message and notifies the observers with the sender node's information
     * @param message		Message to be stored in the buffer
     * @param fromProcessor Node who sent the message
     */
    public void setMessage(Message message, Processor fromProcessor ) {
        this.message = message;
        setFromProcessor(fromProcessor);
        setChanged();
        notifyObservers();
    }
}


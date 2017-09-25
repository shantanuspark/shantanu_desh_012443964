package edu.dt;


/**
 * Messages that can be passed
 * @author Shantanu Deshmukh
 */
public enum  Message {
	/**
	 * Indicates node already has the message
	 */
    ALREADY,
    /**
     * Indicates that the sender node wants the receiver node to be its parent
     */
    PARENT, 
    /**
     * The informative message that is to be circulated in the graph
     */
    M;
}

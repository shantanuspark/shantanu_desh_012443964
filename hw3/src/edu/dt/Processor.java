package edu.dt;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Performs all the processor related tasks
 * @author Shantanu Deshmukh
 * @version 1.0
 *
 */
public class Processor implements Observer {
	Processor parent;
    Buffer messageBuffer ;
    Integer id ;
    List<Processor> children ;
    List<Processor> unexplored ;

    /**
     * Initializes the processor with id, children and unexplored lists. Adds himself in the observers list.
     * @param id of the processor
     */
    public Processor(int id) {
        messageBuffer = new Buffer();
        this.id = id; 
        children = new ArrayList<>();
        unexplored = new ArrayList<>();
        messageBuffer.addObserver(this);
    }
    
    /**
     * Sets unexplored list of the processor
     * @param children list of child nodes
     */
    public void setUnexplored(List<Processor> children) {
    	this.unexplored = children;
    }

    /**
     * Removes node from the unexplored list
     * @param processor Node to be removed from the unexplored list
     */
    private void removeFromUnexplored(Processor processor){
    	unexplored.remove(processor);
    }

    /**
     * This method will add a message to this processors buffer.
     * Other processors will invoke this method to send a message to this Processor
     * @param message		Message to be sent
     * @param fromProcessor Node who sent the message
     */
    public void sendMessgeToMyBuffer(Message message, Processor fromProcessor){
        messageBuffer.setMessage(message, fromProcessor);
    }
    
    /**
     * Overloaded method, called with single argument
     * This method will add a message to this processors buffer.
     * Other processors will invoke this method to send a message to this Processor
     * @param message Message to be sent
     */
    public void sendMessgeToMyBuffer(Message message){
        messageBuffer.setMessage(message, this);
    }


    /**
     * Gets called when a node receives a message in it buffer
     * Processes the message received in the buffer, accordingly explores the tree further.
     */
    public void update(Observable observable, Object arg) {
    	Processor fromProcessor = messageBuffer.getFromProcessor();
    	if(observable == messageBuffer) { 
    		switch (messageBuffer.getMessage()) {
    			case M:
    				if(parent == null) {
    	    			parent = fromProcessor;
    	    			removeFromUnexplored(fromProcessor);
    	    			explore();
    	    		} else {
    	    			removeFromUnexplored(fromProcessor);
    	    			fromProcessor.sendMessgeToMyBuffer(Message.ALREADY);
    	    		}
    				break;
    			case ALREADY:
    				explore();
    				break;
    			case PARENT:
    				children.add(fromProcessor);
        			explore();
    				break;
    		}
    	}

    }

    /**
     * Explores the tree depending on the unexplored list
     */
    public void explore(){
    	if(!unexplored.isEmpty()) {
    		Processor pj = unexplored.get(0);
    		removeFromUnexplored(pj);
    		pj.sendMessgeToMyBuffer(Message.M, this);
    	} else {
    		if(parent != this) {
    			parent.sendMessgeToMyBuffer(Message.PARENT, this);
    		}
    	}
    }

}

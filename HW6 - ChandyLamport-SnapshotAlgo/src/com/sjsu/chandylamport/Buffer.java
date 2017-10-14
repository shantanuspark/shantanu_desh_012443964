package com.sjsu.chandylamport;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Observable Buffer of each node
 *
 * @author Sample
 * @version 1.0
 */
//A channel should have a buffer associated with it.
public class Buffer extends Observable {
    String label;
    private List<Message> messages;

    /**
     * Creates empty buffer
     */
    public Buffer(String label) {
        messages = new CopyOnWriteArrayList<Message>();
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /**
     * @return Message from the buffer
     */
    public Message getMessage(int index) {
        return messages.get(index);
    }

    /**
     * Sets the message and notifies the observers with the sender node's information
     *
     * @param message Message to be stored in the buffer
     */
    public void saveMessage(Message message) {
        this.messages.add(message);
        setChanged();
        notifyObservers();
    }

    int getTotalMessageCount() {
        return messages.size();
    }
}


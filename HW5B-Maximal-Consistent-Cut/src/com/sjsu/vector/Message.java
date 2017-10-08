package com.sjsu.vector;

public class Message {
	MessageType messageType;
	VectorClock vectorClock;

	public Message(MessageType messageType, VectorClock vectorClock) {
		this.messageType = messageType;
		this.vectorClock = vectorClock;
	}

}

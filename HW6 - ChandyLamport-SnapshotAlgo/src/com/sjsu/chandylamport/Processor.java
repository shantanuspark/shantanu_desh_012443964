package com.sjsu.chandylamport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Performs all the processor related tasks
 *
 * @author Sample
 * @version 1.0
 */

public class Processor implements Observer {

	List<Buffer> inChannels = new ArrayList<>();

	List<Buffer> outChannels = null;

	Map<Buffer, List<Message>> channelState = null;

	Map<Processor, Integer> processorMarkerCount = null;
	
	Integer id;
	boolean isInitiator = false;

	/**
	 * 
	 * @param id
	 * @param inChannels
	 * @param outChannels
	 */
	public Processor(int id, List<Buffer> inChannels, List<Buffer> outChannels) {
		this.id = id;
		this.inChannels = inChannels;
		this.outChannels = outChannels;
		inChannels.forEach(inChannel -> inChannel.addObserver(this));
		
		processorMarkerCount = new HashMap<Processor, Integer>();
		channelState = new HashMap<Buffer, List<Message>>();
	}

	/**
	 * This is a dummy implementation which will record current state of this
	 * processor
	 */
	public void recordMyCurrentState() {
		System.out.println("Processor "+this.id+" : Recording my registers...");
		System.out.println("Processor "+this.id+" : Recording my program counters...");
		System.out.println("Processor "+this.id+" : Recording my local variables...");
	}

	/**
	 * This method marks the channel as empty
	 * 
	 * @param channel
	 */
	public void recordChannelAsEmpty(Buffer channel) {

		channelState.put(channel, Collections.emptyList());

	}

	/**
	 * Starts threaded recording on given channel
	 * @param channel
	 */
	public void recordChannel(Buffer channel) {

		Recorder channelRecorder = new Recorder();
		channelRecorder.setChannel(channel);
		channelRecorder.setChannelState(channelState);
		System.out.println("Recording on incoming channel "+channel.label+" started...");
		//Starts threaded recording on the given channel
		channelRecorder.start();

	}

	/**
	 * Overloaded method, called with single argument This method will add a message
	 * to this processors buffer. Other processors will invoke this method to send a
	 * message to this Processor
	 *
	 * @param message
	 *            Message to be sent
	 */
	public void sendMessgeTo(Message message, Buffer channel) {
		channel.saveMessage(message);

	}

	/**
	 * Checks if the processor has received a marker message before
	 * @return true if this is the first marker false otherwise
	 */
	public boolean isFirstMarker() {
		if(processorMarkerCount.containsKey(this) && processorMarkerCount.get(this)>0)
			return false;
		return true;
	}

	/**
	 * 
	 * @param observable fromChannel of the incoming message
	 */
	public void update(Observable observable, Object arg) {
		Buffer buffer = (Buffer) observable;
		Message message = buffer.getMessage(buffer.getTotalMessageCount() - 1);
		if (message.getMessageType().equals(MessageType.MARKER)) {
			Buffer fromChannel = (Buffer) observable;
			if (isFirstMarker()) {
				recordMyCurrentState();
				recordChannelAsEmpty(fromChannel);
				processorMarkerCount.put(this, processorMarkerCount.get(this)==null?1:processorMarkerCount.get(this) + 1);
				
				// From the other incoming Channels (excluding the fromChannel which has sent the marker) start recording messages
				for (Buffer inChannel : inChannels) {
					if (inChannel.equals(fromChannel))
						continue;
					recordChannel(inChannel);
				}

				// Sending marker messages to each of the out channels
				for (Buffer outChannel : outChannels) {
					sendMessgeTo(new Message(MessageType.MARKER), outChannel);
				}
			} else {
				System.out.println("Duplicate Marker Message received for Processor"+this.id+", stopping recording");
			}

		} else {
			if (message.getMessageType().equals(MessageType.ALGORITHM)) {
				System.out.println("Processing Algorithm message....");
			} 
		}

	}
	
	/**
	 * Initiates snapshot from this processor
	 */
	public void initiateSnapShot() {
		// Recording own state before sending marker messages to other processors
		recordMyCurrentState();
		// Adding dummy marker counts to the initiator node so as to avoid re-recording of its states
		processorMarkerCount.put(this, 1);
		
		// Start recording messages on all incoming channels
		inChannels.forEach(inChannel -> recordChannel(inChannel));
		
		// Sending marker message on each outgoing channel
		outChannels.forEach(outChannel -> sendMessgeTo(new Message(MessageType.MARKER), outChannel));
	}

}

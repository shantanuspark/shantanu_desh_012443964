package com.sjsu.chandylamport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Recorder extends Thread {

	private Buffer channel;
	private Map<Buffer, List<Message>> channelState;
	
	public Buffer getChannel() {
		return channel;
	}

	public synchronized void setChannel(Buffer channel) {
		this.channel = channel;
	}

	public Map<Buffer, List<Message>> getChannelState() {
		return channelState;
	}

	public synchronized void setChannelState(Map<Buffer, List<Message>> channelState) {
		this.channelState = channelState;
	}



	@Override
	public void run() {
		int lastIdx = channel.getTotalMessageCount() - 1;
		List<Message> recordedMessagesSinceMarker = new ArrayList<>();
		while(lastIdx >= 0) {
			Message message = channel.getMessage(lastIdx);
			if(MessageType.MARKER.equals(message.getMessageType()))
				break;
			recordedMessagesSinceMarker.add(message);
			lastIdx--;
		}
		channelState.put(channel, recordedMessagesSinceMarker);
	}

}

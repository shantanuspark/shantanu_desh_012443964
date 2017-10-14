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

		while(true) {
			if(channel.getTotalMessageCount()-1 > lastIdx) {
				Message m = channel.getMessage(channel.getTotalMessageCount()-1);
				lastIdx = channel.getTotalMessageCount()-1;
				recordedMessagesSinceMarker.add(m);
				if(Thread.interrupted()) {
					channelState.put(channel, recordedMessagesSinceMarker);
				}
			}
		}
	}

}

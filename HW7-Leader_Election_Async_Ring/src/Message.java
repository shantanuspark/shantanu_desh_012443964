
public class Message {
	private int message;
	private MessageType messageType;
	private Processor processor;

	public Message(int message, MessageType messageType, Processor processor) {
		super();
		this.message = message;
		this.messageType = messageType;
		this.processor = processor;
	}

	public int getMessage() {
		return message;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public Processor getProcessor() {
		return processor;
	}

}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ring {
	private Processor head;

	/**
	 * Adds processor to the ring.
	 * 
	 * @param processor
	 */
	public void add(Processor processor) {
		if (head == null) {
			head = processor;
			processor.next = head;
			processor.previous = head;
		} else {
			append(processor);
		}
	}

	/**
	 * Appends processor to the ring.
	 * 
	 * @param processor
	 */
	private void append(Processor processor) {
		Processor current = head;
		while (current.next != head) {
			current = current.next;
		}
		current.next = processor;
		processor.previous = current;
		processor.next = head;
		head.previous = processor;
	}

	/**
	 * Prints the ring.
	 */
	public void print() {
		Processor current = head;
		do {
			System.out.print(current.id);
			current = current.next;
			System.out.print("<-->");
		} while (current != head);
	}

	/**
	 * Starts the election process by executing all the processors as individual
	 * threads using executor service.
	 */
	public void startElection() {
		ExecutorService executor = Executors.newFixedThreadPool(50);
		System.out.println("\n\nInitiaing election process...");
		Processor current = head;

		do {
			executor.execute(current);
			current = current.next;
		} while (current != head);
	}
}

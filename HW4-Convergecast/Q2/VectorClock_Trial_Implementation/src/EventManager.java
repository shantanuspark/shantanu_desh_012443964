
public class EventManager {
	public void sendEvent(Processor source, Processor destination) {
		VectorClock sourceVector = source.getVectorClock();
		VectorClock destinationVector = destination.getVectorClock();
		
		if(sourceVector.getP0() > destinationVector.getP0())
			destinationVector.setP0(sourceVector.getP0());
		if(sourceVector.getP1() > destinationVector.getP1())
			destinationVector.setP1(sourceVector.getP1());
		if(sourceVector.getP2() > destinationVector.getP2())
			destinationVector.setP2(sourceVector.getP2());
		
		destination.incrementClock();

	}

	public void computationEvent(Processor current) {
		current.incrementClock();
	}
}

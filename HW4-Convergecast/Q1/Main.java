public class Main {
	
    private static Processor p0, p1, p2, p3, p4, p5, p6, p7, p8;

    public static void main(String[] args) {
        initProcessors();
        initGraph();
		//Getting max value
        System.out.println("Maximum value is "+p0.findMax());
		//Getting concatenated value
        System.out.println("Concanated value is "+p0.findConcate());

    }
	//Initialize processors
    private static void initProcessors() {
        p0 = new Processor(2);
        p1 = new Processor(7);
        p2 = new Processor(5);
        p3 = new Processor(2);
        p4 = new Processor(6);
        p5 = new Processor(9);
        p6 = new Processor(5);
        p7 = new Processor(11);
        p8 = new Processor(4);
    }
	
	//Create graph as per the diagram
    private static void initGraph() {
        p0.children.add(p1);
        p0.children.add(p2);
        
        p1.children.add(p3);
        p1.children.add(p4);
        
        p2.children.add(p5);
        
        p4.children.add(p6);
        p4.children.add(p7);
        
        p5.children.add(p8);
    }

}
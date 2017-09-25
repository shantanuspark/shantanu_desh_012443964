package edu.dt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Initializes the Graph and starts exploring from the user defined root node
 * @author Shantanu Deshmukh
 * @version 1.0
 * 
 */
public class Main {
    static Map <Processor, List<Processor>> graph;

    /**
     * Initializes graph on creating object of the Main() class
     */
    public  Main(){
        init();
    }

    /**
     * Sets parent of the root node and starts exploring the graph from the root node
     * @param args accepts the id of the root node
     */
    public static void main ( String args[]){
        Main m = new Main();
        Processor root = null;
        try {
	        for(Processor processor:graph.keySet()) {
	        	if(processor.id == Integer.parseInt(args[0])) {
	        		root = processor;
	        		break;
	        	}
	        }
	        System.out.println("####### Initial Graph #######");
	        m.plotGraph();
	        root.parent = root;
	        root.explore(); 
	        for(Processor processor:graph.keySet()) {
	        	graph.put(processor, processor.children);
	        }
	        System.out.println("####### Final Graph #######");
	        m.plotGraph();
        } catch(ArrayIndexOutOfBoundsException e) {
        	System.out.println("Please enter the root node id as a command line argument..");
        }

    }

    /**
     * Creates the Graph with defined sets of nodes and childrens
     */
    public void init(){
    	Processor p0 = new Processor(0);
    	Processor p1 = new Processor(1);
    	Processor p2 = new Processor(2);
    	Processor p3 = new Processor(3);
    	Processor p4 = new Processor(4);
    	Processor p5 = new Processor(5);
    	
    	final List<Processor> p0list = new ArrayList<>();
    	p0list.add(p1);
    	p0list.add(p2);
    	p0list.add(p3);
    	p0.setUnexplored(p0list);  
    	
    	final List<Processor> p1List = new ArrayList<>();
    	p1List.add(p0);
    	p1List.add(p2);
    	p1List.add(p4);
    	p1.setUnexplored(p1List);
    	
    	final List<Processor> p2List = new ArrayList<>();
    	p2List.add(p0);
    	p2List.add(p1);
    	p2List.add(p5);
    	p2.setUnexplored(p2List);
    	
    	final List<Processor> p3List = new ArrayList<>();
    	p3List.add(p0);
    	p3.setUnexplored(p3List);
    	
    	final List<Processor> p4List = new ArrayList<>();
    	p4List.add(p1);
    	p4List.add(p5);
    	p4.setUnexplored(p4List);
    	
    	final List<Processor> p5List = new ArrayList<>();
    	p5List.add(p2);
    	p5List.add(p4);
    	p5.setUnexplored(p5List);
    	
    	graph = new HashMap<>();
    	graph.put(p0, p0list);
    	graph.put(p1, p1List);
    	graph.put(p2, p2List);
    	graph.put(p3, p3List);
    	graph.put(p4, p4List);
    	graph.put(p5, p5List);
    	
    }
    
    /**
     * Prints each node in the graph followed by its childrens like
     * N0 - N1,N2,N3	where N0 is the parent and N1,N2,N3 are its childrens
     */
    public void plotGraph() {
    	for(Processor p : graph.keySet()) {
    		System.out.print("Processor P"+p.id+"->");
    		for(Processor temp : graph.get(p)) {
    			System.out.print(" P"+temp.id);
    		}
    		System.out.println("");
    	}
    }
}

package com.sjsu.chandylamport;

/**
 * This is the simulation of a main algorithm that will run on processors P1, P2, P3
 * This could be a banking application, payroll application or any other distributed application
 */
public class Algorithm {

    /**
     * The processors which will participate in a distributed application
     */
    Processor processor1, processor2, processor3;

    public Algorithm(Processor processor1, Processor processor2, Processor processor3) {
        //TODO: Homeork: Initialize processors so that they represent the topology of 3 processor system
    }



    public void executionPlan1 (){
        compute(processor1);
        compute(processor1);

        compute(processor1);
        compute(processor1);
        compute(processor1);
        compute(processor1);
        compute(processor1);
/**
 * TODO: Homework: Implement send message from processor1 to different processors. Add a time gap betweeen two different
 *                send events. Add computation events between two diferent sends.
 *      [Hint: Create a loop that kills time, sleep , wait on somevalue etc..]
 *
 */
        processor1.sendMessgeTo(null,null);

        processor1.sendMessgeTo(null,null);

        processor1.sendMessgeTo(null,null);

    }

    // Write hard coded execution plan for processors
    public void executionPlanP1() {


    }

    // Write hard coded execution plan for processors
    public void executionPlanP2() {


    }

    /**
     * A dummy computation.
     * @param p
     */
    public void compute(Processor p) {
        System.out.println("Doing some computation on " + p.getClass().getSimpleName());
    }

    /**
     *
     * @param to processor to which message is sent
     * @param channel the incoming channel on the to processor that will receive this message
     */
    public void send(Processor to, Buffer channel) {
        to.sendMessgeTo(null, channel); // ALGORITHM
    }

}

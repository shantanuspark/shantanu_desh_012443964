package com.sjsu.chandylamport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tphadke on 9/27/17.
 */
public class Main {

    public static void main(String args[]) {

        //Channels from P3 to P1 and P2 to P1
        Buffer channelP31 = new Buffer("P31");
        Buffer channelP21 = new Buffer("P21");

        //Channels from P3 to P2 and P1 to P2
        Buffer channelP32 = new Buffer("P32"); 
        Buffer channelP12 = new Buffer("P12"); 

        //Channels from P2 to P3 and P1 to P3
        Buffer channelP23 = new Buffer("P23");
        Buffer channelP13 = new Buffer("P13");


        List<Buffer> inChannelsP1 = new ArrayList<>();
        inChannelsP1.add(channelP31);
        inChannelsP1.add(channelP21);
        List<Buffer> outChannelsP1 = new ArrayList<>();
        outChannelsP1.add(channelP13);
        outChannelsP1.add(channelP12);
        Processor processor1 = new Processor(1, inChannelsP1, outChannelsP1); //Only observes in channels.

        List<Buffer> inChannelsP2 = new ArrayList<>();
        inChannelsP2.add(channelP32);
        inChannelsP2.add(channelP12);
        List<Buffer> outChannelsP2 = new ArrayList<>();
        outChannelsP2.add(channelP21);
        outChannelsP2.add(channelP23);
        Processor processor2 = new Processor(2, inChannelsP2, outChannelsP2); //Only observes in channels.

        List<Buffer> inChannelsP3 = new ArrayList<>();
        inChannelsP3.add(channelP13);
        inChannelsP3.add(channelP23);
        List<Buffer> outChannelsP3 = new ArrayList<>();
        outChannelsP3.add(channelP31);
        outChannelsP3.add(channelP32);
        Processor processor3 = new Processor(3, inChannelsP3, outChannelsP3); //Only observes in channels.

        //Initiating Snapshot on processor 1
        processor1.initiateSnapShot();

    }


}

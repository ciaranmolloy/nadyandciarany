package com.nady;

import com.nady.controller.NadyController;
import com.nady.server.NadyServer;

public class App {
	
    public static void main( String[] args ) {
    	NadyServer.runServer(new NadyController());
    }
}

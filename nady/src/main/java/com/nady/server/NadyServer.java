package com.nady.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.nady.controller.NadyController;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

public class NadyServer {

	public static void runServer(final NadyController controller) {
    	HttpServer server = null;
        try {
			server = HttpServer.create(new InetSocketAddress(8080), 0);
		    HttpContext context = server.createContext("/", controller);
		    server.setExecutor(null);
			server.start();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}

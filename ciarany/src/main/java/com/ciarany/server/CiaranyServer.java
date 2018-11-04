package com.ciarany.server;

import static java.net.HttpURLConnection.HTTP_OK;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class CiaranyServer {

	public static void runServer() {
    	HttpServer server = null;
        try {
			server = HttpServer.create(new InetSocketAddress(8080), 0);
		    HttpContext context = server.createContext("/");
		    context.setHandler(new HttpHandler() {
		        public void handle(HttpExchange exchange) throws IOException {
					final String response = "<html>\r\n" + 
							"<body>\r\n" + 
							"\r\n" + 
							"<button type=\"button\" onclick=\"alert('Hello world!')\">Click Me!</button>\r\n" + 
							" \r\n" + 
							"</body>\r\n" + 
							"</html>";
					exchange.sendResponseHeaders(HTTP_OK, response.length());
			        OutputStream os = exchange.getResponseBody();
			        os.write(response.getBytes());
			        os.close();
		        }
		    });
		    server.setExecutor(null);
			server.start();
			
			
			
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}

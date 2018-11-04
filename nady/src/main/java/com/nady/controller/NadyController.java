package com.nady.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nady.controller.uri.NadyURI;
import com.nady.service.NadyService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import static java.net.HttpURLConnection.HTTP_OK;

import static com.nady.controller.uri.NadyURI.HI;
import static com.nady.controller.uri.NadyURI.OH;

public class NadyController implements HttpHandler {
	
	private NadyService service = new NadyService();
	private byte [] response;

	public void handle(final HttpExchange exchange) throws IOException {
        mapRequest(exchange);
        returnResponse(exchange);
	}
	
	private void mapRequest(final HttpExchange exchange) throws IOException {
		switch (exchange.getRequestURI().toString()) {
        	case HI : getHello(); break;
        	case OH : postOh(exchange.getRequestBody()); break;
        	default : response = "Sinéad is gay.".getBytes();
        }
	}

	private void returnResponse(final HttpExchange exchange) throws IOException {
		exchange.sendResponseHeaders(HTTP_OK, response.length);
        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
	}
	
	private byte[] readBody(final InputStream inputStream) throws IOException {
		byte[] body = new byte[10];
		int data = inputStream.read(body);
		while(data != -1) {

		  data = inputStream.read(body);
		}
		return body;
	}
	
	private void postOh(final InputStream inputStream) throws IOException {
		response = service.handleOh(readBody(inputStream));
		inputStream.close();
	}

	private void getHello() {
		response = "Hello".getBytes();
	}
}

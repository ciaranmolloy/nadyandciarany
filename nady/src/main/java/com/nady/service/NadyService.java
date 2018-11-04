package com.nady.service;

public class NadyService {

	public byte[] handleOh(byte[] readBody) {
		return new String(readBody).trim().getBytes();
	}
}

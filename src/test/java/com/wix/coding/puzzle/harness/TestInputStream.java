package com.wix.coding.puzzle.harness;

import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestInputStream extends InputStream {

	private BlockingQueue<byte[]> byteArraysQueue = new LinkedBlockingQueue<>();

	public void write(String input) {
		byteArraysQueue.offer((input+"\r\n").getBytes());
	}

	@Override
	public int read(byte[] b, int off, int len) {
		byte[] take = new byte[0];
		try {
			take = byteArraysQueue.take();
			System.arraycopy(take, 0, b, 0, take.length);
		}
		catch (InterruptedException e) {
		}

		return take.length;
	}

	@Override
	public int read() {
		return -1;
	}
}

package com.wix.coding.puzzle.harness;

import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.junit.Assert;

public class TestOutputStream extends OutputStream {

	private final StringBuffer buffer = new StringBuffer();

	@Override
	public void write(int b) {
		buffer.append((char) b);
	}

	public void verifyContent(String content) {
		String currentContent = buffer.toString().trim();
		String trimmedContent = content.trim();

		Assert.assertTrue(
				"Expected next String : \r\n [\r\n" + trimmedContent + "\r\n] but was \r\n [\r\n" + currentContent + "\r\n]",
				currentContent.equals(trimmedContent)
		);
	}

	public String stapshot() {
		return buffer.toString();
	}

	public void verifyNotEmpty() {
		Assert.assertTrue(buffer.length() > 0);
	}

	public void reset() {
		buffer.delete(0, buffer.length());
	}
}

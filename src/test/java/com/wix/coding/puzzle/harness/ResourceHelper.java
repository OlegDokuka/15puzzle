package com.wix.coding.puzzle.harness;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public final class ResourceHelper {
	private ResourceHelper() { }

	public static String loadSystemResourceAsString(String filename) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(filename)))) {
			return br.lines().parallel().collect(Collectors.joining("\n"));
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

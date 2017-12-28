package com.wix.coding.puzzle.launcher.adapter.io.harness;

public class IOBoardViewParser {

	public static int[][] parse(String output) {
		int start = output.indexOf("-");
		int index = output.lastIndexOf("-\n");

		String content = output.substring(start, index + 1);
		String[] lines = content.split("\n");
		int size = lines.length;
		int matrixSize = ((lines.length - 1) / 4);
		int[][] matrix = new int[matrixSize][];

		for (int i = 2, y = 0; i < size; i += 4, y++) {
			String[] split = lines[i].split("\\|");
			int[] ints = new int[matrixSize];
			int cells = split.length;

			for (int j = 1, x = 0; j < cells; j++, x++) {
				String value = split[j].trim();

				if (value.isEmpty()) {
					ints[x] = 0;
				}
				else {
					ints[x] = Integer.parseInt(value);
				}
			}

			matrix[y] = ints;
		}

		return matrix;
	}
}

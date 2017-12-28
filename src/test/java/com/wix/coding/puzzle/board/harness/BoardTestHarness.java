package com.wix.coding.puzzle.board.harness;

import java.util.Set;
import java.util.TreeSet;

import com.wix.coding.puzzle.board.Board;
import org.junit.Assert;

public final class BoardTestHarness {
	private BoardTestHarness() { }


	public static int countDifferences(int[][] boardL, int[][] boardR) {
		int diff = 0;
		int length = boardR.length;

		for (int x = 0; x < length; x++) {
			for (int y = 0; y < length; y++) {
				if (boardL[x][y] != boardR[x][y]) {
					diff++;
				}
			}
		}
		return diff;
	}

	public static void verifyMatrixFormedCorrectly(Board board, int[][] matrix) {
		Set<Integer> plain = new TreeSet<>();
		int length = matrix.length;

		for (int x = 0; x < length; x++) {
			for (int y = 0; y < length; y++) {
				plain.add(matrix[x][y]);
			}
		}

		int elementsCountOnBoard = board.size() * board.size();
		int maxValueOnPuzzle = elementsCountOnBoard - 1;

		Assert.assertTrue(plain.size() == elementsCountOnBoard);
		Assert.assertTrue(plain.contains(0));
		Assert.assertTrue(plain.contains(maxValueOnPuzzle));
	}
}

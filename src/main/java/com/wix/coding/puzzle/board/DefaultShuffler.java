package com.wix.coding.puzzle.board;

import java.util.Random;

public class DefaultShuffler implements Shuffler {

	public void shuffle(int[] ints) {
		Random rnd = new Random();

		// Shuffle array
		for (int i = ints.length; i > 1; i--) {
			swap(ints, i - 1, rnd.nextInt(i));
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}

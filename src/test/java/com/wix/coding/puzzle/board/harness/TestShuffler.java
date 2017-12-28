package com.wix.coding.puzzle.board.harness;

import com.wix.coding.puzzle.board.Shuffler;

public class TestShuffler implements Shuffler {

	private final int[] expectation;

	public TestShuffler() {
		this(null);
	}

	public TestShuffler(int[] expectation) {
		this.expectation = expectation;
	}

	@Override
	public void shuffle(int[] ints) {
		if (expectation != null) {
			System.arraycopy(expectation, 0, ints, 0, expectation.length);
		}
	}
}

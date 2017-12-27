package com.wix.coding.puzzle;

import org.junit.Assert;

public class TestPresenter implements Presenter {

	private Set<Puzzle> puzzles;

	public void verifyGameBoardIsRendered() {
		Assert.assertTrue(puzzles.size() > 0);
	}
}

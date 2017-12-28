package com.wix.coding.puzzle.board.adapter.io.view.harness;

import com.wix.coding.puzzle.core.presenter.Model;

public class TestBoardModel implements Model<int[][]> {

	public final int[][] ints;

	public TestBoardModel(int[][] ints) {
		this.ints = ints;

	}

	@Override
	public int[][] content() {
		return ints;
	}

}

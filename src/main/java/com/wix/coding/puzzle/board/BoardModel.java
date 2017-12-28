package com.wix.coding.puzzle.board;

import com.wix.coding.puzzle.core.presenter.Model;

public class BoardModel implements Model<int[][]> {

	private final int[][] content;

	BoardModel(int[][] content) {
		this.content = content;
	}

	@Override
	public int[][] content() {
		return content;
	}
}

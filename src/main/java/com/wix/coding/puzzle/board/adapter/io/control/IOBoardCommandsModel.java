package com.wix.coding.puzzle.board.adapter.io.control;

import java.util.Arrays;
import java.util.List;

import com.wix.coding.puzzle.core.presenter.Model;

public class IOBoardCommandsModel implements Model<List<String>> {
	static final String[] commands = new String[] {
			"Use 1 to move element from left to right empty space",
			"Use 2 to move element from right to left empty space",
			"Use 3 to move element from top to bottom empty space",
			"Use 4 to move element from bottom to top empty space"
	};

	@Override
	public List<String> content() {
		return Arrays.asList(commands);
	}
}

package com.wix.coding.puzzle.launcher.adapter.io.control;

import java.util.Arrays;
import java.util.List;

import com.wix.coding.puzzle.core.presenter.Model;

public class IOGameCommandsModel implements Model<List<String>> {
	private static final String[] commands = new String[] {
			"Use r to restart the game",
			"Use q to quit the game"
	};

	@Override
	public List<String> content() {
		return Arrays.asList(commands);
	}
}

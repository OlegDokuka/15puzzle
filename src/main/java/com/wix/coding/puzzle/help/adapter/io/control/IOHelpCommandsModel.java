package com.wix.coding.puzzle.help.adapter.io.control;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.wix.coding.puzzle.core.control.CommandsInvoker;
import com.wix.coding.puzzle.core.presenter.Model;
import com.wix.coding.puzzle.help.Help;

public class IOHelpCommandsModel implements Model<List<String>> {
	static final String[] commands = new String[] {
			"Use h to show help menu"
	};

	@Override
	public List<String> content() {
		return Arrays.asList(commands);
	}
}

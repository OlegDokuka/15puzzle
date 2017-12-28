package com.wix.coding.puzzle.help.adapter.io.control;

import java.util.List;

import com.wix.coding.puzzle.core.control.CommandsInvoker;
import com.wix.coding.puzzle.core.presenter.Model;
import com.wix.coding.puzzle.help.Help;

public class IOHelpCommandsInvoker implements CommandsInvoker {

	private final Help help;

	public IOHelpCommandsInvoker(Help help) {
		this.help = help;
	}

	@Override
	public boolean support(Object command) {
		return command.toString()
		              .equalsIgnoreCase("h");
	}

	@Override
	public void invoke(Object command) {
		if(command.toString()
		          .equalsIgnoreCase("h")) {
			help.show();
		}
	}

	@Override
	public Model<List<String>> commands() {
		return new IOHelpCommandsModel();
	}
}

package com.wix.coding.puzzle.launcher.adapter.io.control;

import java.util.Collection;

import com.wix.coding.puzzle.launcher.Launcher;
import com.wix.coding.puzzle.core.control.CommandsInvoker;
import com.wix.coding.puzzle.core.presenter.Model;

public class IOGameCommandsInvoker implements CommandsInvoker {

	private final Launcher launcher;

	public IOGameCommandsInvoker(Launcher launcher) {
		this.launcher = launcher;
	}

	@Override
	public boolean support(Object command) {
		String input = command.toString();

		switch (input) {
			case "q":
			case "r":
				return true;
		}

		return false;
	}

	@Override
	public void invoke(Object command) {
		String input = command.toString();

		switch (input) {
			case "q":
				System.exit(1);
				break;
			case "r":
				launcher.relaunch();
				break;
		}
	}

	@Override
	public Model<? extends Collection<String>> commands() {
		return new IOGameCommandsModel();
	}
}

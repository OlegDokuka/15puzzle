package com.wix.coding.puzzle.core.control;

import java.util.Collection;

import com.wix.coding.puzzle.core.presenter.Model;

public interface CommandsInvoker {

	boolean support(Object command);

	void invoke(Object command);

	Model<? extends Collection<String>> commands();
}

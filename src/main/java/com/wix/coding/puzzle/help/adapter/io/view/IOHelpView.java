package com.wix.coding.puzzle.help.adapter.io.view;

import java.io.PrintStream;
import java.util.Collection;

import com.wix.coding.puzzle.core.presenter.Model;
import com.wix.coding.puzzle.help.view.HelpView;

public class IOHelpView implements HelpView {

	private final PrintStream out;

	public IOHelpView(PrintStream out) {
		this.out = out;
	}

	@Override
	public <M extends Model<? extends Collection<String>>> void render(M model) {
		if (model == null || model.content() == null || model.content().isEmpty()) {
			return;
		}

		out.println("Please, use following game-play commands :");

		for (String command : model.content()) {
			out.println("  * " + command);
		}
	}
}

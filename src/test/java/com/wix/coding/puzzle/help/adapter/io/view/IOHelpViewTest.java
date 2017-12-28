package com.wix.coding.puzzle.help.adapter.io.view;

import java.io.PrintStream;
import java.util.Collections;

import com.wix.coding.puzzle.harness.TestOutputStream;
import com.wix.coding.puzzle.help.ComposedHelpModel;
import org.junit.Test;

public class IOHelpViewTest {

	@Test
	public void shouldRenderHelpCommands() {
		TestOutputStream out = new TestOutputStream();
		IOHelpView view = new IOHelpView(new PrintStream(out, true));

		view.render(new ComposedHelpModel(() -> Collections.singleton("Hello worlds")));
		out.verifyContent(
				"Please, use following game-play commands :\n" +
				"  * Hello worlds"
		);
	}

	@Test
	public void shouldNotRenderHelpCommandsIfTheyAreEmpty() {
		TestOutputStream out = new TestOutputStream();
		IOHelpView view = new IOHelpView(new PrintStream(out, true));

		view.render(new ComposedHelpModel(() -> Collections.emptyList()));
		out.verifyContent("");
	}
}

package com.wix.coding.puzzle.board.adapter.io.view;

import java.io.PrintStream;

import com.wix.coding.puzzle.core.presenter.View;
import com.wix.coding.puzzle.harness.ResourceHelper;
import com.wix.coding.puzzle.harness.TestOutputStream;
import org.junit.Test;

public class IOWinViewTest {

	@Test
	public void shouldPrintWinMessage() {
		TestOutputStream out = new TestOutputStream();
		View view = new IOWinView(
				new PrintStream(out, true), false
		);

		view.render(null);
		out.verifyContent(ResourceHelper.loadSystemResourceAsString("win.txt"));
	}
}

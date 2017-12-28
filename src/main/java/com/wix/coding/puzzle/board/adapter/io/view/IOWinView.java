package com.wix.coding.puzzle.board.adapter.io.view;

import java.io.PrintStream;

import com.wix.coding.puzzle.board.view.WinView;
import com.wix.coding.puzzle.core.presenter.Model;

public class IOWinView implements WinView {

	private final PrintStream out;
	private final boolean     clean;

	public IOWinView(PrintStream out) {
		this(out, true);
	}

	public IOWinView(PrintStream out, boolean clean) {
		this.out = out;
		this.clean = clean;
	}

	@Override
	public <M extends Model<? extends Void>> void render(M model) {
		if (clean) {
			clean();
		}

		out.println("----------------------");
		out.println("|------YOU WIN!------|");
		out.println("----------------------");
	}

	private void clean() {
		out.print("\033[H\033[2J");
		out.flush();
		out.print("\n");
	}
}

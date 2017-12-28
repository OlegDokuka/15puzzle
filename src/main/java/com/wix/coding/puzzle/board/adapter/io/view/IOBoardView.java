package com.wix.coding.puzzle.board.adapter.io.view;

import java.io.PrintStream;

import com.wix.coding.puzzle.board.view.BoardView;
import com.wix.coding.puzzle.core.presenter.Model;

public class IOBoardView implements BoardView {

	private final PrintStream out;
	private final boolean     clean;

	public IOBoardView(PrintStream out) {
		this(out, true);
	}

	public IOBoardView(PrintStream out, boolean clean) {
		this.out = out;
		this.clean = clean;
	}

	@Override
	public <M extends Model<? extends int[][]>> void render(M model) {
		int[][] puzzles = model.content();
		int size = puzzles.length;

		if (size == 0) {
			return;
		}

		if (clean) {
			clean();
		}

		drawHorizontalLine(size);
		for (int x = 0; x < size; x++) {
			drawVerticalLines(size);
			drawValues(puzzles[x]);
			drawVerticalLines(size);
			drawHorizontalLine(size);
		}
	}

	private void clean() {
		out.print("\033[H\033[2J");
		out.flush();
		out.print("\n");
	}

	private void drawHorizontalLine(int size) {
		out.print("-");
		for (int i = 0; i < size; i++) {
			out.print("--------");
		}
		out.print("\n");
	}

	private void drawVerticalLines(int size) {
		out.print("|");
		for (int x = 0; x < size; x++) {
			out.print("       |");
		}
		out.print("\n");
	}

	private void drawValues(int[] values) {
		int size = values.length;

		out.print("|");
		for (int i = 0; i < size; i++) {
			int value = values[i];

			if ((value / 10) == 1) {
				out.format("  %s   |", value);
			}
			else {
				out.format("   %s   |", value == 0 ? " " : value);
			}
		}

		out.print("\n");
	}
}

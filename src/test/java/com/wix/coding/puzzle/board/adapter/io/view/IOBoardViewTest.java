package com.wix.coding.puzzle.board.adapter.io.view;

import java.io.PrintStream;

import com.wix.coding.puzzle.board.adapter.io.view.harness.TestBoardModel;
import com.wix.coding.puzzle.harness.ResourceHelper;
import com.wix.coding.puzzle.harness.TestOutputStream;
import com.wix.coding.puzzle.core.presenter.View;
import org.junit.Test;

public class IOBoardViewTest {

	@Test
	public void shouldPrintToOutput15PuzzleBoard() {
		TestOutputStream out = new TestOutputStream();
		View view = new IOBoardView(
				new PrintStream(out, true), false
		);

		int[][] puzzles3x3 = new int[][] {
				new int[] {0 , 1, 2},
				new int[] {3 , 4, 5},
				new int[] {6 , 7, 8},
		};

		view.render(new TestBoardModel(puzzles3x3));
		out.verifyContent(ResourceHelper.loadSystemResourceAsString("board3x3.txt"));
		out.reset();

		int[][] puzzles4x4 = new int[][] {
				new int[] {0 , 1, 2, 3},
				new int[] {4 , 5, 6, 7},
				new int[] {8, 9, 10, 11},
				new int[] {12, 13, 14, 15},
		};

		view.render(new TestBoardModel(puzzles4x4));
		out.verifyContent(ResourceHelper.loadSystemResourceAsString("board4x4.txt"));
		out.reset();
	}

	@Test
	public void shouldBeNoOutputInCaseOfEmptyMatrix() {
		TestOutputStream out = new TestOutputStream();
		View view = new IOBoardView(
				new PrintStream(out, true), false
		);

		int[][] puzzles0x0 = new int[0][];

		view.render(new TestBoardModel(puzzles0x0));
		out.verifyContent("");
		out.reset();
	}
}

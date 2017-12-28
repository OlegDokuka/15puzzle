package com.wix.coding.puzzle.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.wix.coding.puzzle.board.harness.TestBoardChangedListener;
import com.wix.coding.puzzle.board.harness.TestShuffler;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import static com.wix.coding.puzzle.board.harness.BoardTestHarness.countDifferences;
import static com.wix.coding.puzzle.board.harness.BoardTestHarness.verifyMatrixFormedCorrectly;

public class GameBoardTest {

	@Test
	public void shouldReturnCorrectSizeOfBoardSizeAndPuzzlesAmount() {
		GameBoard board = new GameBoard(4, new TestShuffler());
		TestBoardChangedListener listener = new TestBoardChangedListener();

		board.registerListener(listener);
		board.init();

		int[][] matrix = listener.snapshot();

		Assert.assertEquals(4, board.size());
		verifyMatrixFormedCorrectly(board, matrix);
		Assert.assertTrue(countDifferences(matrix,
				new int[][]{
					new int[]{0, 1, 2, 3},
					new int[]{4, 5, 6, 7},
					new int[]{8, 9, 10, 11},
					new int[]{12, 13, 14, 15}
				}
		) == 0);
	}

	@Test
	public void shouldSwitchingCorrectlyForEmptyInX0Y0Puzzles() {
		GameBoard board = new GameBoard(4, new TestShuffler());
		TestBoardChangedListener listener = new TestBoardChangedListener();

		board.registerListener(listener);
		board.init();

		listener.expectInvokedNTimes(1);

		board.switchWithRight();
		listener.expectMatrixChanged();

		board.switchWithLeft();
		listener.expectMatrixChanged();

		board.switchWithBottom();
		listener.expectMatrixChanged();

		board.switchWithTop();
		listener.expectMatrixChanged();

		board.switchWithLeft();
		listener.expectMatrixNotChanged();

		board.switchWithTop();
		listener.expectMatrixNotChanged();

		listener.expectInvokedNTimes(7);
		listener.expectUserNotWon();
	}

	@Test
	public void theUserShouldGotAWin() {
		GameBoard board =
				new GameBoard(3, new TestShuffler(new int[]{1, 2, 3, 4, 5, 6, 7, 0, 8}));
		TestBoardChangedListener listener = new TestBoardChangedListener();

		board.registerListener(listener);
		board.init();

		board.switchWithRight();
		listener.expectMatrixChanged();
		listener.expectInvokedNTimes(2);
		listener.expectUsersWin();
	}

	@Test
	public void boardShufflingShouldBeTransparentAndDependsOnlyOnShufflerImplementation() {
		int attempts = new Random().nextInt(100) + 2;
		List<int[][]> boardsMatrices = new ArrayList<>();
		GameBoard board = new GameBoard(4, new TestShuffler());
		TestBoardChangedListener listener = new TestBoardChangedListener();

		board.registerListener(listener);

		for (int i = 0; i < attempts; i++) {
			board.init();
			boardsMatrices.add(listener.snapshot());
		}

		for (int i = 1; i < attempts; i++) {
			Assert.assertTrue(countDifferences(
					boardsMatrices.get(i - 1),
					boardsMatrices.get(i)
			) == 0);
		}
	}

	@Test
	public void assumeThatAllBoardsShouldBeDifferentBasedOnJavaBasedRandomShufflerImplementation() {
		int attempts = new Random().nextInt(100) + 2;
		List<int[][]> boardsMatrices = new ArrayList<>();
		GameBoard board = new GameBoard(4, new DefaultShuffler());
		TestBoardChangedListener listener = new TestBoardChangedListener();

		board.registerListener(listener);

		for (int i = 0; i < attempts; i++) {
			board.init();
			boardsMatrices.add(listener.snapshot());
		}

		for (int i = 1; i < attempts; i++) {
			Assume.assumeTrue(countDifferences(
					boardsMatrices.get(i - 1),
					boardsMatrices.get(i)
			) > 0);
		}
	}
}

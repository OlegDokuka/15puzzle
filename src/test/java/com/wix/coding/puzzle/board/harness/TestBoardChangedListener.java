package com.wix.coding.puzzle.board.harness;

import com.wix.coding.puzzle.board.event.BoardChangedEvent;
import com.wix.coding.puzzle.board.event.BoardEvent;
import com.wix.coding.puzzle.board.event.WinEvent;
import com.wix.coding.puzzle.core.event.Event;
import com.wix.coding.puzzle.core.event.EventListener;
import org.junit.Assert;

import static com.wix.coding.puzzle.board.harness.BoardTestHarness.countDifferences;

public class TestBoardChangedListener implements EventListener<BoardEvent> {

	private int[][] last;
	private int[][] current;
	private int     eventsCount;
	private boolean userWin = false;

	public int[][] snapshot() {
		return current;
	}

	@Override
	public void onEvent(BoardEvent event) {
		if (event instanceof BoardChangedEvent) {
			int[][] puzzles = ((BoardChangedEvent) event).body()
			                                             .content();
			last = current;
			current = puzzles;
			eventsCount++;
			userWin = false;
		} else if(event instanceof WinEvent)  {
			userWin = true;
		}
	}

	public void expectInvokedNTimes(int times) {
		Assert.assertEquals(times, eventsCount);
	}

	public void expectMatrixChanged() {
		Assert.assertTrue(last != current);

		if (last == null) {
			return;
		}

		Assert.assertTrue(countDifferences(last, current) > 0);
	}

	public void expectUsersWin() {
		Assert.assertTrue(userWin);
	}

	public void expectUserNotWon() {
		Assert.assertFalse(userWin);
	}

	public void expectMatrixNotChanged() {
		Assert.assertTrue(last == current || countDifferences(last, current) == 0);
	}
}

package com.wix.coding.puzzle.board.controller;

import com.wix.coding.puzzle.board.event.BoardChangedEvent;
import com.wix.coding.puzzle.board.event.WinEvent;
import com.wix.coding.puzzle.board.view.BoardView;
import com.wix.coding.puzzle.board.view.WinView;
import org.junit.Test;
import org.mockito.Mockito;

public class BoardControllerTest {

	@Test
	public void shouldRenderBoardViewInCaseOfBoardChangedEvent() {
		WinView mockWinView = Mockito.mock(WinView.class);
		BoardView mockBoardView = Mockito.mock(BoardView.class);

		BoardController controller = new BoardController(mockWinView, mockBoardView);

		controller.onEvent(new BoardChangedEvent(Mockito.any()));

		Mockito.verifyZeroInteractions(mockWinView);
		Mockito.verify(mockBoardView).render(Mockito.any());
	}

	@Test
	public void shouldRenderWinViewInCaseOfWinEvent() {
		WinView mockWinView = Mockito.mock(WinView.class);
		BoardView mockBoardView = Mockito.mock(BoardView.class);

		BoardController controller = new BoardController(mockWinView, mockBoardView);

		controller.onEvent(new WinEvent());

		Mockito.verifyZeroInteractions(mockBoardView);
		Mockito.verify(mockWinView).render(null);
	}
}

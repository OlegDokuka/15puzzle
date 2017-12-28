package com.wix.coding.puzzle.board.controller;

import com.wix.coding.puzzle.board.event.BoardChangedEvent;
import com.wix.coding.puzzle.board.event.BoardEvent;
import com.wix.coding.puzzle.board.event.WinEvent;
import com.wix.coding.puzzle.board.view.BoardView;
import com.wix.coding.puzzle.board.view.WinView;
import com.wix.coding.puzzle.core.event.EventListener;

public class BoardController implements EventListener<BoardEvent> {

	private final WinView   winView;
	private final BoardView boardView;

	public BoardController(WinView view, BoardView boardView) {
		this.winView = view;
		this.boardView = boardView;
	}

	@Override
	public void onEvent(BoardEvent event) {
		if (event instanceof WinEvent) {
			winView.render(null);
		}
		else if (event instanceof BoardChangedEvent) {
			BoardChangedEvent boardChangedEvent = (BoardChangedEvent) event;

			boardView.render(boardChangedEvent.body());
		}
	}
}

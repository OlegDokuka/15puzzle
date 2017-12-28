package com.wix.coding.puzzle.board.event;

import com.wix.coding.puzzle.board.BoardModel;

public class BoardChangedEvent implements BoardEvent {

	private final BoardModel model;

	public BoardChangedEvent(BoardModel model) {
		this.model = model;
	}

	@Override
	public BoardModel body() {
		return model;
	}
}

package com.wix.coding.puzzle.board;

import com.wix.coding.puzzle.board.event.BoardEvent;
import com.wix.coding.puzzle.core.event.EventProducer;
import com.wix.coding.puzzle.core.presenter.Model;

public interface Board extends EventProducer<Object, BoardEvent> {

	int size();

	void init();

	void switchWithLeft();

	void switchWithRight();

	void switchWithTop();

	void switchWithBottom();
}

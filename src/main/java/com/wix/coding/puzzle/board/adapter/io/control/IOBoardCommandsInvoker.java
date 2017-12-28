package com.wix.coding.puzzle.board.adapter.io.control;

import java.util.List;
import java.util.Objects;

import com.wix.coding.puzzle.board.Board;
import com.wix.coding.puzzle.core.control.CommandsInvoker;
import com.wix.coding.puzzle.core.presenter.Model;

public class IOBoardCommandsInvoker implements CommandsInvoker {

	private final Board board;

	public IOBoardCommandsInvoker(Board board) {
		this.board = Objects.requireNonNull(board);
	}

	@Override
	public boolean support(Object command) {
		String input = command.toString();

		switch (input) {
			case "1":
			case "2":
			case "3":
			case "4":
				return true;
		}

		return false;
	}

	@Override
	public void invoke(Object command) {
		String input = command.toString();

		switch (input) {
			case "1":
				board.switchWithLeft();
				break;
			case "2":
				board.switchWithRight();
				break;
			case "3":
				board.switchWithTop();
				break;
			case "4":
				board.switchWithBottom();
				break;
		}
	}

	@Override
	public Model<List<String>> commands() {
		return new IOBoardCommandsModel();
	}


}

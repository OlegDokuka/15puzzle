package com.wix.coding.puzzle.launcher;

import java.util.List;

import com.wix.coding.puzzle.board.Board;
import com.wix.coding.puzzle.board.controller.BoardController;
import com.wix.coding.puzzle.board.view.BoardView;
import com.wix.coding.puzzle.board.view.WinView;
import com.wix.coding.puzzle.core.control.CommandsInvoker;
import com.wix.coding.puzzle.help.Help;
import com.wix.coding.puzzle.help.controller.HelpController;
import com.wix.coding.puzzle.help.view.HelpView;

public abstract class AbstractGameLauncher implements Launcher {

	protected final Board                 board;
	protected final Help                  help;
	protected final List<CommandsInvoker> commandInvokers;

	protected final WinView   winView;
	protected final BoardView boardView;
	protected final HelpView  helpView;

	public AbstractGameLauncher(Board board,
			Help help,
			List<CommandsInvoker> invokers,
			WinView winView,
			BoardView boardView,
			HelpView helpView) {
		this.board = board;
		this.help = help;

		commandInvokers = invokers;
		this.winView = winView;
		this.boardView = boardView;
		this.helpView = helpView;
	}

	@Override
	public void launch() {
		registerListeners();
		init();
	}

	@Override
	public void relaunch() {
		init();
	}

	protected void registerListeners() {
		BoardController controller = new BoardController(winView, boardView);
		HelpController listener = new HelpController(helpView, commandInvokers);

		board.registerListener(controller);
		help.registerListener(listener);
	}

	protected void init() {
		board.init();
		help.show();
	}
}

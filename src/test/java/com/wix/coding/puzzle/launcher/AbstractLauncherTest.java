package com.wix.coding.puzzle.launcher;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wix.coding.puzzle.board.GameBoard;
import com.wix.coding.puzzle.board.adapter.io.control.IOBoardCommandsInvoker;
import com.wix.coding.puzzle.board.adapter.io.view.IOBoardView;
import com.wix.coding.puzzle.board.adapter.io.view.IOWinView;
import com.wix.coding.puzzle.board.harness.TestShuffler;
import com.wix.coding.puzzle.core.control.CommandsInvoker;
import com.wix.coding.puzzle.harness.TestOutputStream;
import com.wix.coding.puzzle.help.GameHelp;
import com.wix.coding.puzzle.help.adapter.io.control.IOHelpCommandsInvoker;
import com.wix.coding.puzzle.help.adapter.io.view.IOHelpView;
import com.wix.coding.puzzle.launcher.adapter.io.control.IOGameCommandsInvoker;
import org.junit.Test;

import static com.wix.coding.puzzle.harness.ResourceHelper.loadSystemResourceAsString;

public class AbstractLauncherTest {

	@Test
	public void theGameShouldBeAbleToRunSuccessfully() {
		TestOutputStream out = new TestOutputStream();
		PrintStream printStream = new PrintStream(out);
		GameBoard board = new GameBoard(4, new TestShuffler());
		GameHelp help = new GameHelp();
		IOWinView winView = new IOWinView(printStream, false);
		IOBoardView boardView = new IOBoardView(printStream, false);
		IOHelpView helpView = new IOHelpView(printStream);

		List<CommandsInvoker> invokers = new ArrayList<>();
		Launcher launcher = new AbstractGameLauncher(board,
				help,
				invokers,
				winView,
				boardView,
				helpView) {
		};

		IOBoardCommandsInvoker boardCommandsInvoker = new IOBoardCommandsInvoker(board);
		IOHelpCommandsInvoker helpCommandsInvoker = new IOHelpCommandsInvoker(help);
		IOGameCommandsInvoker gameCommandsInvoker = new IOGameCommandsInvoker(launcher);

		invokers.addAll(Arrays.asList(boardCommandsInvoker,
				gameCommandsInvoker,
				helpCommandsInvoker));

		launcher.launch();
		out.verifyContent(loadSystemResourceAsString("board4x4TestLaunch.txt"));
	}

	@Test
	public void theGameShouldSwitchPuzzlesOnUsersInput() {
		String[] boards = loadSystemResourceAsString("board4x4Interactive.txt").split("&\n");
		TestOutputStream out = new TestOutputStream();
		PrintStream printStream = new PrintStream(out);
		GameBoard board = new GameBoard(4, new TestShuffler());
		GameHelp help = new GameHelp();
		IOWinView winView = new IOWinView(printStream, false);
		IOBoardView boardView = new IOBoardView(printStream, false);
		IOHelpView helpView = new IOHelpView(printStream);

		List<CommandsInvoker> invokers = new ArrayList<>();
		Launcher launcher = new AbstractGameLauncher(board,
				help,
				invokers,
				winView,
				boardView,
				helpView) {
		};

		IOBoardCommandsInvoker boardCommandsInvoker = new IOBoardCommandsInvoker(board);
		IOHelpCommandsInvoker helpCommandsInvoker = new IOHelpCommandsInvoker(help);
		IOGameCommandsInvoker gameCommandsInvoker = new IOGameCommandsInvoker(launcher);

		invokers.addAll(Arrays.asList(boardCommandsInvoker,
				gameCommandsInvoker,
				helpCommandsInvoker));

		launcher.launch();
		out.reset();

		boardCommandsInvoker.invoke("2");
		out.verifyContent(boards[0]);
		out.reset();

		boardCommandsInvoker.invoke("1");
		out.verifyContent(boards[1]);
		out.reset();

		boardCommandsInvoker.invoke("4");
		out.verifyContent(boards[2]);
		out.reset();

		boardCommandsInvoker.invoke("3");
		out.verifyContent(boards[3]);
		out.reset();
	}

	@Test
	public void userShouldBeAbleToWinTheGameAndSeeWinMessage() {
		TestOutputStream out = new TestOutputStream();
		PrintStream printStream = new PrintStream(out);
		GameBoard board = new GameBoard(3, new TestShuffler(new int[]{1, 2, 3, 4, 5, 6, 7, 0, 8}));
		GameHelp help = new GameHelp();
		IOWinView winView = new IOWinView(printStream, false);
		IOBoardView boardView = new IOBoardView(printStream, false);
		IOHelpView helpView = new IOHelpView(printStream);

		List<CommandsInvoker> invokers = new ArrayList<>();
		Launcher launcher = new AbstractGameLauncher(board,
				help,
				invokers,
				winView,
				boardView,
				helpView) {
		};

		IOBoardCommandsInvoker boardCommandsInvoker = new IOBoardCommandsInvoker(board);
		IOHelpCommandsInvoker helpCommandsInvoker = new IOHelpCommandsInvoker(help);
		IOGameCommandsInvoker gameCommandsInvoker = new IOGameCommandsInvoker(launcher);

		invokers.addAll(Arrays.asList(boardCommandsInvoker,
				gameCommandsInvoker,
				helpCommandsInvoker));

		launcher.launch();
		out.reset();

		boardCommandsInvoker.invoke("2");
		out.verifyContent(loadSystemResourceAsString("board3x3&win.txt"));
	}

	@Test
	public void userShouldBeAbleToRestartTheGame() {
		TestOutputStream out = new TestOutputStream();
		PrintStream printStream = new PrintStream(out);
		GameBoard board = new GameBoard(3, new TestShuffler(new int[]{1, 2, 3, 4, 5, 6, 7, 0, 8}));
		GameHelp help = new GameHelp();
		IOWinView winView = new IOWinView(printStream, false);
		IOBoardView boardView = new IOBoardView(printStream, false);
		IOHelpView helpView = new IOHelpView(printStream);

		List<CommandsInvoker> invokers = new ArrayList<>();
		Launcher launcher = new AbstractGameLauncher(board,
				help,
				invokers,
				winView,
				boardView,
				helpView) {
		};

		IOBoardCommandsInvoker boardCommandsInvoker = new IOBoardCommandsInvoker(board);
		IOHelpCommandsInvoker helpCommandsInvoker = new IOHelpCommandsInvoker(help);
		IOGameCommandsInvoker gameCommandsInvoker = new IOGameCommandsInvoker(launcher);

		invokers.addAll(Arrays.asList(boardCommandsInvoker,
				gameCommandsInvoker,
				helpCommandsInvoker));

		launcher.launch();
		out.reset();

		boardCommandsInvoker.invoke("2");
		gameCommandsInvoker.invoke("r");
		out.verifyContent(loadSystemResourceAsString("board3x3&win&reset.txt"));
	}

}
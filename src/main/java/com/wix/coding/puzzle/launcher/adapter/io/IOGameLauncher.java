package com.wix.coding.puzzle.launcher.adapter.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.wix.coding.puzzle.board.DefaultShuffler;
import com.wix.coding.puzzle.board.GameBoard;
import com.wix.coding.puzzle.board.adapter.io.control.IOBoardCommandsInvoker;
import com.wix.coding.puzzle.board.adapter.io.view.IOBoardView;
import com.wix.coding.puzzle.board.adapter.io.view.IOWinView;
import com.wix.coding.puzzle.core.control.CommandsInvoker;
import com.wix.coding.puzzle.help.GameHelp;
import com.wix.coding.puzzle.help.adapter.io.control.IOHelpCommandsInvoker;
import com.wix.coding.puzzle.help.adapter.io.view.IOHelpView;
import com.wix.coding.puzzle.launcher.AbstractGameLauncher;
import com.wix.coding.puzzle.launcher.adapter.io.control.IOGameCommandsInvoker;

public class IOGameLauncher extends AbstractGameLauncher {

	private final InputStream in;
	private final PrintStream out;

	public IOGameLauncher(InputStream in, PrintStream out) {
		super(new GameBoard(4, new DefaultShuffler()),
				new GameHelp(),
				new ArrayList<>(),
				new IOWinView(out),
				new IOBoardView(out),
				new IOHelpView(out));

		this.in = in;
		this.out = out;

		commandInvokers.addAll(Arrays.asList(new IOBoardCommandsInvoker(board),
				new IOGameCommandsInvoker(this),
				new IOHelpCommandsInvoker(help)));
	}

	@Override
	public void launch() {
		super.launch();
		Scanner scanner = new Scanner(in);

		while (true) {
			String command = scanner.nextLine().trim();
			CommandsInvoker selectedCommandsInvoker = null;

			for (CommandsInvoker invoker : commandInvokers) {
				if (invoker.support(command)) {
					selectedCommandsInvoker = invoker;
				}
			}

			if (selectedCommandsInvoker != null) {
				selectedCommandsInvoker.invoke(command);
			}
			else {
				help.show();
			}
		}
	}

	public static void main(String[] args) {
		new IOGameLauncher(System.in, System.out).launch();
	}
}

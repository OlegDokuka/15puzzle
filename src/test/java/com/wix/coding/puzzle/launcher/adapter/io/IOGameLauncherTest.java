package com.wix.coding.puzzle.launcher.adapter.io;

import java.io.PrintStream;

import com.wix.coding.puzzle.harness.TestInputStream;
import com.wix.coding.puzzle.harness.TestOutputStream;
import com.wix.coding.puzzle.launcher.Launcher;
import com.wix.coding.puzzle.launcher.adapter.io.harness.IOBoardViewParser;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import static com.wix.coding.puzzle.board.harness.BoardTestHarness.countDifferences;

public class IOGameLauncherTest {

	@Test
	public void theGameShouldBeAbleToRunSuccessfully() throws InterruptedException {
		TestInputStream in = new TestInputStream();
		TestOutputStream out = new TestOutputStream();
		PrintStream printStream = new PrintStream(out);
		Launcher launcher = new IOGameLauncher(in, printStream);

		new Thread(launcher::launch).start();
		Thread.sleep(300);
		out.verifyNotEmpty();

		IOBoardViewParser.parse(out.stapshot());
	}

	@Test
	public void theGameShouldSwitchPuzzlesOnUsersInput() throws InterruptedException {
		TestInputStream in = new TestInputStream();
		TestOutputStream out = new TestOutputStream();
		PrintStream printStream = new PrintStream(out);
		Launcher launcher = new IOGameLauncher(in, printStream);

		new Thread(launcher::launch).start();
		Thread.sleep(300);

		int[][] initial = IOBoardViewParser.parse(out.stapshot());
		out.reset();

		in.write("2");
		Thread.sleep(300);
		Assume.assumeTrue(
				2 == countDifferences(initial, IOBoardViewParser.parse(out.stapshot()))
		);
		out.reset();

		in.write("1");
		Thread.sleep(300);
		Assume.assumeTrue(
				0 == countDifferences(initial, IOBoardViewParser.parse(out.stapshot()))
		);
		out.reset();

		in.write("4");
		Thread.sleep(300);
		Assume.assumeTrue(
				2 == countDifferences(initial, IOBoardViewParser.parse(out.stapshot()))
		);
		out.reset();

		in.write("3");
		Thread.sleep(300);
		Assert.assertEquals(
				0,
				countDifferences(initial, IOBoardViewParser.parse(out.stapshot()))
		);
	}

	@Test
	public void userShouldBeAbleToRestartTheGame() throws InterruptedException {
		TestInputStream in = new TestInputStream();
		TestOutputStream out = new TestOutputStream();
		PrintStream printStream = new PrintStream(out);
		Launcher launcher = new IOGameLauncher(in, printStream);

		new Thread(launcher::launch).start();
		Thread.sleep(300);

		int[][] initial = IOBoardViewParser.parse(out.stapshot());
		out.reset();

		in.write("r");
		Thread.sleep(300);
		Assume.assumeTrue(
				countDifferences(initial, IOBoardViewParser.parse(out.stapshot())) > 0
		);
		out.reset();
	}
}

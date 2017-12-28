package com.wix.coding.puzzle.board.adapter.io.control;

import java.util.List;

import com.wix.coding.puzzle.board.Board;
import com.wix.coding.puzzle.core.presenter.Model;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class IOBoardCommandsInvokerTest {

	@Test(expected = NullPointerException.class)
	public void shouldNotAcceptNullableBoard() {
		new IOBoardCommandsInvoker(null);
	}

	@Test
	public void shouldSupportNextListOfCommands() {
		Board mockBoard = Mockito.mock(Board.class);

		IOBoardCommandsInvoker invoker = new IOBoardCommandsInvoker(mockBoard);

		for (int i = 1; i <= 4; i++) {
			Assert.assertTrue(invoker.support(String.valueOf(i)));
		}
	}

	@Test
	public void shouldInvokeSwitchWithLeftMethodOnCommand1() {
		Board mockBoard = Mockito.mock(Board.class);

		IOBoardCommandsInvoker invoker = new IOBoardCommandsInvoker(mockBoard);

		invoker.invoke("1");
		Mockito.verify(mockBoard).switchWithLeft();
	}

	@Test
	public void shouldInvokeSwitchWithRightMethodOnCommand2() {
		Board mockBoard = Mockito.mock(Board.class);

		IOBoardCommandsInvoker invoker = new IOBoardCommandsInvoker(mockBoard);

		invoker.invoke("2");
		Mockito.verify(mockBoard).switchWithRight();
	}

	@Test
	public void shouldInvokeSwitchWithTopMethodOnCommand3() {
		Board mockBoard = Mockito.mock(Board.class);

		IOBoardCommandsInvoker invoker = new IOBoardCommandsInvoker(mockBoard);

		invoker.invoke("3");
		Mockito.verify(mockBoard).switchWithTop();
	}

	@Test
	public void shouldInvokeSwitchWithBottomMethodOnCommand4() {
		Board mockBoard = Mockito.mock(Board.class);

		IOBoardCommandsInvoker invoker = new IOBoardCommandsInvoker(mockBoard);

		invoker.invoke("4");
		Mockito.verify(mockBoard).switchWithBottom();
	}

	@Test
	public void boardShouldNotBeInvokedOnUnsupportedCommand() {
		Board mockBoard = Mockito.mock(Board.class);

		IOBoardCommandsInvoker invoker = new IOBoardCommandsInvoker(mockBoard);

		invoker.invoke("5");
		Mockito.verifyZeroInteractions(mockBoard);
	}

	@Test
	public void shouldReturnModelWithCommandsDescriptions() {
		Board mockBoard = Mockito.mock(Board.class);

		IOBoardCommandsInvoker invoker = new IOBoardCommandsInvoker(mockBoard);
		Model<List<String>> commands = invoker.commands();

		Assert.assertArrayEquals(IOBoardCommandsModel.commands, commands.content().toArray());
	}
}

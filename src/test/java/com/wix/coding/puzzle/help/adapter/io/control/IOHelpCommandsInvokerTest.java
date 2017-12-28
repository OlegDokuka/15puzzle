package com.wix.coding.puzzle.help.adapter.io.control;

import java.util.List;

import com.wix.coding.puzzle.board.adapter.io.control.IOBoardCommandsInvoker;
import com.wix.coding.puzzle.core.presenter.Model;
import com.wix.coding.puzzle.help.Help;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class IOHelpCommandsInvokerTest {

	@Test(expected = NullPointerException.class)
	public void shouldNotAcceptNullableBoard() {
		new IOBoardCommandsInvoker(null);
	}

	@Test
	public void shouldSupportNextListOfCommands() {
		Help mockHelp = Mockito.mock(Help.class);

		IOHelpCommandsInvoker invoker = new IOHelpCommandsInvoker(mockHelp);

		Assert.assertTrue(invoker.support("h"));
	}

	@Test
	public void boardShouldNotBeInvokedOnUnsupportedCommand() {
		Help mockHelp = Mockito.mock(Help.class);

		IOHelpCommandsInvoker invoker = new IOHelpCommandsInvoker(mockHelp);

		invoker.invoke("5");
		Mockito.verifyZeroInteractions(mockHelp);
	}

	@Test
	public void shouldInvokeShowHelpOnCommandH() {
		Help mockHelp = Mockito.mock(Help.class);

		IOHelpCommandsInvoker invoker = new IOHelpCommandsInvoker(mockHelp);


		invoker.invoke("h");
		Mockito.verify(mockHelp).show();
	}

	@Test
	public void shouldReturnModelWithCommandsDescriptions() {
		Help mockHelp = Mockito.mock(Help.class);

		IOHelpCommandsInvoker invoker = new IOHelpCommandsInvoker(mockHelp);
		Model<List<String>> commands = invoker.commands();

		Assert.assertArrayEquals(IOHelpCommandsModel.commands,
				commands.content()
				        .toArray());
	}
}


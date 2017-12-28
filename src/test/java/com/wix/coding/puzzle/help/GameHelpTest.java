package com.wix.coding.puzzle.help;

import com.wix.coding.puzzle.core.event.EventListener;
import org.junit.Test;
import org.mockito.Mockito;

public class GameHelpTest {

	@Test
	public void shouldInvokeListenersOnShowHelp() {
		EventListener mockEventListener = Mockito.mock(EventListener.class);
		Help help = new GameHelp();

		help.registerListener(mockEventListener);
		help.show();

		Mockito.verify(mockEventListener).onEvent(Mockito.any());
	}
}

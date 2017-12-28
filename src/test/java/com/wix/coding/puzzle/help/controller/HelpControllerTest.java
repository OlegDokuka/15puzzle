package com.wix.coding.puzzle.help.controller;

import java.util.Collections;

import com.wix.coding.puzzle.help.event.ShowHelpEvent;
import com.wix.coding.puzzle.help.view.HelpView;
import org.junit.Test;
import org.mockito.Mockito;

public class HelpControllerTest {

	@Test(expected = NullPointerException.class)
	public void shouldThrowNPEOnNullableParams() {
		new HelpController(null, null);
	}

	@Test
	public void shouldInvokeViewRenderingOnHelpEvent() {
		HelpView mockHelpView = Mockito.mock(HelpView.class);
		HelpController controller = new HelpController(mockHelpView, Collections.emptyList());

		controller.onEvent(new ShowHelpEvent());

		Mockito.verify(mockHelpView).render(Mockito.any());
	}
}

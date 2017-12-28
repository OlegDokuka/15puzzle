package com.wix.coding.puzzle.help.controller;

import java.util.List;
import java.util.Objects;

import com.wix.coding.puzzle.core.control.CommandsInvoker;
import com.wix.coding.puzzle.core.event.EventListener;
import com.wix.coding.puzzle.core.presenter.Model;
import com.wix.coding.puzzle.help.ComposedHelpModel;
import com.wix.coding.puzzle.help.event.ShowHelpEvent;
import com.wix.coding.puzzle.help.view.HelpView;

public class HelpController implements EventListener<ShowHelpEvent> {

	private final HelpView              view;
	private final List<CommandsInvoker> invokers;

	public HelpController(HelpView view, List<CommandsInvoker> invokers) {
		this.view = Objects.requireNonNull(view);
		this.invokers = Objects.requireNonNull(invokers);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void onEvent(ShowHelpEvent event) {
		view.render(new ComposedHelpModel(invokers.stream()
		                                          .map(CommandsInvoker::commands)
		                                          .toArray(Model[]::new)));
	}
}

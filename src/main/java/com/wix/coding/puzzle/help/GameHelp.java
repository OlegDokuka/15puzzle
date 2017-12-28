package com.wix.coding.puzzle.help;

import java.util.ArrayList;
import java.util.List;

import com.wix.coding.puzzle.core.event.EventListener;
import com.wix.coding.puzzle.help.event.ShowHelpEvent;

public class GameHelp implements Help {

	private final List<EventListener<ShowHelpEvent>> listeners;

	public GameHelp() {
		this.listeners = new ArrayList<>();
	}

	public void show() {
		listeners.forEach(l -> l.onEvent(new ShowHelpEvent()));
	}

	@Override
	public EventListener.Disposable registerListener(EventListener<ShowHelpEvent> listener) {
		listeners.add(listener);

		return () -> listeners.remove(listener);
	}
}

package com.wix.coding.puzzle.core.event;

public interface EventListener<E extends Event> {
	void onEvent(E event);

	interface Disposable {
		void dispose();
	}
}

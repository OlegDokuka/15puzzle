package com.wix.coding.puzzle.core.event;

public interface EventProducer<T, E extends Event<T>> {

	EventListener.Disposable registerListener(EventListener<E> listener);

}

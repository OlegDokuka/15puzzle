package com.wix.coding.puzzle.core.presenter;

public interface View<T> {

	<M extends Model<? extends T>> void render(M model);
}

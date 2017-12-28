package com.wix.coding.puzzle.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import com.wix.coding.puzzle.board.event.BoardChangedEvent;
import com.wix.coding.puzzle.board.event.BoardEvent;
import com.wix.coding.puzzle.board.event.WinEvent;
import com.wix.coding.puzzle.core.event.EventListener;

public class GameBoard implements Board {

	private final int                             size;
	private final int[][]                         puzzles;
	private final Shuffler shuffler;
	private final List<EventListener<BoardEvent>> listeners;

	private int zeroX;
	private int zeroY;

	public GameBoard(int size, Shuffler shuffler) {
		this.size = size;
		this.puzzles = new int[size][];
		this.shuffler = Objects.requireNonNull(shuffler);
		this.listeners = new ArrayList<>();
	}

	@Override
	public EventListener.Disposable registerListener(EventListener<BoardEvent> listener) {
		listeners.add(listener);

		return () -> listeners.remove(listener);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void init() {
		fill(shuffler);
		invokeListeners();
	}

	@Override
	public void switchWithLeft() {
		int swapX = zeroX - 1;

		if (swapX > -1) {
			swapZeroWith(swapX, zeroY);
		}

		invokeListeners();
	}

	@Override
	public void switchWithRight() {
		int swapX = zeroX + 1;

		if (swapX < size) {
			swapZeroWith(swapX, zeroY);
		}

		invokeListeners();
	}

	@Override
	public void switchWithTop() {
		int swapY = zeroY - 1;

		if (swapY > -1) {
			swapZeroWith(zeroX, swapY);
		}

		invokeListeners();
	}

	@Override
	public void switchWithBottom() {
		int swapY = zeroY + 1;

		if (swapY < size) {
			swapZeroWith(zeroX, swapY);
		}

		invokeListeners();
	}

	private void invokeListeners() {
		listeners.forEach(l -> l.onEvent(new BoardChangedEvent(new BoardModel(copy()))));

		if (checkWin()) {
			listeners.forEach(l -> l.onEvent(new WinEvent()));
		}
	}

	private void swapZeroWith(int x, int y) {
		puzzles[zeroY][zeroX] = puzzles[y][x];
		puzzles[y][x] = 0;

		zeroX = x;
		zeroY = y;
	}

	private boolean checkWin() {
		if (puzzles[0][0] == 1 && puzzles[size - 1][size - 1] == 0 && puzzles[size - 1][size - 2] == size * size - 1) {
			int previous = puzzles[0][0];
			for (int x = 0; x < size; x++) {
				int[] line = puzzles[x];
				for (int y = 0; y < size; y++) {
					int current = line[y];

					if (x == size - 1 && y == size - 1) {
						return true;
					}

					if (previous > current) {
						return false;
					}

					previous = current;
				}
			}
		}

		return false;
	}

	private void fill(Shuffler shuffler) {
		int itemsPerBoard = size * size;
		int[] ints = IntStream.range(0, itemsPerBoard)
		                      .toArray();
		shuffler.shuffle(ints);

		for (int next = 0, y = 0; y < size; y++) {
			puzzles[y] = new int[size];
			for (int x = 0; x < size; x++, next++) {
				int id = ints[next];

				if (id == 0) {
					zeroX = x;
					zeroY = y;
				}

				puzzles[y][x] = id;
			}
		}
	}

	private int[][] copy() {
		int[][] copy = new int[size][];

		for (int i = 0; i < size; i++) {
			copy[i] = Arrays.copyOf(puzzles[i], size);
		}

		return copy;
	}
}

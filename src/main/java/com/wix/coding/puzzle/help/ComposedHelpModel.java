package com.wix.coding.puzzle.help;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.wix.coding.puzzle.core.presenter.Model;

public class ComposedHelpModel implements Model<Collection<String>> {

	private final Model<Collection<String>>[] models;

	public ComposedHelpModel(Model<Collection<String>>... models) {
		this.models = models;
	}

	@Override
	public Collection<String> content() {
		return Arrays.stream(models)
		             .flatMap(m -> m.content().stream())
		             .collect(Collectors.toList());
	}
}

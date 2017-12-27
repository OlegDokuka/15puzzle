package com.wix.coding.puzzle;

import org.junit.Test;

public class GameIntegrationTest {

	@Test
	public void theGameShouldBeAbleToRunSuccessfully() {
		Configurer configurer = new DefaultConfigurer();
		Presenter presenter = new TestPresenter();
		GameEngine engine = new GameEngine(presenter, configurer);

		engine.run();
	}

	@Test
	public void theGameShouldBeAbleToSendBoard() {
		Configurer configurer = new DefaultConfigurer();
		TestPresenter presenter = new TestPresenter();
		GameEngine engine = new GameEngine(presenter, configurer);

		engine.run();
		presenter.verifyGameBoardIsRendered();
	}
}

package com.wix.coding.puzzle;

public class GameIntegrationTest {
	public void theGameShouldBeAbleToStart() {
		Configurer configurer = new DefaultConfigurer();
		Presenter presenter = new ConsolePresenter();
		GameEngine engine = new GameEngine(presenter, configurer);

		engine.start();
	}
}

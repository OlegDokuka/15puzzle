package com.wix.coding.puzzle.help;

import com.wix.coding.puzzle.core.event.EventProducer;
import com.wix.coding.puzzle.help.event.ShowHelpEvent;

public interface Help extends EventProducer<Void, ShowHelpEvent> {
	void show();
}

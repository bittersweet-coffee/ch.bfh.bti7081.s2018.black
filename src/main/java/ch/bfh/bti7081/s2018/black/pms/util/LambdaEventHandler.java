package ch.bfh.bti7081.s2018.black.pms.util;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class LambdaEventHandler extends HashMap<String, List<BiConsumer<String, Object>>> {
	
	public void add(String event, BiConsumer<String, Object> listener) {
		this.get(event).add(listener);
	}
	
	public void notify(String event, Object sender) {
		for (BiConsumer<String, Object> listener : this.get(event)) {
			listener.accept(event, sender);
		}

		for (BiConsumer<String, Object> listener : this.get("*")) {
			listener.accept(event, sender);
		}
	}
}

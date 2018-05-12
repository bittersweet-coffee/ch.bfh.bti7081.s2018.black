package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class AddictionViewImpl extends CustomComponent implements View, AddictionView {

	public static final String NAME = "addiction";
	
	public AddictionViewImpl() {
		Label test = new Label("Addiction here");
        setCompositionRoot(test);
	}
}

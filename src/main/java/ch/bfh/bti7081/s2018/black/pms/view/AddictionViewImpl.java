package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;

public class AddictionViewImpl extends PmsCustomComponent implements View, AddictionView {

	public static final String NAME = "addiction";

	public AddictionViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		Label test = new Label("Addiction here");
        super.contentPanel.setContent(test);
	}
}

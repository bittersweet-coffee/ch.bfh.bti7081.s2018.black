package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;

public class ClinicViewImpl extends PmsCustomComponent implements View, ClinicView {

	public static final String NAME = "clinic";

	public ClinicViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		Label test = new Label("Clinic here");
        super.contentPanel.setContent(test);
	}
}

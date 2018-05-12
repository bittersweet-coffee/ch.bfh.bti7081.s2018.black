package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class PatientViewImpl extends PmsCustomComponent implements View, PatientView {

	public static final String NAME = "patient";

	public PatientViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		Label test = new Label("Patient here");
        super.contentPanel.setContent(test);
	}
}
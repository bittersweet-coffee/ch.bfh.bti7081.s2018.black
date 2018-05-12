package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class ClinicViewImpl extends CustomComponent implements View, ClinicView {

	public static final String NAME = "clinic";
	
	public ClinicViewImpl() {
		Label test = new Label("Clinic here");
        setCompositionRoot(test);
	}
}

package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class PatientViewImpl extends CustomComponent implements View, PatientView {

	public static final String NAME = "patient";
	
	public PatientViewImpl() {
		Label test = new Label("Patient here");
        setCompositionRoot(test);
	}
}

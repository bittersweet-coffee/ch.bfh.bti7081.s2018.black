package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class DrugViewImpl extends CustomComponent implements View, DrugView {

	public static final String NAME = "drug";
	
	public DrugViewImpl() {
		Label test = new Label("Drug here");
        setCompositionRoot(test);
	}
}

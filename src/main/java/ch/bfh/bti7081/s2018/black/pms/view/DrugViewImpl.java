package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;

public class DrugViewImpl extends PmsCustomComponent implements View, DrugView {

	public static final String NAME = "drug";

	public DrugViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		super.menuBar.getItems().get(1).setText((String) VaadinSession.getCurrent().getAttribute("username"));
		Label test = new Label("Drug here");
        super.contentPanel.setContent(test);
	}
}

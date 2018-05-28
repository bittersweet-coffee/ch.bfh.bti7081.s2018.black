package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;

public class ReportViewImpl extends PmsCustomComponent implements View, ReportView {

	public static final String NAME = "report";
	
	public ReportViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		Label test = new Label("Report here");
        super.contentPanel.setContent(test);
	}
}

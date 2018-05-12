package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class ReportViewImpl extends CustomComponent implements View, ReportView {

	public static final String NAME = "report";
	
	public ReportViewImpl() {
		Label test = new Label("Report here");
        setCompositionRoot(test);
	}
}

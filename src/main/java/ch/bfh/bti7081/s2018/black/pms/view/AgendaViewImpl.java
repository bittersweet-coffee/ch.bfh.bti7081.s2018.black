package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class AgendaViewImpl extends CustomComponent implements View, AgendaView {

	public static final String NAME = "agenda";
	
	public AgendaViewImpl() {
		Label test = new Label("Agenda here");
        setCompositionRoot(test);
	}
}

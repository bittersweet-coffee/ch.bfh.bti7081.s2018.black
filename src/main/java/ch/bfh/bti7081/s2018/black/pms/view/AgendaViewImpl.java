package ch.bfh.bti7081.s2018.black.pms.view;

import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.item.BasicItem;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;

public class AgendaViewImpl extends PmsCustomComponent implements View, AgendaView {

	public static final String NAME = "agenda";

	public AgendaViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		Label test = new Label("Agenda here");
        super.contentPanel.setContent(test);
        Calendar<BasicItem> testCal = new Calendar<>();
        super.contentPanel.setContent(testCal);
	}
}

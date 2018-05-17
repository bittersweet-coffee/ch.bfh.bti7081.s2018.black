package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import ch.bfh.bti7081.s2018.black.pms.model.Appointment;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentDataProvider;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;

public class AgendaViewImpl extends PmsCustomComponent implements View, AgendaView {

	public static final String NAME = "agenda";

	private List<AgendaViewListener> listeners = new ArrayList<AgendaViewListener>();
	Calendar<AppointmentItem> cal = new Calendar<>();

	public AgendaViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		cal.setWidth(super.contentPanel.getWidth(), super.contentPanel.getWidthUnits());
		addCalendarEventListeners();
		super.contentPanel.setContent(cal);
	}
	
	private void onCalendarRangeSelect(CalendarComponentEvents.RangeSelectEvent event) {
		//final AppointmentWindow window = new AppointmentWindow(this, event.getStart().toLocalDateTime(), event.getEnd().toLocalDateTime());  
		AppointmentItem appointmentItem = new AppointmentItem(new Appointment(event.getStart().toLocalDateTime(), event.getEnd().toLocalDateTime()));
		final AppointmentWindow window = new AppointmentWindow(this, appointmentItem);  
		super.getUI().getUI().addWindow(window);
    }
	
	private void onCalendarItemClick(CalendarComponentEvents.ItemClickEvent event) {
		//final AppointmentWindow window = new AppointmentWindow(this, event.getCalendarItem().getStart().toLocalDateTime(), event.getCalendarItem().getEnd().toLocalDateTime(), event.getCalendarItem().getCaption(), event.getCalendarItem().getDescription());
		final AppointmentWindow window = new AppointmentWindow(this, (AppointmentItem) event.getCalendarItem());
		super.getUI().getUI().addWindow(window);;
	}
	private void addCalendarEventListeners() {
        cal.setHandler(this::onCalendarRangeSelect);
        cal.setHandler(this::onCalendarItemClick);
    }
	
	public void save(AppointmentItem appointmentItem) {
		for (AgendaViewListener listener: listeners)
			listener.saveButtonClick(appointmentItem);
	}

	@Override
	public void addListener(AgendaViewListener listener) {
		listeners.add(listener);		
	}

	@Override
	public void addEventProvider(AppointmentDataProvider eventProvider) {
		cal.setDataProvider(eventProvider);
	}
}

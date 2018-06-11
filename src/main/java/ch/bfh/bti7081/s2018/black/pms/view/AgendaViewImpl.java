package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.handler.BasicDateClickHandler;
import org.vaadin.addon.calendar.handler.BasicItemMoveHandler;
import org.vaadin.addon.calendar.handler.BasicItemResizeHandler;
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
		super.bC.makeCrumbs(AgendaViewImpl.NAME);
		super.bC.visibleBreadcrumbs();
		//cal.setWidth(super.contentPanel.getWidth(), super.contentPanel.getWidthUnits());
		cal.setWidth("1200px");
		addCalendarEventListeners();
		changeCalendarRange();
		super.contentPanel.setSizeUndefined();
		super.contentPanel.setContent(cal);
	}
	
	private void onCalendarRangeSelect(CalendarComponentEvents.RangeSelectEvent event) {
		AppointmentItem appointmentItem = new AppointmentItem(new Appointment(event.getStart().toLocalDateTime(), event.getEnd().toLocalDateTime()));
		final AppointmentWindow window = new AppointmentWindow(this, appointmentItem); 
		window.setModal(true);
		super.getUI().getUI().addWindow(window);
    }
	
	private void onCalendarItemClick(CalendarComponentEvents.ItemClickEvent event) {
		final AppointmentWindow window = new AppointmentWindow(this, (AppointmentItem) event.getCalendarItem());
		window.setModal(true);
		super.getUI().getUI().addWindow(window);;
	}
	
	private void onDateClick(CalendarComponentEvents.DateClickEvent event) {
		BasicDateClickHandler basicDateClickHandler = new BasicDateClickHandler();
		basicDateClickHandler.dateClick(event);
		changeCalendarRange();
	}
	private void onItemResize(CalendarComponentEvents.ItemResizeEvent event) {
		BasicItemResizeHandler basicItemResizeHandler = new BasicItemResizeHandler();
		basicItemResizeHandler.itemResize(event);
		saveAppointment((AppointmentItem)event.getCalendarItem());
	}
	
	private void onItemMove(CalendarComponentEvents.ItemMoveEvent event) {
		BasicItemMoveHandler basicItemMoveHandler = new BasicItemMoveHandler();
		basicItemMoveHandler.itemMove(event);
		saveAppointment((AppointmentItem)event.getCalendarItem());
	}
	
	private void addCalendarEventListeners() {
        cal.setHandler(this::onCalendarRangeSelect);
        cal.setHandler(this::onCalendarItemClick);
        cal.setHandler(this::onDateClick);
        cal.setHandler(this::onItemResize);
        cal.setHandler(this::onItemMove);
    }
	
	public void saveAppointment(AppointmentItem appointmentItem) {
		for (AgendaViewListener listener: listeners)
			listener.saveAppointment(appointmentItem);
	}
	
	public void deleteAppointment(AppointmentItem appointmentItem) {
		for (AgendaViewListener listener: listeners)
			listener.deleteAppointment(appointmentItem);
	}

	@Override
	public void addListener(AgendaViewListener listener) {
		listeners.add(listener);		
	}

	@Override
	public void addEventProvider(AppointmentDataProvider eventProvider) {
		cal.setDataProvider(eventProvider);
	}
	
	public void changeCalendarRange() {
	}
}

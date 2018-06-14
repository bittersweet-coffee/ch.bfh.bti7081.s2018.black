package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.handler.BasicDateClickHandler;
import org.vaadin.addon.calendar.handler.BasicItemMoveHandler;
import org.vaadin.addon.calendar.handler.BasicItemResizeHandler;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;

import ch.bfh.bti7081.s2018.black.pms.model.Appointment;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentDataProvider;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
/**
 * AgendaViewImpl Class
 * View Implementation of AgendaView
 * @author bielc1
 *
 */
public class AgendaViewImpl extends PmsCustomComponent implements View, AgendaView {

	// identifier used for displaying the correct URL
	public static final String NAME = "agenda";

	// List containing all listeners for this object (mostly the corresponding Presenter Class)
	private List<AgendaViewListener> listeners = new ArrayList<AgendaViewListener>();
	
	//Vaadin Calendar is for visualizing items in a calendar. 
	//Calendar items can be visualized in the variable length view depending on the start and end dates. 
	Calendar<AppointmentItem> cal = new Calendar<>();
	
	// List containing Mock Objects for the PatientModel
	private List<PatientItem> patientItemList;
	
	// List containing Mock Objects for the DoctorModel
	private List<DoctorItem> doctorItemList;

	/**
	 * Default Constructor like all other ViewImplementations to trigger the super-class constructor  
	 */
	public AgendaViewImpl() {
		super();
	}
	/**
	 * Called before the view is shown on screen. 
	 * The event object contains information about parameters used when showing the view,
	 * in addition to references to the old view and the new view.
	 */
	public void enter(ViewChangeEvent event) {
		super.bC.makeCrumbs(AgendaViewImpl.NAME);
		super.bC.visibleBreadcrumbs();
		super.menuBar.getItems().get(1).setText((String) VaadinSession.getCurrent().getAttribute("username"));
		//cal.setWidth(super.contentPanel.getWidth(), super.contentPanel.getWidthUnits());
		cal.setWidth("1200px");
		addCalendarEventListeners();
		this.patientItemList = new LinkedList<>();
		updatePatientItemList();
		this.doctorItemList = new LinkedList<>();
		updateDoctorItemList();
		super.contentPanel.setSizeUndefined();
		super.contentPanel.setContent(cal);
	}
	

	/**
	 * RangeSelectEvent is sent when day or time cells are drag-marked with mouse.
	 * @param event an event object containing information about the selected range
	 */
	private void onCalendarRangeSelect(CalendarComponentEvents.RangeSelectEvent event) {
		AppointmentItem appointmentItem = new AppointmentItem(new Appointment(event.getStart().toLocalDateTime(), event.getEnd().toLocalDateTime()));
		final AppointmentWindow window = new AppointmentWindow(this, appointmentItem, patientItemList, doctorItemList); 
		window.setModal(true);
		super.getUI().getUI().addWindow(window);
    }
	
	/**
	 * ItemClickEvent is sent when an item on the calendar is clicked.
	 * @param event an event object containing information about the clicked item
	 */
	private void onCalendarItemClick(CalendarComponentEvents.ItemClickEvent event) {
		final AppointmentWindow window = new AppointmentWindow(this, (AppointmentItem) event.getCalendarItem(),patientItemList, doctorItemList);
		window.setModal(true);
		super.getUI().getUI().addWindow(window);;
	}
	
	/**
	 * DateClickEvent is sent when a date is clicked.
	 * @param event an event object containing information about the clicked date
	 */
	private void onDateClick(CalendarComponentEvents.DateClickEvent event) {
		BasicDateClickHandler basicDateClickHandler = new BasicDateClickHandler();
		basicDateClickHandler.dateClick(event);
	}
	
	/**
	 * ItemResizeEvent is sent when an item is resized
	 * @param event an event object containing information about the resized item
	 */
	private void onItemResize(CalendarComponentEvents.ItemResizeEvent event) {
		BasicItemResizeHandler basicItemResizeHandler = new BasicItemResizeHandler();
		basicItemResizeHandler.itemResize(event);
		saveAppointment((AppointmentItem)event.getCalendarItem());
	}
	
	/**
	 * ItemMoveEvent is sent when existing item is dragged to a new position.
	 * @param event an event object containing information about the moved item
	 */
	private void onItemMove(CalendarComponentEvents.ItemMoveEvent event) {
		BasicItemMoveHandler basicItemMoveHandler = new BasicItemMoveHandler();
		basicItemMoveHandler.itemMove(event);
		saveAppointment((AppointmentItem)event.getCalendarItem());
	}
	
	/**
	 * Method used to add all needed calendar handlers
	 */
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
	
	private void updatePatientItemList() {
		for (AgendaViewListener listener: listeners) {
			this.patientItemList = listener.setupPatientItemList();
		}
	}
	
	private void updateDoctorItemList() {
		for (AgendaViewListener listener: listeners) {
			this.doctorItemList = listener.setupDoctorItemList();
		}
	}
}

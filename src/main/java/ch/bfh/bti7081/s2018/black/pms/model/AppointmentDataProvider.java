package ch.bfh.bti7081.s2018.black.pms.model;

import org.vaadin.addon.calendar.item.BasicItemProvider;
import org.vaadin.addon.calendar.item.EditableCalendarItem.ItemChangedEvent;

/**
 * Appointment data provider to interact with the appointment items.
 */
public class AppointmentDataProvider extends BasicItemProvider<AppointmentItem>{
	
	@Override
	public void itemChanged(ItemChangedEvent event) {
		fireItemSetChanged();
	}
}

package ch.bfh.bti7081.s2018.black.pms.model;

import org.vaadin.addon.calendar.item.BasicItemProvider;
import org.vaadin.addon.calendar.item.EditableCalendarItem;
import org.vaadin.addon.calendar.item.EditableCalendarItem.ItemChangedEvent;

public class AppointmentDataProvider extends BasicItemProvider<AppointmentItem>{
	
	@Override
	public void itemChanged(ItemChangedEvent event) {
		System.out.println(event.getCalendarEvent().getCaption());
		fireItemSetChanged();
	}
	
}

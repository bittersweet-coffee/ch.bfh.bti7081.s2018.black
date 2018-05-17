package ch.bfh.bti7081.s2018.black.pms.model;


import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.vaadin.addon.calendar.item.BasicItem;

import com.vaadin.icons.VaadinIcons;

public class AppointmentItem extends BasicItem {

	private final Appointment appointment;
	
	public AppointmentItem(Appointment appointment) {
        super();
        this.appointment = appointment;
        if (appointment.getName() != null) {
        	setCaption(appointment.getName());
        }
        if (appointment.getDescription() != null) {
        	setCaption(appointment.getDescription());
        }
        setStart(ZonedDateTime.of(appointment.getStart(), ZoneId.systemDefault()));
        setEnd(ZonedDateTime.of(appointment.getEnd(), ZoneId.systemDefault()));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointment == null) ? 0 : appointment.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentItem other = (AppointmentItem) obj;
		if (appointment == null) {
			if (other.appointment != null)
				return false;
		} else if (!appointment.equals(other.appointment))
			return false;
		return true;
	}

	public Appointment getAppointment() {
		return this.appointment;
	}
	
	@Override
	public void setEnd(ZonedDateTime end) {
		appointment.setEnd(end.toLocalDateTime());
		super.setEnd(end);
	}

	@Override
	public void setStart(ZonedDateTime start) {
		appointment.setStart(start.toLocalDateTime());
		super.setStart(start);
	}
	
	@Override
    public String getDateCaptionFormat() {
        //return CalendarItem.RANGE_TIME;
        return VaadinIcons.CLOCK.getHtml()+" %s<br>" +
               VaadinIcons.ARROW_CIRCLE_RIGHT_O.getHtml()+" %s";
	}
	
	@Override
	public void setCaption(String caption) {
		appointment.setName(caption);
		super.setCaption(caption);
	}
	
	@Override
	public void setDescription(String description) {
		appointment.setDescription(description);
		super.setDescription(description);
	}
}

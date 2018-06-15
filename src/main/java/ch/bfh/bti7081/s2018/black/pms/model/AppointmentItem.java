package ch.bfh.bti7081.s2018.black.pms.model;


import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.vaadin.addon.calendar.item.BasicItem;

import com.vaadin.icons.VaadinIcons;

/**
 * Appointment item class
 */
public class AppointmentItem extends BasicItem {

	// Appointment
	private final Appointment appointment;
	
	public AppointmentItem(Appointment appointment) {
        super();
        this.appointment = appointment;
        if (appointment.getTitle() != null) {
        	setCaption(appointment.getTitle());
        }
        if (appointment.getDescription() != null) {
        	setDescription(appointment.getDescription());
        }
        setStart(ZonedDateTime.of(appointment.getStart(), ZoneId.systemDefault()));
        setEnd(ZonedDateTime.of(appointment.getEnd(), ZoneId.systemDefault()));
	}
	
	/**
	 * Some magic happens here.
	 * Only the best applications contain some magic. :)
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointment == null) ? 0 : appointment.hashCode());
		return result;
	}

	/**
	 * Comparison method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AppointmentItem other = (AppointmentItem) obj;
		if (appointment == null) {
			if (other.appointment != null) {
				return false;
			}
		} else if (!appointment.equals(other.appointment)) {
			return false;
		}
		return true;
	}

	/**
	 * Getter for the appointment
	 * @return the appointment
	 */
	public Appointment getAppointment() {
		return this.appointment;
	}
	
	/**
	 * Setter for the end date time
	 * @param the end date time
	 */
	@Override
	public void setEnd(ZonedDateTime end) {
		appointment.setEnd(end.toLocalDateTime());
		super.setEnd(end);
	}

	/**
	 * Setter for the start date time
	 * @param the start date time
	 */
	@Override
	public void setStart(ZonedDateTime start) {
		appointment.setStart(start.toLocalDateTime());
		super.setStart(start);
	}
	
	/**
	 * Getter for the formatted date caption
	 * @return formatted date caption
	 */
	@Override
    public String getDateCaptionFormat() {
        return VaadinIcons.CLOCK.getHtml()+" %s<br>" +
               VaadinIcons.ARROW_CIRCLE_RIGHT_O.getHtml()+" %s";
	}
	
	/**
	 * Setter for the caption
	 * @param the caption
	 */
	@Override
	public void setCaption(String caption) {
		appointment.setTitle(caption);
		super.setCaption(caption);
	}
	
	/**
	 * Setter for the description
	 * @param the description
	 */
	@Override
	public void setDescription(String description) {
		appointment.setDescription(description);
		super.setDescription(description);
	}
}

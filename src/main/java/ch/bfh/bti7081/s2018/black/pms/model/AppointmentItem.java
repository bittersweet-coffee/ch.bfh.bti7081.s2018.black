package ch.bfh.bti7081.s2018.black.pms.model;


import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.vaadin.addon.calendar.item.BasicItem;

public class AppointmentItem extends BasicItem {

	private final Appointment appointment;
	
	
	
	
	public AppointmentItem(Appointment appointment) {
        super(appointment.getTitle(), appointment.getDescription(), appointment.getStart(), appointment.getEnd());
        this.appointment = appointment;
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
		   this.appointment.setEndTime(LocalTime.of(end.getHour(), end.getMinute(), end.getSecond()));
		   super.setEnd(end);
		}

		@Override
		public void setStart(ZonedDateTime start) {
			this.appointment.setStartTime(LocalTime.of(start.getHour(), start.getMinute(), start.getSecond()));
			super.setStart(start);
		}
	
	
}

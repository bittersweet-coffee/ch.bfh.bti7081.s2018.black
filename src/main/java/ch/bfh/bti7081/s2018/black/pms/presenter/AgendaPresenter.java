package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import ch.bfh.bti7081.s2018.black.pms.model.Appointment;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentDataProvider;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorItem;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.view.AgendaView;
import ch.bfh.bti7081.s2018.black.pms.view.AgendaViewImpl;

/**
 * AgendaPresenter Class
 * Presenter Class used to manage data exchange between
 * Models and Views as well as triggering database queries.
 */
public class AgendaPresenter implements AgendaView.AgendaViewListener {

	private AgendaView view;
	
	private AppointmentDataProvider eventProvider = new AppointmentDataProvider();
	private List<AppointmentModel> appointmentModelList;
	
	private List<PatientItem> patientItemList = new LinkedList<>();
	private List<PatientModel> patientModelList;
	
	private List<DoctorItem> doctorItemList = new LinkedList<>();
	private List<DoctorModel> doctorModelList;

	/**
	 * Constructor for the AgendaPresenter
	 * Used to register itself as a listener in the corresponding view as well as initializing the AppointmentList,
	 * PatientList and DoctorList
	 * @param view Instance of the corresponding View
	 */
	public AgendaPresenter() {
		appointmentModelList = new LinkedList<AppointmentModel>();
		this.fillAppointmentList();
		this.patientModelList = new LinkedList<>();
		this.doctorModelList = new LinkedList<>();
	}

	/**
	 * Save the appointment
	 * @param the appointment item
	 */
	@Override
	public void saveAppointment(AppointmentItem appointmentItem) {
		if (appointmentItem.getAppointment().getId() == 0) {
			
			eventProvider.addItem(appointmentItem);
			AppointmentModel appointmentModel = new AppointmentModel();
			appointmentModel.setName(appointmentItem.getAppointment().getTitle());
			appointmentModel.setDescription(appointmentItem.getAppointment().getDescription());
			appointmentModel.setStart(appointmentItem.getAppointment().getStart());
			appointmentModel.setEnd(appointmentItem.getAppointment().getEnd());
			if (appointmentItem.getAppointment().getPatientItem() != null) {
				Optional<PatientModel> patientModel = JpaServicePresenter.findAll(PatientModel.class).stream()
						.filter(patient -> patient.getId() == appointmentItem.getAppointment()
						.getPatientItem().getId())
						.findFirst();
				
				if (patientModel.isPresent()) {
					
					appointmentModel.setPatient(patientModel.get());
					if (patientModel.get().getAppointments() == null) {
						List<AppointmentModel> appointmentList = new LinkedList<>();
						appointmentList.add(appointmentModel);
						patientModel.get().setAppointments(appointmentList);
					} else {
						patientModel.get().getAppointments().add(appointmentModel);
					}
				}
			}
			if (appointmentItem.getAppointment().getDoctorItem() != null) {
				
				Optional<DoctorModel> doctorModel = JpaServicePresenter.findAll(DoctorModel.class).stream()
						.filter(doctor -> doctor.getId() == appointmentItem.getAppointment()
						.getDoctorItem().getId())
						.findFirst();
				
				if (doctorModel.isPresent()) {
					appointmentModel.setDoctor(doctorModel.get());
				}
			}
			JpaServicePresenter.store(appointmentModel);
			appointmentItem.getAppointment().setId(appointmentModel.getId());
			appointmentModelList.add(appointmentModel);
		} else {
			for (AppointmentModel appointmentModel : this.appointmentModelList) {
				
				if (appointmentModel.getId() == appointmentItem.getAppointment().getId()) {
					appointmentModel.setName(appointmentItem.getAppointment().getTitle());
					appointmentModel.setDescription(appointmentItem.getAppointment().getDescription());
					appointmentModel.setStart(appointmentItem.getAppointment().getStart());
					appointmentModel.setEnd(appointmentItem.getAppointment().getEnd());
					Optional<PatientModel> patientModel = JpaServicePresenter.findAll(PatientModel.class).stream().filter(patien -> patien.getId() == appointmentItem.getAppointment().getPatientItem().getId()).findFirst();
					
					if (patientModel.isPresent()) {
						appointmentModel.setPatient(patientModel.get());
					}
					
					Optional<DoctorModel> doctorModel = JpaServicePresenter.findAll(DoctorModel.class).stream().filter(doctor -> doctor.getId() == appointmentItem.getAppointment().getDoctorItem().getId()).findFirst();
					if (doctorModel.isPresent()) {
						appointmentModel.setDoctor(doctorModel.get());
					}
					JpaServicePresenter.update(appointmentModel);
				}
			}
		}
	}
	
	@Override
	public void deleteAppointment(AppointmentItem appointmentItem) {
		eventProvider.removeItem(appointmentItem);
		if (appointmentItem.getAppointment().getId() != 0) {
			removeAppointment(appointmentItem.getAppointment());
		}
	}
	
	private void removeAppointment(Appointment appointment) {
		for (AppointmentModel appointmentModel : this.appointmentModelList) {
			if (appointmentModel.getId() == appointment.getId()) {
				JpaServicePresenter.remove(appointmentModel);
				appointmentModel.getPatient().getAppointments().remove(appointmentModel);
			}
     	}
	}
	
	/**
	 * Method used to query the database and fill the AppointmentItemList
	 * with representations/mockObjects from the AppointmentModels.
	 */
	public void fillAppointmentList() {
		this.appointmentModelList = JpaServicePresenter.findAll(AppointmentModel.class);
		for (AppointmentModel appointmentModel : this.appointmentModelList) {
			Appointment appointment = new Appointment();
			appointment.setId(appointmentModel.getId());
			appointment.setTitle(appointmentModel.getName());
			appointment.setDescription(appointmentModel.getDescription());
			appointment.setStart(appointmentModel.getStart());
			appointment.setEnd(appointmentModel.getEnd());
			if (appointmentModel.getPatient() != null) {
				PatientItem patientItem = new PatientItem(appointmentModel.getPatient());
				appointment.setPatientItem(patientItem);
			}
			if (appointmentModel.getDoctor() !=null) {
				DoctorItem doctorItem =  new DoctorItem(appointmentModel.getDoctor());
				appointment.setDoctorItem(doctorItem);
			}
			eventProvider.addItem(new AppointmentItem(appointment));
     	}
	}
	
	@Override
	public List<PatientItem> setupPatientItemList() {
		this.fillPatientList();
		return this.patientItemList;
	}
	
	/**
	 * Method used to query the database and fill the PatientItemList with representations/mockObjects from the PatientModels
	 */
	public void fillPatientList() {
		this.patientModelList = JpaServicePresenter.findAll(PatientModel.class);
		this.patientItemList = new LinkedList<>();
		for (PatientModel patient : this.patientModelList) {
			this.patientItemList.add(new PatientItem(patient));
		}
	}
	
	@Override
	public List<DoctorItem> setupDoctorItemList() {
		this.fillDoctorList();
		return this.doctorItemList;
	}

	/**
	 * Method used to query the database and fill the DoctorItemList with representations/mockObjects from the DoctorModels
	 */
	public void fillDoctorList() {
		this.doctorModelList = JpaServicePresenter.findAll(DoctorModel.class);
		this.doctorItemList = new LinkedList<>();
		for (DoctorModel doctor : this.doctorModelList) {
			this.doctorItemList.add(new DoctorItem(doctor));
		}
	}

	public void setupView(AgendaViewImpl agendaView) {
		this.view = agendaView;
		this.view.addListener(this);
		this.view.addEventProvider(eventProvider);
		
	}
}

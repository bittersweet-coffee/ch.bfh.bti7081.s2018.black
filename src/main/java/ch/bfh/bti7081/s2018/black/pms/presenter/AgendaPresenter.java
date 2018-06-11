package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.Appointment;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentDataProvider;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentItem;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorItem;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.AgendaView;

public class AgendaPresenter implements AgendaView.AgendaViewListener {

	private AgendaView view;
	
	private AppointmentDataProvider eventProvider = new AppointmentDataProvider();
	private List<AppointmentModel> appointmentModelList;
	
	private LocalDateTime start = LocalDateTime.now();
	private LocalDateTime end = LocalDateTime.now();
	
	private List<PatientItem> patientItemList = new LinkedList<>();
	private List<PatientModel> patientModelList;
	
	private List<DoctorItem> doctorItemList = new LinkedList<>();
	private List<DoctorModel> doctorModelList;
	
	public AgendaPresenter(AgendaView view) {
		this.view = view;
		this.view.addListener(this);
		this.view.addEventProvider(eventProvider);
		appointmentModelList = new LinkedList<AppointmentModel>();
		this.fillAppointmentList();
		this.patientModelList = new LinkedList<>();
		this.doctorModelList = new LinkedList<>();
	}

	@Override
	public void saveAppointment(AppointmentItem appointmentItem) {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		if (appointmentItem.getAppointment().getId() == 0) {
			eventProvider.addItem(appointmentItem);
			AppointmentModel appointmentModel = new AppointmentModel();
			appointmentModel.setName(appointmentItem.getAppointment().getTitle());
			appointmentModel.setDescription(appointmentItem.getAppointment().getDescription());
			appointmentModel.setStart(appointmentItem.getAppointment().getStart());
			appointmentModel.setEnd(appointmentItem.getAppointment().getEnd());
			if(appointmentItem.getAppointment().getPatientItem() != null) {
				Optional<PatientModel> patientModel = objects.findAll(PatientModel.class).stream().filter(patien -> patien.getId() == appointmentItem.getAppointment().getPatientItem().getId()).findFirst();
				if(patientModel.isPresent()) {
					appointmentModel.setPatient(patientModel.get());
				}
			}
			if(appointmentItem.getAppointment().getDoctorItem() != null) {
				Optional<DoctorModel> doctorModel = objects.findAll(DoctorModel.class).stream().filter(doctor -> doctor.getId() == appointmentItem.getAppointment().getDoctorItem().getId()).findFirst();
				if(doctorModel.isPresent()) {
					appointmentModel.setDoctor(doctorModel.get());
				}
			}
			objects.store(appointmentModel);
			appointmentItem.getAppointment().setId(objects.getLastId());
			appointmentModelList.add(appointmentModel);
		} else {
			for (AppointmentModel appointmentModel : this.appointmentModelList) {
				if (appointmentModel.getId() == appointmentItem.getAppointment().getId()) {
					appointmentModel.setName(appointmentItem.getAppointment().getTitle());
					appointmentModel.setDescription(appointmentItem.getAppointment().getDescription());
					appointmentModel.setStart(appointmentItem.getAppointment().getStart());
					appointmentModel.setEnd(appointmentItem.getAppointment().getEnd());
					Optional<PatientModel> patientModel = objects.findAll(PatientModel.class).stream().filter(patien -> patien.getId() == appointmentItem.getAppointment().getPatientItem().getId()).findFirst();
					if(patientModel.isPresent()) {
						appointmentModel.setPatient(patientModel.get());
					}
					Optional<DoctorModel> doctorModel = objects.findAll(DoctorModel.class).stream().filter(doctor -> doctor.getId() == appointmentItem.getAppointment().getDoctorItem().getId()).findFirst();
					if(doctorModel.isPresent()) {
						appointmentModel.setDoctor(doctorModel.get());
					}
					objects.update(appointmentModel);
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
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		for (AppointmentModel appointmentModel : this.appointmentModelList) {
			if (appointmentModel.getId() == appointment.getId()) {
				objects.remove(appointmentModel);
			}
     	}
	}
	
	public void fillAppointmentList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.appointmentModelList = objects.findAll(AppointmentModel.class);
		for (AppointmentModel appointmentModel : this.appointmentModelList) {
			Appointment appointment = new Appointment();
			appointment.setId(appointmentModel.getId());
			appointment.setTitle(appointmentModel.getName());
			appointment.setDescription(appointmentModel.getDescription());
			appointment.setStart(appointmentModel.getStart());
			appointment.setEnd(appointmentModel.getEnd());
			if(appointmentModel.getPatient() != null) {
				PatientItem patientItem = new PatientItem(appointmentModel.getPatient());
				appointment.setPatientItem(patientItem);
			}
			if(appointmentModel.getDoctor() !=null) {
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
	public void fillPatientList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.patientModelList = objects.findAll(PatientModel.class);
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
	public void fillDoctorList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.doctorModelList = objects.findAll(DoctorModel.class);
		this.doctorItemList = new LinkedList<>();
		for (DoctorModel doctor : this.doctorModelList) {
			this.doctorItemList.add(new DoctorItem(doctor));
		}
	}
}

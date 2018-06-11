package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.LocationModel;
import ch.bfh.bti7081.s2018.black.pms.model.NoticeModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.PatientView;

public class PatientPresenter implements PatientView.PatientViewListener {

	private PatientView view;
	private List<PatientModel> patientModelList;
	private List<PatientItem> patientItemList = new LinkedList<>();

	public PatientPresenter(PatientView view) {
		this.view = view;
		this.view.addListener(this);
		this.patientModelList = new LinkedList<>();
	}

	@Override
	public void saveButtonClick(PatientModel patient) {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.patientModelList = objects.findAll(PatientModel.class);
		objects.store(patient);
		this.patientItemList = new LinkedList<>();
		for (PatientModel p : this.patientModelList) {
			this.patientItemList.add(new PatientItem(p));
		}
	}

	/**
	 * Not used Anymore but left for debug, checks and junittests to get infos
	 * 
	 * @param patient
	 * @param objects
	 */
	private void addDummyData(PatientModel patient, JpaDataAccessObject objects) {
		List<PatientModel> pml = objects.findAll(PatientModel.class);
		PatientModel pmDummy = pml.get(0);
		patient.setAddictions(pmDummy.getAddictions());
		patient.setAppointments(pmDummy.getAppointments());
		patient.setDoctors(pmDummy.getDoctors());
		patient.setDrugs(pmDummy.getDrugs());
		patient.setLocation(pmDummy.getLocation());
		List<NoticeModel> nmDummy = new LinkedList<NoticeModel>();
		NoticeModel nDummy = new NoticeModel();
		nDummy.setNote("Dummy Note");
		nDummy.setPatient(patient);
		objects.store(nDummy);
		nmDummy.add(nDummy);
		patient.setNotes(nmDummy);
		patient.setNotice("Dummy notice");
		patient.setPostCode(1234);
		patient.setStreet("Dummy Street");
		patient.setTelephone("Dummy Phone");

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
	public List<PatientItem> setupPatientItemList() {
		this.fillPatientList();
		return this.patientItemList;
	}

	@Override
	public List<List<String>> openButtonClicked(Integer patientId, String patientName) {
		List<List<String>> wrapper = new LinkedList<>();
		List<String> addictionList = new LinkedList<>(); // fetch DB for assigned Addictions
		List<String> drugList = new LinkedList<>(); // fetch DB for assigned Drugs
		List<String> appointmentList = new LinkedList<>(); // fetch DB for assigned Appointments
		List<String> birthdayList = new LinkedList<>(); // fetch DB for Patient's Birthday

		wrapper.add(addictionList);
		wrapper.add(drugList);
		wrapper.add(appointmentList);
		wrapper.add(birthdayList);

		return wrapper;
	}

	@Override
	public void saveNoteButtonClicked(PatientItem patientItem, String newNote) {

		PatientModel patient = patientItem.getModel();

		NoticeModel note = new NoticeModel();
		note.setNote(newNote);
		note.setPatient(patient);

		JpaUtility t2 = new JpaUtility();
		JpaDataAccessObject ob2 = new JpaDataAccessObject(t2);
		ob2.update(note);

		patient.getNotes().add(note);
	}
	
	@Override
	public void saveButtonClicked(PatientItem patientItem, String newNote) {
		PatientModel patient = new PatientModel();
		patient.setFirstname(patientItem.getFirstName());
		patient.setLastname(patientItem.getLastName());
		patient.setStreet(patientItem.getStreet());
		patient.setTelephone(patientItem.getTelephone());
		patient.setPostCode(patientItem.getPostcode());
		patient.setAddictions(patientItem.getAddictions());
		patient.setDoctors(patientItem.getDoctors());
		patient.setDrugs(patientItem.getDrugs());
		patient.setLocation(patientItem.getLocation());
		
		NoticeModel note = new NoticeModel();
		note.setNote(newNote);
		note.setPatient(patient);
		List<NoticeModel> notes = new LinkedList<>();
		notes.add(note);
		patient.setNotes(notes);

		JpaUtility t2 = new JpaUtility();
		JpaDataAccessObject ob2 = new JpaDataAccessObject(t2);
		ob2.store(patient);
	}

	@Override
	public List<String> getNotesForPatient(Integer patientId) {
		List<String> patientNotes = new LinkedList<>(); // fetch DB for Patient Notes
		return patientNotes;
	}

	@Override
	public List<DoctorModel> getDoctors() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		return objects.findAll(DoctorModel.class);
	}

	@Override
	public List<LocationModel> getLocation() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		return objects.findAll(LocationModel.class);
	}

	@Override
	public List<AddictionModel> getAddictions() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		return objects.findAll(AddictionModel.class);
	}

	@Override
	public List<DrugModel> getDrugs() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		return objects.findAll(DrugModel.class);
	}
}

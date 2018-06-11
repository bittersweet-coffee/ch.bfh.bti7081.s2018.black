package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.AppointmentModel;
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
		JpaUtility t2 = new JpaUtility();
		JpaDataAccessObject ob2 = new JpaDataAccessObject(t2);
		PatientModel patient = new PatientModel();
		patient.setFirstname(patientItem.getFirstName());
		patient.setLastname(patientItem.getLastName());
		patient.setStreet(patientItem.getStreet());
		patient.setTelephone(patientItem.getTelephone());
		patient.setPostCode(patientItem.getPostcode());
		patient.setBirthday(patientItem.getBirthday());
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

		
		ob2.store(patient);
	}

	@Override
	public List<String> getNotesForPatient(Integer patientId) {
		List<String> patientNotes = new LinkedList<>(); // fetch DB for Patient Notes
		return patientNotes;
	}
}

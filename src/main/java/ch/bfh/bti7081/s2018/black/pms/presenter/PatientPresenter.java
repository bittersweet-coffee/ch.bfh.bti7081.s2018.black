package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.NoticeModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.view.PatientView;
import ch.bfh.bti7081.s2018.black.pms.view.PatientViewImpl;

/**
 * Patient presenter
 */
public class PatientPresenter implements PatientView.PatientViewListener {

	private PatientView view;
	private List<PatientModel> patientModelList;
	private List<PatientItem> patientItemList = new LinkedList<>();

	public PatientPresenter() {
		this.patientModelList = new LinkedList<>();
	}

	public void fillPatientList() {
		this.patientModelList = JpaServicePresenter.findAll(PatientModel.class);
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
	public void saveNoteButtonClicked(PatientItem patientItem, String newNote) {
		PatientModel patient = patientItem.getModel();
		NoticeModel note = new NoticeModel();
		note.setNote(newNote);
		note.setPatient(patient);
		JpaServicePresenter.update(note);
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
		patient.setBirthday(patientItem.getBirthday());
		patient.setAddictions(patientItem.getAddictions());
		patient.setDoctors(patientItem.getDoctors());
		patient.setClinic(patientItem.getClinic());	
		
		NoticeModel note = new NoticeModel();
		note.setNote(newNote);
		note.setPatient(patient);
		List<NoticeModel> notes = new LinkedList<>();
		notes.add(note);
		patient.setNotes(notes);
		JpaServicePresenter.store(patient);
	}

	@Override
	public List<String> getNotesForPatient(Integer patientId) {
		List<String> patientNotes = new LinkedList<>(); // fetch DB for Patient Notes
		return patientNotes;
	}

	public void setupView(PatientViewImpl patientView) {
		this.view = patientView;
		this.view.addListener(this);
	}
}

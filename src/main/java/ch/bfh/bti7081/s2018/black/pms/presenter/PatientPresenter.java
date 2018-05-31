package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.NoticeModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.util.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.util.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.PatientView;

public class PatientPresenter implements PatientView.PatientViewListener{
	
	private PatientView view;
	private List<PatientModel> patientModelList;
	private List<PatientItem> patientItemList = new LinkedList<>();

	public PatientPresenter(PatientView view) {
		this.view = view;
		this.patientModelList = new LinkedList<>();
		view.addListener(this);
	}

	@Override
	public void saveButtonClick(PatientItem patient) {
	}

	public void fillPatientList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.patientModelList = objects.findAll(PatientModel.class);
     	
		this.patientItemList = new LinkedList<>();
		for (PatientModel patient : this.patientModelList) {
			List<String> mockNotes = new LinkedList<>();
			for (NoticeModel note : patient.getNotes()) {
				System.out.println(note.getNote());
				mockNotes.add(note.getNote());
			}
			
			this.patientItemList.add(new PatientItem(patient.getId(), patient.getFirstname(), patient.getLastname(), mockNotes));
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
		List<String> addictionList = new LinkedList<>();		// fetch DB for assigned Addictions
		List<String> drugList = new LinkedList<>();				// fetch DB for assigned Drugs
		List<String> appointmentList = new LinkedList<>();		// fetch DB for assigned Appointments
		List<String> birthdayList = new LinkedList<>();			// fetch DB for Patient's Birthday 
		
		wrapper.add(addictionList);
		wrapper.add(drugList);
		wrapper.add(appointmentList);
		wrapper.add(birthdayList);
		
		return wrapper;
	}

	@Override
	public void saveNoteButtonClicked(PatientItem patientItem, String note) {
		
		JpaUtility t1 = new JpaUtility();
		JpaDataAccessObject ob1 = new JpaDataAccessObject(t1);
		PatientModel patient = (PatientModel) ob1.byid(patientItem.getId());
		
		NoticeModel notes = new NoticeModel();
		notes.setNote(note);
		notes.setPatient(patient);
		
		JpaUtility t2 = new JpaUtility();
		JpaDataAccessObject ob2 = new JpaDataAccessObject(t2);
		ob2.update(notes);
		
		//setupPatientItemList();
	}

	@Override
	public List<String> getNotesForPatient(Integer patientId) {
		
		List<String> patientNotes = new LinkedList<>();		// fetch DB for Patient Notes
		
		
		return patientNotes;
	}
}

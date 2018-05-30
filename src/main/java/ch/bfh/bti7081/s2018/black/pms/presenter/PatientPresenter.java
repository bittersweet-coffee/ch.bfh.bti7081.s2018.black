package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		this.fillPatientList();
	}

	@Override
	public void saveButtonClick(PatientItem patient) {
	}

	public void fillPatientList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.patientModelList = objects.findAll(PatientModel.class);
     	
		for (PatientModel patient : this.patientModelList) {
			List<String> mockNotes = new LinkedList<>();
			mockNotes.add("Note_1");
			mockNotes.add("NOte_2");
			this.patientItemList.add(new PatientItem(patient.getId(), patient.getFirstname(), patient.getLastname(), mockNotes));
     	}
	}

	@Override
	public List<PatientItem> setupPatientItemList() {
		
		/*
		for(int i=0; i<10; i++) {
			List<String> notes = new LinkedList<>();
			notes.add("Note1");
			notes.add("Note2");
			PatientItem temp = new PatientItem(i, "First_" + i, "Last_" + i*3, notes);
			this.patientItemList.add(temp);
			
		}
		*/
		
		
		
		return this.patientItemList;
	}

	@Override
	public Map<Integer, String> searchButtonClicked(String searchTerm) {
		Map<Integer, String> optionalPatient = this.patientModelList.stream()
				.filter(patient -> patient.getFirstname().toLowerCase().contains(searchTerm.toLowerCase()) || 
						patient.getLastname().toLowerCase().contains(searchTerm.toLowerCase())
						)
				.collect(Collectors.toMap(PatientModel::getId, patient -> patient.getFirstname() + ", " + patient.getLastname()));
		
		return optionalPatient;
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
	public void saveNoteButtonClicked(Integer patientId, String note) {
		
		// put this information into the DB
		System.out.println("ID: " + patientId + "\nNote: " + note);
	}

	@Override
	public List<String> getNotesForPatient(Integer patientId) {
		
		List<String> patientNotes = new LinkedList<>();		// fetch DB for Patient Notes
		
		
		return patientNotes;
	}
}

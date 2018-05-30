package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.HashMap;
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
	private Map<Integer, String> patientNameList = new HashMap<>();

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
			this.patientNameList.put(patient.getId(), patient.getFirstname() + ", " + patient.getLastname());
     	}
	}

	@Override
	public Map<Integer, String> setupPatientList() {
		return this.patientNameList;
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
}

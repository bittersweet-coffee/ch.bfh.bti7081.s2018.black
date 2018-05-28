package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     		//this.patientNameList.add(patient.getFirstname() + ", " + patient.getLastname());
     		System.out.println(patient.getFirstname() + " " + patient.getLastname());
     	}
	}

	@Override
	public void setupPatientList() {
		//this.view.setupAddictList(this.addictNameList);
		this.view.setupPatientList(this.patientNameList);
	}



	@Override
	public void searchButtonClicked(String searchTerm) {
		Map<Integer, String> optionalPatient = this.patientModelList.stream()
				.filter(patient -> patient.getFirstname().toLowerCase().contains(searchTerm.toLowerCase()) || patient.getLastname().toLowerCase().contains(searchTerm.toLowerCase()))
				//.flatMap(patient -> Stream.of(patient.getId(), patient.getFirstname() + ", " + patient.getLastname()))
				.collect(Collectors.toMap(PatientModel::getId, patient -> patient.getFirstname() + ", " + patient.getLastname()));
		
		System.out.println(optionalPatient.toString());
		this.view.setupPatientList(optionalPatient);
	}
	
	/*
	@Override
	public void selectListChanged(String firstName, String lastName) {
		Optional<PatientModel> optionalPatient = this.patientModelList.stream()
			.filter(patient -> patient.getFirstname().equals(firstName))
			.findAny();
			
		if(optionalPatient.isPresent()) {
			this.view.setListDesc(optionalPatient.get().getDescription());
			this.view.setListSymptoms(optionalPatient.get().getSymptomsAsString());
		}
	}
	
	*/
}

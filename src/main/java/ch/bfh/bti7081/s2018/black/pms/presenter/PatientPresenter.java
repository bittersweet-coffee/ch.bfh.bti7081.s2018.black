package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.util.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.util.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.PatientView;

public class PatientPresenter implements PatientView.PatientViewListener{
	
	private PatientView view;
	private PatientModel model;
	private List<PatientModel> patientModelList;
	private List<String> patientfirstnameList = new LinkedList<>();
	private List<String> patientlastnameList = new LinkedList<>();


	public PatientPresenter(PatientView view, PatientModel model) {
		this.view = view;
		this.model = model;
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
     		this.patientfirstnameList.add(patient.getFirstname());
     		this.patientlastnameList.add(patient.getLastname());
     	}
	}
	
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
}

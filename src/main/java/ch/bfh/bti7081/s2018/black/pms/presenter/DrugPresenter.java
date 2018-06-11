package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.Pair;
import ch.bfh.bti7081.s2018.black.pms.model.PatientDrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.DrugView;

public class DrugPresenter implements DrugView.DrugViewListener {
	
	
	private DrugView view;
	private List<DrugModel> drugModelList;
	private List<String> drugNameList = new LinkedList<>();
	private List<PatientModel> patientModelList;
	private List<PatientItem> patientItemList = new LinkedList<>();
	
	public DrugPresenter(DrugView view) {
		this.view = view;
		view.addListener(this);
		this.drugModelList = new LinkedList<>();
		this.fillDrugList();
		
		System.out.println("(0): " + this.drugModelList.get(0).getName());
		this.drugModelList.get(0).setMeasure("Integer");
		this.drugModelList.get(0).setMinDose(new Double(1));
		this.drugModelList.get(0).setMaxDose(new Double(10));
		
		System.out.println("(1): " + this.drugModelList.get(1).getName());
		this.drugModelList.get(1).setMeasure("Halves");
		this.drugModelList.get(1).setMinDose(new Double(0.5));
		this.drugModelList.get(1).setMaxDose(new Double(15.5));
		
		System.out.println("(2): " + this.drugModelList.get(2).getName());
		this.drugModelList.get(2).setMeasure("Double");
		this.drugModelList.get(2).setMinDose(new Double(0.125));
		this.drugModelList.get(2).setMaxDose(new Double(1.75));


		Pair result = this.drugModelList.get(0).checkDose(new Double(3.2));
		System.out.println("\nFailure expected");
		System.out.println("\tResult: " + result.getResult() + "\n\tMessage: " + result.getMessage());
		
		result = this.drugModelList.get(0).checkDose(new Double(3.0));
		System.out.println("\n\nSuccess expected");
		System.out.println("\tResult: " + result.getResult() + "\n\tMessage: " + result.getMessage());
	}
	
	public void fillDrugList() {
		
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.drugModelList = objects.findAll(DrugModel.class);
		
		for (DrugModel drug : this.drugModelList) {
     		this.drugNameList.add(drug.getName());
     	}
		
	}
	
	
	@Override
	public List<String> searchButtonClicked(String searchTerm) {
		List<String> optionalDrug = this.drugModelList.stream()
				.filter(drug -> drug.getName().toLowerCase().contains(searchTerm.toLowerCase()))
				.map(DrugModel::getName)
				.collect(Collectors.toList());
			
		return optionalDrug;
	}
	
	@Override
	public List<String> addToButtonClicked() {
		
		List<String> patientList = new LinkedList<>();

		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.patientModelList = objects.findAll(PatientModel.class);
     	
		this.patientItemList = new LinkedList<>();
		for (PatientModel patient : this.patientModelList) {
			patientList.add(patient.getFirstname() + " " + patient.getLastname());
     	}

	// These are Mock Objects because Patient Management isn't ready yet
		//patientList.add("Toni Donato");
		//patientList.add("Nico Schlup");
		//patientList.add("Cederik Bielmann");
		//patientList.add("Michi Hofer");
		//patientList.add("Jan Henzi");
		
		//
		//
		// Put Logic in here once Patient Management is ready
		//
		//
		
		return patientList;
	}
	
	@Override
	public Pair allocateButtonClicked(String drugName, PatientItem patientItem, Double dose) {
		Optional<DrugModel> optionalDrug = this.drugModelList.stream()
				.filter(drug -> drug.getName().equals(drugName))
				.findFirst();
		
		Pair result = new Pair();
		
		if(optionalDrug.isPresent()) {
			result = optionalDrug.get().checkDose(dose);
			
			// Check if dose is within DoseBounds
			if (result.getResult()) {
				
				// Check if Drug can be successfully allocated to the patient
				if(allocateDrugToPatient(optionalDrug.get(), patientItem.getModel())) {
					return result;
					
				// Drug couldn't be allocated to the patient
				} else {
					result.put(false, "The selected drug has already been prescribed to the patient!");
				}
			} 
		}
		
		return result;
	}
	
	
	private boolean allocateDrugToPatient(DrugModel drug, PatientModel patient) {
		Optional<PatientDrugModel> drugList = patient.getDrugs().stream()
				.filter(d -> d.getDrug().getId() == drug.getId())
				.findFirst();
		
		System.out.println(drugList);
		if(!drugList.isPresent()) {
			PatientDrugModel test = new PatientDrugModel();
			test.setPatient(patient);
			test.setDrug(drug);
			test.setDose(3);
			patient.getDrugs().add(test);
			drug.getPatients().add(test);
			JpaUtility transaction = new JpaUtility();
			JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
			objects.store2(test);
			return true;
		}
		return false;
	}
	
	public void addDrug(DrugModel drug) {
		this.drugModelList.add(drug);
	}
	
	public List<DrugModel> getDrugModelList() {
		return this.drugModelList;
	}
	
	
	@Override
	public List<String> selectListChanged(String drugName) {
		Optional<DrugModel> optionalDrug = this.drugModelList.stream()
			.filter(drug -> drug.getName().equals(drugName))
			.findFirst();
			
		List<String> drugDetails = new LinkedList<>();
		
		if(optionalDrug.isPresent()) {
			drugDetails.add(optionalDrug.get().getDescription());
		} else {
			drugDetails.add("No Description present");
			drugDetails.add("No Symptoms present");
		}
		
		return drugDetails;
	}
	
	@Override
	public List<String> setupDrugList() {
		return this.drugNameList;
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
}

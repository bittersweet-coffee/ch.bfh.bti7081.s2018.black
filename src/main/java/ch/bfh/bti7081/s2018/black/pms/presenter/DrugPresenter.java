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
			
			// check whether the patient has already allocated the selected drug 
			if (checkAllocation(optionalDrug.get(), patientItem.getModel())) {
				
				// Check if dose is within DoseBounds
				if(result.getResult()) {
					allocateDrugToPatient(optionalDrug.get(), patientItem.getModel(), dose);
					
					// Drug has been successfully allocated to the patient
					return result;
				} else {
					// Drug isn't within DoseBounds
					return result;
				}
			} else {
				// Drug couldn't be allocated to the patient
				result.put(false, "The selected drug has already been prescribed to the patient!");
			}
		}
		return result;
	}
	
	private boolean checkAllocation(DrugModel drug, PatientModel patient) {
		Optional<PatientDrugModel> drugList = patient.getDrugs().stream()
				.filter(d -> d.getDrug().getId() == drug.getId())
				.findFirst();
		
		if(drugList.isPresent()) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean allocateDrugToPatient(DrugModel drug, PatientModel patient, Double dose) {
		
		PatientDrugModel model = new PatientDrugModel();
		model.setPatient(patient);
		model.setDrug(drug);
		model.setDose(dose);
		patient.getDrugs().add(model);
		drug.getPatients().add(model);
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		objects.store(model);
		return true;
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

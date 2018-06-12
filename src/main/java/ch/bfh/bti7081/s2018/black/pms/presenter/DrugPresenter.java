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

/**
 * DrugPresenter Class
 * Presenter Class used to manage data exchange between Models and Views as well as triggering database queries
 * @author schaa4
 *
 */
public class DrugPresenter implements DrugView.DrugViewListener {

	private DrugView view;
	private List<DrugModel> drugModelList;
	private List<String> drugNameList = new LinkedList<>();
	private List<PatientModel> patientModelList;
	private List<PatientItem> patientItemList = new LinkedList<>();
	
	/**
	 * Constructor for the DrugPresenter
	 * Used to register itself as a listener in the corresponding view as well as initializing the DrugList
	 * @param view Instance of the corresponding View
	 */
	public DrugPresenter(DrugView view) {
		this.view = view;
		view.addListener(this);
		this.drugModelList = new LinkedList<>();
		this.fillDrugList();
	}
	
	/**
	 * Helper Method used to tell whether a Patient has already prescribed the selected drug
	 * @param drug The DrugModel which has to be allocated to the patient
	 * @param patient The PatientModel to whom the Drug has to be allocated
	 * @return boolean response whether an allocation is possible otherwise the drug is already allocated to the patient
	 */
	private boolean checkAllocation(DrugModel drug, PatientModel patient) {
		Optional<PatientDrugModel> drugList = Optional.empty();
		
		if (patient.getDrugs() == null) {
			return true;
		}
		
		drugList = patient.getDrugs().stream()
				   .filter(d -> d.getDrug().getId() == drug.getId())
				   .findFirst();
		
		if(drugList.isPresent()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Method to allocate a Drug to a Patient
	 * @param drug The DrugModel which has to be allocated to the patient
	 * @param patient The PatientModel to whom the Drug has to be allocated to
	 * @param dose The dose of the Drug the Patient needs to take
	 * @return boolean response whether the allocation could be performed
	 */
	private boolean allocateDrugToPatient(DrugModel drug, PatientModel patient, Double dose) {
		PatientDrugModel model = new PatientDrugModel();
		model.setPatient(patient);
		model.setDrug(drug);
		model.setDose(dose);
		if(patient.getDrugs() == null) {
			List<PatientDrugModel> list = new LinkedList<>();
			list.add(model);
			patient.setDrugs(list);
		}
		else {
			patient.getDrugs().add(model);
		}
		drug.getPatients().add(model);
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		objects.store(model);
		return true;
	}
	
	/**
	 * Method used to query the database and fill the DrugModelList with all DrugModels from the database
	 */
	public void fillDrugList() {
		
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.drugModelList = objects.findAll(DrugModel.class);
		
		for (DrugModel drug : this.drugModelList) {
     		this.drugNameList.add(drug.getName());
     	}
		
	}
	
	/**
	 * Method used to query the database and fill the PatientItemList with representations/mockObjects from the PatientModels
	 */
	private void fillPatientList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.patientModelList = objects.findAll(PatientModel.class);
     	
		this.patientItemList = new LinkedList<>();
		for (PatientModel patient : this.patientModelList) {
			this.patientItemList.add(new PatientItem(patient));
     	}
	}
	/*
	public void addDrug(DrugModel drug) {
		this.drugModelList.add(drug);
	}
	
	public List<DrugModel> getDrugModelList() {
		return this.drugModelList;
	}
	*/
	@Override
	public List<String> searchButtonClicked(String searchTerm) {
		List<String> optionalDrug = this.drugModelList.stream()
				.filter(drug -> drug.getName().toLowerCase().contains(searchTerm.toLowerCase()))
				.map(DrugModel::getName)
				.collect(Collectors.toList());
			
		return optionalDrug;
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
}

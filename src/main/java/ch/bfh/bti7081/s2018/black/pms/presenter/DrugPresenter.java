package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.Pair;
import ch.bfh.bti7081.s2018.black.pms.model.PatientDrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.view.DrugView;
import ch.bfh.bti7081.s2018.black.pms.view.DrugViewImpl;

/**
 * DrugPresenter Class
 * Presenter Class used to manage data exchange between Models and Views as well as triggering database queries
 */
public class DrugPresenter implements DrugView.DrugViewListener {
	
	final static Logger logger = Logger.getLogger(DrugPresenter.class);

	private DrugView view;
	private List<DrugModel> drugModelList;
	private List<String> drugNameList = new LinkedList<>();
	private List<PatientModel> patientModelList;
	private List<PatientItem> patientItemList = new LinkedList<>();
	
	/**
	 * Constructor for the DrugPresenter
	 * Used to register itself as a listener in the corresponding view as well as initializing the DrugList
	 */
	public DrugPresenter() {
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
		
		if (drugList.isPresent()) {
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
		if (patient.getDrugs() == null) {
			List<PatientDrugModel> list = new LinkedList<>();
			list.add(model);
			patient.setDrugs(list);
		} else {
			patient.getDrugs().add(model);
		}
		drug.getPatients().add(model);
		JpaServicePresenter.store(model);
		return true;
	}
	
	/**
	 * Method used to query the database and fill the DrugModelList with all DrugModels from the database
	 */
	public void fillDrugList() {
		this.drugModelList = JpaServicePresenter.findAll(DrugModel.class);
		
		for (DrugModel drug : this.drugModelList) {
     		this.drugNameList.add(drug.getName());
     	}
	}
	
	/**
	 * Method used to query the database and fill the PatientItemList with
	 * representations/mockObjects from the PatientModels.
	 */
	private void fillPatientList() {
		this.patientModelList = JpaServicePresenter.findAll(PatientModel.class);
     	
		this.patientItemList = new LinkedList<>();
		for (PatientModel patient : this.patientModelList) {
			this.patientItemList.add(new PatientItem(patient));
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
	public Pair allocateButtonClicked(String drugName, PatientItem patientItem, Double dose) {
		Optional<DrugModel> optionalDrug = this.drugModelList.stream()
				.filter(drug -> drug.getName().equals(drugName))
				.findFirst();
		
		Pair result = new Pair();
		
		if (optionalDrug.isPresent()) {
			
			result = optionalDrug.get().checkDose(dose);
			
			// Check whether the patient has already allocated the selected drug 
			if (checkAllocation(optionalDrug.get(), patientItem.getModel())) {
				
				// Check if dose is within DoseBounds
				if (result.getResult()) {
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
		
		if (optionalDrug.isPresent()) {
			drugDetails.add(optionalDrug.get().getDescription());
			drugDetails.add(optionalDrug.get().getMeasure());
			drugDetails.add("ml");
			drugDetails.add(optionalDrug.get().getMinDose().toString());
			drugDetails.add(optionalDrug.get().getMaxDose().toString());
		} else {
			drugDetails.add("No Description present");
			drugDetails.add("No Symptoms present");
			drugDetails.add("No Min Dose present");
			drugDetails.add("No Max Dose present");
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

	@Override
	public boolean isDouble(String str) {
		  try {
			// try to parse entered Dose to Double
		    Double.parseDouble(str);
		    
		    // No exception thrown to this point, so it is Double-parsable
		    // Check if entered number has less than 7 decimal places
		    if (str.contains(".")) {
		    	String[] splitted = str.split("\\.");
		    	
		    	if (splitted[1].length() < 6) {
		    		return true;
		    	} else {
		    		return false;
		    	}
		    }
		    	
		    return true;
		    
		  } catch(Exception e) {
			logger.error(e);
		    return false;
		  }
	}

	public void setupView(DrugViewImpl drugView) {
		this.view = drugView;
		view.addListener(this);
	}
}

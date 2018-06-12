package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.AddictionView;

/**
 * AddictionPresenter Class
 * Presenter Class used to manage data exchange between Models and Views as well as triggering database queries
 * @author schaa4
 */
public class AddictionPresenter implements AddictionView.AddictionViewListener {

	private AddictionView view;
	private List<AddictionModel> addictModelList;
	private List<String> addictNameList = new LinkedList<>();
	private List<PatientModel> patientModelList;
	private List<PatientItem> patientItemList = new LinkedList<>();
	
	/**
	 * Constructor for the AddictionPresenter
	 * Used to register itself as a listener in the corresponding view as well as initializing the AddictionList
	 * @param view Instance of the corresponding View
	 */
	public AddictionPresenter(AddictionView view) {
		this.view = view;
		this.addictModelList = new LinkedList<>();
		view.addListener(this);
		this.fillAddictionList();
	}
	
	/**
	 * Method to allocate an Addiction to a Patient
	 * @param addict The AddictionModel which has to be allocated to the patient
	 * @param patient The PatientModel to whom the Addiction has to be allocated to
	 * @return boolean response whether the allocation could be performed
	 */
	private boolean allocateAddictionToPatient(AddictionModel addict, PatientModel patient) {
		Optional<AddictionModel> addictList = patient.getAddictions().stream()
				.filter(a -> a.getId() == addict.getId())
				.findFirst();
		if(!addictList.isPresent()) {
			patient.getAddictions().add(addict);
			JpaUtility transaction = new JpaUtility();
			JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
			objects.update(patient);
			return true;
		}
		return false;
	}
	
	/**
	 * Method used to query the database and fill the AddictionModelList with all AddictionModels from the database
	 */
	public void fillAddictionList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.addictModelList = objects.findAll(AddictionModel.class);
     	
		for (AddictionModel addict : this.addictModelList) {
     		this.addictNameList.add(addict.getName());
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
	
	public void addAddiction(AddictionModel addiction) {
		this.addictModelList.add(addiction);
	}
	
	public List<AddictionModel> getAddictionModelList() {
		return this.addictModelList;
	}

	@Override
	public List<String> searchButtonClicked(String searchTerm) {
		List<String> optionalAddict = this.addictModelList.stream()
				.filter(addict -> addict.getName().toLowerCase().contains(searchTerm.toLowerCase()))
				.map(AddictionModel::getName)
				.collect(Collectors.toList());
			
		return optionalAddict;
	}
	
	@Override
	public boolean allocateButtonClicked(String addictionName, PatientItem patientItem) {
		Optional<AddictionModel> optionalAddict = this.addictModelList.stream()
				.filter(addict -> addict.getName().equals(addictionName))
				.findFirst();
		
		if(optionalAddict.isPresent()) {
			return allocateAddictionToPatient(optionalAddict.get(), patientItem.getModel());
		}
		return false;
	}
	
	@Override
	public List<String> selectListChanged(String addictionName) {
		Optional<AddictionModel> optionalAddict = this.addictModelList.stream()
			.filter(addict -> addict.getName().equals(addictionName))
			.findFirst();
			
		List<String> addictionDetails = new LinkedList<>();
		
		if(optionalAddict.isPresent()) {
			addictionDetails.add(optionalAddict.get().getDescription());
			addictionDetails.add(optionalAddict.get().getSymptomsAsString());
		} else {
			addictionDetails.add("No Description present");
			addictionDetails.add("No Symptoms present");
		}
		
		return addictionDetails;
	}
	
	@Override
	public List<String> setupAddictList() {
		return this.addictNameList;
	}

	@Override
	public List<PatientItem> setupPatientItemList() {
		this.fillPatientList();
		return this.patientItemList;
	}

}

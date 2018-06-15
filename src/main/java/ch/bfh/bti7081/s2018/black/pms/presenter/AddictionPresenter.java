package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionItem;
import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.view.AddictionView;
import ch.bfh.bti7081.s2018.black.pms.view.AddictionViewImpl;

/**
 * AddictionPresenter Class
 * Presenter Class used to manage data exchange between Models and
 * Views as well as triggering database queries.
 */
public class AddictionPresenter implements AddictionView.AddictionViewListener {

	private AddictionView view;
	private List<AddictionModel> addictModelList;
	private List<String> addictNameList = new LinkedList<>();
	private List<PatientModel> patientModelList;
	private List<PatientItem> patientItemList = new LinkedList<>();
	
	/**
	 * Constructor for the AddictionPresenter
	 * Used to register itself as a listener in the corresponding
	 * view as well as initializing the AddictionList.
	 */
	public AddictionPresenter() {
		this.addictModelList = new LinkedList<>();
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
		if (!addictList.isPresent()) {
			patient.getAddictions().add(addict);
			JpaServicePresenter.update(patient);
			return true;
		}
		return false;
	}
	
	/**
	 * Method used to query the database and fill the AddictionModelList
	 * with all AddictionModels from the database.
	 */
	public void fillAddictionList() {
		this.addictModelList = JpaServicePresenter.findAll(AddictionModel.class);
     	
		for (AddictionModel addict : this.addictModelList) {
     		this.addictNameList.add(addict.getName());
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

	/**
	 * Logic when the search button was clicked
	 * @param search string
	 */
	@Override
	public List<String> searchButtonClicked(String searchTerm) {
		List<String> optionalAddict = this.addictModelList.stream()
				.filter(addict -> addict.getName().toLowerCase().contains(searchTerm.toLowerCase()))
				.map(AddictionModel::getName)
				.collect(Collectors.toList());
			
		return optionalAddict;
	}
	
	/**
	 * Logic when the allocate button was clicked
	 * @param addiction name
	 * @param patient item
	 */
	@Override
	public boolean allocateButtonClicked(String addictionName, PatientItem patientItem) {
		Optional<AddictionModel> optionalAddict = this.addictModelList.stream()
				.filter(addict -> addict.getName().equals(addictionName))
				.findFirst();
		
		if (optionalAddict.isPresent()) {
			return allocateAddictionToPatient(optionalAddict.get(), patientItem.getModel());
		}
		return false;
	}
	
	/**
	 * Logic when the select element in the list changed
	 * @param addiction name
	 */
	@Override
	public List<String> selectListChanged(String addictionName) {
		Optional<AddictionModel> optionalAddict = this.addictModelList.stream()
			.filter(addict -> addict.getName().equals(addictionName))
			.findFirst();
			
		List<String> addictionDetails = new LinkedList<>();
		
		if (optionalAddict.isPresent()) {
			addictionDetails.add(optionalAddict.get().getDescription());
			addictionDetails.add(optionalAddict.get().getSymptomsAsString());
		} else {
			addictionDetails.add("No Description present");
			addictionDetails.add("No Symptoms present");
		}
		
		return addictionDetails;
	}
	
	/**
	 * Setup the addict list
	 * @return list of addicts
	 */
	@Override
	public List<String> setupAddictList() {
		return this.addictNameList;
	}

	/**
	 * Setup the patient item list
	 * @return patient item list
	 */
	@Override
	public List<PatientItem> setupPatientItemList() {
		this.fillPatientList();
		return this.patientItemList;
	}

	/**
	 * Setup the view
	 * @param addictionView
	 */
	public void setupView(AddictionViewImpl addictionView) {
		this.view = addictionView;
		view.addListener(this);
	}

	/**
	 * Getter for the addiction items
	 * @return addiction item list
	 */
	public static List<AddictionItem> getAddictionItems() {
		List<AddictionModel> addictionModelList = JpaServicePresenter.findAll(AddictionModel.class);
		List<AddictionItem> addictionItemList = new LinkedList<AddictionItem>();
		for (AddictionModel addiction : addictionModelList) {
			AddictionItem a = new AddictionItem();
			a.setName(addiction.getName());
			addictionItemList.add(a);
		}
		return addictionItemList;
	}

	/**
	 * Setter for the addiction to patients relation
	 * @param selectedItems
	 * @param patient
	 */
	public static void setAddictionsToPatient(Set<String> selectedItems, PatientItem patient) {
		LinkedList<AddictionModel> addictionListAdd = new LinkedList<AddictionModel>();
		List<AddictionModel> addictionModelList = JpaServicePresenter.findAll(AddictionModel.class);
		for (String string : selectedItems) {
			for (AddictionModel addictionModel : addictionModelList) {
				if (string.equals(addictionModel.getName())) {
					addictionListAdd.add(addictionModel);
				}
			}
		}
		patient.setAddictions(addictionListAdd);
	}
}

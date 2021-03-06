package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.ClinicItem;
import ch.bfh.bti7081.s2018.black.pms.model.ClinicModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.view.ClinicView;
import ch.bfh.bti7081.s2018.black.pms.view.ClinicViewImpl;

/**
 * ClinicPresenter Class
 * Presenter Class used to manage data exchange between Models and Views as well as triggering database queries
 */
public class ClinicPresenter implements ClinicView.ClinicViewListener{
	
	private ClinicView view;
	private List<ClinicModel> clinicModelList;
	private List<String> clinicNameList = new LinkedList<>();
	
	/**
	 * Constructor for the ClinicPresenter
	 * Used to register itself as a listener in the corresponding view as well as initializing the ClinicList
	 */
	public ClinicPresenter(ClinicView view) {
		this.view = view;
	}
	
	public ClinicPresenter() {
		this.clinicModelList = new LinkedList<>();
		this.fillClinicList();
	}
	
	/**
	 * Method to add a new clinic to the list
	 */
	public void addClinic(ClinicModel clinic) {
		this.clinicModelList.add(clinic);
	}
	
	/**
	 * Method to return the actual clinic list
	 */
	public List<ClinicModel> getClinicModelList() {
		return this.clinicModelList;
	}
	
	/**
	 * Method used to query the database and fill the ClinicModelList with all ClinicModels from the database
	 */
	private void fillClinicList() {
		this.clinicModelList = JpaServicePresenter.findAll(ClinicModel.class);
     	
		for (ClinicModel clinic : this.clinicModelList) {
     		this.clinicNameList.add(clinic.getName());
     	}
	}

	/**
	 * Logic when the search button was clicked.
	 * Gather information from the different resources and present them accordingly.
	 * @param search term
	 * @param search mode
	 */
	@Override
	public List<String> searchButtonClicked(String searchTerm, String searchMode) {
		
		List<String> optionalClinic = new LinkedList<>();
		
		if (!searchTerm.isEmpty()) {
			if (searchMode.equals("Clinic")) {
				optionalClinic = this.clinicModelList.stream()
						.filter(clinic -> clinic.getName().toLowerCase().contains(searchTerm.toLowerCase()))
						.map(ClinicModel::getName)
						.collect(Collectors.toList());
				
			} else if (searchMode.equals("Addiction")) {
				
				List<AddictionModel> addictionList = new LinkedList<>();
				
				for (ClinicModel clinic : this.clinicModelList) {
					addictionList = clinic.getAddictions().stream()
							.filter(addiction -> addiction.getName().toLowerCase().contains(searchTerm.toLowerCase()))
							.collect(Collectors.toList());
					
					if (!addictionList.isEmpty()) {
						optionalClinic.add(clinic.getName());
					}
				}
			}
				
			return optionalClinic;

		} else {
			return this.clinicNameList;
		}
	}

	/**
	 * Logic when the selected list changed
	 * @param clinic name
	 */
	@Override
	public List<String> selectListChanged(String clinicName) {
		Optional<ClinicModel> optionalClinic = this.clinicModelList.stream()
				.filter(clinic -> clinic.getName().equals(clinicName))
				.findFirst();
				
			List<String> clinicDetails = new LinkedList<>();
			
			if (optionalClinic.isPresent()) {
				clinicDetails.add(optionalClinic.get().getPlace());
				clinicDetails.add(String.valueOf(optionalClinic.get().getPostCode()));
				clinicDetails.add(optionalClinic.get().getStreet());
				clinicDetails.add(optionalClinic.get().getTelephone());
				clinicDetails.add(optionalClinic.get().getemail());
				clinicDetails.add(optionalClinic.get().getAddictionsAsString());
			} else {
				clinicDetails.add("No City present");
				clinicDetails.add("No PostCode present");
				clinicDetails.add("No Street present");
				clinicDetails.add("No Telephone present");
				clinicDetails.add("No E-Mail present");
				clinicDetails.add("No Addictions present");
			}
			
			return clinicDetails;
	}

	@Override
	public List<String> setupClinicList() {
		return this.clinicNameList;
	}

	public void setupView(ClinicViewImpl clinicView) {
		this.view = clinicView;
		this.view.addListener(this);
	}

	public static List<ClinicItem> getClinicNames() {
		List<ClinicModel> clinicModelList = JpaServicePresenter.findAll(ClinicModel.class);
		List<ClinicItem> clinicItemList = new LinkedList<ClinicItem>();
		for (ClinicModel clinic : clinicModelList) {
			ClinicItem c = new ClinicItem();
			c.setName(clinic.getName());
			clinicItemList.add(c);
		}
		return clinicItemList;
	}

	public static void setupClinic(Optional<String> clinic, PatientItem patient) {
		List<ClinicModel> clinicModelList = JpaServicePresenter.findAll(ClinicModel.class);
		for (ClinicModel clinicModel : clinicModelList) {
			if (clinic.get().equals(clinicModel.getName())) {
				patient.setClinic(clinicModel);
			}
		}
	}
}

package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.ClinicModel;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.ClinicView;

public class ClinicPresenter implements ClinicView.ClinicViewListener{
	
	private ClinicView view;
	private List<ClinicModel> clinicModelList;
	private List<String> clinicNameList = new LinkedList<>();
	
	public ClinicPresenter(ClinicView view) {
		this.view = view;
		this.clinicModelList = new LinkedList<>();
		view.addListener(this);
		this.fillClinicList();
	}
	
	public void addClinic(ClinicModel clinic) {
		this.clinicModelList.add(clinic);
	}
	
	public List<ClinicModel> getAddictionModelList() {
		return this.clinicModelList;
	}
	
	public void fillClinicList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.clinicModelList = objects.findAll(ClinicModel.class);
     	
		for (ClinicModel clinic : this.clinicModelList) {
     		this.clinicNameList.add(clinic.getName());
     	}
	}

	@Override
	public List<String> searchButtonClicked(String searchTerm, String searchMode) {
		
		List<String> optionalClinic = new LinkedList<>();
		
		if (!searchTerm.isEmpty()) {
			if(searchMode.equals("Clinic")) {
				optionalClinic = this.clinicModelList.stream()
						.filter(clinic -> clinic.getName().toLowerCase().contains(searchTerm.toLowerCase()))
						.map(ClinicModel::getName)
						.collect(Collectors.toList());
				
			} else if(searchMode.equals("Addiction")) {
				
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


	@Override
	public List<String> selectListChanged(String clinicName) {
		Optional<ClinicModel> optionalClinic = this.clinicModelList.stream()
				.filter(clinic -> clinic.getName().equals(clinicName))
				.findFirst();
				
			List<String> clinicDetails = new LinkedList<>();
			
			if(optionalClinic.isPresent()) {
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

}
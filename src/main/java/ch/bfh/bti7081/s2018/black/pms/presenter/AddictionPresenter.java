package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.util.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.util.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.AddictionView;

public class AddictionPresenter implements AddictionView.AddictionViewListener {

	private AddictionView view;
	private List<AddictionModel> addictModelList;
	private List<String> addictNameList = new LinkedList<>();
	
	public AddictionPresenter(AddictionView view) {
		this.view = view;
		this.addictModelList = new LinkedList<>();
		view.addListener(this);
		this.fillAddictionList();
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
	public List<String> addToButtonClicked() {

	// These are Mock Objects because Patient Management isn't ready yet
		List<String> patientList = new LinkedList<>();
		patientList.add("Toni Donato");
		patientList.add("Nico Schlup");
		patientList.add("Cederik Bielmann");
		patientList.add("Michi Hofer");
		patientList.add("Jan Henzi");
		
		//
		//
		// Put Logic in here once Patient Management is ready
		//
		//
		
		return patientList;
	}
	
	@Override
	public void allocateButtonClicked(String addictionName, String patientName) {
		Optional<AddictionModel> optionalAddict = this.addictModelList.stream()
				.filter(addict -> addict.getName().equals(addictionName))
				.findFirst();
		
		if(optionalAddict.isPresent()) {
			optionalAddict.get();		//		this is your AddictionModel :)
			System.out.println("Allocation: " + patientName + " suffers from " + optionalAddict.get().getName());
			//
			//
			// Put Logic in here
			//
			//
		}
	}
	
	public void addAddiction(AddictionModel addiction) {
		this.addictModelList.add(addiction);
	}
	
	public List<AddictionModel> getAddictionModelList() {
		return this.addictModelList;
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
	
	public void fillAddictionList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.addictModelList = objects.findAll(AddictionModel.class);
     	
		for (AddictionModel addict : this.addictModelList) {
     		this.addictNameList.add(addict.getName());
     	}
	}

	@Override
	public List<String> setupAddictList() {
		return this.addictNameList;
	}
}

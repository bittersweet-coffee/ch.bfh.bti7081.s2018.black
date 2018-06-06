package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.view.DrugView;

public class DrugPresenter implements DrugView.DrugViewListener {
	
	
	private DrugView view;
	private List<DrugModel> drugModelList;
	private List<String> drugNameList = new LinkedList<>();

	
	public DrugPresenter(DrugView view) {
		this.view = view;
		view.addListener(this);
		this.drugModelList = new LinkedList<>();
		this.fillDrugList();
	}
	
	
	public void fillDrugList() {
		
		for(int i=0; i<20; i++) {
			DrugModel dummy = new DrugModel();
			dummy.setName("Drug_" + Integer.toString(i));
			
			String desc = "";
			
			for(int j=0; j<15; j++) {
				desc = desc.concat("This is a Description.....\n\n");
			}
			
			desc.substring(0, desc.length()-2);
			dummy.setDescription(desc);
			
			this.drugModelList.add(dummy);
		}
		
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
	public void allocateButtonClicked(String drugName, String patientName) {
		Optional<DrugModel> optionalDrug = this.drugModelList.stream()
				.filter(drug -> drug.getName().equals(drugName))
				.findFirst();
		
		if(optionalDrug.isPresent()) {
			optionalDrug.get();		//		this is your DrugModel :)
			System.out.println("Allocation: " + patientName + " needs to take " + optionalDrug.get().getName());
			//
			//
			// Put Logic in here
			//
			//
		}
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
	
	
	
	
	
	
	

}

package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.view.AddictionView;

public class AddictionPresenter implements AddictionView.AddictionViewListener {

	private AddictionView view;
	private List<AddictionModel> addictList;
	private List<String> mockListNames = new LinkedList<>();
	
	
	public AddictionPresenter(AddictionView view) {
		this.view = view;
		this.addictList = new LinkedList<>();
		view.addListener(this);
		this.setupMockList();
		this.view.setupAddictList(mockListNames);
	}


	@Override
	public void searchButtonClicked(String searchTerm) {
		
		List<String> optionalAddict = this.addictList.stream()
				.filter(addict -> addict.getName().toLowerCase().contains(searchTerm.toLowerCase()))
				.map(AddictionModel::getName)
				.collect(Collectors.toList());
				
		this.view.setupAddictList(optionalAddict);
		
	}


	@Override
	public void addToButtonClicked() {
		
		List<String> patientList = new LinkedList<>();
		patientList.add("Toni Donato");
		patientList.add("Nico Schlup");
		patientList.add("Cederik Bielmann");
		patientList.add("Michi Hofer");
		patientList.add("Jan Henzi");
		
		this.view.setupPatientList(patientList);
		
	}
	
	@Override
	public void allocateButtonClicked(String addictionName, String patientName) {
		
		Optional<AddictionModel> optionalAddict = this.addictList.stream()
				.filter(addict -> addict.getName().equals(addictionName))
				.findFirst();
		
		
		if(optionalAddict.isPresent()) {
			optionalAddict.get();		//		this is your AddictionModel :)
			System.out.println("Allocation: " + patientName + " suffers from " + optionalAddict.get().getName());
			//
			//
			// Put Logic in here (
			//
			//
		}
		
	}
	
	public void addAddiction(AddictionModel addiction) {
		this.addictList.add(addiction);
	}
	
	public List<AddictionModel> getMockList() {
		return this.addictList;
	}
	
	@Override
	public void selectListChanged(String addictionName) {
		
		Optional<AddictionModel> optionalAddict = this.addictList.stream()
			.filter(addict -> addict.getName().equals(addictionName))
			.findFirst();
			
		if(optionalAddict.isPresent()) {
			this.view.setListDesc(optionalAddict.get().getDescription());
			this.view.setListSymptoms(optionalAddict.get().getSymptomsAsString());
		}
		
	}
	
public void setupMockList() {
		
        AddictionModel addictAlc = new AddictionModel();
        addictAlc.setName("Alcoholism");
        addictAlc.setDescription("Alcoholism, also known as alcohol use disorder (AUD), is a broad term for any drinking of alcohol that results in mental or physical health problems. The disorder was previously divided into two types: alcohol abuse and alcohol dependence. In a medical context, alcoholism is said to exist when two or more of the following conditions is present: a person drinks large amounts over a long time period, has difficulty cutting down, acquiring and drinking alcohol takes up a great deal of time, alcohol is strongly desired, usage results in not fulfilling responsibilities, usage results in social problems, usage results in health problems, usage results in risky situations, withdrawal occurs when stopping, and alcohol tolerance has occurred with use.");
        
        List<String> additionals = new LinkedList<>();
        additionals.add("- Drinks large amounts over a long period");
        additionals.add("- difficulty cutting down");
        additionals.add("- acquiring and drinking alcohol takes up a lot of time");
        additionals.add("- usage results in problems");
        additionals.add("- withdrawal occurs when stopping");
        additionals.add("- alcohol tolerance has occurred");
        
        addictAlc.setSymptoms(additionals);
        additionals = new LinkedList<>();
        
        AddictionModel addictCocaine = new AddictionModel();
        addictCocaine.setName("Cocaine Dependence");
     	addictCocaine.setDescription("Cocaine dependence is a psychological desire to use cocaine regularly. Cocaine overdose may result in cardiovascular and brain damage, such as: constricting blood vessels in the brain, causing strokes and constricting arteries in the heart; causing heart attacks. The use of cocaine creates euphoria and high amounts of energy. If taken in large, unsafe doses, it is possible to cause mood swings, paranoia, insomnia, psychosis, high blood pressure, a fast heart rate, panic attacks, cognitive impairments and drastic changes in personality.");
     	
     	additionals.add("- aggression");
     	additionals.add("- severe paranioa");
     	additionals.add("- restlessness");
     	additionals.add("- confusion");
     	additionals.add("- tactile hallucinations");
     	additionals.add("- suicide thoughts");
     	additionals.add("- unusual weight loss");
     	additionals.add("- trouble maintaining relationships");
     	additionals.add("- unhealthy pale appearance");
     	
     	addictCocaine.setSymptoms(additionals);
     	additionals = new LinkedList<>();
        
     	AddictionModel addictGambling = new AddictionModel();
     	addictGambling.setName("Problem Gambling");
     	addictGambling.setDescription("Problem gambling (or ludomania, but usually referred to as \"gambling addiction\" or \"compulsive gambling\") is an urge to gamble continuously despite harmful negative consequences or a desire to stop. ");
     	
     	additionals.add("- need to gamble with increasing amounts of money in order to achieve the desired excitement");
     	additionals.add("- restless or irritable when attempting to cut down or stop gambling");
     	additionals.add("- repeated unsuccessful efforts to control, cut back, or stop gambling");
     	additionals.add("- often preoccupied with gambling");
     	additionals.add("- gambling when feeling distressed");
     	additionals.add("- lying to conceal the extent of involvement with gambling");
     	additionals.add("- jeopardize or loss a significant relationship, job, education or career opportunity because of gambling");
     	additionals.add("- relying on others to provide money to relieve desperate financial situations caused by gambling");
     	
     	addictGambling.setSymptoms(additionals);
     	additionals = new LinkedList<>();
     	
     	this.addictList.add(addictAlc);
     	this.addictList.add(addictCocaine);
     	this.addictList.add(addictGambling);
     	
     	for (int i=0; i<20; i++) {
     		AddictionModel temp = new AddictionModel();
     		temp.setName("Generated_" + String.valueOf(i));
     		temp.setDescription(Collections.nCopies(150, String.valueOf(i)).stream().collect(Collectors.joining("")));
     		
     		for (int j=0; j<20; j++) additionals.add("- " + String.valueOf(j));
     		temp.setSymptoms(additionals);
     		
     		additionals = new LinkedList<>();
     		this.addictList.add(temp);
     	}
        
     	for (AddictionModel addict : this.addictList) mockListNames.add(addict.getName());
	}


	
}

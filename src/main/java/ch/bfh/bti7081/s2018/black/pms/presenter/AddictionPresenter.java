package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
		this.view.setMockListNames(mockListNames);
	}


	@Override
	public void searchButtonClicked(String searchTerm) {
		// fetch Database
		
	}


	@Override
	public void addToButtonClicked(String addictionName) {
		
		
		
	}
	
	public void addAddiction(AddictionModel addiction) {
		this.addictList.add(addiction);
	}
	
	public List<AddictionModel> getMockList() {
		return this.addictList;
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
        
        AddictionModel addictCocaine = new AddictionModel();
        addictCocaine.setName("Cocaine Dependence");
        addictCocaine.setDescription("Cocaine dependence is a psychological desire to use cocaine regularly. Cocaine overdose may result in cardiovascular and brain damage, such as: constricting blood vessels in the brain, causing strokes and constricting arteries in the heart; causing heart attacks. The use of cocaine creates euphoria and high amounts of energy. If taken in large, unsafe doses, it is possible to cause mood swings, paranoia, insomnia, psychosis, high blood pressure, a fast heart rate, panic attacks, cognitive impairments and drastic changes in personality.");
        
        AddictionModel addictNicotine = new AddictionModel();
        addictNicotine.setName("Nicotine Dependence");
        addictNicotine.setDescription("Nicotine dependence, or tobacco use disorder, is a state of dependence upon nicotine. There are different ways of measuring nicotine dependence.");
        
        this.addictList.add(addictAlc);
        this.addictList.add(addictCocaine);
        this.addictList.add(addictNicotine);
        
        
        for (AddictionModel addict : this.addictList) mockListNames.add(addict.getName());
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
	
	/*
	public List<String> getMockListNames() {
		
		List<String> listNames = new LinkedList<>();
		for (AddictionModel addict : this.addictList) listNames.add(addict.getName());
		return listNames;
	}
	
	*/


	
}

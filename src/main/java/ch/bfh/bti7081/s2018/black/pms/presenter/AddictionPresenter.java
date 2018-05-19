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
        addictAlc.setName("Alcohol Addiction");
        addictAlc.setDescription("Alcoholism, also known as alcohol use disorder (AUD), is a broad term for any drinking of alcohol that results in mental or physical health problems. The disorder was previously divided into two types: alcohol abuse and alcohol dependence. In a medical context, alcoholism is said to exist when two or more of the following conditions is present: a person drinks large amounts over a long time period, has difficulty cutting down, acquiring and drinking alcohol takes up a great deal of time, alcohol is strongly desired, usage results in not fulfilling responsibilities, usage results in social problems, usage results in health problems, usage results in risky situations, withdrawal occurs when stopping, and alcohol tolerance has occurred with use.");
        
        AddictionModel addictCocaine = new AddictionModel();
        addictCocaine.setName("Cocaine Addiction");
        addictCocaine.setDescription("Massive Cocaine Consumption");
        
        AddictionModel addictNicotine = new AddictionModel();
        addictNicotine.setName("Nicotine Addiction");
        addictNicotine.setDescription("Massive Cigarette Consumption");
        
        this.addictList.add(addictAlc);
        this.addictList.add(addictCocaine);
        this.addictList.add(addictNicotine);
        
        
        for (AddictionModel addict : this.addictList) mockListNames.add(addict.getName());
	}


	@Override
	public void selectListChanged(String addictionName) {
		
		Optional<String> optionalDesc = this.addictList.stream()
			.filter(addict -> addict.getName().equals(addictionName))
			.map(AddictionModel::getDescription)
			.findFirst();
			
		if(optionalDesc.isPresent()) {
			String desc = optionalDesc.get();
			this.view.setListDesc(desc);
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

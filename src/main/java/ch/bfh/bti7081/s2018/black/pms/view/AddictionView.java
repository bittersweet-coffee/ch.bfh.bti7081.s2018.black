package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;


public interface AddictionView {
	
	public interface AddictionViewListener {
		void searchButtonClicked(String searchTerm);
		void addToButtonClicked(String addictionName);
		void selectListChanged(String addictionName);
		
	}
	
	public void addListener(AddictionViewListener listener);
	public void setMockListNames(List<String> mockListNames);
	public void setListDesc(String desc);
	public void setListSymptoms(String symptoms);
}

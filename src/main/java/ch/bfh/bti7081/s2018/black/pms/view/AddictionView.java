package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

/**
 * AddictionView Interface
 * View Interface used by the ViewImplementation
 */
public interface AddictionView {
	
	/**
	 * AddictionViewListener Interface
	 * ViewListener Interface used by the Presenter Class
	 */
	public interface AddictionViewListener {
		
		/**
		 * Method used to tell the Listener a search is requested
		 * @param searchTerm The search term used to search an Addiction by its name
		 * @return List of Strings containing the matches of Addictions which contain the search term
		 */
		List<String> searchButtonClicked(String searchTerm);
		
		/**
		 * Method used to tell the Listener an allocation is requested to be done/checked
		 * @param addictionName The name of the selected Addiction
		 * @param patientName The mock object of the selected Patient (PatientItem)
		 * @return boolean response whether the operation could be performed
		 */
		boolean allocateButtonClicked(String addictionName, PatientItem patientItem);
		
		/**
		 * Method used to tell the Listener an Addiction has been selected. Addiction Details have then to be displayed (Description & Symptoms)
		 * @param addictionName The name of the selected Addiction
		 * @return List of Strings containing the Description of the Addiction at Index (0) and its Symptoms at Index (1)
		 */
		List<String> selectListChanged(String addictionName);
		
		/**
		 * Method used to tell the Listener to setup the List with all Addiction Names (used only once during enter() Method of the ViewImpl) 
		 * @return List of Strings containing the names of all Addictions
		 */
		List<String> setupAddictList();
		
		/**
		 * Method used to tell the Listener to setup the List with all PatientItems. Used to build the patientItemGrid for allocation.
		 * @return List of PatientItems containing mock objects of the PatientModel (PatientItem)
		 */
		List<PatientItem> setupPatientItemList();
		
	}
	
	/**
	 * Method used by the Presenter to register itself as listener
	 * @param listener instance of the corresponding presenter
	 */
	public void addListener(AddictionViewListener listener);
	
}

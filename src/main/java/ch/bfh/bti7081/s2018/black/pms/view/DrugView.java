package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.Pair;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

/**
 * DrugView Interface
 * View Interface used by the ViewImplementation
 * @author schaa4
 *
 */
public interface DrugView {
	
	/**
	 * DrugViewListener Interface
	 * ViewListener Interface used by the Presenter Class
	 * @author schaa4
	 *
	 */
	public interface DrugViewListener {

		/**
		 * Method used to tell the Listener a search is requested
		 * @param searchTerm The search term used to search a Drug by its name
		 * @return List of Strings containing the matches of Drugs which contain the search term
		 */
		List<String> searchButtonClicked(String searchTerm);
		
		/**
		 * Method used to tell the Listener an allocation is requested to be done/checked
		 * @param drugName The name of the selected Drug
		 * @param patientItem The mock object of the selected Patient (PatientItem)
		 * @param dose Entered dose to be checked if it is a valid input
		 * @return Pair object containing the boolean response as well as its corresponding message as a String
		 */
		Pair allocateButtonClicked(String drugName, PatientItem patientItem, Double dose);
		
		/**
		 * Method used to tell the Listener a Drug has been selected. Drug Details have then to be displayed (Description)
		 * @param drugName The name of the selected Drug
		 * @return List of Strings containing the Description of the Drug at Index (0), the MinDose at Index (1) and the MaxDose at Index (2)
		 */
		List<String> selectListChanged(String drugName);
		
		/**
		 * Method used to tell the Listener to setup the List with all Drug Names (used only once during enter() Method of the ViewImpl) 
		 * @return List of Strings containing the names of all Drugs
		 */
		List<String> setupDrugList();
		
		/**
		 * Method used to tell the Listener to setup the List with all PatientItems. Used to build the patientItemGrid for allocation.
		 * @return List of PatientItems containing mock objects of the PatientModel (PatientItem)
		 */
		List<PatientItem> setupPatientItemList();
		
		/**
		 * Method to detect whether entered Dose is a valid Double number.
		 * Valid means: Standard Double format & less than 7 decimal places
		 * @param str Entered Dose retrieved as String
		 * @return boolean response whether entered Dose is a valid Double number
		 */
		boolean isDouble(String str);
	}
	
	/**
	 * Method used by the Presenter to register itself as listener
	 * @param listener instance of the corresponding presenter
	 */
	public void addListener(DrugViewListener listener);
}

package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

/**
 * ClinicView Interface
 * View Interface used by the ViewImplementation
 */
public interface ClinicView {
	
	/**
	 * ClinicViewListener Interface
	 * ViewListener Interface used by the Presenter Class
	 */
	public interface ClinicViewListener {
		
		/**
		 * Method used to tell the Listener a search is requested
		 * @param searchTerm The search term used to search a Clinic by its name
		 * @return List of Strings containing the matches of Clinics which contain the search term
		 */
		List<String> searchButtonClicked(String searchTerm, String searchMode);
		
		/**
		 * Method used to tell the Listener a Clinic has been selected. Clinicname has then to be displayed
		 * @param clinicName The name of the selected Clinic
		 * @return List of Strings containing the name of the Clinic at Index (0)
		 */
		List<String> selectListChanged(String clinicName);
		
		/**
		 * Method used to tell the Listener to setup the List with all Clinic Names (used only once during enter() Method of the ViewImpl) 
		 * @return List of Strings containing the names of all Clinics
		 */
		List<String> setupClinicList();
	}
	
	/**
	 * Method used by the Presenter to register itself as listener
	 * @param listener instance of the corresponding presenter
	 */
	public void addListener(ClinicViewListener listener);
}

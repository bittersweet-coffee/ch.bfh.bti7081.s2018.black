package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

/**
 * ReportView Interface
 * View Interface used by the ViewImplementation
 */
public interface ReportView {
	/**
	 * ReportViewListener Interface
	 * ViewListener Interface used by the Presenter Class
	 */
	public interface ReportViewListener {
		/**
		 * Method used to tell the Listener to setup the List with all PatientItems.
		 * @return List of PatientItems containing mock objects of the PatientModel (PatientItem)
		 */
		List<PatientItem> setupPatientItemList();
	}
	/**
	 * Method used by the Presenter to register itself as listener
	 * @param listener instance of the corresponding presenter
	 */
	public void addListener(ReportViewListener listener);
}

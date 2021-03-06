package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

/**
 * PatientView
 */
public interface PatientView {
	
	public interface PatientViewListener {
		List<String> getNotesForPatient(Integer patientId);
		void saveNoteButtonClicked(PatientItem patientItem, String note);
		void saveButtonClicked(PatientItem patientItem, String note);
		List<PatientItem> setupPatientItemList();
		
	}
	
	public void addListener(PatientViewListener listener);
	public void saveNoteButtonClicked(PatientItem patientItem, String note);
}

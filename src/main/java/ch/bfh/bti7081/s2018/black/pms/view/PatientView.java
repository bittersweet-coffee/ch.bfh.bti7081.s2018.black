package ch.bfh.bti7081.s2018.black.pms.view;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

public interface PatientView {
	
	public interface PatientViewListener {
		
		void saveButtonClick(PatientItem patient);
		
	}
}

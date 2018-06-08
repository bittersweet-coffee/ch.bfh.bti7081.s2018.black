package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;
import java.util.Map;

import com.vaadin.data.provider.ListDataProvider;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.LocationModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;

public interface PatientView {
	
	public interface PatientViewListener {
		void saveButtonClick(PatientModel patient);
		List<List<String>> openButtonClicked(Integer patientId, String patientName);
		List<String> getNotesForPatient(Integer patientId);
		void saveNoteButtonClicked(PatientItem patientItem, String note);
		void saveButtonClicked(PatientItem patientItem, String note);
		List<PatientItem> setupPatientItemList();
		List<DoctorModel> getDoctors();
		List<LocationModel> getLocation();
		List<AddictionModel> getAddictions();
		List<DrugModel> getDrugs();
		
	}
	
	public void addListener(PatientViewListener listener);
	public void saveNoteButtonClicked(PatientItem patientItem, String note);
}

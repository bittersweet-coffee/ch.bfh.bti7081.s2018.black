package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.view.PatientView.PatientViewListener;

public interface ReportView {
	
	public interface ReportViewListener {
		List<PatientItem> setupPatientItemList();
	}
	
	public void addListener(ReportViewListener listener);
}

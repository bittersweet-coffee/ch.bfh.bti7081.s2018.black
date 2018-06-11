package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.persistence.JpaUtility;
import ch.bfh.bti7081.s2018.black.pms.view.ReportView;

public class ReportPresenter implements ReportView.ReportViewListener {
	
	private ReportView view;
	private List<PatientModel> patientModelList;
	private List<PatientItem> patientItemList = new LinkedList<>();
	
	public ReportPresenter(ReportView view) {
		this.view = view;
		this.patientModelList = new LinkedList<>();
		view.addListener(this);
	}

	@Override
	public List<PatientItem> setupPatientItemList() {
		this.fillPatientList();
		return this.patientItemList;
	}
	
	public void fillPatientList() {
		JpaUtility transaction = new JpaUtility();
		JpaDataAccessObject objects = new JpaDataAccessObject(transaction);
		this.patientModelList = objects.findAll(PatientModel.class);
     	
		this.patientItemList = new LinkedList<>();
		for (PatientModel patient : this.patientModelList) {
			this.patientItemList.add(new PatientItem(patient));
     	}
	}

}

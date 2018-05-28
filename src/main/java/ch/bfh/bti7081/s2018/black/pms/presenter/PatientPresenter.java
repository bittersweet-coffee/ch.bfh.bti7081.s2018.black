package ch.bfh.bti7081.s2018.black.pms.presenter;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientModel;
import ch.bfh.bti7081.s2018.black.pms.view.PatientView;

public class PatientPresenter implements PatientView.PatientViewListener{
	
	private PatientView view;
	private PatientModel model;

	public PatientPresenter(PatientView view, PatientModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void saveButtonClick(PatientItem patient) {
		
	}

}

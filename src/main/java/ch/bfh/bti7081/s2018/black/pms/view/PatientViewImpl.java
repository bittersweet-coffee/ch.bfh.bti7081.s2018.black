package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2018.black.pms.model.UserModel;
import ch.bfh.bti7081.s2018.black.pms.util.JpaDemo;
import ch.bfh.bti7081.s2018.black.pms.util.JpaUtility;

public class PatientViewImpl extends PmsCustomComponent implements View, PatientView {

	public static final String NAME = "patient";

	public PatientViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		Label test = new Label("Patient here");
        super.contentPanel.setContent(test);
        
        UserModel user = new UserModel();
        user.setName("Test generic");
        JpaUtility.persist(user);
        JpaDemo.testUser();
	}
}

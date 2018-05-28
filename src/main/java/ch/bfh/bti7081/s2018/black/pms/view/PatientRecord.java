package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PatientRecord extends PmsCustomComponent implements View, PatientView {
	
	public static final String NAME = "patient-record";

	public PatientRecord() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {

		Label lblSurname = new Label("<h4>Surname: </h4>", ContentMode.HTML);
		lblSurname.setStyleName("patient-view-positions");
		
		Label lblName = new Label("<h4>Name: </h4>", ContentMode.HTML);
		lblName.setStyleName("patient-view-positions");
		
		Label lblBirthday = new Label("<h4>Birthday: </h4>", ContentMode.HTML);
		lblBirthday.setStyleName("patient-view-positions");
		
		GridLayout tileGridPatient = new GridLayout(2,3);
		tileGridPatient.addComponent(lblSurname, 0, 0);
		tileGridPatient.addComponent(lblName, 0, 1);
		tileGridPatient.addComponent(lblBirthday, 0, 2);
		
		
		VerticalLayout vBoxLeft = new VerticalLayout();
		vBoxLeft.setWidth("600px");
		vBoxLeft.addComponents(tileGridPatient);
		
		VerticalLayout vBoxRight = new VerticalLayout();
		vBoxRight.setWidth("600px");
		
		HorizontalLayout hBox = new HorizontalLayout();
		hBox.addComponents(vBoxLeft, vBoxRight);
		
		super.contentPanel.setSizeUndefined();
		super.contentPanel.setContent(hBox);
	}

}

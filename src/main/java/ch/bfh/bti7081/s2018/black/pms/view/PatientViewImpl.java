package ch.bfh.bti7081.s2018.black.pms.view;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

public class PatientViewImpl extends PmsCustomComponent implements View, PatientView {

	public static final String NAME = "patient";
	
	private List<PatientViewListener> listeners = new ArrayList<PatientViewListener>();

	public PatientViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		
		
		Button btnNewPatient = new Button("New Patient");
		
		btnNewPatient.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				patientWindow();
			}
		});
		
		
		Label lblNewPatient = new Label("<h4>Neuer Patient anlegen: </h4>", ContentMode.HTML);
		HorizontalLayout hNewPatient = new HorizontalLayout();
		
		hNewPatient.addComponents(lblNewPatient, btnNewPatient);
		
		VerticalLayout vMainLayout = new VerticalLayout();
		
		vMainLayout.addComponents(hNewPatient);
		super.contentPanel.setContent(vMainLayout);
        
        //UserModel user = new UserModel();
        //user.setName("Test generic");
        //JpaUtility.persist(user);
        //JpaDemo.testUser();
	}

	protected void patientWindow() {
		final PatientWindow window = new PatientWindow(this);   
		super.getUI().getUI().addWindow(window);
		
	}

	public void save(PatientItem newPatient) {
		
		
	}
}

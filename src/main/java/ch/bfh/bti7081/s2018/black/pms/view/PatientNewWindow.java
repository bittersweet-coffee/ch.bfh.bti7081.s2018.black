package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PatientNewWindow extends Window {
	
	PatientViewImpl view;
	//PatientItem newPatient = new PatientItem();
	
	public PatientNewWindow(PatientViewImpl view) {
		super("New Patient");
		this.view = view;
		buildWindow();
	}
	
	private void buildWindow() {
		
		Label lblfirstName = new Label("First Name: ");
		Label lblLastName = new Label("Last Name: ");
		Label lblNotes = new Label("Notes: ");
		
		TwinColSelect<String> addictionselect = new TwinColSelect<>("Addictions:");
		addictionselect.setItems("Addiction 1", "Addiction 2", "Addiction 3", "Addiction 4", "Addiction 5", "Addiction 6");
		addictionselect.setRows(5);
		
		TextField firstNameField = new TextField();
		TextField lastNameField = new TextField();
		
		TextArea descriptionField = new TextArea();
		descriptionField.setRows(5);
	
		Button btnSave = new Button("Save", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				//view.save(newPatient);
				close();
			}
		});
		Button btnCancel = new Button("Cancel", event -> this.close());
		
		GridLayout tileGrid = new GridLayout(2, 7);
		
		tileGrid.setMargin(true);
		
        tileGrid.addComponent(lblfirstName, 0, 0);
        tileGrid.addComponent(firstNameField, 1, 0);
        tileGrid.addComponent(lblLastName, 0, 1);
        tileGrid.addComponent(lastNameField, 1, 1);
        tileGrid.addComponent(addictionselect, 0, 2);
        tileGrid.addComponent(lblNotes, 0, 3);
        tileGrid.addComponent(descriptionField, 0, 4);
        tileGrid.addComponent(btnSave, 0, 5); 
		tileGrid.addComponent(btnCancel, 1, 5); 

        setContent(tileGrid);
	}
}

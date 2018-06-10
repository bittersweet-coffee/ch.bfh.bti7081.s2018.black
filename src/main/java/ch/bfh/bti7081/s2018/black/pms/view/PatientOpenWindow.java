package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.AppointmentModel;
import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

public class PatientOpenWindow extends Window {

	private PatientViewImpl view;
	private PatientItem patientItem;

	public PatientOpenWindow(PatientViewImpl view, PatientItem patientItem) {
		super("Open Patient");
		this.view = view;
		this.patientItem = patientItem;
		buildWindow();
	}
	
	private void buildWindow() {
		Label lblFirstName = new Label("Firstname:");
		Label lblLastName = new Label("Lastname:");
		Label lblBirthday = new Label("Birthday:");
		
		TextField txtFirstName = new TextField();
		txtFirstName.setValue(this.patientItem.getFirstName());
		txtFirstName.setMaxLength(20);
		txtFirstName.setReadOnly(true);
		
		TextField txtLastName = new TextField();
		txtLastName.setReadOnly(true);
		txtLastName.setMaxLength(20);
		txtLastName.setValue(this.patientItem.getLastName());
		
		TextField txtBirthday = new TextField();
		txtBirthday.setPlaceholder("Insert Birthday");
		txtBirthday.setMaxLength(20);
		txtBirthday.setReadOnly(true);
		txtBirthday.setValue(this.patientItem.getBirthdayAsString());
		
		GridLayout tileGridPatient = new GridLayout(2,3);
		tileGridPatient.addComponent(lblFirstName, 0, 0);
		tileGridPatient.addComponent(lblLastName, 0, 1);
		tileGridPatient.addComponent(lblBirthday, 0, 2);
		tileGridPatient.addComponent(txtFirstName, 1, 0);
		tileGridPatient.setComponentAlignment(txtFirstName, Alignment.MIDDLE_CENTER);
		tileGridPatient.addComponent(txtLastName, 1, 1);
		tileGridPatient.setComponentAlignment(txtLastName, Alignment.MIDDLE_CENTER);
		tileGridPatient.addComponent(txtBirthday, 1, 2);
		tileGridPatient.setComponentAlignment(txtBirthday, Alignment.MIDDLE_CENTER);
		
		//Appointment Part
		List<AppointmentModel> appointmentList =  patientItem.getModel().getAppointments();
		
        List<String> dataAppointment = new LinkedList<String>();
        for (AppointmentModel a: appointmentList) {
        	dataAppointment.add(a.getName() + "Strart: " + a.getStart() + "End: " + a.getEnd());
			
		}
        
        ListSelect lsAppointment = new ListSelect<>("Appointments", dataAppointment);
        lsAppointment.setRows(4);
        lsAppointment.setWidth("500px");
        lsAppointment.setStyleName("select.v-select-select");
        
        //lsPatient.addValueChangeListener(event -> System.out.println("Value changed"));
           
        
		GridLayout tileGridAppointment = new GridLayout(1,2);
		tileGridAppointment.addComponent(lsAppointment, 0, 0);
		
		//Comment Part
		TextArea txtCurrentNotes = new TextArea();
		txtCurrentNotes.setWidth("210px");
		txtCurrentNotes.setHeight("340px");
		txtCurrentNotes.setReadOnly(true);
		txtCurrentNotes.setCaptionAsHtml(true);
		txtCurrentNotes.setValue(this.patientItem.getNotesAsString());
		
		TextArea txtNewNote = new TextArea();
		txtNewNote.setWidth("210px");
		txtNewNote.setHeight("340px");
		
		Button btnSaveNote = new Button("Save");
		//TextField 
		
		GridLayout tileGridComment = new GridLayout(2,2);
		tileGridComment.addComponent(txtCurrentNotes, 0, 0);
		tileGridComment.addComponent(txtNewNote, 1, 0);
		tileGridComment.addComponent(btnSaveNote, 0, 1);
		
		
		
		//Addiction Part
		List<AddictionModel> addicList = patientItem.getModel().getAddictions(); 
		List<String> dataAddiction = new LinkedList<String>();
		for (AddictionModel addic : addicList) {
			dataAddiction.add(addic.getName());
		}
			
        ListSelect lsAddiction = new ListSelect<>("Addictions", dataAddiction);
        lsAddiction.setRows(4);
        lsAddiction.setWidth("500px");
        lsAddiction.setStyleName("select.v-select-select");
        
        //lsPatient.addValueChangeListener(event -> System.out.println("Value changed"));
        
    
		GridLayout tileGridAddiction = new GridLayout(1,2);
		tileGridAddiction.addComponent(lsAddiction, 0, 0);
		
		//Medication Part
		List<DrugModel> dataMediObjects = patientItem.getModel().getDrugs();
		List<String> dataMedication = new LinkedList<String>();
		for (DrugModel drugs : dataMediObjects) {
			dataMedication.add(drugs.getName());
		}
        ListSelect lsMedication = new ListSelect<>("Medications", dataMedication);
        lsMedication.setRows(4);
        lsMedication.setWidth("500px");
        lsMedication.setStyleName("select.v-select-select");
        
        //lsPatient.addValueChangeListener(event -> System.out.println("Value changed"));
         
        
		GridLayout tileGridMedication = new GridLayout(1,2);
		tileGridMedication.addComponent(lsMedication, 0, 0);
	
		VerticalLayout vBoxLeft = new VerticalLayout();
		vBoxLeft.setWidth("600px");
		vBoxLeft.addComponents(tileGridPatient, tileGridAddiction, tileGridMedication);
		
		VerticalLayout vBoxRight = new VerticalLayout();
		vBoxRight.setWidth("600px");
		vBoxRight.addComponents(tileGridAppointment,tileGridComment);
		
		GridLayout tileGrid = new GridLayout(2,1);
		tileGrid.addComponent(vBoxLeft, 0, 0);
		tileGrid.addComponent(vBoxRight, 1, 0);
		
		setContent(tileGrid);
		
		
		btnSaveNote.addClickListener(click -> {
			this.view.saveNoteButtonClicked(this.patientItem, txtNewNote.getValue());
			this.close();
		});
	}
}

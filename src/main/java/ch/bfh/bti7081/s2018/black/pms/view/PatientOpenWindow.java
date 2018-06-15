package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.LinkedList;
import java.util.List;

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
import ch.bfh.bti7081.s2018.black.pms.model.DoctorModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientDrugModel;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

/**
 * PatientOpenWindow Class
 * Used to display all important content of a patient record.
 */
public class PatientOpenWindow extends Window {

	private PatientViewImpl view;
	private PatientItem patientItem;

	/**
	 * Constructor for the PatientOpenWindow
	 * Used to build the UI to display the patient record as well as initializing the patient-mock-objects
	 */
	public PatientOpenWindow(PatientViewImpl view, PatientItem patientItem) {
		super("Open Patient");
		this.view = view;
		this.patientItem = patientItem;
		buildWindow();
	}
	
	//Opens the new Window
	private void buildWindow() {
		
		//Components to declare which information of the patient is on the right side
		Label lblFirstName = new Label("Firstname:");
		Label lblLastName = new Label("Lastname:");
		Label lblBirthday = new Label("Birthday:");
		Label lblStreet = new Label("Street:");
		Label lblPostCode = new Label("ZIP:");
		Label lblTel = new Label("Telephone:");
		Label lblClinic = new Label("Clinic:");
		
		//TextFields which contain the informations about the patient
		TextField txtFirstName = new TextField();
		txtFirstName.setValue(this.patientItem.getFirstName());
		txtFirstName.setMaxLength(20);
		txtFirstName.setReadOnly(true);
		
		TextField txtLastName = new TextField();
		txtLastName.setReadOnly(true);
		txtLastName.setMaxLength(20);
		txtLastName.setValue(this.patientItem.getLastName());
		
		TextField txtBirthday = new TextField();
		txtBirthday.setMaxLength(20);
		txtBirthday.setReadOnly(true);
		txtBirthday.setValue(this.patientItem.getBirthdayAsString());
		
		TextField txtStreet = new TextField();
		txtStreet.setMaxLength(20);
		txtStreet.setReadOnly(true);
		txtStreet.setValue(this.patientItem.getStreet());
		
		TextField txtPostCode = new TextField();
		txtPostCode.setMaxLength(20);
		txtPostCode.setReadOnly(true);
		txtPostCode.setValue(String.valueOf(this.patientItem.getPostcode()));
		
		TextField txtTel = new TextField();
		txtTel.setMaxLength(20);
		txtTel.setReadOnly(true);
		txtTel.setValue(this.patientItem.getTelephone());
		
		TextField txtClinic = new TextField();
		//txtClinic.setMaxLength(20);
		txtClinic.setReadOnly(true);
		txtClinic.setValue(this.patientItem.getClinic().getName());
		
		//Layout to group all components
		GridLayout tileGridPatient = new GridLayout(4,4);
		tileGridPatient.addComponent(lblFirstName, 0, 0);
		tileGridPatient.addComponent(lblLastName, 0, 1);
		tileGridPatient.addComponent(lblBirthday, 0, 2);
		tileGridPatient.addComponent(lblClinic, 0, 3);
		tileGridPatient.addComponent(txtFirstName, 1, 0);
		tileGridPatient.setComponentAlignment(txtFirstName, Alignment.MIDDLE_CENTER);
		tileGridPatient.addComponent(txtLastName, 1, 1);
		tileGridPatient.setComponentAlignment(txtLastName, Alignment.MIDDLE_CENTER);
		tileGridPatient.addComponent(txtBirthday, 1, 2);
		tileGridPatient.setComponentAlignment(txtBirthday, Alignment.MIDDLE_CENTER);
		tileGridPatient.addComponent(txtClinic, 1, 3);
		tileGridPatient.setComponentAlignment(txtClinic, Alignment.MIDDLE_CENTER);		
		
		tileGridPatient.addComponent(lblStreet, 2, 0);
		tileGridPatient.addComponent(lblPostCode, 2, 1);
		tileGridPatient.addComponent(lblTel, 2, 2);
		tileGridPatient.addComponent(txtStreet, 3, 0);
		tileGridPatient.setComponentAlignment(txtStreet, Alignment.MIDDLE_CENTER);
		tileGridPatient.addComponent(txtPostCode, 3, 1);
		tileGridPatient.setComponentAlignment(txtPostCode, Alignment.MIDDLE_CENTER);
		tileGridPatient.addComponent(txtTel, 3, 2);
		tileGridPatient.setComponentAlignment(txtTel, Alignment.MIDDLE_CENTER);
		
		//Part to display all Appointments of the patient
		List<AppointmentModel> appointmentList =  patientItem.getModel().getAppointments();
		List<String> dataAppointment = new LinkedList<String>();
		if(appointmentList != null) {
			if (!appointmentList.isEmpty()) {
				for (AppointmentModel a: appointmentList) {
		        	dataAppointment.add(a.getName() + "   Start: " + a.getStart() + "   End: " + a.getEnd());
				}
			} else {
				dataAppointment.add("No Appointments");
			}
		} else {
			dataAppointment.add("No Appointments");
		}
        
	    ListSelect<String> lsAppointment = new ListSelect<>("Appointments", dataAppointment);
	    lsAppointment.setRows(4);
	    lsAppointment.setWidth("500px");
	    lsAppointment.setStyleName("select.v-select-select");

		GridLayout tileGridAppointment = new GridLayout(1,2);
		tileGridAppointment.addComponent(lsAppointment, 0, 0);
		
		//Part to display and write the comments about the patient
		TextArea txtCurrentNotes = new TextArea();
		txtCurrentNotes.setWidth("250px");
		txtCurrentNotes.setHeight("340px");
		txtCurrentNotes.setReadOnly(true);
		txtCurrentNotes.setCaptionAsHtml(true);
		txtCurrentNotes.setValue(this.patientItem.getNotesAsString());
		
		TextArea txtNewNote = new TextArea();
		txtNewNote.setWidth("250px");
		txtNewNote.setHeight("340px");
		
		Button btnSaveNote = new Button("Save");
		
		GridLayout tileGridComment = new GridLayout(2,2);
		tileGridComment.addComponent(txtCurrentNotes, 0, 0);
		tileGridComment.addComponent(txtNewNote, 1, 0);
		tileGridComment.addComponent(btnSaveNote, 0, 1);
		
		//Part to display all Addictions of the patient
		List<AddictionModel> addicList = patientItem.getModel().getAddictions(); 
		List<String> dataAddiction = new LinkedList<String>();
		for (AddictionModel addic : addicList) {
			dataAddiction.add(addic.getName());
		}
			
	    ListSelect lsAddiction = new ListSelect<>("Addictions", dataAddiction);
	    lsAddiction.setRows(3);
	    lsAddiction.setWidth("525px");
	    lsAddiction.setStyleName("select.v-select-select");        
    
		GridLayout tileGridAddiction = new GridLayout(1,2);
		tileGridAddiction.addComponent(lsAddiction, 0, 0);
		
		//Part to display all Medications allocated to the patient
		List<PatientDrugModel> dataMediObjects = patientItem.getModel().getDrugs();
		
		List<String> dataMedication = new LinkedList<String>();
		if(dataMediObjects != null) {
			for (PatientDrugModel drugs : dataMediObjects) {
				dataMedication.add(drugs.getDrug().getName() + " Dose: " + drugs.getDose() + "" + drugs.getDrug().getUnit());
			}
		}
		
	    ListSelect lsMedication = new ListSelect<>("Medications", dataMedication);
	    lsMedication.setRows(3);
	    lsMedication.setWidth("525px");
	    lsMedication.setStyleName("select.v-select-select");
                
		GridLayout tileGridMedication = new GridLayout(1,2);
		tileGridMedication.addComponent(lsMedication, 0, 0);
	
		//Part to display all the Doctors allocated to the patient
		List<DoctorModel> docList = patientItem.getModel().getDoctors();
		List<String> dataDoc = new LinkedList<String>();
		for (DoctorModel doctor : docList) {
			dataDoc.add(doctor.getLastname() + " " + doctor.getFirstname());
		}
		
        ListSelect lsDoc = new ListSelect<>("Docotrs", dataDoc);
        lsDoc.setRows(2);
        lsDoc.setWidth("525px");
        lsDoc.setStyleName("select.v-select-select");
                
		GridLayout tileGridDoc = new GridLayout(1,2);
		tileGridDoc.addComponent(lsDoc, 0, 0);
		
		//Layouts to group the other components
		VerticalLayout vBoxLeft = new VerticalLayout();
		vBoxLeft.setWidth("600px");
		vBoxLeft.addComponents(tileGridPatient, tileGridAddiction, tileGridMedication, tileGridDoc);
		
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

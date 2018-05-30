package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PatientOpenWindow extends Window {

	private PatientViewImpl view;
	private String patientName;

	public PatientOpenWindow(PatientViewImpl view, String patientName) {
		super("Open Patient");
		this.view = view;
		this.patientName = patientName;
		buildWindow();
	}
	
	private void buildWindow() {
		Label lblFirstName = new Label("Firstname:");
		Label lblLastName = new Label("Lastname:");
		Label lblBirthday = new Label("Birthday:");
		
		String[] splittedName = patientName.split("\\,");
		
		TextField txtFirstName = new TextField();
		//txtFirstName.setPlaceholder("Insert surname");
		txtFirstName.setMaxLength(20);
		txtFirstName.setValue(splittedName[0]);
		txtFirstName.setReadOnly(true);
		
		TextField txtLastName = new TextField();
		//txtLastName.setPlaceholder("Insert name");
		txtLastName.setReadOnly(true);
		txtLastName.setMaxLength(20);
		txtLastName.setValue(splittedName[1].trim());
		
		TextField txtBirthday = new TextField();
		txtBirthday.setPlaceholder("Insert Birthday");
		txtBirthday.setMaxLength(20);
		txtBirthday.setReadOnly(true);
		
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
        List<String> data = IntStream.range(0, 4).mapToObj(i -> "Appointment " + i).collect(Collectors.toList());
        
        ListSelect lsAppointment = new ListSelect<>("Appointments", data);
        lsAppointment.setRows(4);
        lsAppointment.select(data.get(0));
        lsAppointment.setWidth("500px");
        lsAppointment.setStyleName("select.v-select-select");
        
        //lsPatient.addValueChangeListener(event -> System.out.println("Value changed"));
        
        Button btnNew = new Button("New");
        //btnNew.setStyleName("patient-record-view");      
        
		GridLayout tileGridAppointment = new GridLayout(1,2);
		tileGridAppointment.addComponent(lsAppointment, 0, 0);
		tileGridAppointment.addComponent(btnNew, 0, 1);
		tileGridAppointment.setComponentAlignment(btnNew, Alignment.BOTTOM_RIGHT);
		
		//Comment Part
		TextField tfComments = new TextField();
		tfComments.setWidth("510px");
		tfComments.setHeight("340px");
		tfComments.setPlaceholder("Insert Comment");
		
		GridLayout tileGridComment = new GridLayout(1,1);
		tileGridComment.addComponent(tfComments, 0, 0);
		
		//Addiction Part
		List<String> dataAddiction = IntStream.range(0, 4).mapToObj(i -> "Addiction " + i).collect(Collectors.toList());
        
        ListSelect lsAddiction = new ListSelect<>("Addictions", dataAddiction);
        lsAddiction.setRows(4);
        lsAddiction.select(dataAddiction.get(0));
        lsAddiction.setWidth("500px");
        lsAddiction.setStyleName("select.v-select-select");
        
        //lsPatient.addValueChangeListener(event -> System.out.println("Value changed"));
        
        Button btnAdd = new Button("Add");
        btnAdd.setStyleName("patient-record-view");      
        
		GridLayout tileGridAddiction = new GridLayout(1,2);
		tileGridAddiction.addComponent(lsAddiction, 0, 0);
		tileGridAddiction.addComponent(btnAdd, 0, 1);
		tileGridAddiction.setComponentAlignment(btnAdd, Alignment.BOTTOM_RIGHT);
		
		//Medication Part
		List<String> dataMedication = IntStream.range(0, 4).mapToObj(i -> "Medication " + i).collect(Collectors.toList());
        
        ListSelect lsMedication = new ListSelect<>("Medications", dataMedication);
        lsMedication.setRows(4);
        lsMedication.select(dataMedication.get(0));
        lsMedication.setWidth("500px");
        lsMedication.setStyleName("select.v-select-select");
        
        //lsPatient.addValueChangeListener(event -> System.out.println("Value changed"));
        
        Button btnAddMed = new Button("Add");
        btnAddMed.setStyleName("patient-record-view");      
        
		GridLayout tileGridMedication = new GridLayout(1,2);
		tileGridMedication.addComponent(lsMedication, 0, 0);
		tileGridMedication.addComponent(btnAddMed, 0, 1);
		tileGridMedication.setComponentAlignment(btnAddMed, Alignment.BOTTOM_RIGHT);
		
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
	}
}

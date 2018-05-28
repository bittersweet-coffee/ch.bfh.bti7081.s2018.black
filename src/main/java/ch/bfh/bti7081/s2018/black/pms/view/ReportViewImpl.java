package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ReportViewImpl extends PmsCustomComponent implements View, ReportView {

	public static final String NAME = "report";
	
	public ReportViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		
		//Patient Part
		Label lblSurname = new Label("<h3>Surname: </h3>", ContentMode.HTML);
		
		Label lblName = new Label("<h3>Name: </h3>", ContentMode.HTML);
		
		Label lblBirthday = new Label("<h3>Birthday: </h3>", ContentMode.HTML);
		
		TextField tfSurname = new TextField();
		tfSurname.setStyleName("patient-record-view-lbl");
		tfSurname.setPlaceholder("Insert surname");
		tfSurname.setMaxLength(20);
		
		TextField tfName = new TextField();
		tfName.setStyleName("patient-record-view-lbl");
		tfName.setPlaceholder("Insert name");
		tfName.setMaxLength(20);
		
		TextField tfBirthday = new TextField();
		tfBirthday.setStyleName("patient-record-view-lbl");
		tfBirthday.setPlaceholder("Insert Birthday");
		tfBirthday.setMaxLength(20);
		
		//Patient Part
		GridLayout tileGridPatient = new GridLayout(2,3);
		tileGridPatient.addComponent(lblSurname, 0, 0);
		tileGridPatient.addComponent(lblName, 0, 1);
		tileGridPatient.addComponent(lblBirthday, 0, 2);
		tileGridPatient.addComponent(tfSurname, 1, 0);
		tileGridPatient.setComponentAlignment(tfSurname, Alignment.MIDDLE_CENTER);
		tileGridPatient.addComponent(tfName, 1, 1);
		tileGridPatient.setComponentAlignment(tfName, Alignment.MIDDLE_CENTER);
		tileGridPatient.addComponent(tfBirthday, 1, 2);
		tileGridPatient.setComponentAlignment(tfBirthday, Alignment.MIDDLE_CENTER);
		
		
		
		//Appointment Part
        List<String> data = IntStream.range(0, 4).mapToObj(i -> "Appointment " + i).collect(Collectors.toList());
        
        ListSelect lsAppointment = new ListSelect<>("Appointments", data);
        lsAppointment.setRows(4);
        lsAppointment.select(data.get(0));
        lsAppointment.setWidth("500px");
        lsAppointment.setStyleName("select.v-select-select");
        
        //lsPatient.addValueChangeListener(event -> System.out.println("Value changed"));
        
        Button btnNew = new Button("New");
        btnNew.setStyleName("patient-record-view");      
        
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
		
		
		super.contentPanel.setSizeUndefined();
		super.contentPanel.setContent(tileGrid);
	}
}

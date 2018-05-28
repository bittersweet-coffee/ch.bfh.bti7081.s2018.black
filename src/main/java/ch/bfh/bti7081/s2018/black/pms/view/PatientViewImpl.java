package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

public class PatientViewImpl extends PmsCustomComponent implements View, PatientView {

	public static final String NAME = "patient";
	
	private List<PatientViewListener> listeners = new ArrayList<PatientViewListener>();

	public PatientViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		
		Label lblFilter = new Label("<h4>Filter: </h4>", ContentMode.HTML);
		lblFilter.setStyleName("patient-view-positions");
		
		TextField tfSearchterm = new TextField();
        tfSearchterm.setPlaceholder("Insert searchterm");
        tfSearchterm.setMaxLength(20);
        tfSearchterm.setStyleName("patient-view-positions");
        
        Button btnSearch = new Button("Search");
        btnSearch.setStyleName("patient-view-positions");
		
        List<String> data = IntStream.range(0, 10).mapToObj(i -> "Option " + i).collect(Collectors.toList());
        
        ListSelect lsPatient = new ListSelect<>("", data);
        lsPatient.setRows(10);
        lsPatient.select(data.get(0));
        lsPatient.setStyleName("patient-view-positions");
        lsPatient.setWidth("1120px");
        //lsPatient.setWidth(100.0f, Unit.PERCENTAGE);
        
        //lsPatient.addValueChangeListener(event -> System.out.println("Value changed"));
        
        Button btnOpenPatient = new Button("Open");
		btnOpenPatient.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				patientOpenWindow();
			}
		});
		
		Button btnNewPatient = new Button("New Patient");
		btnNewPatient.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				patientNewWindow();
			}
		});
		
		HorizontalLayout hBoxBottom = new HorizontalLayout();
		hBoxBottom.setStyleName("patient-view-positions");
		hBoxBottom.addComponents(btnNewPatient, btnOpenPatient);
				
		/*
		HorizontalLayout hBox = new HorizontalLayout();
		hBox.setStyleName("patient-view-positions");
		hBox.addComponents(tfSearchterm, btnSearch);
		
		GridLayout tileGrid = new GridLayout(1,4);
		tileGrid.setHeight("700px");
		tileGrid.setWidth("1200px");
		
		tileGrid.addComponent(lblFilter, 0, 0);
		tileGrid.setRowExpandRatio(0, 0.01f);
		tileGrid.setComponentAlignment(lblFilter, Alignment.TOP_LEFT);
		
		tileGrid.addComponent(hBox, 0, 0);
		tileGrid.setRowExpandRatio(1, 0.1f);
		
		tileGrid.addComponent(lsPatient, 0, 2);
		tileGrid.setRowExpandRatio(2, 0.69f);
		
		tileGrid.addComponent(hBoxBottom, 0, 3);
		tileGrid.setRowExpandRatio(0, 0.2f);
		tileGrid.setComponentAlignment(hBoxBottom, Alignment.MIDDLE_RIGHT);		
		 */
		
		GridLayout tileGridTop = new GridLayout(2,2);
		tileGridTop.addComponent(lblFilter, 0, 0);
		tileGridTop.addComponent(tfSearchterm, 0, 1);
		tileGridTop.addComponent(btnSearch, 1, 1);
		
		GridLayout tileGridMiddle = new GridLayout(1,1);
		tileGridMiddle.addComponent(lsPatient, 0, 0);
		
		GridLayout tileGridBottom = new GridLayout(1,1);
		tileGridBottom.addComponent(hBoxBottom, 0, 0);
		tileGridBottom.setWidth("1200px");
		tileGridBottom.setComponentAlignment(hBoxBottom, Alignment.BOTTOM_RIGHT);
		
		VerticalLayout vBox = new VerticalLayout();
		vBox.setWidth("1200px");
		//vBox.setHeight("700px");
		vBox.addComponents(tileGridTop, tileGridMiddle, tileGridBottom);
		vBox.setComponentAlignment(tileGridBottom, Alignment.BOTTOM_RIGHT);
		vBox.setComponentAlignment(tileGridMiddle, Alignment.TOP_LEFT);

		super.contentPanel.setSizeUndefined();
		super.contentPanel.setContent(vBox);
        
        //UserModel user = new UserModel();
        //user.setName("Test generic");
        //JpaUtility.persist(user);
        //JpaDemo.testUser();
	}

	protected void patientNewWindow() {
		final PatientNewWindow window = new PatientNewWindow(this);   
		window.setModal(true);
		super.getUI().getUI().addWindow(window);
	}

	protected void patientOpenWindow() {
		final PatientOpenWindow window = new PatientOpenWindow(this);   
		window.setModal(true);
		super.getUI().getUI().addWindow(window);
	}

	public void save(PatientItem newPatient) {
	}
}

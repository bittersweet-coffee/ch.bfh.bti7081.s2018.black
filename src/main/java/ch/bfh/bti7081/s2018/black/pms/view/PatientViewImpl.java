package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

public class PatientViewImpl extends PmsCustomComponent implements View, PatientView {

	public static final String NAME = "patient";
	
	private List<PatientViewListener> listeners = new ArrayList<PatientViewListener>();
	
	private Map<Integer, String> patientList;
	
	NativeSelect<String> nativePatient;

	public PatientViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		this.nativePatient = new NativeSelect<>();
		this.patientList = new HashMap();
        
        this.nativePatient.setVisibleItemCount(10);
        this.nativePatient.setItems(this.patientList.values());
        this.nativePatient.setEmptySelectionAllowed(false);
        this.nativePatient.setWidth("80%");
		
		for (PatientViewListener listener: listeners) {
    		listener.setupPatientList();
		}
		
		Label lblFilter = new Label("Filter:");
		TextField txtSearch = new TextField();
		Button btnSearch = new Button("Search");
		
		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout searchLayout = new HorizontalLayout();
		
		searchLayout.addComponents(txtSearch, btnSearch);
				
		Button btnOpen = new Button("Open");
		btnOpen.setEnabled(false);	
		Button btnNewPatient = new Button("New Patient");
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponents(this.nativePatient, btnOpen, btnNewPatient);
		
		vLayout.addComponents(lblFilter, searchLayout, hLayout);
		vLayout.setWidth("100%");
	
		super.contentPanel.setSizeUndefined();
		super.contentPanel.setContent(vLayout);
        
		
		
		
		
		btnOpen.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				patientOpenWindow();
			}
		});
		
		
		btnNewPatient.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				patientNewWindow();
			}
		});
		
		this.nativePatient.addValueChangeListener(selected -> {
			if(this.nativePatient.getSelectedItem().isPresent()) {
				btnOpen.setEnabled(true);
			} else {
				btnOpen.setEnabled(false);
			}
				
		});
		
		
		
        btnSearch.addClickListener(click -> {
        	System.out.println("ViewImpl: " + txtSearch.getValue());
        	if(this.nativePatient.getSelectedItem().isPresent() || !txtSearch.isEmpty()) {
        		this.nativePatient.setSelectedItem(null);
        	}
        	btnOpen.setEnabled(false);
        	for (PatientViewListener listener: listeners) {
        		listener.searchButtonClicked(txtSearch.getValue());
        	}
        });
        
        
        //lblFilter.setStyleName("patient-view-positions");
        //txtSearch.setPlaceholder("Insert searchterm");
        //txtSearch.setMaxLength(20);
        //txtSearch.setStyleName("patient-view-positions");
        //btnSearch.setStyleName("patient-view-positions");
        //List<String> data = IntStream.range(0, 10).mapToObj(i -> "Option " + i).collect(Collectors.toList());
        
		
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
        
	/*
		
		HorizontalLayout hBoxBottom = new HorizontalLayout();
		//hBoxBottom.setStyleName("patient-view-positions");
		hBoxBottom.addComponents(btnNewPatient, btnOpen);
		
		
		GridLayout tileGridTop = new GridLayout(2,2);
		tileGridTop.addComponent(lblFilter, 0, 0);
		tileGridTop.addComponent(txtSearch, 0, 1);
		tileGridTop.addComponent(btnSearch, 1, 1);
		
		GridLayout tileGridMiddle = new GridLayout(1,1);
		tileGridMiddle.addComponent(this.nativePatient, 0, 0);
		
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
*/

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

	@Override
	public void setupPatientList(Map<Integer, String> patientList) {
		this.nativePatient.setItems(patientList.values());
		
	}

	@Override
	public void addListener(PatientViewListener listener) {
		this.listeners.add(listener);
	}
	
	
	

}

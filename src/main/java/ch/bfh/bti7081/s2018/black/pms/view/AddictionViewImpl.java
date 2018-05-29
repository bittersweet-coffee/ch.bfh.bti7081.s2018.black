package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AddictionViewImpl extends PmsCustomComponent implements View, AddictionView {

	public static final String NAME = "addiction";
	
	private List<AddictionViewListener> listeners = new ArrayList<AddictionViewListener>();
	
	private List<String> patientList;
	
	private NativeSelect<String> nativeAddict;
	
	private Label lblAddictNameTitle, lblAddictDescTitle, lblSymptoms;
	
	private TextArea txtAddictName, txtAddictDesc, txtSymptoms;

	public AddictionViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		this.nativeAddict = new NativeSelect<>();
		this.patientList = new LinkedList<>();
		
		for (AddictionViewListener listener: listeners) {
			this.nativeAddict.setItems(listener.setupAddictList());
		}
		
        TextField txtSearch = new TextField("Filter:");
        txtSearch.setTabIndex(1);
        txtSearch.focus();
        Button btnSearch = new Button("Search");
        btnSearch.setTabIndex(2);
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.addComponents(txtSearch, btnSearch);
        searchLayout.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
        searchLayout.setMargin(new MarginInfo(false, false, true, true));
        
        HorizontalLayout hLayout = new HorizontalLayout();
        
        this.nativeAddict.setTabIndex(3);
        this.nativeAddict.setVisibleItemCount(10);
        this.nativeAddict.setEmptySelectionAllowed(false);
        this.nativeAddict.setWidth("80%");
        this.nativeAddict.setHeight("100%");
        
        VerticalLayout addictDetails = new VerticalLayout();
        this.lblAddictNameTitle = new Label("Name:");
        this.lblAddictDescTitle = new Label("Description:");
        this.txtAddictName = new TextArea();
        this.txtAddictName.setReadOnly(true);
        this.txtAddictName.setRows(1);
        this.txtAddictName.setWidth("100%");
        this.lblSymptoms = new Label("Symptoms:");
        
        this.txtAddictDesc = new TextArea();
        this.txtAddictDesc.setWidth("100%");
        this.txtAddictDesc.setReadOnly(true);
        
        this.txtSymptoms = new TextArea();
        this.txtSymptoms.setWidth("100%");
        this.txtSymptoms.setReadOnly(true);
        
        addictDetails.addComponents(
        		lblAddictNameTitle,
        		txtAddictName,
        		lblAddictDescTitle,
        		txtAddictDesc,
        		lblSymptoms,
        		txtSymptoms
        		);
        addictDetails.setMargin(false);
        
        Button btnAddTo = new Button("Add To");
        btnAddTo.setEnabled(false);
        
        hLayout.addComponents(
        		nativeAddict, 
        		addictDetails, 
        		btnAddTo);
        
        hLayout.setWidth("100%");
        hLayout.setMargin(new MarginInfo(false, true, true, true));
        hLayout.setComponentAlignment(btnAddTo, Alignment.BOTTOM_RIGHT);
        hLayout.setComponentAlignment(nativeAddict, Alignment.TOP_LEFT);
        hLayout.setComponentAlignment(addictDetails, Alignment.TOP_CENTER);
        
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponents(searchLayout, hLayout);
        
        VerticalLayout allocateContent = new VerticalLayout();
        VerticalLayout marginLayout = new VerticalLayout();
        HorizontalLayout patientLayout = new HorizontalLayout();
        
        Label lblPatient = new Label("Patient:");
        Label lblSelectedAddict = new Label();
        
        NativeSelect<String> nativePatient = new NativeSelect<>();
        nativePatient.setWidth(300.0f, Unit.PIXELS);
        nativePatient.setEmptySelectionAllowed(false);
        
        patientLayout.addComponents(lblPatient, nativePatient);
        patientLayout.setMargin(new MarginInfo(true, false, true, false));
        
        Button btnPatient = new Button("Allocate");
        
        marginLayout.addComponents(lblSelectedAddict, patientLayout, btnPatient);
        marginLayout.setMargin(true);
        
        allocateContent.addComponent(marginLayout);
        allocateContent.setMargin(true);
        
        final Window windowPatient = new Window("Allocate to Patient");
        windowPatient.setWidth(600.0f, Unit.PIXELS);
        windowPatient.setContent(allocateContent);
        windowPatient.setModal(true);
        
        // Set content
        super.contentPanel.setContent(vLayout);
        
        btnSearch.addClickListener(click -> {
        	if(this.nativeAddict.getSelectedItem().isPresent() || !txtSearch.isEmpty()) {
        		this.nativeAddict.setSelectedItem(null);
        	}
        	btnAddTo.setEnabled(false);
        	this.txtAddictName.setValue("");
        	this.txtAddictDesc.setValue("");
        	this.txtSymptoms.setValue("");
        	for (AddictionViewListener listener: listeners) {
        		this.nativeAddict.setItems(listener.searchButtonClicked(txtSearch.getValue()));
        	}
        });
        
        btnAddTo.addClickListener(click -> {
			for (AddictionViewListener listener: listeners) {
        		this.patientList = listener.addToButtonClicked();
			}
        	super.contentPanel.getUI().getUI().addWindow(windowPatient);
        	lblSelectedAddict.setValue("Selected Addiction: " + this.nativeAddict.getSelectedItem().get());
        	nativePatient.setItems(this.patientList);
        });
    
		this.nativeAddict.addValueChangeListener(selected -> {
			for (AddictionViewListener listener: listeners) {
        		if(this.nativeAddict.getSelectedItem().isPresent()) {
        			List<String> addictDetailList = listener.selectListChanged(this.nativeAddict.getSelectedItem().get());
        			
        			this.txtAddictName.setValue(selected.getValue());
        			this.txtAddictDesc.setValue(addictDetailList.get(0));
        			this.txtSymptoms.setValue(addictDetailList.get(1));
        			
        		}
			}	
			btnAddTo.setEnabled(true);
		});
		
		btnPatient.addClickListener(click -> {
			if(nativePatient.getSelectedItem().isPresent()) {
				for (AddictionViewListener listener: listeners)
	        		listener.allocateButtonClicked(nativeAddict.getSelectedItem().get(),
	        				nativePatient.getSelectedItem().get());
			} else {
				Notification.show("Input Data Incomplete");
			}
			
		});
		
		ShortcutListener enterSearchListener = new ShortcutListener("Enter Search Listener", ShortcutAction.KeyCode.ENTER, null) {
			@Override
			public void handleAction(Object sender, Object target) {
				btnSearch.click();
			}
		};
		
		txtSearch.addShortcutListener(enterSearchListener);
		btnSearch.addShortcutListener(enterSearchListener);
	}
	
	@Override
	public void addListener(AddictionViewListener listener) {
		this.listeners.add(listener);
	}
	
	/*
	
	@Override
	public void setupAddictList(List<String> addictionList) {
		this.nativeAddict.setItems(addictionList);
	}
	
	

	@Override
	public void setListDesc(String desc) {
		this.txtAddictDesc.setValue(desc);
	}

	@Override
	public void setListSymptoms(String symptoms) {
		this.txtSymptoms.setValue(symptoms);
	}

	@Override
	public void setupPatientList(List<String> patientList) {
		this.patientList = patientList;
	}
	
	*/
}

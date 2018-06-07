package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ClinicViewImpl extends PmsCustomComponent implements View, ClinicView {

	public static final String NAME = "clinic";
	
	private List<ClinicViewListener> listeners = new ArrayList<ClinicViewListener>();
	
	private List<String> patientList;
	
	private NativeSelect<String> nativeClinic;
	
	private Label lblClinicNameTitle, lblCity, lblPostCode, lblStreet, lblTelephone;
	
	private TextArea txtClinicName, txtCityName, txtPostCode, txtStreet, txtTelephone;

	public ClinicViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		this.nativeClinic = new NativeSelect<>();
		this.patientList = new LinkedList<>();
		
		/*
		for (ClinicViewListener listener: listeners) {
			this.nativeClinic.setItems(listener.setupAddictList());
		}
		*/
		
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
        
        this.nativeClinic.setTabIndex(3);
        this.nativeClinic.setVisibleItemCount(10);
        this.nativeClinic.setEmptySelectionAllowed(false);
        this.nativeClinic.setWidth("80%");
        this.nativeClinic.setHeight("100%");
        
        VerticalLayout addictDetails = new VerticalLayout();
        this.lblClinicNameTitle = new Label("Name:");
        this.txtClinicName = new TextArea();
        this.txtClinicName.setReadOnly(true);
        this.txtClinicName.setRows(1);
        this.txtClinicName.setWidth("100%");
        
        this.lblStreet = new Label("Street:");
        this.txtStreet = new TextArea();
        this.txtStreet.setReadOnly(true);
        this.txtStreet.setRows(1);
        this.txtStreet.setWidth("100%");
        
        this.lblCity = new Label("City:");
        this.txtCityName = new TextArea();
        this.txtCityName.setReadOnly(true);
        this.txtCityName.setRows(1);
        this.txtCityName.setWidth("100%");
                   
        this.lblPostCode = new Label("PostCode:");
        this.txtPostCode = new TextArea();
        this.txtPostCode.setReadOnly(true);
        this.txtPostCode.setRows(1);
        this.txtPostCode.setWidth("100%");

        this.lblTelephone = new Label("Telephone:");
        this.txtTelephone = new TextArea();
        this.txtTelephone.setReadOnly(true);
        this.txtTelephone.setRows(1);
        this.txtTelephone.setWidth("100%");
        
        addictDetails.addComponents(
        		lblClinicNameTitle,
        		txtClinicName,
        		lblStreet,
        		txtStreet,
        		lblCity,
        		txtCityName,
        		lblPostCode,
        		txtPostCode,
        		lblTelephone,
        		txtTelephone
        		);
        addictDetails.setMargin(false);
        
        Button btnAddTo = new Button("Mail To");
        btnAddTo.setEnabled(false);
        
        hLayout.addComponents(
        		nativeClinic, 
        		addictDetails, 
        		btnAddTo
        		);
        
        hLayout.setWidth("100%");
        hLayout.setMargin(new MarginInfo(false, true, true, true));
        hLayout.setComponentAlignment(btnAddTo, Alignment.BOTTOM_RIGHT);
        hLayout.setComponentAlignment(nativeClinic, Alignment.TOP_LEFT);
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
        
        /*
        btnSearch.addClickListener(click -> {
        	if (this.nativeClinic.getSelectedItem().isPresent() || !txtSearch.isEmpty()) {
        		this.nativeClinic.setSelectedItem(null);
        	}
        	btnAddTo.setEnabled(false);
        	this.txtClinicName.setValue("");
        	this.txtCityName.setValue("");
        	this.txtPostCode.setValue("");
        	for (ClinicViewListener listener: listeners) {
        		this.nativeClinic.setItems(listener.searchButtonClicked(txtSearch.getValue()));
        	}
        });
        
        btnAddTo.addClickListener(click -> {
			for (ClinicViewListener listener: listeners) {
        		this.patientList = listener.addToButtonClicked();
			}
        	super.contentPanel.getUI().getUI().addWindow(windowPatient);
        	lblSelectedAddict.setValue("Selected Addiction: " + this.nativeClinic.getSelectedItem().get());
        	nativePatient.setItems(this.patientList);
        });
    
		this.nativeClinic.addValueChangeListener(selected -> {
			for (ClinicViewListener listener: listeners) {
        		if(this.nativeClinic.getSelectedItem().isPresent()) {
        			List<String> addictDetailList = listener.selectListChanged(this.nativeClinic.getSelectedItem().get());
        			
        			this.txtClinicName.setValue(selected.getValue());
        			this.txtCityName.setValue(addictDetailList.get(0));
        			this.txtPostCode.setValue(addictDetailList.get(1));
        			
        		}
			}	
			btnAddTo.setEnabled(true);
		});
		
		btnPatient.addClickListener(click -> {
			if (nativePatient.getSelectedItem().isPresent()) {
				for (ClinicViewListener listener: listeners)
	        		listener.allocateButtonClicked(nativeClinic.getSelectedItem().get(),
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
	public void addListener(ClinicViewListener listener) {
		this.listeners.add(listener);
	}
	*/
	}
	
}

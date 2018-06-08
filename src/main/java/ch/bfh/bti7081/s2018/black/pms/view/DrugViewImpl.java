package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Grid.SelectionMode;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;



public class DrugViewImpl extends PmsCustomComponent implements View, DrugView {

	public static final String NAME = "drug";
	
	private List<DrugViewListener> listeners = new ArrayList<DrugViewListener>();
	
	private List<String> patientList;
	
	private List<PatientItem> patientItemList;
	
	private NativeSelect<String> nativeDrug;
	
	private Grid<PatientItem> patientItemGrid;
	
	private ListDataProvider<PatientItem> patientProvider;
	
	private Label lblDrugNameTitle, lblDrugDescTitle;
	
	private TextArea txtDrugName, txtDrugDesc;

	public DrugViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		this.nativeDrug = new NativeSelect<>();
		
		for (DrugViewListener listener: listeners) {
			this.nativeDrug.setItems(listener.setupDrugList());
		}
		
        TextField txtSearch = new TextField("Filter:");
        txtSearch.setTabIndex(1);
        txtSearch.focus();
        Button btnSearch = new Button("Search");
        btnSearch.setTabIndex(2);
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.addComponents(txtSearch, btnSearch);
        searchLayout.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
        searchLayout.setMargin(new MarginInfo(false, false, true, false));
        
        HorizontalLayout hLayout = new HorizontalLayout();
        
        this.nativeDrug.setTabIndex(3);
        this.nativeDrug.setVisibleItemCount(10);
        this.nativeDrug.setEmptySelectionAllowed(false);
        this.nativeDrug.setWidth("80%");
        this.nativeDrug.setHeight("100%");
        
        VerticalLayout drugDetails = new VerticalLayout();
        this.lblDrugNameTitle = new Label("Name:");
        this.lblDrugDescTitle = new Label("Description:");
        this.txtDrugName = new TextArea();
        this.txtDrugName.setReadOnly(true);
        this.txtDrugName.setRows(1);
        this.txtDrugName.setWidth("100%");
        
        this.txtDrugDesc = new TextArea();
        this.txtDrugDesc.setWidth("100%");
        this.txtDrugDesc.setReadOnly(true);
        
        drugDetails.addComponents(
        		lblDrugNameTitle,
        		txtDrugName,
        		lblDrugDescTitle,
        		txtDrugDesc
        		);
        
        drugDetails.setMargin(false);
		
	
	    Button btnAddTo = new Button("Add To");
	    btnAddTo.setEnabled(false);
	    
	    hLayout.addComponents(nativeDrug, drugDetails, btnAddTo);
	    hLayout.setWidth("100%");
	    hLayout.setMargin(new MarginInfo(false, false, true, false));
	    hLayout.setComponentAlignment(btnAddTo, Alignment.BOTTOM_RIGHT);
	    hLayout.setComponentAlignment(nativeDrug, Alignment.TOP_LEFT);
	    hLayout.setComponentAlignment(drugDetails, Alignment.TOP_CENTER);
	    
	    VerticalLayout vLayout = new VerticalLayout();
	    vLayout.addComponents(searchLayout, hLayout);
	    
	    VerticalLayout allocateContent = new VerticalLayout();
	    VerticalLayout marginLayout = new VerticalLayout();
	    HorizontalLayout patientLayout = new HorizontalLayout();
	    
		this.patientItemGrid = new Grid<>();
		this.patientItemList = new LinkedList<>();
		
		patientItemGrid.addColumn(PatientItem::getId).setCaption("ID");
		patientItemGrid.addColumn(PatientItem::getFirstName).setCaption("Firstname");
		patientItemGrid.addColumn(PatientItem::getLastName).setCaption("Lastname");
	    
		updatePatientItemList();
		patientProvider = DataProvider.ofCollection(patientItemList);
		patientProvider.refreshAll();
		
		patientProvider.withConfigurableFilter();
		
		patientItemGrid.setDataProvider(patientProvider);
		patientItemGrid.setSelectionMode(SelectionMode.SINGLE);
		patientItemGrid.setWidth(800.0f, Unit.PIXELS);
	    
		TextField txtFilter = new TextField();
		txtFilter.setPlaceholder("Filter by Firstname or Lastname");
		txtFilter.setWidth(735.0f, Unit.PIXELS);
	    
		txtFilter.addValueChangeListener(action -> {
			patientProvider.setFilter(name -> {
				String firstNameLower = name.getFirstName().toLowerCase();
				String lastNameLower = name.getLastName().toLowerCase();
				String filterLower = action.getValue().toLowerCase();
				return firstNameLower.contains(filterLower) || lastNameLower.contains(filterLower);
		
			});
		});
	    
		
	    Label lblPatient = new Label("Patient:");
	    Label lblSelectedDrug = new Label();
	    
	    patientLayout.addComponents(lblPatient, txtFilter);
	    patientLayout.setMargin(new MarginInfo(true, false, false, false));
	    
	    Button btnPatient = new Button("Allocate");
	    btnPatient.setEnabled(false);
	    
	    marginLayout.addComponents(lblSelectedDrug, patientLayout, patientItemGrid, btnPatient);
	    marginLayout.setMargin(true);
	    
	    allocateContent.addComponent(marginLayout);
	    allocateContent.setMargin(true);
	    
	    final Window windowPatient = new Window("Allocate to Patient");
	    windowPatient.setWidth(900.0f, Unit.PIXELS);
	    windowPatient.setContent(allocateContent);
	    windowPatient.setModal(true);
	    
	    // Set content
	    super.contentPanel.setContent(vLayout);
	    
	    
        btnSearch.addClickListener(click -> {
        	if (this.nativeDrug.getSelectedItem().isPresent() || !txtSearch.isEmpty()) {
        		this.nativeDrug.setSelectedItem(null);
        	}
        	btnAddTo.setEnabled(false);
        	this.txtDrugName.setValue("");
        	this.txtDrugDesc.setValue("");
        	for (DrugViewListener listener: listeners) {
        		this.nativeDrug.setItems(listener.searchButtonClicked(txtSearch.getValue()));
        	}
        });
        
        btnAddTo.addClickListener(click -> {
			for (DrugViewListener listener: listeners) {
        		this.patientList = listener.addToButtonClicked();
			}
        	super.contentPanel.getUI().getUI().addWindow(windowPatient);
        	lblSelectedDrug.setValue("Selected Drug: " + this.nativeDrug.getSelectedItem().get());
        });
    
		this.nativeDrug.addValueChangeListener(selected -> {
			for (DrugViewListener listener: listeners) {
        		if(this.nativeDrug.getSelectedItem().isPresent()) {
        			List<String> addictDetailList = listener.selectListChanged(this.nativeDrug.getSelectedItem().get());
        			
        			this.txtDrugName.setValue(selected.getValue());
        			this.txtDrugDesc.setValue(addictDetailList.get(0));
        			
        		}
			}	
			btnAddTo.setEnabled(true);
		});
		
		btnPatient.addClickListener(click -> {
			if (patientItemGrid.getSelectedItems().iterator().hasNext()) {
				for (DrugViewListener listener: listeners) {
					listener.allocateButtonClicked(nativeDrug.getSelectedItem().get(),
	        				patientItemGrid.getSelectedItems().iterator().next());
				}
	        		
			} else {
				Notification.show("Input Data Incomplete");
			}
			
		});
		
		patientItemGrid.addSelectionListener(selection -> {
			
			if (patientItemGrid.getSelectedItems().iterator().hasNext()) {
				btnPatient.setEnabled(true);
			} else {
				btnPatient.setEnabled(false);
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
	public void addListener(DrugViewListener listener) {
		this.listeners.add(listener);
	}
	
	private void updatePatientItemList() {
		for (DrugViewListener listener: listeners) {
			this.patientItemList = listener.setupPatientItemList();
		}
	}
	
}
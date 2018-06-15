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
import com.vaadin.server.VaadinSession;
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

/**
 * AddictionViewImpl Class
 * View Implementation of AddictionView
 */
public class AddictionViewImpl extends PmsCustomComponent implements View, AddictionView {
	
	// Identifier used for displaying the correct URL
	public static final String NAME = "addiction";
	
	// List containing all listeners for this object (mostly the corresponding Presenter Class)
	private List<AddictionViewListener> listeners = new ArrayList<AddictionViewListener>();
	
	// List containing Mock Objects for the PatientModel
	private List<PatientItem> patientItemList;
	
	// UI element displaying the addiction names on the left side of the UI
	private NativeSelect<String> nativeAddict;
	
	// Grid displaying all patients of the PMS
	// used for allocation of a drug to a patient
	// provides filter capabilities
	private Grid<PatientItem> patientItemGrid;
	
	// DataProvider used to populate the patientItemGrid
	private ListDataProvider<PatientItem> patientProvider;
	
	// Window which pops up when an allocation is to be done
	// contains the patientItemGrid
	private Window windowPatient;
	
	// labels used for describing Addiction Properties
	private Label lblAddictNameTitle, lblAddictDescTitle, lblSymptoms;
	
	// TextAreas used for displaying Addiction Properties
	private TextArea txtAddictName, txtAddictDesc, txtSymptoms;

	/**
	 * Default Constructor like all other ViewImplementations to trigger the super-class constructor  
	 */
	public AddictionViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		super.bC.makeCrumbs(AddictionViewImpl.NAME);
    	super.bC.visibleBreadcrumbs();
		super.menuBar.getItems().get(1).setText((String) VaadinSession.getCurrent().getAttribute("username"));
		this.nativeAddict = new NativeSelect<>();
		
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
        		btnAddTo
        		);
        
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
		txtFilter.focus();
	    
		txtFilter.addValueChangeListener(action -> {
			patientProvider.setFilter(name -> {
				String firstNameLower = name.getFirstName().toLowerCase();
				String lastNameLower = name.getLastName().toLowerCase();
				String filterLower = action.getValue().toLowerCase();
				patientItemGrid.deselectAll();
				return firstNameLower.contains(filterLower) || lastNameLower.contains(filterLower);
			});
		});        
        
        Label lblPatient = new Label("Patient:");
        Label lblSelectedAddict = new Label();
        
	    patientLayout.addComponents(lblPatient, txtFilter);
	    patientLayout.setMargin(new MarginInfo(true, false, false, false));
	    
	    Button btnAllocate = new Button("Allocate");
	    btnAllocate.setEnabled(false);
	    
	    marginLayout.addComponents(lblSelectedAddict, patientLayout, patientItemGrid, btnAllocate);
	    marginLayout.setMargin(true);
	    
	    allocateContent.addComponent(marginLayout);
	    allocateContent.setMargin(true);
	    
        windowPatient = new Window("Allocate to Patient");
        windowPatient.setWidth(900.0f, Unit.PIXELS);
        windowPatient.setContent(allocateContent);
        windowPatient.setModal(true);
        
        
        // Set content
        super.contentPanel.setContent(vLayout);
        
        btnSearch.addClickListener(click -> {
        	if (this.nativeAddict.getSelectedItem().isPresent() || !txtSearch.isEmpty()) {
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
        	super.contentPanel.getUI().getUI().addWindow(windowPatient);
        	lblSelectedAddict.setValue("Selected Addiction: " + this.nativeAddict.getSelectedItem().get());
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
		
		btnAllocate.addClickListener(click -> {
			if (patientItemGrid.getSelectedItems().iterator().hasNext()) {
				for (AddictionViewListener listener: listeners) {
					if (listener.allocateButtonClicked(nativeAddict.getSelectedItem().get(),
	        				patientItemGrid.getSelectedItems().iterator().next())) {
						this.windowPatient.close();
					} else {
						Notification.show("Warning", "The selected addiction has already been assigned to the patient!", Notification.TYPE_ERROR_MESSAGE);
					}
				}
			} else {
				Notification.show("Input Data Incomplete");
			}
		});
		
		patientItemGrid.addSelectionListener(selection -> {
			if (patientItemGrid.getSelectedItems().iterator().hasNext()) {
				btnAllocate.setEnabled(true);
			} else {
				btnAllocate.setEnabled(false);
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
		
		txtFilter.addShortcutListener(enterSearchListener);
		btnAllocate.addShortcutListener(enterSearchListener);
	}
	
	@Override
	public void addListener(AddictionViewListener listener) {
		this.listeners.add(listener);
	}
	
	/**
	 * Method used by the patiemtItemGrid to update its DataProvider 
	 */
	private void updatePatientItemList() {
		for (AddictionViewListener listener: listeners) {
			this.patientItemList = listener.setupPatientItemList();
		}
	}
}

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

import ch.bfh.bti7081.s2018.black.pms.model.Pair;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;


/**
 * DrugViewImpl Class
 * View Implementation of DrugView
 * @author schaa4
 *
 */
public class DrugViewImpl extends PmsCustomComponent implements View, DrugView {

	// identifier used for displaying the correct URL
	public static final String NAME = "drug";
	
	// List containing all listeners for this object (mostly the corresponding Presenter Class)
	private List<DrugViewListener> listeners = new ArrayList<DrugViewListener>();
	
	// List containing Mock Objects for the PatientModel
	private List<PatientItem> patientItemList;
	
	// UI element displaying the addiction names on the left side of the UI
	private NativeSelect<String> nativeDrug;
	
	// Grid displaying all patients of the PMS
	// used for allocation of a drug to a patient
	// provides filter capabilities
	private Grid<PatientItem> patientItemGrid;
	
	// DataProvider used to populate the patientItemGrid
	private ListDataProvider<PatientItem> patientProvider;
	
	// Window which pops up when an allocation is to be done
	// contains the patientItemGrid
	private Window windowPatient;
	
	// labels used for describing Drug Properties
	private Label lblDrugNameTitle, lblDrugDescTitle;
	
	// TextAreas used for displaying Drug Properties
	private TextArea txtDrugName, txtDrugDesc;

	/**
	 * Default Constructor like all other ViewImplementations to trigger the super-class constructor  
	 */
	public DrugViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		super.bC.makeCrumbs(DrugViewImpl.NAME);
		super.bC.visibleBreadcrumbs();
		super.menuBar.getItems().get(1).setText((String) VaadinSession.getCurrent().getAttribute("username"));
		Label test = new Label("Drug here");
        super.contentPanel.setContent(test);
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
	    HorizontalLayout doseLayout = new HorizontalLayout();
	    
		this.patientItemGrid = new Grid<>();
		this.patientItemList = new LinkedList<>();
		
		patientItemGrid.addColumn(PatientItem::getId).setCaption("ID");
		patientItemGrid.addColumn(PatientItem::getFirstName).setCaption("Firstname");
		patientItemGrid.addColumn(PatientItem::getLastName).setCaption("Lastname");
		patientItemGrid.addColumn(PatientItem::getBirthdayAsString).setCaption("Birthday");
	    
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
				patientItemGrid.deselectAll();
				return firstNameLower.contains(filterLower) || lastNameLower.contains(filterLower);		
			});
		});
		
	    Label lblPatient = new Label("Patient:");
	    Label lblSelectedDrug = new Label();
	    
	    Label lblDose = new Label("Enter Dose:");
	    Label lblMeasurement = new Label("Measurement:");
	    
	    TextField txtDose = new TextField();
	    txtDose.setPlaceholder("Dose");
	    txtDose.setRequiredIndicatorVisible(true);
	    
	    doseLayout.addComponents(lblDose, txtDose);
	    doseLayout.setMargin(new MarginInfo(true, false, false, false));
	    
	    patientLayout.addComponents(lblPatient, txtFilter);
	    patientLayout.setMargin(new MarginInfo(true, false, false, false));
	    
	    Button btnAllocatePatient = new Button("Allocate");
	    btnAllocatePatient.setEnabled(false);
	    
	    marginLayout.addComponents(lblSelectedDrug, lblMeasurement, doseLayout, patientLayout, patientItemGrid, btnAllocatePatient);
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
		
		btnAllocatePatient.addClickListener(click -> {
			if (patientItemGrid.getSelectedItems().iterator().hasNext()) {
				if(isDouble(txtDose.getValue())) {
					for (DrugViewListener listener: listeners) {
						
						Pair result = listener.allocateButtonClicked(nativeDrug.getSelectedItem().get(),
		        				patientItemGrid.getSelectedItems().iterator().next(), Double.parseDouble(txtDose.getValue()));
						
						
						if(result.getResult()) {
							this.windowPatient.close();
						} else {
							Notification.show("Warning", result.getMessage(), Notification.TYPE_ERROR_MESSAGE);
						}
					}
				} else {
					Notification.show("Warning", "Please enter a Dose of type Double and less than 7 decimal places!", Notification.TYPE_ERROR_MESSAGE);
				}
			} else {
				Notification.show("Input Data Incomplete");
			}
		});
		
		patientItemGrid.addSelectionListener(selection -> {
			if (patientItemGrid.getSelectedItems().iterator().hasNext()) {
				btnAllocatePatient.setEnabled(true);
			} else {
				btnAllocatePatient.setEnabled(false);
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
	
	/**
	 * Helper Method to detect whether entered Dose is a valid Double number.
	 * Valid means: Standard Double format & less than 7 decimal places
	 * @param str Entered Dose retrieved as String
	 * @return boolean response whether entered Dose is a valid Double number
	 */
	private boolean isDouble(String str) {
		  try{
			// try to parse entered Dose to Double
		    Double.parseDouble(str);
		    
		    // No exception thrown to this point, so it is Double-parsable
		    // Check if entered number has less than 7 decimal places
		    if(str.contains(".")) {
		    	String[] splitted = str.split("\\.");
		    	
		    	if(splitted[1].length() < 6) {
		    		return true;
		    	} else {
		    		return false;
		    	}
		    }
		    	
		    return true;
		    
		  } catch(Exception e) {
		    return false;
		  }
	}

	@Override
	public void addListener(DrugViewListener listener) {
		this.listeners.add(listener);
	}
	
	/**
	 * Method used by the patiemtItemGrid to update its DataProvider 
	 */
	private void updatePatientItemList() {
		for (DrugViewListener listener: listeners) {
			this.patientItemList = listener.setupPatientItemList();
		}
	}

}
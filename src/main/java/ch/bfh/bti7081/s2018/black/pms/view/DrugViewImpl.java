package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;


public class DrugViewImpl extends PmsCustomComponent implements View, DrugView {

	public static final String NAME = "drug";
	
	private List<DrugViewListener> listeners = new ArrayList<DrugViewListener>();
	
	private NativeSelect<String> nativeDrug;
	
	private Label lblDrugNameTitle, lblDrugDescTitle;
	
	private TextArea txtDrugName, txtDrugDesc;

	public DrugViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		

		this.nativeDrug = new NativeSelect<>();
		
		for (DrugViewListener listener: listeners) {
    		//listener.setupDrugList();
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
	    
	    Label lblPatient = new Label("Patient:");
	    Label lblSelectedDrug = new Label();
	    
	    NativeSelect<String> nativePatient = new NativeSelect<>();
	    nativePatient.setWidth(300.0f, Unit.PIXELS);
	    nativePatient.setEmptySelectionAllowed(false);
	    
	    patientLayout.addComponents(lblPatient, nativePatient);
	    patientLayout.setMargin(new MarginInfo(true, false, true, false));
	    
	    Button btnPatient = new Button("Allocate");
	    
	    marginLayout.addComponents(lblSelectedDrug, patientLayout, btnPatient);
	    marginLayout.setMargin(true);
	    
	    allocateContent.addComponent(marginLayout);
	    allocateContent.setMargin(true);
	    
	    final Window windowPatient = new Window("Allocate to Patient");
	    windowPatient.setWidth(600.0f, Unit.PIXELS);
	    windowPatient.setContent(allocateContent);
	    windowPatient.setModal(true);
	    
	    // Set content
	    super.contentPanel.setContent(vLayout);
	    
	}

	@Override
	public void addListener(DrugViewListener listener) {
		// TODO Auto-generated method stub
		
	}
}

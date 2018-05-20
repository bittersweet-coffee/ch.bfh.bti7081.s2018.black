package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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

public class AddictionViewImpl extends PmsCustomComponent implements View, AddictionView {

	public static final String NAME = "addiction";
	
	private List<AddictionViewListener> listeners = new ArrayList<AddictionViewListener>();
	
	private List<String> mockListNames = new LinkedList<>();
	
	private Label lblAddictNameTitle, lblAddictDescTitle, lblAddictName, lblSymptoms;
	
	private TextArea txtAddictDesc, txtSymptoms;
	
	private NativeSelect<String> nativeAddict = new NativeSelect<>();;

	public AddictionViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
        
        TextField txtSearch = new TextField("Filter:");
        Button btnSearch = new Button("Search");
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.addComponents(txtSearch, btnSearch);
        searchLayout.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
        searchLayout.setMargin(new MarginInfo(false, false, true, false));
        
        HorizontalLayout hLayout = new HorizontalLayout();
        
        
        this.nativeAddict.setVisibleItemCount(10);
        this.nativeAddict.setEmptySelectionAllowed(false);
        
        this.nativeAddict.setWidth(300, Unit.PIXELS);
        
        VerticalLayout addictDetails = new VerticalLayout();
        this.lblAddictNameTitle = new Label("Name:");
        this.lblAddictDescTitle = new Label("Description:");
        this.lblAddictName = new Label("");
        this.lblSymptoms = new Label("Symptoms:");
        
        this.txtAddictDesc = new TextArea();
        this.txtAddictDesc.setWidth("100%");
        this.txtAddictDesc.setReadOnly(true);
        
        this.txtSymptoms = new TextArea();
        this.txtSymptoms.setWidth("100%");
        this.txtSymptoms.setReadOnly(true);
        
        addictDetails.addComponents(lblAddictNameTitle, lblAddictName, lblAddictDescTitle, txtAddictDesc, lblSymptoms, txtSymptoms);
        
        Button btnAddTo = new Button("Add To");
        btnAddTo.setEnabled(false);
        
        hLayout.addComponents(nativeAddict, addictDetails, btnAddTo);
        hLayout.setWidth("100%");
        hLayout.setComponentAlignment(btnAddTo, Alignment.BOTTOM_RIGHT);
        hLayout.setComponentAlignment(nativeAddict, Alignment.TOP_LEFT);
        hLayout.setComponentAlignment(addictDetails, Alignment.TOP_CENTER);
        
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponents(searchLayout, hLayout);
        
        VerticalLayout allocateContent = new VerticalLayout();
        VerticalLayout marginLayout = new VerticalLayout();
        HorizontalLayout patientLayout = new HorizontalLayout();
        
        Label lblPatient = new Label("Patient: ");
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
        
        
        super.contentPanel.setContent(vLayout);
        
        
        btnSearch.addClickListener(click -> {
        	for (AddictionViewListener listener: listeners)
        		listener.searchButtonClicked(txtSearch.getValue());
        });
        
        btnAddTo.addClickListener(click -> {
        	for (AddictionViewListener listener: listeners)
        		listener.addToButtonClicked(nativeAddict.getSelectedItem().get());
        	
        	super.contentPanel.getUI().getUI().addWindow(windowPatient);
        	lblSelectedAddict.setValue("Selected Addiction: " + nativeAddict.getSelectedItem().get());
        	
        });
    
		nativeAddict.addValueChangeListener(selected -> {
			for (AddictionViewListener listener: listeners)
        		listener.selectListChanged(nativeAddict.getSelectedItem().get());
			
			lblAddictName.setValue(selected.getValue());
			btnAddTo.setEnabled(true);
		});
	}

	@Override
	public void addListener(AddictionViewListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void setupNativeList(List<String> mockListNames) {
		this.nativeAddict.setItems(mockListNames);
	}

	@Override
	public void setListDesc(String desc) {
		this.txtAddictDesc.setValue(desc);
	}

	@Override
	public void setListSymptoms(String symptoms) {
		this.txtSymptoms.setValue(symptoms);
	}

	
}

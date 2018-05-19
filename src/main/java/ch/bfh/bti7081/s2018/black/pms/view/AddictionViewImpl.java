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
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2018.black.pms.util.JpaDemo;
import ch.bfh.bti7081.s2018.black.pms.util.JpaUtility;

public class AddictionViewImpl extends PmsCustomComponent implements View, AddictionView {

	public static final String NAME = "addiction";
	
	private List<AddictionViewListener> listeners = new ArrayList<AddictionViewListener>();
	
	private List<String> mockListNames = new LinkedList<>();
	
	private Label lblAddictNameTitle, lblAddictDescTitle, lblAddictName, lblSymptoms;
	
	private TextArea txtAddictDesc, txtSymptoms;

	public AddictionViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
        
        TextField txtSearch = new TextField("Filter:");
        Button btnSearch = new Button("Search");
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.addComponents(txtSearch, btnSearch);
        searchLayout.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
        
        HorizontalLayout hLayout = new HorizontalLayout();
        
        
        ListSelect<String> addictList = new ListSelect<>();
        addictList.setWidth(300, UNITS_PIXELS);
        
        VerticalLayout addictDetails = new VerticalLayout();
        this.lblAddictNameTitle = new Label("Name:");
        this.lblAddictDescTitle = new Label("Description:");
        this.lblAddictName = new Label("Test");
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
        
        hLayout.addComponents(addictList, addictDetails, btnAddTo);
        hLayout.setWidth("100%");
        hLayout.setComponentAlignment(btnAddTo, Alignment.BOTTOM_RIGHT);
        hLayout.setComponentAlignment(addictList, Alignment.MIDDLE_LEFT);
        
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponents(searchLayout, hLayout);
        
        
        super.contentPanel.setContent(vLayout);
        
        addictList.setItems(mockListNames);
        
        btnSearch.addClickListener(click -> {
        	for (AddictionViewListener listener: listeners)
        		listener.searchButtonClicked(txtSearch.getCaption());
        });
        
        btnAddTo.addClickListener(click -> {
        	for (AddictionViewListener listener: listeners)
        		listener.addToButtonClicked(addictList.getSelectedItems().iterator().next());
        });
    
		addictList.addValueChangeListener(selected -> {
			for (AddictionViewListener listener: listeners)
        		listener.selectListChanged(addictList.getSelectedItems().iterator().next());
			
			lblAddictName.setValue(selected.getValue().iterator().next());
			btnAddTo.setEnabled(true);
		});
		
        //JpaUtility.persist(addiction);
        //JpaDemo.testAddiction();
	}

	@Override
	public void addListener(AddictionViewListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void setMockListNames(List<String> mockListNames) {
		this.mockListNames = mockListNames;
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

package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;

public class PatientViewImpl extends PmsCustomComponent implements View, PatientView {

	public static final String NAME = "patient";
	
	private List<PatientViewListener> listeners = new ArrayList<PatientViewListener>();
	
	private Map<Integer, String> patientList;
	
	private NativeSelect<String> nativePatient;

	public PatientViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		this.nativePatient = new NativeSelect<>();
		this.patientList = new HashMap<>();
        
        this.nativePatient.setVisibleItemCount(18);
        this.nativePatient.setEmptySelectionAllowed(false);
        this.nativePatient.setItems(this.patientList.values());

		this.nativePatient.setWidth("80%");
        
		for (PatientViewListener listener: listeners) {
			this.nativePatient.setItems(listener.setupPatientList().values());
		}
		
		Label lblFilter = new Label("Filter:");
		TextField txtSearch = new TextField();
		txtSearch.setPlaceholder("Insert Searchterm");
		Button btnSearch = new Button("Search");
		
		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout searchLayout = new HorizontalLayout();
		
		searchLayout.addComponents(txtSearch, btnSearch);
		searchLayout.setMargin(new MarginInfo(true, false, false, true));
				
		Button btnOpen = new Button("Open");
		btnOpen.setEnabled(false);	
		Button btnNewPatient = new Button("New Patient");
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponents(this.nativePatient, btnOpen, btnNewPatient);
		hLayout.setComponentAlignment(btnOpen, Alignment.BOTTOM_CENTER);
		hLayout.setComponentAlignment(btnNewPatient, Alignment.BOTTOM_RIGHT);
		hLayout.setWidth("100%");
		hLayout.setMargin(new MarginInfo(false, false, true, true));
		
		vLayout.addComponents(lblFilter, searchLayout, hLayout);
		vLayout.setWidth("100%");
		vLayout.setMargin(new MarginInfo(true));
	
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
			if (this.nativePatient.getSelectedItem().isPresent()) {
				btnOpen.setEnabled(true);
			} else {
				btnOpen.setEnabled(false);
			}
		});
		
        btnSearch.addClickListener(click -> {
        	if (this.nativePatient.getSelectedItem().isPresent() || !txtSearch.isEmpty()) {
        		this.nativePatient.setSelectedItem(null);
        	}
        	btnOpen.setEnabled(false);
        	for (PatientViewListener listener: listeners) {
        		this.nativePatient.setItems(listener.searchButtonClicked(txtSearch.getValue()).values());
        	}
        });
	}

	protected void patientNewWindow() {
		final PatientNewWindow window = new PatientNewWindow(this);   
		window.setModal(true);
		super.getUI().getUI().addWindow(window);
	}

	protected void patientOpenWindow() {
		final PatientOpenWindow window = new PatientOpenWindow(this, this.nativePatient.getSelectedItem().get());   
		window.setModal(true);
		super.getUI().getUI().addWindow(window);
	}

	public void save(PatientItem newPatient) {
	}
	
	@Override
	public void addListener(PatientViewListener listener) {
		this.listeners.add(listener);
	}
}

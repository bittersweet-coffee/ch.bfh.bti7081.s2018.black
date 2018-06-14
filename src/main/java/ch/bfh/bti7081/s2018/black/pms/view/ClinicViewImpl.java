package ch.bfh.bti7081.s2018.black.pms.view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * ClinicViewImpl Class
 * View Implementation of ClinicView
 * @author supnic
 *
 */
public class ClinicViewImpl extends PmsCustomComponent implements View, ClinicView {

	// identifier used for displaying the correct URL
	public static final String NAME = "clinic";
	
	// String to store the email-address
	private String email;
	
	// List containing all listeners for this object (mostly the corresponding Presenter Class)
	private List<ClinicViewListener> listeners = new ArrayList<ClinicViewListener>();
	
	// List containing Mock Objects for the PatientModel
	private List<String> patientList;
	
	// UI element displaying the clinic names on the left side of the UI
	private NativeSelect<String> nativeClinic;
	
	// Labels to describe the content in the textareas
	private Label lblClinicNameTitle, lblCity, lblPostCode, lblStreet, lblTelephone, lblAddictions;
	
	// Textareas which contain all important information about the clinic
	private TextArea txtClinicName, txtCityName, txtPostCode, txtStreet, txtTelephone, txtAddictions;
	
	final static Logger logger = Logger.getLogger(ClinicViewImpl.class);

	/**
	 * Default Constructor like all other ViewImplementations to trigger the super-class constructor  
	 */
	public ClinicViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		super.bC.makeCrumbs(ClinicViewImpl.NAME);
		super.bC.visibleBreadcrumbs();
		super.menuBar.getItems().get(1).setText((String) VaadinSession.getCurrent().getAttribute("username"));
		Label test = new Label("Clinic here");
        super.contentPanel.setContent(test);
		this.nativeClinic = new NativeSelect<>();
		this.patientList = new LinkedList<>();
		
		
		for (ClinicViewListener listener: listeners) {
			this.nativeClinic.setItems(listener.setupClinicList());
		}
		
		//Components for the clinic name search
        TextField txtSearchClinic = new TextField("Enter Clinic Name");
        txtSearchClinic.setTabIndex(1);
        txtSearchClinic.focus();
        
        //Components for the addiction-clinic search
        TextField txtSearchAddiction = new TextField("Enter Addiction Name");
        txtSearchAddiction.setEnabled(false);
        
        //Button to trigger the search
        Button btnSearch = new Button("Search");
        btnSearch.setTabIndex(2);
        
        //Radiobuttons to select the searchmethod
        RadioButtonGroup<String> searchMode = new RadioButtonGroup<>();
        searchMode.setItems("Clinic", "Addiction");
        searchMode.setSelectedItem("Clinic");
        
        //Layout to group the search components
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.addComponents(searchMode, txtSearchClinic, txtSearchAddiction, btnSearch);
        searchLayout.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
        searchLayout.setMargin(new MarginInfo(false, false, true, true));
        
        //Positioning the labels and textareas which contain the clinic information
        HorizontalLayout hLayout = new HorizontalLayout();
        
        this.nativeClinic.setTabIndex(3);
        this.nativeClinic.setVisibleItemCount(10);
        this.nativeClinic.setEmptySelectionAllowed(false);
        this.nativeClinic.setWidth("80%");
        this.nativeClinic.setHeight("100%");
        
        VerticalLayout clinictDetails = new VerticalLayout();
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
        
        clinictDetails.addComponents(
        		this.lblClinicNameTitle,
        		this.txtClinicName,
        		this.lblStreet,
        		this.txtStreet,
        		this.lblCity,
        		this.txtCityName,
        		this.lblPostCode,
        		this.txtPostCode,
        		this.lblTelephone,
        		this.txtTelephone
        		);
        clinictDetails.setMargin(false);
        
        //Components to display the informations about the addictions, which the clinic treats
        this.txtAddictions = new TextArea();
        this.txtAddictions.setWidth("100%");
        this.txtAddictions.setReadOnly(true);
        
        this.lblAddictions = new Label("Addictions treated:");
        
        Button btnMailTo = new Button("Mail To");
        btnMailTo.setEnabled(false);
        
        //Components for the clinicmailrequest
        VerticalLayout addictMailTo = new VerticalLayout();
        addictMailTo.setHeight("100%");
        addictMailTo.addComponents(this.lblAddictions, this.txtAddictions, btnMailTo);
        addictMailTo.setComponentAlignment(btnMailTo, Alignment.BOTTOM_RIGHT);
        addictMailTo.setComponentAlignment(this.lblAddictions, Alignment.TOP_LEFT);
        addictMailTo.setComponentAlignment(this.txtAddictions, Alignment.TOP_LEFT);
        
        hLayout.addComponents(
        		nativeClinic, 
        		clinictDetails,
        		addictMailTo
        		);
        
        hLayout.setWidth("100%");
        hLayout.setMargin(new MarginInfo(false, true, true, true));
        hLayout.setComponentAlignment(nativeClinic, Alignment.TOP_LEFT);
        hLayout.setComponentAlignment(clinictDetails, Alignment.TOP_CENTER);
        hLayout.setComponentAlignment(addictMailTo, Alignment.TOP_RIGHT);	
        
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponents(searchLayout, hLayout);
        
        VerticalLayout allocateContent = new VerticalLayout();
        VerticalLayout marginLayout = new VerticalLayout();
        
        Label lblMailError = new Label();        
        
        marginLayout.addComponents(lblMailError);
        marginLayout.setMargin(true);
        
        allocateContent.addComponent(marginLayout);
        allocateContent.setMargin(true);
        
        final Window windowPatient = new Window("Mail Request Error");
        windowPatient.setWidth(600.0f, Unit.PIXELS);
        windowPatient.setContent(allocateContent);
        windowPatient.setModal(true);
        
        // Set content
        super.contentPanel.setContent(vLayout);        

        searchMode.addValueChangeListener(change -> {
        	
        	if(String.valueOf(change.getValue()).equals("Clinic")) {
        		txtSearchClinic.setEnabled(true);
        		txtSearchAddiction.setEnabled(false);
        		txtSearchAddiction.setValue("");
        	} else {
        		txtSearchAddiction.setEnabled(true);
        		txtSearchClinic.setEnabled(false);
        		txtSearchClinic.setValue("");
        	}
        });
        
        //Generates the mailrequest
        btnMailTo.addClickListener(click -> {
            Desktop desktop;
            if (Desktop.isDesktopSupported() 
                && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
              URI mailto = null;
    		try {
    			mailto = new URI("mailto:" + this.email + "?subject=Request");
    		} catch (URISyntaxException e) {
    			logger.error(e);
    		}
              try {
    			desktop.mail(mailto);
    		} catch (IOException e) {
    			logger.error(e);
    		}
            } else {
            	super.contentPanel.getUI().getUI().addWindow(windowPatient);
            	lblMailError.setValue("Desktop doesn't support mailto; mail is dead anyway! ;)");
            	logger.error("Desktop doesn't support mailto; mail is dead anyway! ;)");
            	throw new RuntimeException("Desktop doesn't support mailto; mail is dead anyway! ;)");
            }
        });
        
        //Triggers the search
        btnSearch.addClickListener(click -> {
        	if (this.nativeClinic.getSelectedItem().isPresent() || (txtSearchClinic.isEnabled() && !txtSearchClinic.isEmpty()) || (txtSearchAddiction.isEnabled() && !txtSearchAddiction.isEmpty())) {
        		this.nativeClinic.setSelectedItem(null);
        	}
        	
        	btnMailTo.setEnabled(false);
        	this.txtClinicName.setValue("");
        	this.txtCityName.setValue("");
        	this.txtPostCode.setValue("");
        	this.txtStreet.setValue("");
        	this.txtTelephone.setValue("");
        	this.txtAddictions.setValue("");
        	
        	for (ClinicViewListener listener: listeners) {
        		
        		if(txtSearchClinic.isEnabled()) {
        			this.nativeClinic.setItems(listener.searchButtonClicked(txtSearchClinic.getValue(), searchMode.getSelectedItem().get()));
        		} else if(txtSearchAddiction.isEnabled()) {
        			this.nativeClinic.setItems(listener.searchButtonClicked(txtSearchAddiction.getValue(), searchMode.getSelectedItem().get()));
        		}
        		
        		
        	}
        });
    
        //Loads the new values
		this.nativeClinic.addValueChangeListener(selected -> {
			for (ClinicViewListener listener: listeners) {
        		if(this.nativeClinic.getSelectedItem().isPresent()) {
        			List<String> clinicDetailList = listener.selectListChanged(this.nativeClinic.getSelectedItem().get());
        			
        			this.txtClinicName.setValue(selected.getValue());
        			this.txtCityName.setValue(clinicDetailList.get(0));
        			this.txtPostCode.setValue(clinicDetailList.get(1));
        			this.txtStreet.setValue(clinicDetailList.get(2));
        			this.txtTelephone.setValue(clinicDetailList.get(3));
        			this.email = clinicDetailList.get(4);
        			this.txtAddictions.setValue(clinicDetailList.get(5));

        		}
			}	
			btnMailTo.setEnabled(true);
		});
		
		ShortcutListener enterSearchListener = new ShortcutListener("Enter Search Listener", ShortcutAction.KeyCode.ENTER, null) {
			@Override
			public void handleAction(Object sender, Object target) {
				btnSearch.click();
			}
		};
		
		txtSearchClinic.addShortcutListener(enterSearchListener);
		btnSearch.addShortcutListener(enterSearchListener);
	}
	
	@Override
	public void addListener(ClinicViewListener listener) {
		this.listeners.add(listener);
	}
	
}

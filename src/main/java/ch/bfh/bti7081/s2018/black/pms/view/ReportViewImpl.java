package ch.bfh.bti7081.s2018.black.pms.view;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.presenter.PdfSource;

/**
 * ReportViewImpl Class
 * View Implementation of ReportView
 * @author bielc1
 *
 */
public class ReportViewImpl extends PmsCustomComponent implements View, ReportView {

	// identifier used for displaying the correct URL
	public static final String NAME = "report";
	
	// List containing all listeners for this object (mostly the corresponding Presenter Class)
	private List<ReportViewListener> listeners = new ArrayList<ReportViewListener>();
	
	// List containing Mock Objects for the PatientModel
	private List<PatientItem> patientItemList;
	
	// Grid displaying all patients of the PMS
	// used for allocation of a drug to a patient
	// provides filter capabilities
	private Grid<PatientItem> patientItemGrid;
	
	// DataProvider used to populate the patientItemGrid
	private ListDataProvider<PatientItem> patientProvider;
	
	StreamSource source;
	StreamResource resource;
	BrowserWindowOpener opener;
	
	final static Logger logger = Logger.getLogger(ReportViewImpl.class);
	
	/**
	 * Default Constructor like all other ViewImplementations to trigger the super-class constructor  
	 */
	public ReportViewImpl() {
		super();
	}
	
	/**
	 * Called before the view is shown on screen. 
	 * The event object contains information about parameters used when showing the view,
	 * in addition to references to the old view and the new view.
	 */
	public void enter(ViewChangeEvent event) {
		super.bC.makeCrumbs(ReportViewImpl.NAME);
		super.bC.visibleBreadcrumbs();
		super.menuBar.getItems().get(1).setText((String) VaadinSession.getCurrent().getAttribute("username"));
		Label test = new Label("Report here");
        super.contentPanel.setContent(test);
		this.patientItemGrid = new Grid<>();
		this.patientItemList = new LinkedList<>();
		
		patientItemGrid = new Grid<>();
		patientItemGrid.addColumn(PatientItem::getId).setCaption("ID");
		patientItemGrid.addColumn(PatientItem::getFirstName).setCaption("Firstname");
		patientItemGrid.addColumn(PatientItem::getLastName).setCaption("Lastname");
		
		updatePatientItemList();
		patientProvider = DataProvider.ofCollection(patientItemList);
		patientProvider.refreshAll();
		
		patientProvider.withConfigurableFilter();
		
		patientItemGrid.setDataProvider(patientProvider);
		patientItemGrid.setSelectionMode(SelectionMode.SINGLE);
		
		TextField txtFilter = new TextField();
		txtFilter.setPlaceholder("Filter by first- or lastname");
		txtFilter.setWidth("30%");
		
		txtFilter.addValueChangeListener(action -> {
			patientProvider.setFilter(name -> {
				String firstNameLower = name.getFirstName().toLowerCase();
				String lastNameLower = name.getLastName().toLowerCase();
				String filterLower = action.getValue().toLowerCase();
				return firstNameLower.contains(filterLower) || lastNameLower.contains(filterLower);
		
			});
		});
		
		Label lblFilter = new Label("Filter:");
		
		VerticalLayout vLayout = new VerticalLayout();
		VerticalLayout searchLayout = new VerticalLayout();
		
		searchLayout.addComponents(lblFilter, txtFilter);
		searchLayout.setMargin(new MarginInfo(true, false, false, true));
		
		source = new PdfSource();


        String fileName = NAME + ".pdf";
        resource = new StreamResource(source, fileName);

        resource.setMIMEType("application/pdf");
        resource.getStream().setParameter("Content-Disposition", "attachment; filename=" + fileName);
        resource.setCacheTime(0);
        
        Button btnGenerateReport = new Button("Generate Report");
        btnGenerateReport.setEnabled(false);
        opener = new BrowserWindowOpener(resource);
        opener.extend(btnGenerateReport);
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponent(patientItemGrid);
		hLayout.addComponent(new Button());
		hLayout.addComponent(btnGenerateReport);
		hLayout.setComponentAlignment(btnGenerateReport, Alignment.BOTTOM_RIGHT);
		hLayout.setWidth("100%");
		hLayout.setMargin(new MarginInfo(false, false, true, true));
		
		vLayout.addComponents(lblFilter, searchLayout, hLayout);
		vLayout.setWidth("100%");
		vLayout.setMargin(new MarginInfo(true));
		
		super.contentPanel.setContent(vLayout);

		patientItemGrid.addSelectionListener(selection -> {
			if (selection.getFirstSelectedItem().isPresent()) {
				btnGenerateReport.setEnabled(true);
				try {
					source = new PdfSource(this.patientItemGrid.getSelectedItems().iterator().next());
				} catch (MalformedURLException e) {
					logger.error(e);
				}
				resource.setStreamSource(source);
			} else {
				btnGenerateReport.setEnabled(false);
			}
		});
	}
	
	/**
	 * Method used by the patiemtItemGrid to update its DataProvider 
	 */
	private void updatePatientItemList() {
		for (ReportViewListener listener: listeners) {
			this.patientItemList = listener.setupPatientItemList();
		}
	}

	@Override
	public void addListener(ReportViewListener listener) {
		this.listeners.add(listener);		
	}

}

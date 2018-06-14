package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionItem;
import ch.bfh.bti7081.s2018.black.pms.model.ClinicItem;
import ch.bfh.bti7081.s2018.black.pms.model.DoctorItem;
import ch.bfh.bti7081.s2018.black.pms.model.PatientItem;
import ch.bfh.bti7081.s2018.black.pms.presenter.AddictionPresenter;
import ch.bfh.bti7081.s2018.black.pms.presenter.ClinicPresenter;
import ch.bfh.bti7081.s2018.black.pms.presenter.DoctorPresenter;

public class PatientViewImpl extends PmsCustomComponent implements View, PatientView {

	public static final String NAME = "patient";

	private List<PatientViewListener> listeners = new ArrayList<PatientViewListener>();

	private List<PatientItem> patientItemList;

	private Grid<PatientItem> patientItemGrid;

	private ListDataProvider<PatientItem> patientProvider;

	public PatientViewImpl() {
		super();
	}

	public void enter(ViewChangeEvent event) {
		super.menuBar.getItems().get(1).setText((String) VaadinSession.getCurrent().getAttribute("username"));
		this.patientItemGrid = new Grid<>();
		this.patientItemList = new LinkedList<>();

		patientItemGrid = new Grid<>();
		patientItemGrid.addColumn(PatientItem::getId).setCaption("ID");
		patientItemGrid.addColumn(PatientItem::getFirstName).setCaption("Firstname");
		patientItemGrid.addColumn(PatientItem::getLastName).setCaption("Lastname");
		patientItemGrid.addColumn(PatientItem::getBirthday).setCaption("Birthday");

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

		Button btnOpen = new Button("Open");
		btnOpen.setEnabled(false);
		Button btnNewPatient = new Button("New Patient");

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponents(patientItemGrid, btnOpen, btnNewPatient);
		hLayout.setComponentAlignment(btnOpen, Alignment.BOTTOM_RIGHT);
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

		patientItemGrid.addSelectionListener(selection -> {
			if (selection.getFirstSelectedItem().isPresent()) {
				btnOpen.setEnabled(true);
			} else {
				btnOpen.setEnabled(false);
			}
		});
	}

	private void updatePatientItemList() {
		for (PatientViewListener listener : listeners) {
			this.patientItemList = listener.setupPatientItemList();
		}
	}

	protected void patientNewWindow() {
		final PatientNewWindow window = new PatientNewWindow(this, new PatientItem());
		window.setModal(true);
		super.getUI().getUI().addWindow(window);
	}

	protected void patientOpenWindow() {
		PatientItem patient = this.patientItemGrid.getSelectedItems().iterator().next();
		final PatientOpenWindow window = new PatientOpenWindow(this, patient);
		window.setModal(true);
		super.getUI().getUI().addWindow(window);
	}

	public void save(PatientItem patientItem, String note) {
		for (PatientViewListener listener : listeners) {
			listener.saveButtonClicked(patientItem, note);
		}
	}

	@Override
	public void addListener(PatientViewListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void saveNoteButtonClicked(PatientItem patientItem, String note) {
		for (PatientViewListener listener : listeners) {
			listener.saveNoteButtonClicked(patientItem, note);
		}
		patientItem.reloadFromModel();
		patientProvider.refreshItem(patientItem);
	}

	public List<String> getClinicNames() {
		List<String> locNameList = new LinkedList<String>();
		for (ClinicItem clinic : ClinicPresenter.getClinicNames()) {
			locNameList.add(clinic.getName());
		}
		return locNameList;
	}

	public void getSelectedClinic(Optional<String> clinic, PatientItem patient) {
			ClinicPresenter.setupClinic(clinic, patient);
		}

	public List<String> getDocNames() {
		List<String> docNameList = new LinkedList<String>();
		for (DoctorItem doc : DoctorPresenter.getDoctorNames()) {
			docNameList.add(doc.getLastName());
		}
		return docNameList;
	}

	public void getSelectedDoctor(Optional<String> doc, PatientItem patient) {
		DoctorPresenter.setupDoctor(doc, patient);
	}

	public Collection<String> getAddictionNames() {
		List<String> addictionNameList = new LinkedList<String>();
		for (AddictionItem addiction : AddictionPresenter.getAddictionItems()) {
			addictionNameList.add(addiction.getName());
		}
		return addictionNameList;
	}

	public void setSelectedAddictions(Set<String> selectedItems, PatientItem patient) {
		AddictionPresenter.setAddictionsToPatient(selectedItems, patient);
		
	}
}

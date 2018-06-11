package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;

public class DashboardViewImpl extends PmsCustomComponent implements View, DashboardView {
	
	public static final String NAME = "";
	
	public DashboardViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		super.menuBar.getItems().get(1).setText((String) VaadinSession.getCurrent().getAttribute("username"));
        Image addictionIcon = new Image("", new ThemeResource("img/addiction.svg"));
        addictionIcon.setAlternateText("Addiction");
        addictionIcon.setDescription("Addiction");
        addictionIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(AddictionViewImpl.NAME);
        });
        addictionIcon.setWidth("200px");
        addictionIcon.setHeight("200px");

        Image agendaIcon = new Image("", new ThemeResource("img/agenda.svg"));
        agendaIcon.setAlternateText("Agenda");
        agendaIcon.setDescription("Agenda");
        agendaIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(AgendaViewImpl.NAME);
        });
        agendaIcon.setWidth("200px");
        agendaIcon.setHeight("200px");

        Image clinicIcon = new Image("", new ThemeResource("img/clinic.svg"));
        clinicIcon.setAlternateText("Clinic");
        clinicIcon.setDescription("Clinic");
        clinicIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(ClinicViewImpl.NAME);
        });
        clinicIcon.setWidth("200px");
        clinicIcon.setHeight("200px");

        Image drugIcon = new Image("", new ThemeResource("img/drug.svg"));
        drugIcon.setAlternateText("Drug");
        drugIcon.setDescription("Drug");
        drugIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(DrugViewImpl.NAME);
        });
        drugIcon.setWidth("200px");
        drugIcon.setHeight("200px");

        Image patientIcon = new Image("", new ThemeResource("img/patient.svg"));
        patientIcon.setAlternateText("Patient");
        patientIcon.setDescription("Patient");
        patientIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(PatientViewImpl.NAME);
        });
        patientIcon.setWidth("200px");
        patientIcon.setHeight("200px");

        Image reportIcon = new Image("", new ThemeResource("img/report.svg"));
        reportIcon.setAlternateText("Report");
        reportIcon.setDescription("Report");
        reportIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(ReportViewImpl.NAME);
        });
        reportIcon.setWidth("200px");
        reportIcon.setHeight("200px");

        GridLayout tileGrid = new GridLayout(3, 2);
    	tileGrid.setWidth("1200px");
        tileGrid.setHeight("700px");
        tileGrid.addStyleName("main-tile-nav");
        tileGrid.addComponent(patientIcon, 0, 0);
        tileGrid.addComponent(clinicIcon, 1, 0);
        tileGrid.addComponent(agendaIcon, 2, 0);
        tileGrid.addComponent(drugIcon, 0, 1);
        tileGrid.addComponent(reportIcon, 1, 1);
        tileGrid.addComponent(addictionIcon, 2, 1);
        tileGrid.setComponentAlignment(patientIcon, Alignment.MIDDLE_CENTER);
        tileGrid.setComponentAlignment(clinicIcon, Alignment.MIDDLE_CENTER);
        tileGrid.setComponentAlignment(agendaIcon, Alignment.MIDDLE_CENTER);
        tileGrid.setComponentAlignment(drugIcon, Alignment.MIDDLE_CENTER);
        tileGrid.setComponentAlignment(reportIcon, Alignment.MIDDLE_CENTER);
        tileGrid.setComponentAlignment(addictionIcon, Alignment.MIDDLE_CENTER);
        
        super.contentPanel.setSizeUndefined();
        super.contentPanel.setContent(tileGrid);
	}
}

package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;

public class MainViewImpl extends CustomComponent implements View, MainView {
	
	public MainViewImpl() {
        final GridLayout tileGrid = new GridLayout(3, 2);
        tileGrid.addStyleName("main-tile-navigation");
        
        final Link patientLink = new Link("Patient Managament", new ExternalResource("/patient"));
        patientLink.setDescription("Patient Management");
        patientLink.setIcon(new ThemeResource("../pms/img/patient.svg"));

        final Link agendaLink= new Link("Agenda Managament", new ExternalResource("/agenda"));
        agendaLink.setDescription("Agenda Management");
        agendaLink.setIcon(new ThemeResource("../pms/img/patient.svg"));

        final Link clinicLink = new Link("Clinic Managament", new ExternalResource("/clinic"));
        clinicLink.setDescription("Clinic Management");
        clinicLink.setIcon(new ThemeResource("../pms/img/patient.svg"));

        final Link medicamentLink= new Link("Medicament Managament", new ExternalResource("/medicament"));
        medicamentLink.setDescription("Medicament Management");
        medicamentLink.setIcon(new ThemeResource("../pms/img/patient.svg"));

        final Link reportLink= new Link("Report Managament", new ExternalResource("/report"));
        reportLink.setDescription("Report Management");
        reportLink.setIcon(new ThemeResource("../pms/img/patient.svg"));

        final Link addictionLink = new Link("Addiction Managament", new ExternalResource("/addiction"));
        addictionLink.setDescription("Addiction Management");
        addictionLink.setIcon(new ThemeResource("../pms/img/patient.svg"));
        
        tileGrid.addComponent(patientLink, 0, 0);
        tileGrid.addComponent(agendaLink, 1, 0);
        tileGrid.addComponent(clinicLink, 2, 0);
        tileGrid.addComponent(medicamentLink, 0, 1);
        tileGrid.addComponent(reportLink, 1, 1);
        tileGrid.addComponent(addictionLink, 2, 1);
        
        setCompositionRoot(tileGrid);
	}

	public void addListener(MainViewListener listener) {
	}

	public void navigationClick() {
	}

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Hello World");
    }
}

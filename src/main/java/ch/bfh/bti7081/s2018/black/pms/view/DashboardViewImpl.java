package ch.bfh.bti7081.s2018.black.pms.view;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2018.black.pms.util.JpaDemo;
import ch.bfh.bti7081.s2018.black.pms.util.JpaUtility;

public class DashboardViewImpl extends PmsCustomComponent implements View, DashboardView {
	
	public static final String NAME = "";
	
	public DashboardViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
        Image addictionIcon = new Image("", new ThemeResource("img/addiction.svg"));
        addictionIcon.setAlternateText("Addiction");
        addictionIcon.setDescription("Addiction");
        addictionIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(AddictionViewImpl.NAME);
        });

        Image agendaIcon = new Image("", new ThemeResource("img/agenda.svg"));
        agendaIcon.setAlternateText("Agenda");
        agendaIcon.setDescription("Agenda");
        agendaIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(AgendaViewImpl.NAME);
        });

        Image clinicIcon = new Image("", new ThemeResource("img/clinic.svg"));
        clinicIcon.setAlternateText("Clinic");
        clinicIcon.setDescription("Clinic");
        clinicIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(ClinicViewImpl.NAME);
        });

        Image drugIcon = new Image("", new ThemeResource("img/drug.svg"));
        drugIcon.setAlternateText("Drug");
        drugIcon.setDescription("Drug");
        drugIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(DrugViewImpl.NAME);
        });

        Image patientIcon = new Image("", new ThemeResource("img/patient.svg"));
        patientIcon.setAlternateText("Patient");
        patientIcon.setDescription("Patient");
        patientIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(PatientViewImpl.NAME);
        });

        Image reportIcon = new Image("", new ThemeResource("img/report.svg"));
        reportIcon.setAlternateText("Report");
        reportIcon.setDescription("Report");
        reportIcon.addClickListener(listener -> {
        	UI.getCurrent().getNavigator().navigateTo(ReportViewImpl.NAME);
        });

        GridLayout tileGrid = new GridLayout(3, 2);
        tileGrid.addStyleName("main-tile-nav");
        tileGrid.addComponent(patientIcon, 0, 0);
        tileGrid.addComponent(clinicIcon, 1, 0);
        tileGrid.addComponent(agendaIcon, 2, 0);
        tileGrid.addComponent(drugIcon, 0, 1);
        tileGrid.addComponent(reportIcon, 1, 1);
        tileGrid.addComponent(addictionIcon, 2, 1);
        
        super.contentPanel.setContent(tileGrid);
        
        //EntityManagerFactory entityManagerFactory;
		//entityManagerFactory = Persistence.createEntityManagerFactory("PMS");
		JpaDemo.test();
	}
}

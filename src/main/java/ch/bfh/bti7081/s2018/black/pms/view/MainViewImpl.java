package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;

public class MainViewImpl extends CustomComponent implements View, MainView {
	
	public static final String NAME = "";
	
	public MainViewImpl() {
        GridLayout tileGrid = new GridLayout(3, 2);
        tileGrid.addStyleName("main-tile-nav");

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

        tileGrid.addComponent(patientIcon, 0, 0);
        tileGrid.addComponent(clinicIcon, 1, 0);
        tileGrid.addComponent(agendaIcon, 2, 0);
        tileGrid.addComponent(drugIcon, 0, 1);
        tileGrid.addComponent(reportIcon, 1, 1);
        tileGrid.addComponent(addictionIcon, 2, 1);

        HorizontalLayout horizontal = new HorizontalLayout();
        horizontal.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        horizontal.addComponent(tileGrid);

        MenuBar.Command pmsCommand = new MenuBar.Command() {
        	@Override
			public void menuSelected(MenuItem selectedItem) {
				UI.getCurrent().getNavigator().navigateTo(MainViewImpl.NAME);
			}
		};

        MenuBar.Command logoutCommand = new MenuBar.Command() {
        	@Override
			public void menuSelected(MenuItem selectedItem) {
				Notification.show("kthxbye!");
			}
		};

    	MenuBar menuBar = new MenuBar();
    	menuBar.addStyleName("main-menubar");

    	MenuItem pmsItem = menuBar.addItem("PATIENT MANAGEMENT SYSTEM", new ThemeResource("img/pms_32px.png"), pmsCommand);
    	pmsItem.setDescription("PMS");
    	
    	MenuItem userItem = menuBar.addItem("Anonymous", null, null);
    	userItem.setEnabled(false);
    	userItem.setStyleName("main-menubar-user");

    	MenuItem logoutItem = menuBar.addItem("Logout", new ThemeResource("img/poweroff.png"), logoutCommand);
    	logoutItem.setDescription("Logout");
    	logoutItem.setStyleName("main-menubar-logout");

		VerticalLayout vertical = new VerticalLayout();
		vertical.setSizeFull();
		vertical.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		vertical.addComponents(menuBar, horizontal);
        
        setCompositionRoot(vertical);
	}
}

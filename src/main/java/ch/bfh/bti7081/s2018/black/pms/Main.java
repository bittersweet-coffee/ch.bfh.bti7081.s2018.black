package ch.bfh.bti7081.s2018.black.pms;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2018.black.pms.presenter.AddictionPresenter;
import ch.bfh.bti7081.s2018.black.pms.presenter.AgendaPresenter;
import ch.bfh.bti7081.s2018.black.pms.presenter.LoginPresenter;
import ch.bfh.bti7081.s2018.black.pms.presenter.ClinicPresenter;
import ch.bfh.bti7081.s2018.black.pms.presenter.DrugPresenter;
import ch.bfh.bti7081.s2018.black.pms.presenter.PatientPresenter;
import ch.bfh.bti7081.s2018.black.pms.presenter.ReportPresenter;
import ch.bfh.bti7081.s2018.black.pms.view.*;

// Load the default mytheme which also includes the Valo theme
@Theme("mytheme")

// Let Navigator use the HTML5 history API to have nicer URLs and catch backwards navigation
@PushStateNavigation

// Custom browser tab title
@Title("PMS")
public class Main extends UI {
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	// Initialize the LoginView first in order to handle the user session
    	LoginViewImpl loginView = new LoginViewImpl();
    	Navigator navigator = new Navigator(this, this);
    	navigator.addView(LoginViewImpl.NAME, loginView);

    	// TODO: This should actually create an instance of *View and not *ViewImpl to be independent of VAADIN
    	AddictionViewImpl addictionView = new AddictionViewImpl();
    	AgendaViewImpl agendaView = new AgendaViewImpl();
    	ClinicViewImpl clinicView = new ClinicViewImpl();
    	DrugViewImpl drugView = new DrugViewImpl();
    	DashboardViewImpl dashboardView = new DashboardViewImpl();
    	PatientViewImpl patientView = new PatientViewImpl();
    	ReportViewImpl reportView = new ReportViewImpl();
    	
    	new AgendaPresenter(agendaView);
    	new AddictionPresenter(addictionView);
    	new PatientPresenter(patientView);
    	new LoginPresenter(loginView);
    	new ClinicPresenter(clinicView);
    	new ReportPresenter(reportView);
    	new DrugPresenter(drugView);

    	navigator.addView(DashboardViewImpl.NAME, dashboardView);
    	navigator.addView(AddictionViewImpl.NAME, addictionView);
    	navigator.addView(AgendaViewImpl.NAME, agendaView);
    	navigator.addView(ClinicViewImpl.NAME, clinicView);
    	navigator.addView(DrugViewImpl.NAME, drugView);
    	navigator.addView(PatientViewImpl.NAME, patientView);
    	navigator.addView(ReportViewImpl.NAME, reportView);
    	
    	navigator.navigateTo("login");
    }

    @WebServlet(urlPatterns = "/*", name = "MainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MainServlet extends VaadinServlet {
    }
}

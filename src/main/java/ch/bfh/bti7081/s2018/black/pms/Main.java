package ch.bfh.bti7081.s2018.black.pms;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.vaadin.annotations.PreserveOnRefresh;
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

// Prevent logout when refreshing the session (F5)
@PreserveOnRefresh

// Custom browser tab title
@Title("PMS")

/**
 * Main entry point for the whole application. PMS heavily
 * uses MVP and JPA.
 */
public class Main extends UI {
	
	final static Logger logger = Logger.getLogger(Main.class);
	
	/**
	 * Main entry point of VAADIN. Initialize all VAADIN
	 * components and PMS application.
	 * @param vaadinRequest The VaadinRequest
	 */
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	logger.info("Initializing PMS");
    	
    	// Initialize the LoginView first in order to handle the user session
    	LoginViewImpl loginView = new LoginViewImpl();
    	Navigator navigator = new Navigator(this, this);
    	navigator.addView(LoginViewImpl.NAME, loginView);

    	// Initialize all views
    	AddictionViewImpl addictionView = new AddictionViewImpl();
    	AgendaViewImpl agendaView = new AgendaViewImpl();
    	ClinicViewImpl clinicView = new ClinicViewImpl();
    	DrugViewImpl drugView = new DrugViewImpl();
    	DashboardViewImpl dashboardView = new DashboardViewImpl();
    	PatientViewImpl patientView = new PatientViewImpl();
    	ReportViewImpl reportView = new ReportViewImpl();
    	
    	// Initialize presenters and connect them with their specific views
    	AgendaPresenter ap = new AgendaPresenter();
    	ap.setupView(agendaView);
    	AddictionPresenter ad = new AddictionPresenter();
    	ad.setupView(addictionView);
    	PatientPresenter pp = new PatientPresenter();
    	pp.setupView(patientView);
    	LoginPresenter lp = new LoginPresenter();
    	lp.setupView(loginView);
    	ClinicPresenter cp = new ClinicPresenter();
    	cp.setupView(clinicView);
    	ReportPresenter rp = new ReportPresenter();
    	rp.setupView(reportView);
    	DrugPresenter dp = new DrugPresenter();
    	dp.setupView(drugView);
    	
    	// Setup navigator to allow proper navigation
    	navigator.addView(DashboardViewImpl.NAME, dashboardView);
    	navigator.addView(AddictionViewImpl.NAME, addictionView);
    	navigator.addView(AgendaViewImpl.NAME, agendaView);
    	navigator.addView(ClinicViewImpl.NAME, clinicView);
    	navigator.addView(DrugViewImpl.NAME, drugView);
    	navigator.addView(PatientViewImpl.NAME, patientView);
    	navigator.addView(ReportViewImpl.NAME, reportView);
    	logger.info("Finished initializing PMS");
    	
    	// Navigate to the login page when opening PMS for the first time
    	navigator.navigateTo("login");
    }

    /**
     * MainServlet class to interact with the java application on a lower level.
     */
    @WebServlet(urlPatterns = "/*", name = "MainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MainServlet extends VaadinServlet {
    }
}

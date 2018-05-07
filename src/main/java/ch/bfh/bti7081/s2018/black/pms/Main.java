package ch.bfh.bti7081.s2018.black.pms;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2018.black.pms.presentor.*;
import ch.bfh.bti7081.s2018.black.pms.view.*;
import ch.bfh.bti7081.s2018.black.pms.model.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("pms")
public class Main extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	MainViewImpl mainView = new MainViewImpl();
    	new MainPresentor(mainView);
    	
    	AddictionModel addictionModel = new AddictionModel("Drugs", "Lorem Ipsum");
    	
        VerticalLayout mainLayout = new VerticalLayout(mainView);
        mainLayout.setSizeFull();
        setContent(mainLayout);

    	Navigator navigator = new Navigator(this, this);
    	navigator.addView("", mainView);
    }

    @WebServlet(urlPatterns = "/*", name = "MainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MainServlet extends VaadinServlet {
    }
}

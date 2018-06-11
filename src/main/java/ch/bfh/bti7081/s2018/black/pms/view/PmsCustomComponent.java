package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PmsCustomComponent extends CustomComponent {

    protected Panel contentPanel = new Panel();
    protected BreadCrumbMaker bC = new BreadCrumbMaker();
	
	public PmsCustomComponent() {

        MenuBar.Command pmsCommand = new MenuBar.Command() {
        	@Override
			public void menuSelected(MenuItem selectedItem) {
        		Notification.show("PMS is awesome!");
			}
		};

        MenuBar.Command logoutCommand = new MenuBar.Command() {
        	@Override
			public void menuSelected(MenuItem selectedItem) {
				Notification.show("kthxbye!");
			}
		};
		
    	MenuBar menuBar = new MenuBar();
    	menuBar.setWidth("1200px");
    	menuBar.addStyleName("main-menubar");

    	MenuItem pmsItem = menuBar.addItem("PATIENT MANAGEMENT SYSTEM", new ThemeResource("img/pms_32px.png"), pmsCommand);
    	pmsItem.setDescription("PMS");
    	
    	MenuItem userItem = menuBar.addItem("Anonymous", null, null);
    	userItem.setEnabled(false);
    	userItem.setStyleName("main-menubar-user");

    	MenuItem logoutItem = menuBar.addItem("Logout", new ThemeResource("img/poweroff.png"), logoutCommand);
    	logoutItem.setDescription("Logout");
    	logoutItem.setStyleName("main-menubar-logout");
    	
    	MenuBar breadcrumbs = bC.visibleBreadcrumbs();
    	
    	HorizontalLayout contentBody = new HorizontalLayout();
    	contentBody.setSizeFull();
        contentBody.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        contentBody.addComponent(contentPanel);
        contentPanel.setWidth("1200px");

        VerticalLayout content = new VerticalLayout();
		content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		content.addComponents(menuBar, breadcrumbs, contentBody);
		
		setCompositionRoot(content);
	}
}

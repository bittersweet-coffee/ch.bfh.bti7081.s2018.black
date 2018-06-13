package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PmsCustomComponent extends CustomComponent {

    protected Panel contentPanel = new Panel();
    protected BreadCrumbMaker bC;
    protected MenuBar menuBar = new MenuBar();
    private String username = "Anonymous";
	
	public PmsCustomComponent() {
		if (!checkLogin()) {
			UI.getCurrent().getNavigator().navigateTo("login");
			bC = new BreadCrumbMaker();
		}

        MenuBar.Command pmsCommand = new MenuBar.Command() {
        	@Override
			public void menuSelected(MenuItem selectedItem) {
        		UI.getCurrent().getNavigator().navigateTo("");
        		bC.visibleBreadcrumbs();
        	}
		};

        MenuBar.Command logoutCommand = new MenuBar.Command() {
        	@Override
			public void menuSelected(MenuItem selectedItem) {
        		VaadinSession.getCurrent().close();
        		UI.getCurrent().getNavigator().navigateTo("login");
			}
		};
		

    	menuBar.setWidth("1200px");
    	menuBar.addStyleName("main-menubar");

    	MenuItem pmsItem = menuBar.addItem("PATIENT MANAGEMENT SYSTEM", new ThemeResource("img/pms_32px.png"), pmsCommand);
    	pmsItem.setDescription("PMS");
    	
    	MenuItem userItem = menuBar.addItem(this.username, null, null);
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
	
	private boolean checkLogin() {
		String username = (String) VaadinSession.getCurrent().getAttribute("username");

		if (username != null && !username.isEmpty()) {
			return true;
		}

		return false;
	}
}

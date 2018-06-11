package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;

//Creates Breadcrumbs when the User navigates through the App
public class BreadCrumbMaker {

	private static final String DASHBOARD = "";
	private static final String FIRST_CRUMB = "Home";

	private MenuBar breadcrumbs = new MenuBar();
	
	//Initialize the BreadCrumbs
	public BreadCrumbMaker(){
		homeButton();
		this.breadcrumbs.setVisible(false);
		this.breadcrumbs.setHeight("35");
	}
	
	// makes it visible on any other window except on the Dashboard
	// returns to the PmsCustomComponent
	// Must be used in other ViewImpl classes
	public MenuBar visibleBreadcrumbs(){
		try{
			//Checks whether there exists a Navigator to controll the crumbs
			if(UI.getCurrent().getNavigator() != null){
				String path = UI.getCurrent().getNavigator().getState();
					if(path.equals(null) || path.equals("")){
						this.breadcrumbs.setVisible(false);
						removeBreadcrumbs();
					} 
					else {
						this.breadcrumbs.setVisible(true);
					}
			}
		} catch (NullPointerException e){
			Notification.show("Couldn't find correct path!");
		}
		return this.breadcrumbs;
	}
	
	//adds the next breadcrumb to the row
	public void makeCrumbs(String path){
		MenuBar.Command breadcrumbCommand = new MenuBar.Command() {	
			@Override
			public void menuSelected(MenuItem selectedItem) {
				UI.getCurrent().getNavigator().navigateTo(path);
			}
		};
		this.breadcrumbs.addItem(path + " >", breadcrumbCommand);
	}
	
	//Command for "Home" Button Breadcrumb to reset the Breadcrumbs when going back to Dashboard
	private void homeButton(){
			MenuBar.Command breadcrumbCommand = new MenuBar.Command() {	
				@Override
				public void menuSelected(MenuItem selectedItem) {
					UI.getCurrent().getNavigator().navigateTo(DASHBOARD);
					removeBreadcrumbs();
					homeButton();
				}
			};
		this.breadcrumbs.addItem(FIRST_CRUMB + " >", breadcrumbCommand);
	}
	
	//removes all Items from the MenuBar
	private void removeBreadcrumbs(){
		this.breadcrumbs.removeItems();
	}
}

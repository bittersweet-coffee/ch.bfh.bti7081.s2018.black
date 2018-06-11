package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.List;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;

/**
 * 
 * @author andrejica
 * Creates Breadcrumbs when the User navigates through the Application
 */
public class BreadCrumbMaker {

	private static final String DASHBOARD = "";
	private static final String FIRST_CRUMB = "Home";

	private MenuBar breadcrumbs = new MenuBar();
	
	//Initialize the BreadCrumbs
	public BreadCrumbMaker(){
		homeButton();
		this.breadcrumbs.setVisible(false);
		this.breadcrumbs.setWidth("1200px");
		this.breadcrumbs.setHeight("35");
	}
	
	/**
	 * Checks if the Navigator is on the Dashboard or not
	 * sets the Breadcrumbs Visible if not on the Home Display.
	 * 
	 * Method used in all ViewImpl to get access to the paths.
	 * 
	 * @return 
	 * Returns the breadcrumbs MenuBar to the PmsCustomComponent class.
	 * 
	 * NullPointerException if there is no Navigator.
	 */
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
	
	/**
	 * Makes the breadcrumb command and adds it to the breadcrumb MenuBar
	 * Checks that there can't be 2 identical paths in a row listet.
	 * 
	 * @param path
	 * The path where the user is navigating
	 */
	public void makeCrumbs(String path){
		List<MenuItem> crumbs = this.breadcrumbs.getItems();
		String lastPath = crumbs.get(this.breadcrumbs.getSize()-1).getText();
		//Check for duplicate crumbs
		if(lastPath.substring(0, lastPath.length() - 2).equals(path)){
		} 
		else {
			MenuBar.Command breadcrumbCommand = new MenuBar.Command() {	
				@Override
				public void menuSelected(MenuItem selectedItem) {
					UI.getCurrent().getNavigator().navigateTo(path);
				}
			};
			this.breadcrumbs.addItem(path + " >", breadcrumbCommand);
		} 
	}
	
	/**
	 * Creates command for the first Breadcrumb button when going back to Dashboard
	 */
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
	
	/**
	 * Removing all items from the Breadcrumbs MenuBar
	 */
	private void removeBreadcrumbs(){
		this.breadcrumbs.removeItems();
	}
}

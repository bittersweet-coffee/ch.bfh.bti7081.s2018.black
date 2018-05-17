package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.breadcrumb.Breadcrumb;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;


//Started Implementing but not finished yet. 
//When starting to run the application via jetty:run it gives error about missing GWT Module
public class BreadCrumbs {

	private VerticalLayout content = new VerticalLayout();
	
	public BreadCrumbs(){
		Breadcrumb bC = new Breadcrumb();
		bC.setShowAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
		bC.setHideAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
		bC.setUseDefaultClickBehaviour(false);
		bC.addLink(new Button());
		bC.setLinkEnabled(false, 0);
		bC.setHeight(18, Sizeable.Unit.PIXELS);
		
		content.addComponent(bC);
	}

	public VerticalLayout getContent() {
		return content;
	}
	
//	breadCrumb.select(0);
//    final int size = breadcrumbList.size();
//    for (int i = 0, breadcrumbListSize = breadcrumbList.size(); i < breadcrumbListSize; i++) {
//        State state = breadcrumbList.get(i);
//        breadCrumb.addLink(new Button(getName(state), new BreadcrumbClickListener("cancel", eventProcessor, size - (i + 1))));
//    }
}

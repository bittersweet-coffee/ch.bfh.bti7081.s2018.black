package ch.bfh.bti7081.s2018.black.pms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.util.JpaDataAccessObject;
import ch.bfh.bti7081.s2018.black.pms.util.JpaDemo;
import ch.bfh.bti7081.s2018.black.pms.util.JpaUtility;

public class AddictionViewImpl extends PmsCustomComponent implements View, AddictionView {

	public static final String NAME = "addiction";

	public AddictionViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		Label test = new Label("Addiction here");
        super.contentPanel.setContent(test);
        
        AddictionModel addiction = new AddictionModel();
        addiction.setName("Toni");
        addiction.setDescription("Generic is cool!");
        JpaUtility transaction = new JpaUtility();
        JpaDataAccessObject object = new JpaDataAccessObject(transaction);
        object.store(addiction);
        //JpaUtility.persist(addiction);
        //JpaDemo.testAddiction();
        AddictionModel addiction2 = (AddictionModel) object.findById(1, addiction);
        System.out.println("Name: " + addiction2.getName() + ", Description: " + addiction2.getDescription());
	}
}

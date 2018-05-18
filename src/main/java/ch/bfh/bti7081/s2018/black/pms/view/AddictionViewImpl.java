package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ContextClickEvent.ContextClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.util.JpaDemo;
import ch.bfh.bti7081.s2018.black.pms.util.JpaUtility;

public class AddictionViewImpl extends PmsCustomComponent implements View, AddictionView {

	public static final String NAME = "addiction";

	public AddictionViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
		
        
        TextField txtSearch = new TextField("Filter:");
        HorizontalLayout hLayout = new HorizontalLayout();
        
        
        ListSelect<AddictionModel> addictUIList = new ListSelect<>();
        addictUIList.setWidth(300, UNITS_PIXELS);
        
        VerticalLayout addictDetails = new VerticalLayout();
        Label addictName = new Label("Test");
        Label addictDesc = new Label("Description....");
        addictDetails.addComponents(addictName, addictDesc);
        
        
        hLayout.addComponents(addictUIList, addictDetails);
        
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponents(txtSearch, hLayout);
        
        
        super.contentPanel.setContent(vLayout);
        
        
        
        AddictionModel addictAlc = new AddictionModel();
        addictAlc.setName("Alcohol Addiction");
        addictAlc.setDescription("Massive Alcohol Consumption");
        
        AddictionModel addictCocaine = new AddictionModel();
        addictCocaine.setName("Cocaine Addiction");
        addictCocaine.setDescription("Massive Cocaine Consumption");
        
        AddictionModel addictNicotine = new AddictionModel();
        addictNicotine.setName("Nicotine Addiction");
        addictNicotine.setDescription("Massive Cigarette Consumption");
        
        
        
        addictUIList.setItems(addictAlc, addictCocaine, addictNicotine);
        
    
		addictUIList.addValueChangeListener(selected -> {
			
			System.out.println(selected.getValue());
			
			
		});
        
        //addiction.setName("Toni");
        //addiction.setDescription("Generic is cool!");
        //JpaUtility.persist(addiction);
        //JpaDemo.testAddiction();
	}
}

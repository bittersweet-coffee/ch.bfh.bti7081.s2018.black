package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MainViewImpl extends CustomComponent implements MainView, ClickListener {
	
	private List<MainViewListener> listeners = new ArrayList<MainViewListener>();
	
	public MainViewImpl() {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it still works good!"));
        });
        
        layout.addComponents(name, button);
	}

	public void addListener(MainViewListener listener) {
		listeners.add(listener);
	}

	public void buttonClick(ClickEvent event) {
	}
}

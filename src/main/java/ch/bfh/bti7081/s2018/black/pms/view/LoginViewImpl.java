package ch.bfh.bti7081.s2018.black.pms.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginViewImpl extends CustomComponent implements View, LoginView {

	public static final String NAME = "login";

	private List<LoginViewListener> listeners = new ArrayList<LoginViewListener>();
	
	private boolean login = false;
	
	public LoginViewImpl() {
		super();
	}
	
	public void enter(ViewChangeEvent event) {
        FormLayout fLayout = new FormLayout();
        
		TextField usernameField = new TextField();
		usernameField.setCaption("Username:");

		PasswordField passwordField = new PasswordField();
		passwordField.setCaption("Password:");

		Button btnLogin = new Button("Login");
        btnLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		btnLogin.addClickListener(login -> {
			for (LoginViewListener listener: listeners) {
				this.login = listener.loginButtonClicked(usernameField.getValue(), passwordField.getValue());
			}
			if (!this.login) {
				Notification.show("Login failed! Either the username or password was wrong.", Notification.Type.ERROR_MESSAGE);
			}
			
			if (this.login) {
				VaadinSession.getCurrent().setAttribute("username", usernameField.getValue());
				UI.getCurrent().getNavigator().navigateTo("");
			}
		});

		Label helpText = new Label("To reset your password or get a new account contact support@pms.ch.");

        fLayout.addComponents(usernameField, passwordField, btnLogin, helpText);
        fLayout.setSizeUndefined();
        fLayout.setMargin(true);
        Panel content = new Panel(fLayout);
        content.setSizeUndefined();
        
        HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        hLayout.setSizeFull();
        hLayout.addComponent(content);
		
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setSizeFull();
		vLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		vLayout.setStyleName("login-view");
		vLayout.addComponent(hLayout);
        
        // Set content
		setCompositionRoot(vLayout);
	}
	
	@Override
	public void addListener(LoginViewListener listener) {
		this.listeners.add(listener);
	}
}

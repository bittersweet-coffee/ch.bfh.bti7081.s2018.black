package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

import ch.bfh.bti7081.s2018.black.pms.model.UserModel;
import ch.bfh.bti7081.s2018.black.pms.view.LoginView;
import ch.bfh.bti7081.s2018.black.pms.view.LoginViewImpl;

public class LoginPresenter implements LoginView.LoginViewListener {

	private LoginView view;
	final static Logger logger = Logger.getLogger(LoginPresenter.class);
	
	public LoginPresenter() {
		
	}

	@Override
	public boolean loginButtonClicked(String username, String password) {
		List<UserModel> userModelList = JpaServicePresenter.findAll(UserModel.class);
		Optional<UserModel> userModel = userModelList.stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst();
		
		if (!userModel.isPresent()) {
			logger.warn("Username '" + username + "' not found!");
			return false;
		}
		
		if (!BCrypt.checkpw(password, userModel.get().getPasswordHash())) {
			logger.warn("Wrong password supplied for user '" + username + "'!");
			return false;
		}
	
		logger.debug("Login successful for user '" + username + "'!");
		return true;
	}

	public void setupView(LoginViewImpl loginView) {
		this.view = loginView;
		this.view.addListener(this);
	}
}

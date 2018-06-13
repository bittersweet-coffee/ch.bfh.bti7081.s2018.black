package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCrypt;

import ch.bfh.bti7081.s2018.black.pms.model.UserModel;
import ch.bfh.bti7081.s2018.black.pms.view.LoginView;

public class LoginPresenter implements LoginView.LoginViewListener {

	private LoginView view;
	
	public LoginPresenter(LoginView view) {
		this.view = view;
		view.addListener(this);
	}

	@Override
	public boolean loginButtonClicked(String username, String password) {
		List<UserModel> userModelList = JpaServicePresenter.findAll(UserModel.class);
		Optional<UserModel> userModel = userModelList.stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst();
		
		if (!userModel.isPresent()) {
			return false;
		}
		
		if (!BCrypt.checkpw(password, userModel.get().getPasswordHash())) {
			return false;
		}
	
		return true;
	}
}

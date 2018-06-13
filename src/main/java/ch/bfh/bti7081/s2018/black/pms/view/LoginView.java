package ch.bfh.bti7081.s2018.black.pms.view;

public interface LoginView {
	
	public interface LoginViewListener {
		public boolean loginButtonClicked(String username, String password);
	}
	
	public void addListener(LoginViewListener listener);
}

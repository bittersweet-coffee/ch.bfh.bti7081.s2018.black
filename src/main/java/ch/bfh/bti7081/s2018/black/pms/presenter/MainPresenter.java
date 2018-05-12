package ch.bfh.bti7081.s2018.black.pms.presenter;

import ch.bfh.bti7081.s2018.black.pms.view.MainView;

public class MainPresenter implements MainView.MainViewListener {
	private MainView view;
	
	public MainPresenter(MainView view) {
		this.view = view;
	}

	public void navigationClick(String location) {
	}
}

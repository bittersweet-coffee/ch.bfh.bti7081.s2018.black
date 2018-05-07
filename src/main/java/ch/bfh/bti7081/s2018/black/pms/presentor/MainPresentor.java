package ch.bfh.bti7081.s2018.black.pms.presentor;

import ch.bfh.bti7081.s2018.black.pms.view.MainView;

public class MainPresentor implements MainView.MainViewListener {
	private MainView view;
	
	public MainPresentor(MainView view) {
		this.view = view;
	}

	public void navigationClick(String location) {
	}
}

package ch.bfh.bti7081.s2018.black.pms.view;

import ch.bfh.bti7081.s2018.black.pms.presenter.DrugPresenter;

public interface DrugView {
	
	public interface DrugViewListener {
	}
	
	public void addListener(DrugViewListener listener);
}

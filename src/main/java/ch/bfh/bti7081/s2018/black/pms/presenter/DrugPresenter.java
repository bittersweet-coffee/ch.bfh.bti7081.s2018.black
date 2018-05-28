package ch.bfh.bti7081.s2018.black.pms.presenter;

import java.util.LinkedList;
import java.util.List;

import ch.bfh.bti7081.s2018.black.pms.model.DrugModel;
import ch.bfh.bti7081.s2018.black.pms.view.DrugView;

public class DrugPresenter implements DrugView.DrugViewListener{
	
	private DrugView view;
	private List<String> addictNameList = new LinkedList<>();
	private List<DrugModel> drugModelList;
	
	public DrugPresenter(DrugView view) {
		this.view = view;
		this.drugModelList = new LinkedList<>();
		view.addListener(this);
		//this.fillAddictionList();
	}
	
	

}

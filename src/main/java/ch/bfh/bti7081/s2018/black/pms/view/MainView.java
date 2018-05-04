package ch.bfh.bti7081.s2018.black.pms.view;

public interface MainView {
	
	public interface MainViewListener {
		public void iconClick(String location);
	}
	
	public void addListener(MainViewListener listener);
}

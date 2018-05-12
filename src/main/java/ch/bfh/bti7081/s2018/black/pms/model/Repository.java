package ch.bfh.bti7081.s2018.black.pms.model;

public class Repository implements IRepository {
	
	private DBController dbc;
	
	public Repository() {
		dbc = new DBController();
		dbc.initDBConnection();
	}
	
	public void getLocations() {
		dbc.getLocations();
	}

	public static void main(String[] args) {
		Repository repo = new Repository();
		repo.getLocations();
	}

}

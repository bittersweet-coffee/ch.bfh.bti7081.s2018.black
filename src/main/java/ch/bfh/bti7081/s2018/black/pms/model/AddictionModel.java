package ch.bfh.bti7081.s2018.black.pms.model;

public class AddictionModel {
	private String name;
	private String description;
	
	public AddictionModel(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
}

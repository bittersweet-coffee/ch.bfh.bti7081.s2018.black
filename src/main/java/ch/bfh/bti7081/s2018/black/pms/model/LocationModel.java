package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="location")
public class LocationModel {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String street;
	
	@Column(name="`post_code`")
	private int postCode;
	
	private String place;
	
	private String telephone;
	
	@Column(name="`email`")
	private String email;
	
	@OneToMany(mappedBy = "location")
	private List<PatientModel> patients;
	
	public LocationModel(String name, String street, int postCode, String place, String telephone, String email, List<PatientModel> patients) {
		this.name = name;
		this.street = street;
		this.postCode = postCode;
		this.place = place;
		this.telephone = telephone;
		this.email = email;
		this.patients = patients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
}

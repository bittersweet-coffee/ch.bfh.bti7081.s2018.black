package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="patient")
public class PatientModel {
	
	@ Id
	@ GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private String firstname;
	
	@Column(nullable=false)
	private String lastname;
	
	private String street;
	
	@Column(name="post_code")
	private int postCode;
	
	private String telephone;
	
	@ManyToMany
    @JoinTable(name="patient_drug",
        joinColumns=
            @JoinColumn(name="patient_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="drug_id", referencedColumnName="id"))
	private List<DrugModel> drugs;
	
	@ManyToMany
    @JoinTable(name="patient_addiction",
        joinColumns=
            @JoinColumn(name="patient_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="addiction_id", referencedColumnName="id"))
	private List<AddictionModel> addictions;
	
	@ManyToMany
    @JoinTable(name="patient_doctor",
        joinColumns=
            @JoinColumn(name="patient_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="doctor_id", referencedColumnName="id"))
	private List<DoctorModel> doctors;
	
	@ManyToOne
	@JoinColumn(name="location_id", nullable=false)
	private LocationModel location;
    
	public PatientModel(String firstname, String lastname, String street, int postCode, String telephone,
			List<DrugModel> drugs, List<AddictionModel> addictions, List<DoctorModel> doctors) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.postCode = postCode;
		this.telephone = telephone;
		this.drugs = drugs;
		this.addictions = addictions;
		this.doctors = doctors;
	}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
		
}

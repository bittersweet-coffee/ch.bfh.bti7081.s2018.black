package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="doctor")
public class DoctorModel {

	@ Id
	@ GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private String firstname;
	
	@Column(nullable=false)
	private String lastname;
	
	@ManyToMany(mappedBy="doctors")
	private List<PatientModel> patients;
	
	@OneToOne
	@JoinColumn(name="user_id", nullable=false)
	private UserModel user;
	
	public DoctorModel(String firstname, String lastname, List<PatientModel> patients) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.patients = patients;
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
	
}

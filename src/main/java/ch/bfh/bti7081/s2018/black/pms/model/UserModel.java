package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="user")
public class UserModel {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String password;
	
	@Column(name="expiry_date")
	private Date expiryDate;
	
	@OneToOne(mappedBy="user")
	private DoctorModel doctor;
	
	public UserModel() {}
	
	public UserModel(String name, String password, Date expiryDate, DoctorModel doctor) {
		this.name = name;
		this.password = password;
		this.expiryDate = expiryDate;
		this.doctor = doctor;
	}
	
	public UserModel(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}

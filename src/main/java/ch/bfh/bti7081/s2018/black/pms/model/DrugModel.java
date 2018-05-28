package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ch.bfh.bti7081.s2018.black.pms.presenter.DrugPresenter;

/**
 * Drug class
 * @author musaa1
 * @version 0.1
 */
@Entity
@Table(name="drug")
public class DrugModel extends EntityModel {
	
	// name of the drug
	private String name;
	
	// description of the drug
	private String description;
	
	// list of the patients that have to take the drug
	// is mapped with the variable drugs of the class PatientModel
    @ManyToMany(mappedBy="drugs")
	private List<PatientModel> patients;
    
    @ManyToMany
    @JoinTable(name="drug_addiction",
        joinColumns=
            @JoinColumn(name="drug_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="addiction_id", referencedColumnName="id"))
	private List<AddictionModel> addictions;
    

    /**
     * getter of the name
     * @return the name of the drug
     */
	public String getName() {
		return this.name;
	}

	/**
	 * setter of the name
	 * @param name of the drug
	 */
	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * getter of the description
     * @return the description of the drug
     */
	public String getDescription() {
		return this.description;
	}

	/**
	 * setter of the description
	 * @param description of the drug
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * getter of the patients
	 * @return a list of the patient that have to take the drug
	 */
	public List<PatientModel> getPatients() {
		return this.patients;
	}

	/**
	 * setter of the patients
	 * @param patients that have to take the drug
	 */
	public void setPatients(List<PatientModel> patients) {
		this.patients = patients;
	}
}

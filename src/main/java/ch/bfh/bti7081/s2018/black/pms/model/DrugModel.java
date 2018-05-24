package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	
	// list of the patients that have to take the drug
	// is mapped with the variable drugs of the class PatientModel
    @ManyToMany(mappedBy="drugs")
	private List<PatientModel> patients;

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

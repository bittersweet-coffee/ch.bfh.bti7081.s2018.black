package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	
	// description of the drug
	private String description;
	
	// minimal Dose of the drug
	private Double minDose;
	
	// maximal Dose of the drug
	private Double maxDose;
	
	// measure of the drug
	@Column(columnDefinition="VARCHAR(40)")
	private Measurement measure;
	
	// ENUM containing all possible measures for our drugs
	private enum Measurement {
		DOSE_INTEGER("Integer"), 
		DOSE_DOUBLE("Double"),
		DOSE_HALVES("Halves");
		;
		
		private final String enumMeasure;
		
		Measurement(String enumMeasure) {
			this.enumMeasure = enumMeasure;
		}
		
		public String getMeasureString() {
			return this.enumMeasure;
		}
		
	};
	
	/**
	 * Method to check if the entered Dose has the correct Type and is in the Drugs Bounds
	 * @param enteredDose Dose entered by the Doctor
	 * @return Pair containing the result and its corresponding message
	 */
	public Pair checkDose(Double enteredDose) {
		
		Pair checker = this.internalLimitCheck(enteredDose);
		Pair result = new Pair();
		
		switch (this.measure) {
		
		case DOSE_INTEGER:
			// Check whether enteredDose is an Integer
			if((enteredDose - Math.floor(enteredDose)) == 0) {
				result.put(checker.getResult(), checker.getMessage());
			} else {
				result.put(false, "Entered Dose isn't an Integer!");
			};
			break;
			
		case DOSE_HALVES:
			// Check whether enteredDose is an Integer or Half	
			if ((enteredDose - Math.floor(enteredDose) == 0) || (enteredDose % 1 == 0.5)) {
				result.put(checker.getResult(), checker.getMessage());
			} else {
				result.put(false, "Entered Dose isn't an Half!");
			};
			break;
			
		case DOSE_DOUBLE:
			// enteredDose has to be Double	
			if(this.measure.equals(Measurement.DOSE_DOUBLE)) {
				result.put(checker.getResult(), checker.getMessage());
			} else {
				result.put(false, "Entered Dose isn't an Integer!");
			};
			break;
		default:
			result.put(false, "Oops, the selected Drug has no Measurement set!");
			break;
		}
		
		return result;
	}
	
	/**
	 * Helper Method for checking Dose Bounds
	 * @param enteredDose Dose entered by the Doctor
	 * @return Pair containing the result and its corresponding message
	 */
	private Pair internalLimitCheck(Double enteredDose) {
		
		if ((enteredDose >= this.minDose) && (enteredDose <= this.maxDose)) {
			return new Pair(true, "Success");
		} else {
			return new Pair(false, "The dose entered is not within the drug thresholds: \n" + "Min: " + this.minDose + "\nMax: " + this.maxDose + "\n");
		}
		
	}
	
	
    
// ONLY FOR TESTING! WILL BE REMOVED AFTER DRUGS ARE CORRECTLY IN DB!
    public void setMeasure(String measure) {
    	if(measure.equals(Measurement.DOSE_INTEGER.getMeasureString())) {
    		this.measure = Measurement.DOSE_INTEGER;
    	} else if (measure.equals(Measurement.DOSE_DOUBLE.getMeasureString())) {
    		this.measure = Measurement.DOSE_DOUBLE;
    	}
    }
	
// ONLY FOR TESTING! WILL BE REMOVED AFTER DRUGS ARE CORRECTLY IN DB!
	public boolean setMinDose(Double minDose) {
		// Ensure minDose hasn't been set yet
		if (this.minDose == null) {
			// Check whether minDose is an Integer
			if(this.measure.equals(Measurement.DOSE_INTEGER) && (minDose - Math.floor(minDose) == 0)) {
				this.minDose = minDose;	
				return true;
			// Check whether minDose is an Integer or Half	
			} else if(this.measure.equals(Measurement.DOSE_HALVES) && ((minDose - Math.floor(minDose) == 0) || (minDose % 1 == 0.5))) {
				
			// minDose has to be Double	
			} else if(this.measure.equals(Measurement.DOSE_DOUBLE)) {
				this.minDose = minDose;
				return true;
			}
		}
		return false;
	}
	

// ONLY FOR TESTING! WILL BE REMOVED AFTER DRUGS ARE CORRECTLY IN DB!
	public boolean setMaxDose(Double maxDose) {
		// Ensure minDose hasn't been set yet
		if (this.maxDose == null) {
			// Check whether minDose is an Integer
			if(this.measure.equals(Measurement.DOSE_INTEGER) && (maxDose - Math.floor(maxDose) == 0)) {
				this.maxDose = maxDose;	
				return true;
			// Check whether minDose is an Integer or Half	
			} else if(this.measure.equals(Measurement.DOSE_HALVES) && ((maxDose - Math.floor(maxDose) == 0) || (maxDose % 1 == 0.5))) {
				
			// Number has to be Double	
			} else if(this.measure.equals(Measurement.DOSE_DOUBLE)) {
				this.maxDose = maxDose;
				return true;
			}
		}
		return false;
	}
	
	// list of the patients that have to take the drug
	// is mapped with the variable drugs of the class PatientModel
    @OneToMany(mappedBy="patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientDrugModel> patients;
    
    
    /**
     * getter of the measurement
     * @return the measure specific to this drug
     */
    public String getMeasure() {
    	return this.measure.getMeasureString();
    }
    
	/**
	 * getter of the minimal Dose for this drug
	 * @return minimal Dose for this drug
	 */
	public Double getMinDose() {
		return this.minDose;
	}
	
	/**
	 * getter of the maximal Dose for this drug
	 * @return maximal Dose for this drug
	 */
	public Double getMaxDose() {
		return this.maxDose;
	}
    
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
	public List<PatientDrugModel> getPatients() {
		return this.patients;
	}

	/**
	 * setter of the patients
	 * @param patients that have to take the drug
	 */
	public void setPatients(List<PatientDrugModel> patients) {
		this.patients = patients;
	}





}

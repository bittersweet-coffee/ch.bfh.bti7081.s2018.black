package ch.bfh.bti7081.s2018.black.pms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Drug class
 */
@Entity
@Table(name="drug")
public class DrugModel extends EntityModel {
	
	// Name of the drug
	private String name;
	
	// Description of the drug size is set to 1000 characters
	@Column(length=1000)
	private String description;
	
	// Minimal Dose of the drug
	private Double minDose;
	
	// Maximal Dose of the drug
	private Double maxDose;
	
	// Measure of the drug
	@Enumerated(EnumType.STRING)
	private Measurement measure;
	
	// Unit of the drug
	@Enumerated(EnumType.STRING)
	private Unit unit;
	
	// List of the patients that have to take the drug
	// Is mapped with the variable drugs of the class PatientModel
    @OneToMany(mappedBy="patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientDrugModel> patients;
    
    // ENUM containing all possible measures for our drugs
 	private enum Measurement {
 		DOSE_INTEGER("Integer"), 
 		DOSE_DOUBLE("Double"),
 		DOSE_HALVES("Halves"); 	
 		
 		private final String enumMeasure;
 		
 		/**
 		 * Contructor for associating a string to each enum entry
 		 * @param enumMeasure string representing the name of the enum entry
 		 */
 		Measurement(String enumMeasure) {
 			this.enumMeasure = enumMeasure;
 		}
 		
 		/**
 		 * Getter for the enum entry as string
 		 * @return String representing the name of the enum entry
 		 */
 		public String getMeasureString() {
 			return this.enumMeasure;
 		}
 	};
 	
 	// ENUM containing all possible units for our drugs
 	private enum Unit {
 		UNIT_MILLILITER("ml"),
 		UNIT_MILLIGRAM("mg"),
 		UNIT_GRAM("g"),
 		UNIT_PILL("pills"),
 		UNIT_POUCH("pouch");
 		
 		private final String enumUnit;
 		
 		/**
 		 * Contructor for associating a string to each enum entry
 		 * @param enumMeasure string representing the name of the enum entry
 		 */
 		Unit(String enumUnit) {
 			this.enumUnit = enumUnit;
 		}
 		
 		/**
 		 * Getter for the enum entry as string
 		 * @return string representing the name of the enum entry
 		 */
 		public String getUnitString() {
 			return this.enumUnit;
 		}
 	};
 	
 	/**
	 * Method to check if the entered sose has the correct type and is in the drugs bounds
	 * @param enteredDose dose entered by the doctor
	 * @return pair containing the result and its corresponding message
	 */
	public Pair checkDose(Double enteredDose) {
		
		Pair checker = this.internalLimitCheck(enteredDose);
		Pair result = new Pair();
		
		switch (this.measure) {
		
		case DOSE_INTEGER:
			// Check whether enteredDose is an integer
			if((enteredDose - Math.floor(enteredDose)) == 0) {
				result.put(checker.getResult(), checker.getMessage());
			} else {
				result.put(false, "Entered Dose isn't an Integer!");
			};
			break;
			
		case DOSE_HALVES:
			// Check whether enteredDose is an integer or half	
			if ((enteredDose - Math.floor(enteredDose) == 0) || (enteredDose % 1 == 0.5)) {
				result.put(checker.getResult(), checker.getMessage());
			} else {
				result.put(false, "Entered Dose isn't an Half!");
			};
			break;
			
		case DOSE_DOUBLE:
			// enteredDose has to be double	
			if(this.measure.equals(Measurement.DOSE_DOUBLE)) {
				result.put(checker.getResult(), checker.getMessage());
			} else {
				result.put(false, "Entered dose isn't an integer!");
			};
			break;

		default:
			result.put(false, "Oops, the selected drug has no measurement set!");
			break;
		}
		
		return result;
	}
	
	/**
	 * Helper method for checking dose bounds
	 * @param enteredDose dose entered by the doctor
	 * @return Pair containing the result and its corresponding message
	 */
	private Pair internalLimitCheck(Double enteredDose) {
		if ((enteredDose >= this.minDose) && (enteredDose <= this.maxDose)) {
			return new Pair(true, "Success");
		} else {
			return new Pair(false, "The dose entered is not within the drug thresholds: \n" + "Min: " + this.minDose + " " + this.getUnit() + "\nMax: " + this.maxDose + " " + this.getUnit() + "\n");
		}
	}
	
	 /**
     * Getter for the unit
     * @return the unit specific to this drug
     */
	public String getUnit() {
		return this.unit.getUnitString();
	}
	
    /**
     * Getter for the measurement
     * @return the measure specific to this drug
     */
    public String getMeasure() {
    	return this.measure.getMeasureString();
    }
    
	/**
	 * Getter for the minimal dose for this drug
	 * @return minimal dose for this drug
	 */
	public Double getMinDose() {
		return this.minDose;
	}
	
	/**
	 * Getter for the maximal dose for this drug
	 * @return maximal dose for this drug
	 */
	public Double getMaxDose() {
		return this.maxDose;
	}
    
    /**
     * Getter for the name
     * @return the name of the drug
     */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter for the name
	 * @param name of the drug
	 */
	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * Getter for the description
     * @return the description of the drug
     */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setter for the description
	 * @param description of the drug
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for the patients
	 * @return a list of the patient that have to take the drug
	 */
	public List<PatientDrugModel> getPatients() {
		return this.patients;
	}

	/**
	 * Setter for the patients
	 * @param patients that have to take the drug
	 */
	public void setPatients(List<PatientDrugModel> patients) {
		this.patients = patients;
	}
}

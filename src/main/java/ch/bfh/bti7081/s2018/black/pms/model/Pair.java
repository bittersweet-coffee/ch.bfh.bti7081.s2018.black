package ch.bfh.bti7081.s2018.black.pms.model;

/**
 * Pair class
 * Pair is a Utility Class to help getting boolean results from a 
 * method while providing the corresponding message. 
 * Used for displaying detailed error messages in the GUI for example.
 */
public class Pair {
	// the actual result of the operation
    private boolean result;
    
    // the message corresponding to the result
    private String message;

    /**
     * Constructor without params which sets both variables to default
     */
    public Pair() {
    	this.result = false;
    	this.message = "";
    }

    /**
     * Constructor if result and message are already known
     * @param result the actual boolean result from the operation
     * @param message the message corresponding to the result
     */
    public Pair(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    /**
     * Getter for the result
     * @return the actual result from the operation
     */
    public boolean getResult() {
        return this.result;
    }

    /**
     * Getter for the message
     * @return the message corresponding to the actual result
     */
    public String getMessage() {
        return this.message;
    }
    
    /**
     * Setter for both result and message
     * @param result the actual result from the operation
     * @param message the message corresponding to the actual result
     */
    public void put(boolean result, String message) {
    	this.result = result;
    	this.message = message;
    }
}
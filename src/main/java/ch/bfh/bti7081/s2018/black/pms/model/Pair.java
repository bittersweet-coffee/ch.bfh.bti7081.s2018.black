package ch.bfh.bti7081.s2018.black.pms.model;

public class Pair {
    private boolean result;
    private String message;

    public Pair(boolean result, String message) {
        this.result = result;
        this.message = message;
    }
    
    public Pair() {
    	this.result = false;
    	this.message = "";
    }

    public boolean getResult() {
        return this.result;
    }

    public String getMessage() {
        return this.message;
    }
    
    public void put(boolean result, String message) {
    	this.result = result;
    	this.message = message;
    }
}
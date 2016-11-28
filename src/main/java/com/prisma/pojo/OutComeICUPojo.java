package com.prisma.pojo;

public class OutComeICUPojo {

	private String id;
    private String outcome;
    
    public OutComeICUPojo(String id, String outcome){
    	this.id = id;
    	this.outcome = outcome;
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
    
    	
}

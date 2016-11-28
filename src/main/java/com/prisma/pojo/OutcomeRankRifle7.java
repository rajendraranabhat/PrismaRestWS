package com.prisma.pojo;

public class OutcomeRankRifle7 {
	
	private String id;
	private int rank;
	private String var;
	private float weight;
	private String actual;
	private String value;
	private String description;
	private String pr1Description;
	private String mdcDescription;
	
	public String getId() {
		return id;
	}	
	public void setId(String id) {
		this.id = id;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = Math.abs(weight);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getActual() {
		return actual;
	}
	public void setActual(String actual) {
		this.actual = actual;
	}
	
	public String getPr1Description() {
		return pr1Description;
	}
	public void setPr1Description(String pr1Description) {
		this.pr1Description = pr1Description;
	}
	public String getMdcDescription() {
		return mdcDescription;
	}
	public void setMdcDescription(String mdcDescription) {
		this.mdcDescription = mdcDescription;
	}
	@Override
	public String toString() {
		return "[id:"+id+" rank:"+rank+" var:"+var+" weight:"+weight+" actual:"+actual+" value:"+value+" description:"+description+" pr1Description:"+pr1Description+" mdcDescription:"+mdcDescription+"]";
	}
}

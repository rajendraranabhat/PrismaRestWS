package com.prisma.pojo;

public class RiskAssessment {
	
	private String docId;
	private String patientId;
	
	private float rifle7;
	private float icu;
	private float ventilator;
	private float cardiovascular;
	private float sepsis;
	private float neurological;
	private float venous;
	private float wound;
	private int numAttempts;
	
	private int risktype; // 1 == initialrisk; 2 == finalrisk
	
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public float getRifle7() {
		return rifle7;
	}
	public void setRifle7(float rifle7) {
		this.rifle7 = rifle7;
	}
	public float getIcu() {
		return icu;
	}
	public void setIcu(float icu) {
		this.icu = icu;
	}
	public float getVentilator() {
		return ventilator;
	}
	public void setVentilator(float ventilator) {
		this.ventilator = ventilator;
	}
	public float getCardiovascular() {
		return cardiovascular;
	}
	public void setCardiovascular(float cardiovascular) {
		this.cardiovascular = cardiovascular;
	}
	public float getSepsis() {
		return sepsis;
	}
	public void setSepsis(float sepsis) {
		this.sepsis = sepsis;
	}
	public float getNeurological() {
		return neurological;
	}
	public void setNeurological(float neurological) {
		this.neurological = neurological;
	}
	public float getVenous() {
		return venous;
	}
	public void setVenous(float venous) {
		this.venous = venous;
	}
	public float getWound() {
		return wound;
	}
	public void setWound(float wound) {
		this.wound = wound;
	}
	public int getNumAttempts() {
		return numAttempts;
	}
	public void setNumAttempts(int numAttempts) {
		this.numAttempts = numAttempts;
	}
	public int getRisktype() {
		return risktype;
	}
	public void setRisktype(int risktype) {
		this.risktype = risktype;
	}	
}

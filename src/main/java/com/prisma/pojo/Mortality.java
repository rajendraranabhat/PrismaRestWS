package com.prisma.pojo;

public class Mortality {
	
	private String patientId;
	private float mort_status_30d;
	private float mort_status_90d;
	private float mort_status_6m;
	private float mort_status_1y;
	private float mort_status_2y;
	
	public String getPatientId() {
		return patientId;
	}
	
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public float getMort_status_30d() {
		return mort_status_30d;
	}
	
	public void setMort_status_30d(float mort_status_30d) {
		this.mort_status_30d = mort_status_30d;
	}
	
	public float getMort_status_90d() {
		return mort_status_90d;
	}
	public void setMort_status_90d(float mort_status_90d) {
		this.mort_status_90d = mort_status_90d;
	}
	
	public float getMort_status_6m() {
		return mort_status_6m;
	}
	
	public void setMort_status_6m(float mort_status_6m) {
		this.mort_status_6m = mort_status_6m;
	}
	public float getMort_status_1y() {
		return mort_status_1y;
	}
	
	public void setMort_status_1y(float mort_status_1y) {
		this.mort_status_1y = mort_status_1y;
	}
	
	public float getMort_status_2y() {
		return mort_status_2y;
	}
	
	public void setMort_status_2y(float mort_status_2y) {
		this.mort_status_2y = mort_status_2y;
	}
}

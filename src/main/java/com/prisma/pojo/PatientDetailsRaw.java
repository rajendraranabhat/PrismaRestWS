package com.prisma.pojo;

import java.util.HashMap;

public class PatientDetailsRaw {
	
	private String encounterId;
	private String patientId;
	private HashMap featureValueMap;
	
	public String getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public HashMap getFeatureValueMap() {
		return featureValueMap;
	}
	public void setFeatureValueMap(HashMap featureValueMap) {
		this.featureValueMap = featureValueMap;
	}
	
}


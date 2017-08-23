package com.prisma.pojo;

import java.util.HashMap;

public class PatientDetails {
	
	private String doctorId;
	private String doctorName;
	private String encounterId;
	private String patientId;
	private String story1;
	private String story2;
	private HashMap<Integer, String> labels;	

	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}	
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getStory1() {
		return story1;
	}
	public void setStory1(String story1) {
		this.story1 = story1;
	}
	public String getStory2() {
		return story2;
	}
	public void setStory2(String story2) {
		this.story2 = story2;
	}
	public HashMap<Integer, String> getLabels() {
		return labels;
	}
	public void setLabels(HashMap<Integer, String> labels) {
		this.labels = labels;
	}
	public String getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}
	
}

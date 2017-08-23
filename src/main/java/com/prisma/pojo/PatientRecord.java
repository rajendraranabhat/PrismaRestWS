package com.prisma.pojo;

public class PatientRecord {
	
	private String patientid;
	
	private String meddata;
	private String labdata;
	private String dgndata;
	
	public String getDgndata() {
		return dgndata;
	}
	public void setDgndata(String dgndata) {
		this.dgndata = dgndata;
	}
	public String getMeddata() {
		return meddata;
	}
	public void setMeddata(String meddata) {
		this.meddata = meddata;
	}
	public String getLabdata() {
		return labdata;
	}
	public void setLabdata(String labdata) {
		this.labdata = labdata;
	}	
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
}

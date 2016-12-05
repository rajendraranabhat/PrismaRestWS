package com.prisma.pojo;

public class IndexPatient {
	
	private String user;
	private String patientId;
	private int noAttempt;
	private float timeScreen;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public int getNoAttempt() {
		return noAttempt;
	}
	public void setNoAttempt(int noAttempt) {
		this.noAttempt = noAttempt;
	}
	public float getTimeScreen() {
		return timeScreen;
	}
	public void setTimeScreen(float timeScreen) {
		this.timeScreen = timeScreen;
	}
}

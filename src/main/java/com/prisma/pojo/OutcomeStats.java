package com.prisma.pojo;

public class OutcomeStats {
	
	private String username;
	private String patientId;
	private int outcomeId;
	private float timeScreen1;
	private float timeScreen2;
	private int click1;
	private int click2;	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public int getOutcomeId() {
		return outcomeId;
	}
	public void setOutcomeId(int outcomeId) {
		this.outcomeId = outcomeId;
	}
	public float getTimeScreen1() {
		return timeScreen1;
	}
	public void setTimeScreen1(float timeScreen1) {
		this.timeScreen1 = timeScreen1;
	}
	public float getTimeScreen2() {
		return timeScreen2;
	}
	public void setTimeScreen2(float timeScreen2) {
		this.timeScreen2 = timeScreen2;
	}
	public int getClick1() {
		return click1;
	}
	public void setClick1(int click1) {
		this.click1 = click1;
	}
	public int getClick2() {
		return click2;
	}
	public void setClick2(int click2) {
		this.click2 = click2;
	}
}

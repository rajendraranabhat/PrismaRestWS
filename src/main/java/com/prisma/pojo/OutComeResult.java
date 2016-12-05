package com.prisma.pojo;

public class OutComeResult {

	private String username;
	private String patientId;
	private int outcomeId;
	private int attempt1;
	private int attempt2;
	
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
	public int getAttempt1() {
		return attempt1;
	}
	public void setAttempt1(int attempt1) {
		this.attempt1 = attempt1;
	}
	public int getAttempt2() {
		return attempt2;
	}
	public void setAttempt2(int attempt2) {
		this.attempt2 = attempt2;
	}	
}

package com.prisma.pojo;

public class OutcomeRankFeature {

	private int orderId;
	private String encounter_deiden_id;
	private String patient_deiden_id;
	private String complicationname;

	private String btmfeature1;
	private String btmfeature2;
	private String btmfeature3;

	private String topfeature1;
	private String topfeature2;
	private String topfeature3;
	
	private String riskScore;
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getEncounter_deiden_id() {
		return encounter_deiden_id;
	}

	public void setEncounter_deiden_id(String encounter_deiden_id) {
		this.encounter_deiden_id = encounter_deiden_id;
	}

	public String getPatient_deiden_id() {
		return patient_deiden_id;
	}

	public void setPatient_deiden_id(String patient_deiden_id) {
		this.patient_deiden_id = patient_deiden_id;
	}

	public String getComplicationname() {
		return complicationname;
	}

	public void setComplicationname(String complicationname) {
		this.complicationname = complicationname;
	}

	public String getBtmfeature1() {
		return btmfeature1;
	}

	public void setBtmfeature1(String btmfeature1) {
		this.btmfeature1 = btmfeature1;
	}

	public String getBtmfeature2() {
		return btmfeature2;
	}

	public void setBtmfeature2(String btmfeature2) {
		this.btmfeature2 = btmfeature2;
	}

	public String getBtmfeature3() {
		return btmfeature3;
	}

	public void setBtmfeature3(String btmfeature3) {
		this.btmfeature3 = btmfeature3;
	}

	public String getTopfeature1() {
		return topfeature1;
	}

	public void setTopfeature1(String topfeature1) {
		this.topfeature1 = topfeature1;
	}

	public String getTopfeature2() {
		return topfeature2;
	}

	public void setTopfeature2(String topfeature2) {
		this.topfeature2 = topfeature2;
	}

	public String getTopfeature3() {
		return topfeature3;
	}

	public void setTopfeature3(String topfeature3) {
		this.topfeature3 = topfeature3;
	}
	
	
	public String getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
	}

	@Override
	public String toString() {
		return "[encounter_deiden_id:" + encounter_deiden_id + " complicationname:" + complicationname + " btmfeature1:" + btmfeature1 + " btmfeature2:" + btmfeature2 +
				" btmfeature3:" + btmfeature3 + " patient_deiden_id:"
				+ patient_deiden_id + " topfeature1:" + topfeature1 + " topfeature2:" + topfeature2 + " topfeature3:"
				+ topfeature3 + " riskScore:"+ riskScore+ "]";
	}
}

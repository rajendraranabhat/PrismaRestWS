package com.prisma.pojo;

import java.util.ArrayList;

public class OutcomeRank {
	
	private String outcomeRankName;
	private String outcome;
	private String label;
	private String table;
	private ArrayList outcomeRankPositiveList;
	private ArrayList outcomeRankNegativeList;
	
	public String getOutcomeRankName() {
		return outcomeRankName;
	}
	public void setOutcomeRankName(String outcomeRankName) {
		this.outcomeRankName = outcomeRankName;
	}	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public ArrayList getOutcomeRankPositiveList() {
		return outcomeRankPositiveList;
	}
	public void setOutcomeRankPositiveList(ArrayList outcomeRankPositiveList) {
		this.outcomeRankPositiveList = outcomeRankPositiveList;
	}
	public ArrayList getOutcomeRankNegativeList() {
		return outcomeRankNegativeList;
	}
	public void setOutcomeRankNegativeList(ArrayList outcomeRankNegativeList) {
		this.outcomeRankNegativeList = outcomeRankNegativeList;
	}	
}

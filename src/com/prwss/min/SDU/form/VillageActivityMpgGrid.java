package com.prwss.min.SDU.form;

import java.io.Serializable;

public class VillageActivityMpgGrid implements Serializable{
	
	private static final long serialVersionUID = 18916548616466L;
	
	private String activity;
	private String expectedStartDate;
	private String expectedEndDate;
	private String village;
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getExpectedStartDate() {
		return expectedStartDate;
	}
	public void setExpectedStartDate(String expectedStartDate) {
		this.expectedStartDate = expectedStartDate;
	}
	public String getExpectedEndDate() {
		return expectedEndDate;
	}
	public void setExpectedEndDate(String expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	@Override
	public String toString() {
		return "VillageActivityMpgGrid [activity=" + activity + ", expectedStartDate=" + expectedStartDate
				+ ", expectedEndDate=" + expectedEndDate + ", village=" + village + "]";
	}
	
}

package com.prwss.min.SDU.bean;

public class ConsolidatedPlanActivityWiseDTO {

	private String activityName;
	private String cmb_name;
	private Long totalComponent;  

	@Override
	public String toString() {
		return "ConsolidatedPlanActivityWiseDTO [activityName=" + activityName + ", cmb_name=" + cmb_name
				+ ", totalComponent=" + totalComponent + "]";
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getCmb_name() {
		return cmb_name;
	}

	public void setCmb_name(String cmb_name) {
		this.cmb_name = cmb_name;
	}

	public Long getTotalComponent() {
		return totalComponent;
	}

	public void setTotalComponent(Long totalComponent) {
		this.totalComponent = totalComponent;
	}
	
	

}

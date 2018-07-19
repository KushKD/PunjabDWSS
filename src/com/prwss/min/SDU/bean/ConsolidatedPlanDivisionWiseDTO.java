package com.prwss.min.SDU.bean;

import java.sql.Date;

import org.apache.struts.validator.ValidatorForm;

public class ConsolidatedPlanDivisionWiseDTO extends ValidatorForm{
	
	private static final long serialVersionUID = -636159851755286135L;
	
	private Integer divisionId;
	private String divisionName;
	private String componentName;
	private Long totalVillage;
	private String villageName;
	private String activityName;
	private Date expectedStartDate;
	private Date expectedEndDate;
	
	public Integer getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public Long getTotalVillage() {
		return totalVillage;
	}
	public void setTotalVillage(Long totalVillage) {
		this.totalVillage = totalVillage;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Date getExpectedStartDate() {
		return expectedStartDate;
	}
	public void setExpectedStartDate(Date expectedStartDate) {
		this.expectedStartDate = expectedStartDate;
	}
	public Date getExpectedEndDate() {
		return expectedEndDate;
	}
	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}
	@Override
	public String toString() {
		return "ConsolidatedPlanDivisionWiseDTO [divisionId=" + divisionId + ", divisionName=" + divisionName
				+ ", componentName=" + componentName + ", totalVillage=" + totalVillage + "]";
	}
	
}

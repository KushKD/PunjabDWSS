package com.prwss.min.SDU.form;

import java.sql.Date;

import org.apache.struts.validator.ValidatorForm;

public class DivisionWisePlanViewForm extends ValidatorForm{
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 8830703939324461036L;
	
	private Integer activityVillageId;
	private String financialYear;
	private String divisionId;
	private String categoryName;
	private String stageName;
	private String componentName;
	private String villageName;
	private String activityName;
	private Date startDate;
	private Date endDate;
	private String campaignName;
	private String startDate1;
	private String endDate1;
	
	public Integer getActivityVillageId() {
		return activityVillageId;
	}
	public void setActivityVillageId(Integer activityVillageId) {
		this.activityVillageId = activityVillageId;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStartDate1() {
		return startDate1;
	}
	public void setStartDate1(String startDate1) {
		this.startDate1 = startDate1;
	}
	public String getEndDate1() {
		return endDate1;
	}
	public void setEndDate1(String endDate1) {
		this.endDate1 = endDate1;
	}
	@Override
	public String toString() {
		return "DivisionWisePlanViewForm [activityVillageId=" + activityVillageId + ", financialYear=" + financialYear
				+ ", divisionId=" + divisionId + ", categoryName=" + categoryName + ", stageName=" + stageName
				+ ", componentName=" + componentName + ", villageName=" + villageName + ", activityName=" + activityName
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", campaignName=" + campaignName
				+ ", startDate1=" + startDate1 + ", endDate1=" + endDate1 + "]";
	}
	
}

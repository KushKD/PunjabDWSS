package com.prwss.min.SDU.BO;

import java.io.Serializable;
import java.sql.Date;

public class TrackingDto implements Serializable{
	
	private static final long serialVersionUID = -6921239477860986521L;
	
	private String s_No;
	private String activity_Name;
	private Date expected_Start_Date;
	private Date expected_End_Date;
	private Date actual_start_date;
	private Date actual_End_Date;
	private String outcome_achieved;
	private Integer achieved;
	private String coments;
	private Integer activityId;
	private Integer villageactivityId;
	private Integer villageActivityDetailID;
	private String aStartDate;
	private String aEndDate;
	private String dbAcheived;
	private String  outcome_Name;
	private Integer outcomeId;
	
	
	
	
	public String getOutcome_Name() {
		return outcome_Name;
	}
	public void setOutcome_Name(String outcome_Name) {
		this.outcome_Name = outcome_Name;
	}
	public String getS_No() {
		return s_No;
	}
	public void setS_No(String s_No) {
		this.s_No = s_No;
	}
	public String getActivity_Name() {
		return activity_Name;
	}
	public void setActivity_Name(String activity_Name) {
		this.activity_Name = activity_Name;
	}
	public Date getExpected_Start_Date() {
		return expected_Start_Date;
	}
	public void setExpected_Start_Date(Date expected_Start_Date) {
		this.expected_Start_Date = expected_Start_Date;
	}
	public Date getExpected_End_Date() {
		return expected_End_Date;
	}
	public void setExpected_End_Date(Date expected_End_Date) {
		this.expected_End_Date = expected_End_Date;
	}
	public Date getActual_start_date() {
		return actual_start_date;
	}
	public void setActual_start_date(Date actual_start_date) {
		this.actual_start_date = actual_start_date;
	}
	public Date getActual_End_Date() {
		return actual_End_Date;
	}
	public void setActual_End_Date(Date actual_End_Date) {
		this.actual_End_Date = actual_End_Date;
	}
	public String getOutcome_achieved() {
		return outcome_achieved;
	}
	public void setOutcome_achieved(String outcome_achieved) {
		this.outcome_achieved = outcome_achieved;
	}
	public Integer getAchieved() {
		return achieved;
	}
	public void setAchieved(Integer achieved) {
		this.achieved = achieved;
	}
	public String getComents() {
		return coments;
	}
	public void setComents(String coments) {
		this.coments = coments;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getVillageactivityId() {
		return villageactivityId;
	}
	public void setVillageactivityId(Integer villageactivityId) {
		this.villageactivityId = villageactivityId;
	}
	public Integer getVillageActivityDetailID() {
		return villageActivityDetailID;
	}
	public void setVillageActivityDetailID(Integer villageActivityDetailID) {
		this.villageActivityDetailID = villageActivityDetailID;
	}
	public String getaStartDate() {
		return aStartDate;
	}
	public void setaStartDate(String aStartDate) {
		this.aStartDate = aStartDate;
	}
	public String getaEndDate() {
		return aEndDate;
	}
	public void setaEndDate(String aEndDate) {
		this.aEndDate = aEndDate;
	}
	public String getDbAcheived() {
		return dbAcheived;
	}
	public void setDbAcheived(String dbAcheived) {
		this.dbAcheived = dbAcheived;
	}
	public Integer getOutcomeId() {
		return outcomeId;
	}
	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
	}
	@Override
	public String toString() {
		return "TrackingDto [s_No=" + s_No + ", activity_Name=" + activity_Name
				+ ", expected_Start_Date=" + expected_Start_Date
				+ ", expected_End_Date=" + expected_End_Date
				+ ", actual_start_date=" + actual_start_date
				+ ", actual_End_Date=" + actual_End_Date
				+ ", outcome_achieved=" + outcome_achieved + ", achieved="
				+ achieved + ", coments=" + coments + ", activityId="
				+ activityId + ", villageactivityId=" + villageactivityId
				+ ", villageActivityDetailID=" + villageActivityDetailID
				+ ", aStartDate=" + aStartDate + ", aEndDate=" + aEndDate
				+ ", dbAcheived=" + dbAcheived + ", outcome_Name="
				+ outcome_Name + ", outcomeId=" + outcomeId + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
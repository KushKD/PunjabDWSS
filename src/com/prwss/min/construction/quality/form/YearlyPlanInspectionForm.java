/**
 * 
 */
package com.prwss.min.construction.quality.form;

import org.apache.struts.action.ActionForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class YearlyPlanInspectionForm extends ActionForm {
	private static final long serialVersionUID = -233110618776L;
	
	private String financialYear;
	private String finalizationDate;
	private String inspectionType;
	private String assignedTo;
	private String component;
	private String numberOfVillage;
	private String visitVillage;
	private String periodIml;
	private String visitPerVillage;
	private String totalVisit;
	private String totalDuration;
	private String perMonthVisit;
	private String comments;
	private String financialYearName;
	private String assignedToName;
	private String componentName;
	private String planName;
	private Datagrid yearlyPlanGrid;
	private String yearPlanId;
	private String componentPlanId;
	
	
	public String getYearPlanId() {
		return yearPlanId;
	}
	public void setYearPlanId(String yearPlanId) {
		this.yearPlanId = yearPlanId;
	}
	public String getComponentPlanId() {
		return componentPlanId;
	}
	public void setComponentPlanId(String componentPlanId) {
		this.componentPlanId = componentPlanId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getAssignedToName() {
		return assignedToName;
	}
	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getFinancialYearName() {
		return financialYearName;
	}
	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}
	public Datagrid getYearlyPlanGrid() {
		return yearlyPlanGrid;
	}
	public void setYearlyPlanGrid(Datagrid yearlyPlanGrid) {
		this.yearlyPlanGrid = yearlyPlanGrid;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getFinalizationDate() {
		return finalizationDate;
	}
	public void setFinalizationDate(String finalizationDate) {
		this.finalizationDate = finalizationDate;
	}
	public String getInspectionType() {
		return inspectionType;
	}
	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getNumberOfVillage() {
		return numberOfVillage;
	}
	public void setNumberOfVillage(String numberOfVillage) {
		this.numberOfVillage = numberOfVillage;
	}
	public String getVisitVillage() {
		return visitVillage;
	}
	public void setVisitVillage(String visitVillage) {
		this.visitVillage = visitVillage;
	}
	public String getPeriodIml() {
		return periodIml;
	}
	public void setPeriodIml(String periodIml) {
		this.periodIml = periodIml;
	}
	public String getVisitPerVillage() {
		return visitPerVillage;
	}
	public void setVisitPerVillage(String visitPerVillage) {
		this.visitPerVillage = visitPerVillage;
	}
	public String getTotalVisit() {
		return totalVisit;
	}
	public void setTotalVisit(String totalVisit) {
		this.totalVisit = totalVisit;
	}
	public String getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(String totalDuration) {
		this.totalDuration = totalDuration;
	}
	public String getPerMonthVisit() {
		return perMonthVisit;
	}
	public void setPerMonthVisit(String perMonthVisit) {
		this.perMonthVisit = perMonthVisit;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "YearlyPlanInspection [financialYear=" + financialYear + ", finalizationDate=" + finalizationDate
				+ ", inspectionType=" + inspectionType + ", assignedTo=" + assignedTo + ", component=" + component
				+ ", numberOfVillage=" + numberOfVillage + ", visitVillage=" + visitVillage + ", periodIml=" + periodIml
				+ ", visitPerVillage=" + visitPerVillage + ", totalVisit=" + totalVisit + ", totalDuration="
				+ totalDuration + ", perMonthVisit=" + perMonthVisit + ", comments=" + comments + "]";
	}
	
}

package com.prwss.min.construction.quality.form;

import java.io.Serializable;

/**
 * @author BH390738
 *
 */
public class YearlyInspectionGrid implements Serializable{
	
	private static final long serialVersionUID = 36350765334815L;

	private String financialYear;
	private String financialYearName;
	private String component;
	private String numberOfVillage;
	private String finalizationDate;
	
	private String visitVillage;
	private String periodIml;
	private String visitPerVillage;
	private String totalVisit;
	private String totalDuration;
	private String perMonthVisit;
	
	
	
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

	public String getFinalizationDate() {
		return finalizationDate;
	}

	public void setFinalizationDate(String finalizationDate) {
		this.finalizationDate = finalizationDate;
	}

	public String getFinancialYearName() {
		return financialYearName;
	}

	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYears) {
		this.financialYear = financialYears;
	}

	@Override
	public String toString() {
		return "YearlyInspectionGrid [financialYear=" + financialYear + ", financialYearName=" + financialYearName
				+ ", component=" + component + ", numberOfVillage=" + numberOfVillage + ", finalizationDate="
				+ finalizationDate + "]";
	}
	
}

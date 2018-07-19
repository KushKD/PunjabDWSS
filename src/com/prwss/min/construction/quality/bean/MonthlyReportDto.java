/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

/**
 * @author BH390738
 *
 */
public class MonthlyReportDto implements Serializable{
	private static final long serialVersionUID=-133452345L;
	
	private String  financialYear;
	private String  yearlyPlanName;
	private String month;
	private Integer lyingWithUser;
	private Integer monthId;
	private Integer fnclYear;
	
	
	
	public Integer getFnclYear() {
		return fnclYear;
	}
	public void setFnclYear(Integer fnclYear) {
		this.fnclYear = fnclYear;
	}
	public Integer getMonthId() {
		return monthId;
	}
	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}
	public Integer getLyingWithUser() {
		return lyingWithUser;
	}
	public void setLyingWithUser(Integer lyingWithUser) {
		this.lyingWithUser = lyingWithUser;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getYearlyPlanName() {
		return yearlyPlanName;
	}
	public void setYearlyPlanName(String yearlyPlanName) {
		this.yearlyPlanName = yearlyPlanName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return "MonthlyReportDto [financialYear=" + financialYear + ", yearlyPlanName=" + yearlyPlanName + ", month="
				+ month + "]";
	}
	
}

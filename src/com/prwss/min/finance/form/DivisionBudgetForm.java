/**
 * 
 */
package com.prwss.min.finance.form;

import org.apache.struts.action.ActionForm;

/**
 * @author BH390738
 *
 */
public class DivisionBudgetForm extends ActionForm{

	private static final long serialVersionUID = -363555415L;
	
	private String financialYear;
	private String division;
	private String scheme;
	private String component;
	private String subComponent;
	private String subSubComponent;
	private String activity;
	private String estimatedCosts;
	private String alreadyExpended;
	private String nextYearReq;
	private String quarter1;
	private String quarter2;
	private String quarter3;
	private String quarter4;
	private String financialYearName;
	private String budgetRefNo;
	private String subSubComponentLevel3;
	private String divAnnBudgId;
	private String budgetDiv;
	
	
	
	
	
	
	public String getBudgetDiv() {
		return budgetDiv;
	}
	public void setBudgetDiv(String budgetDiv) {
		this.budgetDiv = budgetDiv;
	}
	public String getDivAnnBudgId() {
		return divAnnBudgId;
	}
	public void setDivAnnBudgId(String divAnnBudgId) {
		this.divAnnBudgId = divAnnBudgId;
	}
	public String getSubSubComponentLevel3() {
		return subSubComponentLevel3;
	}
	public void setSubSubComponentLevel3(String subSubComponentLevel3) {
		this.subSubComponentLevel3 = subSubComponentLevel3;
	}
	public String getBudgetRefNo() {
		return budgetRefNo;
	}
	public void setBudgetRefNo(String budgetRefNo) {
		this.budgetRefNo = budgetRefNo;
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
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getSubComponent() {
		return subComponent;
	}
	public void setSubComponent(String subComponent) {
		this.subComponent = subComponent;
	}
	public String getSubSubComponent() {
		return subSubComponent;
	}
	public void setSubSubComponent(String subSubComponent) {
		this.subSubComponent = subSubComponent;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getEstimatedCosts() {
		return estimatedCosts;
	}
	public void setEstimatedCosts(String estimatedCosts) {
		this.estimatedCosts = estimatedCosts;
	}
	public String getAlreadyExpended() {
		return alreadyExpended;
	}
	public void setAlreadyExpended(String alreadyExpended) {
		this.alreadyExpended = alreadyExpended;
	}
	public String getNextYearReq() {
		return nextYearReq;
	}
	public void setNextYearReq(String nextYearReq) {
		this.nextYearReq = nextYearReq;
	}
	public String getQuarter1() {
		return quarter1;
	}
	public void setQuarter1(String quarter1) {
		this.quarter1 = quarter1;
	}
	public String getQuarter2() {
		return quarter2;
	}
	public void setQuarter2(String quarter2) {
		this.quarter2 = quarter2;
	}
	public String getQuarter3() {
		return quarter3;
	}
	public void setQuarter3(String quarter3) {
		this.quarter3 = quarter3;
	}
	public String getQuarter4() {
		return quarter4;
	}
	public void setQuarter4(String quarter4) {
		this.quarter4 = quarter4;
	}
	@Override
	public String toString() {
		return "DivisionBudgetForm [financialYear=" + financialYear + ", division=" + division + ", scheme=" + scheme
				+ ", component=" + component + ", subComponent=" + subComponent + ", subSubComponent=" + subSubComponent
				+ ", activity=" + activity + ", estimatedCosts=" + estimatedCosts + ", alreadyExpended="
				+ alreadyExpended + ", nextYearReq=" + nextYearReq + ", quarter1=" + quarter1 + ", quarter2=" + quarter2
				+ ", quarter3=" + quarter3 + ", quarter4=" + quarter4 + "]";
	}
	
}

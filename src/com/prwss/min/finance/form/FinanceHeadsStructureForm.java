/**
 * 
 */
package com.prwss.min.finance.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class FinanceHeadsStructureForm extends ValidatorForm {

	private static final long serialVersionUID = 36355776815L;
	
	private String description;
	private String financialYear;
	private String dimandNo;
	private String majorHead;
	private String subMajorHead;
	private String minorHead;
	private String subHead;
	private String detailedHead;
	private String SOE;
	private String planNonPlan;
	private String votedCharged;
	private String headStructureId;
	
	
	
	public String getHeadStructureId() {
		return headStructureId;
	}
	public void setHeadStructureId(String headStructureId) {
		this.headStructureId = headStructureId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getDimandNo() {
		return dimandNo;
	}
	public void setDimandNo(String dimandNo) {
		this.dimandNo = dimandNo;
	}
	public String getMajorHead() {
		return majorHead;
	}
	public void setMajorHead(String majorHead) {
		this.majorHead = majorHead;
	}
	public String getSubMajorHead() {
		return subMajorHead;
	}
	public void setSubMajorHead(String subMajorHead) {
		this.subMajorHead = subMajorHead;
	}
	public String getMinorHead() {
		return minorHead;
	}
	public void setMinorHead(String minorHead) {
		this.minorHead = minorHead;
	}
	public String getSubHead() {
		return subHead;
	}
	public void setSubHead(String subHead) {
		this.subHead = subHead;
	}
	public String getDetailedHead() {
		return detailedHead;
	}
	public void setDetailedHead(String detailedHead) {
		this.detailedHead = detailedHead;
	}
	public String getSOE() {
		return SOE;
	}
	public void setSOE(String sOE) {
		SOE = sOE;
	}
	public String getPlanNonPlan() {
		return planNonPlan;
	}
	public void setPlanNonPlan(String planNonPlan) {
		this.planNonPlan = planNonPlan;
	}
	public String getVotedCharged() {
		return votedCharged;
	}
	public void setVotedCharged(String votedCharged) {
		this.votedCharged = votedCharged;
	}
	@Override
	public String toString() {
		return "FinanceHeadsStructureForm [description=" + description + ", financialYear=" + financialYear
				+ ", dimandNo=" + dimandNo + ", majorHead=" + majorHead + ", subMajorHead=" + subMajorHead
				+ ", minorHead=" + minorHead + ", subHead=" + subHead + ", detailedHead=" + detailedHead + ", SOE="
				+ SOE + ", planNonPlan=" + planNonPlan + ", votedCharged=" + votedCharged + "]";
	}
	
}

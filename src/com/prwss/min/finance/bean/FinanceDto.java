/**
 * 
 */
package com.prwss.min.finance.bean;

import java.sql.Date;

/**
 * @author BH390738
 *
 */
public class FinanceDto {
	
	
	private String head_type;
	private String headName;
	private String headNumber;
	private String parent_head_name;
	private String headStructureName;
	private String financialYear;
	private String demandHeadNumber;
	private String majorHead;
	private String subMajorHead;
	private String minorHead;
	private String subHead;
	private String detailHead;
	private String soeHead;
	private String planNonPlan;
	private String votedCharged;
	private String Hoa;
	private String componentName;
	private String componentType;
	private String parentComponentName;
	private String budgetRefNo;
	private String division;
	private String subComponentName;
	private String activityComponentName;
	private String subSubComponentName;
	private Long estimatedCost;
	private Long reqNxtYear;
	private Long alreadySpent;
	private Double qtr1;
	private Double qtr2;
	private Double qtr3;
	private Double qtr4;
	private Integer cmb_id;
	private String cmb_name;
	private Long scheme;
	private String fundSourceMst;
	private String stateShare;
	private String centerShare;
	private String transactionNo;
	private String transactionType;
	private Date transactionDate;
	private Double paymentAmt;
	private String transactionDate1;
	private String ddoNumber;
	private String ddoName;
	private Long headId;
	private Integer parent_head_id;
	private Integer headType;
	

	private Integer financialYears;
	private Integer demandNumber;
	private Integer majorHeads;
	private Integer subMajorHeads;
	private Integer minorHeads;
	private Integer subHeads;
	private Integer detailedHead;
	private Integer soeNumber;
	private Integer planNonplan;
	private Integer votedChargeds;
	private  Long headStructureId;
	private String description;
	private Long componentId;
	private Long componentDetailsId;
	private Integer parentCompoId;
	private Integer componentTypes;
	private Integer activeFlag;
	private Integer finYear;
	private Integer divisionId;
	private Long schemeId;
	private Long subComponentId;
	private Long subComponentLevel3Id;
	private Long activityId;
	private Long subSubComponentId;
	private Long divAnnBudgId;
	
	private Integer isForward;
	private String forward;
	private String divisonSubDivisonDetailsName;
	
	
	
	
	
	public String getDivisonSubDivisonDetailsName() {
		return divisonSubDivisonDetailsName;
	}

	public void setDivisonSubDivisonDetailsName(String divisonSubDivisonDetailsName) {
		this.divisonSubDivisonDetailsName = divisonSubDivisonDetailsName;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public Integer getIsForward() {
		return isForward;
	}

	public void setIsForward(Integer isForward) {
		this.isForward = isForward;
	}

	public Long getDivAnnBudgId() {
		return divAnnBudgId;
	}

	public void setDivAnnBudgId(Long divAnnBudgId) {
		this.divAnnBudgId = divAnnBudgId;
	}

	public Long getSubSubComponentId() {
		return subSubComponentId;
	}

	public void setSubSubComponentId(Long subSubComponentId) {
		this.subSubComponentId = subSubComponentId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	public Long getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}

	public Long getSubComponentId() {
		return subComponentId;
	}

	public void setSubComponentId(Long subComponentId) {
		this.subComponentId = subComponentId;
	}

	public Long getSubComponentLevel3Id() {
		return subComponentLevel3Id;
	}

	public void setSubComponentLevel3Id(Long subComponentLevel3Id) {
		this.subComponentLevel3Id = subComponentLevel3Id;
	}

	public Integer getFinYear() {
		return finYear;
	}

	public void setFinYear(Integer finYear) {
		this.finYear = finYear;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getParentCompoId() {
		return parentCompoId;
	}

	public void setParentCompoId(Integer parentCompoId) {
		this.parentCompoId = parentCompoId;
	}

	public Integer getComponentTypes() {
		return componentTypes;
	}

	public void setComponentTypes(Integer componentTypes) {
		this.componentTypes = componentTypes;
	}

	public Long getComponentDetailsId() {
		return componentDetailsId;
	}

	public void setComponentDetailsId(Long componentDetailsId) {
		this.componentDetailsId = componentDetailsId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

	public Integer getFinancialYears() {
		return financialYears;
	}

	public void setFinancialYears(Integer financialYears) {
		this.financialYears = financialYears;
	}

	public Integer getDemandNumber() {
		return demandNumber;
	}

	public void setDemandNumber(Integer demandNumber) {
		this.demandNumber = demandNumber;
	}

	public Integer getMajorHeads() {
		return majorHeads;
	}

	public void setMajorHeads(Integer majorHeads) {
		this.majorHeads = majorHeads;
	}

	public Integer getSubMajorHeads() {
		return subMajorHeads;
	}

	public void setSubMajorHeads(Integer subMajorHeads) {
		this.subMajorHeads = subMajorHeads;
	}

	public Integer getMinorHeads() {
		return minorHeads;
	}

	public void setMinorHeads(Integer minorHeads) {
		this.minorHeads = minorHeads;
	}

	public Integer getSubHeads() {
		return subHeads;
	}

	public void setSubHeads(Integer subHeads) {
		this.subHeads = subHeads;
	}

	public Integer getDetailedHead() {
		return detailedHead;
	}

	public void setDetailedHead(Integer detailedHead) {
		this.detailedHead = detailedHead;
	}

	public Integer getSoeNumber() {
		return soeNumber;
	}

	public void setSoeNumber(Integer soeNumber) {
		this.soeNumber = soeNumber;
	}

	public Integer getPlanNonplan() {
		return planNonplan;
	}

	public void setPlanNonplan(Integer planNonplan) {
		this.planNonplan = planNonplan;
	}

	public Integer getVotedChargeds() {
		return votedChargeds;
	}

	public void setVotedChargeds(Integer votedChargeds) {
		this.votedChargeds = votedChargeds;
	}

	public Long getHeadStructureId() {
		return headStructureId;
	}

	public void setHeadStructureId(Long headStructureId) {
		this.headStructureId = headStructureId;
	}

	public Integer getHeadType() {
		return headType;
	}

	public void setHeadType(Integer headType) {
		this.headType = headType;
	}

	public Integer getParent_head_id() {
		return parent_head_id;
	}

	public void setParent_head_id(Integer parent_head_id) {
		this.parent_head_id = parent_head_id;
	}

	public Long getHeadId() {
		return headId;
	}

	public void setHeadId(Long headId) {
		this.headId = headId;
	}

	public String getDdoNumber() {
		return ddoNumber;
	}

	public void setDdoNumber(String ddoNumber) {
		this.ddoNumber = ddoNumber;
	}

	public String getDdoName() {
		return ddoName;
	}

	public void setDdoName(String ddoName) {
		this.ddoName = ddoName;
	}

	public String getTransactionDate1() {
		return transactionDate1;
	}

	public void setTransactionDate1(String transactionDate1) {
		this.transactionDate1 = transactionDate1;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getPaymentAmt() {
		return paymentAmt;
	}

	public void setPaymentAmt(Double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	public Long getScheme() {
		return scheme;
	}

	public void setScheme(Long scheme) {
		this.scheme = scheme;
	}

	public String getFundSourceMst() {
		return fundSourceMst;
	}

	public void setFundSourceMst(String fundSourceMst) {
		this.fundSourceMst = fundSourceMst;
	}

	public String getStateShare() {
		return stateShare;
	}

	public void setStateShare(String stateShare) {
		this.stateShare = stateShare;
	}

	public String getCenterShare() {
		return centerShare;
	}

	public void setCenterShare(String centerShare) {
		this.centerShare = centerShare;
	}

	public Integer getCmb_id() {
		return cmb_id;
	}

	public void setCmb_id(Integer cmb_id) {
		this.cmb_id = cmb_id;
	}

	public String getCmb_name() {
		return cmb_name;
	}

	public void setCmb_name(String cmb_name) {
		this.cmb_name = cmb_name;
	}

	public String getSubComponentName() {
		return subComponentName;
	}

	public void setSubComponentName(String subComponentName) {
		this.subComponentName = subComponentName;
	}

	public String getActivityComponentName() {
		return activityComponentName;
	}

	public void setActivityComponentName(String activityComponentName) {
		this.activityComponentName = activityComponentName;
	}

	public String getSubSubComponentName() {
		return subSubComponentName;
	}

	public void setSubSubComponentName(String subSubComponentName) {
		this.subSubComponentName = subSubComponentName;
	}

	public Long getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Long estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Long getAlreadySpent() {
		return alreadySpent;
	}

	public void setAlreadySpent(Long alreadySpent) {
		this.alreadySpent = alreadySpent;
	}

	public Double getQtr1() {
		return qtr1;
	}

	public void setQtr1(Double qtr1) {
		this.qtr1 = qtr1;
	}

	public Double getQtr2() {
		return qtr2;
	}

	public void setQtr2(Double qtr2) {
		this.qtr2 = qtr2;
	}

	public Double getQtr3() {
		return qtr3;
	}

	public void setQtr3(Double qtr3) {
		this.qtr3 = qtr3;
	}

	public Double getQtr4() {
		return qtr4;
	}

	public void setQtr4(Double qtr4) {
		this.qtr4 = qtr4;
	}

	public Long getReqNxtYear() {
		return reqNxtYear;
	}

	public void setReqNxtYear(Long reqNxtYear) {
		this.reqNxtYear = reqNxtYear;
	}

	public String getBudgetRefNo() {
		return budgetRefNo;
	}

	public void setBudgetRefNo(String budgetRefNo) {
		this.budgetRefNo = budgetRefNo;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getParentComponentName() {
		return parentComponentName;
	}

	public void setParentComponentName(String parentComponentName) {
		this.parentComponentName = parentComponentName;
	}

	public String getHoa() {
		return Hoa;
	}

	public void setHoa(String hoa) {
		Hoa = hoa;
	}

	public String getHeadStructureName() {
		return headStructureName;
	}

	public void setHeadStructureName(String headStructureName) {
		this.headStructureName = headStructureName;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getDemandHeadNumber() {
		return demandHeadNumber;
	}

	public void setDemandHeadNumber(String demandHeadNumber) {
		this.demandHeadNumber = demandHeadNumber;
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

	public String getDetailHead() {
		return detailHead;
	}

	public void setDetailHead(String detailHead) {
		this.detailHead = detailHead;
	}

	public String getSoeHead() {
		return soeHead;
	}

	public void setSoeHead(String soeHead) {
		this.soeHead = soeHead;
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

	public String getHead_type() {
		return head_type;
	}

	public void setHead_type(String head_type) {
		this.head_type = head_type;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getHeadNumber() {
		return headNumber;
	}

	public void setHeadNumber(String headNumber) {
		this.headNumber = headNumber;
	}

	public String getParent_head_name() {
		return parent_head_name;
	}

	public void setParent_head_name(String parent_head_name) {
		this.parent_head_name = parent_head_name;
	}

	@Override
	public String toString() {
		return "FinanceDto [head_type=" + head_type + ", headName=" + headName + ", headNumber=" + headNumber
				+ ", parent_head_name=" + parent_head_name + "]";
	}

}

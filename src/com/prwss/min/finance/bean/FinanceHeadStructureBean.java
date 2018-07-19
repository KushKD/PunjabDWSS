/**
 * 
 */
package com.prwss.min.finance.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.min.sanitation.bean.ComboBeanDetails;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="finance_head_structrure",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class FinanceHeadStructureBean implements Serializable{
	
private static final long serialVersionUID = 3635577615L;
	
	@Id
	@GeneratedValue(generator = "finance_head_structrure_head_structure_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "finance_head_structrure_head_structure_id_seq", sequenceName = "prwss_main.finance_head_structrure_head_structure_id_seq")
	@Column(name="head_structure_id")
	private Long headStructureId;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="financial_year",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboDetailsFinancialYear;
	
	@OneToOne(targetEntity=FinanceHeadBean.class)
	@JoinColumn(name="demand_number",referencedColumnName="head_id",insertable=false,updatable=false)
	private FinanceHeadBean  financeHeadBeanDemand;
	
	@OneToOne(targetEntity=FinanceHeadBean.class)
	@JoinColumn(name="major_head",referencedColumnName="head_id",insertable=false,updatable=false)
	private FinanceHeadBean  financeHeadBeanMajorHead;
	
	@OneToOne(targetEntity=FinanceHeadBean.class)
	@JoinColumn(name="sub_major_head",referencedColumnName="head_id",insertable=false,updatable=false)
	private FinanceHeadBean  financeHeadBeanSubMajorHead;
	
	@OneToOne(targetEntity=FinanceHeadBean.class)
	@JoinColumn(name="minor_head",referencedColumnName="head_id",insertable=false,updatable=false)
	private FinanceHeadBean  financeHeadBeanMinorHead;
	
	@OneToOne(targetEntity=FinanceHeadBean.class)
	@JoinColumn(name="sub_head",referencedColumnName="head_id",insertable=false,updatable=false)
	private FinanceHeadBean  financeHeadBeanSubHead;
	
	@OneToOne(targetEntity=FinanceHeadBean.class)
	@JoinColumn(name="detailed_head",referencedColumnName="head_id",insertable=false,updatable=false)
	private FinanceHeadBean  financeHeadBeanDetailHead;
	
	@OneToOne(targetEntity=FinanceHeadBean.class)
	@JoinColumn(name="soe_number",referencedColumnName="head_id",insertable=false,updatable=false)
	private FinanceHeadBean  financeHeadBeanSoeHead;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="plan_nonplan",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboDetailsPlanNonPlan;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="voted_charged",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboDetailsVotedCharged;
	
	
	@Column(name = "head_structure_name")
	private String headStructureName;
	
	@Column(name = "financial_year")
	private Integer financialYear;
	
	@Column(name = "demand_number")
	private Integer demandNumber;
	
	@Column(name = "major_head")
	private Integer majorHead;
	
	@Column(name = "sub_major_head")
	private Integer subMajorHead;
	
	@Column(name = "minor_head")
	private Integer minorHead;
	
	@Column(name = "sub_head")
	private Integer subHead;
	
	@Column(name = "detailed_head")
	private Integer detailedHead;
	
	@Column(name = "soe_number")
	private Integer soeNumber;
	
	@Column(name = "plan_nonplan")
	private Integer planNonplan;
	
	@Column(name = "voted_charged")
	private Integer votedCharged;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;
	
	

	public ComboBeanDetails getComboDetailsFinancialYear() {
		return comboDetailsFinancialYear;
	}

	public void setComboDetailsFinancialYear(ComboBeanDetails comboDetailsFinancialYear) {
		this.comboDetailsFinancialYear = comboDetailsFinancialYear;
	}

	public FinanceHeadBean getFinanceHeadBeanDemand() {
		return financeHeadBeanDemand;
	}

	public void setFinanceHeadBeanDemand(FinanceHeadBean financeHeadBeanDemand) {
		this.financeHeadBeanDemand = financeHeadBeanDemand;
	}

	public FinanceHeadBean getFinanceHeadBeanMajorHead() {
		return financeHeadBeanMajorHead;
	}

	public void setFinanceHeadBeanMajorHead(FinanceHeadBean financeHeadBeanMajorHead) {
		this.financeHeadBeanMajorHead = financeHeadBeanMajorHead;
	}

	public FinanceHeadBean getFinanceHeadBeanSubMajorHead() {
		return financeHeadBeanSubMajorHead;
	}

	public void setFinanceHeadBeanSubMajorHead(FinanceHeadBean financeHeadBeanSubMajorHead) {
		this.financeHeadBeanSubMajorHead = financeHeadBeanSubMajorHead;
	}

	public FinanceHeadBean getFinanceHeadBeanMinorHead() {
		return financeHeadBeanMinorHead;
	}

	public void setFinanceHeadBeanMinorHead(FinanceHeadBean financeHeadBeanMinorHead) {
		this.financeHeadBeanMinorHead = financeHeadBeanMinorHead;
	}

	public FinanceHeadBean getFinanceHeadBeanSubHead() {
		return financeHeadBeanSubHead;
	}

	public void setFinanceHeadBeanSubHead(FinanceHeadBean financeHeadBeanSubHead) {
		this.financeHeadBeanSubHead = financeHeadBeanSubHead;
	}

	public FinanceHeadBean getFinanceHeadBeanDetailHead() {
		return financeHeadBeanDetailHead;
	}

	public void setFinanceHeadBeanDetailHead(FinanceHeadBean financeHeadBeanDetailHead) {
		this.financeHeadBeanDetailHead = financeHeadBeanDetailHead;
	}

	public FinanceHeadBean getFinanceHeadBeanSoeHead() {
		return financeHeadBeanSoeHead;
	}

	public void setFinanceHeadBeanSoeHead(FinanceHeadBean financeHeadBeanSoeHead) {
		this.financeHeadBeanSoeHead = financeHeadBeanSoeHead;
	}

	public ComboBeanDetails getComboDetailsPlanNonPlan() {
		return comboDetailsPlanNonPlan;
	}

	public void setComboDetailsPlanNonPlan(ComboBeanDetails comboDetailsPlanNonPlan) {
		this.comboDetailsPlanNonPlan = comboDetailsPlanNonPlan;
	}

	public ComboBeanDetails getComboDetailsVotedCharged() {
		return comboDetailsVotedCharged;
	}

	public void setComboDetailsVotedCharged(ComboBeanDetails comboDetailsVotedCharged) {
		this.comboDetailsVotedCharged = comboDetailsVotedCharged;
	}

	public Long getHeadStructureId() {
		return headStructureId;
	}

	public void setHeadStructureId(Long headStructureId) {
		this.headStructureId = headStructureId;
	}

	public String getHeadStructureName() {
		return headStructureName;
	}

	public void setHeadStructureName(String headStructureName) {
		this.headStructureName = headStructureName;
	}

	public Integer getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(Integer financialYear) {
		this.financialYear = financialYear;
	}

	public Integer getDemandNumber() {
		return demandNumber;
	}

	public void setDemandNumber(Integer demandNumber) {
		this.demandNumber = demandNumber;
	}

	public Integer getMajorHead() {
		return majorHead;
	}

	public void setMajorHead(Integer majorHead) {
		this.majorHead = majorHead;
	}

	public Integer getSubMajorHead() {
		return subMajorHead;
	}

	public void setSubMajorHead(Integer subMajorHead) {
		this.subMajorHead = subMajorHead;
	}

	public Integer getMinorHead() {
		return minorHead;
	}

	public void setMinorHead(Integer minorHead) {
		this.minorHead = minorHead;
	}

	public Integer getSubHead() {
		return subHead;
	}

	public void setSubHead(Integer subHead) {
		this.subHead = subHead;
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

	public Integer getVotedCharged() {
		return votedCharged;
	}

	public void setVotedCharged(Integer votedCharged) {
		this.votedCharged = votedCharged;
	}

	public Long getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Long crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "FinanceHeadStructureBean [headStructureId=" + headStructureId + ", comboDetailsFinancialYear="
				+ comboDetailsFinancialYear + ", financeHeadBeanDemand=" + financeHeadBeanDemand
				+ ", financeHeadBeanMajorHead=" + financeHeadBeanMajorHead + ", financeHeadBeanSubMajorHead="
				+ financeHeadBeanSubMajorHead + ", financeHeadBeanMinorHead=" + financeHeadBeanMinorHead
				+ ", financeHeadBeanSubHead=" + financeHeadBeanSubHead + ", financeHeadBeanDetailHead="
				+ financeHeadBeanDetailHead + ", financeHeadBeanSoeHead=" + financeHeadBeanSoeHead
				+ ", comboDetailsPlanNonPlan=" + comboDetailsPlanNonPlan + ", comboDetailsVotedCharged="
				+ comboDetailsVotedCharged + ", headStructureName=" + headStructureName + ", financialYear="
				+ financialYear + ", demandNumber=" + demandNumber + ", majorHead=" + majorHead + ", subMajorHead="
				+ subMajorHead + ", minorHead=" + minorHead + ", subHead=" + subHead + ", detailedHead=" + detailedHead
				+ ", soeNumber=" + soeNumber + ", planNonplan=" + planNonplan + ", votedCharged=" + votedCharged
				+ ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}
	
}

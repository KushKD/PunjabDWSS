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
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="divisional_annual_budget",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DivisionBudgetBean implements Serializable{

	private static final long serialVersionUID = 363556815L;
	
	@Id
	@GeneratedValue(generator = "divisional_annual_budget_div_ann_budg_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "divisional_annual_budget_div_ann_budg_id_seq", sequenceName = "prwss_main.divisional_annual_budget_div_ann_budg_id_seq")
	@Column(name="div_ann_budg_id")
	private Long divAnnBudgId;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="fin_year",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailFinancialYear;
	
	@OneToOne(targetEntity=LocationDivisionSubDivisonDetailsBean.class)
	@JoinColumn(name="division_id",referencedColumnName="div_subdiv_detail_id",insertable=false,updatable=false)
	private LocationDivisionSubDivisonDetailsBean  locationDivisionSubDivisonDetailsBean;
	
	@OneToOne(targetEntity=ComponentDetailsBean.class)
	@JoinColumn(name="component_id",referencedColumnName="fin_comp_dtl_id",insertable=false,updatable=false)
	private ComponentDetailsBean  componentBean;
	
	@OneToOne(targetEntity=ComponentDetailsBean.class)
	@JoinColumn(name="sub_component_level1_id",referencedColumnName="fin_comp_dtl_id",insertable=false,updatable=false)
	private ComponentDetailsBean  subComponentBean;
	
	@OneToOne(targetEntity=ComponentDetailsBean.class)
	@JoinColumn(name="activity_id",referencedColumnName="fin_comp_dtl_id",insertable=false,updatable=false)
	private ComponentDetailsBean  activityComponentBean;
	
	@OneToOne(targetEntity=ComponentDetailsBean.class)
	@JoinColumn(name="sub_component_level2_id",referencedColumnName="fin_comp_dtl_id",insertable=false,updatable=false)
	private ComponentDetailsBean  subSubComponentBean;
	
	@Column(name = "fin_year")
	private Integer finYear;
	
	@Column(name = "division_id")
	private Integer divisionId;
	
	@Column(name = "scheme_id")
	private Long schemeId;
	
	@Column(name = "component_id")
	private Long componentId;
	
	@Column(name = "sub_component_level1_id")
	private Long subComponentId;
	
	@Column(name = "activity_id")
	private Long activityId;
	
	@Column(name = "sub_component_level2_id")
	private Long subSubComponentId;
	
	@Column(name = "sub_component_level3_id")
	private Long subComponentLevel3Id;
	
	@Column(name = "estimated_cost")
	private Long estimatedCost;
	
	@Column(name = "req_nxt_year")
	private Long reqNxtYear;
	
	@Column(name = "already_spent")
	private Long alreadySpent;
	
	@Column(name = "qtr_1")
	private Double qtr1;
	
	@Column(name = "qtr_2")
	private Double qtr2;
	
	@Column(name = "qtr_3")
	private Double qtr3;
	
	@Column(name = "qtr_4")
	private Double qtr4;
	
	@Column(name = "budget_ref_No")
	private String budgetRefNo;
	
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	@Column(name = "is_forward")
	private Integer isForward;
	
	@Column(name = "lying_with_usr")
	private String lyingWithUsr;
	
	@Column(name = "is_approve")
	private Integer isApprove;
	
	
	
	public Integer getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(Integer isApprove) {
		this.isApprove = isApprove;
	}

	public String getLyingWithUsr() {
		return lyingWithUsr;
	}

	public void setLyingWithUsr(String lyingWithUsr) {
		this.lyingWithUsr = lyingWithUsr;
	}

	public Long getSubComponentLevel3Id() {
		return subComponentLevel3Id;
	}

	public void setSubComponentLevel3Id(Long subComponentLevel3Id) {
		this.subComponentLevel3Id = subComponentLevel3Id;
	}

	public Integer getIsForward() {
		return isForward;
	}

	public void setIsForward(Integer isForward) {
		this.isForward = isForward;
	}

	public ComponentDetailsBean getSubComponentBean() {
		return subComponentBean;
	}

	public void setSubComponentBean(ComponentDetailsBean subComponentBean) {
		this.subComponentBean = subComponentBean;
	}

	public ComponentDetailsBean getActivityComponentBean() {
		return activityComponentBean;
	}

	public void setActivityComponentBean(ComponentDetailsBean activityComponentBean) {
		this.activityComponentBean = activityComponentBean;
	}

	public ComponentDetailsBean getSubSubComponentBean() {
		return subSubComponentBean;
	}

	public void setSubSubComponentBean(ComponentDetailsBean subSubComponentBean) {
		this.subSubComponentBean = subSubComponentBean;
	}

	public ComponentDetailsBean getComponentBean() {
		return componentBean;
	}

	public void setComponentBean(ComponentDetailsBean componentBean) {
		this.componentBean = componentBean;
	}

	public LocationDivisionSubDivisonDetailsBean getLocationDivisionSubDivisonDetailsBean() {
		return locationDivisionSubDivisonDetailsBean;
	}

	public void setLocationDivisionSubDivisonDetailsBean(
			LocationDivisionSubDivisonDetailsBean locationDivisionSubDivisonDetailsBean) {
		this.locationDivisionSubDivisonDetailsBean = locationDivisionSubDivisonDetailsBean;
	}

	public ComboBeanDetails getCombodetailFinancialYear() {
		return combodetailFinancialYear;
	}

	public void setCombodetailFinancialYear(ComboBeanDetails combodetailFinancialYear) {
		this.combodetailFinancialYear = combodetailFinancialYear;
	}

	public Long getDivAnnBudgId() {
		return divAnnBudgId;
	}

	public void setDivAnnBudgId(Long divAnnBudgId) {
		this.divAnnBudgId = divAnnBudgId;
	}

	public Integer getFinYear() {
		return finYear;
	}

	public void setFinYear(Integer finYear) {
		this.finYear = finYear;
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

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

	public Long getSubComponentId() {
		return subComponentId;
	}

	public void setSubComponentId(Long subComponentId) {
		this.subComponentId = subComponentId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}



	public Long getSubSubComponentId() {
		return subSubComponentId;
	}

	public void setSubSubComponentId(Long subSubComponentId) {
		this.subSubComponentId = subSubComponentId;
	}

	public Long getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Long estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Long getReqNxtYear() {
		return reqNxtYear;
	}

	public void setReqNxtYear(Long reqNxtYear) {
		this.reqNxtYear = reqNxtYear;
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

	public String getBudgetRefNo() {
		return budgetRefNo;
	}

	public void setBudgetRefNo(String budgetRefNo) {
		this.budgetRefNo = budgetRefNo;
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
		return "DivisionBudgetBean [divAnnBudgId=" + divAnnBudgId + ", finYear=" + finYear + ", divisionId="
				+ divisionId + ", schemeId=" + schemeId + ", componentId=" + componentId + ", subComponentId="
				+ subComponentId + ", activityId=" + activityId + ", subSubComponentId=" + subSubComponentId
				+ ", estimatedCost=" + estimatedCost + ", reqNxtYear=" + reqNxtYear + ", alreadySpent=" + alreadySpent
				+ ", qtr1=" + qtr1 + ", qtr2=" + qtr2 + ", qtr3=" + qtr3 + ", qtr4=" + qtr4 + ", budgetRefNo="
				+ budgetRefNo + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}
	
}

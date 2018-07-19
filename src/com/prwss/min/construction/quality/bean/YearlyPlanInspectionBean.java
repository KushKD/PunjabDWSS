/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name="cc_yearly_plan",schema=MISConstants.MIS_DB_SCHEMA_NAME)

public class YearlyPlanInspectionBean implements Serializable {

	private static final long serialVersionUID = 36350765815L;
 
	@Id
	@GeneratedValue(generator="cc_yearly_plan_yearly_plan_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="cc_yearly_plan_yearly_plan_id_seq",sequenceName="prwss_main.cc_yearly_plan_yearly_plan_id_seq")
	@Column(name="yearly_plan_id")
	private int yearlyPlanId;
	
	@OneToMany(targetEntity=YearlyPlanningComponentMappingBean.class,fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="yearly_plan_id", updatable = false, insertable = false) 
	private Set<YearlyPlanningComponentMappingBean> yearlyPlanningComponentMappingBeans;
	
	@OneToOne(targetEntity=ExternalAgencyMasterBean.class)
	@JoinColumn(name="assigned_to",referencedColumnName="external_agency_id",insertable=false,updatable=false)
	private ExternalAgencyMasterBean  externalAgencyMasterBean;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="financial_year",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboDetailsYear;
	
	@Column(name="yearly_plan_name")
	private String yearlyPlanName;
	
	@Column(name="date_finalization")
	private Date dateFinalization;
	
	@Column(name="inspection_type")
	private Integer inspectionType;
	
	@Column(name="financial_year")
	private Integer financialYear;
	
	@Column(name="assigned_to")
	private Integer assignedTo;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="crt_by_user")
	private Integer crtByUsr;

	
	
	public ComboBeanDetails getComboDetailsYear() {
		return comboDetailsYear;
	}

	public void setComboDetailsYear(ComboBeanDetails comboDetailsYear) {
		this.comboDetailsYear = comboDetailsYear;
	}

	public ExternalAgencyMasterBean getExternalAgencyMasterBean() {
		return externalAgencyMasterBean;
	}

	public void setExternalAgencyMasterBean(ExternalAgencyMasterBean externalAgencyMasterBean) {
		this.externalAgencyMasterBean = externalAgencyMasterBean;
	}

	public Set<YearlyPlanningComponentMappingBean> getYearlyPlanningComponentMappingBeans() {
		return yearlyPlanningComponentMappingBeans;
	}

	public void setYearlyPlanningComponentMappingBeans(
			Set<YearlyPlanningComponentMappingBean> yearlyPlanningComponentMappingBeans) {
		this.yearlyPlanningComponentMappingBeans = yearlyPlanningComponentMappingBeans;
	}

	public int getYearlyPlanId() {
		return yearlyPlanId;
	}

	public void setYearlyPlanId(int yearlyPlanId) {
		this.yearlyPlanId = yearlyPlanId;
	}

	public String getYearlyPlanName() {
		return yearlyPlanName;
	}

	public void setYearlyPlanName(String yearlyPlanName) {
		this.yearlyPlanName = yearlyPlanName;
	}

	public Date getDateFinalization() {
		return dateFinalization;
	}

	public void setDateFinalization(Date dateFinalization) {
		this.dateFinalization = dateFinalization;
	}

	public Integer getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(Integer inspectionType) {
		this.inspectionType = inspectionType;
	}

	public Integer getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(Integer financialYear) {
		this.financialYear = financialYear;
	}

	public Integer getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Integer assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Integer crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	@Override
	public String toString() {
		return "YearlyPlanInspectionBean [yearlyPlanId=" + yearlyPlanId + ", yearlyPlanningComponentMappingBeans="
				+ yearlyPlanningComponentMappingBeans + ", externalAgencyMasterBean=" + externalAgencyMasterBean
				+ ", yearlyPlanName=" + yearlyPlanName + ", dateFinalization=" + dateFinalization + ", inspectionType="
				+ inspectionType + ", financialYear=" + financialYear + ", assignedTo=" + assignedTo + ", activeFlag="
				+ activeFlag + ", crtByUsr=" + crtByUsr + "]";
	}
	
}

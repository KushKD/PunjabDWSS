/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.prwss.min.sanitation.bean.ComboBeanDetails;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="cc_monthly_progress",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class MonthlyProgressBean implements Serializable{

	private static final long serialVersionUID=-456534542L;
	
	@Id
	@GeneratedValue(generator = "cc_monthly_progress_monthly_progress_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cc_monthly_progress_monthly_progress_id_seq", sequenceName = "prwss_main.cc_monthly_progress_monthly_progress_id_seq")
	@Column(name = "monthly_progress_id")
	private long monthlyProgressId;
	
	@OneToOne(targetEntity=TeamMasterBean.class)
	@JoinColumn(name="team_id",referencedColumnName="team_id",insertable=false,updatable=false)
	private TeamMasterBean  teamMasterBean;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="component",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailComponent;
	
	@OneToOne(targetEntity=MonthlyPlanInspectionBean.class)
	@JoinColumn(name="monthly_plan_id",referencedColumnName="monthly_plan_id",insertable=false,updatable=false)
	private MonthlyPlanInspectionBean  monthlyPlanInspectionBean;
	
	@Column(name="report")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] report;
	
	
	@Column(name = "report_name")
	private String reportName;
	
	
/*	@Column(name = "yearly_plan_id")
	private Integer yearlyPlanId;*/
	
	@Column(name = "monthly_plan_id")
	private Integer monthlyPlanId;
	
	@Column(name = "component")
	private Integer component;
	
	@Column(name = "team_id")
	private Integer teamId;
	
	@Column(name = "no_of_village_to_be_visited")
	private Long villageToBeVisited;
	
	@Column(name = "phase_planning")
	private Long planning;
	
	@Column(name = "phase_implementaion")
	private Long implementaion;
	
	@Column(name = "phase_postimplementaion")
	private Long postimplementaion;
	
	@Column(name = "crt_by_user")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;
	
	
	
	
	public byte[] getReport() {
		return report;
	}

	public void setReport(byte[] report) {
		this.report = report;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public MonthlyPlanInspectionBean getMonthlyPlanInspectionBean() {
		return monthlyPlanInspectionBean;
	}

	public void setMonthlyPlanInspectionBean(MonthlyPlanInspectionBean monthlyPlanInspectionBean) {
		this.monthlyPlanInspectionBean = monthlyPlanInspectionBean;
	}

	public TeamMasterBean getTeamMasterBean() {
		return teamMasterBean;
	}

	public void setTeamMasterBean(TeamMasterBean teamMasterBean) {
		this.teamMasterBean = teamMasterBean;
	}

	public ComboBeanDetails getCombodetailComponent() {
		return combodetailComponent;
	}

	public void setCombodetailComponent(ComboBeanDetails combodetailComponent) {
		this.combodetailComponent = combodetailComponent;
	}

	public Long getVillageToBeVisited() {
		return villageToBeVisited;
	}

	public void setVillageToBeVisited(Long villageToBeVisited) {
		this.villageToBeVisited = villageToBeVisited;
	}

	/*public Integer getYearlyPlanId() {
		return yearlyPlanId;
	}

	public void setYearlyPlanId(Integer yearlyPlanId) {
		this.yearlyPlanId = yearlyPlanId;
	}*/

	public long getMonthlyProgressId() {
		return monthlyProgressId;
	}

	public void setMonthlyProgressId(long monthlyProgressId) {
		this.monthlyProgressId = monthlyProgressId;
	}

	public Integer getMonthlyPlanId() {
		return monthlyPlanId;
	}

	public void setMonthlyPlanId(Integer monthlyPlanId) {
		this.monthlyPlanId = monthlyPlanId;
	}

	public Integer getComponent() {
		return component;
	}

	public void setComponent(Integer component) {
		this.component = component;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Long getPlanning() {
		return planning;
	}

	public void setPlanning(Long planning) {
		this.planning = planning;
	}

	public Long getImplementaion() {
		return implementaion;
	}

	public void setImplementaion(Long implementaion) {
		this.implementaion = implementaion;
	}

	public Long getPostimplementaion() {
		return postimplementaion;
	}

	public void setPostimplementaion(Long postimplementaion) {
		this.postimplementaion = postimplementaion;
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
		return "MonthlyProgressBean [monthlyProgressId=" + monthlyProgressId + ", teamMasterBean=" + teamMasterBean
				+ ", combodetailComponent=" + combodetailComponent + ", monthlyPlanId=" + monthlyPlanId + ", component="
				+ component + ", teamId=" + teamId + ", villageToBeVisited=" + villageToBeVisited + ", planning="
				+ planning + ", implementaion=" + implementaion + ", postimplementaion=" + postimplementaion
				+ ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}

//	@Override
//	public String toString() {
//		return "MonthlyProgressBean [monthlyProgressId=" + monthlyProgressId + ", yearlyPlanId=" + yearlyPlanId
//				+ ", monthlyPlanId=" + monthlyPlanId + ", component=" + component + ", teamId=" + teamId
//				+ ", villageToBeVisited=" + villageToBeVisited + ", planning=" + planning + ", implementaion="
//				+ implementaion + ", postimplementaion=" + postimplementaion + ", crtByUsr=" + crtByUsr
//				+ ", activeFlag=" + activeFlag + "]";
//	}

	

	
}

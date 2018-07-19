/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="cc_monthly_progress_movement",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class MonthlyProgressMovementBean implements Serializable{
	
	private static final long serialVersionUID=-456532234542L;
	
	@Id
	@GeneratedValue(generator = "cc_monthly_progress_movement_progress_mvmnt_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cc_monthly_progress_movement_progress_mvmnt_id_seq", sequenceName = "prwss_main.cc_monthly_progress_movement_progress_mvmnt_id_seq")
	@Column(name = "progress_mvmnt_id")
	private long progressMvmntId;
	
	@OneToMany(targetEntity=ProgressCommentMappingBean.class)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="monthly_plan_id", updatable = false, insertable = false)
	Set<ProgressCommentMappingBean> progressCommentMappingBeans;
	
	@OneToOne(targetEntity=MonthlyPlanInspectionBean.class)
	@JoinColumn(name="monthly_plan_id",referencedColumnName="monthly_plan_id",insertable=false,updatable=false)
	private MonthlyPlanInspectionBean  monthlyPlanInspectionBean;
	
	@Column(name = "monthly_plan_id")
	private Integer monthlyPlanId;
	
	@Column(name = "approval_status")
	private Integer approvalStatus;
	
	@Column(name = "approved_by")
	private Integer approvedBy;
	
	@Column(name = "lying_with_user")
	private Integer lyingWithUser;
	
	@Column(name = "crt_by_user")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	
	public MonthlyPlanInspectionBean getMonthlyPlanInspectionBean() {
		return monthlyPlanInspectionBean;
	}

	public void setMonthlyPlanInspectionBean(MonthlyPlanInspectionBean monthlyPlanInspectionBean) {
		this.monthlyPlanInspectionBean = monthlyPlanInspectionBean;
	}

	public Set<ProgressCommentMappingBean> getProgressCommentMappingBeans() {
		return progressCommentMappingBeans;
	}

	public void setProgressCommentMappingBeans(Set<ProgressCommentMappingBean> progressCommentMappingBeans) {
		this.progressCommentMappingBeans = progressCommentMappingBeans;
	}

	public long getProgressMvmntId() {
		return progressMvmntId;
	}

	public void setProgressMvmntId(long progressMvmntId) {
		this.progressMvmntId = progressMvmntId;
	}

	public Integer getMonthlyPlanId() {
		return monthlyPlanId;
	}

	public void setMonthlyPlanId(Integer monthlyPlanId) {
		this.monthlyPlanId = monthlyPlanId;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Integer getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Integer getLyingWithUser() {
		return lyingWithUser;
	}

	public void setLyingWithUser(Integer lyingWithUser) {
		this.lyingWithUser = lyingWithUser;
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
		return "MonthlyProgressMovementBean [progressMvmntId=" + progressMvmntId + ", monthlyPlanId=" + monthlyPlanId
				+ ", approvalStatus=" + approvalStatus + ", approvedBy=" + approvedBy + ", lyingWithUser="
				+ lyingWithUser + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}
}

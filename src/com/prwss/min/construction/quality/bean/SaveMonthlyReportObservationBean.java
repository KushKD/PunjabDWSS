/**
 * 
 */
package com.prwss.min.construction.quality.bean;

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

import com.prwss.mis.common.util.MISConstants;
/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="cc_monthly_plan_observation_mapping",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SaveMonthlyReportObservationBean implements Serializable {
	
	private static final long serialVersionUID=-3454534534L;
	
	@Id
	@GeneratedValue(generator = "cc_monthly_plan_observation_mapping_plan_observation_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name="cc_monthly_plan_observation_mapping_plan_observation_id_seq", sequenceName = "prwss_main.cc_monthly_plan_observation_mapping_plan_observation_id_seq")
	@Column(name = "plan_observation_id")
	private long planObservationId;
	
	@OneToOne(targetEntity=MonthlyPlanInspectionBean.class)
	@JoinColumn(name="monthly_plan_id",referencedColumnName="monthly_plan_id",insertable=false,updatable=false)
	private MonthlyPlanInspectionBean  monthlyPlanInspectionBean;
	
	@Column(name = "monthly_plan_id")
	private Long monthlyPlanId;
	
	@Column(name = "comment_type")
	private Integer commentType;
	
	@Column(name = "scheme")
	private Integer scheme;
	
	@Column(name = "observation")
	private String observation;
	
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

	public long getPlanObservationId() {
		return planObservationId;
	}

	public void setPlanObservationId(long planObservationId) {
		this.planObservationId = planObservationId;
	}

	public Long getMonthlyPlanId() {
		return monthlyPlanId;
	}

	public void setMonthlyPlanId(Long monthlyPlanId) {
		this.monthlyPlanId = monthlyPlanId;
	}

	public Integer getCommentType() {
		return commentType;
	}

	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

	public Integer getScheme() {
		return scheme;
	}

	public void setScheme(Integer scheme) {
		this.scheme = scheme;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
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
		return "SaveMonthlyReportObservationBean [planObservationId=" + planObservationId + ", monthlyPlanId="
				+ monthlyPlanId + ", commentType=" + commentType + ", scheme=" + scheme + ", observation=" + observation
				+ ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}

}

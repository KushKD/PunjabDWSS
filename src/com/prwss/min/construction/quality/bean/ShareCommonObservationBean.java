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
@Table(name="cc_mst_sharing_obs",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ShareCommonObservationBean implements Serializable{

	private static final long serialVersionUID=-4565345334332L;
	
	@Id
	@GeneratedValue(generator = "cc_mst_sharing_obs_obs_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cc_mst_sharing_obs_obs_id_seq", sequenceName = "prwss_main.cc_mst_sharing_obs_obs_id_seq")
	@Column(name = "obs_id")
	private long ObsId;
	
	
	@Column(name = "monthly_plan_id")
	private Integer monthlyPlanId;

	@Column(name = "subject")
	private String subject;
	
	@Column(name = "to_emp")
	private Integer toEmp;
	
	
	@Column(name = "crt_by_user")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	public long getObsId() {
		return ObsId;
	}

	public void setObsId(long obsId) {
		ObsId = obsId;
	}

	public Integer getMonthlyPlanId() {
		return monthlyPlanId;
	}

	public void setMonthlyPlanId(Integer monthlyPlanId) {
		this.monthlyPlanId = monthlyPlanId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getToEmp() {
		return toEmp;
	}

	public void setToEmp(Integer toEmp) {
		this.toEmp = toEmp;
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
		return "ShareCommonObservationBean [ObsId=" + ObsId + ", monthlyPlanId=" + monthlyPlanId + ", subject="
				+ subject + ", toEmp=" + toEmp + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}

}

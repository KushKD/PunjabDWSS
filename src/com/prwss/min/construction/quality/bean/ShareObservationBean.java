/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name="cc_sharing_obs",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ShareObservationBean implements Serializable{

	private static final long serialVersionUID=-45653454332L;
	
	@Id
	@GeneratedValue(generator = "cc_sharing_obs_share_obs_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cc_sharing_obs_share_obs_id_seq", sequenceName = "prwss_main.cc_sharing_obs_share_obs_id_seq")
	@Column(name = "share_obs_id")
	private long shareObsId;
	
/*	
	@Column(name = "monthly_plan_id")
	private Integer monthlyPlanId;

	@Column(name = "subject")
	private String subject;
	
	@Column(name = "to_emp")
	private Integer toEmp;*/
	
	
	@Column(name = "obs_id")
	private Long obsId;
	
	
	@Column(name = "scheme_id")
	private Integer schemeId;
	
	@Column(name = "scheme_stage")
	private Integer schemeStage;
	
	@Column(name = "checked_for")
	private Integer checkedFor;
	
	@Column(name = "inspection_date")
	private Date inspectionDate;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "crt_by_user")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	
	
	
	public Long getObsId() {
		return obsId;
	}

	public void setObsId(Long obsId) {
		this.obsId = obsId;
	}

	public long getShareObsId() {
		return shareObsId;
	}

	public void setShareObsId(long shareObsId) {
		this.shareObsId = shareObsId;
	}

	
	public Integer getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	public Integer getSchemeStage() {
		return schemeStage;
	}

	public void setSchemeStage(Integer schemeStage) {
		this.schemeStage = schemeStage;
	}

	public Integer getCheckedFor() {
		return checkedFor;
	}

	public void setCheckedFor(Integer checkedFor) {
		this.checkedFor = checkedFor;
	}

	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "ShareObservationBean [shareObsId=" + shareObsId + ", schemeId=" + schemeId + ", schemeStage="
				+ schemeStage + ", checkedFor=" + checkedFor + ", inspectionDate=" + inspectionDate + ", remarks="
				+ remarks + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}
	
}

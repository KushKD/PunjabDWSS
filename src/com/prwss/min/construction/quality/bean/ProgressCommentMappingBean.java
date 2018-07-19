/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Table(name="cc_progress_cmnt_mapping",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ProgressCommentMappingBean implements Serializable {

	private static final long serialVersionUID=-456533334542L; 
	
	@Id
	@GeneratedValue(generator = "cc_progress_cmnt_mapping_progress_cmnt_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cc_progress_cmnt_mapping_progress_cmnt_id_seq", sequenceName = "prwss_main.cc_progress_cmnt_mapping_progress_cmnt_id_seq")
	@Column(name = "progress_cmnt_id")
	private long progressCmntId;
	
	@Column(name = "progress_mvmnt_id")
	private Long progressMvmntId;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "provided_by")
	private Integer providedBy;
	
	@Column(name = "crt_by_user")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	public long getProgressCmntId() {
		return progressCmntId;
	}

	public void setProgressCmntId(long progressCmntId) {
		this.progressCmntId = progressCmntId;
	}

	public Long getProgressMvmntId() {
		return progressMvmntId;
	}

	public void setProgressMvmntId(Long progressMvmntId) {
		this.progressMvmntId = progressMvmntId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getProvidedBy() {
		return providedBy;
	}

	public void setProvidedBy(Integer providedBy) {
		this.providedBy = providedBy;
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
		return "ProgressCommentMappingBean [progressCmntId=" + progressCmntId + ", progressMvmntId=" + progressMvmntId
				+ ", comment=" + comment + ", providedBy=" + providedBy + ", crtByUsr=" + crtByUsr + ", activeFlag="
				+ activeFlag + "]";
	}
	
}

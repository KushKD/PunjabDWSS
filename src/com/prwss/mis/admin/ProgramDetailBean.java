/**
 * 
 */
package com.prwss.mis.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Table(name="program_dtl", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ProgramDetailBean  implements Serializable{

	
	private static final long serialVersionUID = -398019516478117L;
	@Id	
	@Column(name="prog_dtl_id")
	private String progDtlId;
	
	@Column(name="prog_id", nullable=false)
	private String programId;
	
	@Column(name="prog_name", nullable=false)
	private String progName;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	public String getProgDtlId() {
		return progDtlId;
	}

	public void setProgDtlId(String progDtlId) {
		this.progDtlId = progDtlId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
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
		return "ProgramDetailBean [progDtlId=" + progDtlId + ", programId=" + programId + ", progName=" + progName
				+ ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}
	
	
}

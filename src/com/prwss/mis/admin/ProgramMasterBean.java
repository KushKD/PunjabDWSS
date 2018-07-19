/**
 * 
 */
package com.prwss.mis.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Table(name="program_mst", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ProgramMasterBean implements Serializable{

	private static final long serialVersionUID = -39801957616478117L;
	
	@Id	
	@Column(name="prog_mst_id")
	private String programMstId;
	
	@OneToOne(targetEntity=ProgramDetailBean.class)
	@JoinColumn(name="prog_id",referencedColumnName="prog_id",insertable=false,updatable=false)
	private ProgramDetailBean  programDetailBean;

	@Column(name="prog_id")
	private String programId;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;
	
	
	
	
	
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

	public ProgramDetailBean getProgramDetailBean() {
		return programDetailBean;
	}

	public void setProgramDetailBean(ProgramDetailBean programDetailBean) {
		this.programDetailBean = programDetailBean;
	}

	public String getProgramMstId() {
		return programMstId;
	}

	public void setProgramMstId(String programMstId) {
		this.programMstId = programMstId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	@Override
	public String toString() {
		return "ProgramMasterBean [programMstId=" + programMstId + ", programId=" + programId + "]";
	}
	
}

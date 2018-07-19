/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author bhsingh
 *
 */

@Entity
@Table(name="wq_lab_emp_mpg", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LabEmployee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -363503890815L;
	
	@Id
	@Column(name="lab_emp_id")
	private Integer labEmpId;
	
	
	@Column(name="lab_id")
	private Integer labId;
	
	@Column(name="emp_id")
	private Integer empId;
	
	@Column(name="crt_by_user")
	private Integer crtByUsr;
	
	@Column(name="active_flag")
	private Integer activeFlag ;

	public Integer getLabEmpId() {
		return labEmpId;
	}

	public void setLabEmpId(Integer labEmpId) {
		this.labEmpId = labEmpId;
	}

	public Integer getLabId() {
		return labId;
	}

	public void setLabId(Integer labId) {
		this.labId = labId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Integer crtByUsr) {
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
		return "LabEmployee [labEmpId=" + labEmpId + ", labId=" + labId + ", empId=" + empId + ", crtByUsr=" + crtByUsr
				+ ", activeFlag=" + activeFlag + "]";
	}

}

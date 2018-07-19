/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bhsingh
 *
 */

@Entity
@Table(name="workflow_master",schema="prwss_main")
public class WorkFlowMasterBean implements Serializable {

	private static final long serialVersionUID = -13453412744L;
	
	
	@Id
	@Column(name = "workflow_id")
	private int workflow_id;
	
	
	@Column(name = "level")
	private Integer level;
	
	@Column(name = "from_emp_id")
	private Integer from_emp_id;
	
	@Column(name = "to_emp_id_fwd")
	private Integer to_emp_id_fwd;
	
	@Column(name = "to_emp_id_ret")
	private Integer to_emp_id_ret;
	
	@Column(name = "to_emp_id_rti")
	private Integer to_emp_id_rti;
	
	@Column(name = "subject_id")
	private Integer subject_id;
	
	@Column(name = "active_flag")
	private Integer active_flag;

	/**
	 * @return the workflow_id
	 */
	public int getWorkflow_id() {
		return workflow_id;
	}

	/**
	 * @param workflow_id the workflow_id to set
	 */
	public void setWorkflow_id(int workflow_id) {
		this.workflow_id = workflow_id;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the from_emp_id
	 */
	public Integer getFrom_emp_id() {
		return from_emp_id;
	}

	/**
	 * @param from_emp_id the from_emp_id to set
	 */
	public void setFrom_emp_id(Integer from_emp_id) {
		this.from_emp_id = from_emp_id;
	}

	/**
	 * @return the to_emp_id_fwd
	 */
	public Integer getTo_emp_id_fwd() {
		return to_emp_id_fwd;
	}

	/**
	 * @param to_emp_id_fwd the to_emp_id_fwd to set
	 */
	public void setTo_emp_id_fwd(Integer to_emp_id_fwd) {
		this.to_emp_id_fwd = to_emp_id_fwd;
	}

	/**
	 * @return the to_emp_id_ret
	 */
	public Integer getTo_emp_id_ret() {
		return to_emp_id_ret;
	}

	/**
	 * @param to_emp_id_ret the to_emp_id_ret to set
	 */
	public void setTo_emp_id_ret(Integer to_emp_id_ret) {
		this.to_emp_id_ret = to_emp_id_ret;
	}

	/**
	 * @return the to_emp_id_rti
	 */
	public Integer getTo_emp_id_rti() {
		return to_emp_id_rti;
	}

	/**
	 * @param to_emp_id_rti the to_emp_id_rti to set
	 */
	public void setTo_emp_id_rti(Integer to_emp_id_rti) {
		this.to_emp_id_rti = to_emp_id_rti;
	}

	/**
	 * @return the subject_id
	 */
	public Integer getSubject_id() {
		return subject_id;
	}

	/**
	 * @param subject_id the subject_id to set
	 */
	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}

	/**
	 * @return the active_flag
	 */
	public Integer getActive_flag() {
		return active_flag;
	}

	/**
	 * @param active_flag the active_flag to set
	 */
	public void setActive_flag(Integer active_flag) {
		this.active_flag = active_flag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WorkFlowMasterBean [workflow_id=" + workflow_id + ", level=" + level + ", from_emp_id=" + from_emp_id
				+ ", to_emp_id_fwd=" + to_emp_id_fwd + ", to_emp_id_ret=" + to_emp_id_ret + ", to_emp_id_rti="
				+ to_emp_id_rti + ", subject_id=" + subject_id + ", active_flag=" + active_flag + "]";
	}

	
}

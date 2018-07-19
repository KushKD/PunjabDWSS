/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="wq_lab_tt_param_mapping",schema="prwss_main")
public class LabToParameterMapping implements Serializable{

	private static final long serialVersionUID = -134534264845544L;
	
	@Id
	@Column(name="lab_tt_param_id")
	private Integer labToParameterId;
	
	@Column(name="lab_id")
	private Integer lab_id;
	
	@Column(name="parameter_id")
	private Integer parameter_id;
	
	@Column(name="active_flag")
	private Integer active_flag;
	
	@Column(name="test_type")
	private String test_type;

	public Integer getLabToParameterId() {
		return labToParameterId;
	}

	public void setLabToParameterId(Integer labToParameterId) {
		this.labToParameterId = labToParameterId;
	}

	public Integer getLab_id() {
		return lab_id;
	}

	public void setLab_id(Integer lab_id) {
		this.lab_id = lab_id;
	}

	public Integer getParameter_id() {
		return parameter_id;
	}

	public void setParameter_id(Integer parameter_id) {
		this.parameter_id = parameter_id;
	}

	public Integer getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(Integer active_flag) {
		this.active_flag = active_flag;
	}

	public String getTest_type() {
		return test_type;
	}

	public void setTest_type(String test_type) {
		this.test_type = test_type;
	}

	@Override
	public String toString() {
		return "LabToParameterMapping [labToParameterId=" + labToParameterId + ", lab_id=" + lab_id + ", parameter_id="
				+ parameter_id + ", active_flag=" + active_flag + ", test_type=" + test_type + "]";
	}

	
}

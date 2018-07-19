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
@Table(name="cc_carbon_copy",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CCBean implements Serializable{

	private static final long serialVersionUID=-456533454332L;
	
	@Id
	@GeneratedValue(generator = "cc_carbon_copy_cc_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cc_carbon_copy_cc_id_seq", sequenceName = "prwss_main.cc_carbon_copy_cc_id_seq")
	@Column(name = "cc_id")
	private long ccId;
	
	@Column(name = "obs_id")
	private Long obsId;
	
	
	@Column(name = "emp_name")
	private Long empName;
	
	@Column(name = "emp_designation")
	private Integer empDesignation;
	
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

	public long getCcId() {
		return ccId;
	}

	public void setCcId(long ccId) {
		this.ccId = ccId;
	}

	

	public Long getEmpName() {
		return empName;
	}

	public void setEmpName(Long empName) {
		this.empName = empName;
	}

	public Integer getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(Integer empDesignation) {
		this.empDesignation = empDesignation;
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
		return "CCBean [ccId=" + ccId + ", obsId=" + obsId + ", empName=" + empName + ", empDesignation="
				+ empDesignation + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}


	
}

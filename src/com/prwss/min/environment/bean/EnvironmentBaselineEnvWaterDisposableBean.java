package com.prwss.min.environment.bean;

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

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="env_eds_baseline_env_wd",schema=MISConstants.MIS_DB_SCHEMA_NAME)

public class EnvironmentBaselineEnvWaterDisposableBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6693396143876907351L;



	@Id
	@GeneratedValue(generator="env_eds_baseline_env_wd_eds_bsln_env_wd_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_baseline_env_wd_eds_bsln_env_wd_id_seq",sequenceName="prwss_main.env_eds_baseline_env_wd_eds_bsln_env_wd_id_seq")
	@Column(name="eds_bsln_env_wd_id")
	private Integer eds_bsln_env_wd_id;
	
	
	
	@Column(name="eds_bsln_env_id")
	private Integer eds_bsln_env_id;
	
	@Column(name="waste_dis_id")
	private Integer waste_dis_id;
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;

	public Integer getEds_bsln_env_wd_id() {
		return eds_bsln_env_wd_id;
	}

	public void setEds_bsln_env_wd_id(Integer eds_bsln_env_wd_id) {
		this.eds_bsln_env_wd_id = eds_bsln_env_wd_id;
	}

	public Integer getEds_bsln_env_id() {
		return eds_bsln_env_id;
	}

	public void setEds_bsln_env_id(Integer eds_bsln_env_id) {
		this.eds_bsln_env_id = eds_bsln_env_id;
	}

	public Integer getWaste_dis_id() {
		return waste_dis_id;
	}

	public void setWaste_dis_id(Integer waste_dis_id) {
		this.waste_dis_id = waste_dis_id;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	@Override
	public String toString() {
		return "EnvironmentBaselineEnvWaterDisposableBean [eds_bsln_env_wd_id="
				+ eds_bsln_env_wd_id + ", eds_bsln_env_id=" + eds_bsln_env_id
				+ ", waste_dis_id=" + waste_dis_id + ", crt_by_user="
				+ crt_by_user + "]";
	}
	
	
	
	
	
}

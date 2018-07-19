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
@Table(name="env_eds_baseline_env_pond",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EnvironmentBaselineEnvPondBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077020563601928115L;
	
	
	@Id
	@GeneratedValue(generator="env_eds_baseline_env_pond_eds_bsln_env_pond_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_baseline_env_pond_eds_bsln_env_pond_id_seq",sequenceName="prwss_main.env_eds_baseline_env_pond_eds_bsln_env_pond_id_seq")
	@Column(name="eds_bsln_env_pond_id")
	private Integer eds_bsln_env_pond_id;
	
	
	
	@Column(name="eds_bsln_env_id")
	private Integer eds_bsln_env_id;
	
	@Column(name="usage_id")
	private Integer usage_id;
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;

	public Integer getEds_bsln_env_pond_id() {
		return eds_bsln_env_pond_id;
	}

	public void setEds_bsln_env_pond_id(Integer eds_bsln_env_pond_id) {
		this.eds_bsln_env_pond_id = eds_bsln_env_pond_id;
	}

	public Integer getEds_bsln_env_id() {
		return eds_bsln_env_id;
	}

	public void setEds_bsln_env_id(Integer eds_bsln_env_id) {
		this.eds_bsln_env_id = eds_bsln_env_id;
	}

	public Integer getUsage_id() {
		return usage_id;
	}

	public void setUsage_id(Integer usage_id) {
		this.usage_id = usage_id;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	@Override
	public String toString() {
		return "EnvironmentBaselineEnvPondBean [eds_bsln_env_pond_id="
				+ eds_bsln_env_pond_id + ", eds_bsln_env_id=" + eds_bsln_env_id
				+ ", usage_id=" + usage_id + ", crt_by_user=" + crt_by_user
				+ "]";
	}
	
	
	
	
	
	

}

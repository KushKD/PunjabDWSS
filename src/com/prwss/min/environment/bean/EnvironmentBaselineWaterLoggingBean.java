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
@Table(name="env_eds_water_logged",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EnvironmentBaselineWaterLoggingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1530075214791930138L;
	
	

	@Id
	@GeneratedValue(generator="env_eds_water_logged_wl_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_water_logged_wl_id_seq",sequenceName="prwss_main.env_eds_water_logged_wl_id_seq")
	@Column(name="wl_id")
	private Integer wl_id;
	
	
	
	@Column(name="eds_bsln_env_id")
	private Integer eds_bsln_env_id;
	
	@Column(name="wl_area_name")
	private String wl_area_name;
	
	@Column(name="area_wl")
	private String area_wl;
	
	@Column(name="period_wl")
	private String period_wl;
	
	@Column(name="population_effctd")
	private Integer population_effctd;
	
	@Column(name="contamination_wl")
	private String contamination_wl;
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;

	public Integer getWl_id() {
		return wl_id;
	}

	public void setWl_id(Integer wl_id) {
		this.wl_id = wl_id;
	}

	public Integer getEds_bsln_env_id() {
		return eds_bsln_env_id;
	}

	public void setEds_bsln_env_id(Integer eds_bsln_env_id) {
		this.eds_bsln_env_id = eds_bsln_env_id;
	}

	public String getWl_area_name() {
		return wl_area_name;
	}

	public void setWl_area_name(String wl_area_name) {
		this.wl_area_name = wl_area_name;
	}

	public String getArea_wl() {
		return area_wl;
	}

	public void setArea_wl(String area_wl) {
		this.area_wl = area_wl;
	}

	public String getPeriod_wl() {
		return period_wl;
	}

	public void setPeriod_wl(String period_wl) {
		this.period_wl = period_wl;
	}

	public Integer getPopulation_effctd() {
		return population_effctd;
	}

	public void setPopulation_effctd(Integer population_effctd) {
		this.population_effctd = population_effctd;
	}

	public String getContamination_wl() {
		return contamination_wl;
	}

	public void setContamination_wl(String contamination_wl) {
		this.contamination_wl = contamination_wl;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	@Override
	public String toString() {
		return "EnvironmentBaselineWaterLoggingBean [wl_id=" + wl_id
				+ ", eds_bsln_env_id=" + eds_bsln_env_id + ", wl_area_name="
				+ wl_area_name + ", area_wl=" + area_wl + ", period_wl="
				+ period_wl + ", population_effctd=" + population_effctd
				+ ", contamination_wl=" + contamination_wl + ", crt_by_user="
				+ crt_by_user + "]";
	}
	
	
	
	
	

}

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
@Table(name="env_eds_water_supply_ei",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class WaterSupplyGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -501106424487757747L;
	
	
	@Id
	@GeneratedValue(generator="env_eds_water_supply_ei_water_supp_ei_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_water_supply_ei_water_supp_ei_id_seq",sequenceName="prwss_main.env_eds_water_supply_ei_water_supp_ei_id_seq")
	@Column(name="water_supp_ei_id")
	private Integer water_supp_ei_id;
	
	
	
	@Column(name="water_supp_sch_id")
	private Integer water_supp_sch_id;
	
	@Column(name="parameter_id")
	private Integer parameter_id;
	
	@Column(name="issue")
	private String issue;
	
	@Column(name="mitigation")
	private String mitigation;
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;

	public Integer getWater_supp_ei_id() {
		return water_supp_ei_id;
	}

	public void setWater_supp_ei_id(Integer water_supp_ei_id) {
		this.water_supp_ei_id = water_supp_ei_id;
	}

	public Integer getWater_supp_sch_id() {
		return water_supp_sch_id;
	}

	public void setWater_supp_sch_id(Integer water_supp_sch_id) {
		this.water_supp_sch_id = water_supp_sch_id;
	}

	public Integer getParameter_id() {
		return parameter_id;
	}

	public void setParameter_id(Integer parameter_id) {
		this.parameter_id = parameter_id;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getMitigation() {
		return mitigation;
	}

	public void setMitigation(String mitigation) {
		this.mitigation = mitigation;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	@Override
	public String toString() {
		return "WaterSupplyGridBean [water_supp_ei_id=" + water_supp_ei_id
				+ ", water_supp_sch_id=" + water_supp_sch_id
				+ ", parameter_id=" + parameter_id + ", issue=" + issue
				+ ", mitigation=" + mitigation + ", crt_by_user=" + crt_by_user
				+ "]";
	}
	
	
	
	
	

}

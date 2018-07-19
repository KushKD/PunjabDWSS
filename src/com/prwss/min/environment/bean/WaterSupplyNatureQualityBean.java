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
@Table(name="env_eds_water_supplyNatureQualiy",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class WaterSupplyNatureQualityBean implements Serializable{

	
	
	private static final long serialVersionUID = 1436891235397203732L;

	@Id
	@GeneratedValue(generator="env_eds_water_supplynaturequa_env_eds_water_supplynaturequa_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_water_supplynaturequa_env_eds_water_supplynaturequa_seq",sequenceName="prwss_main.env_eds_water_supplynaturequa_env_eds_water_supplynaturequa_seq")
	@Column(name="env_eds_water_supplyNatureQualiy_id")
	private Integer env_eds_water_supplyNatureQualiy_id;
	
	@Column(name="water_supp_sch_id")
	private Integer water_supp_sch_id;
	
	@Column(name="disease_id")
	private Integer disease_id;
	
	
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;



	public Integer getEnv_eds_water_supplyNatureQualiy_id() {
		return env_eds_water_supplyNatureQualiy_id;
	}



	public void setEnv_eds_water_supplyNatureQualiy_id(
			Integer env_eds_water_supplyNatureQualiy_id) {
		this.env_eds_water_supplyNatureQualiy_id = env_eds_water_supplyNatureQualiy_id;
	}



	public Integer getWater_supp_sch_id() {
		return water_supp_sch_id;
	}



	public void setWater_supp_sch_id(Integer water_supp_sch_id) {
		this.water_supp_sch_id = water_supp_sch_id;
	}



	public Integer getDisease_id() {
		return disease_id;
	}



	public void setDisease_id(Integer disease_id) {
		this.disease_id = disease_id;
	}



	public Integer getCrt_by_user() {
		return crt_by_user;
	}



	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}



	@Override
	public String toString() {
		return "WaterSupplyNatureQualityBean [env_eds_water_supplyNatureQualiy_id="
				+ env_eds_water_supplyNatureQualiy_id
				+ ", water_supp_sch_id="
				+ water_supp_sch_id
				+ ", disease_id="
				+ disease_id
				+ ", crt_by_user=" + crt_by_user + "]";
	}

	

		
		
		
		
		

}

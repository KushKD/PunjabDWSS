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
@Table(name="env_eds_water_supply_sch",schema=MISConstants.MIS_DB_SCHEMA_NAME)

public class EnvironmentWaterSchemeBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(generator="env_eds_water_supply_sch_water_supp_sch_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_water_supply_sch_water_supp_sch_id_seq",sequenceName="prwss_main.env_eds_water_supply_sch_water_supp_sch_id_seq")
	@Column(name="water_supp_sch_id")
	private Integer water_supp_sch_id;
	
	@Column(name="eds_id")
	private Integer eds_id	;
	
	@Column(name="scheme_bifurcation")
	private Integer scheme_bifurcation ;
	
	@Column(name="data_crnt_water")
	private String data_crnt_water ;
	
	@Column(name="src_drink_water")
	private Integer src_drink_water	;
	
	@Column(name="water_availability")
	private String water_availability ;
	
	@Column(name="land_availability")
	private Integer land_availability ;
	
	
	@Column(name="water_qual_access")
	private Integer water_qual_access ;
	
	@Column(name="nature_problem")
	private Integer nature_problem	;
	
	@Column(name="industrial_cont")
	private Integer industrial_cont	 ;
	
	@Column(name="prev_corr_measures")
	private String prev_corr_measures  ;
	
	@Column(name="borewell_ind_pak")
	private Integer borewell_ind_pak ;
	
	@Column(name="canal_source_treat")
	private Integer canal_source_treat  ;
	
	@Column(name="grwater_trear")
	private Integer grwater_trear ;
	
	@Column(name="disinfection_prov")
	private Integer disinfection_prov ;
	
	@Column(name="type_disinfection")
	 private Integer type_disinfection ;
	
	@Column(name="air_nose_water_test")
	 private Integer  air_nose_water_test ;
	
	@Column(name="lawn_tree_plant")
	private Integer lawn_tree_plant	 ;
	
	@Column(name="crt_by_user")
    private Integer     crt_by_user ;

	public Integer getWater_supp_sch_id() {
		return water_supp_sch_id;
	}

	public void setWater_supp_sch_id(Integer water_supp_sch_id) {
		this.water_supp_sch_id = water_supp_sch_id;
	}

	public Integer getEds_id() {
		return eds_id;
	}

	public void setEds_id(Integer eds_id) {
		this.eds_id = eds_id;
	}

	public Integer getScheme_bifurcation() {
		return scheme_bifurcation;
	}

	public void setScheme_bifurcation(Integer scheme_bifurcation) {
		this.scheme_bifurcation = scheme_bifurcation;
	}

	public String getData_crnt_water() {
		return data_crnt_water;
	}

	public void setData_crnt_water(String data_crnt_water) {
		this.data_crnt_water = data_crnt_water;
	}

	public Integer getSrc_drink_water() {
		return src_drink_water;
	}

	public void setSrc_drink_water(Integer src_drink_water) {
		this.src_drink_water = src_drink_water;
	}

	public String getWater_availability() {
		return water_availability;
	}

	public void setWater_availability(String water_availability) {
		this.water_availability = water_availability;
	}

	public Integer getLand_availability() {
		return land_availability;
	}

	public void setLand_availability(Integer land_availability) {
		this.land_availability = land_availability;
	}

	public Integer getWater_qual_access() {
		return water_qual_access;
	}

	public void setWater_qual_access(Integer water_qual_access) {
		this.water_qual_access = water_qual_access;
	}

	public Integer getNature_problem() {
		return nature_problem;
	}

	public void setNature_problem(Integer nature_problem) {
		this.nature_problem = nature_problem;
	}

	public Integer getIndustrial_cont() {
		return industrial_cont;
	}

	public void setIndustrial_cont(Integer industrial_cont) {
		this.industrial_cont = industrial_cont;
	}

	public String getPrev_corr_measures() {
		return prev_corr_measures;
	}

	public void setPrev_corr_measures(String prev_corr_measures) {
		this.prev_corr_measures = prev_corr_measures;
	}

	public Integer getBorewell_ind_pak() {
		return borewell_ind_pak;
	}

	public void setBorewell_ind_pak(Integer borewell_ind_pak) {
		this.borewell_ind_pak = borewell_ind_pak;
	}

	public Integer getCanal_source_treat() {
		return canal_source_treat;
	}

	public void setCanal_source_treat(Integer canal_source_treat) {
		this.canal_source_treat = canal_source_treat;
	}

	public Integer getGrwater_trear() {
		return grwater_trear;
	}

	public void setGrwater_trear(Integer grwater_trear) {
		this.grwater_trear = grwater_trear;
	}

	public Integer getDisinfection_prov() {
		return disinfection_prov;
	}

	public void setDisinfection_prov(Integer disinfection_prov) {
		this.disinfection_prov = disinfection_prov;
	}

	public Integer getType_disinfection() {
		return type_disinfection;
	}

	public void setType_disinfection(Integer type_disinfection) {
		this.type_disinfection = type_disinfection;
	}

	public Integer getAir_nose_water_test() {
		return air_nose_water_test;
	}

	public void setAir_nose_water_test(Integer air_nose_water_test) {
		this.air_nose_water_test = air_nose_water_test;
	}

	public Integer getLawn_tree_plant() {
		return lawn_tree_plant;
	}

	public void setLawn_tree_plant(Integer lawn_tree_plant) {
		this.lawn_tree_plant = lawn_tree_plant;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	@Override
	public String toString() {
		return "EnvironmentWaterSchemeBean [water_supp_sch_id="
				+ water_supp_sch_id + ", eds_id=" + eds_id
				+ ", scheme_bifurcation=" + scheme_bifurcation
				+ ", data_crnt_water=" + data_crnt_water + ", src_drink_water="
				+ src_drink_water + ", water_availability="
				+ water_availability + ", land_availability="
				+ land_availability + ", water_qual_access="
				+ water_qual_access + ", nature_problem=" + nature_problem
				+ ", industrial_cont=" + industrial_cont
				+ ", prev_corr_measures=" + prev_corr_measures
				+ ", borewell_ind_pak=" + borewell_ind_pak
				+ ", canal_source_treat=" + canal_source_treat
				+ ", grwater_trear=" + grwater_trear + ", disinfection_prov="
				+ disinfection_prov + ", type_disinfection="
				+ type_disinfection + ", air_nose_water_test="
				+ air_nose_water_test + ", lawn_tree_plant=" + lawn_tree_plant
				+ ", crt_by_user=" + crt_by_user + "]";
	}
	
	
	
	
	
	

}

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
@Table(name="env_eds_socailEnvironmnet",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EnvironmentSocialBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8585388031571509673L;



	@Id
	@GeneratedValue(generator="env_eds_socailenvironmnet_eds_social_env_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_socailenvironmnet_eds_social_env_id_seq",sequenceName="prwss_main.env_eds_socailenvironmnet_eds_social_env_id_seq")
	@Column(name="eds_social_env_id")
	private Integer eds_social_env_id;
	
	
	
	@Column(name="eds_id")
	private Integer eds_id;
	
	@Column(name="population")
	private Integer population;
	
	@Column(name="no_households")
	private Integer no_households;
	
	@Column(name="land_pattern")
	private Integer land_pattern;
	
	@Column(name="hist_rel_impt")
	private Integer hist_rel_impt;
	
	//income_src
	@Column(name="income_src")
	private Integer income_src;
	
	//effct_nat_prop
	@Column(name="effct_nat_prop")
	private Integer effct_nat_prop;
	
	//app_safegaurd
	@Column(name="app_safegaurd")
	private String app_safegaurd;
	
	//infrindge_rights
	@Column(name="infrindge_rights")
	private Integer infrindge_rights;
	
	
	//app_mitigation
	@Column(name="app_mitigation")
	private String app_mitigation;
	
	//crt_by_user
	@Column(name="crt_by_user")
	private Integer crt_by_user;

	
	
	
	
	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Integer getNo_households() {
		return no_households;
	}

	public void setNo_households(Integer no_households) {
		this.no_households = no_households;
	}

	public Integer getEds_social_env_id() {
		return eds_social_env_id;
	}

	public void setEds_social_env_id(Integer eds_social_env_id) {
		this.eds_social_env_id = eds_social_env_id;
	}

	public Integer getEds_id() {
		return eds_id;
	}

	public void setEds_id(Integer eds_id) {
		this.eds_id = eds_id;
	}

	public Integer getLand_pattern() {
		return land_pattern;
	}

	public void setLand_pattern(Integer land_pattern) {
		this.land_pattern = land_pattern;
	}

	public Integer getHist_rel_impt() {
		return hist_rel_impt;
	}

	public void setHist_rel_impt(Integer hist_rel_impt) {
		this.hist_rel_impt = hist_rel_impt;
	}

	public Integer getIncome_src() {
		return income_src;
	}

	public void setIncome_src(Integer income_src) {
		this.income_src = income_src;
	}

	public Integer getEffct_nat_prop() {
		return effct_nat_prop;
	}

	public void setEffct_nat_prop(Integer effct_nat_prop) {
		this.effct_nat_prop = effct_nat_prop;
	}

	public String getApp_safegaurd() {
		return app_safegaurd;
	}

	public void setApp_safegaurd(String app_safegaurd) {
		this.app_safegaurd = app_safegaurd;
	}

	public Integer getInfrindge_rights() {
		return infrindge_rights;
	}

	public void setInfrindge_rights(Integer infrindge_rights) {
		this.infrindge_rights = infrindge_rights;
	}

	public String getApp_mitigation() {
		return app_mitigation;
	}

	public void setApp_mitigation(String app_mitigation) {
		this.app_mitigation = app_mitigation;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	@Override
	public String toString() {
		return "EnvironmentSocialBean [eds_social_env_id=" + eds_social_env_id
				+ ", eds_id=" + eds_id + ", population=" + population
				+ ", no_households=" + no_households + ", land_pattern="
				+ land_pattern + ", hist_rel_impt=" + hist_rel_impt
				+ ", income_src=" + income_src + ", effct_nat_prop="
				+ effct_nat_prop + ", app_safegaurd=" + app_safegaurd
				+ ", infrindge_rights=" + infrindge_rights
				+ ", app_mitigation=" + app_mitigation + ", crt_by_user="
				+ crt_by_user + "]";
	}

	
	

}

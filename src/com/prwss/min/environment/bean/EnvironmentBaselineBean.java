package com.prwss.min.environment.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.sanitation.bean.ComboBeanDetails;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="env_eds_baseline_env",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EnvironmentBaselineBean  implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3968069388538083998L;
	//env_eds_baseline_env_eds_bsln_env_id_seq
	
	@Id
	@GeneratedValue(generator="env_eds_baseline_env_eds_bsln_env_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_baseline_env_eds_bsln_env_id_seq",sequenceName="prwss_main.env_eds_baseline_env_eds_bsln_env_id_seq")
	@Column(name="eds_bsln_env_id")
	private Integer eds_bsln_env_id;
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;
	
	
	@Column(name="eds_id")
	private Integer eds_id;
	
	@Column(name="topography_id")
	private Integer topography_id;
	
	@Column(name="soil_type")
	private Integer soil_type;
	
	@Column(name="rain_fall")
	private Integer rain_fall;
	
	@Column(name="min_temp")
	private Integer min_temp;
	
	@Column(name="max_temp")
	private Integer max_temp;
	
	@Column(name="wind_direction")
	private Integer wind_direction;
	
	@Column(name="land_slope")
	private String land_slope;
	
	
	
	
	
	@Column(name="water_level")
	private Integer water_level;
	
	@Column(name="water_body")
	private Integer water_body;
	
	@Column(name="water_logging")
	private Integer water_logging;
	
	@Column(name="road_min_wdth")
	private Integer road_min_wdth;
	
	
	@Column(name="road_max_wdth")
	private Integer road_max_wdth;
	
	@Column(name="road_type")
	private Integer road_type;
	
	@Column(name="lcl_vegetation")
	private Integer lcl_vegetation;
	
	
	

	public String getLand_slope() {
		return land_slope;
	}

	public void setLand_slope(String land_slope) {
		this.land_slope = land_slope;
	}

	public Integer getEds_bsln_env_id() {
		return eds_bsln_env_id;
	}

	public void setEds_bsln_env_id(Integer eds_bsln_env_id) {
		this.eds_bsln_env_id = eds_bsln_env_id;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	public Integer getEds_id() {
		return eds_id;
	}

	public void setEds_id(Integer eds_id) {
		this.eds_id = eds_id;
	}

	public Integer getTopography_id() {
		return topography_id;
	}

	public void setTopography_id(Integer topography_id) {
		this.topography_id = topography_id;
	}

	public Integer getSoil_type() {
		return soil_type;
	}

	public void setSoil_type(Integer soil_type) {
		this.soil_type = soil_type;
	}

	public Integer getRain_fall() {
		return rain_fall;
	}

	public void setRain_fall(Integer rain_fall) {
		this.rain_fall = rain_fall;
	}

	public Integer getMin_temp() {
		return min_temp;
	}

	public void setMin_temp(Integer min_temp) {
		this.min_temp = min_temp;
	}

	public Integer getMax_temp() {
		return max_temp;
	}

	public void setMax_temp(Integer max_temp) {
		this.max_temp = max_temp;
	}

	public Integer getWind_direction() {
		return wind_direction;
	}

	public void setWind_direction(Integer wind_direction) {
		this.wind_direction = wind_direction;
	}

	public Integer getWater_level() {
		return water_level;
	}

	public void setWater_level(Integer water_level) {
		this.water_level = water_level;
	}

	public Integer getWater_body() {
		return water_body;
	}

	public void setWater_body(Integer water_body) {
		this.water_body = water_body;
	}

	public Integer getWater_logging() {
		return water_logging;
	}

	public void setWater_logging(Integer water_logging) {
		this.water_logging = water_logging;
	}

	public Integer getRoad_min_wdth() {
		return road_min_wdth;
	}

	public void setRoad_min_wdth(Integer road_min_wdth) {
		this.road_min_wdth = road_min_wdth;
	}

	public Integer getRoad_max_wdth() {
		return road_max_wdth;
	}

	public void setRoad_max_wdth(Integer road_max_wdth) {
		this.road_max_wdth = road_max_wdth;
	}

	public Integer getRoad_type() {
		return road_type;
	}

	public void setRoad_type(Integer road_type) {
		this.road_type = road_type;
	}

	public Integer getLcl_vegetation() {
		return lcl_vegetation;
	}

	public void setLcl_vegetation(Integer lcl_vegetation) {
		this.lcl_vegetation = lcl_vegetation;
	}

	@Override
	public String toString() {
		return "EnvironmentBaselineBean [eds_bsln_env_id=" + eds_bsln_env_id
				+ ", crt_by_user=" + crt_by_user + ", eds_id=" + eds_id
				+ ", topography_id=" + topography_id + ", soil_type="
				+ soil_type + ", rain_fall=" + rain_fall + ", min_temp="
				+ min_temp + ", max_temp=" + max_temp + ", wind_direction="
				+ wind_direction + ", land_slope=" + land_slope
				+ ", water_level=" + water_level + ", water_body=" + water_body
				+ ", water_logging=" + water_logging + ", road_min_wdth="
				+ road_min_wdth + ", road_max_wdth=" + road_max_wdth
				+ ", road_type=" + road_type + ", lcl_vegetation="
				+ lcl_vegetation + "]";
	}

	
	
	
	
			
}

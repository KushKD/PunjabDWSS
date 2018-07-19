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
@Table(name="env_eds_public_health_vec_det",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EnvironmentVectorBourneDiseaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6113496441196479881L;
	
	@Id
	@GeneratedValue(generator="env_eds_public_health_vec_det_eds_pblc_hlth_vec_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_public_health_vec_det_eds_pblc_hlth_vec_id_seq",sequenceName="prwss_main.env_eds_public_health_vec_det_eds_pblc_hlth_vec_id_seq")
	@Column(name="eds_pblc_hlth_vec_id")
	private Integer eds_pblc_hlth_vec_id;
	
	
	
	@Column(name="eds_pblc_hlth_id")
	private Integer eds_pblc_hlth_id;
	
	@Column(name="disease_id")
	private Integer disease_id;
	
	@Column(name="Crt_By_User")
	private Integer Crt_By_User;

	public Integer getEds_pblc_hlth_vec_id() {
		return eds_pblc_hlth_vec_id;  
	}

	public void setEds_pblc_hlth_vec_id(Integer eds_pblc_hlth_vec_id) {
		this.eds_pblc_hlth_vec_id = eds_pblc_hlth_vec_id;
	}

	public Integer getEds_pblc_hlth_id() {
		return eds_pblc_hlth_id;
	}

	public void setEds_pblc_hlth_id(Integer eds_pblc_hlth_id) {
		this.eds_pblc_hlth_id = eds_pblc_hlth_id;
	}

	public Integer getDisease_id() {
		return disease_id;
	}

	public void setDisease_id(Integer disease_id) {
		this.disease_id = disease_id;
	}

	public Integer getCrt_By_User() {
		return Crt_By_User;
	}

	public void setCrt_By_User(Integer crt_By_User) {
		Crt_By_User = crt_By_User;
	}

	@Override
	public String toString() {
		return "EnvironmentVectorBourneDiseaseBean [eds_pblc_hlth_vec_id="
				+ eds_pblc_hlth_vec_id + ", eds_pblc_hlth_id="
				+ eds_pblc_hlth_id + ", disease_id=" + disease_id
				+ ", Crt_By_User=" + Crt_By_User + "]";
	}
	
	
	

}

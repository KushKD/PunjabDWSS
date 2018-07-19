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
@Table(name="env_eds_public_health",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EnvironmentHealthBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106721623850930272L;
	
	
	
	@Id
	@GeneratedValue(generator="env_eds_public_health_eds_pblc_hlth_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_public_health_eds_pblc_hlth_id_seq",sequenceName="prwss_main.env_eds_public_health_eds_pblc_hlth_id_seq")
	@Column(name="eds_pblc_hlth_id")
	private Integer eds_pblc_hlth_id;
	
	@Column(name="eds_id")
	private Integer eds_id;
	
	@Column(name="water_brn_disease")
	private Integer water_brn_disease;
	
	/*@Column(name="wb_disease_name")
	private Integer wb_disease_name;*/
	
	@Column(name="vector_brn_disease")
	private Integer vector_brn_disease;
	
	/*@Column(name="vb_disease_name")
	private Integer vb_disease_name;*/
	
	//crt_by_user
		@Column(name="crt_by_user")
		private Integer crt_by_user;

		public Integer getEds_pblc_hlth_id() {
			return eds_pblc_hlth_id;
		}

		public void setEds_pblc_hlth_id(Integer eds_pblc_hlth_id) {
			this.eds_pblc_hlth_id = eds_pblc_hlth_id;
		}

		public Integer getEds_id() {
			return eds_id;
		}

		public void setEds_id(Integer eds_id) {
			this.eds_id = eds_id;
		}

		public Integer getWater_brn_disease() {
			return water_brn_disease;
		}

		public void setWater_brn_disease(Integer water_brn_disease) {
			this.water_brn_disease = water_brn_disease;
		}

	/*	public Integer getWb_disease_name() {
			return wb_disease_name;
		}

		public void setWb_disease_name(Integer wb_disease_name) {
			this.wb_disease_name = wb_disease_name;
		}*/

		public Integer getVector_brn_disease() {
			return vector_brn_disease;
		}

		public void setVector_brn_disease(Integer vector_brn_disease) {
			this.vector_brn_disease = vector_brn_disease;
		}

	/*	public Integer getVb_disease_name() {
			return vb_disease_name;
		}

		public void setVb_disease_name(Integer vb_disease_name) {
			this.vb_disease_name = vb_disease_name;
		}*/

		public Integer getCrt_by_user() {
			return crt_by_user;
		}

		public void setCrt_by_user(Integer crt_by_user) {
			this.crt_by_user = crt_by_user;
		}

		@Override
		public String toString() {
			return "EnvironmentHealthBean [eds_pblc_hlth_id="
					+ eds_pblc_hlth_id + ", eds_id=" + eds_id
					+ ", water_brn_disease=" + water_brn_disease
					+ ", vector_brn_disease=" + vector_brn_disease
					+ ", crt_by_user=" + crt_by_user + "]";
		}

		
		
		
		
		

}

package com.prwss.min.SDU.bean;

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
@Table(name="sdu_village_training_material",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SduVillageTrainingMaterialBean implements Serializable{
	
	private static final long serialVersionUID = 3635019876719815L;
	
	@Id
	@GeneratedValue(generator="sdu_village_training_material_trng_material_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="sdu_village_training_material_trng_material_id_seq",sequenceName="prwss_main.sdu_village_training_material_trng_material_id_seq")
	@Column(name="trng_material_id")
	private Integer trng_material_id;
	
	@Column(name="training_id")
	private Long training_id;
	
	@Column(name="material_type")
	private Integer material_type;
	
	@Column(name="quantity")
	private Long quantity;
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;

	

	public Integer getTrng_material_id() {
		return trng_material_id;
	}

	public void setTrng_material_id(Integer trng_material_id) {
		this.trng_material_id = trng_material_id;
	}

	public Long getTraining_id() {
		return training_id;
	}

	public void setTraining_id(Long training_id) {
		this.training_id = training_id;
	}

	public Integer getMaterial_type() {
		return material_type;
	}

	public void setMaterial_type(Integer material_type) {
		this.material_type = material_type;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	@Override
	public String toString() {
		return "SduVillageTrainingMaterialBean [trng_material_id=" + trng_material_id + ", training_id=" + training_id
				+ ", material_type=" + material_type + ", quantity=" + quantity + ", crt_by_user=" + crt_by_user + "]";
	}
	
	
}

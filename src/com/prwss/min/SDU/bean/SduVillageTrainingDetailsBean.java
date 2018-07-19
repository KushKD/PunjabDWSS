package com.prwss.min.SDU.bean;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name="sdu_village_training_details",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SduVillageTrainingDetailsBean implements Serializable{
	
	private static final long serialVersionUID = 3635012326719815L;
	
	@Id
	@GeneratedValue(generator="sdu_village_training_details_training_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="sdu_village_training_details_training_id_seq",sequenceName="prwss_main.sdu_village_training_details_training_id_seq")
	@Column(name="training_id")
	private Integer training_id;
	
	@Column(name="financial_year")
	private Integer financial_year;
	
	@Column(name="division_id")
	private Integer division_id;
	
	@Column(name="village_id")
	private Integer village_id;
	
	@Column(name="activity_id")
	private Integer activity_id;
	
	@Column(name="training_ref_no")
	private String training_ref_no;
	
	@Column(name="training_dt")
	private Date training_dt;
	
	@Column(name="training_level")
	private Integer training_level;
	
	@Column(name="trainer")
	private String trainer;
	
	@Column(name="training_name")
	private String training_name;
	
	@Column(name="expenditure")
	private Double expenditure;
	
	@Column(name="total_male")
	private Long total_male;
	
	@Column(name="sc_male")
	private Long sc_male;
	
	@Column(name="total_female")
	private Long total_female;
	
	@Column(name="sc_female")
	private Long sc_female;
	
	@Column(name="panch_member")
	private Long panch_member;
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;

	
	
	public Integer getTraining_id() {
		return training_id;
	}

	public void setTraining_id(Integer training_id) {
		this.training_id = training_id;
	}

	public Integer getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(Integer financial_year) {
		this.financial_year = financial_year;
	}

	public Integer getDivision_id() {
		return division_id;
	}

	public void setDivision_id(Integer division_id) {
		this.division_id = division_id;
	}

	public Integer getVillage_id() {
		return village_id;
	}

	public void setVillage_id(Integer village_id) {
		this.village_id = village_id;
	}

	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}

	public String getTraining_ref_no() {
		return training_ref_no;
	}

	public void setTraining_ref_no(String training_ref_no) {
		this.training_ref_no = training_ref_no;
	}

	public Date getTraining_dt() {
		return training_dt;
	}

	public void setTraining_dt(Date training_dt) {
		this.training_dt = training_dt;
	}

	public Integer getTraining_level() {
		return training_level;
	}

	public void setTraining_level(Integer training_level) {
		this.training_level = training_level;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getTraining_name() {
		return training_name;
	}

	public void setTraining_name(String training_name) {
		this.training_name = training_name;
	}

	public Double getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(Double expenditure) {
		this.expenditure = expenditure;
	}

	public Long getTotal_male() {
		return total_male;
	}

	public void setTotal_male(Long total_male) {
		this.total_male = total_male;
	}

	public Long getSc_male() {
		return sc_male;
	}

	public void setSc_male(Long sc_male) {
		this.sc_male = sc_male;
	}

	public Long getTotal_female() {
		return total_female;
	}

	public void setTotal_female(Long total_female) {
		this.total_female = total_female;
	}

	public Long getSc_female() {
		return sc_female;
	}

	public void setSc_female(Long sc_female) {
		this.sc_female = sc_female;
	}

	public Long getPanch_member() {
		return panch_member;
	}

	public void setPanch_member(Long panch_member) {
		this.panch_member = panch_member;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	@Override
	public String toString() {
		return "SduVillageTrainingDetailsBean [training_id=" + training_id + ", financial_year=" + financial_year
				+ ", division_id=" + division_id + ", village_id=" + village_id + ", activity_id=" + activity_id
				+ ", training_ref_no=" + training_ref_no + ", training_dt=" + training_dt + ", training_level="
				+ training_level + ", trainer=" + trainer + ", training_name=" + training_name + ", expenditure="
				+ expenditure + ", total_male=" + total_male + ", sc_male=" + sc_male + ", total_female=" + total_female
				+ ", sc_female=" + sc_female + ", panch_member=" + panch_member + ", crt_by_user=" + crt_by_user + "]";
	}
	
}

package com.prwss.min.sanitation.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "san_survey_master", schema = MISConstants.MIS_DB_SCHEMA_NAME)

//san_survey_Master_seq
public class SurveyMasterBean implements Serializable {
	
	
	private static final long serialVersionUID = 565898372583691926L;
	
	
	@Id
	@GeneratedValue(generator = "san_survey_master_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "san_survey_master_seq", sequenceName = "prwss_main.san_survey_master_seq")

	
	@Column(name = "survey_id", nullable = false)
	private Integer survey_id;
	
	
	@Column(name = "survey_name", nullable = false)
	private String survey_name;
	
	@Column(name = "sur_status", nullable = false)
	private Integer sur_status;
	
	@Column(name = "plnd_strt_date", nullable = true)
	private Date plnd_strt_date;
	
	@Column(name = "plnd_end_date", nullable = true)
	private Date plnd_end_date;
	
	@Column(name = "actl", nullable = true)
	private Date actl;
	
	@Column(name = "actl_end_date", nullable = true)
	private Date actl_end_date;
	
	@Column(name = "purpose", nullable = false)
	private String purpose;

	@Column(name = "crt_date", nullable = true)
	private Date crt_date;
	
	@Column(name = "crt_by_usr", nullable = true)
	private Integer crt_by_usr;
	
	@Column(name = "lst_updated_date", nullable = true)
	private Date lst_updated_date;
	
	@Column(name = "lst_updated_user", nullable = true)
	private Integer lst_updated_user;
	
	
	@Column(name = "loc_id", nullable = true)
	private Integer loc_id;
	
	@Column(name = "active_flag", nullable = false)
	private Integer active_flag;
	
	
	
	

	public Integer getSurvey_id() {
		return survey_id;
	}

	public void setSurvey_id(Integer survey_id) {
		this.survey_id = survey_id;
	}

	

	public String getSurvey_name() {
		return survey_name;
	}

	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}

	public Integer getSur_status() {
		return sur_status;
	}

	public void setSur_status(Integer sur_status) {
		this.sur_status = sur_status;
	}

	public Date getPlnd_strt_date() {
		return plnd_strt_date;
	}

	public void setPlnd_strt_date(Date plnd_strt_date) {
		this.plnd_strt_date = plnd_strt_date;
	}

	public Date getPlnd_end_date() {
		return plnd_end_date;
	}

	public void setPlnd_end_date(Date plnd_end_date) {
		this.plnd_end_date = plnd_end_date;
	}

	public Date getActl() {
		return actl;
	}

	public void setActl(Date actl) {
		this.actl = actl;
	}

	public Date getActl_end_date() {
		return actl_end_date;
	}

	public void setActl_end_date(Date actl_end_date) {
		this.actl_end_date = actl_end_date;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Date getCrt_date() {
		return crt_date;
	}

	public void setCrt_date(Date crt_date) {
		this.crt_date = crt_date;
	}

	public Integer getCrt_by_usr() {
		return crt_by_usr;
	}

	public void setCrt_by_usr(Integer crt_by_usr) {
		this.crt_by_usr = crt_by_usr;
	}

	public Date getLst_updated_date() {
		return lst_updated_date;
	}

	public void setLst_updated_date(Date lst_updated_date) {
		this.lst_updated_date = lst_updated_date;
	}

	public Integer getLst_updated_user() {
		return lst_updated_user;
	}

	public void setLst_updated_user(Integer lst_updated_user) {
		this.lst_updated_user = lst_updated_user;
	}

	public Integer getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(Integer loc_id) {
		this.loc_id = loc_id;
	}

	public Integer getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(Integer active_flag) {
		this.active_flag = active_flag;
	}

	@Override
	public String toString() {
		return "SurveyMasterBean [survey_id=" + survey_id  + ", survey_name=" + survey_name + ", sur_status=" + sur_status
				+ ", plnd_strt_date=" + plnd_strt_date + ", plnd_end_date=" + plnd_end_date + ", actl=" + actl
				+ ", actl_end_date=" + actl_end_date + ", purpose=" + purpose + ", crt_date=" + crt_date
				+ ", crt_by_usr=" + crt_by_usr + ", lst_updated_date=" + lst_updated_date + ", lst_updated_user="
				+ lst_updated_user + ", loc_id=" + loc_id + ", active_flag=" + active_flag + "]";
	}
		
	
}

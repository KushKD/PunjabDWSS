package com.prwss.min.finance.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="pms_scheme_dtl_upgrade",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class PMSSchemeDetailUpgradeBean implements Serializable{
	
	private static final long serialVersionUID = 3635322357765L;
	
	@Id
	@Column(name="scheme_dtl")
	private Integer scheme_dtl;
	
	@Column(name="scheme_id")
	private Integer scheme_id;
	
	@Column(name="prog_id")
	private Integer prog_id;
	
	@Column(name="addmin_app_no")
	private String addmin_app_no;
	
	@Column(name="addmin_app_date")
	private Date addmin_app_date;
	
	@Column(name="admin_app_amount")
	private Long admin_app_amount;
	
	@Column(name="tech_app_no")
	private String tech_app_no;
	
	@Column(name="tech_app_date")
	private Date tech_app_date;
	
	@Column(name="tech_app_amount")
	private Long tech_app_amount;
	
	@Column(name="wb_app_no")
	private String wb_app_no;
	
	@Column(name="wb_app_date")
	private Date wb_app_date;
	
	@Column(name="wb_app_amount")
	private Long wb_app_amount;

	

	public Integer getScheme_dtl() {
		return scheme_dtl;
	}

	public void setScheme_dtl(Integer scheme_dtl) {
		this.scheme_dtl = scheme_dtl;
	}

	public Integer getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(Integer scheme_id) {
		this.scheme_id = scheme_id;
	}

	public Integer getProg_id() {
		return prog_id;
	}

	public void setProg_id(Integer prog_id) {
		this.prog_id = prog_id;
	}

	public String getAddmin_app_no() {
		return addmin_app_no;
	}

	public void setAddmin_app_no(String addmin_app_no) {
		this.addmin_app_no = addmin_app_no;
	}

	public Date getAddmin_app_date() {
		return addmin_app_date;
	}

	public void setAddmin_app_date(Date addmin_app_date) {
		this.addmin_app_date = addmin_app_date;
	}

	public Long getAdmin_app_amount() {
		return admin_app_amount;
	}

	public void setAdmin_app_amount(Long admin_app_amount) {
		this.admin_app_amount = admin_app_amount;
	}

	public String getTech_app_no() {
		return tech_app_no;
	}

	public void setTech_app_no(String tech_app_no) {
		this.tech_app_no = tech_app_no;
	}

	public Date getTech_app_date() {
		return tech_app_date;
	}

	public void setTech_app_date(Date tech_app_date) {
		this.tech_app_date = tech_app_date;
	}

	public Long getTech_app_amount() {
		return tech_app_amount;
	}

	public void setTech_app_amount(Long tech_app_amount) {
		this.tech_app_amount = tech_app_amount;
	}

	public String getWb_app_no() {
		return wb_app_no;
	}

	public void setWb_app_no(String wb_app_no) {
		this.wb_app_no = wb_app_no;
	}

	public Date getWb_app_date() {
		return wb_app_date;
	}

	public void setWb_app_date(Date wb_app_date) {
		this.wb_app_date = wb_app_date;
	}

	public Long getWb_app_amount() {
		return wb_app_amount;
	}

	public void setWb_app_amount(Long wb_app_amount) {
		this.wb_app_amount = wb_app_amount;
	}

	@Override
	public String toString() {
		return "PMSSchemeDetailUpgradeBean [scheme_dtl=" + scheme_dtl + ", scheme_id=" + scheme_id + ", prog_id="
				+ prog_id + ", addmin_app_no=" + addmin_app_no + ", addmin_app_date=" + addmin_app_date
				+ ", admin_app_amount=" + admin_app_amount + ", tech_app_no=" + tech_app_no + ", tech_app_date="
				+ tech_app_date + ", tech_app_amount=" + tech_app_amount + ", wb_app_no=" + wb_app_no + ", wb_app_date="
				+ wb_app_date + ", wb_app_amount=" + wb_app_amount + "]";
	}
	
	
}

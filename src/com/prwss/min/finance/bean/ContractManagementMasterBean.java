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
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Table(name="contract_mgmt_mst",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ContractManagementMasterBean implements Serializable{
	
	private static final long serialVersionUID = 363532357765L;
	
	@Id
	@Column(name="contract_mgmt")
	private Integer contract_mgmt;
	
	@Column(name="scheme_id")
	private Integer scheme_id;
	
	@Column(name="award_date")
	private Date award_date;
	
	@Column(name="award_amount")
	private Long award_amount;
	
	@Column(name="award_no")
	private String award_no;

	
	
	public Integer getContract_mgmt() {
		return contract_mgmt;
	}

	public void setContract_mgmt(Integer contract_mgmt) {
		this.contract_mgmt = contract_mgmt;
	}

	public Integer getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(Integer scheme_id) {
		this.scheme_id = scheme_id;
	}

	public Date getAward_date() {
		return award_date;
	}

	public void setAward_date(Date award_date) {
		this.award_date = award_date;
	}

	public Long getAward_amount() {
		return award_amount;
	}

	public void setAward_amount(Long award_amount) {
		this.award_amount = award_amount;
	}

	public String getAward_no() {
		return award_no;
	}

	public void setAward_no(String award_no) {
		this.award_no = award_no;
	}

	@Override
	public String toString() {
		return "ContractManagementMasterBean [contract_mgmt=" + contract_mgmt + ", scheme_id=" + scheme_id
				+ ", award_date=" + award_date + ", award_amount=" + award_amount + ", award_no=" + award_no + "]";
	}
	

}

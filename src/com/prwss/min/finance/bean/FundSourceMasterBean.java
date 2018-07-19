/**
 * 
 */
package com.prwss.min.finance.bean;

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

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="finance_fund_source_master",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class FundSourceMasterBean implements Serializable {

	private static final long serialVersionUID = 3635335776815L;
	
	
	@Id
	@GeneratedValue(generator = "finance_fund_source_master_fund_source_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "finance_fund_source_master_fund_source_id_seq", sequenceName = "prwss_main.finance_fund_source_master_fund_source_id_seq")
	@Column(name="fund_source_id")
	private Long fundSourceId;

	@Column(name = "program_id")
	private Long programId;
	
	@Column(name = "scheme_id")
	private Long schemeId;
	
	@Column(name = "fund_source_mst")
	private String fundSourceMst;
	
	@Column(name = "state_share")
	private String stateShare;
	
	@Column(name = "center_share")
	private String centerShare;
	
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	public Long getFundSourceId() {
		return fundSourceId;
	}

	public void setFundSourceId(Long fundSourceId) {
		this.fundSourceId = fundSourceId;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public Long getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}

	public String getFundSourceMst() {
		return fundSourceMst;
	}

	public void setFundSourceMst(String fundSourceMst) {
		this.fundSourceMst = fundSourceMst;
	}

	public String getStateShare() {
		return stateShare;
	}

	public void setStateShare(String stateShare) {
		this.stateShare = stateShare;
	}

	public String getCenterShare() {
		return centerShare;
	}

	public void setCenterShare(String centerShare) {
		this.centerShare = centerShare;
	}

	public Long getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Long crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "FundSourceMasterBean [fundSourceId=" + fundSourceId + ", programId=" + programId + ", schemeId="
				+ schemeId + ", fundSourceMst=" + fundSourceMst + ", stateShare=" + stateShare + ", centerShare="
				+ centerShare + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}

}

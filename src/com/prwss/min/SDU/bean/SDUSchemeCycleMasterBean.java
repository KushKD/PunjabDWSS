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
@Table(name="sdu_sch_cyl_mst",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SDUSchemeCycleMasterBean implements Serializable{
	
	private static final long serialVersionUID = 692672475448538475L;
	
	@Id
	@SequenceGenerator(name="sdu_sch_cyl_mst_sch_cylce_id_seq",sequenceName="prwss_main.sdu_sch_cyl_mst_sch_cylce_id_seq")
	@GeneratedValue(generator="sdu_sch_cyl_mst_sch_cylce_id_seq",strategy=GenerationType.AUTO)
	@Column(name="sch_cylce_id")
	private Integer schCylceId;
	
	
	@Column(name="stage_id")
	private Integer stageId;
	
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="compaign_id")
	private Integer compaignId;
	
	@Column(name="village_no")
	private Integer villageNo;
	
	@Column(name="financial_year")
	private Integer financialYear;
	
	@Column(name="compaign_description")
	private String compaignDescription;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	/*@Column(name="crt_date")
	private Date createdDate;*/
	
	@Column(name="crt_by_usr")
	private Integer createdByUser;

	
	
	public Integer getSchCylceId() {
		return schCylceId;
	}

	public void setSchCylceId(Integer schCylceId) {
		this.schCylceId = schCylceId;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCompaignId() {
		return compaignId;
	}

	public void setCompaignId(Integer compaignId) {
		this.compaignId = compaignId;
	}

	public Integer getVillageNo() {
		return villageNo;
	}

	public void setVillageNo(Integer villageNo) {
		this.villageNo = villageNo;
	}

	public Integer getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(Integer financialYear) {
		this.financialYear = financialYear;
	}

	public String getCompaignDescription() {
		return compaignDescription;
	}

	public void setCompaignDescription(String compaignDescription) {
		this.compaignDescription = compaignDescription;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	/*public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}*/

	public Integer getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}

	@Override
	public String toString() {
		return "SDUSchemeCycleMasterBean [schCylceId=" + schCylceId + ", stageId=" + stageId + ", categoryId="
				+ categoryId + ", compaignId=" + compaignId + ", villageNo=" + villageNo + ", financialYear="
				+ financialYear + ", compaignDescription=" + compaignDescription + ", activeFlag=" + activeFlag
				+ ", createdByUser=" + createdByUser + "]";
	}
	
}

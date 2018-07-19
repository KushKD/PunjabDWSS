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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "sdu_division_village_mpg", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class DivisionVillageMappingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -521657676663104820L;

	@Id
	@SequenceGenerator(name = "sdu_division_village_mpg_div_village_id_seq", sequenceName = "prwss_main.sdu_division_village_mpg_div_village_id_seq")
	@GeneratedValue(generator = "sdu_division_village_mpg_div_village_id_seq", strategy = GenerationType.AUTO)
	@Column(name = "div_village_id")
	private Integer divVillageId;

	@Column(name = "financial_year")
	private Integer financialYear;

	@Column(name = "stage_id")
	private Integer stageId;

	@Column(name = "component_id")
	private Integer componentId;

	@Column(name = "division_id")
	private Integer divisionId;

	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "active_flag")
	private Integer activeFlag;

	/*
	 * @Column (name="crt_date") private Date createdDate;
	 */

	@Column(name = "crt_by_usr")
	private Integer createdByUser;

	public Integer getDivVillageId() {
		return divVillageId;
	}

	public void setDivVillageId(Integer divVillageId) {
		this.divVillageId = divVillageId;
	}

	public Integer getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(Integer financialYear) {
		this.financialYear = financialYear;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}

	@Override
	public String toString() {
		return "DivisionVillageMappingBean [divVillageId=" + divVillageId + ", financialYear=" + financialYear
				+ ", stageId=" + stageId + ", componentId=" + componentId + ", divisionId=" + divisionId
				+ ", categoryId=" + categoryId + ", activeFlag=" + activeFlag + ", createdByUser=" + createdByUser
				+ "]";
	}

}

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
@Table(name="sdu_division_activity_mpg",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DivisionActivityMpgBean implements Serializable{
	
	private static final long serialVersionUID = 3635012326519815L;

	@Id
	@GeneratedValue(generator="sdu_division_activity_mpg_div_activity_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="sdu_division_activity_mpg_div_activity_id_seq",sequenceName="prwss_main.sdu_division_activity_mpg_div_activity_id_seq")
	@Column(name="div_activity_id")
	private Integer div_activity_id;
	
	@Column(name="financial_year")
	private Integer financial_year;
	
	@Column(name="division_id")
	private Integer division_id;
	
	@Column(name="stage_id")
	private Integer stage_id;
	
	@Column(name="component_id")
	private Integer component_id;
	
	@Column(name="campaign_id")
	private Integer campaign_id;
	
	@Column(name="category_id")
	private Integer category_id;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="crt_by_usr")
	private Integer createdByUser;

	public Integer getDiv_activity_id() {
		return div_activity_id;
	}

	public void setDiv_activity_id(Integer div_activity_id) {
		this.div_activity_id = div_activity_id;
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

	public Integer getStage_id() {
		return stage_id;
	}

	public void setStage_id(Integer stage_id) {
		this.stage_id = stage_id;
	}

	public Integer getComponent_id() {
		return component_id;
	}

	public void setComponent_id(Integer component_id) {
		this.component_id = component_id;
	}

	public Integer getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Integer campaign_id) {
		this.campaign_id = campaign_id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
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
		return "DivisionActivityMpgBean [div_activity_id=" + div_activity_id + ", financial_year=" + financial_year
				+ ", division_id=" + division_id + ", stage_id=" + stage_id + ", component_id=" + component_id
				+ ", campaign_id=" + campaign_id + ", category_id=" + category_id + ", activeFlag=" + activeFlag
				+ ", createdByUser=" + createdByUser + "]";
	}

}

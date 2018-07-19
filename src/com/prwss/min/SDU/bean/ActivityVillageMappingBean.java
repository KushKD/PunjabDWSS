package com.prwss.min.SDU.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.min.sanitation.bean.ComboBeanDetails;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "sdu_village_activity_mpg", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class ActivityVillageMappingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4190044048033112873L;

	@Id
	@SequenceGenerator(name = "sdu_village_activity_mpg_village_activity_id_seq", sequenceName = "prwss_main.sdu_village_activity_mpg_village_activity_id_seq")
	@GeneratedValue(generator = "sdu_village_activity_mpg_village_activity_id_seq", strategy = GenerationType.AUTO)
	@Column(name = "village_activity_id")
	private Integer activityVillageId;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="financial_year",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboBeanDetailsFinancialYear;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="component_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboBeanDetailsComponentId;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="category_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboBeanDetailsCategoryId;
	
	@OneToOne(targetEntity=StageDetailBean.class)
	@JoinColumn(name="stage_id",referencedColumnName="stage_dtl_id",insertable=false,updatable=false)
	private StageDetailBean  stageDetailBeanStageId;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id",referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean  locationDetailsBeanVillageId;
	
	@OneToOne(targetEntity=LocationDivisionSubDivisonDetailsBean.class)
	@JoinColumn(name="division_id",referencedColumnName="div_subdiv_id",insertable=false,updatable=false)
	private LocationDivisionSubDivisonDetailsBean  locationDivisionSubDivisonDetailsBeanDivisionId;
	
	@OneToOne(targetEntity=SchemeCycleAttributeDetailBean.class)
	@JoinColumn(name="campaign_id",referencedColumnName="sch_attribute_dtl_id",insertable=false,updatable=false)
	private SchemeCycleAttributeDetailBean  schemeCycleAttributeDetailBeanCampaignId;
	
/*	@OneToOne(targetEntity=ActivityVillageMappingDetalBean.class)
	@JoinColumn(name="Village_activity_id",referencedColumnName="village_activity_id",insertable=false,updatable=false)
	private ActivityVillageMappingDetalBean  activityVillageMappingDetalBeans;*/

	@Column(name = "financial_year")
	private Integer financialYear;

	@Column(name = "division_id")
	private Integer divisionId;

	@Column(name = "stage_id")
	private Integer stageId;

	@Column(name = "component_id")
	private Integer componentId;

	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "campaign_id")
	private Integer campaignId;

	// village_id
	@Column(name = "village_id")
	private Integer villageId;

	@Column(name = "active_flag")
	private Integer activeFlag;

	@Column(name = "crt_by_usr")
	private Integer createdByUser;
	
	
	
	
	
	
	public ComboBeanDetails getComboBeanDetailsFinancialYear() {
		return comboBeanDetailsFinancialYear;
	}

	public void setComboBeanDetailsFinancialYear(ComboBeanDetails comboBeanDetailsFinancialYear) {
		this.comboBeanDetailsFinancialYear = comboBeanDetailsFinancialYear;
	}

	public ComboBeanDetails getComboBeanDetailsComponentId() {
		return comboBeanDetailsComponentId;
	}

	public void setComboBeanDetailsComponentId(ComboBeanDetails comboBeanDetailsComponentId) {
		this.comboBeanDetailsComponentId = comboBeanDetailsComponentId;
	}

	public ComboBeanDetails getComboBeanDetailsCategoryId() {
		return comboBeanDetailsCategoryId;
	}

	public void setComboBeanDetailsCategoryId(ComboBeanDetails comboBeanDetailsCategoryId) {
		this.comboBeanDetailsCategoryId = comboBeanDetailsCategoryId;
	}

	public StageDetailBean getStageDetailBeanStageId() {
		return stageDetailBeanStageId;
	}

	public void setStageDetailBeanStageId(StageDetailBean stageDetailBeanStageId) {
		this.stageDetailBeanStageId = stageDetailBeanStageId;
	}

	public LocationDetailsBean getLocationDetailsBeanVillageId() {
		return locationDetailsBeanVillageId;
	}

	public void setLocationDetailsBeanVillageId(LocationDetailsBean locationDetailsBeanVillageId) {
		this.locationDetailsBeanVillageId = locationDetailsBeanVillageId;
	}

	public LocationDivisionSubDivisonDetailsBean getLocationDivisionSubDivisonDetailsBeanDivisionId() {
		return locationDivisionSubDivisonDetailsBeanDivisionId;
	}

	public void setLocationDivisionSubDivisonDetailsBeanDivisionId(
			LocationDivisionSubDivisonDetailsBean locationDivisionSubDivisonDetailsBeanDivisionId) {
		this.locationDivisionSubDivisonDetailsBeanDivisionId = locationDivisionSubDivisonDetailsBeanDivisionId;
	}

	/*public SchemeCycleAttributeDetailBean getSchemeCycleAttributeDetailBeanCampaignId() {
		return schemeCycleAttributeDetailBeanCampaignId;
	}

	public void setSchemeCycleAttributeDetailBeanCampaignId(
			SchemeCycleAttributeDetailBean schemeCycleAttributeDetailBeanCampaignId) {
		this.schemeCycleAttributeDetailBeanCampaignId = schemeCycleAttributeDetailBeanCampaignId;
	}*/

	public Integer getActivityVillageId() {
		return activityVillageId;
	}

	public void setActivityVillageId(Integer activityVillageId) {
		this.activityVillageId = activityVillageId;
	}

	public Integer getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(Integer financialYear) {
		this.financialYear = financialYear;
	}

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
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
	
	
	

	public SchemeCycleAttributeDetailBean getSchemeCycleAttributeDetailBeanCampaignId() {
		return schemeCycleAttributeDetailBeanCampaignId;
	}

	public void setSchemeCycleAttributeDetailBeanCampaignId(
			SchemeCycleAttributeDetailBean schemeCycleAttributeDetailBeanCampaignId) {
		this.schemeCycleAttributeDetailBeanCampaignId = schemeCycleAttributeDetailBeanCampaignId;
	}

	@Override
	public String toString() {
		return "ActivityVillageMappingBean [activityVillageId=" + activityVillageId + ", comboBeanDetailsFinancialYear="
				+ comboBeanDetailsFinancialYear + ", comboBeanDetailsComponentId=" + comboBeanDetailsComponentId
				+ ", comboBeanDetailsCategoryId=" + comboBeanDetailsCategoryId + ", stageDetailBeanStageId="
				+ stageDetailBeanStageId + ", locationDetailsBeanVillageId=" + locationDetailsBeanVillageId
				+ ", locationDivisionSubDivisonDetailsBeanDivisionId=" + locationDivisionSubDivisonDetailsBeanDivisionId
				+ ", schemeCycleAttributeDetailBeanCampaignId=" + schemeCycleAttributeDetailBeanCampaignId
				+ ", financialYear=" + financialYear + ", divisionId=" + divisionId + ", stageId=" + stageId
				+ ", componentId=" + componentId + ", categoryId=" + categoryId + ", campaignId=" + campaignId
				+ ", villageId=" + villageId + ", activeFlag=" + activeFlag + ", createdByUser=" + createdByUser + "]";
	}

	

	
	
	

}

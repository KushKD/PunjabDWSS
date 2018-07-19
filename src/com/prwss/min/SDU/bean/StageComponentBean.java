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
import com.prwss.mis.common.util.MISConstants;


@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="sdu_plan_outline_stage_comp_mpg",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class StageComponentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6926711924754853847L;
	
	
	@Id
	@SequenceGenerator(name="sdu_plan_outline_stage_comp_mpg_stg_comp_mpg_id_seq",sequenceName="prwss_main.sdu_plan_outline_stage_comp_mpg_stg_comp_mpg_id_seq")
	@GeneratedValue(generator="sdu_plan_outline_stage_comp_mpg_stg_comp_mpg_id_seq",strategy=GenerationType.AUTO)
	@Column(name="stg_comp_mpg_id")
	private Integer stageComponentMappingId;
	
	@OneToOne(targetEntity=DivisionWiseSummaryBean.class)
	@JoinColumn(name="outline_plan_id",referencedColumnName="outline_plan_id",insertable=false,updatable=false)
	private DivisionWiseSummaryBean  divisionWiseSummaryBean;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="component_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailCompName;
	
	@OneToOne(targetEntity=StageDetailBean.class)
	@JoinColumn(name="stage_id",referencedColumnName="stage_id",insertable=false,updatable=false)
	private StageDetailBean  stageDetailBean;
	


	@Column(name="outline_plan_id")
	private Integer outlinePlanId;

	@Column(name="stage_id")
	private Integer stageId;

	@Column(name="component_id")
	private Integer componentId;

	@Column(name="village_no")
	private Integer numberVillages;

	@Column(name="active_flag")
	private Integer activeFlag;

	/*@Column (name="crt_date")
	private Date createdDate;*/

	@Column(name="crt_by_usr")
	private Integer createdByUser;

	public Integer getStageComponentMappingId() {
		return stageComponentMappingId;
	}

	public void setStageComponentMappingId(Integer stageComponentMappingId) {
		this.stageComponentMappingId = stageComponentMappingId;
	}

	public Integer getOutlinePlanId() {
		return outlinePlanId;
	}

	public void setOutlinePlanId(Integer outlinePlanId) {
		this.outlinePlanId = outlinePlanId;
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

	public Integer getNumberVillages() {
		return numberVillages;
	}

	public void setNumberVillages(Integer numberVillages) {
		this.numberVillages = numberVillages;
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

	
	
	
	
	public DivisionWiseSummaryBean getDivisionWiseSummaryBean() {
		return divisionWiseSummaryBean;
	}

	public void setDivisionWiseSummaryBean(DivisionWiseSummaryBean divisionWiseSummaryBean) {
		this.divisionWiseSummaryBean = divisionWiseSummaryBean;
	}

	public ComboBeanDetails getCombodetailCompName() {
		return combodetailCompName;
	}

	public void setCombodetailCompName(ComboBeanDetails combodetailCompName) {
		this.combodetailCompName = combodetailCompName;
	}

	public StageDetailBean getStageDetailBean() {
		return stageDetailBean;
	}

	public void setStageDetailBean(StageDetailBean stageDetailBean) {
		this.stageDetailBean = stageDetailBean;
	}

	@Override
	public String toString() {
		return "StageComponentBean [stageComponentMappingId=" + stageComponentMappingId + ", divisionWiseSummaryBean="
				+ divisionWiseSummaryBean + ", combodetailCompName=" + combodetailCompName + ", outlinePlanId="
				+ outlinePlanId + ", stageId=" + stageId + ", componentId=" + componentId + ", numberVillages="
				+ numberVillages + ", activeFlag=" + activeFlag + ", createdByUser=" + createdByUser + "]";
	}

	
	
	
	
	
	
	

}

/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="san_survey_review_mapping",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SurveyReviewMapping implements Serializable{

	private static final long serialVersionUID = 363501234515L;

	@Id
	@Column(name="san_survey_review_id")
	private int surveyReviewId;
	
	
	@ManyToOne(targetEntity=SurveyMasterBean.class,fetch=FetchType.LAZY)
	@JoinColumn(name="survey_id",referencedColumnName="survey_id", insertable=false, updatable=false)
	private SurveyMasterBean surveyMasterBean;
	
	
	

	@Column(name="survey_id")
	private Integer surveyId;
	
	@Column(name="location_dist")
	private Integer districtId;

	
	@Column(name="location_block")
	private Integer blockId;
	
	@Column(name="location_gp")
	private String gramPanchayatId;
	
	@Column(name="location_vill")
	private Integer villageId;
	
	@Column(name="crt_by_usr")
	private Integer crtByUsr;
	
	@Column(name="active_flag")
	private Integer activeFlag;

	@Column(name="review_authority")
	private Integer reviewAuthority;

	
	
	public SurveyMasterBean getSurveyMasterBean() {
		return surveyMasterBean;
	}

	public void setSurveyMasterBean(SurveyMasterBean surveyMasterBean) {
		this.surveyMasterBean = surveyMasterBean;
	}
	public int getSurveyReviewId() {
		return surveyReviewId;
	}

	public void setSurveyReviewId(int surveyReviewId) {
		this.surveyReviewId = surveyReviewId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}

	public String getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(String gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Integer getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Integer crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getReviewAuthority() {
		return reviewAuthority;
	}

	public void setReviewAuthority(Integer reviewAuthority) {
		this.reviewAuthority = reviewAuthority;
	}

	@Override
	public String toString() {
		return "SurveyReviewMapping [surveyReviewId=" + surveyReviewId + ", surveyId=" + surveyId + ", districtId="
				+ districtId + ", blockId=" + blockId + ", gramPanchayatId=" + gramPanchayatId + ", villageId="
				+ villageId + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + ", reviewAuthority="
				+ reviewAuthority + "]";
	}
	
	
}

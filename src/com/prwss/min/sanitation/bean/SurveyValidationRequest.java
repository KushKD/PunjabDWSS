/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="san_validation_request",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SurveyValidationRequest implements Serializable{
	
	private static final long serialVersionUID = 36350543215L;

	@Id
	@GeneratedValue(generator="validation_request_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="validation_request_id_seq",sequenceName="prwss_main.validation_request_id_seq")
	@Column(name="validation_request_id")
	private int validationRequestId;
	
	@ManyToOne(targetEntity=SurveyReviewMapping.class,fetch=FetchType.LAZY)
	@JoinColumn(name="survey_id",referencedColumnName="san_survey_review_id", insertable=false, updatable=false)
	private SurveyReviewMapping surveyReviewMapping;
	
	@Column(name="survey_id")
	private Integer surveyId;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="lying_with_usr")
	private Integer lyingWithUsr;
	
	@Column(name="crt_by_usr")
	private Integer crtByUsr;
	
	@Column(name="active_flag")
	private Integer activeFlag;

	@Column(name="issubmitted")
	private Integer isSubmitted;


	public Integer getIsSubmitted() {
		return isSubmitted;
	}

	public void setIsSubmitted(Integer isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

	public Integer getLyingWithUsr() {
		return lyingWithUsr;
	}

	public void setLyingWithUsr(Integer lyingWithUsr) {
		this.lyingWithUsr = lyingWithUsr;
	}

	public SurveyReviewMapping getSurveyReviewMapping() {
		return surveyReviewMapping;
	}

	public void setSurveyReviewMapping(SurveyReviewMapping surveyReviewMapping) {
		this.surveyReviewMapping = surveyReviewMapping;
	}

	public int getValidationRequestId() {
		return validationRequestId;
	}

	public void setValidationRequestId(int validationRequestId) {
		this.validationRequestId = validationRequestId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "SurveyValidationRequest [validationRequestId=" + validationRequestId + ", surveyReviewMapping="
				+ surveyReviewMapping + ", surveyId=" + surveyId + ", userId=" + userId + ", lyingWithUsr="
				+ lyingWithUsr + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}

}

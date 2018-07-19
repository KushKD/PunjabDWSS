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
import javax.persistence.OneToOne;
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
@Table(name="Benifeciery_Validation",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ValidateBeneficiaryBean implements Serializable {
	private static final long serialVersionUID = -36350340815L;
	@Id
	@GeneratedValue(generator="beneficiary_val_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="beneficiary_val_id_seq",sequenceName="prwss_main.beneficiary_val_id_seq")
	@Column(name="beneficiary_val_id")
	private int beneficiaryValId;
	
	
	@OneToOne(targetEntity=SurveyValidationRequest.class)
	@JoinColumn(name="validation_request_id", referencedColumnName="validation_request_id",insertable=false,updatable=false)
	private SurveyValidationRequest surveyValidationRequest;
	
	@ManyToOne(targetEntity=BeneficiaryEntryBean.class,fetch=FetchType.LAZY)
	@JoinColumn(name="beneficiaryid",referencedColumnName="beneficiery_id", insertable=false, updatable=false)
	private BeneficiaryEntryBean beneficiaryEntryBean;
	
	@Column(name="validation_request_id")
	private Integer validationRequestId;
	
	@Column(name="userid")
	private Integer userId;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="beneficiaryid")
	private Integer beneficiaryid;
	
	@Column(name="action")
	private String action;

	public SurveyValidationRequest getSurveyValidationRequest() {
		return surveyValidationRequest;
	}

	public void setSurveyValidationRequest(SurveyValidationRequest surveyValidationRequest) {
		this.surveyValidationRequest = surveyValidationRequest;
	}

	public BeneficiaryEntryBean getBeneficiaryEntryBean() {
		return beneficiaryEntryBean;
	}

	public void setBeneficiaryEntryBean(BeneficiaryEntryBean beneficiaryEntryBean) {
		this.beneficiaryEntryBean = beneficiaryEntryBean;
	}

	public int getBeneficiaryValId() {
		return beneficiaryValId;
	}

	public void setBeneficiaryValId(int beneficiaryValId) {
		this.beneficiaryValId = beneficiaryValId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	public Integer getBeneficiaryid() {
		return beneficiaryid;
	}

	public void setBeneficiaryid(Integer beneficiaryid) {
		this.beneficiaryid = beneficiaryid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getValidationRequestId() {
		return validationRequestId;
	}

	public void setValidationRequestId(Integer validationRequestId) {
		this.validationRequestId = validationRequestId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ValidateBeneficiaryBean [beneficiaryValId=" + beneficiaryValId + ", validationRequestId="
				+ validationRequestId + ", userId=" + userId + ", remarks=" + remarks + ", beneficiaryid="
				+ beneficiaryid + ", action=" + action + "]";
	}
	
}

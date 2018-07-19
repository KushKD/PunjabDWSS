/**
 * 
 */
package com.prwss.min.sanitation.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class ValidateBeneficiaryForm extends ValidatorForm implements Serializable{

	private static final long serialVersionUID = 9913762502058L;
	private String status;
	private String remarks;
	private String beneficiary;
	private String surveyId;
	private String LoginUser;
	
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}
	public String getLoginUser() {
		return LoginUser;
	}
	public void setLoginUser(String loginUser) {
		LoginUser = loginUser;
	}
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	@Override
	public String toString() {
		return "ValidateBeneficiaryForm [surveyId=" + surveyId + ", LoginUser=" + LoginUser + ", status=" + status
				+ ", remarks=" + remarks + ", beneficiary=" + beneficiary + "]";
	}
	
	
}

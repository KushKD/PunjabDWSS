/**
 * 
 */
package com.prwss.min.sanitation.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.prwss.min.sanitation.bean.BeneficiaryDto;

/**
 * @author BH390738
 *
 */
public class ValidateRequestForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = -991376302058L;
	private long loginUser;
	private List<BeneficiaryDto> beneficiaryDto;
	private String baselineSurveyId;
	private String status;
	private String remarks;
	private String test;
	private String surveyId;
	
	
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getBaselineSurveyId() {
		return baselineSurveyId;
	}
	public void setBaselineSurveyId(String baselineSurveyId) {
		this.baselineSurveyId = baselineSurveyId;
	}
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
	public long getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(long loginUser) {
		this.loginUser = loginUser;
	}

//	public List<BeneficiaryDto> getBeneficiaryDto() {
//		return beneficiaryDto;
//	}
//
//	public void setBeneficiaryDto(List<BeneficiaryDto> beneficiaryDto) {
//		this.beneficiaryDto = beneficiaryDto;
//	}
	
	
	public List<BeneficiaryDto> getBeneficiaryDto() {
		return beneficiaryDto;
	}
	public void setBeneficiaryDto(List<BeneficiaryDto> beneficiaryDto) {
		this.beneficiaryDto = beneficiaryDto;
	}

	public void setBeneficiaryLst(int index, BeneficiaryDto value) {
		System.out.println("---------inside setBeneficiaryDto---------"+index);
		this.beneficiaryDto.add(index,value);
	}


	public BeneficiaryDto getBeneficiaryLst(int index) {
		
		System.out.println("----------index getBeneficiaryDto size----------"+index);
		int size = beneficiaryDto.size();
		while (index >= size) {
			beneficiaryDto.add(new BeneficiaryDto());
			size = beneficiaryDto.size();
		}
		return this.beneficiaryDto.get(index);
	}

	@Override
	public String toString() {
		return "ValidateBeneficiaryForm [loginUser=" + loginUser + ", beneficiaryDto=" + beneficiaryDto
				+ ", baselineSurveyId=" + baselineSurveyId + ", status=" + status + ", remarks=" + remarks + "]";
	}
	
}

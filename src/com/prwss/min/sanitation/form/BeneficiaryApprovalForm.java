/**
 * 
 */
package com.prwss.min.sanitation.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.prwss.min.sanitation.bean.BeneficiaryDto;

/**
 * @author BH390738
 *
 */
public class BeneficiaryApprovalForm extends ActionForm{
	
	private static final long serialVersionUID = -3443023543058L;
	
	private String surveyId;
	private int entBy;
	private List<BeneficiaryDto> beneficiaryDto;
	
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	public int getEntBy() {
		return entBy;
	}
	public void setEntBy(int entBy) {
		this.entBy = entBy;
	}
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
		return "BeneficiaryApprovalForm [surveyId=" + surveyId + ", entBy=" + entBy + ", beneficiaryDto="
				+ beneficiaryDto + "]";
	}
	
}

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
public class ForwardBeneficiaryForm extends ActionForm implements Serializable {
	private static final long serialVersionUID = -344302058L;
	private String surveyId;
	private int entBy;
	private List<BeneficiaryDto> beneficiaryDtos;
	
	public int getEntBy() {
		return entBy;
	}
	public void setEntBy(int entBy) {
		this.entBy = entBy;
	}
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	
	
	
	public List<BeneficiaryDto> getBeneficiaryDtos() {
		return beneficiaryDtos;
	}
	public void setBeneficiaryDtos(List<BeneficiaryDto> beneficiaryDtos) {
		this.beneficiaryDtos = beneficiaryDtos;
	}
	public void setBeneficiaryLsts(int index, BeneficiaryDto value) {
		System.out.println("---------inside setBeneficiaryDto---------"+index);
		this.beneficiaryDtos.add(index,value);
	}

	public BeneficiaryDto getBeneficiaryLsts(int index) {
		
		System.out.println("----------index getBeneficiaryDto size----------"+index);
		int size = beneficiaryDtos.size();
		while (index >= size) {
			beneficiaryDtos.add(new BeneficiaryDto());
			size = beneficiaryDtos.size();
		}
		return this.beneficiaryDtos.get(index);
	}
	@Override
	public String toString() {
		return "ForwardBeneficiaryForm [surveyId=" + surveyId + ", entBy=" + entBy + ", beneficiaryDto="
				+ beneficiaryDtos + "]";
	}
	
}

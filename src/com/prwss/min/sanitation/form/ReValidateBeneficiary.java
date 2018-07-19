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
public class ReValidateBeneficiary extends ActionForm {
	private static final long serialVersionUID = -344302058L;
	private String surveyId;
	private int entBy;
	private List<BeneficiaryDto> beneficiaryDto;
	private List<BeneficiaryDto> beneficiaryDtos;
	
	
	public List<BeneficiaryDto> getBeneficiaryDtos() {
		return beneficiaryDtos;
	}
	public void setBeneficiaryDtos(List<BeneficiaryDto> beneficiaryDtos) {
		this.beneficiaryDtos = beneficiaryDtos;
	}
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
	
	public void setBeneficiaryLsts(int index, BeneficiaryDto value) {
		System.out.println("---------inside setBeneficiaryDto---------"+index);
		this.beneficiaryDtos.add(index,value);
	}

	public BeneficiaryDto getBeneficiaryLsts(int index) {
		
		System.out.println("----------index getBeneficiaryDto size----------"+index);
		int size = beneficiaryDto.size();
		while (index >= size) {
			beneficiaryDtos.add(new BeneficiaryDto());
			size = beneficiaryDtos.size();
		}
		return this.beneficiaryDtos.get(index);
	}
	
	
}

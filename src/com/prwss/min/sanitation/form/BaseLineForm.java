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
public class BaseLineForm extends ActionForm{

	private static final long serialVersionUID = -8813762132344302058L;

	private String district;
	private long loginUser;
	private String block;
	private String village;
	private String surveyId;
	private String gramPanchayatId;
	private List<BeneficiaryDto> beneficiaryDto;
	private Long createdBy;
	


	
	
	public Long getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


	public String getDistrict() {
		return district;
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

	public void setDistrict(String district) {
		this.district = district;
	}

	public long getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(long loginUser) {
		this.loginUser = loginUser;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(String gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}
	
	
	

	@Override
	public String toString() {
		return "BaseLineForm [district=" + district + ", loginUser=" + loginUser + ", block=" + block + ", village="
				+ village + ", surveyId=" + surveyId + ", gramPanchayatId=" + gramPanchayatId + ", beneficiaryLst="
				+ beneficiaryDto + "]";
	}


}

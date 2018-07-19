package com.prwss.min.sanitation.form;

import org.apache.struts.validator.ValidatorForm;

public class ViewRegistrationsForm extends ValidatorForm {

	private static final long serialVersionUID = 991376212323302058L;
	
	private String district;
	private long loginUser;
	private String block;
	private String village;
	private String beneficiaryId;
	
	
	public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public long getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(long loginUser) {
		this.loginUser = loginUser;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
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
	@Override
	public String toString() {
		return "ViewRegistrationsForm [district=" + district + ", loginUser=" + loginUser + ", block=" + block
				+ ", village=" + village + ", beneficiaryId=" + beneficiaryId + "]";
	}
	
}

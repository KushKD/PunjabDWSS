package com.prwss.min.sanitation.bean;

import org.apache.struts.validator.ValidatorForm;

public class GramPanchayatDto extends ValidatorForm{
	private static final long serialVersionUID = -8813762114725832058L;
	
	private String nameofGramPanchayat;
	private String district;
	private String block;
	private String village;
	private String gramPanchayatId;
	private String status;
	
	
	


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNameofGramPanchayat() {
		return nameofGramPanchayat;
	}
	public void setNameofGramPanchayat(String nameofGramPanchayat) {
		this.nameofGramPanchayat = nameofGramPanchayat;
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
	public String getGramPanchayatId() {
		return gramPanchayatId;
	}
	public void setGramPanchayatId(String gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}
	
	
}
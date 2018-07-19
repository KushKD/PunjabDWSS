package com.prwss.min.sanitation.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class ProgressofWorkForm extends ValidatorForm{
	private static final long serialVersionUID = -8813762852444302058L;
	
	private String beneficiaryId;
	private FormFile photograph1;
	private FormFile photograph2;
	private FormFile photograph3;
	private String district;
	private String village;
	private String block;
	private String gramPanchayat;
	private Long createdByUser;
	private String progressWorkId;
	private String progressStageId;
	private String activeFlag;
	private String status1;
	private String status2;
	private String status3;
	
	
	
	

	public String getProgressStageId() {
		return progressStageId;
	}
	public void setProgressStageId(String progressStageId) {
		this.progressStageId = progressStageId;
	}
	public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public FormFile getPhotograph1() {
		return photograph1;
	}
	public void setPhotograph1(FormFile photograph1) {
		this.photograph1 = photograph1;
	}
	public FormFile getPhotograph2() {
		return photograph2;
	}
	public void setPhotograph2(FormFile photograph2) {
		this.photograph2 = photograph2;
	}
	public FormFile getPhotograph3() {
		return photograph3;
	}
	public void setPhotograph3(FormFile photograph3) {
		this.photograph3 = photograph3;
	}
	public Long getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(Long createdByUser) {
		this.createdByUser = createdByUser;
	}
	public String getProgressWorkId() {
		return progressWorkId;
	}
	public void setProgressWorkId(String progressWorkId) {
		this.progressWorkId = progressWorkId;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getVillage() {
		return village;
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public String getStatus2() {
		return status2;
	}
	public void setStatus2(String status2) {
		this.status2 = status2;
	}
	public String getStatus3() {
		return status3;
	}
	public void setStatus3(String status3) {
		this.status3 = status3;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getGramPanchayat() {
		return gramPanchayat;
	}
	public void setGramPanchayat(String gramPanchayat) {
		this.gramPanchayat = gramPanchayat;
	}


}

package com.prwss.min.sanitation.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class BeneficiaryForm extends ValidatorForm{
	private static final long serialVersionUID = -8813762132344302058L;
	
	private String personName;
	private String fatherSpouseName;
	private String benifCategory;
	private String cast;
	private String religion;
	private String phoneNumber;
	private String gender;
	private FormFile photograph;
	private String district;
	private String village;
	private String block;
	private String gramPanchayat;
	private String poiType;
	private String poiNumber;
	private String adharNumber;
	private String electricityCon;
	private String bankName;
	private String branch;
	private String accountNo;
	private String ifsCode;
	private FormFile electricityBill;
	private long loginUser;
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
	public FormFile getPhotograph() {
		return photograph;
	}
	public void setPhotograph(FormFile photograph) {
		this.photograph = photograph;
	}
	public FormFile getElectricityBill() {
		return electricityBill;
	}
	public void setElectricityBill(FormFile electricityBill) {
		this.electricityBill = electricityBill;
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
	public String getPoiType() {
		return poiType;
	}
	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}
	public String getPoiNumber() {
		return poiNumber;
	}
	public void setPoiNumber(String poiNumber) {
		this.poiNumber = poiNumber;
	}
	public String getAdharNumber() {
		return adharNumber;
	}
	public void setAdharNumber(String adharNumber) {
		this.adharNumber = adharNumber;
	}
	public String getElectricityCon() {
		return electricityCon;
	}
	public void setElectricityCon(String electricityCon) {
		this.electricityCon = electricityCon;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getIfsCode() {
		return ifsCode;
	}
	public void setIfsCode(String ifsCode) {
		this.ifsCode = ifsCode;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getFatherSpouseName() {
		return fatherSpouseName;
	}
	public void setFatherSpouseName(String fatherSpouseName) {
		this.fatherSpouseName = fatherSpouseName;
	}
	public String getBenifCategory() {
		return benifCategory;
	}
	public void setBenifCategory(String benifCategory) {
		this.benifCategory = benifCategory;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "BeneficiaryForm [personName=" + personName + ", fatherSpouseName=" + fatherSpouseName
				+ ", benifCategory=" + benifCategory + ", cast=" + cast + ", religion=" + religion + ", phoneNumber="
				+ phoneNumber + ", gender=" + gender + ", photograph=" + photograph + ", district=" + district
				+ ", village=" + village + ", block=" + block + ", gramPanchayat=" + gramPanchayat + ", poiType="
				+ poiType + ", poiNumber=" + poiNumber + ", adharNumber=" + adharNumber + ", electricityCon="
				+ electricityCon + ", bankName=" + bankName + ", branch=" + branch + ", accountNo=" + accountNo
				+ ", ifsCode=" + ifsCode + ", electricityBill=" + electricityBill + ", loginUser=" + loginUser + "]";
	}

}

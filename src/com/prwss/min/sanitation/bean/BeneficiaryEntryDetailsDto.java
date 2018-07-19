/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.util.Arrays;

/**
 * @author BH390738
 *
 */
public class BeneficiaryEntryDetailsDto {
	private int beneficiaryId;
	private String casteName;
	private String gender;
	private String category;
	private String religion;
	private String poiType;
	private String bankName;
	private String beneficieryName;
	private String fatHusName;
	private Long phoneNo;
	private String villageName;
	private String districtName;
	private String blockName;
	private String photographName;
	private String gramPanchayatId;
	private String poiId;
	private Long aadhaarNumber;
	private String electConnNumber;
	private String electBill;
	private String branchName;
	private Long accountNumber;
	private String ifscCode;
	private byte[] photograp;
	private String image;
	private byte[] elecConData;
	
	
	public byte[] getElecConData() {
		return elecConData;
	}
	public void setElecConData(byte[] elecConData) {
		this.elecConData = elecConData;
	}
	public int getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(int beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getPoiType() {
		return poiType;
	}
	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBeneficieryName() {
		return beneficieryName;
	}
	public void setBeneficieryName(String beneficieryName) {
		this.beneficieryName = beneficieryName;
	}
	public String getFatHusName() {
		return fatHusName;
	}
	public void setFatHusName(String fatHusName) {
		this.fatHusName = fatHusName;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String vaillageName) {
		this.villageName = vaillageName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getPhotographName() {
		return photographName;
	}
	public void setPhotographName(String photographName) {
		this.photographName = photographName;
	}
	public String getGramPanchayatId() {
		return gramPanchayatId;
	}
	public void setGramPanchayatId(String gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}
	public String getPoiId() {
		return poiId;
	}
	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}
	
	public Long getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(Long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public String getElectConnNumber() {
		return electConnNumber;
	}
	public void setElectConnNumber(String electConnNumber) {
		this.electConnNumber = electConnNumber;
	}
	public String getElectBill() {
		return electBill;
	}
	public void setElectBill(String electBill) {
		this.electBill = electBill;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public byte[] getPhotograp() {
		return photograp;
	}
	public void setPhotograp(byte[] photograp) {
		this.photograp = photograp;
	}
	
	@Override
	public String toString() {
		return "BeneficiaryEntryDetailsDto [casteName=" + casteName + ", gender=" + gender + ", category=" + category
				+ ", religion=" + religion + ", poiType=" + poiType + ", bankName=" + bankName + ", beneficieryName="
				+ beneficieryName + ", fatHusName=" + fatHusName + ", phoneNo=" + phoneNo + ", vaillageName="
				+ villageName + ", districtName=" + districtName + ", blockName=" + blockName + ", photographName="
				+ photographName + ", gramPanchayatId=" + gramPanchayatId + ", poiId=" + poiId + ", aadhaarNumber="
				+ aadhaarNumber + ", electConnNumber=" + electConnNumber + ", electBill=" + electBill + ", branchName="
				+ branchName + ", accountNumber=" + accountNumber + ", ifscCode=" + ifscCode + ", photograp="
				+ Arrays.toString(photograp) + ", image=" + image
				+ "]";
	}

	
}

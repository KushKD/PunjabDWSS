/**
 * 
 */
package com.prwss.min.sanitation.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class MotivatorEntryForm extends ValidatorForm{

	private static final long serialVersionUID = -8813762132344302058L;
	
	private String personName;
	private String fatherSpouseName;
	private String benifCategory;
	private String religion;
	private String phoneNumber;
	private String gender;
	private FormFile photograph;
	
	private String poiType;
	private String poiNumber;
	private String adharNumber;
	
	private String bankName;
	private String branch;
	private String accountNo;
	private String ifsCode;
	
	
	private String dob;
	private String emailId;
	private String districtCorsp;
	private String blockCorsp;
	private String villageCorsp;
	private String locationAreaCorsp;
	private String streetCorsp;
	private String landMarkCorsp;
	private String houseNumberCorsp;
	private String pincodeCorsp;
	private String districtPermanent;
	private String blockPermanent;
	private String villagePermanent;
	private String locationAreaPermanent;
	private String streetPermanent;
	private String landMarkPermanent;
	private String houseNumberPermanent;
	private String pincodePermanent;
	
	private String highPassedYear;
	private String highTotalMarks;
	private String highObtainedMarks;
	private String highBoardUniversity;
	private String interPassedYear;
	private String interTotalMarks;
	private String interObtainedMarks;
	private String interBoardUniversity;
	private String grdPassedYear;
	private String grdTotalMarks;
	private String grdObtainedMarks;
	private String grdBoardUniversity;
	private String pgrdPassedYear;
	private String pgrdTotalMarks;
	private String pgrdObtainedMarks;
	private String pgrdBoardUniversity;
	private String examHighschool;
	private String examIntermediate;
	private String examGraduation;
	private String examPGraduation;
	
	
	
	public String getExamHighschool() {
		return examHighschool;
	}

	public void setExamHighschool(String examHighschool) {
		this.examHighschool = examHighschool;
	}

	public String getExamIntermediate() {
		return examIntermediate;
	}

	public void setExamIntermediate(String examIntermediate) {
		this.examIntermediate = examIntermediate;
	}

	public String getExamGraduation() {
		return examGraduation;
	}

	public void setExamGraduation(String examGraduation) {
		this.examGraduation = examGraduation;
	}

	public String getExamPGraduation() {
		return examPGraduation;
	}

	public void setExamPGraduation(String examPGraduation) {
		this.examPGraduation = examPGraduation;
	}

	private long loginUser;

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

	public FormFile getPhotograph() {
		return photograph;
	}

	public void setPhotograph(FormFile photograph) {
		this.photograph = photograph;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDistrictCorsp() {
		return districtCorsp;
	}

	public void setDistrictCorsp(String districtCorsp) {
		this.districtCorsp = districtCorsp;
	}

	public String getBlockCorsp() {
		return blockCorsp;
	}

	public void setBlockCorsp(String blockCorsp) {
		this.blockCorsp = blockCorsp;
	}

	public String getVillageCorsp() {
		return villageCorsp;
	}

	public void setVillageCorsp(String villageCorsp) {
		this.villageCorsp = villageCorsp;
	}

	public String getLocationAreaCorsp() {
		return locationAreaCorsp;
	}

	public void setLocationAreaCorsp(String locationAreaCorsp) {
		this.locationAreaCorsp = locationAreaCorsp;
	}

	public String getStreetCorsp() {
		return streetCorsp;
	}

	public void setStreetCorsp(String streetCorsp) {
		this.streetCorsp = streetCorsp;
	}

	public String getLandMarkCorsp() {
		return landMarkCorsp;
	}

	public void setLandMarkCorsp(String landMarkCorsp) {
		this.landMarkCorsp = landMarkCorsp;
	}

	public String getHouseNumberCorsp() {
		return houseNumberCorsp;
	}

	public void setHouseNumberCorsp(String houseNumberCorsp) {
		this.houseNumberCorsp = houseNumberCorsp;
	}

	public String getPincodeCorsp() {
		return pincodeCorsp;
	}

	public void setPincodeCorsp(String pincodeCorsp) {
		this.pincodeCorsp = pincodeCorsp;
	}

	public String getDistrictPermanent() {
		return districtPermanent;
	}

	public void setDistrictPermanent(String districtPermanent) {
		this.districtPermanent = districtPermanent;
	}

	public String getBlockPermanent() {
		return blockPermanent;
	}

	public void setBlockPermanent(String blockPermanent) {
		this.blockPermanent = blockPermanent;
	}

	public String getVillagePermanent() {
		return villagePermanent;
	}

	public void setVillagePermanent(String villagePermanent) {
		this.villagePermanent = villagePermanent;
	}

	public String getLocationAreaPermanent() {
		return locationAreaPermanent;
	}

	public void setLocationAreaPermanent(String locationAreaPermanent) {
		this.locationAreaPermanent = locationAreaPermanent;
	}

	public String getStreetPermanent() {
		return streetPermanent;
	}

	public void setStreetPermanent(String streetPermanent) {
		this.streetPermanent = streetPermanent;
	}

	public String getLandMarkPermanent() {
		return landMarkPermanent;
	}

	public void setLandMarkPermanent(String landMarkPermanent) {
		this.landMarkPermanent = landMarkPermanent;
	}

	public String getHouseNumberPermanent() {
		return houseNumberPermanent;
	}

	public void setHouseNumberPermanent(String houseNumberPermanent) {
		this.houseNumberPermanent = houseNumberPermanent;
	}

	public String getPincodePermanent() {
		return pincodePermanent;
	}

	public void setPincodePermanent(String pincodePermanent) {
		this.pincodePermanent = pincodePermanent;
	}

	public String getHighPassedYear() {
		return highPassedYear;
	}

	public void setHighPassedYear(String highPassedYear) {
		this.highPassedYear = highPassedYear;
	}

	public String getHighTotalMarks() {
		return highTotalMarks;
	}

	public void setHighTotalMarks(String highTotalMarks) {
		this.highTotalMarks = highTotalMarks;
	}

	public String getHighObtainedMarks() {
		return highObtainedMarks;
	}

	public void setHighObtainedMarks(String highObtainedMarks) {
		this.highObtainedMarks = highObtainedMarks;
	}

	public String getHighBoardUniversity() {
		return highBoardUniversity;
	}

	public void setHighBoardUniversity(String highBoardUniversity) {
		this.highBoardUniversity = highBoardUniversity;
	}

	public String getInterPassedYear() {
		return interPassedYear;
	}

	public void setInterPassedYear(String interPassedYear) {
		this.interPassedYear = interPassedYear;
	}

	public String getInterTotalMarks() {
		return interTotalMarks;
	}

	public void setInterTotalMarks(String interTotalMarks) {
		this.interTotalMarks = interTotalMarks;
	}

	public String getInterObtainedMarks() {
		return interObtainedMarks;
	}

	public void setInterObtainedMarks(String interObtainedMarks) {
		this.interObtainedMarks = interObtainedMarks;
	}

	public String getInterBoardUniversity() {
		return interBoardUniversity;
	}

	public void setInterBoardUniversity(String interBoardUniversity) {
		this.interBoardUniversity = interBoardUniversity;
	}

	public String getGrdPassedYear() {
		return grdPassedYear;
	}

	public void setGrdPassedYear(String grdPassedYear) {
		this.grdPassedYear = grdPassedYear;
	}

	public String getGrdTotalMarks() {
		return grdTotalMarks;
	}

	public void setGrdTotalMarks(String grdTotalMarks) {
		this.grdTotalMarks = grdTotalMarks;
	}

	public String getGrdObtainedMarks() {
		return grdObtainedMarks;
	}

	public void setGrdObtainedMarks(String grdObtainedMarks) {
		this.grdObtainedMarks = grdObtainedMarks;
	}

	public String getGrdBoardUniversity() {
		return grdBoardUniversity;
	}

	public void setGrdBoardUniversity(String grdBoardUniversity) {
		this.grdBoardUniversity = grdBoardUniversity;
	}

	public String getPgrdPassedYear() {
		return pgrdPassedYear;
	}

	public void setPgrdPassedYear(String pgrdPassedYear) {
		this.pgrdPassedYear = pgrdPassedYear;
	}

	public String getPgrdTotalMarks() {
		return pgrdTotalMarks;
	}

	public void setPgrdTotalMarks(String pgrdTotalMarks) {
		this.pgrdTotalMarks = pgrdTotalMarks;
	}

	public String getPgrdObtainedMarks() {
		return pgrdObtainedMarks;
	}

	public void setPgrdObtainedMarks(String pgrdObtainedMarks) {
		this.pgrdObtainedMarks = pgrdObtainedMarks;
	}

	public String getPgrdBoardUniversity() {
		return pgrdBoardUniversity;
	}

	public void setPgrdBoardUniversity(String pgrdBoardUniversity) {
		this.pgrdBoardUniversity = pgrdBoardUniversity;
	}

	public long getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(long loginUser) {
		this.loginUser = loginUser;
	}

	@Override
	public String toString() {
		return "MotivatorEntryForm [personName=" + personName + ", fatherSpouseName=" + fatherSpouseName
				+ ", benifCategory=" + benifCategory + ", religion=" + religion + ", phoneNumber=" + phoneNumber
				+ ", gender=" + gender + ", photograph=" + photograph + ", poiType=" + poiType + ", poiNumber="
				+ poiNumber + ", adharNumber=" + adharNumber + ", bankName=" + bankName + ", branch=" + branch
				+ ", accountNo=" + accountNo + ", ifsCode=" + ifsCode + ", dob=" + dob + ", emailId=" + emailId
				+ ", districtCorsp=" + districtCorsp + ", blockCorsp=" + blockCorsp + ", villageCorsp=" + villageCorsp
				+ ", locationAreaCorsp=" + locationAreaCorsp + ", streetCorsp=" + streetCorsp + ", landMarkCorsp="
				+ landMarkCorsp + ", houseNumberCorsp=" + houseNumberCorsp + ", pincodeCorsp=" + pincodeCorsp
				+ ", districtPermanent=" + districtPermanent + ", blockPermanent=" + blockPermanent
				+ ", villagePermanent=" + villagePermanent + ", locationAreaPermanent=" + locationAreaPermanent
				+ ", streetPermanent=" + streetPermanent + ", landMarkPermanent=" + landMarkPermanent
				+ ", houseNumberPermanent=" + houseNumberPermanent + ", pincodePermanent=" + pincodePermanent
				+ ", highPassedYear=" + highPassedYear + ", highTotalMarks=" + highTotalMarks + ", highObtainedMarks="
				+ highObtainedMarks + ", highBoardUniversity=" + highBoardUniversity + ", interPassedYear="
				+ interPassedYear + ", interTotalMarks=" + interTotalMarks + ", interObtainedMarks="
				+ interObtainedMarks + ", interBoardUniversity=" + interBoardUniversity + ", grdPassedYear="
				+ grdPassedYear + ", grdTotalMarks=" + grdTotalMarks + ", grdObtainedMarks=" + grdObtainedMarks
				+ ", grdBoardUniversity=" + grdBoardUniversity + ", pgrdPassedYear=" + pgrdPassedYear
				+ ", pgrdTotalMarks=" + pgrdTotalMarks + ", pgrdObtainedMarks=" + pgrdObtainedMarks
				+ ", pgrdBoardUniversity=" + pgrdBoardUniversity + ", examHighschool=" + examHighschool
				+ ", examIntermediate=" + examIntermediate + ", examGraduation=" + examGraduation + ", examPGraduation="
				+ examPGraduation + ", loginUser=" + loginUser + "]";
	}
	
}

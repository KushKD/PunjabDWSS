/**
 * 
 */
package com.prwss.min.sanitation.bean;

/**
 * @author KU854963
 *
 */
public class GramPanchayatDetailsDto {


	private String villageName;
	private String habitationName;
	
	private String familyId;
	private String familyheadName;
	private String fatherHusbandName;
	private String cardType;
	private Long aadhaarNumber;
	
	private String gender;
	private String category;
	private String subcategory;
	private String caste;
	
	
	private String hadToiletBefore;
	private String collDate;
	private String functionalToilet;
	private String havingFunctionalToilet;
	private String waterfacility;
	private String isCovered;
	private String toiletType;
	private String remarks;
	
	private String createdByUser;
	private String locationId;
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getHabitationName() {
		return habitationName;
	}
	public void setHabitationName(String habitationName) {
		this.habitationName = habitationName;
	}
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public String getFamilyheadName() {
		return familyheadName;
	}
	public void setFamilyheadName(String familyheadName) {
		this.familyheadName = familyheadName;
	}
	public String getFatherHusbandName() {
		return fatherHusbandName;
	}
	public void setFatherHusbandName(String fatherHusbandName) {
		this.fatherHusbandName = fatherHusbandName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getHadToiletBefore() {
		return hadToiletBefore;
	}
	public void setHadToiletBefore(String hadToiletBefore) {
		this.hadToiletBefore = hadToiletBefore;
	}
	public String getCollDate() {
		return collDate;
	}
	public void setCollDate(String collDate) {
		this.collDate = collDate;
	}
	public String getFunctionalToilet() {
		return functionalToilet;
	}
	public void setFunctionalToilet(String functionalToilet) {
		this.functionalToilet = functionalToilet;
	}
	public String getHavingFunctionalToilet() {
		return havingFunctionalToilet;
	}
	public void setHavingFunctionalToilet(String havingFunctionalToilet) {
		this.havingFunctionalToilet = havingFunctionalToilet;
	}
	public String getWaterfacility() {
		return waterfacility;
	}
	public void setWaterfacility(String waterfacility) {
		this.waterfacility = waterfacility;
	}
	public String getIsCovered() {
		return isCovered;
	}
	public void setIsCovered(String isCovered) {
		this.isCovered = isCovered;
	}
	public String getToiletType() {
		return toiletType;
	}
	public void setToiletType(String toiletType) {
		this.toiletType = toiletType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public Long getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(Long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	@Override
	public String toString() {
		return "GramPanchayatDetailsDto [villageName=" + villageName + ", habitationName=" + habitationName
				+ ", familyId=" + familyId + ", familyheadName=" + familyheadName + ", fatherHusbandName="
				+ fatherHusbandName + ", cardType=" + cardType + ", aadhaarNumber=" + aadhaarNumber + ", gender="
				+ gender + ", category=" + category + ", subcategory=" + subcategory + ", caste=" + caste
				+ ", hadToiletBefore=" + hadToiletBefore + ", collDate=" + collDate + ", functionalToilet="
				+ functionalToilet + ", havingFunctionalToilet=" + havingFunctionalToilet + ", waterfacility="
				+ waterfacility + ", isCovered=" + isCovered + ", toiletType=" + toiletType + ", remarks=" + remarks
				+ ", createdByUser=" + createdByUser + ", locationId=" + locationId + "]";
	}

	
}

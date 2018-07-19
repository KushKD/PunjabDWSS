/**
 * 
 */
package com.prwss.min.quality;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author bhsingh
 *
 */
public class ReceiveSampleForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331106012338618776L;
	
	private String collectedBy;
	private String designation;
	private String mobileNo;
	private String landLineNo;
	private String schemeName;
	private String schemeStatus;
	private String district;
	private String tehsil;
	private String villageId;
	private String block;
	private String gramPanchayat;
	private String habitation;
	private String waterSource;
	private String collectionType;
	private String sampleNumber;
	private String testType;
	private String poiType;
	private String status;
	private	String locationId;
	private long createdBy;
	private	String emailDwss;
	private	String mobileNoDwss;
	
	
	
	private  String enteredBy;
	private  String lab;
	private String email;
	private String poiId;
	private String collDate;
	private String sampleId;
	private String landMark;
	
	/**
	 * New Fields
	 */
	private String urban;
	private String rural;
	private String city;
	private String sampleotherdetails;
	private String bottleType;
	private String letterRefNum;
	private String quantity;
	private String depth;
	private String schemeNameUrban;
	private String waterSourceUrban;
	
	
	/*
	 * KD Added Two Fiels
	 */
	private String labName;
	private String waterSourceName;
	
	public String getWaterSourceName() {
		return waterSourceName;
	}

	public void setWaterSourceName(String waterSourceName) {
		this.waterSourceName = waterSourceName;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}
	
	
	/**
	 * KD Added Two Fields Done for getting the name of the lab and water source
	 * @return
	 */
	
	
	
	
	

	
	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	/**
	 * @return the sampleId
	 */
	public String getSampleId() {
		return sampleId;
	}

	/**
	 * @param sampleId the sampleId to set
	 */
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	/**
	 * @return the emailDwss
	 */
	public String getEmailDwss() {
		return emailDwss;
	}

	/**
	 * @param emailDwss the emailDwss to set
	 */
	public void setEmailDwss(String emailDwss) {
		this.emailDwss = emailDwss;
	}

	/**
	 * @return the mobileNoDwss
	 */
	public String getMobileNoDwss() {
		return mobileNoDwss;
	}

	/**
	 * @param mobileNoDwss the mobileNoDwss to set
	 */
	public void setMobileNoDwss(String mobileNoDwss) {
		this.mobileNoDwss = mobileNoDwss;
	}

	/**
	 * @return the collDate
	 */
	public String getCollDate() {
		return collDate;
	}

	/**
	 * @param collDate the collDate to set
	 */
	public void setCollDate(String collDate) {
		this.collDate = collDate;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the poiId
	 */
	public String getPoiId() {
		return poiId;
	}

	/**
	 * @param poiId the poiId to set
	 */
	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}

	/**
	 * @return the enteredBy
	 */
	public String getEnteredBy() {
		return enteredBy;
	}

	/**
	 * @param enteredBy the enteredBy to set
	 */
	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	/**
	 * @return the lab
	 */
	public String getLab() {
		return lab;
	}

	/**
	 * @param lab the lab to set
	 */
	public void setLab(String lab) {
		this.lab = lab;
	}

	/**
	 * @return the createdBy
	 */
	public long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the collectionType
	 */
	public String getCollectionType() {
		return collectionType;
	}

	/**
	 * @param collectionType the collectionType to set
	 */
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	/**
	 * @return the sampleNumber
	 */
	public String getSampleNumber() {
		return sampleNumber;
	}

	/**
	 * @param sampleNumber the sampleNumber to set
	 */
	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	/**
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}

	/**
	 * @param testType the testType to set
	 */
	public void setTestType(String testType) {
		this.testType = testType;
	}

	/**
	 * @return the poiType
	 */
	public String getPoiType() {
		return poiType;
	}

	/**
	 * @param poiType the poiType to set
	 */
	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	//address
	private String address;

	
	
	
	
	/**
	 * @return the waterSource
	 */
	public String getWaterSource() {
		return waterSource;
	}

	/**
	 * @param waterSource the waterSource to set
	 */
	public void setWaterSource(String waterSource) {
		this.waterSource = waterSource;
	}

	/**
	 * @return the collectedBy
	 */
	public String getCollectedBy() {
		return collectedBy;
	}

	/**
	 * @param collectedBy the collectedBy to set
	 */
	public void setCollectedBy(String collectedBy) {
		this.collectedBy = collectedBy;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the landLineNo
	 */
	public String getLandLineNo() {
		return landLineNo;
	}

	/**
	 * @param landLineNo the landLineNo to set
	 */
	public void setLandLineNo(String landLineNo) {
		this.landLineNo = landLineNo;
	}

	/**
	 * @return the schemeName
	 */
	public String getSchemeName() {
		return schemeName;
	}

	/**
	 * @param schemeName the schemeName to set
	 */
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	/**
	 * @return the schemeStatus
	 */
	public String getSchemeStatus() {
		return schemeStatus;
	}

	/**
	 * @param schemeStatus the schemeStatus to set
	 */
	public void setSchemeStatus(String schemeStatus) {
		this.schemeStatus = schemeStatus;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the tehsil
	 */
	public String getTehsil() {
		return tehsil;
	}

	/**
	 * @param tehsil the tehsil to set
	 */
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	/**
	 * @return the villageId
	 */
	public String getVillageId() {
		return villageId;
	}

	/**
	 * @param villageId the villageId to set
	 */
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	/**
	 * @return the block
	 */
	public String getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(String block) {
		this.block = block;
	}

	/**
	 * @return the gramPanchayat
	 */
	public String getGramPanchayat() {
		return gramPanchayat;
	}

	/**
	 * @param gramPanchayat the gramPanchayat to set
	 */
	public void setGramPanchayat(String gramPanchayat) {
		this.gramPanchayat = gramPanchayat;
	}

	/**
	 * @return the habitation
	 */
	public String getHabitation() {
		return habitation;
	}

	/**
	 * @param habitation the habitation to set
	 */
	public void setHabitation(String habitation) {
		this.habitation = habitation;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	

	public String getUrban() {
		return urban;
	}

	public void setUrban(String urban) {
		this.urban = urban;
	}

	public String getRural() {
		return rural;
	}

	public void setRural(String rural) {
		this.rural = rural;
	}
	
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	

	public String getSampleotherdetails() {
		return sampleotherdetails;
	}

	public void setSampleotherdetails(String sampleotherdetails) {
		this.sampleotherdetails = sampleotherdetails;
	}
	
	

	public String getBottleType() {
		return bottleType;
	}

	public void setBottleType(String bottleType) {
		this.bottleType = bottleType;
	}

	public String getLetterRefNum() {
		return letterRefNum;
	}

	public void setLetterRefNum(String letterRefNum) {
		this.letterRefNum = letterRefNum;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getSchemeNameUrban() {
		return schemeNameUrban;
	}

	public void setSchemeNameUrban(String schemeNameUrban) {
		this.schemeNameUrban = schemeNameUrban;
	}

	public String getWaterSourceUrban() {
		return waterSourceUrban;
	}

	public void setWaterSourceUrban(String waterSourceUrban) {
		this.waterSourceUrban = waterSourceUrban;
	}

	@Override
	public String toString() {
		return "ReceiveSampleForm [collectedBy=" + collectedBy + ", designation=" + designation + ", mobileNo="
				+ mobileNo + ", landLineNo=" + landLineNo + ", schemeName=" + schemeName + ", schemeStatus="
				+ schemeStatus + ", district=" + district + ", tehsil=" + tehsil + ", villageId=" + villageId
				+ ", block=" + block + ", gramPanchayat=" + gramPanchayat + ", habitation=" + habitation
				+ ", waterSource=" + waterSource + ", collectionType=" + collectionType + ", sampleNumber="
				+ sampleNumber + ", testType=" + testType + ", poiType=" + poiType + ", status=" + status
				+ ", locationId=" + locationId + ", createdBy=" + createdBy + ", emailDwss=" + emailDwss
				+ ", mobileNoDwss=" + mobileNoDwss + ", enteredBy=" + enteredBy + ", lab=" + lab + ", email=" + email
				+ ", poiId=" + poiId + ", collDate=" + collDate + ", sampleId=" + sampleId + ", landMark=" + landMark
				+ ", urban=" + urban + ", rural=" + rural + ", city=" + city + ", sampleotherdetails="
				+ sampleotherdetails + ", bottleType=" + bottleType + ", letterRefNum=" + letterRefNum + ", quantity="
				+ quantity + ", depth=" + depth + ", schemeNameUrban=" + schemeNameUrban + ", waterSourceUrban="
				+ waterSourceUrban + ", labName=" + labName + ", waterSourceName=" + waterSourceName + ", address="
				+ address + "]";
	}

	

	
	
	

	
	
	
	
	

}

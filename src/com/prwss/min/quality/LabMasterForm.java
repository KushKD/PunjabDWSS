/**
 * 
 */
package com.prwss.min.quality;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author bhsingh
 *
 */
public class LabMasterForm extends ValidatorForm {

	private static final long serialVersionUID = -233110623438618776L;

	private String labName;
	private String schemeName;
	private String schemeStatus;
	private String district;
	private String tehsil;
	private String villageId;
	private String block;
	private String status;
	private String locationId;
	private String startDate;
	private String endDate;
	private long enteredBy;
	private String labId;
	private String districtName;
	private String blockName;
	
	private String labLevel;
	private String phoneNo;
	private String address;
	private String postalAddress;
	private String contactPerson;
	private String mobileNo;
	private String division;

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getLabLevel() {
		return labLevel;
	}

	public void setLabLevel(String labLevel) {
		this.labLevel = labLevel;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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

	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	/**
	 * @return the enteredBy
	 */
	public long getEnteredBy() {
		return enteredBy;
	}

	/**
	 * @param enteredBy
	 *            the enteredBy to set
	 */
	public void setEnteredBy(long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeStatus() {
		return schemeStatus;
	}

	public void setSchemeStatus(String schemeStatus) {
		this.schemeStatus = schemeStatus;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LabMasterForm [labName=" + labName + ", schemeName=" + schemeName + ", schemeStatus=" + schemeStatus
				+ ", district=" + district + ", tehsil=" + tehsil + ", villageId=" + villageId + ", block=" + block
				+ ", status=" + status + ", locationId=" + locationId + ", startDate=" + startDate + ", endDate="
				+ endDate + ", enteredBy=" + enteredBy + ", labId=" + labId + ", districtName=" + districtName
				+ ", blockName=" + blockName + "]";
	}

}

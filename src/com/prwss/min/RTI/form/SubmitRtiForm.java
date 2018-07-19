package com.prwss.min.RTI.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class SubmitRtiForm extends ValidatorForm {
	/**
	* 
	*/
	private static final long serialVersionUID = -4230186503255818032L;

	private String rtiID;
	private String rtiRefNo;
	private String office;
	private String zone;
	private String circle;
	private String district;
	private String division;
	private String subdivision;
	private String wings;
	private String pio;
	private String rtiType;
	private String name;
	private String gender;
	private String landline;
	private String mobile;
	private String districta;
	private String block;
	private String villageId;
	private String email;
	private String address;
	private String literate;
	private String poverty;
	private String shortname;
	private String details;
	private String applicationFileName;
	private FormFile applicationFile;
	private String documentsFileName;
	private FormFile documentsFile;
	private String paymentMode;
	private String datePayment;
	private String amount;
	private String remarks;
	private String number;
	private String paymentVia;
	private Long userID;
	private String createdDate;
	private Long createdByUser;
	private String activeFlag;
	private String locationId;
	private String viewDetails;
	private String responseRemarks;
	private String responseName;
	private FormFile responseFile;
	
	

	public String getRtiType() {
		return rtiType;
	}
	public void setRtiType(String rtiType) {
		this.rtiType = rtiType;
	}
	public String getApplicationFileName() {
		return applicationFileName;
	}
	public void setApplicationFileName(String applicationFileName) {
		this.applicationFileName = applicationFileName;
	}
	public String getDocumentsFileName() {
		return documentsFileName;
	}
	public void setDocumentsFileName(String documentsFileName) {
		this.documentsFileName = documentsFileName;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getRtiRefNo() {
		return rtiRefNo;
	}
	public void setRtiRefNo(String rtiRefNo) {
		this.rtiRefNo = rtiRefNo;
	}
	public String getRtiID() {
		return rtiID;
	}
	public void setRtiID(String rtiID) {
		this.rtiID = rtiID;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getSubdivision() {
		return subdivision;
	}
	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}
	public String getWings() {
		return wings;
	}
	public void setWings(String wings) {
		this.wings = wings;
	}
	public String getPio() {
		return pio;
	}
	public void setPio(String pio) {
		this.pio = pio;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDistricta() {
		return districta;
	}
	public void setDistricta(String districta) {
		this.districta = districta;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLiterate() {
		return literate;
	}
	public void setLiterate(String literate) {
		this.literate = literate;
	}
	public String getPoverty() {
		return poverty;
	}
	public void setPoverty(String poverty) {
		this.poverty = poverty;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getDatePayment() {
		return datePayment;
	}
	public void setDatePayment(String datePayment) {
		this.datePayment = datePayment;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPaymentVia() {
		return paymentVia;
	}
	public void setPaymentVia(String paymentVia) {
		this.paymentVia = paymentVia;
	}
	
	
	
	
	
	
	
	public FormFile getApplicationFile() {
		return applicationFile;
	}
	public void setApplicationFile(FormFile applicationFile) {
		this.applicationFile = applicationFile;
	}
	public FormFile getDocumentsFile() {
		return documentsFile;
	}
	public void setDocumentsFile(FormFile documentsFile) {
		this.documentsFile = documentsFile;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Long getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(Long createdByUser) {
		this.createdByUser = createdByUser;
	}	
	public String getViewDetails() {
		return viewDetails;
	}
	public void setViewDetails(String viewDetails) {
		this.viewDetails = viewDetails;
	}
	public String getResponseRemarks() {
		return responseRemarks;
	}
	public void setResponseRemarks(String responseRemarks) {
		this.responseRemarks = responseRemarks;
	}
	public String getResponseName() {
		return responseName;
	}
	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}
	public FormFile getResponseFile() {
		return responseFile;
	}
	public void setResponseFile(FormFile responseFile) {
		this.responseFile = responseFile;
	}
	@Override
	public String toString() {
		return "SubmitRtiForm [zone=" + zone + ", circle=" + circle + ", district=" + district + ", division="
				+ division + ", subdivision=" + subdivision + ", wings=" + wings + ", pio=" + pio + ", name=" + name
				+ ", gender=" + gender + ", landline=" + landline + ", mobile=" + mobile + ", districta=" + districta
				+ ", block=" + block + ", villageId=" + villageId + ", email=" + email + ", address=" + address
				+ ", literate=" + literate + ", poverty=" + poverty + ", shortname=" + shortname + ", details="
				+ details + ", paymentMode="
				+ paymentMode + ", datePayment=" + datePayment + ", amount=" + amount + ", remarks=" + remarks
				+ ", number=" + number + ", paymentVia=" + paymentVia + "]";
	}
	
	
		
	
	
	
	
	
	
}
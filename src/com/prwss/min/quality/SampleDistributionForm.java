package com.prwss.min.quality;

import org.apache.struts.validator.ValidatorForm;

public class SampleDistributionForm extends ValidatorForm {
	private static final long serialVersionUID = 363523815L;
	
	private String sampleNum;
	private String distributionId;
	private String labName;
	private String locationName;
	private String distributionDate;
	private String technician;
	private String samplePartNum;
	private String tests;
	private String requiredBy;
	private String sampleType;
	private String freeze;
	private String labId;
	private String sampleId;
	
	
	
	
	public String getFreeze() {
		return freeze;
	}
	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}
	public String getSampleId() {
		return sampleId;
	}
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	public String getLabId() {
		return labId;
	}
	public void setLabId(String labId) {
		this.labId = labId;
	}
	public String getSampleType() {
		return sampleType;
	}
	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}
	public String getDistributionId() {
		return distributionId;
	}
	public void setDistributionId(String distributionId) {
		this.distributionId = distributionId;
	}
	public String getSampleNum() {
		return sampleNum;
	}
	public void setSampleNum(String sampleNum) {
		this.sampleNum = sampleNum;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public String getTechnician() {
		return technician;
	}
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	public String getSamplePartNum() {
		return samplePartNum;
	}
	public void setSamplePartNum(String samplePartNum) {
		this.samplePartNum = samplePartNum;
	}
	public String getTests() {
		return tests;
	}
	public void setTests(String tests) {
		this.tests = tests;
	}

	public String getRequiredBy() {
		return requiredBy;
	}
	public void setRequiredBy(String requiredBy) {
		this.requiredBy = requiredBy;
	}
	public String getDistributionDate() {
		return distributionDate;
	}
	public void setDistributionDate(String distributionDate) {
		this.distributionDate = distributionDate;
	}
	@Override
	public String toString() {
		return "SampleDistributionForm [sampleNum=" + sampleNum + ", distributionId=" + distributionId + ", labName="
				+ labName + ", locationName=" + locationName + ", distributionDate=" + distributionDate
				+ ", technician=" + technician + ", samplePartNum=" + samplePartNum + ", tests=" + tests
				+ ", requiredBy=" + requiredBy + ", sampleType=" + sampleType + ", freeze=" + freeze + ", labId="
				+ labId + ", sampleId=" + sampleId + "]";
	}

}

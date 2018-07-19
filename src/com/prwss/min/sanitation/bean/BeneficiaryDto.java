package com.prwss.min.sanitation.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

public class BeneficiaryDto implements Serializable {

	private static final long serialVersionUID = -88144302058L;

	private int beneficiaryId;
	private String name;
	private String fatherSpouseName;
	private Long aadharNo;
	private String religion;
	private String electConnNumber;
	private Integer baselineSurveyId;
	private Integer surveyId;
	private String surveyName;
	private String purpose;
	private Date actualStartDate;
	private Date actEndDate;
	private Integer districtId;
	private Integer blockId;
	private Integer villageId;
	private Integer reviewAuthority;
	private Integer surveyReviewId;
	private Integer validationRequestId;
	private String remarks;
	private String action;
	private String isSelected="";
	private String updateStatus;
	private Integer progressStageId;
	private String stageName;
	private Long amount;
	private Integer paymetRequestId;
	private String approvalNo;
	private Integer requestDetailId;
	private Integer activeFlag;
	private byte[] pictureStage;
	private Date crt_date;
	private String billno;
	private Long totalBeneficiary;
	private String image;
	private String creationDate;
	
	
	
	
	
	
	
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getTotalBeneficiary() {
		return totalBeneficiary;
	}
	public void setTotalBeneficiary(Long totalBeneficiary) {
		this.totalBeneficiary = totalBeneficiary;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public Date getCrt_date() {
		return crt_date;
	}
	public void setCrt_date(Date crt_date) {
		this.crt_date = crt_date;
	}
	public byte[] getPictureStage() {
		return pictureStage;
	}
	public void setPictureStage(byte[] pictureStage) {
		this.pictureStage = pictureStage;
	}
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Integer getRequestDetailId() {
		return requestDetailId;
	}
	public void setRequestDetailId(Integer requestDetailId) {
		this.requestDetailId = requestDetailId;
	}
	public String getApprovalNo() {
		return approvalNo;
	}
	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}
	public Integer getPaymetRequestId() {
		return paymetRequestId;
	}
	public void setPaymetRequestId(Integer paymetRequestId) {
		this.paymetRequestId = paymetRequestId;
	}
	public Integer getProgressStageId() {
		return progressStageId;
	}
	public void setProgressStageId(Integer progressStageId) {
		this.progressStageId = progressStageId;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	public String getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getValidationRequestId() {
		return validationRequestId;
	}
	public void setValidationRequestId(Integer validationRequestId) {
		this.validationRequestId = validationRequestId;
	}
	public Integer getReviewAuthority() {
		return reviewAuthority;
	}
	public void setReviewAuthority(Integer reviewAuthority) {
		this.reviewAuthority = reviewAuthority;
	}
	public Integer getSurveyReviewId() {
		return surveyReviewId;
	}
	public void setSurveyReviewId(Integer surveyReviewId) {
		this.surveyReviewId = surveyReviewId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public Integer getBlockId() {
		return blockId;
	}
	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}
	public Integer getVillageId() {
		return villageId;
	}
	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Date getActEndDate() {
		return actEndDate;
	}
	public void setActEndDate(Date actEndDate) {
		this.actEndDate = actEndDate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public Integer getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}


	public int getBeneficiaryId() {
		return beneficiaryId;
	}


	public void setBeneficiaryId(int beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}


	public Integer getBaselineSurveyId() {
		return baselineSurveyId;
	}


	public void setBaselineSurveyId(Integer baselineSurveyId) {
		this.baselineSurveyId = baselineSurveyId;
	}




	public String getElectConnNumber() {
		return electConnNumber;
	}

	
	public void setElectConnNumber(String electConnNumber) {
		this.electConnNumber = electConnNumber;
	}

	public int getBeneficieryId() {
		return beneficiaryId;
	}

	public void setBeneficieryId(int beneficieryId) {
		this.beneficiaryId = beneficieryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherSpouseName() {
		return fatherSpouseName;
	}

	public void setFatherSpouseName(String fatherSpouseName) {
		this.fatherSpouseName = fatherSpouseName;
	}

	public Long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}
	@Override
	public String toString() {
		return "BeneficiaryDto [beneficiaryId=" + beneficiaryId + ", name=" + name + ", fatherSpouseName="
				+ fatherSpouseName + ", aadharNo=" + aadharNo + ", religion=" + religion + ", electConnNumber="
				+ electConnNumber + ", baselineSurveyId=" + baselineSurveyId + ", surveyId=" + surveyId
				+ ", surveyName=" + surveyName + ", purpose=" + purpose + ", actualStartDate=" + actualStartDate
				+ ", actEndDate=" + actEndDate + ", districtId=" + districtId + ", blockId=" + blockId + ", villageId="
				+ villageId + ", reviewAuthority=" + reviewAuthority + ", surveyReviewId=" + surveyReviewId
				+ ", validationRequestId=" + validationRequestId + ", remarks=" + remarks + ", action=" + action
				+ ", isSelected=" + isSelected + ", updateStatus=" + updateStatus + ", progressStageId="
				+ progressStageId + ", stageName=" + stageName + ", amount=" + amount + ", paymetRequestId="
				+ paymetRequestId + ", approvalNo=" + approvalNo + ", requestDetailId=" + requestDetailId
				+ ", activeFlag=" + activeFlag + ", pictureStage=" + Arrays.toString(pictureStage) + ", crt_date="
				+ crt_date + ", billno=" + billno + ", totalBeneficiary=" + totalBeneficiary + "]";
	}
	
	
}

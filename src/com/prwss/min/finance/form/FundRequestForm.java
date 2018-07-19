/**
 * 
 */
package com.prwss.min.finance.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class FundRequestForm extends ValidatorForm{

	private static final long serialVersionUID = 3635577615L;
	
	private String installment;
	private String division;
	private String district;
	private String block;
	private String gpwsc;
	private String requestNo;
	private String schemeType;
	private String schemeName;
	private String approvalNo;
	private String value;
	private String date;
	private String misCode;
	private String sanctionApprovalNo;
	private String sanctionValue;
	private String sanctionDate;
	private String worldBankAppdate;
	private String worldBankApprovalNo;
	private String due;
	private String actuallyCollected;
	private String netDSR;
	private String procDate;
	private String procNumber;
	private String awardDate;
	private String awardNumber;
	private String awardValue;
	private String valueInstallment;
	private String schemePackage;
	private String packageNo;
	private String worldBankDate;
	private FormFile attachment;
	/*private Datagrid fundRequestGrid;
	
	
	
	
	
	public Datagrid getFundRequestGrid() {
		return fundRequestGrid;
	}
	public void setFundRequestGrid(Datagrid fundRequestGrid) {
		this.fundRequestGrid = fundRequestGrid;
	}*/
	public FormFile getAttachment() {
		return attachment;
	}
	public void setAttachment(FormFile attachment) {
		this.attachment = attachment;
	}
	public String getWorldBankDate() {
		return worldBankDate;
	}
	public void setWorldBankDate(String worldBankDate) {
		this.worldBankDate = worldBankDate;
	}
	public String getInstallment() {
		return installment;
	}
	public void setInstallment(String installment) {
		this.installment = installment;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getGpwsc() {
		return gpwsc;
	}
	public void setGpwsc(String gpwsc) {
		this.gpwsc = gpwsc;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getApprovalNo() {
		return approvalNo;
	}
	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMisCode() {
		return misCode;
	}
	public void setMisCode(String misCode) {
		this.misCode = misCode;
	}
	public String getSanctionApprovalNo() {
		return sanctionApprovalNo;
	}
	public void setSanctionApprovalNo(String sanctionApprovalNo) {
		this.sanctionApprovalNo = sanctionApprovalNo;
	}
	public String getSanctionValue() {
		return sanctionValue;
	}
	public void setSanctionValue(String sanctionValue) {
		this.sanctionValue = sanctionValue;
	}
	public String getSanctionDate() {
		return sanctionDate;
	}
	public void setSanctionDate(String sanctionDate) {
		this.sanctionDate = sanctionDate;
	}
	public String getWorldBankAppdate() {
		return worldBankAppdate;
	}
	public void setWorldBankAppdate(String worldBankAppdate) {
		this.worldBankAppdate = worldBankAppdate;
	}
	public String getWorldBankApprovalNo() {
		return worldBankApprovalNo;
	}
	public void setWorldBankApprovalNo(String worldBankApprovalNo) {
		this.worldBankApprovalNo = worldBankApprovalNo;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public String getActuallyCollected() {
		return actuallyCollected;
	}
	public void setActuallyCollected(String actuallyCollected) {
		this.actuallyCollected = actuallyCollected;
	}
	public String getNetDSR() {
		return netDSR;
	}
	public void setNetDSR(String netDSR) {
		this.netDSR = netDSR;
	}
	public String getProcDate() {
		return procDate;
	}
	public void setProcDate(String procDate) {
		this.procDate = procDate;
	}
	public String getProcNumber() {
		return procNumber;
	}
	public void setProcNumber(String procNumber) {
		this.procNumber = procNumber;
	}
	public String getAwardDate() {
		return awardDate;
	}
	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
	}
	public String getAwardNumber() {
		return awardNumber;
	}
	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}
	public String getAwardValue() {
		return awardValue;
	}
	public void setAwardValue(String awardValue) {
		this.awardValue = awardValue;
	}
	public String getValueInstallment() {
		return valueInstallment;
	}
	public void setValueInstallment(String valueInstallment) {
		this.valueInstallment = valueInstallment;
	}
	public String getSchemePackage() {
		return schemePackage;
	}
	public void setSchemePackage(String schemePackage) {
		this.schemePackage = schemePackage;
	}
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	
	@Override
	public String toString() {
		return "FundRequestForm [installment=" + installment + ", division=" + division + ", district=" + district
				+ ", block=" + block + ", gpwsc=" + gpwsc + ", requestNo=" + requestNo + ", schemeType=" + schemeType
				+ ", schemeName=" + schemeName + ", approvalNo=" + approvalNo + ", value=" + value + ", date=" + date
				+ ", misCode=" + misCode + ", sanctionApprovalNo=" + sanctionApprovalNo + ", sanctionValue="
				+ sanctionValue + ", sanctionDate=" + sanctionDate + ", worldBankAppdate=" + worldBankAppdate
				+ ", worldBankApprovalNo=" + worldBankApprovalNo + ", due=" + due + ", actuallyCollected="
				+ actuallyCollected + ", netDSR=" + netDSR + ", procDate=" + procDate + ", procNumber=" + procNumber
				+ ", awardDate=" + awardDate + ", awardNumber=" + awardNumber + ", awardValue=" + awardValue
				+ ", valueInstallment=" + valueInstallment + ", schemePackage=" + schemePackage + ", packageNo="
				+ packageNo + "]";
	}
}

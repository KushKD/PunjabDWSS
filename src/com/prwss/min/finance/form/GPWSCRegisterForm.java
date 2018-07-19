/**
 * 
 */
package com.prwss.min.finance.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class GPWSCRegisterForm  extends ValidatorForm{

	private static final long serialVersionUID = 3635577635L;
	
	private String division;
	private String subDivision;
	private String village;
	private String gPWSC;
	private String schemeName;
	private String transactionDate;
	private String transactionType;
	private String bank;
	private String payeeName;
	private String billNo;
	private String paymentType;
	private String amount;
	private String paymentRemark;
	private String transactionNo;
	private String receiptType;
	private String receiptAmount;
	private String receiptRemark;
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getSubDivision() {
		return subDivision;
	}
	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getgPWSC() {
		return gPWSC;
	}
	public void setgPWSC(String gPWSC) {
		this.gPWSC = gPWSC;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPaymentRemark() {
		return paymentRemark;
	}
	public void setPaymentRemark(String paymentRemark) {
		this.paymentRemark = paymentRemark;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	public String getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public String getReceiptRemark() {
		return receiptRemark;
	}
	public void setReceiptRemark(String receiptRemark) {
		this.receiptRemark = receiptRemark;
	}
	@Override
	public String toString() {
		return "GPWSCRegisterForm [division=" + division + ", subDivision=" + subDivision + ", village=" + village
				+ ", gPWSC=" + gPWSC + ", schemeName=" + schemeName + ", transactionDate=" + transactionDate
				+ ", transactionType=" + transactionType + ", bank=" + bank + ", payeeName=" + payeeName + ", billNo="
				+ billNo + ", paymentType=" + paymentType + ", amount=" + amount + ", paymentRemark=" + paymentRemark
				+ ", transactionNo=" + transactionNo + ", receiptType=" + receiptType + ", receiptAmount="
				+ receiptAmount + ", receiptRemark=" + receiptRemark + "]";
	}
	
}

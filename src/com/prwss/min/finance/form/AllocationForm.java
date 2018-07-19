/**
 * 
 */
package com.prwss.min.finance.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class AllocationForm extends ValidatorForm {

	private static final long serialVersionUID = -36355545L;
	
	
	private String requestNo;
	private String head;
	private String allocationNumber;
	private String dateAllocation;
	private String amountReleased;
	private String revoked;
	private String netAmount;
	private String activity;
	private String value;
	private String total;
	private String amountRequested;
	private String nrdwpNormal;
	private String nrdwpSustain;
	private String nrdwpSupport;
	private String nrdwpOAndM;
	private String nrdwpWQMSP;
	private String revoke;
	
	
	
	
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getAllocationNumber() {
		return allocationNumber;
	}
	public void setAllocationNumber(String allocationNumber) {
		this.allocationNumber = allocationNumber;
	}
	public String getDateAllocation() {
		return dateAllocation;
	}
	public void setDateAllocation(String dateAllocation) {
		this.dateAllocation = dateAllocation;
	}
	public String getAmountReleased() {
		return amountReleased;
	}
	public void setAmountReleased(String amountReleased) {
		this.amountReleased = amountReleased;
	}
	public String getRevoked() {
		return revoked;
	}
	public void setRevoked(String revoked) {
		this.revoked = revoked;
	}
	public String getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getAmountRequested() {
		return amountRequested;
	}
	public void setAmountRequested(String amountRequested) {
		this.amountRequested = amountRequested;
	}
	public String getNrdwpNormal() {
		return nrdwpNormal;
	}
	public void setNrdwpNormal(String nrdwpNormal) {
		this.nrdwpNormal = nrdwpNormal;
	}
	public String getNrdwpSustain() {
		return nrdwpSustain;
	}
	public void setNrdwpSustain(String nrdwpSustain) {
		this.nrdwpSustain = nrdwpSustain;
	}
	public String getNrdwpSupport() {
		return nrdwpSupport;
	}
	public void setNrdwpSupport(String nrdwpSupport) {
		this.nrdwpSupport = nrdwpSupport;
	}
	public String getNrdwpOAndM() {
		return nrdwpOAndM;
	}
	public void setNrdwpOAndM(String nrdwpOAndM) {
		this.nrdwpOAndM = nrdwpOAndM;
	}
	public String getNrdwpWQMSP() {
		return nrdwpWQMSP;
	}
	public void setNrdwpWQMSP(String nrdwpWQMSP) {
		this.nrdwpWQMSP = nrdwpWQMSP;
	}
	public String getRevoke() {
		return revoke;
	}
	public void setRevoke(String revoke) {
		this.revoke = revoke;
	}
	@Override
	public String toString() {
		return "AllocationForm [requestNo=" + requestNo + ", head=" + head + ", allocationNumber=" + allocationNumber
				+ ", dateAllocation=" + dateAllocation + ", amountReleased=" + amountReleased + ", revoked=" + revoked
				+ ", netAmount=" + netAmount + ", activity=" + activity + ", value=" + value + ", total=" + total
				+ ", amountRequested=" + amountRequested + ", nrdwpNormal=" + nrdwpNormal + ", nrdwpSustain="
				+ nrdwpSustain + ", nrdwpSupport=" + nrdwpSupport + ", nrdwpOAndM=" + nrdwpOAndM + ", nrdwpWQMSP="
				+ nrdwpWQMSP + ", revoke=" + revoke + "]";
	}
	
}

/**
 * 
 */
package com.prwss.min.quality;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author bhsingh
 *
 */
public class ParameterMasterForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331106011745298776L;
	
	private String parameterName;
	private String uom;
	private String permissibleLimit;
	private String acceptableLimit;
	private String wHOPermissibleLimit;
	private String wHOAcceptableLimit;
	private String status;
	private String startDate;
	private	String endDate;
	private int empId;
	private String parameterId;
	private String noRelaxation;
	private Long createdByUser;
	private String uom1;
	
	

	public String getUom1() {
		return uom1;
	}
	public void setUom1(String uom1) {
		this.uom1 = uom1;
	}
	public Long getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(Long createdByUser) {
		this.createdByUser = createdByUser;
	}
	public String getNoRelaxation() {
		return noRelaxation;
	}
	public void setNoRelaxation(String noRelaxation) {
		this.noRelaxation = noRelaxation;
	}
	public String getParameterId() {
		return parameterId;
	}
	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}
	/**
	 * @return the empId
	 */
	public int getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getPermissibleLimit() {
		return permissibleLimit;
	}
	public void setPermissibleLimit(String permissibleLimit) {
		this.permissibleLimit = permissibleLimit;
	}
	public String getAcceptableLimit() {
		return acceptableLimit;
	}
	public void setAcceptableLimit(String acceptableLimit) {
		this.acceptableLimit = acceptableLimit;
	}
	public String getwHOPermissibleLimit() {
		return wHOPermissibleLimit;
	}
	public void setwHOPermissibleLimit(String wHOPermissibleLimit) {
		this.wHOPermissibleLimit = wHOPermissibleLimit;
	}
	public String getwHOAcceptableLimit() {
		return wHOAcceptableLimit;
	}
	public void setwHOAcceptableLimit(String wHOAcceptableLimit) {
		this.wHOAcceptableLimit = wHOAcceptableLimit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	@Override
	public String toString() {
		return "ParameterMasterForm [parameterName=" + parameterName + ", uom=" + uom + ", permissibleLimit="
				+ permissibleLimit + ", acceptableLimit=" + acceptableLimit + ", wHOPermissibleLimit="
				+ wHOPermissibleLimit + ", wHOAcceptableLimit=" + wHOAcceptableLimit + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", empId=" + empId + ", parameterId="
				+ parameterId + "]";
	}
}

/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

/**
 * @author BH390738
 *
 */
public class EmployeeDetailsGridBean implements Serializable{

	private static final long serialVersionUID=-123456543L;
	
	private String employeeId;
	private String employeeName;
	private String designationId;
	private String designationName;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDesignationId() {
		return designationId;
	}
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	@Override
	public String toString() {
		return "EmployeeDetailsGridBean [employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", designationId=" + designationId + ", designationName=" + designationName + "]";
	}
}

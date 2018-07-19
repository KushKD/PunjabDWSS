package com.prwss.min.RTI.form;

import org.apache.struts.validator.ValidatorForm;

public class AssignRtiForm extends ValidatorForm {
	/**
	* 
	*/
	private static final long serialVersionUID = -42301803255818032L;

	private String rtiID;
	private String designation;
	private String employee;
	private String dueDate;
	private String assignRemarks;
	
	
	
	public String getRtiID() {
		return rtiID;
	}
	public void setRtiID(String rtiID) {
		this.rtiID = rtiID;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getAssignRemarks() {
		return assignRemarks;
	}
	public void setAssignRemarks(String assignRemarks) {
		this.assignRemarks = assignRemarks;
	}

	
	
}
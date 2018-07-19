/**
 * 
 */
package com.prwss.min.construction.quality.form;

import org.apache.struts.action.ActionForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class ShareObservationForm extends ActionForm {
	
	private static final long serialVersionUID = -34555576L;
	
	private String yearPlan;
	private String month;
	private String subject;
	private String to;
	private String designation;
	private String employee;
	private String scheme;
	private String schemeStage;
	private String checkedFor;
	private String dateOfInspection;
	private String remarks;
	private Datagrid schemeGrid; 
	private Datagrid employeeDetailsGrid; 
	
	private String schemeName;
	private String checkedName;
	private String stageName;
	private String employeeName;
	private String designationName;
	private String remarkss;
	
	
	public String getRemarkss() {
		return remarkss;
	}
	public void setRemarkss(String remarkss) {
		this.remarkss = remarkss;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getCheckedName() {
		return checkedName;
	}
	public void setCheckedName(String checkedName) {
		this.checkedName = checkedName;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public Datagrid getSchemeGrid() {
		return schemeGrid;
	}
	public void setSchemeGrid(Datagrid schemeGrid) {
		this.schemeGrid = schemeGrid;
	}
	public Datagrid getEmployeeDetailsGrid() {
		return employeeDetailsGrid;
	}
	public void setEmployeeDetailsGrid(Datagrid employeeDetailsGrid) {
		this.employeeDetailsGrid = employeeDetailsGrid;
	}
	public String getYearPlan() {
		return yearPlan;
	}
	public void setYearPlan(String yearPlan) {
		this.yearPlan = yearPlan;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
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
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getSchemeStage() {
		return schemeStage;
	}
	public void setSchemeStage(String schemeStage) {
		this.schemeStage = schemeStage;
	}
	public String getCheckedFor() {
		return checkedFor;
	}
	public void setCheckedFor(String checkedFor) {
		this.checkedFor = checkedFor;
	}
	public String getDateOfInspection() {
		return dateOfInspection;
	}
	public void setDateOfInspection(String dateOfInspection) {
		this.dateOfInspection = dateOfInspection;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "ShareObservationForm [yearPlan=" + yearPlan + ", month=" + month + ", subject=" + subject + ", to=" + to
				+ ", designation=" + designation + ", employee=" + employee + ", scheme=" + scheme + ", schemeStage="
				+ schemeStage + ", checkedFor=" + checkedFor + ", dateOfInspection=" + dateOfInspection + ", remarks="
				+ remarks + ", schemeGrid=" + schemeGrid + ", employeeDetailsGrid=" + employeeDetailsGrid + "]";
	}

}

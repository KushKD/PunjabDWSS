/**
 * 
 */
package com.prwss.min.finance.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class DdoMasterForm  extends ValidatorForm{

	private static final long serialVersionUID = -363555433815L;
	
	private String zone;
	private String circle;
	private String division;
	private String ddoNumber;
	private String ddoName;
	private String teasury;
	private String status;
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
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDdoNumber() {
		return ddoNumber;
	}
	public void setDdoNumber(String ddoNumber) {
		this.ddoNumber = ddoNumber;
	}
	public String getDdoName() {
		return ddoName;
	}
	public void setDdoName(String ddoName) {
		this.ddoName = ddoName;
	}
	public String getTeasury() {
		return teasury;
	}
	public void setTeasury(String teasury) {
		this.teasury = teasury;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "DdoMasterForm [zone=" + zone + ", circle=" + circle + ", division=" + division + ", ddoNumber="
				+ ddoNumber + ", ddoName=" + ddoName + ", teasury=" + teasury + ", status=" + status + "]";
	}
}

/**
 * 
 */
package com.prwss.min.quality.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class FreezeUnFreezeForm extends ValidatorForm{
	
	private static final long serialVersionUID = 36352322815L;
	
	private String lab;
	private String fromDate;
	private String toDate;
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	@Override
	public String toString() {
		return "FreezeUnFreezeForm [lab=" + lab + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	
}

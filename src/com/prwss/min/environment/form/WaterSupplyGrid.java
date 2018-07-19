package com.prwss.min.environment.form;

import java.io.Serializable;

public class WaterSupplyGrid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3573427362812039896L;
	
	
	private String parameter;
	private String issue;
	private String measure;
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	@Override
	public String toString() {
		return "WaterSupplyGrid [parameter=" + parameter + ", issue=" + issue
				+ ", measure=" + measure + "]";
	}
	
	
	
	

}

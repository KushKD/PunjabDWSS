package com.prwss.min.SDU.form;

import java.io.Serializable;

public class DivisionActivityMpgGrid implements Serializable {
	
	private static final long serialVersionUID = 3635076755334815L; 
	
	private String financialYear;
	private String division;
	private String stage;
	private String category;
	private String component;
	private String campaign;
	private String activity;
	
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	@Override
	public String toString() {
		return "DivisionActivityMpgGrid [financialYear=" + financialYear + ", division=" + division + ", stage=" + stage
				+ ", category=" + category + ", component=" + component + ", campaign=" + campaign + ", activity="
				+ activity + "]";
	}
	
	
	
}

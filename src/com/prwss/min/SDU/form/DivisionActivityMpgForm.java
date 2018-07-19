package com.prwss.min.SDU.form;

import org.apache.struts.validator.ValidatorForm;
import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class DivisionActivityMpgForm extends ValidatorForm {
	
	private static final long serialVersionUID = -42301865032818032L;
	
	private String financialYear;
	private String financialYear1;
	private String division;
	private String division1;
	private String stage;
	private String stage1;
	private String category;
	private String category1;
	private String campaign;
	private String campaign1;
	private String component;
	private String component1;
	private Datagrid divActivityMpgGrid;
	private String activity;
	
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getFinancialYear1() {
		return financialYear1;
	}
	public void setFinancialYear1(String financialYear1) {
		this.financialYear1 = financialYear1;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDivision1() {
		return division1;
	}
	public void setDivision1(String division1) {
		this.division1 = division1;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getStage1() {
		return stage1;
	}
	public void setStage1(String stage1) {
		this.stage1 = stage1;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getCampaign1() {
		return campaign1;
	}
	public void setCampaign1(String campaign1) {
		this.campaign1 = campaign1;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getComponent1() {
		return component1;
	}
	public void setComponent1(String component1) {
		this.component1 = component1;
	}
	public Datagrid getDivActivityMpgGrid() {
		return divActivityMpgGrid;
	}
	public void setDivActivityMpgGrid(Datagrid divActivityMpgGrid) {
		this.divActivityMpgGrid = divActivityMpgGrid;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	@Override
	public String toString() {
		return "DivisionActivityMpgForm [financialYear=" + financialYear + ", financialYear1=" + financialYear1
				+ ", division=" + division + ", division1=" + division1 + ", stage=" + stage + ", stage1=" + stage1
				+ ", category=" + category + ", category1=" + category1 + ", campaign=" + campaign + ", campaign1="
				+ campaign1 + ", component=" + component + ", component1=" + component1 + ", divActivityMpgGrid="
				+ divActivityMpgGrid + ", activity=" + activity + "]";
	}
	
}

package com.prwss.min.SDU.form;

import org.apache.struts.validator.ValidatorForm;
import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class VillageActivityMpgForm extends ValidatorForm{
	
	private static final long serialVersionUID = 423018650328188032L;
	
	private String stage;
	private String category;
	private String activity;
	private String component;
	private Datagrid villageActivityMpgGrid;
	private String village;
	private String expectedStartDate;
	private String expectedEndDate;
	private String financialYear;
	private String division;
	private String stage1;
	private String category1;
	private String division1;
	private String component1;
	private String financialYear1;
	private String village1;
	
	
	
	
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
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public Datagrid getVillageActivityMpgGrid() {
		return villageActivityMpgGrid;
	}
	public void setVillageActivityMpgGrid(Datagrid villageActivityMpgGrid) {
		this.villageActivityMpgGrid = villageActivityMpgGrid;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getExpectedStartDate() {
		return expectedStartDate;
	}
	public void setExpectedStartDate(String expectedStartDate) {
		this.expectedStartDate = expectedStartDate;
	}
	public String getExpectedEndDate() {
		return expectedEndDate;
	}
	public void setExpectedEndDate(String expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}
	public String getStage1() {
		return stage1;
	}
	public void setStage1(String stage1) {
		this.stage1 = stage1;
	}
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getDivision1() {
		return division1;
	}
	public void setDivision1(String division1) {
		this.division1 = division1;
	}
	public String getComponent1() {
		return component1;
	}
	public void setComponent1(String component1) {
		this.component1 = component1;
	}
	public String getFinancialYear1() {
		return financialYear1;
	}
	public void setFinancialYear1(String financialYear1) {
		this.financialYear1 = financialYear1;
	}
	public String getVillage1() {
		return village1;
	}
	public void setVillage1(String village1) {
		this.village1 = village1;
	}
	@Override
	public String toString() {
		return "VillageActivityMpgForm [stage=" + stage + ", category=" + category + ", activity=" + activity
				+ ", component=" + component + ", villageActivityMpgGrid=" + villageActivityMpgGrid + ", village="
				+ village + ", expectedStartDate=" + expectedStartDate + ", expectedEndDate=" + expectedEndDate
				+ ", financialYear=" + financialYear + ", division=" + division + ", stage1=" + stage1 + ", category1="
				+ category1 + ", division1=" + division1 + ", component1=" + component1 + ", financialYear1="
				+ financialYear1 + ", village1=" + village1 + "]";
	}

}

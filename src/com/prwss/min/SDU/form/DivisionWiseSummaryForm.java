package com.prwss.min.SDU.form;

import org.apache.struts.validator.ValidatorForm;
import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class DivisionWiseSummaryForm extends ValidatorForm {
	/**
	* 
	*/
	private static final long serialVersionUID = -4230186503255818032L;

	private String financialYear;
	private String division;
	private String financialYear1;
	private String division1;
	private String category;
	private String noOfVillages;
	private Datagrid divWiseSumPlanGrid;
	private String categoryName;
	
	
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNoOfVillages() {
		return noOfVillages;
	}
	public void setNoOfVillages(String noOfVillages) {
		this.noOfVillages = noOfVillages;
	}
	public Datagrid getDivWiseSumPlanGrid() {
		return divWiseSumPlanGrid;
	}
	public void setDivWiseSumPlanGrid(Datagrid divWiseSumPlanGrid) {
		this.divWiseSumPlanGrid = divWiseSumPlanGrid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getFinancialYear1() {
		return financialYear1;
	}
	public void setFinancialYear1(String financialYear1) {
		this.financialYear1 = financialYear1;
	}
	public String getDivision1() {
		return division1;
	}
	public void setDivision1(String division1) {
		this.division1 = division1;
	}
	@Override
	public String toString() {
		return "DivisionWiseSummaryForm [financialYear=" + financialYear + ", division=" + division
				+ ", financialYear1=" + financialYear1 + ", division1=" + division1 + ", category=" + category
				+ ", noOfVillages=" + noOfVillages + ", divWiseSumPlanGrid=" + divWiseSumPlanGrid + ", categoryName="
				+ categoryName + "]";
	}
	
	
	
}
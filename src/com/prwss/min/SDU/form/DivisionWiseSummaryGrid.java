package com.prwss.min.SDU.form;

import java.io.Serializable;

public class DivisionWiseSummaryGrid implements Serializable {
	
	private static final long serialVersionUID = 36350765334815L;
	
	private String category;
	private String noOfVillages;
	private String categoryName;
	private String division;
	private String financialYear;
	
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	@Override
	public String toString() {
		return "DivisionWiseSummaryGrid [category=" + category + ", noOfVillages=" + noOfVillages + ", categoryName="
				+ categoryName + ", division=" + division + ", financialYear=" + financialYear + "]";
	}

}

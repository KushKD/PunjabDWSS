package com.prwss.min.SDU.form;

import org.apache.struts.validator.ValidatorForm;

public class ConsolidatedPlanActivityWiseForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6361505985175286135L;
	
	private String financialYear;

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	@Override
	public String toString() {
		return "ConsolidatedPlanActivityWiseForm [financialYear=" + financialYear + "]";
	}
	
	
	

}

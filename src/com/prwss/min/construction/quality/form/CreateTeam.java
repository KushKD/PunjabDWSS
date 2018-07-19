/**
 * 
 */
package com.prwss.min.construction.quality.form;

import org.apache.struts.action.ActionForm;

/**
 * @author BH390738
 *
 */
public class CreateTeam extends ActionForm  {

	private static final long serialVersionUID = 363507654815L;
	
	private String teamName;
	private String financialYear;
	private String details;
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "CreateTeam [teamName=" + teamName + ", financialYear=" + financialYear + ", details=" + details + "]";
	}
}

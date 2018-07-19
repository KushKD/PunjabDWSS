/**
 * 
 */
package com.prwss.min.finance.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class FinanceHeadsForm extends ValidatorForm {

	private static final long serialVersionUID = 3635577654815L;

	private String headType;
	private String description;
	private String number;
	private String parent;
	private String financeHeadId;
	
	
	
	
	public String getFinanceHeadId() {
		return financeHeadId;
	}
	public void setFinanceHeadId(String financeHeadId) {
		this.financeHeadId = financeHeadId;
	}
	public String getHeadType() {
		return headType;
	}
	public void setHeadType(String headType) {
		this.headType = headType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "FinanceHeadsForm [headType=" + headType + ", description=" + description + ", number=" + number
				+ ", parent=" + parent + "]";
	}
	
}

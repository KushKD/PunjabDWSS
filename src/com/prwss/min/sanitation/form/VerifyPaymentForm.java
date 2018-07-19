/**
 * 
 */
package com.prwss.min.sanitation.form;

import org.apache.struts.action.ActionForm;

/**
 * @author BH390738
 *
 */
public class VerifyPaymentForm extends ActionForm{

	private static final long serialVersionUID = -8813762132302058L;
	private String validationRequestId;
	private Long enterBy;
	
	
	public Long getEnterBy() {
		return enterBy;
	}
	public void setEnterBy(Long enterBy) {
		this.enterBy = enterBy;
	}
	public String getValidationRequestId() {
		return validationRequestId;
	}
	public void setValidationRequestId(String validationRequestId) {
		this.validationRequestId = validationRequestId;
	}
	
	
	@Override
	public String toString() {
		return "VerifyPaymentForm [validationRequestId=" + validationRequestId + ", enterBy=" + enterBy + "]";
	}

}

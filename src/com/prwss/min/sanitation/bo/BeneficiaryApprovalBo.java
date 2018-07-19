/**
 * 
 */
package com.prwss.min.sanitation.bo;

import com.prwss.min.sanitation.form.BeneficiaryApprovalForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface BeneficiaryApprovalBo {
	public boolean updateValidationRequestToReturn(BeneficiaryApprovalForm forwardBeneficiaryForm) throws MISException;
	public boolean updateValidationRequestToInitiator(BeneficiaryApprovalForm forwardBeneficiaryForm) throws MISException;
	public boolean updateRequest(BeneficiaryApprovalForm forwardBeneficiaryForm) throws MISException;

}

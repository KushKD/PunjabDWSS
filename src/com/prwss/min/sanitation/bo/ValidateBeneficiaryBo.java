/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.List;

import org.json.simple.JSONArray;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.form.ForwardBeneficiaryForm;
import com.prwss.min.sanitation.form.ReValidateBeneficiary;
import com.prwss.min.sanitation.form.ValidateBeneficiaryForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface ValidateBeneficiaryBo {

	public List<BeneficiaryDto> getBeneficiaryDetails(ValidateBeneficiaryForm validateBeneficiaryForm)
			throws MISException;

	public boolean saveBeneficiaryDetails(JSONArray jsonArray, String surveyId, String entBy) throws MISException;

	public boolean updateValidationRequestToReturn(ReValidateBeneficiary forwardBeneficiaryForm) throws MISException;
	public boolean updateValidationRequestToInitiator(ReValidateBeneficiary forwardBeneficiaryForm) throws MISException;
	public boolean updateRequest(ReValidateBeneficiary forwardBeneficiaryForm) throws MISException;

	public boolean updateValidationRequest(String[] validateRequestId, String entBy) throws MISException;
	
	
	
}

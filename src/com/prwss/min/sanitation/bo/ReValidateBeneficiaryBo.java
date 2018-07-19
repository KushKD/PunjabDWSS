/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.List;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.form.ForwardBeneficiaryForm;
import com.prwss.min.sanitation.form.ReValidateBeneficiary;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface ReValidateBeneficiaryBo {
	public List<BeneficiaryDto> getValidateBeneficiary(String surveyId,Long entBy) throws MISException;
	public boolean saveBeneficiary(ReValidateBeneficiary validateBeneficiary) throws MISException;
	public  List<BeneficiaryDto> getValidatedBeneficiary(String surveyId,Long entBy)throws MISException;
	public  List<BeneficiaryDto> getReValidatedBeneficiary(String surveyId,Long entBy)throws MISException;
}

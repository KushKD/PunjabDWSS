package com.prwss.min.sanitation.bo;

import java.util.List;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface ValidateRequestBo {

	public List<BeneficiaryDto> getFreezedSurvey() throws MISException;
	public boolean saveEmpSurvey(String surveyId,Long entBy) throws MISException;
	
	
}

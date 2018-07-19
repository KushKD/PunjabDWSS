/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.List;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.form.BaseLineForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface BaseLineBo {

	public List<BeneficiaryDto> getBeneficiaryDetails(BaseLineForm baseLineForm) throws MISException;
	public List<BeneficiaryDto> getSurveyDetails(BaseLineForm baseLineForm) throws MISException;
	public boolean saveBeneficiaryDetails(BaseLineForm baseLineForm,String beneficiaryId) throws MISException;
	public boolean updateSurvey(String surveyId,String baseLineId) throws MISException;
}

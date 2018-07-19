/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;

/**
 * @author BH390738
 *
 */
public interface ValidateRequestDao {
	public List<BeneficiaryDto> getFreezedSurvey() throws DataAccessException;
	public List<BeneficiaryDto> findFreezedSurvey(String baselineSurveyId) throws DataAccessException;
	public List<BeneficiaryDto> getSurveyDetails(String surveyId) throws DataAccessException;
	public List<BeneficiaryDto> getEmployeeDetails(BeneficiaryDto beneficiaryDto) throws DataAccessException;
	
	public boolean saveSurveyEmpDetails(List<SurveyValidationRequest> beneficiaryDtos) throws DataAccessException;
}

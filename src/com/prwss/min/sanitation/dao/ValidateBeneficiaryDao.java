/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.min.sanitation.bean.ValidateBeneficiaryBean;
import com.prwss.min.sanitation.form.ValidateBeneficiaryForm;

/**
 * @author BH390738
 *
 */
public interface ValidateBeneficiaryDao {

	public List<BeneficiaryDto> getBeneficiaryDetails(ValidateBeneficiaryForm validateBeneficiaryForm) throws DataAccessException ;
	
	public List<BeneficiaryDto> getValidationRequstId(String surveyId) throws DataAccessException ;
	
	public boolean saveBeneficiaryDetails(List<ValidateBeneficiaryBean> validateBeneficiaryBeans)throws DataAccessException;
	public List<BeneficiaryDto> getBeneficiary(String surveyId)throws DataAccessException;
	
	public List<BeneficiaryDto> getAddedBeneficiary(String surveyId,Long enteredBy)throws DataAccessException;
	
	public List<SurveyValidationRequest> getSurveyValidation(String validationRequest)throws DataAccessException;
	
	public List<WorkFlowMasterBean> getEmployeeDetails(Integer userId)throws DataAccessException;
	
	public boolean updateSurveyValidation(SurveyValidationRequest surveyValidationRequests)throws DataAccessException;

}

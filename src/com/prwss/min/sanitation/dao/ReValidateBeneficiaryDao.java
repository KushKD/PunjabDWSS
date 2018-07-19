/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.min.sanitation.bean.ValidateBeneficiaryBean;

/**
 * @author BH390738
 *
 */
public interface ReValidateBeneficiaryDao {
	public List<BeneficiaryDto> getValidateBeneficiary(String surveyId,Long entBy) throws DataAccessException;
	public List<BeneficiaryDto> getValidatedBeneficiary(String surveyId,Long entBy) throws DataAccessException;
	public boolean saveBeneficiary(List<ValidateBeneficiaryBean> validateBeneficiaryBeans) throws DataAccessException;
	public List<SurveyValidationRequest> findUser(String surveyId,Long entBy)throws DataAccessException;
}

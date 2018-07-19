/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.BaseLineMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.form.BaseLineForm;

/**
 * @author BH390738
 *
 */
public interface BaseLineDao {

	public List<BeneficiaryDto> getBeneficiaryDetail(BaseLineForm baseLineForm) throws DataAccessException;

	public boolean saveBaseLineSurvey(List<BaseLineMasterBean> baseLineMasterBean) throws DataAccessException;

	public List<BeneficiaryDto> getSurveyDetails(BaseLineForm baseLineForm) throws DataAccessException;
	
	public List<BaseLineMasterBean> fetchSurveyDetails(Integer[] surveyId) throws DataAccessException;

	
	public List<BaseLineMasterBean> verifySurveyStatus(BaseLineForm baseLineForm) throws DataAccessException;

	public boolean deleteSurveyDetails(String beneficiaryId) throws DataAccessException;
	public boolean updateSurvey(List<BaseLineMasterBean> baseLineMasterBean) throws DataAccessException;
}

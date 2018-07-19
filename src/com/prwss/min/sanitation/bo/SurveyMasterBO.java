package com.prwss.min.sanitation.bo;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.form.SurveyMasterForm;
import com.prwss.mis.common.exception.MISException;

public interface SurveyMasterBO<T> {
	
	public boolean saveSurveyMasterData(
			SurveyMasterForm surveymasterform) throws MISException;

	public List<SurveyMasterForm> getSurveyMasterByPagination() throws DataAccessException;
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
	List<T> getListBasedOnSearchParameter(String searchParameter, List<T> formList);

	public boolean updateSurvey(SurveyMasterForm surveyMasterForm) throws MISException;
}
